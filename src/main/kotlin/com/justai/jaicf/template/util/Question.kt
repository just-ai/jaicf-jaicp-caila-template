package com.justai.jaicf.template.util

val questions: List<Question> = listOf(
    Question("What is c?", listOf("a", "b", "c", "d"), 3),
    Question("What is c?", listOf("a", "b", "c", "d"), 3),
    Question("What is c?", listOf("a", "b", "c", "d"), 3),
    Question("What is c?", listOf("a", "b", "c", "d"), 3)
)


data class Question(val questionText: String, val answers: List<String>, val correctAnswer: Int) {
    fun formatted(): String {
        var ans = "$questionText\n"
        var i = 1
        answers.forEach { str -> ans += "${i++}: $str\n" }
        return ans
    }
}