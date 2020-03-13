package com.mrsalwater.kapteyn.decompiler.classfile.constantpool;

public final class ConstantNameAndType implements Constant {

    private final int nameIndex;
    private final int descriptorIndex;

    public ConstantNameAndType(int nameIndex, int descriptorIndex) {
        this.nameIndex = nameIndex;
        this.descriptorIndex = descriptorIndex;
    }

    public int getNameIndex() {
        return nameIndex;
    }

    public int getDescriptorIndex() {
        return descriptorIndex;
    }

}
