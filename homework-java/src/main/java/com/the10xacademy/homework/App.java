package com.the10xacademy.homework;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class App {

    public static final String NULL = "null";

    public static void main(String[] args) {
        BrowserHistory browser = new BrowserHistoryImpl();

        browser.visit("google.com");
        browser.visit("github.com");
        browser.visit("stackoverflow.com");

        log.info("Browser current page: {}", browser.getCurrentPage().orElse(NULL)); // stackoverflow.com
        log.info("Browser back page: {}", browser.back().orElse(NULL)); // github.com
        log.info("Browser back page: {}", browser.back().orElse(NULL)); // google.com
        log.info("Browser forward page: {}", browser.forward().orElse(NULL)); // github.com

        browser.visit("typescript.com"); // This should clear forward history
        log.info("Browser forward page: {}", browser.forward().orElse(NULL)); // null (no forward history)

    }

}
