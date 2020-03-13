package com.mrsalwater.kapteyn.decompiler.classfile.attribute.elementvalue.value;

public final class ValueEnum implements Value {

    private final int typeNameIndex;
    private final int constNameIndex;

    public ValueEnum(int typeNameIndex, int constNameIndex) {
        this.typeNameIndex = typeNameIndex;
        this.constNameIndex = constNameIndex;
    }

    public int getTypeNameIndex() {
        return typeNameIndex;
    }

    public int getConstNameIndex() {
        return constNameIndex;
    }

}

