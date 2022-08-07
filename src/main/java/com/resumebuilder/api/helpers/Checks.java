package com.resumebuilder.api.helpers;

public class Checks {

    public boolean isEmptyOrNull(String string) {
        return string == null || string.length() == 0;
    }

}
