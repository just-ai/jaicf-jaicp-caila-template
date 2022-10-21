package com.justai.jaicf.template.scenario

import com.justai.jaicf.builder.createModel
import com.justai.jaicf.channel.telegram.telegram
import com.justai.jaicf.model.scenario.Scenario
import com.justai.jaicf.template.service.UserDataService
import com.justai.jaicf.template.util.Question
import com.justai.jaicf.template.util.questions
import org.springframework.stereotype.Component

@Component
class MainScenario(
    userDataService: UserDataService
) : Scenario {

    override val model = createModel {

        state("new") {
            activators {
                regex("/new")
            }
            action {
                val username = request.telegram?.message?.chat?.username!!
                userDataService.reset(username)
                reactions.go("../test")
            }
        }

        state("test") {
            activators {
                regex("/continue")
            }
            action {
                val username = request.telegram?.message?.chat?.username!!
                val questionNumber = userDataService.getNextQuestion(username)
                val question = questions[questionNumber - 1]
                reactions.run {
                    telegram?.say("Question №$questionNumber:")
                    telegram?.say(question.formatted(), (1..4).toList().map { it.toString() })
                }
            }


            state("answer") {
                activators {
                    regex("[1-4]")
                }
                action {
                    val username = request.telegram?.message?.chat?.username!!
                    val questionNumber = userDataService.getNextQuestion(username)
                    val question = questions[questionNumber - 1]

                    val ans = request.input.toInt()
                    val isCorrect = ans == question.correctAnswer

                    userDataService.answer(isCorrect, username)

                    reactions.run {
                        telegram?.say("answer ${request.input} is ${if (isCorrect) "correct)" else "incorrect("}")
                        if (questionNumber == questions.size)
                            go("../../complete")
                        else
                            go("..")
                    }
                }
            }
        }

        state("complete"){
            action {
                val username = request.telegram?.message?.chat?.username!!
                val score = userDataService.getScore(username)

                reactions.telegram?.say(
                    "Whew! It seems that you've completed test! Your score is $score/${questions.size}. /new to retry"
                )
            }
        }

        state("start") {
            activators {
                regex("/start")
                intent("Hello")
            }
            action {
                val username = request.telegram?.message?.chat?.username!!
                val userData = userDataService.getByName(username)
                reactions.run {
                    val btns = mutableListOf("/new")
                    when (userData.lastAnswered) {
                        0 -> say("It looks like you are new! /new - Begin test")
                        questions.size ->
                            say(
                                "You've already completed test. " +
                                        "Your result is ${userData.score}/${questions.size}. " +
                                        "/new - Begin new test"
                            )
                        else -> {
                            say(
                                "It seems that you've already started test. " +
                                        "/continue - Proceed test. " +
                                        "/new - Begin new test"
                            )
                            btns.add("/continue")
                        }
                    }
                    buttons(*btns.toTypedArray())
                }
            }
        }

        fallback {
            reactions.say("I don't understand. If you want to start using me just type /start ;)")
        }
    }
}