import kotlin.math.*

data class PolarComplex(val radius: Double = 0.0,val theta: Double = 0.0){
    constructor(c:Complex) : this(c.length(),c.getTheta())
    override fun toString():String{
        return "$radius * (cos($theta) + jsin($theta))"
    }

    val R = radius
        get(){return field;}

    val fi = theta
        get(){return field;}
}

data class Complex(
    val real:Double = 0.0,
    val imaginary:Double = 0.0,
){
    constructor(polar:PolarComplex):this(
        real = polar.R * cos(polar.fi),
        imaginary = polar.R * sin(polar.fi)
    )

    fun conjugate() = Complex(real, -imaginary)
    fun length() = sqrt(real * real + imaginary * imaginary)
    fun getTheta() = atan(imaginary / real) / PI * 180

    fun reciproc(): Complex {
        val denumerator = real * real + imaginary * imaginary
        return Complex(
            real = real / (denumerator),
            imaginary = -(imaginary / denumerator)
        )
    }

    operator fun unaryMinus() = Complex(-real, -imaginary)

    override fun equals(other: Any?) :Boolean {
        if (other == null ||
            other !is Complex ||
            real != other.real || imaginary != other.imaginary) return false

        return true
    }

    operator fun plus(rhs: Complex) :Complex{
        return Complex(real + rhs.real, imaginary + rhs.imaginary)
    }
    operator fun minus(rhs: Complex) :Complex {
        return Complex(real - rhs.real, imaginary - rhs.imaginary)
    }

    operator fun times(rhs: Complex) :Complex{
        val result:Complex = Complex(
            real = (real * rhs.real - imaginary * rhs.imaginary),
            imaginary =(real * rhs.imaginary + imaginary * rhs.real)
        )
        return result;
    }
    operator fun times(rhs: Double) :Complex{
        return Complex(rhs * real, rhs * imaginary)
    }

    operator fun div(rhs:Complex) :Complex{
        val denum: Double = real*real + imaginary*imaginary
        return Complex(
            real = (real * rhs.real + imaginary * rhs.imaginary)/denum,
            imaginary = (real * rhs.imaginary + imaginary * rhs.real)/denum
        )
    }
    operator fun div(rhs:Double) :Complex{
        return  Complex(real/rhs,imaginary/rhs)
    }

    fun pow(n:Double) :Complex{
        //TODO implement
        return Complex();
    }

    override fun toString():String{
        return "$real + j$imaginary"
    }

}

operator fun Double.times(rhs: Complex) :Complex{
    return Complex(this * rhs.real,this * rhs.imaginary)
}

operator fun Double.div(rhs: Complex) :Complex{
    return (this * rhs.reciproc())
}

operator fun Double.plus(rhs: Complex) :Complex{
    return Complex(this + rhs.real, rhs.imaginary)
}
