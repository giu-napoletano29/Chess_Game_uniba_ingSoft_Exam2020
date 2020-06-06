package it.uniba.command;
import it.uniba.chessboard.Chessboard;
import it.uniba.command.history.History;
import java.util.Scanner;
/**
 * <b>This is the class of command</b>
 * <br><b>Responsibility</b>
 * <br> This class can:
 * <br> - show application menu
 * <br> - show command list
 * <br> - ask for commands
 * <br>This class know:
 * <br> - "Chessboard" class methods
 * <br> - "History" class methods
 * <br><b>This class is <i>" boundary "</i></b>.
 */
public class Command {

    /**
     * Instantiate a new Chessboard.
     */
    static Chessboard chess = new Chessboard();

    /**
     * Instantiate a new match history.
     */
    static History h = new History();

    /**
     * Display welcome menu.
     */
    public static void menu() {
        System.out.println("---------- BENVENUTI IN COMMAND-LINE CHESS GAME ----------\n\n");
    }

    /**
     * Get user's command from input stream.
     * @return command
     */
    public static String enterCommand() {
        Scanner input = new Scanner(System.in);
        System.out.println("Inserire il comando:  ");
        String command = input.nextLine(); //.toLowerCase();
        return command;
    }

    /**
     * Calls the function associated with the given command.
     * @param arg User's command returned by enterCommand()
     */
    public static void commandList(String arg) {
        arg = arg.toLowerCase();
        switch (arg) {
            case "help":
                help();
                break;
            case "play":
                play();
                break;
            case "quit":
                //Close.quit();
                quit();
                break;
            case "board":
                //chess.display();
                board();
                break;
            case "moves":
                history();
                break;
            case "captures":
                captured();
                break;
            default:
                System.out.println("Il comando inserito non esiste! "
                        + "\n\nAssicurati di aver usato la sintassi corretta, "
                        + "oppure digita 'help' per visualizzare la "
                        + "lista dei comandi");
        }
    }

    /**
     * Show all commands and descriptions.
     */
    private static void help() {
        System.out.println ( "Lista comandi:" +
                "\n\n --play: inizia una nuova partita" +
                "\n\n --quit: esci dalla partita e dal gioco" +
                "\n\n --help: visualizza la lista dei comandi" +
                "\n\n --board: visualizza la scacchiera" +
                "\n\n --moves: visualizza lo storico delle mosse" +
                "\n\n --captures: visualizza la lista dei pezzi catturati" );
    }

    /**
     * Show played moves.
     */
    private static void history() {
        h.printPreviousMoves();
    }

    /**
     * Show captured pieces.
     */
    private static void captured() {
        h.printCatchHistory();
    }

    /**
     * Start a new match.
     */
    private static void play() {
        chess = new Chessboard();
        h = new History();
        h.clearPrevious();
        h.clearCaptured();
        System.out.println("\nHai iniziato una nuova partita!\n");
        System.out.println("---Istruzioni per l'uso---");
        System.out.println("\t-È possibile effettuare una mossa utilizzando la notazione algebrica "
                + "italiana abbreviata. Es.: Ta5 per muovere, Txa5 per catturare");
        System.out.println("\t-In alternativa è possibile muovere inserendo la posizione"
                + " iniziale e la posizione finale. Es.: b2 b4");
        System.out.println("\t-Fai la tua mossa o digita 'help' per la lista dei comandi");
        while (true) {
            chess.update();
        }
    }

    /**
     * Quit current match.
     */
    private static void quit(){
        Scanner input = new Scanner ( System.in, "UTF-8");
        String command;
        System.out.println("Sei sicuro di voler uscire? [s/n]");
        command = input.nextLine().toLowerCase();
        while (!command.equals("s") && !command.equals("n")) {
            System.out.println("Errore! Digita 's' per confermare, 'n' "
                    + "se vuoi continuare la partita");
            command = input.nextLine().toLowerCase();
        }
        if (command.equals("s")) {
            System.out.println("End of Program");
            System.exit(0);
        } else {
            if (command.equals("n")) {
                System.out.println("Continuerai la partita corrente");
            }
        }
    }

    /**
     * Show the cheesboard.
     */
    private static void board(){
        chess.display();
    }
}