package com.mrsalwater.kapteyn.decompiler.classfile.attribute.elementvalue;

public final class ElementValuePair {

    private final int elementNameIndex;
    private final ElementValue value;

    public ElementValuePair(int elementNameIndex, ElementValue value) {
        this.elementNameIndex = elementNameIndex;
        this.value = value;
    }

    public int getElementNameIndex() {
        return elementNameIndex;
    }

    public ElementValue getValue() {
        return value;
    }

}
