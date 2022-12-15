package org.teamthree.objectaid.util;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.teamtree.objectaid.util.LineSeparator;

import static org.assertj.core.api.Assertions.assertThat;

class LineSeparatorTests {

    private LineSeparator lineSeparator;

    @BeforeEach
    public void setUp() {
        lineSeparator = new LineSeparator();
    }

    @Test
    @DisplayName("Line separator should not be null")
    void lineSeparatorShouldNotBeNull() {
        assertThat(lineSeparator).isNotNull();
    }

    @Test
    @DisplayName("Line separator should have a black color")
    void lineSeparatorShouldHaveBlackColor() {
        assertThat(lineSeparator.getFill()).isEqualTo(javafx.scene.paint.Color.BLACK);
    }

    @Test
    @DisplayName("Line separator should have a height of 1")
    void lineSeparatorShouldHaveHeightOf1() {
        assertThat(lineSeparator.getHeight()).isEqualTo(1);
    }

    @Test
    @DisplayName("Line separator should have a width of 100")
    void lineSeparatorShouldHaveWidthOf100() {
        assertThat(lineSeparator.getWidth()).isEqualTo(100);
    }
}
