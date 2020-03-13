package com.mrsalwater.kapteyn.decompiler.classfile.attribute;

import com.mrsalwater.kapteyn.decompiler.classfile.attribute.annotation.TypeAnnotation;

public final class AttributeRuntimeVisibleTypeAnnotations implements Attribute {

    private final TypeAnnotation[] annotations;

    public AttributeRuntimeVisibleTypeAnnotations(TypeAnnotation[] annotations) {
        this.annotations = annotations;
    }

    public TypeAnnotation[] getAnnotations() {
        return annotations;
    }

}
