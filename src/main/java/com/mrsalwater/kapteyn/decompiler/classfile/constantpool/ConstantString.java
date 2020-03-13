package com.mrsalwater.kapteyn.decompiler.classfile.constantpool;

public final class ConstantString implements Constant {

    private final int stringIndex;

    public ConstantString(int stringIndex) {
        this.stringIndex = stringIndex;
    }

    public int getStringIndex() {
        return stringIndex;
    }

}
