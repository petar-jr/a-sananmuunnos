package org.isokissa.sananmuunnos;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.Test;

class SplitterTest {
    
    @Test
    public void splitsEmptyString() {
        List<String> result = Splitter.splitToTokens("");
        assertThat(result.size()).isEqualTo(0);
    }

    @Test
    public void splitsStringWithOneWord() {
        List<String> result = Splitter.splitToTokens("abc");
        assertThat(result.size()).isEqualTo(1);
        assertThat(result.get(0)).isEqualTo("abc");
    }

    @Test
    public void splitsStringWithTwoWords() {
        List<String> result = Splitter.splitToTokens("abc  ");
        assertThat(result.size()).isEqualTo(1);
        assertThat(result.get(0)).isEqualTo("abc  ");
    }

    @Test
    public void splitsStringWithTwoWordsStartingWithSpaces() {
        List<String> result = Splitter.splitToTokens("    abc  def      ");
        assertThat(result.size()).isEqualTo(3);
        assertThat(result.get(0)).isEqualTo("    ");
        assertThat(result.get(1)).isEqualTo("abc  ");
        assertThat(result.get(2)).isEqualTo("def      ");
    }

    @Test
    public void findsTheBeginningAndRest() {
        checkTheBeginningAndRest("", "", "");
        checkTheBeginningAndRest("b", "b", "");
        checkTheBeginningAndRest("ba", "ba", "");
        checkTheBeginningAndRest("bac", "ba", "c");
        checkTheBeginningAndRest("baa", "baa", "");
        checkTheBeginningAndRest("baaca", "baa", "ca");
        checkTheBeginningAndRest("!baaca  ", "!baa", "ca  ");
        checkTheBeginningAndRest("ba'aca", "ba", "'aca");
    }

    private void checkTheBeginningAndRest(String word, String expectedBeginning, String expectedRest) {
        Splitter.WordParts wordParts = Splitter.splitWord(word);
        assertThat(wordParts.getBeginning()).isEqualTo(expectedBeginning);
        assertThat(wordParts.getRest()).isEqualTo(expectedRest);
    }
}