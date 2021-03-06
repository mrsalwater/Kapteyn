package com.mrsalwater.kapteyn.decompiler.classfile.field;

import com.mrsalwater.kapteyn.decompiler.classfile.attribute.Attributes;

public final class Field {

    private final int accessFlags;
    private final int nameIndex;
    private final int descriptorIndex;
    private final Attributes attributes;

    public Field(int accessFlags, int nameIndex, int descriptorIndex, Attributes attributes) {
        this.accessFlags = accessFlags;
        this.nameIndex = nameIndex;
        this.descriptorIndex = descriptorIndex;
        this.attributes = attributes;
    }

    public int getAccessFlags() {
        return accessFlags;
    }

    public int getNameIndex() {
        return nameIndex;
    }

    public int getDescriptorIndex() {
        return descriptorIndex;
    }

    public Attributes getAttributes() {
        return attributes;
    }

}
