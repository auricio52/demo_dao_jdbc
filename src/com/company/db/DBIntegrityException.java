package com.company.db;

public class DBIntegrityException extends RuntimeException {
    private static final Long serialVersionUID = 1L;

    public DBIntegrityException(String message) {
        super(message);
    }
}
