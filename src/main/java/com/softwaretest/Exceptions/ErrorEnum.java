package com.softwaretest.Exceptions;

import com.softwaretest.Constants.Constants;

public enum ErrorEnum
{
    MISSING_ARGUMENT(Status.INTERNAL_SERVER_ERROR, Constants.MISSING_ARGUMENT),
    FIELD_TOO_SHORT_ARGUMENT(Status.INTERNAL_SERVER_ERROR, Constants.FIELD_TOO_SHORT),
    FIELD_TOO_LONG_ARGUMENT(Status.INTERNAL_SERVER_ERROR, Constants.FIELD_TOO_LONG),
    ILLEGAL_ARGUMENT(Status.INTERNAL_SERVER_ERROR, Constants.ILLEGAL_ARGUMENT),

            ;

    private final Status status;

    private final String description;

    private Constants constants;

    ErrorEnum(Status status, String description) {
        this.status = status;
        this.description = description;
        this.constants = new Constants();
    }

    public Status getStatus() {
        return status;
    }

    public String getDescription() {
        return description;
    }
}
