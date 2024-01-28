package ca.mcmaster.se2aa4.mazerunner;

import java.util.Arrays;

public class VerifyPath {

    private char[][] parseMaze;
    private int[] entranceWE;
    private int[] exitWE;
    private int[] entranceEW;
    private int[] exitEW;
    private String enterPath;
    
    // Constructor for the VerifyPath class
    public VerifyPath(char[][] maze, String enteredPath) {
        this.parseMaze = maze;
        
        this.entranceWE = Openings.getWestEntrance(maze);
        this.exitWE = Openings.getEastExit(maze);

        // This is because default setting is going West to East
        this.entranceEW = Openings.getEastEntrance(maze);
        this.exitEW = Openings.getWestExit(maze);

        this.enterPath = enteredPath;
    }

    

    // Method to check for path verification
    public String pathVerified() {
        StringBuilder sb = new StringBuilder();
        String status = "";

        status = verifyWE();

        // If the entered path does not pass the check from going West to East, then we
        // check it for the second direction.
        if (!status.equals("PASS")) {
            sb.append(verifyWE());
            status = verifyEW();

            if (!status.equals("PASS")) {
                sb.append("\n" + verifyEW());
                status = "FAIL \n" + sb.toString();
            }
        }

        return status;
    }

    // Method to verify the path from going West to East
    private String verifyWE() {
        String status = "";
        String orientation = "EAST";
        int[] currentLocation = entranceWE;
        String path = factorToCanon();

        // Loop to iterate through the string to trace the steps and check for
        // any walls hit as a result of the entered path.
        for (int index = 0; index < path.length(); index++) {
            char currentChar = path.charAt(index);
            if (currentChar != 'F') {
                orientation = Movement.getNextOrientation(orientation, currentChar);
            } else {
                currentLocation = Movement.getCheckLocation(orientation, currentLocation);
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
                status = "FAILED FOR WEST TO EAST: DID NOT REACH EXIT. FINAL LOCATION: "
                        + Arrays.toString(currentLocation)
                        + " Desired final Location: " + Arrays.toString(exitWE);
            }
        }

        return status;
    }
    
    // Method to verify the path from going East to West
    private String verifyEW() {
        String status = "";
        String orientation = "WEST";
        int[] currentLocation = entranceEW;
        String path = factorToCanon();

        // Loop to iterate through the string to trace the steps and check for
        // any walls hit as a result of the entered path.
        for (int index = 0; index < path.length(); index++) {
            char currentChar = path.charAt(index);
            if (currentChar != 'F') {
                orientation = Movement.getNextOrientation(orientation, currentChar);
            } else {
                currentLocation = Movement.getCheckLocation(orientation, currentLocation);
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
                status = "FAILED FOR EAST TO WEST: DID NOT REACH EXIT. FINAL LOCATION: "
                        + Arrays.toString(currentLocation)
                        + " Desired final Location: " + Arrays.toString(exitEW);
            }
        }

        return status;
    }
    
    // Method to convert a factorized path to a canonical path.
    private String factorToCanon() {
        StringBuilder sb = new StringBuilder();
        boolean hasNumber = false;
        String factPath = enterPath.replaceAll(" ", "");

        if (Character.isDigit(factPath.charAt(factPath.length() - 1))) {
            return "ERROR";
        }

        // Checks if there are factorized path elements in the entered path.
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

            // Checks if the character is a digit, if it is then it multiplies
            // the previous value by 10 and adds the new value.
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
}
