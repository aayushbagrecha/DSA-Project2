import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import student.TestCase;

/**
 * @author Aayush Bagrecha
 * @author Yash Shrikant
 * 
 * @version 1.0
 */
public class SemSearchTest extends TestCase {
    /**
     * Sets up the tests that follow. In general, used for initialization
     */
    public void setUp() {
        // Nothing here
    }


    /**
     * Get code coverage of the class declaration.
     */
    public void testMInitx() {
        SemSearch sem = new SemSearch();
        assertNotNull(sem);
    }


    /**
     * Check the validArgument parameter
     */
    public void testValidArguments() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setErr(new PrintStream(outContent));
        String[] args = { "128", "SingleInput.txt" };
        SemSearch.main(args);
        // Restore the standard error stream
        System.setErr(System.err);
        String errorMessage = outContent.toString().trim();
        assertEquals("", errorMessage);
    }


    /**
     * Check the validArgument parameter
     */
    public void testInvalidFileParameter() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setErr(new PrintStream(outContent));
        String[] args = { "16", "InvalidFile.txt" };
        SemSearch.main(args);
        String expectedOutput = "File not found:\n";
        assertEquals(expectedOutput, outContent.toString());
    }



    /**
     * Check the validArgument parameter
     */
    public void testInvalidNonPowerTwoInteger() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setErr(new PrintStream(outContent));
        String[] args = { "63", "P2Sample_input.txt" };
        SemSearch.main(args);
        String expectedOutput = "Argument must be power of two.\n";
        assertEquals(expectedOutput, outContent.toString());
    }


    /**
     * Check the isPowerOfTwo method
     */
    public void testIsPowerOfTwo() {
        assertTrue(SemSearch.isPowerOfTwo(1)); // 2^0 = 1
        assertFalse(SemSearch.isPowerOfTwo(0)); // Not a power of two
    }
}
