package com.mrsalwater.kapteyn.decompiler.classfile.attribute.annotation;

import com.mrsalwater.kapteyn.decompiler.classfile.attribute.elementvalue.ElementValuePair;

public final class Annotation {

    private final int typeIndex;
    private final ElementValuePair[] elementValuePairs;

    public Annotation(int typeIndex, ElementValuePair[] elementValuePairs) {
        this.typeIndex = typeIndex;
        this.elementValuePairs = elementValuePairs;
    }

    public int getTypeIndex() {
        return typeIndex;
    }

    public ElementValuePair[] getElementValuePairs() {
        return elementValuePairs;
    }

}
