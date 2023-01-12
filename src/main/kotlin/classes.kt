
/*
* Empty class
* */
class Empty

/*
* Primary constructor
* if the constructor doesn't have any annotations of visibility modifiers, can omit term constructor
* */
class Person constructor(name: String)

class MaxMo(tyreSize: Int)

/*
* Initialization blocks
* */
class Initialization {
    val one = "I executed first".also { println(it) }

    init {
        println("Nooooo, I am the first")
    }

    val two = "I am no more".also { println(it) }

    // Multiple init blocks possible
    init {
        println("Seconddd")
    }
}

/*
* Property initializers
* */
class Book(title: String) {
    val titleKey = title.uppercase()
}

/*
* Declare and initialize with no time
* default values are also possible
* */
class Headphone(private val madeOf: String, private var isWired: Boolean = false) {
    init {
        println("This headphone is made of $madeOf and it is ${if(isWired) "wired one" else "wireless one"}.")
        isWired = !isWired;
        println("Now it's changed to ${if(isWired) "wired one" else "wireless one"}")
    }
}

/*
* Secondary constructors
* */

class Owner(val collectibles: MutableList<Laptop> = mutableListOf())

class Laptop {
    constructor(owner: Owner) {
        owner.collectibles.add(this)
    }
}

/*
* Primary constructor and secondary constructor using at the same time
* Delegating
* */
class AndroidPhone(brand: String) {
    val androidPhones: MutableList<AndroidPhone> = mutableListOf()
    constructor(brand: String, phone: AndroidPhone): this(brand) {
        phone.androidPhones.add(this)
    }
}

/*
* Private constructor
* */
class DoNotCreateMe private constructor(title: String)

/*
* Inheritance
* all classes are inherited by Any class in Kotlin
* to make a class inheritable make it open
* */
open class Parent

class Child: Parent()

/*
* Has primary constructors
* */
open class Animal(name: String)

class Tiger: Animal {
    constructor() : super("Patrick")
    constructor(madeOf: String) : super("Patrick & $madeOf")
}

/*
* Overriding
* */
open class Shape {
    open fun draw() = println("Drawing...")

    open fun brush() = println("Brushing...")

    fun iAmPrivate() = println("I am private")
}

open class Mordor: Shape() {
    // can override open functions only
    final override fun draw() {
        super.draw()
    }

    override fun brush() {
        super.brush()
    }
}

/*
* Prohibit re-overriding
* if a function is overridden, then subclasses can override further
* prohibit scenario by using keyword final
* */
class MordorExecutor: Mordor() {
    // Override further
    override fun brush() {
        super.brush()
    }
}

fun main() {
    Mordor().draw()
    Mordor().iAmPrivate()
}


