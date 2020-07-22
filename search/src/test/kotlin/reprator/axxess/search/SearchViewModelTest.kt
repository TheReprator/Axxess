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
import javax.inject.Inject

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

    private val QUERY = "apple"

    private val EMPTY_QUERY: String = ""

    @Before
    fun setup() {
        MockKAnnotations.init(this)

        coroutineDispatchers = AppCoroutineDispatchersImpl(Dispatchers.Unconfined, Dispatchers.Unconfined,
            Dispatchers.Unconfined, Dispatchers.Unconfined, Dispatchers.Unconfined);

        every { savedStateHandle.get<String>(any()) } returns EMPTY_QUERY
        every { savedStateHandle.set(any(), any<String>()) } returns Unit

        searchViewModel = SearchViewModel(searchUseCase, coroutineDispatchers, savedStateHandle)
    }

    @After
    fun tearDown() {
        clearAllMocks()
    }

    @Test
    fun `debounce check for 250ms`() {
        coroutinesTestRule.testDispatcher.runBlockingTest  {

            val result = flow {
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

            Truth.assertThat(result).isEqualTo(listOf(1, 5, 6))
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
    private val observer = mockk<Observer<List<SearchModal>>>(relaxed = true)

    @Test
    fun `debounce `() = coroutinesTestRule.testDispatcher.runBlockingTest  {

        // Given
        val successEmptyResponse = Success(emptyList<SearchModal>())

        every { savedStateHandle.set(any(), any<String>()) } returns Unit
        coEvery {
            searchUseCase(QUERY)
        } returns flow {
            emit(successEmptyResponse)
        }

        searchViewModel.searchItemList.observeForever(observer)


        //When
        searchViewModel.setSearchQuery(QUERY)


        //Then
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