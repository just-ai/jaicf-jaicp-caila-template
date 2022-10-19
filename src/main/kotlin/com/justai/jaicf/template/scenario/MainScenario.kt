package com.justai.jaicf.template.scenario

import com.justai.jaicf.builder.Scenario
import com.justai.jaicf.template.util.questions

val mainScenario = Scenario {

    state("test") {
        val q = questions.first()
        activators {
            regex("/test")
        }
        action {
            reactions.run {
                say(q.formatted())
                buttons("1", "2", "3", "4")
            }

        }


        state("answer") {
            activators {
                regex("[1-${q.answers.size}]")
            }
            action {
                val ans = request.input.toInt()
                reactions.run {
                    say("${request.input} ${if (ans == q.correctAnswer) " correct " else " incorrect "}")
                }

            }
        }
    }

    state("start") {
        activators {
            regex("/start")
            intent("Hello")
        }
        action {
            reactions.run {
                image("https://media.giphy.com/media/ICOgUNjpvO0PC/source.gif")
                sayRandom(
                    "Hello! How can I help?",
                    "Hi there! How can I help you?"
                )
                buttons(
                    "/test"
                )
            }
        }
    }

    fallback {
        reactions.sayRandom(
            "Sorry, I didn't get that...",
            "Sorry, could you repeat please?"
        )
    }
}