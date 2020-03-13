package com.mrsalwater.kapteyn.decompiler.classfile.attribute.stackmapframe;

public abstract class StackMapFrame {

    private final int frameType;

    public StackMapFrame(int frameType) {
        this.frameType = frameType;
    }

    public int getFrameType() {
        return frameType;
    }

}
