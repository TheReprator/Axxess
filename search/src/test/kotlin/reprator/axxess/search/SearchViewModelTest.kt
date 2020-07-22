package reprator.axxess.search

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.SavedStateHandle
import com.google.common.truth.Truth
import io.mockk.*
import io.mockk.impl.annotations.MockK
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlinx.coroutines.yield
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import reprator.axxess.base.util.AppCoroutineDispatchers
import reprator.axxess.search.domain.usecase.SearchUseCase

class SearchViewModelTest {

    @get:Rule
    private val testCoroutineRule = TestCoroutineRule()

    @get:Rule
    private val taskExecutorRule = InstantTaskExecutorRule()

    @MockK
    lateinit var searchUseCase: SearchUseCase

    @MockK
    lateinit var coroutineDispatchers: AppCoroutineDispatchers

    @MockK
    lateinit var savedStateHandle: SavedStateHandle

    lateinit var searchViewModel: SearchViewModel

    private val QUERY = "apple"

    private val EMPTY_QUERY: String ?= null

    @Before
    fun setup() {
        MockKAnnotations.init(this)

        every { savedStateHandle.get<String>(any())} returns EMPTY_QUERY

        searchViewModel = SearchViewModel(searchUseCase, coroutineDispatchers, savedStateHandle)
    }

    @After
    fun tearDown() {
        clearAllMocks()
    }

    @Test
    fun `debounce check for 250ms`() {
        testCoroutineRule.runBlockingTest {

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
        testCoroutineRule.runBlockingTest {
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
    fun `debounce `() = testCoroutineRule.runBlockingTest {

    }
}
