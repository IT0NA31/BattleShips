import java.util.Objects;

/**
 * A class used to implement logic/QOL methods which are important for the Player and Bot classes.
 * All methods and variables are for usage in the child classes.
 * Do not create objects based on this class!
 */
abstract class Ships {
    private int bat = 1;
    private int cru = 2;
    private int des = 3;
    private int sub = 4;

    private final int shipFields = bat * 5 + cru * 4 + des * 3 + sub * 2;
    private int hits = 0;

    private String[][] groundPlay;
    private String[][] groundOpp;
    private String[][] groundTransfer;



    private void setShip(String[][] playingGround, int posX, int posY){
        playingGround[posY][posX] = "S";
        for (int i = (posX <= 0)? 0 : posX -1; i <= ((posX >= 9)? 9 : posX +1); i++) {
            for (int j = (posY <= 0)? 0 : posY -1; j <= ((posY >= 9)? 9 : posY +1); j++) {
                if(Objects.equals(playingGround[j][i], " ")) {
                    playingGround[j][i] = "X";
                }
            }
        }
    }

    public void craftShip(String[][] playingGround, int posX, int posY, int rotation, int size){
        for (int i = 0; i < size; i++){
            setShip(playingGround, (rotation >= 1)? posX + ((rotation == 2)? + i : -i) : posX, (rotation <= -1)? posY + ((rotation == -2)? + i : -i) : posY);
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
