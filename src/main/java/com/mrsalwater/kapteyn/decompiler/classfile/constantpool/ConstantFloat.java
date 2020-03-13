package com.mrsalwater.kapteyn.decompiler.classfile.constantpool;

public final class ConstantFloat implements Constant {

    private final float value;

    public ConstantFloat(int bits) {
        this.value = Float.intBitsToFloat(bits);
    }

    public float getValue() {
        return value;
    }

}
