package org.transactionService.utils;

/**
 * This is a custom exception class that is used to represent validation-related exceptions in the application.
 *
 * @author rpranay665@gmail.com
 */
public class ValidationException extends Throwable {
    public ValidationException(String message) {
        super(message);
    }

}