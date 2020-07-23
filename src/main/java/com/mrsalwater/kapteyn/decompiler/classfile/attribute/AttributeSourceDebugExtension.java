package com.mrsalwater.kapteyn.decompiler.classfile.attribute;

public final class AttributeSourceDebugExtension implements Attribute {

    private final int[] debugExtension;

    public AttributeSourceDebugExtension(int[] debugExtension) {
        this.debugExtension = debugExtension;
    }

    public int[] getDebugExtension() {
        return debugExtension;
    }

}
