package se.kth.iv1350.progExe.startup;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {
    private Main instancetotest;
    private ByteArrayOutputStream printoutBuffer;
    private PrintStream originalSysOut;

    @BeforeEach
    void setUp() {
        instancetotest  = new Main();

        printoutBuffer = new ByteArrayOutputStream();
        PrintStream inMemSysOut = new PrintStream(printoutBuffer);
        originalSysOut = System.out;
        System.setOut(inMemSysOut);
    }

    @AfterEach
    void tearDown() {
        instancetotest = null;
        printoutBuffer = null;
        System.setOut(originalSysOut);
    }

    @Test
    void testUIHasStarted() throws Exception {
        Main.main(null);
        String printout = printoutBuffer.toString();
        String expectedOutput = "started";
        assertTrue(printout.contains(expectedOutput), "UI did not start correctly");


    }
}