package com.mrsalwater.kapteyn.decompiler.classfile.constantpool;

public enum ConstantPoolTag {

    CLASS("CONSTANT_Class", 7),
    FIELD_REFERENCE("CONSTANT_Fieldref", 9),
    METHOD_REFERENCE("CONSTANT_Methodref", 10),
    INTERFACE_METHOD_REFERENCE("CONSTANT_InterfaceMethodref", 11),
    STRING("CONSTANT_String", 8),
    INTEGER("CONSTANT_Integer", 3),
    FLOAT("CONSTANT_Float", 4),
    LONG("CONSTANT_Long", 5),
    DOUBLE("CONSTANT_Double", 6),
    NAME_AND_TYPE("CONSTANT_NameAndType", 12),
    UTF_8("CONSTANT_Utf8", 1),
    METHOD_HANDLE("CONSTANT_MethodHandle", 15),
    METHOD_TYPE("CONSTANT_MethodType", 16),
    INVOKE_DYNAMIC("CONSTANT_InvokeDynamic", 18),
    UNKNOWN("Unknown", -1);

    private final String constantType;
    private final int tag;

    ConstantPoolTag(String constantType, int value) {
        this.constantType = constantType;
        this.tag = value;
    }

    public String getConstantType() {
        return constantType;
    }

    public int getTag() {
        return tag;
    }

    public static ConstantPoolTag match(int tag) {
        for (ConstantPoolTag constantPoolTag : values()) {
            if (constantPoolTag.tag == tag) {
                return constantPoolTag;
            }
        }

        return UNKNOWN;
    }

}
