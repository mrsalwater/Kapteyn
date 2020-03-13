package com.mrsalwater.kapteyn.decompiler.classfile.attribute.annotation.targetinfo;

public final class TargetInfoOffset implements TargetInfo {

    private final int offset;

    public TargetInfoOffset(int offset) {
        this.offset = offset;
    }

    public int getOffset() {
        return offset;
    }

}
