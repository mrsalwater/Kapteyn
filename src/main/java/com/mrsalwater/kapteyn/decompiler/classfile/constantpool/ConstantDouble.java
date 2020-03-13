package com.mrsalwater.kapteyn.decompiler.classfile.constantpool;

public final class ConstantDouble implements Constant {

    private final double value;

    public ConstantDouble(int high, int low) {
        this.value = Double.longBitsToDouble(((high & 0xffffffffL) << 32) | (low & 0xffffffffL));
    }

    public double getValue() {
        return value;
    }

}
