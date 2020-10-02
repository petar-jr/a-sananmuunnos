package org.isokissa.sananmuunnos;

import java.util.List; 

public class SananmuunnosService {

    public static String muunna(String inputWords) {
        List<String> tokens = Splitter.splitToTokens(inputWords);
        if (tokens.size() < 3) {
            return inputWords;
        }
        StringBuilder result = new StringBuilder();
        int currentToken = 0;
        boolean isFirstTokenSpace = isSpaceToken(tokens.get(currentToken));
        if (isFirstTokenSpace) {
            result.append(tokens.get(currentToken++));
        }
        while (currentToken < tokens.size() - 2) {
            Splitter.WordParts firstWord = Splitter.splitWord(tokens.get(currentToken++));
            String spaces = tokens.get(currentToken++);
            Splitter.WordParts secondWord = Splitter.splitWord(tokens.get(currentToken++));
            result.append(secondWord.getBeginning());
            result.append(firstWord.getRest());
            result.append(spaces);
            result.append(firstWord.getBeginning());
            result.append(secondWord.getRest());
            boolean isNextTokenSpace = currentToken < tokens.size() && isSpaceToken(tokens.get(currentToken));
            if (isNextTokenSpace) {
                result.append(tokens.get(currentToken++));
            }
        }
        while (currentToken < tokens.size()) {
            result.append(tokens.get(currentToken++));
        }
        return result.toString();
    }

    private static boolean isSpaceToken(String token) {
        return token.charAt(0) == ' ';
    }

}
