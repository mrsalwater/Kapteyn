package com.mrsalwater.kapteyn.decompiler.util;

public final class ClassFileUtil {

    private ClassFileUtil() {

    }

    public static String getReturnType(String descriptor) {
        char firstCharacter = descriptor.charAt(0);
        if (firstCharacter == '[') {
            return getReturnType(descriptor.substring(1)).concat("[]");
        }

        if (firstCharacter == 'V') {
            return "void";
        } else if (firstCharacter == 'L') {
            return descriptor.substring(1, descriptor.length() - 1);
        }

        return getPrimitiveType(firstCharacter);
    }

    public static String getFieldType(String descriptor) {
        char firstCharacter = descriptor.charAt(0);
        if (firstCharacter == '[') {
            return getFieldType(descriptor.substring(1)).concat("[]");
        }

        if (firstCharacter == 'L') {
            return descriptor.substring(1, descriptor.length() - 1);
        }

        return getPrimitiveType(firstCharacter);
    }

    public static String getPrimitiveType(char c) {
        switch (c) {
            case 'B':
                return "byte";
            case 'C':
                return "char";
            case 'D':
                return "double";
            case 'F':
                return "float";
            case 'I':
                return "int";
            case 'J':
                return "long";
            case 'S':
                return "short";
            case 'Z':
                return "boolean";
            default:
                throw new NullPointerException("");
        }
    }

    public static String getNewArrayPrimitiveType(int atype) {
        switch (atype) {
            case 4:
                return "T_BOOLEAN";
            case 5:
                return "T_CHAR";
            case 6:
                return "T_FLOAT";
            case 7:
                return "T_DOUBLE";
            case 8:
                return "T_BYTE";
            case 9:
                return "T_SHORT";
            case 10:
                return "T_INT";
            case 11:
                return "T_LONG";
            default:
                throw new NullPointerException();
        }
    }

}
