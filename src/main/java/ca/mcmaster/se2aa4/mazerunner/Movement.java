package ca.mcmaster.se2aa4.mazerunner;

import java.util.Arrays;

public class Movement implements MoveInterface {
    
    // Method to determine the location to be checked for wall.
    private static int[] nextLocation(String orientation, int[] currentLocation) {
        int[] frontLocation = Arrays.copyOf(currentLocation, currentLocation.length);

        if (orientation.equals("EAST")) {
            frontLocation[1] += 1;
        } else if (orientation.equals("WEST")) {
            frontLocation[1] -= 1;
        } else if (orientation.equals("NORTH")) {
            frontLocation[0] -= 1;
        } else {
            frontLocation[0] += 1;
        }

        return frontLocation;
    }

    @Override
    public int[] getNextLocation(String orientation, int[] currentLocation) {
        return nextLocation(orientation, currentLocation);
    }

    // Method to determine the next orientation of the object traversing the maze.
    private static String nextOrientation(String currentOrientation, char turnDirection) {
        String nextOrientation = currentOrientation;

        switch (currentOrientation) {
            case "WEST":
                if (turnDirection == 'L') {
                    nextOrientation = "SOUTH";
                } else if (turnDirection == 'R') {
                    nextOrientation = "NORTH";
                }
                break;
            case "EAST":
                if (turnDirection == 'L') {
                    nextOrientation = "NORTH";
                } else if (turnDirection == 'R') {
                    nextOrientation = "SOUTH";
                }
                break;
            case "NORTH":
                if (turnDirection == 'L') {
                    nextOrientation = "WEST";
                } else if (turnDirection == 'R') {
                    nextOrientation = "EAST";
                }
                break;
            case "SOUTH":
                if (turnDirection == 'L') {
                    nextOrientation = "EAST";
                } else if (turnDirection == 'R') {
                    nextOrientation = "WEST";
                }
                break;
            default:
                nextOrientation = currentOrientation;
                break;
        }

        return nextOrientation;
    }

    @Override
    public String getNextOrientation(String currentOrientation, char turnDirection) {
        return nextOrientation(currentOrientation, turnDirection);
    }

    // Method to move forward
    
    @Override
    public char forward() {
        return 'F';
    }

    // Method to turn right
    
    @Override
    public char right() {
        return 'R';
    }

    // Method to turn left
    
    @Override
    public char left() {
        return 'L';
    }
}
