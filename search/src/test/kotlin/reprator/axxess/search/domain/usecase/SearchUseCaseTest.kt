package reprator.axxess.search.domain.usecase

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth
import io.mockk.*
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import reprator.axxess.base.util.useCases.ErrorResult
import reprator.axxess.base.util.useCases.Success
import reprator.axxess.base_android.SearchModal
import reprator.axxess.search.TestCoroutineRule
import reprator.axxess.search.domain.repository.SearchRepository
import java.lang.Exception

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

            val successResponse = Success(listOf(
                SearchModal("1","apple","appleUrl"),
                SearchModal("2","apple pne","appleUrl pine")
            ))

            coEvery {
                searchUseCase("apple")
            } returns flow {
                emit(successResponse)
            }

            coEvery {
                searchRepository.searchItems("apple")
            } returns flow {
                throw  Exception("vvvv")
            }

            val result = searchUseCase("apple")

            result.collect {
                    Truth.assertThat(it).isEqualTo(successResponse)
                    Truth.assertThat(it.get()).hasSize(2)
                }
        }
    }
}