package com.mrsalwater.kapteyn.decompiler.classfile.attribute;

public final class AttributeExceptions implements Attribute {

    private final int[] exceptionIndexTable;

    public AttributeExceptions(int[] exceptionIndexTable) {
        this.exceptionIndexTable = exceptionIndexTable;
    }

    public int[] getExceptionIndexTable() {
        return exceptionIndexTable;
    }

}
