package ca.mcmaster.se2aa4.mazerunner;

import java.util.Arrays;

public class VerifyPath {

    private PathFinder pathFinder = new PathFinder();
    private char[][] parseMaze;
    private int[] entranceWE;
    private int[] exitWE;
    private int[] entranceEW;
    private int[] exitEW;
    private String enterPath;
    
    public VerifyPath(char[][] maze, String enteredPath) {
        this.parseMaze = maze;

        this.entranceWE = pathFinder.entrance(maze);
        this.exitWE = pathFinder.exit(maze);

        // This is because default setting is going West to East
        this.entranceEW = pathFinder.exit(maze);
        this.exitEW = pathFinder.entrance(maze);

        this.enterPath = enteredPath;
    }

    public String pathVerified() {
        StringBuilder sb = new StringBuilder();
        String status = "";

        status = verifyWE();

        if (!status.equals("PASS")) {
            sb.append(verifyWE());
            status = verifyEW();

            if (!status.equals("PASS")) {
                sb.append("\n" + verifyEW());
                status = sb.toString();
            }
        }
        
        return status;
    }

    private String verifyWE() {
        String status = "";
        String orientation = "EAST";
        int[] currentLocation = entranceWE;
        String path = factorToCanon(enterPath);

        for (int index = 0; index < path.length(); index++) {
            char currentChar = path.charAt(index);
            if (currentChar != 'F') {
                orientation = nextOrientation(orientation, currentChar);
            } else {
                currentLocation = nextLocation(orientation, currentLocation);
                if (parseMaze[currentLocation[0]][currentLocation[1]] == '#') {
                    status = "FAILED FOR WEST TO EAST: HIT LOCATION - " + Arrays.toString(currentLocation)
                            + " ORIENTATION: " + orientation;
                    break;
                } else {
                    status = "PASS";
                }
            }
        }

        if (status.equals("PASS")) {
            if (!Arrays.equals(currentLocation, exitWE)) {
                status = "FAILED FOR WEST TO EAST: DID NOT REACH EXIT. FINAL LOCATION: " + Arrays.toString(currentLocation)
                        + " Desired final Location: " + Arrays.toString(exitWE);
            }
        }

        return status;
    }
    
    private String verifyEW() {
        String status = "";
        String orientation = "WEST";
        int[] currentLocation = entranceEW;
        String path = factorToCanon(enterPath);

        for (int index = 0; index < path.length(); index++) {
            char currentChar = path.charAt(index);
            if (currentChar != 'F') {
                orientation = nextOrientation(orientation, currentChar);
            } else {
                currentLocation = nextLocation(orientation, currentLocation);
                if (parseMaze[currentLocation[0]][currentLocation[1]] == '#') {
                    status = "FAILED FOR EAST TO WEST: HIT LOCATION - " + Arrays.toString(currentLocation)
                            + " ORIENTATION: " + orientation;
                    break;
                } else {
                    status = "PASS";
                }
            }
        }

        if (status.equals("PASS")) {
            if (!Arrays.equals(currentLocation, exitEW)) {
                status = "FAILED FOR EAST TO WEST: DID NOT REACH EXIT. FINAL LOCATION: " + Arrays.toString(currentLocation)
                        + " Desired final Location: " + Arrays.toString(exitEW);
            }
        }

        return status;
    }
    
    private String factorToCanon(String factorPath) {
        StringBuilder sb = new StringBuilder();
        boolean hasNumber = false;
        String factPath = factorPath.replaceAll(" ", "");

        if (Character.isDigit(factPath.charAt(factPath.length() - 1))) {
            return "ERROR";
        }

        for (int i = 0; i < factPath.length(); i++) {
            if (Character.isDigit(factPath.charAt(i))) {
                hasNumber = true;
                break;
            }
        }

        if (!hasNumber) {
            return factPath;
        }

        char currentChar;
        int charValue;
        int index = 0;

        while (index < factPath.length()) {
            charValue = 0;
            while (Character.isDigit(factPath.charAt(index))) {
                charValue = charValue * 10 + Character.getNumericValue(factPath.charAt(index));
                index++;
            }

            currentChar = factPath.charAt(index);

            if (charValue == 0) {
                sb.append(currentChar);
            } else {
                for (int i = 0; i < charValue; i++) {
                    sb.append(currentChar);
                }
            }

            index++;
        }

        String canonicalPath = sb.toString();
        return canonicalPath;
    }
    
    // Method to determine the location to be checked for wall.
    private int[] nextLocation(String orientation, int[] currentLocation) {
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

    // Method to determine the next orientation of the object traversing the maze.
    private String nextOrientation(String currentOrientation, char turnDirection) {
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

}