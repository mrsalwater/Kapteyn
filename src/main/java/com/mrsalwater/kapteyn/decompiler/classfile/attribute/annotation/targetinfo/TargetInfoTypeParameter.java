package com.mrsalwater.kapteyn.decompiler.classfile.attribute.annotation.targetinfo;

public final class TargetInfoTypeParameter implements TargetInfo {

    private final int typeParameterIndex;

    public TargetInfoTypeParameter(int typeParameterIndex) {
        this.typeParameterIndex = typeParameterIndex;
    }

    public int getTypeParameterIndex() {
        return typeParameterIndex;
    }

}
