package com.mrsalwater.kapteyn.decompiler.classfile.attribute;

import com.mrsalwater.kapteyn.decompiler.classfile.attribute.annotation.TypeAnnotation;

public final class AttributeRuntimeInvisibleTypeAnnotations implements Attribute {

    private final TypeAnnotation[] annotations;

    public AttributeRuntimeInvisibleTypeAnnotations(TypeAnnotation[] annotations) {
        this.annotations = annotations;
    }

    public TypeAnnotation[] getAnnotations() {
        return annotations;
    }

}
