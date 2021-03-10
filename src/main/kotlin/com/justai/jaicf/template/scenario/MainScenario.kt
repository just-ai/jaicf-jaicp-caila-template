package com.justai.jaicf.template.scenario

import com.justai.jaicf.activator.caila.caila
import com.justai.jaicf.builder.Scenario

val mainScenario = Scenario {
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
                    "Help me!",
                    "How are you?",
                    "What is your name?"
                )
            }
        }
    }

    state("bye") {
        activators {
            intent("Bye")
        }

        action {
            reactions.sayRandom(
                "See you soon!",
                "Bye-bye!"
            )
            reactions.image("https://media.giphy.com/media/EE185t7OeMbTy/source.gif")
        }
    }

    state("smalltalk", noContext = true) {
        activators {
            anyIntent()
        }

        action(caila) {
            activator.topIntent.answer?.let { reactions.say(it) } ?: reactions.go("/fallback")
        }
    }

    fallback {
        reactions.sayRandom(
            "Sorry, I didn't get that...",
            "Sorry, could you repeat please?"
        )
    }
}