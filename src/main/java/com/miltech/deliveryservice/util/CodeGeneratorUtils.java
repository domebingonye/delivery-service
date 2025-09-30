package com.miltech.deliveryservice.util;

public class CodeGeneratorUtils {

    public static String generateCode(String prefix, long count) {
        return prefix + (++count);
    }
}
