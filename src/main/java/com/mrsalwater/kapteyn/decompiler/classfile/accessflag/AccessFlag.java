package com.mrsalwater.kapteyn.decompiler.classfile.accessflag;

import static com.mrsalwater.kapteyn.decompiler.classfile.accessflag.AccessFlagTarget.*;

public enum AccessFlag {

    PUBLIC("public", 0x0001, CLASS, FIELD, METHOD, INNER_CLASS),
    PRIVATE("private", 0x0002, FIELD, METHOD, INNER_CLASS),
    PROTECTED("protected", 0x0004, FIELD, METHOD, INNER_CLASS),
    STATIC("static", 0x0008, FIELD, METHOD, INNER_CLASS),
    FINAL("final", 0x0010, CLASS, FIELD, METHOD, INNER_CLASS, PARAMETER),
    SUPER("super", 0x0020, CLASS),
    SYNCHRONIZED("synchronized", 0x0020, METHOD),
    VOLATILE("volatile", 0x0040, FIELD),
    BRIDGE("bridge", 0x0040, METHOD),
    TRANSIENT("transient", 0x0080, FIELD),
    VARARGS("varargs", 0x0080, METHOD),
    NATIVE("native", 0x0100, METHOD),
    INTERFACE("interface", 0x0200, CLASS, INNER_CLASS),
    ABSTRACT("abstract", 0x0400, CLASS, METHOD, INNER_CLASS),
    STRICT("strict", 0x0800, METHOD),
    SYNTHETIC("synthetic", 0x1000, CLASS, FIELD, METHOD, INNER_CLASS, PARAMETER),
    ANNOTATION("annotation", 0x2000, CLASS, INNER_CLASS),
    ENUM("enum", 0x4000, CLASS, FIELD, INNER_CLASS),
    MANDATED("mandated", 0x8000, PARAMETER);

    private final String name;
    private final int flag;
    private final AccessFlagTarget[] target;

    AccessFlag(String name, int flag, AccessFlagTarget... target) {
        this.name = name;
        this.flag = flag;
        this.target = target;
    }

    public String getName() {
        return name;
    }

    public int getFlag() {
        return flag;
    }

    public AccessFlagTarget[] getTarget() {
        return target;
    }

    public boolean isTarget(AccessFlagTarget target) {
        for (AccessFlagTarget accessFlagTarget : this.target) {
            if (accessFlagTarget == target) {
                return true;
            }
        }

        return false;
    }

}
