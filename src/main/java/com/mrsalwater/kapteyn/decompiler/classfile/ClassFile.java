package com.mrsalwater.kapteyn.decompiler.classfile;

import com.mrsalwater.kapteyn.decompiler.classfile.attribute.Attributes;
import com.mrsalwater.kapteyn.decompiler.classfile.constantpool.ConstantPool;
import com.mrsalwater.kapteyn.decompiler.classfile.field.Field;
import com.mrsalwater.kapteyn.decompiler.classfile.method.Method;

public final class ClassFile {

    private final int minorVersion;
    private final int majorVersion;

    private final ConstantPool constantPool;

    private final int accessFlags;
    private final int thisClass;
    private final int superClass;

    private final int[] interfaces;
    private final Field[] fields;
    private final Method[] methods;
    private final Attributes attributes;

    public ClassFile(int minorVersion, int majorVersion, ConstantPool constantPool, int accessFlags, int thisClass, int superClass, int[] interfaces, Field[] fields, Method[] methods, Attributes attributes) {
        this.minorVersion = minorVersion;
        this.majorVersion = majorVersion;
        this.constantPool = constantPool;
        this.accessFlags = accessFlags;
        this.thisClass = thisClass;
        this.superClass = superClass;
        this.interfaces = interfaces;
        this.fields = fields;
        this.methods = methods;
        this.attributes = attributes;
    }

    public int getMinorVersion() {
        return minorVersion;
    }

    public int getMajorVersion() {
        return majorVersion;
    }

    public ConstantPool getConstantPool() {
        return constantPool;
    }

    public int getAccessFlags() {
        return accessFlags;
    }

    public int getThisClass() {
        return thisClass;
    }

    public int getSuperClass() {
        return superClass;
    }

    public int[] getInterfaces() {
        return interfaces;
    }

    public Field[] getFields() {
        return fields;
    }

    public Method[] getMethods() {
        return methods;
    }

    public Attributes getAttributes() {
        return attributes;
    }

}
