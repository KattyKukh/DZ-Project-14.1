package ru.netology.repository;

public class AlreadyExistsException extends RuntimeException {
    private String e;

    public AlreadyExistsException(String e) {
        super(e);
    }

}
