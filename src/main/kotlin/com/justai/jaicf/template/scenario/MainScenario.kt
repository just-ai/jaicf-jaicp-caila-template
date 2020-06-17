package com.justai.jaicf.template.scenario

import com.justai.jaicf.model.scenario.Scenario

object MainScenario: Scenario() {

    init {
        state("Hello") {
            activators {
                intent("Привет")
            }

            action {
                reactions.say("Hi there!")
            }
        }
        state("Bye") {
            activators {
                intent("Bye")
            }

            action {
                reactions.say("See you soon!")
            }
        }

        state("fallback", noContext = true) {
            activators {
                catchAll()
            }

            action {
                reactions.say("I have nothing to say yet...")
            }
        }
    }
}