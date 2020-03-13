package com.mrsalwater.kapteyn.decompiler.classfile.constantpool;

public final class ConstantMethodType implements Constant {

    private final int descriptorIndex;

    public ConstantMethodType(int descriptorIndex) {
        this.descriptorIndex = descriptorIndex;
    }

    public int getDescriptorIndex() {
        return descriptorIndex;
    }

}
