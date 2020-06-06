package it.uniba.test.java.it.uniba.testCases.ChecksTest;

import it.uniba.checks.CmdString;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CmdStringTest {

    @Test
    void getCastWhiteTest() {
        CmdString cmd = new CmdString();
        String color = "white";
        int[] test = cmd.getCast(color);

        assertArrayEquals(test, cmd.castW);
    }

    @Test
    void getCastBlackTest() {
        CmdString cmd = new CmdString();
        String color = "black";
        int[] test = cmd.getCast(color);

        assertArrayEquals(test, cmd.castB);
    }
}