package com.softwaretest.Exceptions;

public class PersonalException extends RuntimeException
{

    public static PersonalException exceptionOf(ErrorEnum ee, String message)
    {
        return new PersonalException(ee, message);
    }

    private final ErrorEnum errorEnum;

    private PersonalException(ErrorEnum errorEnum, String message)
    {
        super(message);

        ErrorPrerequisites.notNull(errorEnum, "Error Enum must not be null");
        this.errorEnum = errorEnum;
    }
}
