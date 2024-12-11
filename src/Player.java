public interface Player {
    boolean placeShip();

    void shoot(char posX, int posY);

    void printField();
}
