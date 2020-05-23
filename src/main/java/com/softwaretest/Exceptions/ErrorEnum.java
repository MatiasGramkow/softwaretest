package com.softwaretest.Exceptions;

public enum ErrorEnum
{
    MISSING_ARGUMENT(Status.INTERNAL_SERVER_ERROR, "Required field"),
    FIELD_TOO_SHORT_ARGUMENT(Status.INTERNAL_SERVER_ERROR, "Field too short"),
    FIELD_TOO_LONG_ARGUMENT(Status.INTERNAL_SERVER_ERROR, "Field too long"),
    ILLEGAL_ARGUMENT(Status.INTERNAL_SERVER_ERROR, "Illegal argument, you have entered something that is not valid"),

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
