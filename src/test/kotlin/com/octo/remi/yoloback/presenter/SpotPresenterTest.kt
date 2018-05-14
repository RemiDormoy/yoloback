package com.octo.remi.yoloback.presenter

import com.nhaarman.mockitokotlin2.given
import com.octo.remi.yoloback.entities.ChargeSpotViewModel
import com.octo.remi.yoloback.interactor.SpotInteractor
import com.octo.remi.yoloback.utils.dummySpot
import org.assertj.core.api.Assertions
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import java.math.BigDecimal

@RunWith(MockitoJUnitRunner::class)
class SpotPresenterTest {

    @Mock private lateinit var interactor: SpotInteractor
    @InjectMocks private lateinit var presenter: SpotPresenter

    @Test
    fun getSpots() {
        // Given
        given(interactor.getSpots()).willReturn(listOf(dummySpot()))

        // When
        val result = presenter.getSpots()

        // Then
        Assertions.assertThat(result).isEqualTo(listOf(
                ChargeSpotViewModel(
                        "label",
                        "0",
                        "0",
                        "address"
                )
        ))
    }

    @Test
    fun getSpotsWithFilter() {
        // Given
        given(interactor.getSpots()).willReturn(listOf(dummySpot(),
                dummySpot(latitude = BigDecimal("11"), longitude = BigDecimal("13"))))

        // When
        val result = presenter.getSpotsWithFilter("12", "12")

        // Then
        Assertions.assertThat(result).isEqualTo(listOf(
                ChargeSpotViewModel(
                        "label",
                        "11",
                        "13",
                        "address"
                ),
                ChargeSpotViewModel(
                        "label",
                        "0",
                        "0",
                        "address"
                )
        ))
    }
}