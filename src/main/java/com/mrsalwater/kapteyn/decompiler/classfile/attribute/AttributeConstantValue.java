package com.mrsalwater.kapteyn.decompiler.classfile.attribute;

public final class AttributeConstantValue implements Attribute {

    private final int constantValueIndex;

    public AttributeConstantValue(int constantValueIndex) {
        this.constantValueIndex = constantValueIndex;
    }

    public int getConstantValueIndex() {
        return constantValueIndex;
    }

}
