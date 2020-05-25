package com.softwaretest.Exceptions;

import static com.softwaretest.Exceptions.Constants.*;

public enum ErrorEnum
{
    MISSING_ARGUMENT(Status.INTERNAL_SERVER_ERROR, FIELD_REQUIRED),
    FIELD_TOO_SHORT_ARGUMENT(Status.INTERNAL_SERVER_ERROR, FIELD_TOO_SHORT),
    FIELD_TOO_LONG_ARGUMENT(Status.INTERNAL_SERVER_ERROR, FIELD_TOO_LONG),
    FIELD_CONTAINS_SPACES_ARGUMENT(Status.INTERNAL_SERVER_ERROR, FIELD_CONTAINS_SPACES),
    FIELD_EQUALS_ZERO_ARGUMENT(Status.INTERNAL_SERVER_ERROR, FIELD_EQUALS_ZERO),
    FIELD_CONTAINS_SYMBOLS_ARGUMENT(Status.INTERNAL_SERVER_ERROR, FIELD_CONTAINS_SYMBOLS),
    FIELD_INVALID_FORMAT_ARGUMENT(Status.INTERNAL_SERVER_ERROR, FIELD_INVALID_FORMAT),


            ;

    private final Status status;

    private final String description;


    ErrorEnum(Status status, String description) {
        this.status = status;
        this.description = description;
    }

    public Status getStatus() {
        return status;
    }

    public String getDescription() {
        return description;
    }
}
