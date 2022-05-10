import kotlin.math.*

data class ExpComplex(private val magnitude: Double = 0.0,private val exponent: Double = 0.0){

    val R:Double
        get(){return magnitude}
    val Theta:Double
        get(){return exponent}

    constructor(c:Complex) : this(c.length(),c.getFi())

    override fun toString():String{
        return "$magnitude * ej$exponent"
    }
}
data class PolarComplex(private val radius: Double = 0.0,private val theta: Double = 0.0){
    constructor(c:Complex) : this(c.length(),c.getFi())

    val R:Double
        get(){return radius}
    val Theta:Double
        get(){return theta}

    override fun toString():String{
        return "$radius * (cos($theta) + jsin($theta))"
    }
}

data class Complex(
    private var real:Double = 0.0,
    private var imaginary:Double = 0.0,
){
    constructor(polar:PolarComplex):this(
        real = polar.R * cos(polar.Theta),
        imaginary = polar.R * sin(polar.Theta)
    )
    constructor(exp:ExpComplex):this(
        real = exp.R * cos(exp.Theta),
        imaginary = exp.R * sin(exp.Theta)
    )

    var Re:Double
        get(){return real;}
        set(value){real = value;}

    var Im:Double
        get(){return imaginary;}
        set(value){imaginary = value;}

    fun conjugate() = Complex(real, -imaginary)
    fun length() = sqrt(real * real + imaginary * imaginary)
    fun inv() = Complex(-real, -imaginary)
    fun getFi() = atan(imaginary / real) / PI * 180

    fun reciproc(): Complex {
        val denumerator = real * real + imaginary * imaginary
        return Complex(
            real = real / (denumerator),
            imaginary = -(imaginary / denumerator)
        )
    }

    operator fun unaryMinus() = Complex(-real, -imaginary)

    override fun equals(other: Any?): Boolean {
        if (other == null ||
            other !is Complex ||
            real != other.Re || imaginary != other.Im) return false

        return true
    }

    operator fun plus(rhs: Complex): Complex{
        return Complex(real + rhs.real, imaginary + rhs.imaginary)
    }
    operator fun minus(rhs: Complex): Complex {
        return Complex(real - rhs.real, imaginary - rhs.imaginary)
    }

    operator fun times(rhs: Complex): Complex{
        val result:Complex = Complex(
            real = (real * rhs.real - imaginary * rhs.imaginary),
            imaginary =(real * rhs.imaginary + imaginary * rhs.real)
        )
        return result;
    }
    operator fun times(rhs: Double): Complex{
        return Complex(rhs * real, rhs * imaginary)
    }

    operator fun div(rhs:Complex):Complex{
        val denum: Double = real*real + imaginary*imaginary
        return Complex(
            real = (real * rhs.Re + imaginary * rhs.Im)/denum,
            imaginary = (real * rhs.Im + imaginary * rhs.Re)/denum
        )
    }
    operator fun div(rhs:Double):Complex{
        return  Complex(real/rhs,imaginary/rhs)
    }

    fun pow(n:Double){
        //TODO implement
    }

    override fun toString():String{
        return "$real + j$imaginary"
    }

}

operator fun Double.times(rhs: Complex):Complex{
    return Complex(this * rhs.Re,this * rhs.Im)
}

operator fun Double.div(rhs: Complex): Complex{
    return (this * rhs.reciproc())
}