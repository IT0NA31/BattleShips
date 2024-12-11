import java.util.Objects;

public class Ships {

    private void setShip(String[][] playingGround, int posX, int posY){
        playingGround[posY][posX] = "S";
        for (int i = (posX <= 0)? 0 : posX -1; i <= ((posX >= 9)? 9 : posX +1); i++) {
            for (int j = (posY <= 0)? 0 : posY -1; j <= ((posY >= 9)? 9 : posY +1); j++) {
                if(Objects.equals(playingGround[j][i], "0")) {
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
}
