package com.mrsalwater.kapteyn.decompiler.classfile.attribute.elementvalue.value;

import com.mrsalwater.kapteyn.decompiler.classfile.attribute.annotation.Annotation;

public final class ValueAnnotation implements Value {

    private final Annotation annotationValue;

    public ValueAnnotation(Annotation annotationValue) {
        this.annotationValue = annotationValue;
    }

    public Annotation getAnnotationValue() {
        return annotationValue;
    }

}
