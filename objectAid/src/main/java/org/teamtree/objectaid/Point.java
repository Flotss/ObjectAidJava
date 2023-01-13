package org.teamtree.objectaid;

/**
 * Classe Point
 */
public class Point {
    /**
     * Coordonnée x du point
     */
    private int x;

    /**
     * Coordonnée y du point
     */
    private int y;

    /**
     * Constructeur de la classe Point
     *
     * @param x Coordonnée x du point
     * @param y Coordonnée y du point
     */
    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Méthode qui permet de récupérer la coordonnée x du point
     *
     * @return Coordonnée x du point
     */
    public int getX() {
        return x;
    }

    /**
     * Méthode qui permet de définir la coordonnée x du point
     *
     * @param x Coordonnée x du point
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * Méthode qui permet de récupérer la coordonnée y du point
     *
     * @return Coordonnée y du point
     */
    public int getY() {
        return y;
    }

    /**
     * Méthode qui permet de définir la coordonnée y du point
     *
     * @param y Coordonnée y du point
     */
    public void setY(int y) {
        this.y = y;
    }
}
