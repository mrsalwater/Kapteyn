package com.mrsalwater.kapteyn.decompiler.classfile.constantpool;

public final class ConstantInvokeDynamic implements Constant {

    private final int bootstrapMethodAttributeIndex;
    private final int nameAndTypeIndex;

    public ConstantInvokeDynamic(int bootstrapMethodAttributeIndex, int nameAndTypeIndex) {
        this.bootstrapMethodAttributeIndex = bootstrapMethodAttributeIndex;
        this.nameAndTypeIndex = nameAndTypeIndex;
    }

    public int getBootstrapMethodAttributeIndex() {
        return bootstrapMethodAttributeIndex;
    }

    public int getNameAndTypeIndex() {
        return nameAndTypeIndex;
    }

}
