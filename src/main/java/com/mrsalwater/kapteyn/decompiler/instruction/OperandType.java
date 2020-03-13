package com.mrsalwater.kapteyn.decompiler.instruction;

public enum OperandType {

    NONE(0),
    LOCAL(1), LOCAL_I(2),
    TYPE_REFERENCE(2), TYPE_REFERENCE_I(3), FIELD_REFERENCE(2), METHOD_REFERENCE(2), INTERFACE_REFERENCE(4), DYNAMIC_CALL_SITE(4),
    PRIMITIVE_TYPE_CODE(1),
    SWITCH(-1),
    INT(1), SHORT(2),
    BRANCH_TARGET(2), BRANCH_TARGET_WIDE(4),
    CONSTANT(1), CONSTANT_WIDE(2);

    private final int length;

    OperandType(int length) {
        this.length = length;
    }

    public int getLength() {
        return length;
    }

}
