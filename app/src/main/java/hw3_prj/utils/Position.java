package hw3_prj.utils;

public class Position implements Comparable<Position> {
    private int xPos;
    private int yPos;

    public Position() {
        xPos = 0;
        yPos = 0;
    }

    public Position(int x, int y) {
        xPos = x;
        yPos = y;
    }

    public int getX() {
        return xPos;
    }

    public int getY() {
        return yPos;
    }

    public double range(Position vector) {
        return Math.sqrt(Math.pow(xPos - vector.xPos, 2) + Math.pow(yPos - vector.yPos, 2));
    }

    public void setX(int x) {
        xPos = x;
    }

    public void setY(int y) {
        yPos = y;
    }

    @Override
    public int compareTo(Position o) {
        if (yPos == o.getY())
            return Integer.compare(xPos, o.xPos);
        return Integer.compare(yPos, o.yPos);
    }
}

