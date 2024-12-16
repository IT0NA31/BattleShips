import java.util.Arrays;
import java.util.Objects;
import java.util.Scanner;

public class Player extends Ships implements User {
    /*
    ein Schlachtschiff (5 Kästchen) -> Battleship
    zwei Kreuzer (je 4 Kästchen) -> Cruiser
    drei Zerstörer (je 3 Kästchen) -> Destroyer
    vier U-Boote (je 2 Kästchen) -> Submarine
    */

    private final boolean debugMode = false;

    private int bat = 1;
    private int cru = 2;
    private int des = 3;
    private int sub = 4;

    private int shipFields = bat * 5 + cru * 4 + des * 3 + sub * 2;
    private int hits = 0;

    private final String[][] groundPlay;
    private final String[][] groundOpp;
    private final String[][] groundTransfer;

    public Player(String[][] groundPlay, String[][] groundOpp, String[][] groundTransfer) {
        this.groundPlay     = groundPlay;
        this.groundOpp      = groundOpp;
        this.groundTransfer = groundTransfer;
    }

    // QOL methods

    private int convert(int i){
        int out = i - 48;

        if(out < 10){
            return out;
        } else {
            return out - 17;
        }
    }

    @Override
    public void printField(){
        int conSize = 40;

        System.out.print("Your playing field:");
        for(int i = 0; i <= conSize + 14; i++)
            System.out.print(" ");
        System.out.println("Your enemies playing field:");

        System.out.print("_ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _");
        for(int i = 0; i <= conSize; i++)
            System.out.print(" ");
        System.out.println("_ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _");

        System.out.print("   A  B  C  D  E  F  G  H  I  J");
        for(int i = 0; i <= conSize + 2; i++)
            System.out.print(" ");
        System.out.println("   A  B  C  D  E  F  G  H  I  J");
        for(int i = 0; i < 10; i++){
            System.out.print(i + " " + Arrays.toString(groundPlay[i]));
            for(int j = 0; j <= conSize + 1; j++)
                System.out.print(" ");
            System.out.println(i + " " + Arrays.toString(groundOpp[i]));
        }

        System.out.print("_ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _");
        for(int i = 0; i <= conSize; i++)
            System.out.print(" ");
        System.out.println("_ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _");
    }

    // Testing methods

    private boolean checkField(int posX, int posY){
        if(debugMode) {
            System.out.println("checking X: " + posX);
            System.out.println("checking Y: " + posY);
        }
        try {
            return (Objects.equals(groundPlay[posY][posX], "0"));
        } catch (ArrayIndexOutOfBoundsException e){
            System.out.println("Error: " + e);
            return false;
        }
    }

    private boolean checkPlacement(int posX, int posY, int rotation, int size){
        boolean check = true;
        if(debugMode) System.out.println("size: " + size);
        if(debugMode) System.out.println("rotation: " + rotation);
        for (int i = 0; i < size; i++){
            if(debugMode) System.out.println("i: " + i);
            if(check)
                check = checkField((rotation >= 1)? posX + ((rotation == 2)? + i : -i) : posX, (rotation <= -1)? posY + ((rotation == -2)? + i : -i) : posY);
        }
        return check;
    }

    public boolean isSetupOver(){
        return ((bat == 0) &&
                (cru == 0) &&
                (des == 0) &&
                (sub == 0));
    }

    @Override
    public boolean isGameOver(){
        return false;
    }

    // Playing methods

    @Override
    public boolean placeShip(){
        boolean err = false;

        Scanner sc = new Scanner(System.in);
        System.out.println("Please pick the first coordinate of your ship:");
        String in = sc.nextLine();
        int len = in.length();
        char x1 = in.charAt(0);
        char y1 = in.charAt(len - 1);

        int posX1 = convert(x1);
        int posY1 = convert(y1);

        if(debugMode) {
            System.out.println("posX1: " + posX1);
            System.out.println("posY1: " + posY1);
            System.out.println("- - - - - - -");
        }

        System.out.println("Now enter the type of ship");
        System.out.println("Choose one of the following:");
        if (bat > 0) System.out.println("Battleship -> Bat   // " + bat + " remaining");
        if (cru > 0) System.out.println("Cruiser    -> Cru   // " + cru + " remaining");
        if (des > 0) System.out.println("Destroyer  -> Des   // " + des + " remaining");
        if (sub > 0) System.out.println("Submarine  -> Sub   // " + sub + " remaining");
        in = sc.nextLine();
        int size = switch (in){
            case "bat", "Bat", "battleship", "Battleship" -> 5;
            case "cru", "Cru", "cruiser", "Cruiser" -> 4;
            case "des", "Des", "destroyer", "Destroyer" -> 3;
            case "sub", "Sub", "submarine", "Submarine" -> 2;
            default -> 0;};
        if(     ((bat == 0) && (size == 5)) ||
                ((cru == 0) && (size == 4)) ||
                ((des == 0) && (size == 3)) ||
                ((sub == 0) && (size == 2)))
            size = 0;

        System.out.println("Furthermore, enter the direction of your ship");
        System.out.println("Options: up, down, left, right");
        in = sc.nextLine();
        int rotation = switch(in){
            case "down" -> -2;
            case "up" -> -1;
            case "left" -> 1;
            case "right" -> 2;
            default -> 0;
        };

        err =   (posX1 <= -1) ||
                (posX1 >= 10)    ||
                (posY1 <= -1) ||
                (posY1 >= 10)    ||
                (size == 0)   ||
                (rotation == 0)  ||
                !checkPlacement(posX1, posY1, rotation, size);

        if(!err)
            craftShip(groundPlay, posX1, posY1, rotation, size);
        else
            System.err.println("Wrong user Input");

        return err;
    }

    @Override
    public void shoot(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Please select where to shoot:");
        String in = sc.nextLine();
        int len = in.length();
        char x1 = in.charAt(0);
        char y1 = in.charAt(len - 1);

        int posX1 = convert(x1);
        int posY1 = convert(y1);

        try {
            if (Objects.equals(groundTransfer[posY1][posX1], "S")) {
                groundOpp[posY1][posX1] = "☣";
                System.out.println("! ! ! Ship hit ! ! !");
            } else {
                groundOpp[posY1][posX1] = "W";
                System.out.println("- - - Shot missed - - -");
            }
        } catch (ArrayIndexOutOfBoundsException e){
            System.out.println("Error: " + e);
        }
    }

    // Transfer methods

    public String[][] getGround(){
        return groundPlay;
    }

    public void transfer(String[][] groundInput){
        for(int i = 0; i < groundInput.length; i++)
            groundTransfer[i] = groundInput[i];
    }
}