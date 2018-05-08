package com.octo.remi.yoloback.entities

import java.math.BigDecimal

data class ChargeSpot(val latitude: BigDecimal,
                      val longitude: BigDecimal,
                      val label: String,
                      val address: String)