package com.justai.jaicf.template.service

import com.justai.jaicf.template.model.UserData
import com.justai.jaicf.template.repository.UserDataRepo
import org.springframework.stereotype.Service

@Service
class UserDataService(
    val userDataRepo: UserDataRepo
) {
    fun getNextQuestion(username: String) = getByName(username).lastAnswered + 1

    fun getByName(name: String): UserData = userDataRepo.getFirstByName(name) ?: createNewUser(name)

    fun answer(correct: Boolean, username: String) {
        val userData = getByName(username)

        if (correct)
            userData.score++
        userData.lastAnswered++
        userDataRepo.save(userData)
    }

    fun getScore(username: String) = getByName(username).score

    fun createNewUser(username: String): UserData {
        val newUser = UserData(name = username)
        return userDataRepo.save(newUser)
    }

    fun reset(username: String) {
        val userData = getByName(username)
        userData.score = 0
        userData.lastAnswered = 0
        userDataRepo.save(userData)
    }


}