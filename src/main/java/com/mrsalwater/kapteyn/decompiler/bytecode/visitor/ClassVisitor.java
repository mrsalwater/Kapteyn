package com.mrsalwater.kapteyn.decompiler.bytecode.visitor;

import com.mrsalwater.kapteyn.decompiler.bytecode.settings.ByteCodeSetting;
import com.mrsalwater.kapteyn.decompiler.bytecode.settings.ByteCodeSettings;
import com.mrsalwater.kapteyn.decompiler.bytecode.util.AttributeUtil;
import com.mrsalwater.kapteyn.decompiler.bytecode.util.ConstantUtil;
import com.mrsalwater.kapteyn.decompiler.classfile.ClassFile;
import com.mrsalwater.kapteyn.decompiler.classfile.ClassFileVersion;
import com.mrsalwater.kapteyn.decompiler.classfile.accessflag.AccessFlagTarget;
import com.mrsalwater.kapteyn.decompiler.classfile.accessflag.AccessFlags;
import com.mrsalwater.kapteyn.decompiler.classfile.attribute.*;
import com.mrsalwater.kapteyn.decompiler.classfile.constantpool.*;
import com.mrsalwater.kapteyn.decompiler.exception.ClassFileException;
import com.mrsalwater.kapteyn.decompiler.exception.CorruptClassFileException;

import java.util.List;

public final class ClassVisitor extends Visitor<ClassFile> {

    public ClassVisitor(ClassFile classFile, ByteCodeSettings settings) {
        super(classFile, settings);
    }

    @Override
    public void visit(ClassFile classFile) throws ClassFileException {
        AccessFlags accessFlags = new AccessFlags(classFile.getAccessFlags());

        writer.append("// class version: ").append(classFile.getMajorVersion()).append(".").append(classFile.getMinorVersion()).append(" (").append(ClassFileVersion.match(classFile.getMajorVersion()).getName()).append(")").indent();

        if (accessFlags.hasFlags()) {
            writer.append("// access flags: ").append(accessFlags.toString(AccessFlagTarget.CLASS)).indent();
        }

        visitAttributes(classFile);

        if (accessFlags.hasFlags()) {
            if (accessFlags.isPublic()) {
                writer.append("public ");
            }

            if (accessFlags.isInterface()) {
                if (accessFlags.isAnnotation()) writer.append("@");
                writer.append("interface ");
            } else if (accessFlags.isEnum()) {
                writer.append("enum ");
            } else {
                if (accessFlags.isFinal()) writer.append("final ");
                if (accessFlags.isAbstract()) writer.append("abstract ");
                writer.append("class ");
            }
        }

        String thisClass = constantPool.getUTF8(constantPool.getClass(classFile.getThisClass()).getNameIndex()).getValue();
        String superClass = constantPool.getUTF8(constantPool.getClass(classFile.getSuperClass()).getNameIndex()).getValue();

        writer.append(thisClass);
        if (!superClass.equals("java/lang/Object")) {
            writer.append(" extends ").append(superClass);
        }

        int[] interfaces = classFile.getInterfaces();
        if (interfaces.length != 0) {
            writer.append(" implements ");

            for (int i = 0, length = interfaces.length; i < length; i++) {
                writer.append(constantPool.getUTF8(constantPool.getClass(interfaces[i]).getNameIndex()).getValue());
                if (i + 1 != length) writer.append(", ");
            }
        }

        visitInnerAttributes(classFile);

        writer.finish();
    }

