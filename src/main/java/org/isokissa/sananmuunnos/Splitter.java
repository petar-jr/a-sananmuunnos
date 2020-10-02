package org.isokissa.sananmuunnos;

import java.util.List; 
import java.util.ArrayList;

import static java.lang.Character.toLowerCase;

class Splitter {

    public static List<String> splitToTokens(String input) {
        List<String> result = new ArrayList<String>();
        if (input.isEmpty()) {
            return result; 
        } 
        StringBuilder current = new StringBuilder(input.substring(0, 1));
        boolean isOnSpace = input.charAt(0) == ' ';
        for (int i = 1; i < input.length(); i++) {
            char c = input.charAt(i);
            if ((isOnSpace && c != ' ') || (!isOnSpace && c == ' ')) {
                result.add(current.toString());
                current.delete(0, current.length());
                isOnSpace = !isOnSpace;
            }
            current.append(c);
        }
        result.add(current.toString());
        return result;
    }

    public static WordParts splitWord(String word) {
        if (word.isEmpty()) {
            return new WordParts("", "");
        }
        final String vowels = "aeiouyåöä";
        StringBuilder beginning = new StringBuilder();
        beginning.append(word.charAt(0));
        StringBuilder rest = new StringBuilder();
        boolean inTheBeginning = true;
        for (int i = 1; i < word.length(); i++) {
            char c = word.charAt(i);
            if (inTheBeginning && vowels.indexOf(toLowerCase(c)) != -1) {
                beginning.append(c);
            } else {
                rest.append(c);
                inTheBeginning = false;
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
