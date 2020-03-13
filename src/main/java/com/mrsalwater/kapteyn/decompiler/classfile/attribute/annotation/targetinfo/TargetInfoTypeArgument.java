package com.mrsalwater.kapteyn.decompiler.classfile.attribute.annotation.targetinfo;

public final class TargetInfoTypeArgument implements TargetInfo {

    private final int offset;
    private final int typeArgumentIndex;

    public TargetInfoTypeArgument(int offset, int typeArgumentIndex) {
        this.offset = offset;
        this.typeArgumentIndex = typeArgumentIndex;
    }

    public int getOffset() {
        return offset;
    }

    public int getTypeArgumentIndex() {
        return typeArgumentIndex;
    }

}
