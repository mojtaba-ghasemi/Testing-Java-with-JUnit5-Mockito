package com.azin.TestingJavawithJUnit5Mockito;

import com.azin.TestingJavawithJUnit5Mockito.service.Calculator;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Test class to test all calculator class methods")
@SpringBootTest
@Order(1)
class CalculatorTest {

    Calculator calculator;

    @BeforeAll
    static void beforeAll() {
        System.out.println("before all method in CalculatorTest called");
    }

    @AfterAll
    static void afterAll() {
        System.out.println("after all method in CalculatorTest called");
    }

    @BeforeEach
    void beforeEach() {
        System.out.println("before each method in CalculatorTest called");
        calculator = new Calculator();
    }

    @AfterEach
    void afterEach() {
        System.out.println("aftyer each method in CalculatorTest called");
    }


    @DisplayName("Sum 2+4 = 6")
    @Test
    void testSum_When2SumBy4_Return6() {
        //Arrange
        int i = 2;
        int j = 4;
        //Act
        int result = calculator.sum(i, j);
        //Assert
        assertEquals(6, result, () -> String.format("Calculate sum result for %s , %s is: %s", i, j, result));
    }

    @DisplayName("Division 8/4 = 2")
    @Test
    void testDivision_When8SumBy4_Return2() {
        //Arrange
        int i = 8;
        int j = 4;
        //Act
        int result = calculator.division(i, j);
        //Assert
        assertEquals(2, result, () -> String.format("Calculate division result for %s , %s is: %s", i, j, result));
    }

    @Disabled("TODO: have not any implementation")
    @DisplayName("fail method")
    @Test
    void testFailed() {
        fail();
    }

    @DisplayName("Division by zero")
    @Test
    void testDivision_WhenDividedByZero_ThrowException() {
        //Arrange
        int i = 4;
        int j = 0;
        String expectedExceptionMessage = "/ by zero";
        //Act & Assert
        ArithmeticException actualException = assertThrows(ArithmeticException.class, () -> {
            calculator.division(i, j);
        }, "Divide by zero should throw an exception");
        //Assert
        assertEquals(expectedExceptionMessage, actualException.getMessage(), "");
    }


    @ParameterizedTest
    @ValueSource(strings = {"name1", "name2"})
    void valueSourceDemonstration(String firstName) {
        System.out.println(firstName);
        assertNotNull(firstName);
    }

//    @ParameterizedTest
//    @CsvSource({"32,2,30" , "55,5,50"})
//    void testSubTract(int minuend, int subtrahend, int expectedResult) {
//        //Arrange
//        //...
//        //Act
//        int result = calculator.subtract(minuend, subtrahend);
//        System.out.println(String.format("Running test subtract: %s , %s = %s", minuend, subtrahend, result));
//
//        //Assert
//        assertEquals(expectedResult, result,
//                () -> String.format("Running test subtract. Expected result: %s , i: %s , j: %s , result: %s"
//                        , expectedResult, minuend, subtrahend, result));
//    }

//    @ParameterizedTest
//    @CsvFileSource(resources = "/testSubTract.csv")
//    void testSubTract(int minuend, int subtrahend, int expectedResult) {
//        //Arrange
//        //...
//        //Act
//        int result = calculator.subtract(minuend, subtrahend);
//        System.out.println(String.format("Running test subtract: %s , %s = %s", minuend, subtrahend, result));
//
//        //Assert
//        assertEquals(expectedResult, result,
//                () -> String.format("Running test subtract. Expected result: %s , i: %s , j: %s , result: %s"
//                        , expectedResult, minuend, subtrahend, result));
//    }
}
