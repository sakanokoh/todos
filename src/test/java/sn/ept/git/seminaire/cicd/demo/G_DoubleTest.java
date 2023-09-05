package sn.ept.git.seminaire.cicd.demo;

import lombok.extern.slf4j.Slf4j;
import static org.assertj.core.api.Assertions.*;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
import org.mockito.Spy;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;
import sn.ept.git.seminaire.cicd.demo.exception.DivisionByZeroException;

import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.AdditionalAnswers.returnsSecondArg;


@Slf4j
@ExtendWith(MockitoExtension.class)
class G_DoubleTest {

    //annotations @Mock,@Spy, ... or static methods
    private ICalculator mockCalculator = mock(ICalculator.class);
    // private ICalculator spyCalculator = spy(ICalculator.class);

    @Mock
    private List<String> mockList;

    @Spy
    private List<String> spyList = new ArrayList<>();

    private double a, b;
    String item ="item to add";

    @BeforeEach
    void beforeEach() {
        a = 11;
        b = 22;
    }

    @Test
    void test_thenReturn() {
        when(mockCalculator.add(ArgumentMatchers.anyDouble(), ArgumentMatchers.anyDouble()))
                .thenReturn(0.0);

        double expected = 0.0;
        double result = mockCalculator.add(342, 122);
       assertThat(result).isEqualTo(expected);
    }

    @Test
    void test_verify() {
        when(mockCalculator.add(ArgumentMatchers.anyDouble(), ArgumentMatchers.anyDouble()))
                .then(returnsSecondArg());

        double expected = 1232;
        double result = mockCalculator.add(2345, expected);
       assertThat(result).isEqualTo(expected);

        verify(mockCalculator, times(1))
                .add(ArgumentMatchers.anyDouble(), ArgumentMatchers.anyDouble());
    }


    @Test
    void test_thenAnswer() {
        when(mockCalculator.add(ArgumentMatchers.anyDouble(), ArgumentMatchers.anyDouble()))
                .thenAnswer(new Answer<Double>() {
                    //please replace with lambda
                    //note : mocks do not ame at implementing logic
                    @Override
                    public Double answer(InvocationOnMock invocation) throws Throwable {
                        Object[] args = invocation.getArguments();
                        return Double.parseDouble(args[0].toString())+Double.parseDouble(args[1].toString());
                    }
                });
        double expected = a+b;

        double result = mockCalculator.add(a, b);

        Assertions
                .assertThat(result)
                .isEqualTo(expected);
    }


    @Test
    void test_then() {
        when(mockCalculator.add(ArgumentMatchers.anyDouble(), ArgumentMatchers.anyDouble()))
                .then(returnsFirstArg());
        double result = mockCalculator.add(a, b);
        double expected = a;
        Assertions
                .assertThat(result)
                .isEqualTo(expected);
    }

    @Test
    void test_thenThrow() throws DivisionByZeroException {
        when(mockCalculator.divide(ArgumentMatchers.anyDouble(), ArgumentMatchers.eq(0.0)))
                .thenThrow(ArithmeticException.class);

        assertThrows(
                Exception.class,
                () -> mockCalculator.divide(a, 0.0)
        );
    }

    /**
     * mock complete replacement. By default, calling the methods of mock object will do nothing
     */
    @Test
    void testMockList() {
        int expected =0;
        mockList.add(item);
        verify(mockList).add(item);
         assertThat(mockList).hasSize(expected);
    }

    /**
     * spy  => partial mock (part of the object will be mocked and part will use real method invocations)
     * spy object will call the real method when not stub
     *
     * ------------------------------
     * When shoud we use mock or spy?
     * ------------------------------
     * Mock : If you want to avoid  calling external services and just want to test the logic inside of the unit,
     * Spy : If you want to run the program as it is and just stub specific methods.
     *
     */
    @Test
    void testSpyList() {
        spyList.add(item);
        spyList.add("value");

        Assertions
                .assertThat(spyList)
                .hasSize(2)
                //.containsExactly("value",item)
                .containsExactlyInAnyOrder("value",item);

        verify(spyList).add(item);
    }

    @Test
    void testMockWithStub() {
        //stubbing a method
        when(mockList.get(ArgumentMatchers.anyInt())).thenReturn(item);
        Assertions
                .assertThat(mockList.get(0))
                .isEqualTo(item);

    }

    @Test
    void testSpyWithStub() {
        //stubbing a spy method will result the same as the mock object
        //take note of using doReturn instead of when
        doReturn(item).when(spyList).get(300);
        Assertions
                .assertThat(spyList.get(300))
                .isEqualTo(item);
    }

}