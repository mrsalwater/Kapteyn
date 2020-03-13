package com.mrsalwater.kapteyn.decompiler.classfile.constantpool;

public final class ConstantLong implements Constant {

    private final long value;

    public ConstantLong(int high, int low) {
        this.value = ((high & 0xffffffffL) << 32) | (low & 0xffffffffL);
    }

    public long getValue() {
        return value;
    }

}
