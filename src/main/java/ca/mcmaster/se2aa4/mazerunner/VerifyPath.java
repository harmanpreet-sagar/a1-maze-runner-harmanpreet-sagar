package ca.mcmaster.se2aa4.mazerunner;

import java.util.Arrays;

public class VerifyPath {

    private char[][] parseMaze;
    private int[] entranceWE;
    private int[] exitWE;
    private int[] entranceEW;
    private int[] exitEW;
    private String enterPath;
    
    MoveInterface move = new Movement();

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
    private String pathVerified() {
        String status = "";
        String statusOut = "";

        status = verifyWE();

        // If the entered path does not pass the check from going West to East, then we
        // check it for the second direction.
        if (!status.equals("PASS")) {
            status = verifyEW();
        }

        if (status.equals("PASS")) {
            statusOut = "correct path";
        } else {
            statusOut = "incorrect path";
        }

        return statusOut;
    }

    public String pathVerificationResult() {
        return pathVerified();
    }

    // Method to verify the path from going West to East
    private String verifyWE() {
        String orientation = "EAST";
        int[] currentLocation = entranceWE;
        int[] finalLocation = exitWE;
        return hitTest(orientation, currentLocation, finalLocation);
    }
    
    // Method to verify the path from going East to West
    private String verifyEW() {
        String orientation = "WEST";
        int[] currentLocation = entranceEW;
        int[] finalLocation = exitEW;
        return hitTest(orientation, currentLocation, finalLocation);
    }

    // Method to carry out the hit test
    private String hitTest(String startOrientation, int[] currentLocation, int[] finalLocation) {
        String status = "";
        String path = factorToCanon();

        // Loop to iterate through the string to trace the steps and check for
        // any walls hit as a result of the entered path.
        for (int index = 0; index < path.length(); index++) {
            char currentChar = path.charAt(index);
            if (currentChar != move.forward()) {
                startOrientation = move.getNextOrientation(startOrientation, currentChar);
            } else {
                currentLocation = move.getNextLocation(startOrientation, currentLocation);
                if (parseMaze[currentLocation[0]][currentLocation[1]] == '#') {
                    status = "FAIL";
                    break;
                } else {
                    status = "PASS";
                }
            }
        }

        if (status.equals("PASS")) {
            if (!Arrays.equals(currentLocation, finalLocation)) {
                status = "FAIL";
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

        // Loop to convert factorized path to canonical path
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
