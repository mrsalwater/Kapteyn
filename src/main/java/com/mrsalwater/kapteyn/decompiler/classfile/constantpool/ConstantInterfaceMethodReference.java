package com.mrsalwater.kapteyn.decompiler.classfile.constantpool;

public final class ConstantInterfaceMethodReference implements Constant {

    private final int classIndex;
    private final int nameAndTypeIndex;

    public ConstantInterfaceMethodReference(int classIndex, int nameAndTypeIndex) {
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
