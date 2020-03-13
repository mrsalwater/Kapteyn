package com.mrsalwater.kapteyn.decompiler.classfile.attribute;

import com.mrsalwater.kapteyn.decompiler.classfile.attribute.annotation.Annotation;

public final class AttributeRuntimeInvisibleAnnotations implements Attribute {

    private final Annotation[] annotations;

    public AttributeRuntimeInvisibleAnnotations(Annotation[] annotations) {
        this.annotations = annotations;
    }

    public Annotation[] getAnnotations() {
        return annotations;
    }

}
