package com.octo.remi.yoloback.persistence.repository

import com.octo.remi.yoloback.persistence.entities.DBFootballPlayer
import org.springframework.data.jpa.repository.JpaRepository

interface DBPlayerRepository : JpaRepository<DBFootballPlayer, Long> {}