package com.mrsalwater.kapteyn.decompiler.classfile.attribute.annotation;

import com.mrsalwater.kapteyn.decompiler.classfile.attribute.annotation.targetinfo.TargetInfo;
import com.mrsalwater.kapteyn.decompiler.classfile.attribute.annotation.typepath.TypePath;
import com.mrsalwater.kapteyn.decompiler.classfile.attribute.elementvalue.ElementValuePair;

public final class TypeAnnotation {

    private final int targetType;
    private final TargetInfo targetInfo;
    private final TypePath typePath;
    private final int typeIndex;
    private final ElementValuePair[] elementValuePairs;

    public TypeAnnotation(int targetType, TargetInfo targetInfo, TypePath typePath, int typeIndex, ElementValuePair[] elementValuePairs) {
        this.targetType = targetType;
        this.targetInfo = targetInfo;
        this.typePath = typePath;
        this.typeIndex = typeIndex;
        this.elementValuePairs = elementValuePairs;
    }

    public int getTargetType() {
        return targetType;
    }

    public TargetInfo getTargetInfo() {
        return targetInfo;
    }

    public TypePath getTypePath() {
        return typePath;
    }

    public int getTypeIndex() {
        return typeIndex;
    }

    public ElementValuePair[] getElementValuePairs() {
        return elementValuePairs;
    }

}
