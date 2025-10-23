package com.the10xacademy.homework;

import java.util.Optional;

public interface BrowserHistory {

    // Navigate to a new page
    void visit(String url);

    // Go back one page, return the URL or null if can't go back
    Optional<String> back();

    // Go forward one page, return the URL or null if can't go forward
    Optional<String> forward();

    // Get current page URL or null if no history
    Optional<String> getCurrentPage();

    // Get number of pages in back history
    int getBackCount();

    // Get number of pages in forward history
    int getForwardCount();

}
