package com.the10xacademy.homework;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class BrowserHistoryImplTest {

    private BrowserHistoryImpl browserHistory;

    @BeforeEach
    void setUp() {
        browserHistory = new BrowserHistoryImpl();
    }

    @Test
    void testVisitAndCurrentPage() {
        browserHistory.visit("http://example.com");
        assertEquals(Optional.of("http://example.com"), browserHistory.getCurrentPage());
    }

    @Test
    void testBackFunctionWithoutHistory() {
        assertEquals(Optional.empty(), browserHistory.back());
    }

    @Test
    void testForwardFunctionWithoutHistory() {
        assertEquals(Optional.empty(), browserHistory.forward());
    }

    @Test
    void testBackFunction() {
        browserHistory.visit("http://first.com");
        browserHistory.visit("http://second.com");
        assertEquals(Optional.of("http://first.com"), browserHistory.back());
    }

    @Test
    void testForwardFunction() {
        browserHistory.visit("http://first.com");
        browserHistory.visit("http://second.com");
        browserHistory.back();
        assertEquals(Optional.of("http://second.com"), browserHistory.forward());
    }

    @Test
    void testGetBackCount() {
        browserHistory.visit("http://first.com");
        browserHistory.visit("http://second.com");
        assertEquals(2, browserHistory.getBackCount());
    }

    @Test
    void testGetForwardCount() {
        browserHistory.visit("http://first.com");
        browserHistory.visit("http://second.com");
        browserHistory.back();
        assertEquals(1, browserHistory.getForwardCount());
    }

    // Complex Test Case 1: Multiple Navigations with Mixed Operations
    @Test
    void testComplexNavigationScenario() {
        // Visiting pages
        browserHistory.visit("http://page1.com");
        browserHistory.visit("http://page2.com");
        browserHistory.visit("http://page3.com");

        // Back operations
        assertEquals(Optional.of("http://page2.com"), browserHistory.back());
        assertEquals(Optional.of("http://page1.com"), browserHistory.back());

        // Forward operation
        assertEquals(Optional.of("http://page2.com"), browserHistory.forward());

        // Visit another page
        browserHistory.visit("http://page4.com");

        // Validate forward stack is reset
        assertEquals(0, browserHistory.getForwardCount());

        // Current page should be the latest visited
        assertEquals(Optional.of("http://page4.com"), browserHistory.getCurrentPage());
    }

    // Complex Test Case 2: Edge Cases with Exhausted History
    @Test
    void testEdgeCaseWithExhaustedHistory() {
        // Initial visit
        browserHistory.visit("http://start.com");

        // Exhaust back stack
        browserHistory.back();
        assertEquals(Optional.empty(), browserHistory.back()); // No more history

        // Forward operation
        assertEquals(Optional.of("http://start.com"), browserHistory.forward());
        assertEquals(Optional.empty(), browserHistory.forward()); // No forward history

        // Repeated operations
        browserHistory.back();
        assertEquals(Optional.empty(), browserHistory.back());
        browserHistory.forward();
        assertEquals(Optional.empty(), browserHistory.forward());
    }
}
