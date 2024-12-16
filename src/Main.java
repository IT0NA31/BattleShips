import java.util.Arrays;

public class Main {

    private static void clearScreen(){
        int delSize = 40;

        for(int i = 0; i < delSize; i++)
            System.out.println();
    }

    public static void main(String[] args) {

        //setting up the game

        String[][] PlayingGround = {
                {"0", "0", "0", "0", "0", "0", "0", "0", "0", "0"},
                {"0", "0", "0", "0", "0", "0", "0", "0", "0", "0"},
                {"0", "0", "0", "0", "0", "0", "0", "0", "0", "0"},
                {"0", "0", "0", "0", "0", "0", "0", "0", "0", "0"},
                {"0", "0", "0", "0", "0", "0", "0", "0", "0", "0"},
                {"0", "0", "0", "0", "0", "0", "0", "0", "0", "0"},
                {"0", "0", "0", "0", "0", "0", "0", "0", "0", "0"},
                {"0", "0", "0", "0", "0", "0", "0", "0", "0", "0"},
                {"0", "0", "0", "0", "0", "0", "0", "0", "0", "0"},
                {"0", "0", "0", "0", "0", "0", "0", "0", "0", "0"}};

        String[][] p1Play = new String[PlayingGround.length][];
        for(int i = 0; i < 10; i++)
            p1Play[i] = Arrays.copyOf(PlayingGround[i], PlayingGround.length);

        String[][] p1Opp = new String[PlayingGround.length][];
        for(int i = 0; i < 10; i++)
            p1Opp[i] = Arrays.copyOf(PlayingGround[i], PlayingGround.length);

        String[][] p1Transfer = new String[PlayingGround.length][];
        for(int i = 0; i < 10; i++)
            p1Transfer[i] = Arrays.copyOf(PlayingGround[i], PlayingGround.length);

        String[][] p2Play = new String[PlayingGround.length][];
        for(int i = 0; i < 10; i++)
            p2Play[i] = Arrays.copyOf(PlayingGround[i], PlayingGround.length);

        String[][] p2Opp = new String[PlayingGround.length][];
        for(int i = 0; i < 10; i++)
            p2Opp[i] = Arrays.copyOf(PlayingGround[i], PlayingGround.length);

        String[][] p2Transfer = new String[PlayingGround.length][];
        for(int i = 0; i < 10; i++)
            p2Transfer[i] = Arrays.copyOf(PlayingGround[i], PlayingGround.length);

        Player p1 = new Player(p1Play, p1Opp, p1Transfer);
        Player p2 = new Player(p2Play, p2Opp, p2Transfer);

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
                if(player){
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
}






























/*
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[][] PlayingGround = {
                {"0", "0", "0", "0", "0", "0", "0", "0", "0", "0"},
                {"0", "0", "0", "0", "0", "0", "0", "0", "0", "0"},
                {"0", "0", "0", "0", "0", "0", "0", "0", "0", "0"},
                {"0", "0", "0", "0", "0", "0", "0", "0", "0", "0"},
                {"0", "0", "0", "0", "0", "0", "0", "0", "0", "0"},
                {"0", "0", "0", "0", "0", "0", "0", "0", "0", "0"},
                {"0", "0", "0", "0", "0", "0", "0", "0", "0", "0"},
                {"0", "0", "0", "0", "0", "0", "0", "0", "0", "0"},
                {"0", "0", "0", "0", "0", "0", "0", "0", "0", "0"},
                {"0", "0", "0", "0", "0", "0", "0", "0", "0", "0"}};


        boolean running = true;
        String[][] p1Play= PlayingGround;
        String[][] p1Opp= PlayingGround;
        String[][] p2Play= PlayingGround;
        String[][] p2Opp= PlayingGround;
        Player p1 = new Player(p1Play, p1Opp);
        Player p2 = new Player(p2Play, p2Opp);
        boolean user = true;
        while(running){
            switch(sc.nextLine()) {
                case "exit":
                    running = false;
                    break;
                case "place":
                    if(user){
                        System.out.println("Choose one of the following:");
                        System.out.println("Battleship -> Bat");
                        System.out.println("Cruiser -> Cru");
                        System.out.println("Destroyer -> Des");
                        System.out.println("Submarine -> Sub");
                        String type = sc.nextLine();

                        System.out.println("Now pick an X Coordinate");
                        int posX = switch (sc.nextLine()){
                            case "A" -> 0;
                            case "B" -> 1;
                            case "C" -> 2;
                            case "D" -> 3;
                            case "E" -> 4;
                            case "F" -> 5;
                            case "G" -> 6;
                            case "H" -> 7;
                            case "I" -> 8;
                            case "J" -> 9;
                            default -> 0;
                        };

                        System.out.println("Now pick a Y Coordinate");
                        int posY = sc.nextInt();

                        System.out.println("Now pick a direction");
                        System.out.println("Horizontal -> X");
                        System.out.println("Vertical -> Y");
                        boolean rotation = switch (sc.nextLine()){
                            case "X" -> true;
                            case "Y" -> false;
                            default -> true;
                        };

                        p1.placeShip(posX , posY, rotation, type);

                        p1.printField();
                        user = !user;
                    } else {
                        //copy code from above and replace p1 to p2
                        user = !user;
                    }
                    break;

            }
        }
    }
}
*/
