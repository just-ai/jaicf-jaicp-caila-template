package com.justai.jaicf.template.util

val questions: List<Question> = listOf(
    Question(
        "What is the difference between val and var in Kotlin?",
        listOf(
            "Variables declared with var are final, those with val are not.",
            "Variables declared with val are final, those with var are not.",
            "Variables declared with val can only access const members",
            "var is scoped to the nearest function block and val is scoped to the nearest enclosing block"
        ),
        2
    ),
    Question(
        "What does a data class not offer",
        listOf(
            "An auto-generated toString() method",
            "Automatic conversion from/to JSON",
            "Auto-generated hashCode() and equals() methods",
            "A generated copy(...) method, to create copies of instances"
        ),
        2
    ),
    Question(
        "What does this code do?\nfoo()()",
        listOf(
            "Calls foo asynchronously",
            "Creates a two-dimensional array",
            "Calls a function which is returned by the call of foo",
            "Fails compilation"
        ),
        3
    ),
    Question(
        "What are Kotlin coroutines?",
        listOf(
            "That's how the automatically generated methods hashCode() and equals() in data classes are called",
            "They provide asynchronous code without thread blocking",
            "These are functions which accept other functions as arguments or return them",
            "It's Kotlin's term for class methods"
        ),
        2
    ),
    Question(
        "What is to in the example below:\nval test = 33 to 42",
        listOf(
            "An infix extension function creating a Pair(33, 42)",
            "A Kotlin keyword to create a Pair(33, 42)",
            "A Kotlin keyword to create a Range from 33 to 42",
            "A syntax error"
        ),
        1
    ),
    Question(
        "What is the correct way to declare a variable of integer type in Kotlin?",
        listOf(
            "let i = 42",
            "var i : Int = 42",
            "var i : int = 42",
            "int i = 42"
        ),
        2
    ),
    Question(
        "Which is true for the following simple class declaration?\nclass Person (val name: String)",
        listOf(
            "It has a private property \"name\"",
            "It is package-private",
            "It is public",
            "It can be extended by other classes"
        ),
        3
    ),
    Question(
        "What is the correct syntax to convert the String \"42\" to a Long in Kotlin?",
        listOf(
            "val l: Long = Long.parseLong(\"42\")",
            "val l: Long = <Long>\"42\"",
            "val l: Long = \"42\".toLong()",
            "val l: Long = (Long)\"42\""
        ),
        3
    ),
    Question(
        "Does Kotlin have primitive data types, such as int, long, float?",
        listOf(
            "Yes, Kotlin is similar to Java in this respect",
            "Yes, but Kotlin internally always converts them to their non-primitive counterparts",
            "No, Kotlin does not have nor use primitive data types",
            "No, not at language level. But the Kotlin compiler makes use of JVM primitives for best performance"
        ),
        4
    ),
    Question(
        "What does the following code print?\nval listA = mutableListOf(1, 2, 3)\n" +
                "val listB = listA.add(4)\n" +
                "print(listB)",
        listOf(
            "true",
            "Nothing, there is a compile error.",
            "[1, 2, 3, 4]",
            "Unit"
        ),
        1
    ),
    Question(
        "What is the difference between a and b?\nvar a: String? = \"KotlinQuiz\"\n" +
                "var b: String = \"KotlinQuiz\"",
        listOf(
            "b can never become null",
            "b is final and cannot be changed",
            "a is volatile as in Java",
            "a is final and cannot be changed"
        ),
        1
    ),
    Question(
        "Which is a valid function declaration in Kotlin?",
        listOf(
            "fun sum(a: Int, b: Int): Int",
            "int sum(int a, int b)",
            "int sum(a: Int, b: Int)",
            "function sum(a: Int, b: Int): Int"
        ),
        1
    ),
    Question(
        "What does the !! operator do?",
        listOf(
            "It returns the left-hand operand if the operand is not null; otherwise it returns the right hand operand",
            "It compares two values for identity rather than equality",
            "It converts any value to a non-null type and throws an exception if the value is in fact null",
            "It's the modulo operator in Kotlin, similar to Java's %"
        ),
        3
    ),
    Question(
        "What is the output of the following code?\nval list : List<Int> = listOf(1, 2, 3)\n" +
                "list.add(4)\n" +
                "print(list)",
        listOf(
            "It does not compile, as listOf is not known",
            "[1, 2, 3, 4]",
            "[5, 6, 7]",
            "It does not compile as List has no add method"
        ),
        4
    ),
    Question(
        "What is the key difference between Iterable<T> and Sequence<T> in Kotlin?",
        listOf(
            "Sequences are processed sequentially, Iterables in parallel (multithreaded)",
            "Iterable<T> works only on immutable collections, Sequence<T> is also applicable to mutable ones",
            "Sequences are processed lazily, Iterables eagerly",
            "There is no difference, as Sequence<T> is Kotlin's term for Iterable<T>"
        ),
        3
    ),
    Question(
        "What is the type of arr in the following code?\nval arr = arrayOf(1, 2, 3)",
        listOf(
            "int[]",
            "Array<Int>",
            "IntArray",
            "Int[]"
        ),
        2
    ),
    Question(
        "What is the equivalent of the following Java expression in Kotlin?\nint x = a ? b : c",
        listOf(
            "val x = a ?: b, c",
            "val x = a ? b : c",
            "val x = if (a) b else c",
            "val x = if (a) b : c"
        ),
        3
    ),
    Question(
        "Under which license is Kotlin available?",
        listOf(
            "Kotlin is closed source, commercial software",
            "Apache 2",
            "GPL",
            "MIT"
        ),
        2
    ),
    Question(
        "What does the following code print?\nval a: String? = null\n" +
                "val b: String = \"Hello World\"\n" +
                "println(a==b)",
        listOf(
            "Nothing, but throws NullPointerException",
            "false",
            "true",
            "Does not compile"
        ),
        2
    ),
    Question(
        "What about Java interoperability?",
        listOf(
            "Kotlin can easily call Java code while Java cannot access code written in Kotlin",
            "While Kotlin runs on the JVM, it cannot interoperate with Java",
            "Kotlin can easily call Java code and vice versa",
            "Kotlin provides a compatibility layer for Java interoperability which comes with some cost at runtime"
        ),
        3
    )
)


data class Question(val questionText: String, val answers: List<String>, val correctAnswer: Int) {
    fun formatted(): String {
        var ans = "$questionText\n"
        var i = 1
        answers.forEach { str -> ans += "${i++}: $str\n" }
        return ans
    }
}