package com.mrsalwater.kapteyn.decompiler.classfile.attribute;

public final class AttributeDebugExtension implements Attribute {

    private final int[] debugExtension;

    public AttributeDebugExtension(int[] debugExtension) {
        this.debugExtension = debugExtension;
    }

    public int[] getDebugExtension() {
        return debugExtension;
    }

}
