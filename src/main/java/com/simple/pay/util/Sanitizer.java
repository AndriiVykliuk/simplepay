package com.simple.pay.util;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.util.Strings;

public final class Sanitizer {

    private Sanitizer() {
    }

    public static String sanitize(String string) {
        if (StringUtils.isBlank(string)) {
            return Strings.EMPTY;
        }
        return new String(new char[string.length()]).replace('\0', '*');
    }
}
