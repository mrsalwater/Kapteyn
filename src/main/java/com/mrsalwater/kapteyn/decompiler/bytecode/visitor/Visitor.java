package com.mrsalwater.kapteyn.decompiler.bytecode.visitor;

import com.mrsalwater.kapteyn.decompiler.bytecode.ByteCodeWriter;
import com.mrsalwater.kapteyn.decompiler.bytecode.settings.ByteCodeSettings;
import com.mrsalwater.kapteyn.decompiler.classfile.ClassFile;
import com.mrsalwater.kapteyn.decompiler.classfile.constantpool.ConstantPool;
import com.mrsalwater.kapteyn.decompiler.exception.ClassFileException;

import java.util.List;

public abstract class Visitor<T> {

    protected final ByteCodeWriter writer = new ByteCodeWriter();

    protected final ClassFile classFile;
    protected final ByteCodeSettings settings;

    protected final ConstantPool constantPool;

    public Visitor(ClassFile classFile, ByteCodeSettings settings) {
        this.classFile = classFile;
        this.settings = settings;

        constantPool = classFile.getConstantPool();
    }

    public abstract void visit(T t) throws ClassFileException;

    public final List<String> getLines() {
        return writer.getLines();
    }

    public final void reset() {
        writer.reset();
    }

}
