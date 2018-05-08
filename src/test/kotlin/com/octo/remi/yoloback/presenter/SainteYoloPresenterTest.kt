package com.octo.remi.yoloback.presenter

import com.octo.remi.yoloback.entities.FootballPlayer
import com.octo.remi.yoloback.entities.FootballPlayerViewModel
import com.octo.remi.yoloback.interactor.SainteYoloInteractor
import org.assertj.core.api.Assertions.*
import org.junit.Test

import org.junit.Before
import org.mockito.BDDMockito.*
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import java.time.LocalDate

class SainteYoloPresenterTest {

    @Mock private lateinit var interactor: SainteYoloInteractor
    @InjectMocks private lateinit var presenter: SainteYoloPresenter

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun onSainteListRequested_empty() {
        // Given
        given(interactor.getSaintePlayers()).willReturn(listOf())

        // When
        val result = presenter.onSainteListRequested()

        // Then
        assertThat(result).isEqualTo(listOf<FootballPlayerViewModel>())
    }

    @Test
    fun onSainteListRequested() {
        // Given
        given(interactor.getSaintePlayers()).willReturn(listOf(
                FootballPlayer(
                        firstName = "firstName",
                        lastName ="lastName",
                        nation = "nation",
                        club = "club",
                        birthDate = LocalDate.now().minusYears(3)
                )
        ))

        // When
        val result = presenter.onSainteListRequested()

        // Then
        assertThat(result).isEqualTo(listOf(
                FootballPlayerViewModel(
                        firstName = "firstName",
                        lastName = "lastName",
                        nation = "nation",
                        club = "club",
                        age = 3
                )
        ))
    }
}