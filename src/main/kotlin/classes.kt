import org.jetbrains.annotations.TestOnly

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
        println("This headphone is made of $madeOf and it is ${if (isWired) "wired one" else "wireless one"}.")
        isWired = !isWired;
        println("Now it's changed to ${if (isWired) "wired one" else "wireless one"}")
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

    constructor(brand: String, phone: AndroidPhone) : this(brand) {
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

class Child : Parent()

/*
* Has primary constructors
* */
open class Animal(name: String)

class Tiger : Animal {
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

open class Mordor : Shape() {
    // can override open functions only
    // Make it final so, subclasses won't be able to override
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
class MordorExecutor : Mordor() {
    // Override further
    override fun brush() {
        super.brush()
    }
}

/*
* Property overriding
* */
open class Cat {
    open val taleless: Boolean = false
}

class Garfield : Cat() {
    override val taleless = true

    init {
        println("Garfield ${if (this.taleless) "hasn't" else "has"} a tale")
    }
}

class Mervin : Cat() {
    init {
        println("Mervin ${if (this.taleless) "hasn't" else "has"} a tale")
    }
}

/*
* Derived class initialization order
* */
open class Comb {
    init {
        println("Comb initialization")
    }
}

class Brush : Comb() {
    init {
        println("Brush initialization")
    }
}

/*
* super keyword
* */
open class Cable {
    open fun connect() {
        println("Trying to connect...")
    }

    val cableColor: String = "Black"
}

class USB : Cable() {
    override fun connect() {
        super.connect()
        println("Connected!")
    }

    private val usbCableColor: String = super.cableColor

    init {
        connect()
        println("Cable color is $usbCableColor")
    }
}

/*
* Super outer
* */
open class Bottle {
    open fun open() {
        println("Opened")
    }
}

class GlassBottle : Bottle() {
    override fun open() {
        println("Glass bottle opened")
    }

    inner class WhiskeyBottle {
        fun drink() {
            super@GlassBottle.open() // Calling to default bottle class
            GlassBottle().open() // Calling to glass bottle class
            println("I am drunk")
        }
    }
}

/*
* Declaring properties
* can declare using var (mutable) or val (read-only)
* */
class HomeAddress {
    var name: String = "456/B"
    var street: String = "London Street"
    val postalCode: Int = 9002
    var state: String? = null

    fun copyAddress() {
        // Accessing properties
        println(HomeAddress().name)
    }
}

/*
* Getters and setters
* */
class Rectangle(val width: Int, val height: Int) {

    // Has type String and default getter and setter
    var name: String = ""

    // Has type of String and only a default getter
    val cakeType: String? = null

    val area: Int
        // Custom getter
        get() = width * height

    // Omitting type when inferred from a getter
    val thickness get() = width * height * 2

    var nominee: String
        get() = this.toString()
        set(value) {
            println(value)
        }
}

fun rectangleTester() {
    Rectangle(height = 100, width = 100).nominee = "Nominee Name"
}

/*
* Late initialization - lateinit
* */
class AppTest {
    lateinit var name: String

    @TestOnly
    fun test() {
        println(name.uppercase())
    }
}


/*
* Interfaces
* */
interface SetupInterface {
    val prop: Int

    val packed: Boolean
        get() = false

    fun remember() {
        println("Remember me")
    }

    fun provide()
}

class Setup : SetupInterface {
    override val prop: Int
        get() = TODO("Not yet implemented")

    override fun provide() {
        TODO("Not yet implemented")
    }

    override fun remember() {
        super.remember()
    }

}

/*
* Interface inheritance
* */
interface BaseProduct {
    val name: String
}

interface SubsidiaryProduct : BaseProduct {
    val subName: String
    val barcode: Int

    override val name: String
        get() = "$subName-XB-$barcode"
}

data class Product(override val subName: String, override val barcode: Int) : SubsidiaryProduct

/*
* Functional interfaces - SAM (Single Abstract Method)
* - an interface with only one abstract method
* */
fun interface Runnable {
    // Scenario - accepting by requiring a number
    fun accept(number: Int): Boolean
}

fun runnableTester() {
    val isEven = Runnable { it % 2 == 0 }
    val isDivideByTen = Runnable { it % 10 == 0 }

    println(isEven.accept(34))
    println(isDivideByTen.accept(41))
}

/*
* Type aliases
* */
typealias IntPredicate = (number: Int) -> Boolean

fun typeAliasTester() {
    val isDivideByHundred: IntPredicate = { it % 100 == 0 }
    println("401 is ${if(isDivideByHundred(401)) "divided by 100." else "not divided by 100."}")
}

fun main() {
    typeAliasTester()
}


