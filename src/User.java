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
    public void placeShip(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Now pick the first coordinate of your ship:");
        String in = sc.nextLine();
        int len = in.length();
        char x1 = in.charAt(0);
        char y1 = in.charAt(len - 1);

        System.out.println("Now pick the second coordinate of your ship:");
        in = sc.nextLine();
        len = in.length();
        char x2 = in.charAt(0);
        char y2 = in.charAt(len - 1);

        int posX1 = convert(x1);
        int posX2 = convert(x2);
        int posY1 = convert(y1);
        int posY2 = convert(y2);

        System.out.println("posX1: " + posX1);
        System.out.println("posX2: " + posX2);
        System.out.println("posY1: " + posY1);
        System.out.println("posY2: " + posY2);
        System.out.println("- - - - - - -");

        int size = Math.abs(posX1 - posX2) + Math.abs(posY1 - posY2) + 1;
        System.out.println("size: " + size);
        System.out.println("- - - - - - -");

        craftShip(groundPlay, Math.min(posX1, posX2), Math.min(posY1, posY2), posX1 != posX2, size);
    }

    @Override
    public void shoot(char posX, int posY){
        //use variable groundOpp instead of playingGround
    }

    @Override
    public void printField(){
        for(int j = 0; j < 10; j++){
            System.out.println();
        }

        System.out.print("Your playing field:");
        System.out.print("                                            ");
        System.out.println("Your enemies playing field:");

        System.out.print("_ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _");
        System.out.print("                              ");
        System.out.println("_ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _");

        System.out.print("   A  B  C  D  E  F  G  H  I  J");
        System.out.print("                                ");
        System.out.println("   A  B  C  D  E  F  G  H  I  J");
        for(int i = 0; i < 10; i++){
            System.out.print(i + " " + Arrays.toString(groundPlay[i]));
            System.out.print("                               ");
            System.out.println(i + " " + Arrays.toString(groundOpp[i]));
        }

        System.out.print("_ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _");
        System.out.print("                              ");
        System.out.println("_ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _");
    }
}
