package reprator.axxess.search.domain.usecase

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth
import io.mockk.MockKAnnotations
import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.flow.*
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import reprator.axxess.base.util.useCases.ErrorResult
import reprator.axxess.base.util.useCases.Success
import reprator.axxess.base_android.SearchModal
import reprator.axxess.search.TestCoroutineRule
import reprator.axxess.search.domain.repository.SearchRepository

class SearchUseCaseTest {

    @MockK
    lateinit var searchRepository: SearchRepository

    @MockK
    lateinit var searchUseCase: SearchUseCase

    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    @get:Rule
    val taskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setup() {
        MockKAnnotations.init(this)
    }

    @After
    fun tearDown() {
        clearAllMocks()
    }

    @Test
    fun `no value emitted`() {
        testCoroutineRule.runBlockingTest {

            val successResponse = Success(emptyList<SearchModal>())

            coEvery {
                searchUseCase("apple")
            } returns flow {
                successResponse
            }

            val result = searchUseCase("apple")

            val flowsWithContentCombined = result.count()
            Truth.assertThat(flowsWithContentCombined).isAtLeast(0)
        }
    }

    @Test
    fun `No data found for the query`() {
        testCoroutineRule.runBlockingTest {

            val emptyResponse = Success(emptyList<SearchModal>())

            coEvery {
                searchUseCase("vikram")
            } returns flow {
                emit(emptyResponse)
            }

            val result = searchUseCase("vikram")

            result.collect {
                Truth.assertThat(it is Success).isTrue()
                Truth.assertThat(it).isEqualTo(emptyResponse)
                Truth.assertThat(it.get()).isEmpty()
            }
        }
    }

    @Test
    fun `Error thrown due to any Reason`() {
        testCoroutineRule.runBlockingTest {

            val errorResponse = ErrorResult(message = "No Internet Connection")

            coEvery {
                searchUseCase("apple")
            } returns flow {
                throw Exception("No Internet Connection")
            }

            val result = searchUseCase("apple")

            result.catch {
                Truth.assertThat(it).isInstanceOf(Exception::class.java)
                Truth.assertThat(it.localizedMessage).contains("No Internet Connection")
            }
                .collect {
                    Truth.assertThat(it is ErrorResult).isTrue()
                    Truth.assertThat(it).isEqualTo(errorResponse)
                }
        }
    }

    @Test
    fun `get list of success data`() {
        testCoroutineRule.runBlockingTest {

            val successResponse = Success(
                listOf(
                    SearchModal("1", "apple", "appleUrl"),
                    SearchModal("2", "apple pne", "appleUrl pine")
                )
            )

            coEvery {
                searchUseCase("apple")
            } returns flow {
                emit(successResponse)
            }

            val result = searchUseCase("apple")

            result.collect {
                Truth.assertThat(it).isEqualTo(successResponse)
                Truth.assertThat(it.get()).hasSize(2)
            }
        }
    }

    @Test
    fun `get list of success data comparison`() {
        testCoroutineRule.runBlockingTest {

            val searchModalList = listOf(
                SearchModal("1", "apple", "appleUrl"),
                SearchModal("2", "apple pne", "appleUrl pine"))

            coEvery {
                searchUseCase("apple")
            } returns flow {
                emit(Success(searchModalList))
            }

            val result = searchUseCase("apple")

            val searchModalListDynamic = mutableListOf<SearchModal>()
            result.collect {
                searchModalListDynamic+= it.get()!!
            }

            Truth.assertThat(searchModalList).isEqualTo(searchModalListDynamic)
        }
    }

    @Test
    fun `get list of success data from respositories`() {
        testCoroutineRule.runBlockingTest {

            val searchModalList = listOf(
                SearchModal("1", "apple", "appleUrl"),
                SearchModal("2", "apple pne", "appleUrl pine"))

            coEvery {
                searchRepository.searchItems("apple")
            } returns flow {
                emit(Success(searchModalList))
            }

            val result = searchRepository.searchItems("apple")

            val searchModalListDynamic = mutableListOf<SearchModal>()
            result.collect {
                searchModalListDynamic+= it.get()!!
            }
        }
    }
}