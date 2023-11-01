package com.azin.TestingJavawithJUnit5Mockito;

import com.azin.TestingJavawithJUnit5Mockito.service.Calculator;
import org.junit.jupiter.api.*;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


@SpringBootTest
@Order(3)
public class CalculatorRepeatedTest {

    Calculator calculator;

    @BeforeEach
    void beforeEach() {
        System.out.println("before each method in CalculatorTest called");
        calculator = new Calculator();
    }

    @DisplayName("Division by zero")
    @RepeatedTest(value = 3, name = "name = {displayName}. Repetition {currentRepetition} of {totalRepetitions}")
    void testDivision_WhenDividedByZero_ThrowException(RepetitionInfo repetitionInfo) {
        //Arrange
        int i = 4;
        int j = 0;
        String expectedExceptionMessage = "/ by zero";

        System.out.println(String.format("RepetitionCount: %s .  CurrentRepetition: %s", repetitionInfo.getTotalRepetitions()
                , repetitionInfo.getCurrentRepetition()));
        //Act & Assert
        ArithmeticException actualException = assertThrows(ArithmeticException.class, () -> {
            calculator.division(i, j);
        }, "Divide by zero should throw an exception");
        //Assert
        assertEquals(expectedExceptionMessage, actualException.getMessage(), "");
    }

}
