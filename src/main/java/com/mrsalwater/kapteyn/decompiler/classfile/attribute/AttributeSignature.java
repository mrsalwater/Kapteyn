package com.mrsalwater.kapteyn.decompiler.classfile.attribute;

public final class AttributeSignature implements Attribute {

    private final int signatureIndex;

    public AttributeSignature(int signatureIndex) {
        this.signatureIndex = signatureIndex;
    }

    public int getSignatureIndex() {
        return signatureIndex;
    }

}
