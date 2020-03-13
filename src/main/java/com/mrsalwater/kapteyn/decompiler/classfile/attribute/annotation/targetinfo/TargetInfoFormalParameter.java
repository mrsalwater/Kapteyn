package com.mrsalwater.kapteyn.decompiler.classfile.attribute.annotation.targetinfo;

public final class TargetInfoFormalParameter implements TargetInfo {

    private final int formalParameterIndex;

    public TargetInfoFormalParameter(int formalParameterIndex) {
        this.formalParameterIndex = formalParameterIndex;
    }

    public int getFormalParameterIndex() {
        return formalParameterIndex;
    }

}
