package com.justai.jaicf.template.service

import com.justai.jaicf.template.model.UserData
import com.justai.jaicf.template.repository.UserDataRepo
import org.springframework.stereotype.Service

@Service
class UserDataService(
    val userDataRepo: UserDataRepo
) {
    fun getAll(): List<UserData> = userDataRepo.findAll()
}