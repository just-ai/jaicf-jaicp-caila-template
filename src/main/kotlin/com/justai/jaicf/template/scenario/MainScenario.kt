package com.justai.jaicf.template.scenario

import com.justai.jaicf.builder.createModel
import com.justai.jaicf.channel.telegram.telegram
import com.justai.jaicf.model.scenario.Scenario
import com.justai.jaicf.template.util.questions
import org.springframework.stereotype.Component

@Component
class MainScenario : Scenario {

    override val model = createModel {
        state("test") {
            val q = questions.first()
            activators {
                regex("/test")
            }
            action {
                reactions.run {
                    telegram?.say(q.formatted(), (1..q.answers.size).toList().map { it.toString() })
                }
            }


            state("answer") {
                activators {
                    regex("[1-${q.answers.size}]")
                }
                action {
                    val ans = request.input.toInt()
                    val isCorrect = ans == q.correctAnswer
                    reactions.run {
                        telegram?.say("answer ${request.input} is ${if (isCorrect) "correct)" else "incorrect("}")

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
}