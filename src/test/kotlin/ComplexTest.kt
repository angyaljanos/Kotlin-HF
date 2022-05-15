import org.junit.Assert.assertEquals
import org.junit.Test

class ComplexTest {
    @Test
    fun `Complex inverz`() {
        val z0 = Complex(1.0,1.0)
        val z1 = -z0

        assertEquals(Complex(0.0, 0.0), z0 + z1)
    }
    @Test
    fun `Complex conjugate`() {
        val z0 = Complex(1.0,1.0)
        val z1 = z0.conjugate()
        assertEquals(Complex(2.0, 0.0) , (z0 + z1))
    }
    @Test
    fun `Complex + operation`() {
        val z0 = Complex(1.0,1.0)
        assertEquals(Complex(2.0,2.0), z0 + z0)
        assertEquals(Complex(2.0, 1.0),z0 + 1.0)
        assertEquals(Complex(2.0, 1.0),1.0 + z0)
    }
    @Test
    fun `Complex * operation`() {
        val z0 = PolarComplex(1.0,90.0)
        val z1 = PolarComplex(2.0,45.0)

        assertEquals(PolarComplex(2.0, 135.0), z0 * z1)
        assertEquals(PolarComplex(2.0, 90.0), z0 * 2.0)


        val z2 = Complex(1.0, 1.0)
        val z3 = Complex(2.0,-1.0)

        assertEquals(Complex(3.0, 1.0), z2 * z3)
        assertEquals(Complex(2.0, 2.0), z2 * 2.0)
    }


}
