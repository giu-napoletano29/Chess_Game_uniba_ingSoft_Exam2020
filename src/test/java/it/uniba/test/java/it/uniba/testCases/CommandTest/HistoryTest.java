package it.uniba.test.java.it.uniba.testCases.CommandTest;

import it.uniba.command.history.History;
import org.junit.Ignore;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.Queue;

import static org.junit.jupiter.api.Assertions.*;

class HistoryTest {

    @Test
    void printPreviousMovesTest() {
        Queue<String> espected = new LinkedList<>();

        History h = new History();
        h.printPreviousMoves();

        assertEquals(espected, h.getPreviousMoves());
    }

    @Test
    void printCatchHistoryTest() {
        Queue<String> espected = new LinkedList<>();

        History h = new History();
        h.printCatchHistory();

        assertEquals(espected, h.getCatchHistory());
    }

    @Ignore
    void addPreviousTest() {
        Queue<String> espected = new LinkedList<>();
        espected.add("a2");

        History h = new History();
        h.addPrevious("a2");

        assertTrue(espected.equals(h.getPreviousMoves()));
    }

    @Ignore
    void addCatchTest() {
        Queue<String> espected = new LinkedList<>();
        espected.add("a2");

        History h = new History();
        h.addCatch("a2");

        assertTrue(espected.equals(h.getCatchHistory()));
    }

    @Test
    void clearPreviousTest() {
        History h = new History();

        h.addPrevious("a2");
        h.addPrevious("c4");
        h.addPrevious("b6");
        h.clearPrevious();

        assertTrue(h.getPreviousMoves().isEmpty());
    }

    @Test
    void clearCapturedTest() {
        History h = new History();

        h.addCatch("a2");
        h.addCatch("c4");
        h.addCatch("b6");
        h.clearCaptured();

        assertTrue(h.getCatchHistory().isEmpty());
    }
}