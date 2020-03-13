package com.mrsalwater.kapteyn.decompiler.bytecode.visitor;

import com.mrsalwater.kapteyn.decompiler.bytecode.settings.ByteCodeSetting;
import com.mrsalwater.kapteyn.decompiler.bytecode.settings.ByteCodeSettings;
import com.mrsalwater.kapteyn.decompiler.bytecode.util.AttributeUtil;
import com.mrsalwater.kapteyn.decompiler.classfile.ClassFile;
import com.mrsalwater.kapteyn.decompiler.classfile.accessflag.AccessFlagTarget;
import com.mrsalwater.kapteyn.decompiler.classfile.accessflag.AccessFlags;
import com.mrsalwater.kapteyn.decompiler.classfile.attribute.*;
import com.mrsalwater.kapteyn.decompiler.classfile.field.Field;
import com.mrsalwater.kapteyn.decompiler.exception.ClassFileException;
import com.mrsalwater.kapteyn.decompiler.exception.CorruptClassFileException;
import com.mrsalwater.kapteyn.decompiler.util.ClassFileUtil;

import java.util.List;

public final class FieldVisitor extends Visitor<Field> {

    public FieldVisitor(ClassFile classFile, ByteCodeSettings settings) {
        super(classFile, settings);
    }

    @Override
    public void visit(Field field) throws ClassFileException {
        AccessFlags accessFlags = new AccessFlags(field.getAccessFlags());

        if (accessFlags.hasFlags()) {
            writer.append("// access flags: ").append(accessFlags.toString(AccessFlagTarget.FIELD)).indent();
        }

        visitAttributes(field);

        if (accessFlags.hasFlags()) {
            if (accessFlags.isPublic()) writer.append("public ");
            else if (accessFlags.isProtected()) writer.append("protected ");
            else if (accessFlags.isPrivate()) writer.append("private ");

            if (accessFlags.isStatic()) writer.append("static ");
            if (accessFlags.isFinal()) writer.append("final ");
            if (accessFlags.isVolatile()) writer.append("volatile ");
            if (accessFlags.isTransient()) writer.append("transient ");
        }

        String name = constantPool.getUTF8(field.getNameIndex()).getValue();
        String descriptor = constantPool.getUTF8(field.getDescriptorIndex()).getValue();

        writer.append(ClassFileUtil.getFieldType(descriptor)).append(" ").append(name);
        writer.finish();
    }

    private void visitAttributes(Field field) throws CorruptClassFileException { // TODO: 18.01.2020 Implement "RuntimeVisibleTypeAnnotations" and "RuntimeInvisibleTypeAnnotations"
        Attributes attributes = field.getAttributes();

        if (attributes.has(AttributeConstantValue.class)) {
            writer.append(AttributeUtil.getAttributeConstantValue(constantPool, field, attributes.get(AttributeConstantValue.class))).indent();
        }

        if (attributes.has(AttributeSynthetic.class)) {
            writer.append(AttributeUtil.getAttributeSynthetic()).indent();
        }

        if (attributes.has(AttributeDeprecated.class)) {
            writer.append(AttributeUtil.getAttributeDeprecated()).indent();
        }

        if (attributes.has(AttributeSignature.class)) {
            writer.append(AttributeUtil.getAttributeSignature(constantPool, attributes.get(AttributeSignature.class))).indent();
        }

        if (attributes.has(AttributeRuntimeVisibleAnnotations.class)) {
            List<String> annotations = AttributeUtil.getAttributeRuntimeVisibleAnnotations(constantPool, attributes.get(AttributeRuntimeVisibleAnnotations.class));

            for (String annotation : annotations) {
                writer.append(annotation).indent();
            }
        }

        if (settings.hasSetting(ByteCodeSetting.SHOW_INVISIBLEANNOTATIONS)) {
            if (attributes.has(AttributeRuntimeInvisibleAnnotations.class)) {
                List<String> annotations = AttributeUtil.getAttributeRuntimeInvisibleAnnotations(constantPool, attributes.get(AttributeRuntimeInvisibleAnnotations.class));

                for (String annotation : annotations) {
                    writer.append(annotation).indent();
                }
            }
        }
    }

}
