package com.mrsalwater.kapteyn.decompiler.classfile.attribute;

public final class Attributes {

    private final Attribute[] attributes;

    public Attributes(Attribute[] attributes) {
        this.attributes = attributes;
    }

    public Attribute[] getAttributes() {
        return attributes;
    }

    public <T extends Attribute> boolean has(Class<T> attributeType) {
        for (Attribute attribute : attributes) {
            if (attribute != null && attribute.getClass() == attributeType) {
                return true;
            }
        }

        return false;
    }

    @SuppressWarnings("unchecked")
    public <T extends Attribute> T get(Class<T> attributeType) {
        for (Attribute attribute : attributes) {
            if (attribute != null && attribute.getClass() == attributeType) {
                return (T) attribute;
            }
        }

        throw new NullPointerException();
    }

}
