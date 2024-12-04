import java.util.Objects;

public class Ships {

    private void setShip(String[][] playingGround, int posX, int posY){
        try{
            playingGround[posY][posX] = "S";
        } catch (ArrayIndexOutOfBoundsException e){
            System.err.println("Youz stoopid no arrayz out of bounds!!! " + e);
        }
        /*
            To be used for debugging:
        System.out.println("PosX: " + posX + " / PosY: " + posY);
        System.out.println("- - - - - - - ");
         */
        for (int i = (posX <= 0)? 0 : posX -1; i <= ((posX >= 9)? 9 : posX +1); i++) {
            for (int j = (posY <= 0)? 0 : posY -1; j <= ((posY >= 9)? 9 : posY +1); j++) {
                System.out.println("X: " + i + " / Y: " + j);
                System.out.println("- - - - - - - ");
                if(Objects.equals(playingGround[j][i], "0")) {
                    playingGround[j][i] = "X";
                }
            }
        }
    }

    public void craftShip(String[][] playingGround, int posX, int posY, boolean rotation, int size){
        for (int i = 0; i < size; i++){
            setShip(playingGround, (rotation)? posX + i: posX, (rotation)? posY : posY + i);
        }
    }
}
