package ca.mcmaster.se2aa4.mazerunner;

import java.util.Arrays;


public class PathFinder {

    char[][] maze;

    public PathFinder(char[][] inputMaze) {
        this.maze = inputMaze;
    }

    // Method to move forward
    private static char forward() {
        return 'F';
    }

    // Method to turn right
    private static char right() {
        return 'R';
    }

    // Method to turn left
    private static char left() {
        return 'L';
    }

    // Method to determine the canonical path
    private String canonicalPath() {
        int[] startLocation;
        int[] stopLocation;
        String orientation;
        
        // Default direction for maze path finding algorithm is West to East
        boolean westEast = true;
        
        if (westEast) {
            startLocation = Openings.getWestEntrance(maze);
            stopLocation = Openings.getEastExit(maze);
            orientation = "EAST";
        } else {
            startLocation = Openings.getEastEntrance(maze);
            stopLocation = Openings.getWestExit(maze);
            orientation = "WEST";
        }
        
        int[] currentLocation = startLocation;

        StringBuilder sb = new StringBuilder();

        while (!Arrays.equals(currentLocation, stopLocation)) {
            int[] checkLoc = Movement.getCheckLocation(orientation, currentLocation);

            // If there is a wall, the object turns left, else it moves forward
            // and turns right.
            if (maze[checkLoc[0]][checkLoc[1]] == '#') {
                orientation = Movement.getNextOrientation(orientation, left());
                sb.append(left());
            } else {
                currentLocation = checkLoc;
                sb.append(forward());
                orientation = Movement.getNextOrientation(orientation, right());
                sb.append(right());
            }

        }
        // Added this as at the end it usually ends at "FR" instead of "FRL",
        // which then can easily be modified to remove the "RL".
        sb.append(left());

        String path = sb.toString();
        
        // Since RL just puts the orientation exactly how it was, we can remove
        // it from the path.
        path = path.replaceAll("RL", "");
        return path;
    }
    
    // Method to determine the factorized path
    public String factorizedPath() {
        StringBuilder sb = new StringBuilder(" ");
        String canonPath = canonicalPath();
        char currentAlpha = 'F';
        int counter = 0;
        
        // Algorithm to convert the canonical path to a factorized path
        for (int i = 0; i < canonPath.length(); i++) {
            if (canonPath.charAt(i) == currentAlpha) {
                counter++;
            } else {
                sb.append(counter);
                sb.append(currentAlpha);
                sb.append(" ");
                counter = 1;
                currentAlpha = canonPath.charAt(i);
            }
        }

        // Appends the last character
        sb.append(counter);
        sb.append(canonPath.charAt(canonPath.length() - 1));

        String factorPath = sb.toString();

        factorPath = factorPath.replaceAll(" 1F", " F");
        factorPath = factorPath.replaceAll(" 1R", " R");
        factorPath = factorPath.replaceAll(" 1L", " L");
        
        return factorPath;
    }
}
