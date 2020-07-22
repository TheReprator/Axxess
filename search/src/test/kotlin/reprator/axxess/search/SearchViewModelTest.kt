package reprator.axxess.search

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import androidx.lifecycle.SavedStateHandle
import com.google.common.truth.Truth
import io.mockk.*
import io.mockk.impl.annotations.MockK
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import reprator.axxess.base.util.AppCoroutineDispatchers
import reprator.axxess.base.util.useCases.Success
import reprator.axxess.base_android.SearchModal
import reprator.axxess.search.domain.usecase.SearchUseCase

class SearchViewModelTest {

    @get:Rule
    val coroutinesTestRule = CoroutinesTestRule()

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @MockK
    lateinit var searchUseCase: SearchUseCase

    lateinit var coroutineDispatchers: AppCoroutineDispatchers

    @MockK
    lateinit var savedStateHandle: SavedStateHandle

    lateinit var searchViewModel: SearchViewModel

    private val observerSuccessList = mockk<Observer<List<SearchModal>>>(relaxed = true)
    private val observerLoader = mockk<Observer<Boolean>>(relaxed = true)

    private val QUERY = "apple"

    private val EMPTY_QUERY: String = ""

    @Before
    fun setup() {
        MockKAnnotations.init(this)

        coroutineDispatchers = AppCoroutineDispatchersImpl(
            Dispatchers.Unconfined, Dispatchers.Unconfined,
            Dispatchers.Unconfined, Dispatchers.Unconfined, Dispatchers.Unconfined
        )

        every { savedStateHandle.get<String>(any()) } returns EMPTY_QUERY
        every { savedStateHandle.set(any(), any<String>()) } returns Unit

        searchViewModel =
            SearchViewModel(searchUseCase, coroutineDispatchers, savedStateHandle).apply {
                searchItemList.observeForever(observerSuccessList)
                showLoader.observeForever(observerLoader)
            }
    }

    @After
    fun tearDown() {
        clearAllMocks()
    }

    @Test
    fun `fetch updated response from server, if multiple input is send within 250ms`() =
        coroutinesTestRule.testDispatcher.runBlockingTest {

            // Given
            val searchModalList1 = emptyList<SearchModal>()
            val searchModalList2 = listOf(
                SearchModal("1", "apple", "img1")
            )
            val searchModalList3 = listOf(
                SearchModal("11", "orange", "img1"),
                SearchModal("222", "Mausami", "img2")
            )

            val successResponse1 = Success(searchModalList1)
            val successResponse2 = Success(searchModalList2)
            val successResponse3 = Success(searchModalList3)

            coEvery {
                searchUseCase("vikram")
            } returns flow {
                emit(successResponse1)
            }

            coEvery {
                searchUseCase("apple")
            } returns flow {
                emit(successResponse2)
            }

            coEvery {
                searchUseCase("orange")
            } returns flow {
                emit(successResponse3)
            }

            searchViewModel.setSearchQuery("vikram")
            searchViewModel.setSearchQuery("apple")
            searchViewModel.setSearchQuery("orange")

            searchViewModel.searchItemList.observeOnce {
                Truth.assertThat(it).isEqualTo(searchModalList3)
            }
        }

    @Test
    fun `debounce check for 250ms`() {
        coroutinesTestRule.testDispatcher.runBlockingTest {

            /* val result = flow {
                 emit(1)
                 delay(300)
                 emit(2)
                 delay(100)
                 emit(3)
                 delay(200)
                 emit(4)
                 delay(240)
                 emit(5)
                 delay(300)
                 emit(6)
             }.debounce(250).toList()

             Truth.assertThat(result).isEqualTo(listOf(1, 5, 6))*/

            val flow = flow {
                emit("A")
                delay(1500)
                emit("B")
                delay(500)
                emit("C")
                delay(250)
                emit("D")
                delay(2000)
                emit("E")
            }
            val result = flow.debounce(1000).toList()
            assertEquals(listOf("A", "D", "E"), result)
        }
    }

    @Test
    fun `debounce check`() =
        coroutinesTestRule.testDispatcher.runBlockingTest {
            val s = CounterModel()
            launch {
                val sum = s.counter.take(11).toList().sum()
                assertEquals(55, sum)
            }
            repeat(10) {
                yield()
                s.inc()
            }
        }


    class CounterModel {
        // private data flow
        private val _counter = MutableStateFlow(0)

        // publicly exposed as a flow
        val counter: StateFlow<Int> get() = _counter

        fun inc() {
            _counter.value++
        }
    }

    @Test
    fun `steps for sucessful query fetch`() =
        coroutinesTestRule.testDispatcher.runBlockingTest {

            // Given
            val searchModalList = listOf(
                SearchModal("1", "apple", "img1"),
                SearchModal("2", "pine apple", "img2")
            )

            val successResponse = Success(searchModalList)

            coEvery {
                searchUseCase(QUERY)
            } returns flow {
                emit(successResponse)
            }

            //When
            searchViewModel.setSearchQuery(QUERY)

            //Then
            verifySequence {
                savedStateHandle.get<String>(any())
                savedStateHandle.set(any(), any<String>())
                savedStateHandle.set(any(), any<String>())
            }

            //When
            searchViewModel.searchServer(QUERY)

            //Then
            verify(ordering = Ordering.ORDERED) {
                observerLoader.onChanged(any())
                observerSuccessList.onChanged(any())
            }
        }

    @Test
    fun `get List for query apple`() =
        coroutinesTestRule.testDispatcher.runBlockingTest {

            // Given
            val searchModalList = listOf(
                SearchModal("1", "apple", "img1"),
                SearchModal("2", "pine apple", "img2")
            )

            val successResponse = Success(searchModalList)

            coEvery {
                searchUseCase(QUERY)
            } returns flow {
                emit(successResponse)
            }

            //When
            searchViewModel.setSearchQuery(QUERY)

            searchViewModel.searchItemList.observeOnce {
                Truth.assertThat(it).isEqualTo(searchModalList)
            }
        }

    @Test
    fun `no data found for query with empty data`() =
        coroutinesTestRule.testDispatcher.runBlockingTest {

            // Given
            val successEmptyResponse = Success(emptyList<SearchModal>())

            coEvery {
                searchUseCase(QUERY)
            } returns flow {
                emit(successEmptyResponse)
            }

            //When
            searchViewModel.setSearchQuery(QUERY)
            searchUseCase(QUERY)

            //Then
            coVerify(exactly = 1) {
                searchUseCase(any())
            }

            searchViewModel.searchItemList.observeOnce {
                Truth.assertThat(it).isEqualTo(successEmptyResponse.data)
            }
        }
}

class AppCoroutineDispatchersImpl constructor(
    override val main: CoroutineDispatcher,
    override val computation: CoroutineDispatcher,
    override val io: CoroutineDispatcher,
    override val default: CoroutineDispatcher,
    override val singleThread: CoroutineDispatcher
) : AppCoroutineDispatchers