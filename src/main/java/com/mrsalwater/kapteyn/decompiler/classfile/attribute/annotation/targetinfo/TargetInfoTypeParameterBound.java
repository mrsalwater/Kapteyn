package com.mrsalwater.kapteyn.decompiler.classfile.attribute.annotation.targetinfo;

public final class TargetInfoTypeParameterBound implements TargetInfo {

    private final int typeParameterIndex;
    private final int boundIndex;

    public TargetInfoTypeParameterBound(int typeParameterIndex, int boundIndex) {
        this.typeParameterIndex = typeParameterIndex;
        this.boundIndex = boundIndex;
    }

    public int getTypeParameterIndex() {
        return typeParameterIndex;
    }

    public int getBoundIndex() {
        return boundIndex;
    }

}
