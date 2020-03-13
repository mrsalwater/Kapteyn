package com.mrsalwater.kapteyn.decompiler.classfile.constantpool;

public final class ConstantUTF8 implements Constant {

    private final String value;

    public ConstantUTF8(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

}
