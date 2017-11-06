package uk.co.craftsmanspirit.kata.waterdispenser.request;

import uk.co.craftsmanspirit.kata.waterdispenser.exception.ArgumentOutOfRange;

public class RequestValidator {

    public static final String REQUESTED_INPUT_INVALID_MESSAGE = "The requested input is not within the valid range (1->14): ";

    public void validate(int input){
        if (input < 1 || input > 14){
            throw new ArgumentOutOfRange(
                    REQUESTED_INPUT_INVALID_MESSAGE +
                    Integer.toString(input));
        }
    }
}
