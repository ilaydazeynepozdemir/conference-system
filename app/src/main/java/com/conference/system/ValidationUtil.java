package com.conference.system;

import com.conference.system.model.PresentationRequest;

import java.security.InvalidParameterException;

public class ValidationUtil {
    private ValidationUtil() {
    }

    public static boolean requestValidation(PresentationRequest request) {
        if (request.getPresentations() == null || request.getPresentations().isEmpty()) {
            throw new InvalidParameterException(ErrorMessage.invalidParameterError + request.getPresentations());
        }
        return true;
    }
}
