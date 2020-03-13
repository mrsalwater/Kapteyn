package com.mrsalwater.kapteyn.decompiler.classfile.attribute.stackmapframe;

import com.mrsalwater.kapteyn.decompiler.classfile.attribute.stackmapframe.verificationtype.VerificationTypeInfo;

public final class StackMapFrameFull extends StackMapFrame {

    private final int offsetDelta;
    private final VerificationTypeInfo[] locals;
    private final VerificationTypeInfo[] stack;

    public StackMapFrameFull(int frameType, int offsetDelta, VerificationTypeInfo[] locals, VerificationTypeInfo[] stack) {
        super(frameType);
        this.offsetDelta = offsetDelta;
        this.locals = locals;
        this.stack = stack;
    }

    public int getOffsetDelta() {
        return offsetDelta;
    }

    public VerificationTypeInfo[] getLocals() {
        return locals;
    }

    public VerificationTypeInfo[] getStack() {
        return stack;
    }

}
