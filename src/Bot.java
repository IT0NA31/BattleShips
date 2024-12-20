import java.util.Objects;
import java.util.Random;

/**
 * A class building an autonomous player/Bot to play against
 */
public class Bot extends Ships implements User{
    private int bat = 1;
    private int cru = 2;
    private int des = 3;
    private int sub = 4;

    private final int shipFields = bat * 5 + cru * 4 + des * 3 + sub * 2;
    private int hits = 0;

    private  String[][] groundPlay;
    private final String[][] groundOpp;
    private final String[][] groundTransfer;

    // Constructor

    public Bot(String[][] groundPlay, String[][] groundOpp, String[][] groundTransfer) {
        this.groundPlay     = groundPlay;
        this.groundOpp      = groundOpp;
        this.groundTransfer = groundTransfer;
    }

    // Playing methods

    public void placeShip(){
        Random rand = new Random();

        groundPlay = grounds[rand.nextInt(0, grounds.length)];
    }

    public void shoot(){
        Random rand = new Random();

        int posX1 = rand.nextInt(0, 9);
        int posY1 = rand.nextInt(0, 9);

        if (Objects.equals(groundTransfer[posY1][posX1], "S")) {
            groundOpp[posY1][posX1] = "X";
            System.out.println("! ! ! Ship hit ! ! !");
            hits++;
            shoot();
        } else {
            groundOpp[posY1][posX1] = "0";
            System.out.println("- - - Shot missed - - -");
        }
    }

    // QOL / Testing methods

    public void printField(){
        // Is not needed since the bot doesn't need to see the screen
    }

    @Override
    public boolean isGameOver(){
        return(hits >= shipFields);
    }

    // Different possible playing fields

    private String[][] playingGround1 = {
            {"S", " ", "S", "S", " ", " ", "S", " ", " ", " "},
            {"S", " ", " ", " ", " ", " ", "S", " ", "S", "S"},
            {"S", " ", "S", "S", "S", " ", "S", " ", " ", " "},
            {"S", " ", " ", " ", " ", " ", " ", " ", " ", " "},
            {"S", " ", " ", "S", " ", " ", " ", "S", "S", "S"},
            {" ", " ", " ", "S", " ", " ", " ", " ", " ", " "},
            {" ", " ", " ", "S", " ", " ", " ", " ", " ", " "},
            {"S", "S", " ", "S", " ", " ", " ", "S", "S", " "},
            {" ", " ", " ", " ", " ", " ", " ", " ", " ", " "},
            {" ", "S", "S", "S", "S", " ", " ", " ", " ", " "}};

    private String[][] playingGround2 = {
            {" ", " ", " ", "S", " ", "S", "S", " ", " ", "S"},
            {" ", " ", " ", "S", " ", " ", " ", " ", " ", "S"},
            {" ", "S", " ", "S", " ", "S", "S", "S", " ", "S"},
            {" ", "S", " ", "S", " ", " ", " ", " ", " ", " "},
            {" ", " ", " ", "S", " ", " ", "S", " ", " ", " "},
            {" ", " ", " ", " ", " ", " ", "S", " ", " ", " "},
            {" ", " ", " ", " ", " ", " ", "S", " ", "S", "S"},
            {" ", "S", " ", "S", "S", " ", "S", " ", " ", " "},
            {" ", "S", " ", " ", " ", " ", " ", " ", " ", " "},
            {" ", "S", " ", " ", "S", "S", "S", "S", " ", " "}};

    private String[][] playingGround3 = {
            {" ", "S", " ", " ", "S", "S", "S", "S", " ", " "},
            {" ", "S", " ", " ", " ", " ", " ", " ", " ", " "},
            {" ", " ", " ", "S", " ", "S", "S", " ", " ", "S"},
            {" ", " ", " ", "S", " ", " ", " ", " ", " ", "S"},
            {" ", "S", " ", "S", " ", "S", "S", "S", " ", "S"},
            {" ", "S", " ", "S", " ", " ", " ", " ", " ", " "},
            {" ", "S", " ", "S", " ", " ", "S", " ", " ", " "},
            {" ", "S", " ", " ", " ", " ", "S", " ", " ", " "},
            {" ", " ", " ", " ", " ", " ", "S", " ", "S", "S"},
            {" ", " ", " ", "S", "S", " ", " ", " ", " ", " "}};

    private String[][] playingGround4 = {
            {" ", " ", "S", " ", " ", " ", " ", " ", " ", " "},
            {"S", " ", "S", " ", " ", "S", "S", "S", " ", "S"},
            {"S", " ", "S", " ", " ", " ", " ", " ", " ", "S"},
            {"S", " ", "S", " ", " ", " ", " ", " ", " ", " "},
            {" ", " ", " ", " ", "S", "S", "S", "S", "S", " "},
            {" ", " ", " ", " ", " ", " ", " ", " ", " ", " "},
            {"S", "S", " ", "S", "S", " ", "S", " ", " ", " "},
            {" ", " ", " ", " ", " ", " ", "S", " ", "S", "S"},
            {" ", "S", "S", "S", " ", " ", "S", " ", " ", " "},
            {" ", " ", " ", " ", " ", " ", "S", " ", " ", " "}};

    private String[][] playingGround5 = {
            {" ", " ", " ", " ", "S", "S", "S", "S", "S", " "},
            {" ", " ", " ", " ", " ", " ", " ", " ", " ", " "},
            {"S", "S", " ", "S", "S", " ", "S", " ", " ", " "},
            {" ", " ", " ", " ", " ", " ", "S", " ", "S", "S"},
            {" ", "S", "S", "S", " ", " ", "S", " ", " ", " "},
            {" ", " ", " ", " ", " ", " ", "S", " ", " ", " "},
            {" ", " ", "S", " ", " ", " ", " ", " ", " ", " "},
            {"S", " ", "S", " ", " ", "S", "S", "S", " ", "S"},
            {"S", " ", "S", " ", " ", " ", " ", " ", " ", "S"},
            {"S", " ", "S", " ", " ", " ", " ", " ", " ", " "}};

    private String[][][] grounds = {playingGround1, playingGround2, playingGround3, playingGround4, playingGround5};
}
