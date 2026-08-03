package com.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class HelloServletTest {

    @Test
    public void testGetGreetingIsNotNull() {
        HelloServlet servlet = new HelloServlet();
        assertNotNull(servlet.getGreeting());
    }

    @Test
    public void testGetGreetingText() {
        HelloServlet servlet = new HelloServlet();
        assertEquals("Hello World from Jenkins CI/CD Pipeline!", servlet.getGreeting());
    }
}
