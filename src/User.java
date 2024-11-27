import java.util.Arrays;

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


    @Override
    public void placeShip(char posX, int posY, boolean rotation, String type){
        int size = switch (type) {
            case "Battleship", "bat" -> 5;
            case "Cruiser", "cru" -> 4;
            case "Destroyer", "des" -> 3;
            case "Submarine", "sub" -> 2;
            default -> 0;
        };

        int coordX = switch (posX){
            case 'A' -> 0;
            case 'B' -> 1;
            case 'C' -> 2;
            case 'D' -> 3;
            case 'E' -> 4;
            case 'F' -> 5;
            case 'G' -> 6;
            case 'H' -> 7;
            case 'I' -> 8;
            case 'J' -> 9;
            default -> 0;
        };

        if (rotation) {
            super.craftShipX(groundPlay, coordX, posY, size);
        } else {
            super.craftShipY(groundPlay, coordX, posY, size);
        }
    }

    @Override
    public void shoot(char posX, int posY){
        //use variable groundOpp instead of playingGround
    }

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
