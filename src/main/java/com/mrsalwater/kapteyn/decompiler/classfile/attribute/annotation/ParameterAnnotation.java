package com.mrsalwater.kapteyn.decompiler.classfile.attribute.annotation;

public final class ParameterAnnotation {

    private final Annotation[] annotations;

    public ParameterAnnotation(Annotation[] annotations) {
        this.annotations = annotations;
    }

    public Annotation[] getAnnotations() {
        return annotations;
    }

}
