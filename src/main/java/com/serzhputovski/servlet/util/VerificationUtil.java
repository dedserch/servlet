package com.serzhputovski.servlet.util;

import java.util.UUID;

public class VerificationUtil {
    public static String generateVerificationToken() {
        return UUID.randomUUID().toString();
    }
}
