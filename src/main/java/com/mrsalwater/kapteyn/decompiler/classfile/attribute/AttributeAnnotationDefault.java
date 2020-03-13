package com.mrsalwater.kapteyn.decompiler.classfile.attribute;

import com.mrsalwater.kapteyn.decompiler.classfile.attribute.elementvalue.ElementValue;

public final class AttributeAnnotationDefault implements Attribute {

    private final ElementValue defaultValue;

    public AttributeAnnotationDefault(ElementValue defaultValue) {
        this.defaultValue = defaultValue;
    }

    public ElementValue getDefaultValue() {
        return defaultValue;
    }

}
