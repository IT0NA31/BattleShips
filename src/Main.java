import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Main {
    // These are QOL Variables to be changed at will
    /*
       'delSize' is the amount of empty lines printed between player screen prints, so it's used for obfuscation
       'eep' is the amount of time one has to switch the screens between different players
     */
    private static final int delSize = 40;
    private static final int eep = 5;

    private static void clearScreen(){
        for(int i = 0; i < delSize; i++)
            System.out.println();
    }

    private static void playermatch() throws InterruptedException {
        //setting up the game

        String[][] playingGround = {
                {" ", " ", " ", " ", " ", " ", " ", " ", " ", " "},
                {" ", " ", " ", " ", " ", " ", " ", " ", " ", " "},
                {" ", " ", " ", " ", " ", " ", " ", " ", " ", " "},
                {" ", " ", " ", " ", " ", " ", " ", " ", " ", " "},
                {" ", " ", " ", " ", " ", " ", " ", " ", " ", " "},
                {" ", " ", " ", " ", " ", " ", " ", " ", " ", " "},
                {" ", " ", " ", " ", " ", " ", " ", " ", " ", " "},
                {" ", " ", " ", " ", " ", " ", " ", " ", " ", " "},
                {" ", " ", " ", " ", " ", " ", " ", " ", " ", " "},
                {" ", " ", " ", " ", " ", " ", " ", " ", " ", " "}};

        Player p1 = new Player(playingGround, playingGround, playingGround);
        Player p2 = new Player(playingGround, playingGround, playingGround);

        //main game loop

        boolean running = true;
        boolean player = true;
        boolean setupPhase = true;
        while(running){
            if(setupPhase){
                if(p1.isSetupOver()){
                    player = false;
                } else if (p2.isSetupOver()) {
                    player = true;
                }

                clearScreen();
                for(int i = eep; i > 0; i--) {
                    System.out.println();
                    System.out.println(i);
                    TimeUnit.SECONDS.sleep(1);
                }
                clearScreen();

                if(player) {
                    System.out.println(" - User 1 - ");
                    p1.printField();
                    p1.placeShip();
                    player = false;
                } else {
                    System.out.println(" - User 2 - ");
                    p2.printField();
                    p2.placeShip();
                    player = true;
                }

                if(p1.isSetupOver() && p2.isSetupOver()) {
                    setupPhase = false;
                    p1.transfer(p2.getGround());
                    p2.transfer(p1.getGround());
                }
            } else {
                clearScreen();
                for(int i = eep; i > 0; i--) {
                    System.out.println();
                    System.out.println(i);
                    TimeUnit.SECONDS.sleep(1);
                }
                clearScreen();
                if(player){
                    System.out.println(" - User 1 - ");
                    p1.printField();
                    p1.shoot();
                    player = false;
                } else {
                    System.out.println(" - User 2 - ");
                    p2.printField();
                    p2.shoot();
                    player = true;
                }

                if(p1.isGameOver() || p2.isGameOver()) {
                    running = false;
                    System.out.println(" - Gamer Over - ");
                }
            }
        }
    }

    private static void botmatch() throws InterruptedException {
        // Setting up the game

        String[][] playingGround = {
                {" ", " ", " ", " ", " ", " ", " ", " ", " ", " "},
                {" ", " ", " ", " ", " ", " ", " ", " ", " ", " "},
                {" ", " ", " ", " ", " ", " ", " ", " ", " ", " "},
                {" ", " ", " ", " ", " ", " ", " ", " ", " ", " "},
                {" ", " ", " ", " ", " ", " ", " ", " ", " ", " "},
                {" ", " ", " ", " ", " ", " ", " ", " ", " ", " "},
                {" ", " ", " ", " ", " ", " ", " ", " ", " ", " "},
                {" ", " ", " ", " ", " ", " ", " ", " ", " ", " "},
                {" ", " ", " ", " ", " ", " ", " ", " ", " ", " "},
                {" ", " ", " ", " ", " ", " ", " ", " ", " ", " "}};

        Player p1 = new Player(playingGround, playingGround, playingGround);
        Bot b1 = new Bot(playingGround, playingGround, playingGround);

        // main game loop

        boolean running = true;
        boolean player = true;
        boolean setupPhase = true;
        while(running) {
            clearScreen();
            if (setupPhase) {
                p1.placeShip();
                if(p1.isSetupOver()){
                    setupPhase = false;
                    p1.transfer(b1.getGround());
                    b1.transfer(p1.getGround());
                }
            } else {
                clearScreen();
                for(int i = eep; i > 0; i--) {
                    System.out.println();
                    System.out.println(i);
                    TimeUnit.SECONDS.sleep(1);
                }
                clearScreen();
            }
            if(p1.isGameOver() || b1.isGameOver()) {
                running = false;
                System.out.println(" - Gamer Over - ");
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        System.out.println("- - - - Greetings Player - - - -");
        System.out.println("Do you wish to play against a Bot? (y/n)");
        Scanner sc = new Scanner(System.in);
        boolean playermatch = switch (sc.nextLine()){
            case "y", "player" -> true;
            default -> false;
        };
        if(playermatch)
            playermatch();
        else
            botmatch();
    }
}