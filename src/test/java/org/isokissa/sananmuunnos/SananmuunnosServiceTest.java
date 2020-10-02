package org.isokissa.sananmuunnos;

import static org.assertj.core.api.Assertions.as;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class SananmuunnosServiceTest {

    @Test
    public void extremeCases() {
        assertSananmuunnos("", "");
        assertSananmuunnos("    ", "    ");
        assertSananmuunnos("bas", "bas");
        assertSananmuunnos("   bas  ", "   bas  ");
    }

    @Test
    public void successfulCases() {
        assertSananmuunnos("tata mama", "mata tama");
        assertSananmuunnos("   tata     mama ", "   mata     tama ");
        assertSananmuunnos("  tata mama deka  ", "  mata tama deka  ");
        assertSananmuunnos("tata mama caca   baba ", "mata tama baca   caba ");
        assertSananmuunnos("tata mama caca   baba  abc", "mata tama baca   caba  abc");
    }

    private void assertSananmuunnos(String input, String expectedOutput) {
        SananmuunnosService s = null;
        String realOutput = SananmuunnosService.muunna(input);
        assertThat(realOutput).isEqualTo(expectedOutput);
    }
}