package com.octo.remi.yoloback.presenter

import com.octo.remi.yoloback.entities.FootballPlayerViewModel
import com.octo.remi.yoloback.interactor.SainteYoloInteractor
import org.assertj.core.api.Assertions
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.junit.runner.RunWith
import org.mockito.BDDMockito
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner

@RunWith(SpringJUnit4ClassRunner::class)
class SainteYoloPresenterTest {

    @Mock private lateinit var interactor: SainteYoloInteractor
    @InjectMocks private lateinit var presenter: SainteYoloPresenter

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun onSainteListRequested() {
        // Given
        BDDMockito.given(interactor.getSaintePlayers()).willReturn(listOf())

        // When
        val result = presenter.onSainteListRequested()

        // Then
        Assertions.assertThat(result).isEqualTo(listOf<FootballPlayerViewModel>())
    }
}