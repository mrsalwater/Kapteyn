package com.mrsalwater.kapteyn.decompiler.classfile.attribute;

public final class AttributeEnclosingMethod implements Attribute {

    private final int classIndex;
    private final int methodIndex;

    public AttributeEnclosingMethod(int classIndex, int methodIndex) {
        this.classIndex = classIndex;
        this.methodIndex = methodIndex;
    }

    public int getClassIndex() {
        return classIndex;
    }

    public int getMethodIndex() {
        return methodIndex;
    }

}
