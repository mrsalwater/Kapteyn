package com.mrsalwater.kapteyn.decompiler.classfile.attribute.annotation.targetinfo;

public final class TargetInfoCatch implements TargetInfo {

    private final int exceptionTableIndex;

    public TargetInfoCatch(int exceptionTableIndex) {
        this.exceptionTableIndex = exceptionTableIndex;
    }

    public int getExceptionTableIndex() {
        return exceptionTableIndex;
    }

}
