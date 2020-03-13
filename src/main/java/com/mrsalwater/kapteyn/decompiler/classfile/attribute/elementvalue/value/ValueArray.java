package com.mrsalwater.kapteyn.decompiler.classfile.attribute.elementvalue.value;

import com.mrsalwater.kapteyn.decompiler.classfile.attribute.elementvalue.ElementValue;

public final class ValueArray implements Value {

    private final ElementValue[] values;

    public ValueArray(ElementValue[] values) {
        this.values = values;
    }

    public ElementValue[] getValues() {
        return values;
    }

}
