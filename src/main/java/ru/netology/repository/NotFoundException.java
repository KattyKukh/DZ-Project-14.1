package ru.netology.repository;

public class NotFoundException extends RuntimeException {
    private String s;

    public NotFoundException(String s) {
        super(s);
    }

}
