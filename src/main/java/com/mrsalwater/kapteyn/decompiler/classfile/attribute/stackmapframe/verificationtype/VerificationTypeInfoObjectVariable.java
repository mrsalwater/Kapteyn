package com.mrsalwater.kapteyn.decompiler.classfile.attribute.stackmapframe.verificationtype;

public final class VerificationTypeInfoObjectVariable implements VerificationTypeInfo {

    private final int cpoolIndex;

    public VerificationTypeInfoObjectVariable(int cpoolIndex) {
        this.cpoolIndex = cpoolIndex;
    }

    public int getCpoolIndex() {
        return cpoolIndex;
    }

}
