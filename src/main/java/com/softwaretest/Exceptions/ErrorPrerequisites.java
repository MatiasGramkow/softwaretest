package com.softwaretest.Exceptions;

public class ErrorPrerequisites
{
    public static <T> T notNull(T object, String message)
    {
        if (object == null) {
            throw PersonalException.exceptionOf(ErrorEnum.MISSING_ARGUMENT, message);
        }

        return object;
    }

    public static String usernameLength(String field)
    {

        if (field.length() > 20)
        {
            throw PersonalException.exceptionOf(ErrorEnum.FIELD_TOO_LONG_ARGUMENT, "Username too long");
        }

        if (field.length() < 5) {
            throw PersonalException.exceptionOf(ErrorEnum.FIELD_TOO_SHORT_ARGUMENT, "Username too short");
        }

        return field;
    }


}