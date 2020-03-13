package com.mrsalwater.kapteyn.decompiler.classfile.attribute.elementvalue;

import com.mrsalwater.kapteyn.decompiler.classfile.attribute.elementvalue.value.Value;

public final class ElementValue {

    private final int tag;
    private final Value value;

    public ElementValue(int tag, Value value) {
        this.tag = tag;
        this.value = value;
    }

    public int getTag() {
        return tag;
    }

    public Value getValue() {
        return value;
    }

}
