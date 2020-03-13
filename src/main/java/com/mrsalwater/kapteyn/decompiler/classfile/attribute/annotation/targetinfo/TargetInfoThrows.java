package com.mrsalwater.kapteyn.decompiler.classfile.attribute.annotation.targetinfo;

public final class TargetInfoThrows implements TargetInfo {

    private final int throwsTypeIndex;

    public TargetInfoThrows(int throwsTypeIndex) {
        this.throwsTypeIndex = throwsTypeIndex;
    }

    public int getThrowsTypeIndex() {
        return throwsTypeIndex;
    }

}