    private void visitAttributes(ClassFile classFile) throws CorruptClassFileException { // TODO: 18.01.2020 Implement "RuntimeVisibleTypeAnnotations" and "RuntimeInvisibleTypeAnnotations"
        Attributes attributes = classFile.getAttributes();

        if (attributes.has(AttributeSourceFile.class)) {
            writer.append(AttributeUtil.getAttributeSourceFile(constantPool, attributes.get(AttributeSourceFile.class))).indent();
        }

        if (attributes.has(AttributeDebugExtension.class)) {
            writer.append(AttributeUtil.getAttributeDebugExtension(attributes.get(AttributeDebugExtension.class))).indent();
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


    private void visitInnerAttributes(ClassFile classFile) throws CorruptClassFileException {
        Attributes attributes = classFile.getAttributes();

        if (settings.hasSetting(ByteCodeSetting.SHOW_ENCLOSINGMETHOD)) {
            if (attributes.has(AttributeEnclosingMethod.class)) {
                writer.indent().indent().tab();
                writer.append(AttributeUtil.getAttributeEnclosingMethods(constantPool, attributes.get(AttributeEnclosingMethod.class)));
            }
        }

        if (settings.hasSetting(ByteCodeSetting.SHOW_INNERCLASSES)) {
            if (attributes.has(AttributeInnerClasses.class)) {
                writer.indent();

                AttributeInnerClasses attributeInnerClasses = attributes.get(AttributeInnerClasses.class);
                visitInnerClassesAttribute(attributeInnerClasses);
            }
        }

        if (settings.hasSetting(ByteCodeSetting.SHOW_BOOTSTRAPMETHODS)) {
            if (attributes.has(AttributeBootstrapMethods.class)) {
                writer.indent();

                AttributeBootstrapMethods attributeBootstrapMethods = attributes.get(AttributeBootstrapMethods.class);
                visitBootstrapMethodsAttribute(attributeBootstrapMethods);
            }
        }
    }

    private void visitInnerClassesAttribute(AttributeInnerClasses attributeInnerClasses) throws CorruptClassFileException {
        for (int i = 0, length = attributeInnerClasses.getClasses().length; i < length; i++) {
            AttributeInnerClasses.Class attributeInnerClass = attributeInnerClasses.getClasses()[i];

            writer.indent().tab();

            AccessFlags accessFlags = new AccessFlags(attributeInnerClass.getInnerClassAccessFlags());
            if (accessFlags.hasFlags()) {
                writer.append("// access flags: ").append(accessFlags.toString(AccessFlagTarget.INNER_CLASS)).indent().tab();

                if (accessFlags.isSynthetic()) {
                    writer.append("// marked *synthetic*").indent().tab();
                }

                if (accessFlags.isPublic()) writer.append("public ");
                else if (accessFlags.isPrivate()) writer.append("private ");
                else if (accessFlags.isProtected()) writer.append("protected ");

                if (accessFlags.isFinal()) writer.append("final ");
                if (accessFlags.isStatic()) writer.append("static ");

                if (accessFlags.isInterface()) {
                    if (accessFlags.isAnnotation()) writer.append("@");
                    writer.append("interface ");
                } else if (accessFlags.isAbstract()) {
                    writer.append("abstract ");
                } else if (accessFlags.isEnum()) {
                    writer.append("enum ");
                }
            }

            writer.append("innerclass ");

            String innerClass = constantPool.getUTF8(constantPool.getClass(attributeInnerClass.getInnerClassInfoIndex()).getNameIndex()).getValue();
            writer.append(innerClass).append(" ");

            if (attributeInnerClass.getOuterClassInfoIndex() != 0) {
                String outerClass = constantPool.getUTF8(constantPool.getClass(attributeInnerClass.getOuterClassInfoIndex()).getNameIndex()).getValue();
                writer.append(outerClass).append(" ");

                String innerClassName = constantPool.getUTF8(attributeInnerClass.getInnerNameIndex()).getValue();
                writer.append(innerClassName);
            }

            if (i + 1 != length) {
                writer.indent();
            }
        }
    }

    private void visitBootstrapMethodsAttribute(AttributeBootstrapMethods attributeBootstrapMethods) throws CorruptClassFileException {
        for (int i = 0, length = attributeBootstrapMethods.getBootstrapMethods().length; i < length; i++) {
            AttributeBootstrapMethods.BootstrapMethod bootstrapMethod = attributeBootstrapMethods.getBootstrapMethods()[i];

            writer.indent();

            writer.tab().append("bootstrapmethod [").indent();
            writer.tab().tab().append(ConstantUtil.getConstantMethodHandleString(constantPool, bootstrapMethod.getBootstrapMethodRef())).indent();
            writer.tab().tab().append("arguments").indent();

            for (int bootstrapArgument : bootstrapMethod.getBootstrapArguments()) {
                Constant constant = constantPool.get(bootstrapArgument);

                String string;
                if (constant instanceof ConstantString) {
                    string = "\"" + constantPool.getUTF8(((ConstantString) constant).getStringIndex()).getValue() + "\"";
                } else if (constant instanceof ConstantClass) {
                    string = constantPool.getUTF8(((ConstantClass) constant).getNameIndex()).getValue();
                } else if (constant instanceof ConstantInteger) {
                    string = String.valueOf(((ConstantInteger) constant).getValue());
                } else if (constant instanceof ConstantLong) {
                    string = String.valueOf(((ConstantLong) constant).getValue());
                } else if (constant instanceof ConstantFloat) {
                    string = String.valueOf(((ConstantFloat) constant).getValue());
                } else if (constant instanceof ConstantDouble) {
                    string = String.valueOf(((ConstantDouble) constant).getValue());
                } else if (constant instanceof ConstantMethodHandle) {
                    string = ConstantUtil.getConstantMethodHandleString(constantPool, bootstrapArgument);
                } else if (constant instanceof ConstantMethodType) {
                    string = constantPool.getUTF8(((ConstantMethodType) constant).getDescriptorIndex()).getValue();
                } else {
                    string = "*error*"; // TODO: 20.01.2020 ?
                }

                writer.tab().tab().append(string).indent();
            }

            writer.tab().append("]");

            if (i + 1 != length) {
                writer.indent();
            }
        }
    }

}
