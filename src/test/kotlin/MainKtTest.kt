import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.io.ByteArrayOutputStream
import java.io.PrintStream

class MainKtTest {

    @Test
    fun testHelloWorldPrintedToConsole() {
        // Redirect System.out to capture the console output
        val originalOut = System.out
        val outputStreamCaptor = ByteArrayOutputStream()
        System.setOut(PrintStream(outputStreamCaptor))

        // Call the function or code that prints "Hello world" to the console
        printHelloWorld()

        // Reset System.out
        System.setOut(originalOut)

        // Verify the console output
        val consoleOutput = outputStreamCaptor.toString().trim()
        assertEquals("Hello World!", consoleOutput)
    }

    private fun printHelloWorld() {
        // This function should contain the code that prints "Hello world" to the console
        println("Hello World!")
    }
}
