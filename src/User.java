import java.util.Arrays;
import java.util.Scanner;

public class User extends Ships implements Player{
    /*
    ein Schlachtschiff (5 Kästchen) -> Battleship
    zwei Kreuzer (je 4 Kästchen) -> Cruiser
    drei Zerstörer (je 3 Kästchen) -> Destroyer
    vier U-Boote (je 2 Kästchen) -> Submarine
    */

    private String[][] groundPlay;
    private String[][] groundOpp;

    public User(String[][] groundPlay, String[][] groundOpp) {
        this.groundPlay = groundPlay;
        this.groundOpp = groundOpp;
    }

    private int convert(int i){
        int out = i - 48;

        if(out < 10){
            return out;
        } else {
            return out - 17;
        }
    }

    @Override
    public boolean placeShip(){
        boolean err = false;

        Scanner sc = new Scanner(System.in);
        System.out.println("Now pick the first coordinate of your ship:");
        String in = sc.nextLine();
        int len = in.length();
        char x1 = in.charAt(0);
        char y1 = in.charAt(len - 1);

        int posX1 = convert(x1);
        int posY1 = convert(y1);

        System.out.println("posX1: " + posX1);
        System.out.println("posY1: " + posY1);
        System.out.println("- - - - - - -");

        System.out.println("Please enter the type of ship");
        System.out.println("Choose one of the following:");
        System.out.println("Battleship -> Bat");
        System.out.println("Cruiser -> Cru");
        System.out.println("Destroyer -> Des");
        System.out.println("Submarine -> Sub");
        in = sc.nextLine();
        int size = switch (in){
            case "bat", "Bat", "battleship", "Battleship" -> 5;
            case "cru", "Cru", "cruiser", "Cruiser" -> 4;
            case "des", "Des", "destroyer", "Destroyer" -> 3;
            case "sub", "Sub", "submarine", "Submarine" -> 2;
            default -> 0;
        };

        System.out.println("Please enter the direction of your ship");
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
                (posX1 >= 10) ||
                (posY1 <= -1) ||
                (posY1 >= 10) ||
                (size == 0)   ||
                (rotation == 0);

        if(!err)
            craftShip(groundPlay, posX1, posY1, rotation, size);
        else
            System.err.println("Wrong user Input");

        return err;
    }

    @Override
    public void shoot(char posX, int posY){
        //use variable groundOpp instead of playingGround
    }

    @Override
    public void printField(){
        int conSize = 30;
        int delSize = 10;

        for(int i = 0; i < delSize; i++)
            System.out.println();

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
}
