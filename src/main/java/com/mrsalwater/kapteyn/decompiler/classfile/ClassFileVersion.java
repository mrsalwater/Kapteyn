package com.mrsalwater.kapteyn.decompiler.classfile;

public enum ClassFileVersion {

    JAVA_13("Java SE 13", 57),
    JAVA_12("Java SE 12", 56),
    JAVA_11("Java SE 11", 55),
    JAVA_10("Java SE 10", 54),
    JAVA_9("Java SE 9", 53),
    JAVA_8("Java SE 8", 52),
    JAVA_7("Java SE 7", 51),
    JAVA_6("Java SE 6.0", 50),
    JAVA_5("Java SE 5.0", 49),
    JAVA_4("Java SE 1.4", 48),
    JAVA_3("Java SE 1.3", 47),
    JAVA_2("Java SE 1.2", 46),
    JAVA_1("Java SE 1.1", 45),
    UNKNOWN("Unknown", -1);

    private final String name;
    private final int number;

    ClassFileVersion(String name, int number) {
        this.name = name;
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public int getNumber() {
        return number;
    }

    public static ClassFileVersion match(int version) {
        for (ClassFileVersion classFileVersion : values()) {
            if(classFileVersion.number == version) {
                return classFileVersion;
            }
        }

        return UNKNOWN;
    }

}
