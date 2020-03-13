package com.mrsalwater.kapteyn.decompiler.classfile.constantpool;

public final class ConstantMethodReference implements Constant {

    private final int classIndex;
    private final int nameAndTypeIndex;

    public ConstantMethodReference(int classIndex, int nameAndTypeIndex) {
        this.classIndex = classIndex;
        this.nameAndTypeIndex = nameAndTypeIndex;
    }

    public int getClassIndex() {
        return classIndex;
    }

    public int getNameAndTypeIndex() {
        return nameAndTypeIndex;
    }

}
