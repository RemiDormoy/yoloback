package com.octo.remi.yoloback.persistence.repository

import com.octo.remi.yoloback.persistence.entities.DBFootballPlayer
import com.octo.remi.yoloback.persistence.entities.DBSpot
import org.springframework.data.jpa.repository.JpaRepository

interface DBPlayerRepository : JpaRepository<DBFootballPlayer, Long> {}
interface DBSpotRepository : JpaRepository<DBSpot, Long> {}