import kotlin.math.*

data class PolarComplex(private var radius: Double = 0.0,private var theta: Double = 0.0){
    init{
        theta %= 360
    }

    var Theta = theta
        set(value) {field = value%360}

    var R = radius

    constructor(c:Complex) : this(c.length(),c.getTheta())

    override fun toString():String{
        return "$radius * (cos($theta) + jsin($theta))"
    }

    override fun equals(other: Any?): Boolean {
        if (other == null ||
            other !is PolarComplex ||
            radius != other.R || theta != other.Theta) return false

        return true
    }

    operator fun unaryMinus() :PolarComplex{
        return PolarComplex(radius, -theta)
    }

    operator fun times(rhs:Double): PolarComplex{
        return PolarComplex(radius * rhs, theta)
    }
    operator fun times(rhs:PolarComplex): PolarComplex{
        return PolarComplex(radius * rhs.radius , rhs.theta + theta)
    }

    operator fun div(rhs:Double): PolarComplex{
        return PolarComplex(radius/rhs, theta)
    }

    operator fun div(rhs: PolarComplex):PolarComplex{
        return PolarComplex(radius / rhs.radius, theta - rhs.theta)
    }


    fun reciproc(): PolarComplex{
        return PolarComplex(1/radius, -theta)
    }
    fun conjugate():PolarComplex{
        return PolarComplex(radius, -theta)
    }
    fun pow(n: Double):PolarComplex{
        return PolarComplex(
            radius = Math.pow(radius, n),
            theta = (theta * n)
        )
    }
    fun algebraticForm() = Complex(this)

}

operator fun PolarComplex.times(rhs:Complex):PolarComplex{
    return this * rhs.polarForm()
}

operator fun PolarComplex.div(rhs: Complex):PolarComplex{
    return this / rhs.polarForm()
}

operator fun Double.times(rhs:PolarComplex): PolarComplex{
    return rhs * this;
}

operator fun Double.plus(rhs:PolarComplex): PolarComplex{
    rhs.R = rhs.R + this
    return rhs;
}
operator fun Int.times(rhs: PolarComplex): PolarComplex{
    return PolarComplex(this * rhs.R, rhs.Theta)
}
operator fun Int.plus(rhs: PolarComplex):PolarComplex{
    rhs.R = rhs.R + this
    return rhs;
}

data class Complex(
    private var real:Double = 0.0,
    private var imaginary:Double = 0.0,
){
    constructor(polar:PolarComplex):this(
        real = polar.R * cos(polar.Theta),
        imaginary = polar.R * sin(polar.Theta)
    )

    var Re = real
    get(){return field;}
    set(value){field = value}

    var Im = imaginary
        get(){return field;}
        set(value){field = value}

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
    operator fun plus(rhs: Double) :Complex{
        return Complex(real + rhs, imaginary)
    }
    operator fun minus(rhs: Complex) :Complex {
        return Complex(real - rhs.real, imaginary - rhs.imaginary)
    }

    operator fun times(rhs: Complex): Complex {
        return Complex(
            real = (real * rhs.real - imaginary * rhs.imaginary),
            imaginary = (real * rhs.imaginary + imaginary * rhs.real)
        )
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

    fun polarForm() = PolarComplex(this)

    override fun toString():String{
        val sign = if(imaginary >= 0){
            "+"
        }
        else{
            "-"
        }

        return "$real $sign j$imaginary"
    }
}

operator fun Double.times(rhs: Complex) :Complex{
    return Complex(this * rhs.Re,this * rhs.Im)
}

operator fun Int.times(rhs: Complex) :Complex{
    return Complex(this * rhs.Re,this * rhs.Im)
}

operator fun Double.div(rhs: Complex) :Complex{
    return (this * rhs.reciproc())
}
operator fun Int.div(rhs: Complex) :Complex{
    return (this * rhs.reciproc())
}

operator fun Double.plus(rhs: Complex) :Complex{
    return Complex(this + rhs.Re, rhs.Im)
}

operator fun Int.plus(rhs: Complex) :Complex{
    return Complex(this + rhs.Re, rhs.Im)
}

operator fun Complex.times(rhs: PolarComplex): Complex{
    return this * rhs.algebraticForm()
}

operator fun Complex.div(rhs: PolarComplex): Complex{
    return this / rhs.algebraticForm()
}
