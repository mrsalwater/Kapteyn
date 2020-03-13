package com.mrsalwater.kapteyn.decompiler.classfile.attribute;

import com.mrsalwater.kapteyn.decompiler.classfile.attribute.annotation.Annotation;

public final class AttributeRuntimeVisibleAnnotations implements Attribute {

    private final Annotation[] annotations;

    public AttributeRuntimeVisibleAnnotations(Annotation[] annotations) {
        this.annotations = annotations;
    }

    public Annotation[] getAnnotations() {
        return annotations;
    }

}
