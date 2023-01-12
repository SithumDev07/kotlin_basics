fun sum(a: Int, b: Int) = a + b

// Explicit return type
fun subtract(a: Int, b: Int): Int = a - b;

// Returns no meaningful value
fun nothingMakeSense(): Unit {
    println("Kotlin is perfect")
}

// Return type can be omitted
fun stillNothingMakeSense() {
    print("Kotlin is perfect")
}

/*
* Member Functions
* */
class Foo {
    fun bar() = println("I'm a member function")
}

// Access member functions using dot notation
fun testFoo() {
    Foo().bar()
}

/*
* Default arguments
* */
fun read(
    reader: String = "MORE"
) {
}

// Class based example
open class A {
    open fun foo(i: Int = 10) {}
}

// Inheritance
class B : A() {
    override fun foo(i: Int) {
        super.foo(i)
    }
}

/*
* Named arguments
* */
fun makeFun(bar: Int, foo: Int) {
}

fun testMakeFun() {
    // Order doesn't matter
    makeFun(foo = 23, bar = 34)
}

/*
* Lambda functions with default arguments
* */
fun makeFunWithLambda(
    bar: Int = 34,
    foo: Int = 22,
    read: () -> Unit
) {
    read()
}

fun makeFunWithNotLastLambda(read: () -> Unit, name: String) {
    read()
}

fun reformat(
    name: String,
    email: String,
    age: Int,
    gap: Double
) {
}

fun TestMakeFunWithLamdba() {

    /*
    * If the last argument is a lambda function after a default parameter,
    * can pass as a named argument or outside the parentheses
    * */
    makeFunWithLambda(22) { // Using default value foo 22
        println("Works first try")
    }

    makeFunWithLambda(foo = 22, read = { println("Works Perfect") }) // Using default value for bar 34

    makeFunWithLambda { println("Works") }

    makeFunWithNotLastLambda(name = "Sithum", read = {}) // Cannot pass outside parentheses

    reformat("Sithum", "sithum+kotlin@gmail.com", gap = 34.8, age = 34)
}

/*
* varargs - Variable number of arguments
* can only be one vararg parameter
* */
fun varargsTester(vararg names: String) {
    for (name in names) {
        println("$name ")
    }
}

// Something lil bit advanced
fun <T> asList(vararg ts: T): List<T> {
    val result = ArrayList<T>()
    for (t in ts) {
        result.add(t)
    }
    return result
}

fun <T> createBook(title: String, price: Double, vararg categories: T): Any {

    return object {
        val title = title
        val price = price
        val categories = categories
    }
}

fun testVarargsTester() {
    varargsTester(*arrayOf("Oswald", "Barbara", "Riddler"))
    varargsTester("Oswald", "Barbara", "Riddler")
    asList(12, 23, 23)

    // use case of varargs
    val book = createBook("Harry Potter", price = 23.4, "More", "Science", "Horror", "Tupido")
}

/*
* Infix notation
* either function is a extension function or a member function
* */
infix fun String.cato(name: String): String {
    return "Hey cato $name"
}

// How to call
fun testInfix() {
    // Way One
    println("Oswald" cato "Gotham")

    // Way Two
    println("Oswald".cato("Gotham"))
}

/*
* Infix in classes
* */
class ParentToChild {
    infix fun laugh(count: Int) {
        println("Laughing $count times")
    }

    fun start() {
        // Way One
        this laugh 10

        // Way Two
        laugh(45)
    }
}

/*
* Local functions
* */
fun outerFunction() {
    fun innerFunction() = println("Hello from inner")
    println("Hello from outer")
}

fun testOuterAndInner() {
    outerFunction()
}

fun main() {
    testOuterAndInner()
}
