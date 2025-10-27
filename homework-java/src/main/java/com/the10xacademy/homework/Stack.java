package com.the10xacademy.homework;

import lombok.AllArgsConstructor;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Spliterator;
import java.util.function.Consumer;

public final class Stack<E> implements Iterable<E> {

    @AllArgsConstructor
    private static final class Node<E> {
        final E item;
        Node<E> next;
    }

    private Node<E> head;
    private int size;

    public void push(E e) {
        if (e == null) {
            return;
        }
        head = new Node<>(e, head);
        size++;
    }

    public E pop() {
        if (head == null) {
            return null;
        }
        E item = head.item;
        Node<E> next = head.next;
        head.next = null;
        head = next;
        size--;
        return item;
    }

    public E peek() {
        if (head == null) {
            return null;
        }
        return head.item;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void clear() {
        Node<E> current = head;
        while (current != null) {
            Node<E> next = current.next;
            current.next = null;
            current = next;
        }
        head = null;
        size = 0;
    }


    @Override
    public Iterator<E> iterator() {
        return new Iterator<>() {
            private Node<E> current = head;

            @Override
            public boolean hasNext() {
                return current != null;
            }

            @Override
            public E next() {
                if (current == null) {
                    throw new NoSuchElementException();
                }
                E value = current.item;
                current = current.next;
                return value;
            }
        };
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Stack[");
        Node<E> current = head;
        while (current != null) {
            sb.append(current.item);
            current = current.next;
            if (current != null) {
                sb.append(", ");
            }
        }
        return sb.append("]").toString();
    }

    @Override
    public void forEach(Consumer<? super E> action) {
        Iterable.super.forEach(action);
    }

    @Override
    public Spliterator<E> spliterator() {
        return Iterable.super.spliterator();
    }
}
