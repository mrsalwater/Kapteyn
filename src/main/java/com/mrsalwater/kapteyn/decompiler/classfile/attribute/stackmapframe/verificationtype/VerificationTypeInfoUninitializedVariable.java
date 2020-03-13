package com.mrsalwater.kapteyn.decompiler.classfile.attribute.stackmapframe.verificationtype;

public final class VerificationTypeInfoUninitializedVariable implements VerificationTypeInfo {

    private final int offset;

    public VerificationTypeInfoUninitializedVariable(int offset) {
        this.offset = offset;
    }

    public int getOffset() {
        return offset;
    }

}
