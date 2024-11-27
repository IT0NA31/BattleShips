public interface Player {
    void placeShip(char posX, int posY, boolean rotation, String type);

    void shoot(char posX, int posY);
}
