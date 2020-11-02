package com.buffup.sdk

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.buffup.sdk.model.*
import com.buffup.sdk.repository.BuffRepository
import com.buffup.sdk.viewmodel.BuffViewModel
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.TestCoroutineScope
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.mockito.Mockito.mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
internal class BuffViewModelTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    private inline fun <reified T : Any> mock() = mock(T::class.java)

    @MockK(relaxUnitFun = true)
    lateinit var buffRepository: BuffRepository

    private val buffObserver: Observer<Result> = mock()

    lateinit var viewModel: BuffViewModel

    @Before
    fun setup() {
        viewModel = BuffViewModel()
        viewModel.buff.observeForever(buffObserver)
    }

    @ExperimentalCoroutinesApi
    @Test
    fun loadBuff_ResultSuccess_Test() {
        TestCoroutineScope().launch {
            val result = Result.Success(Buff(1, 30, 1,
                Author("Mircea", "Soit", ""),
                Question(1, "Question?", 1), listOf<Answer>(), ""))

            Mockito.`when`(buffRepository.loadBuff(1)).thenReturn(result)

            viewModel.loadBuff(1)

            Mockito.verify(buffObserver).onChanged(result)

            Mockito.verifyNoMoreInteractions(buffObserver)
        }
    }

    @ExperimentalCoroutinesApi
    @Test
    fun loadBuff_ResultError_Test() {
        TestCoroutineScope().launch {
            val result = Result.Error("Error")

            Mockito.`when`(buffRepository.loadBuff(1)).thenReturn(result)

            viewModel.loadBuff(1)

            Mockito.verify(buffObserver).onChanged(result)

            Mockito.verifyNoMoreInteractions(buffObserver)
        }
    }
}