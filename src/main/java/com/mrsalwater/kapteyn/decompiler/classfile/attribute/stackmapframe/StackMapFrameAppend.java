package com.mrsalwater.kapteyn.decompiler.classfile.attribute.stackmapframe;

import com.mrsalwater.kapteyn.decompiler.classfile.attribute.stackmapframe.verificationtype.VerificationTypeInfo;

public final class StackMapFrameAppend extends StackMapFrame {

    private final int offsetDelta;
    private final VerificationTypeInfo[] locals;

    public StackMapFrameAppend(int frameType, int offsetDelta, VerificationTypeInfo[] locals) {
        super(frameType);
        this.offsetDelta = offsetDelta;
        this.locals = locals;
    }

    public int getOffsetDelta() {
        return offsetDelta;
    }

    public VerificationTypeInfo[] getLocals() {
        return locals;
    }

}
