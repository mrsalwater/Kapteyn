package com.mrsalwater.kapteyn.decompiler.classfile.attribute.elementvalue.value;

public final class ValueClass implements Value {

    private final int classInfoIndex;

    public ValueClass(int classInfoIndex) {
        this.classInfoIndex = classInfoIndex;
    }

    public int getClassInfoIndex() {
        return classInfoIndex;
    }

}
