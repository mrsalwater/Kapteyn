package com.mrsalwater.kapteyn.decompiler.bytecode;

import com.mrsalwater.kapteyn.decompiler.classfile.ClassFile;
import com.mrsalwater.kapteyn.decompiler.exception.ClassFileException;
import com.mrsalwater.kapteyn.decompiler.bytecode.settings.ByteCodeSettings;
import com.mrsalwater.kapteyn.decompiler.bytecode.visitor.ClassVisitor;
import com.mrsalwater.kapteyn.decompiler.bytecode.visitor.FieldVisitor;
import com.mrsalwater.kapteyn.decompiler.bytecode.visitor.MethodVisitor;
import com.mrsalwater.kapteyn.decompiler.classfile.field.Field;
import com.mrsalwater.kapteyn.decompiler.classfile.method.Method;

public final class ByteCodeParser {

    private final ClassFile classFile;
    private final ByteCodeSettings settings;

    public ByteCodeParser(ClassFile classFile) {
        this(classFile, new ByteCodeSettings());
    }

    public ByteCodeParser(ClassFile classFile, ByteCodeSettings settings) {
        this.classFile = classFile;
        this.settings = settings;
    }

    public ByteCodeSettings getSettings() {
        return settings;
    }

    public ByteCodeFile parse() throws ClassFileException {
        ByteCodeWriter writer = new ByteCodeWriter();

        ClassVisitor classVisitor = new ClassVisitor(classFile, settings);
        classVisitor.visit(classFile);

        for (String line : classVisitor.getLines()) {
            writer.append(line).indent();
        }

        FieldVisitor fieldVisitor = new FieldVisitor(classFile, settings);
        for (Field field : classFile.getFields()) {
            fieldVisitor.visit(field);

            writer.indent();
            for (String line : fieldVisitor.getLines()) {
                writer.tab().append(line).indent();
            }

            fieldVisitor.reset();
        }

        MethodVisitor methodVisitor = new MethodVisitor(classFile, settings);
        for (Method method : classFile.getMethods()) {
            methodVisitor.visit(method);

            writer.indent();
            for (String line : methodVisitor.getLines()) {
                writer.tab().append(line).indent();
            }

            methodVisitor.reset();
        }

        return new ByteCodeFile(writer.getSource());
    }

}
