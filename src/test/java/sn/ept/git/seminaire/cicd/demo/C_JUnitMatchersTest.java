package sn.ept.git.seminaire.cicd.demo;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;


@Slf4j
class C_JUnitMatchersTest {


    public static final String GIT_EPT = "GIT EPT";

    @Test
    @DisplayName("Should be true")
    void shouldBeTrue() {
        boolean result = GIT_EPT.startsWith("G");
        assertTrue(result);
        //with a message displayed in case of failure
        result = GIT_EPT.endsWith("T");
        assertTrue(result, " Should end with t");

    }


    @Test
    @DisplayName("Should be false")
    void shouldBeFalse() {
        boolean result = GIT_EPT.startsWith("P");
        assertFalse(result, "my message");
    }

    @Test
    @DisplayName("Should be null")
    void shouldBeNull() {
        final Object o = null;
        assertNull(o);
    }

    @Test
    @DisplayName("Should not be null")
    void shouldNotBeNull() {
        final Object o = new Object();
        assertNotNull(o);
    }


    @Test
    @DisplayName("Should be equal")
    void shouldBeEqual() {
        final Integer ACTUAL = 9;
        final Integer EXPECTED = 9;
        assertEquals(EXPECTED, ACTUAL, "my message");
    }


    @DisplayName("Should be equal with delta")
    @Test
    void whenAssertingEqualityWithDelta_thenEqual() {
        final float square = 2 * 2; // 4
        final float rectangle = 3 * 2; // 6
        // |6-4|=2
        final float delta = 2;  // |6-4| <= delta
        assertEquals(square, rectangle, delta);
    }


    @Test
    @DisplayName("Should not be equal")
    void shouldNotBeEqual() {
        final Integer ACTUAL = 9;
        final Integer EXPECTED = 8;
        assertNotEquals(EXPECTED, ACTUAL);
    }


    @Test
    @DisplayName("Should refer to the same object")
    void shouldReferToSameObject() {
        final Object ACTUAL = new Object();
        final Object EXPECTED = ACTUAL;
        assertSame(EXPECTED, ACTUAL);
    }

    @Test
    @DisplayName("Should not refer to the same object")
    void shouldNotReferToSameObject() {
        final Object ACTUAL = new Object();
        final Object EXPECTED = new Object();
        assertNotSame(EXPECTED, ACTUAL);
    }


    @Test
    @DisplayName("Should contain the same integers")
    void shouldContainSameIntegers() {
        final int[] ACTUAL = new int[]{2, 5, 7};
        final int[] EXPECTED = new int[]{2, 5, 7};
        assertArrayEquals(EXPECTED, ACTUAL);
    }


    @Test
    void givenMultipleAssertion_whenAssertingAll_thenOK() {
        assertAll(
                "heading",
                () -> assertEquals(4, 2 * 2, "4 is 2 times 2"),
                () -> assertEquals("ept", "EPT".toLowerCase(), "ept is equal ignore case to EPT")
        );
    }


    @Test
    @DisplayName("Should contain the same elements")
    void shouldContainSameElements() {
        final List<Integer> FIRST = Arrays.asList(1, 2, 3);
        final List<Integer> SECOND = Arrays.asList(1, 2, 3);
        assertIterableEquals(FIRST, SECOND);
    }

    @Test
    @DisplayName("Should throw the correct exception")
    void shouldThrowCorrectException() {
        assertThrows(
                NullPointerException.class,
                () -> {
                    throw new NullPointerException();
                }
        );
    }


    @Test
    @DisplayName("Should not throw an exception")
    void shouldNotThrowException() {
        assertDoesNotThrow(
                () -> {}
        );
    }


    //In case we want to assert that the execution of a supplied Executable ends before a given Timeout, we can use the assertTimeout assertion:
    @Test
    @DisplayName("Should return the correct message before timeout is exceeded")
    void shouldReturnCorrectMessageBeforeTimeoutIsExceeded() {
        final String VALUE = "Hello World!";
        final String message = assertTimeout(
                Duration.ofMillis(500),
                () -> {
                    //Thread.sleep(5);
                    return VALUE;
                }
      );
        assertEquals(VALUE, message);
    }

    @Test
    void givenTwoLists_whenAssertingIterables_thenEquals() {
        Iterable<String> one = new ArrayList<>(Arrays.asList("Java", "Junit", "Matchers"));
        Iterable<String> two = new LinkedList<>(Arrays.asList("Java", "Junit", "Matchers"));
        assertIterableEquals(one, two);
    }


    //It asserts that the expected list of Strings matches the actual list. The logic to match a string with another string is :
    /*
        A?	    A occurs once or not at all => {0,1}
        A+	    A occurs once or more times => {1,}
        A*	    A occurs zero or more times =>  => {0,}
        A{n}    A occurs n times only
        A{n,}	A occurs n or more times
        A{n,m}	A occurs at least n times but less than m times
    */
    @Test
    void whenAssertingEqualityListOfStrings_thenEqual() {
        List<String> expected = Arrays.asList("[a-zA-Z]+", "[0-9]+", "JUnit", "^(\\+221|00221)?(33|78|77|76|75|70)[0-9]{7}$");
        List<String> actual = Arrays.asList("GIT", "11", "JUnit", "+221762236160");
        assertLinesMatch(expected, actual);
    }

}