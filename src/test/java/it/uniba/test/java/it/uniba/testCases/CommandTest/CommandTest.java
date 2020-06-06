package it.uniba.test.java.it.uniba.testCases.CommandTest;
import it.uniba.command.history.History;
import org.junit.Ignore;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.LinkedList;
import java.util.Queue;
import static it.uniba.command.Command.*;
import static org.junit.jupiter.api.Assertions.*;
class CommandTest {
    @Test
    void menuTest(){
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        String expected = "---------- BENVENUTI IN COMMAND-LINE CHESS GAME ----------\n\n"+ System.getProperty("line.separator");
        menu();
        try{
            assertEquals( expected, outContent.toString( "UTF-8"));
        } catch (Exception e){
            System.out.println("errore");
        }
    }
    @Test
    void enterCommandTest(){
        String input = "help";
        try{
            InputStream in = new ByteArrayInputStream(input.getBytes( "UTF-8"));
            System.setIn(in);
        } catch (Exception e){
            System.out.println("errore");
        }
        assertEquals("help", enterCommand());
    }
    @Test
    void commandListTest1() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        String command = "help";
        String expected = "Lista comandi:" +
                "\n\n --play: inizia una nuova partita" +
                "\n\n --quit: esci dalla partita e dal gioco" +
                "\n\n --help: visualizza la lista dei comandi" +
                "\n\n --board: visualizza la scacchiera" +
                "\n\n --moves: visualizza lo storico delle mosse" +
                "\n\n --captures: visualizza la lista dei pezzi catturati" + System.getProperty("line.separator");
        commandList(command);
        try{
            assertEquals(expected, outContent.toString( "UTF-8"));
        } catch (Exception e){
            System.out.println("errore");
        }
    }
    @Ignore
    void commandListTest2() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        String command = "play";
        String expected = "\t-Fai la tua mossa o digita 'help' per la lista dei comandi"  + System.getProperty("line.separator");
        commandList(command);
        try{
            assertEquals(expected, outContent.toString( "UTF-8"));
        } catch (Exception e){
            System.out.println("errore");
        }
    }

    @Test
    void commandListTest5() {
        String command = "moves";
        //History h = new History();
        Queue<String> espected = new LinkedList<>();
        commandList(command);
        assertEquals(espected, History.getPreviousMoves());
    }
    @Test
    void commandListTest6() {
        String command = "captures";
        //History h = new History();
        Queue<String> espected = new LinkedList<>();
        commandList(command);
        assertEquals(espected, History.getCatchHistory());
    }

    @Test
    void commandListTest3() {
        String command = "quit";
        String input = "n";
        try {
            InputStream in = new ByteArrayInputStream(input.getBytes());
            System.setIn(in);
        } catch (Exception e){
            System.out.println("errore");
        }
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        commandList(command);
        String expected = "Sei sicuro di voler uscire? [s/n]" + System.getProperty("line.separator") +
                "Continuerai la partita corrente" + System.getProperty("line.separator");
        try{
            assertEquals(expected, outContent.toString( "UTF-8"));
        } catch (Exception e){
            System.out.println("errore");
        }
    }

    @Test
    void historyTest() {
        Queue<String> espected = new LinkedList<>();
        History h = new History();
        h.printPreviousMoves();
        assertEquals(espected, History.getPreviousMoves());
    }
    @Test
    void capturedTest() {
        Queue<String> espected = new LinkedList<>();
        History h = new History();
        h.printCatchHistory();
        assertEquals(espected, History.getCatchHistory());
    }
}

