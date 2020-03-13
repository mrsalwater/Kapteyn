package com.mrsalwater.kapteyn.decompiler.classfile.attribute;

import com.mrsalwater.kapteyn.decompiler.classfile.attribute.stackmapframe.StackMapFrame;

public final class AttributeStackMapTable implements Attribute {

    private final StackMapFrame[] entries;

    public AttributeStackMapTable(StackMapFrame[] entries) {
        this.entries = entries;
    }

    public StackMapFrame[] getEntries() {
        return entries;
    }

}
