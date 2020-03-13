package com.mrsalwater.kapteyn.decompiler.classfile.constantpool;

public final class ConstantClass implements Constant {

    private final int nameIndex;

    public ConstantClass(int nameIndex) {
        this.nameIndex = nameIndex;
    }

    public int getNameIndex() {
        return nameIndex;
    }

}
