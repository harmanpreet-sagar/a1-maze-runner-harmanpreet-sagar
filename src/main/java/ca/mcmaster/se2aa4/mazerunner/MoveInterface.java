package ca.mcmaster.se2aa4.mazerunner;

public interface MoveInterface {
    public int[] getNextLocation(String orientation, int[] currentLocation);

    public String getNextOrientation(String currentOrientation, char turnDirection);

    public char forward();

    public char right();

    public char left();
}
