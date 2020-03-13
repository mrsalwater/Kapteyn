package com.mrsalwater.kapteyn.decompiler.classfile.attribute;

public final class AttributeSourceFile implements Attribute {

    private final int sourceFileIndex;

    public AttributeSourceFile(int sourceFileIndex) {
        this.sourceFileIndex = sourceFileIndex;
    }

    public int getSourceFileIndex() {
        return sourceFileIndex;
    }

}
