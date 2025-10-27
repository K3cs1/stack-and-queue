package com.the10xacademy.homework;

import java.util.Optional;

public class BrowserHistoryImpl implements BrowserHistory {

    private Stack<String> backStack = new Stack<>();
    private Stack<String> forwardStack = new Stack<>();

    @Override
    public void visit(String url) {
        backStack.push(url);
    }

    @Override
    public Optional<String> back() {
        if (backStack.isEmpty()) {
            return Optional.empty();
        }
        forwardStack.push(backStack.pop());
        return Optional.ofNullable(backStack.peek());
    }

    @Override
    public Optional<String> forward() {
        if (forwardStack.isEmpty()) {
            return Optional.empty();
        }
        Optional<String> forwardOptional = Optional.ofNullable(forwardStack.peek());
        forwardStack.clear();
        return forwardOptional;
    }

    @Override
    public Optional<String> getCurrentPage() {
        if (backStack.isEmpty()) {
            return Optional.empty();
        }
        return Optional.ofNullable(backStack.peek());
    }

    @Override
    public int getBackCount() {
        return backStack.size();
    }

    @Override
    public int getForwardCount() {
        return forwardStack.size();
    }

}
