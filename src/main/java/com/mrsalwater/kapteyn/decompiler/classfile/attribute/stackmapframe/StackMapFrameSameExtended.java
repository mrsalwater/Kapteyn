package com.mrsalwater.kapteyn.decompiler.classfile.attribute.stackmapframe;

public final class StackMapFrameSameExtended extends StackMapFrame {

    private final int offsetDelta;

    public StackMapFrameSameExtended(int frameType, int offsetDelta) {
        super(frameType);
        this.offsetDelta = offsetDelta;
    }

    public int getOffsetDelta() {
        return offsetDelta;
    }

}
