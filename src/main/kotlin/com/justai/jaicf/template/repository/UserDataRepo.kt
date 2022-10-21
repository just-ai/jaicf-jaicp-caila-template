package com.justai.jaicf.template.repository

import com.justai.jaicf.template.model.UserData
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UserDataRepo: JpaRepository<UserData, Long> {
    fun getFirstByName(name: String) : UserData?
}