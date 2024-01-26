package ca.mcmaster.se2aa4.mazerunner;

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
        // String status = "";

        String status = factorToCanon(enterPath);

        return status;
    }

    private String verifyWE() {
        String status = "";
        return status;
    }
    
    private String verifyEW() {
        String status = "";
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

        // while (index < factPath.length()) {
        //     if ((index < (factPath.length() - 2)) && (Character.isDigit(factPath.charAt(index)))
        //             && (Character.isDigit(factPath.charAt(index + 1)))) {
        //         charValue = Integer.parseInt(factPath.substring(index, index + 2));
        //         currentChar = factPath.charAt(index + 2);
        //         for (int j = 0; j < charValue; j++) {
        //             sb.append(currentChar);
        //         }
        //         index += 3;
        //     } else if ((index < factPath.length() - 2) && (Character.isDigit(factPath.charAt(index)))) {
        //         charValue = Integer.parseInt(factPath.substring(index, index + 1));
        //         currentChar = factPath.charAt(index + 1);
        //         for (int j = 0; j < charValue; j++) {
        //             sb.append(currentChar);
        //         }
        //         index += 2;
        //     } else {
        //         currentChar = factPath.charAt(index);
        //         sb.append(currentChar);
        //         index++;
        //     }
        // }

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

}