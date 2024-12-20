/**
 * An Interface used for inheriting important methods to both the Player and Bot class
 */
public interface User {
    void placeShip();

    void shoot();

    void printField();

    boolean isGameOver();
}
