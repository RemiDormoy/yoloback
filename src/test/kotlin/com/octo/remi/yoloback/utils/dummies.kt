package com.octo.remi.yoloback.utils

import com.octo.remi.yoloback.entities.ChargeSpot
import java.math.BigDecimal

fun dummySpot(latitude: BigDecimal = BigDecimal.ZERO,
              longitude: BigDecimal = BigDecimal.ZERO,
              label: String = "label",
              address: String = "address") = ChargeSpot(
        latitude,
        longitude,
        label,
        address
)