package com.mrsalwater.kapteyn.decompiler.classfile.constantpool;

public final class ConstantInteger implements Constant {

    private final int value;

    public ConstantInteger(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

}
