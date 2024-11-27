import java.util.Objects;

public class Ships {

    private void setShip(String[][] playingGround, int posX, int posY){
        playingGround[posY][posX] = "S";
        for (int i = (posX <= 0)? posX : posX -1; i <= ((posX >=9)? posX : posX +1); i++) {
            for (int j = (posY <= 0)? posY : posY -1; j <= ((posY >=9)? posY : posY +1); j++) {
                if(Objects.equals(playingGround[j][i], "0")) {
                    playingGround[j][i] = "X";
                }
            }
        }
    }


    public void craftShipX(String[][] playingGround, int posX, int posY, int size) { //method to create horizontal ships on the board
        if (!Objects.equals(playingGround[posY][posX], "X") || !Objects.equals(playingGround[posY][posX], "S")){
            for (int i = 0; i < 10; i++) {
                if (i < size) { //making sure that Ships are a certain size
                    setShip(playingGround, posX + i, posY);
                }
            }
        } else {
            System.err.println("Space already occupied!");
        }
    }


    public void craftShipY(String[][] playingGround, int posX, int posY, int size) { //method to create vertical ships on the board
        if (!Objects.equals(playingGround[posX][posY], "X") || !Objects.equals(playingGround[posX][posY], "S")) {
            for (int i = 0; i < 10; i++) {
                if (i < size) { //making sure that Ships are a certain size
                    setShip(playingGround, posX, posY + 1);
                }
            }
        }
    }
}
