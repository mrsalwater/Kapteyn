package com.mrsalwater.kapteyn.decompiler.classfile.attribute;

import com.mrsalwater.kapteyn.decompiler.classfile.attribute.annotation.ParameterAnnotation;

public final class AttributeRuntimeVisibleParameterAnnotations implements Attribute {

    private final ParameterAnnotation[] parameterAnnotations;

    public AttributeRuntimeVisibleParameterAnnotations(ParameterAnnotation[] parameterAnnotations) {
        this.parameterAnnotations = parameterAnnotations;
    }

    public ParameterAnnotation[] getParameterAnnotations() {
        return parameterAnnotations;
    }

}
