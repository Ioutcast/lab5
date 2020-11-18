package org.example;

import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * класс координа - поле класса воркера
 */
@EqualsAndHashCode
@ToString
public
class Coordinates implements Comparable<Coordinates> {
    private int x; //Максимальное значение поля: 235
    private int y; //Значение поля должно быть больше -245

    private static int xMax = 235;

    private static int yMin = -245;

    /**
     * @param x
     * @param y
     */
    public Coordinates(int x, int y) {
        setX(x);
        setY(y);
    }

    /**
     * @param x
     */
    public void setX(int x) {
        try {
            if (x < xMax) {
                this.x = x;
            } else
                throw new IllegalArgumentException();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            System.out.println("Данное число " + x + " выходит запределы разрешенного. Максимальное число" + getXMax());
        }
    }

    /**
     * @param y
     */
    public void setY(int y) {
        try {
            if (y > yMin) {
                this.y = y;
            } else
                throw new IllegalArgumentException();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            System.out.println("Данное число " + y + " выходит запределы разрешенного. Минимальное число " + getYMin());
        }
    }


    public static int getXMax() {
        return xMax;
    }

    public static int getYMin() {
        return yMin;
    }

    @Override
    public int compareTo(Coordinates o) {
        if (this.x > o.getX() && this.y > o.getY()) return 1;
        else if (this.x < o.getX() && this.y < o.getY()) return -1;
        else return 0;
    }


    public int getX() {
        return this.x;
    }


    public int getY() {
        return this.y;
    }
}
