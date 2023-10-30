package com.azin.TestingJavawithJUnit5Mockito;

import com.azin.TestingJavawithJUnit5Mockito.service.Calculator;
import org.junit.jupiter.api.*;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Test class to test all calculator class methods")
@SpringBootTest
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


    @DisplayName("Sum 2 , 4 = 6")
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

    @DisplayName("Division 2 , 4 = 6")
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
}
