package core;


public class Field {
    private int bigRockNumber   = 0;
    private int smallRockNumber = 0;

    public Field() {

    }

    public int getBigRockNumber() {
        return this.bigRockNumber;
    }

    public void addBigRockNumber(int number) {
        this.bigRockNumber   += number;
    }


    public int getSmallRockNumber() {
        return this.smallRockNumber;
    }

    public void addSmallRockNumber(int number) {
        this.smallRockNumber += number;
    }

    public void setSmallRockNumber(int number) {
        this.smallRockNumber = number;
    }
}
