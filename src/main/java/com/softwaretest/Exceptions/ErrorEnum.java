package com.softwaretest.Exceptions;

import static com.softwaretest.Exceptions.Constants.*;

public enum ErrorEnum
{
    MISSING_ARGUMENT(Status.INTERNAL_SERVER_ERROR, FIELD_REQUIRED),
    FIELD_TOO_SHORT_ARGUMENT(Status.INTERNAL_SERVER_ERROR, FIELD_TOO_SHORT),
    FIELD_TOO_LONG_ARGUMENT(Status.INTERNAL_SERVER_ERROR, FIELD_TOO_LONG),

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
