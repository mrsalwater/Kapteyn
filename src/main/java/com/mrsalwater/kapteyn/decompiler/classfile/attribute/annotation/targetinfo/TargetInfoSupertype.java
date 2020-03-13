package com.mrsalwater.kapteyn.decompiler.classfile.attribute.annotation.targetinfo;

public final class TargetInfoSupertype implements TargetInfo {

    private final int supertypeIndex;

    public TargetInfoSupertype(int supertypeIndex) {
        this.supertypeIndex = supertypeIndex;
    }

    public int getSupertypeIndex() {
        return supertypeIndex;
    }

}
