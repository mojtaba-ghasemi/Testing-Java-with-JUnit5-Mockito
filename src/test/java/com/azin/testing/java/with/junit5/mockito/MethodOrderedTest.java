package com.azin.testing.java.with.junit5.mockito;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

import static org.junit.jupiter.api.Assertions.assertEquals;


//@TestMethodOrder(MethodOrderer.Random.class)
//@TestMethodOrder(MethodOrderer.MethodName.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@Order(2)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class MethodOrderedTest {

    StringBuilder methodOrder = new StringBuilder("");

    @AfterEach
    void afterEach(){
        System.out.println(methodOrder);
    }

    @Order(1)
    @Test
    void testA(){
        System.out.println("Running test A");
        methodOrder.append("1");

    }

    @Order(2)
    @Test
    void testB(){
        System.out.println("Running test B");
        methodOrder.append("2");
    }

    @Order(3)
    @Test
    void testC(){
        System.out.println("Running test C");
        methodOrder.append("3");
    }

    @Order(4)
    @Test
    void testD(){
        System.out.println("Running test D");
        methodOrder.append("4");
    }

}
