package org.isokissa.sananmuunnos;

import java.util.List; 
import java.util.ArrayList;

import static java.lang.Character.isWhitespace;
import static java.lang.Character.toLowerCase;

class Splitter {

    public static List<String> splitToTokens(String input) {
        List<String> result = new ArrayList<String>();
        if (input.isEmpty()) {
            return result; 
        } 
        StringBuilder current = new StringBuilder(input.substring(0, 1));
        boolean isOnSpace = isWhitespace(input.charAt(0));
        for (int i = 1; i < input.length(); i++) {
            char c = input.charAt(i);
            if (isWhitespace(c)) {
                if (!isOnSpace) {
                    isOnSpace = true;
                }
            } else {
                if (isOnSpace) {
                    result.add(current.toString());
                    current.delete(0, current.length());
                    isOnSpace = false;
                }
            }
            current.append(c);
        }
        result.add(current.toString());
        return result;
    }

    private enum State {START, VOWEL_START, REST};

    private static boolean isVowel(char c) {
        final String vowels = "aeiouyåöä";
        return vowels.indexOf(toLowerCase(c)) != -1;
    }

    public static WordParts splitWord(String word) {
        if (word.isEmpty()) {
            return new WordParts("", "");
        }
        StringBuilder beginning = new StringBuilder();
        StringBuilder rest = new StringBuilder();
        State state = State.START; 
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            switch (state) {
                case START: 
                    if (isVowel(c)) {
                        state = State.VOWEL_START;
                    }
                    beginning.append(c);
                    break;
                case VOWEL_START:
                    if (isVowel(c)) {
                        beginning.append(c);
                    } else {
                        state = State.REST; 
                        rest.append(c);
                    }
                    break; 
                default: 
                    rest.append(c);
            }
        }
        return new WordParts(beginning.toString(), rest.toString());
    }

    public static class WordParts {
        private String beginning;
        private String rest;

        public WordParts(String beginning, String rest) {
            this.beginning = beginning;
            this.rest = rest;
        }

        public String getBeginning() {
            return beginning;
        }

        public void setBeginning(String beginning) {
            this.beginning = beginning;
        }

        public String getRest() {
            return rest;
        }

        public void setRest(String rest) {
            this.rest = rest;
        }
    }
}
