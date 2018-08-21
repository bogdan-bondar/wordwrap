package word.wrapp;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static word.wrapp.Wrapper.wrap;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import word.wrapp.WrapperTest.SplitWordTests;

@RunWith(Suite.class)
@Suite.SuiteClasses({ WrapperTest.DegenerateTests.class, SplitWordTests.class, WrapperTest.WrapWordsTest.class })

public class WrapperTest {

    public static class DegenerateTests {

        @Test
        public void emptyString() {
            assertThat(wrap("", 1), equalTo(""));
        }

        @Test
        public void stringShorterThanCol() {
            assertThat(wrap("word", 10), equalTo("word"));
        }
    }

    public static class SplitWordTests {

        @Test
        public void splitOneWord() {
            assertThat(wrap("word", 2), equalTo("wo\nrd"));
        }

        @Test
        public void splitOneWordManyTimes() throws Exception {
            assertThat(wrap("abcdefghij", 3), equalTo("abc\ndef\nghi\nj"));
        }
    }

    public static class WrapWordsTest {

        @Test
        public void wrapWellBeforeWordBoundary() throws Exception {
            assertThat(wrap("word word", 3), equalTo("wor\nd\nwor\nd"));
        }

        @Test
        public void wrapJustBeforeWordBoundary() throws Exception {
            assertThat(wrap("word word", 4), equalTo("word\nword"));
        }

        @Test
        public void wrapOnWordBoundary() throws Exception {
            assertThat(wrap("word word", 5), equalTo("word\nword"));
        }

        @Test
        public void wrapTwoWordsAfterSpace() {
            assertThat(wrap("word word", 6), equalTo("word\nword"));
        }

        @Test
        public void wrapThreeWordsAfterFirstSpace() {
            assertThat(wrap("word word word", 6), equalTo("word\nword\nword"));
        }

        @Test
        public void wrapThreeWordsAfterSecondSpace() {
            assertThat(wrap("word word word", 11), equalTo("word word\nword"));
        }
    }
}
