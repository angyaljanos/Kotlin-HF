import org.junit.Assert.assertEquals
import org.junit.Test
import kotlin.math.sqrt

class ComplexTest {
    @Test
    fun `Complex inverz`() {
        val z0 = Complex(1.0,1.0)
        val z1 = -z0

        assertEquals(Complex(0.0, 0.0), z0 + z1)

        val z2 = PolarComplex(1.0,1.0)
        val z3 = -z2
        assertEquals(Complex(0.0, 0.0), z0 + z1)
    }
    @Test
    fun `Complex conjugate`() {
        val z0 = Complex(1.0,1.0)
        val z1 = z0.conjugate()

        assertEquals(Complex(2.0, 0.0) , (z0 + z1))
    }
    @Test
    fun `Complex plus operation`() {
        val z0 = Complex(1.0,1.0)
        val z1 = Complex(3.1415,2.72)

        assertEquals(Complex(2.0,2.0), z0 + z0)
        assertEquals(Complex(2.0, 1.0),z0 + 1.0)
        assertEquals(Complex(2.0, 1.0),1.0 + z0)
        assertEquals(Complex(4.1415,3.72),z1 + z0)
    }
    @Test
    fun `Complex times operation`() {
        val z0 = PolarComplex(1.0,90.0)
        val z1 = PolarComplex(2.0,45.0)

        assertEquals(PolarComplex(2.0, 135.0), z0 * z1)
        assertEquals(PolarComplex(2.0, 90.0), z0 * 2.0)


        val z2 = Complex(1.0, 1.0)
        val z3 = Complex(2.0,-1.0)

        assertEquals(Complex(3.0, 1.0), z2 * z3)
        assertEquals(Complex(2.0, 2.0), z2 * 2.0)
    }

    @Test
    fun `Complex div operation`() {
        val z0 = PolarComplex(1.0,90.0)
        val z1 = PolarComplex(2.0,45.0)

        assertEquals(PolarComplex(0.5, 45.0), z0 / z1)
        assertEquals(PolarComplex(0.5, 90.0), z0 / 2.0)

        val z2 = Complex(1.0, 1.0)
        val z3 = Complex(2.0,-1.0)

        assertEquals(Complex(0.5, 0.5), z2 / z3)
        assertEquals(Complex(0.5, 0.5), z2 / 2.0)
    }
    @Test
    fun `Complex equals`(){
        assertEquals(true, Complex(1.0, 1.0)  ==  Complex(1.0, 1.0))
        assertEquals(false, Complex(1.0, 1.0)  ==  Complex(1.0, - 1.0))

        assertEquals(true, PolarComplex(1.0, 1.0)  ==  PolarComplex(1.0, 1.0))
        assertEquals(false, PolarComplex(1.0, 1.0)  ==  PolarComplex(1.0, - 1.0))
    }
    @Test
    fun `Complex transforms`(){
        val z =Complex(1.0, 1.0)
        assertEquals(true, z.polarForm()  ==  PolarComplex(sqrt(2.0), 45.0))

        val z2 = PolarComplex(1.0, 0.0)
        assertEquals(true, z2.algebraticForm()  ==  Complex(1.0, 0.0))
    }
    @Test
    fun `Complex power`(){
        val z0 = PolarComplex(2.0, 45.0)

        assertEquals(PolarComplex(4.0, 90.0),z0.pow(2.0))
    }

}
