package com.mrsalwater.kapteyn.decompiler.bytecode.visitor;

import com.mrsalwater.kapteyn.decompiler.bytecode.settings.ByteCodeSetting;
import com.mrsalwater.kapteyn.decompiler.bytecode.settings.ByteCodeSettings;
import com.mrsalwater.kapteyn.decompiler.bytecode.util.AnnotationUtil;
import com.mrsalwater.kapteyn.decompiler.bytecode.util.AttributeUtil;
import com.mrsalwater.kapteyn.decompiler.classfile.ClassFile;
import com.mrsalwater.kapteyn.decompiler.classfile.accessflag.AccessFlagTarget;
import com.mrsalwater.kapteyn.decompiler.classfile.accessflag.AccessFlags;
import com.mrsalwater.kapteyn.decompiler.classfile.attribute.*;
import com.mrsalwater.kapteyn.decompiler.classfile.attribute.annotation.Annotation;
import com.mrsalwater.kapteyn.decompiler.classfile.attribute.annotation.ParameterAnnotation;
import com.mrsalwater.kapteyn.decompiler.classfile.method.Method;
import com.mrsalwater.kapteyn.decompiler.exception.ClassFileException;
import com.mrsalwater.kapteyn.decompiler.exception.CorruptClassFileException;
import com.mrsalwater.kapteyn.decompiler.util.ClassFileUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class MethodVisitor extends Visitor<Method> {

    public MethodVisitor(ClassFile classFile, ByteCodeSettings settings) {
        super(classFile, settings);
    }

    @Override
    public void visit(Method method) throws ClassFileException {
        AccessFlags accessFlags = new AccessFlags(method.getAccessFlags());
        Attributes attributes = method.getAttributes();

        if (accessFlags.hasFlags()) {
            writer.append("// access flags: ").append(accessFlags.toString(AccessFlagTarget.METHOD)).indent();
        }

        visitAttributes(method);

        if (accessFlags.hasFlags()) {
            if (accessFlags.isPublic()) writer.append("public ");
            else if (accessFlags.isProtected()) writer.append("protected ");
            else if (accessFlags.isPrivate()) writer.append("private ");

            if (accessFlags.isStatic()) writer.append("static ");
            if (accessFlags.isFinal()) writer.append("final ");
            if (accessFlags.isSynchronized()) writer.append("synchronized ");
            if (accessFlags.isNative()) writer.append("native ");
            if (accessFlags.isAbstract()) writer.append("abstract ");
        }

        String name = constantPool.getUTF8(method.getNameIndex()).getValue();
        String descriptor = constantPool.getUTF8(method.getDescriptorIndex()).getValue();
        String returnTypeDescriptor = descriptor.substring(descriptor.indexOf(")") + 1);

        writer.append(ClassFileUtil.getReturnType(returnTypeDescriptor)).append(" ").append(name);
        writer.append("(").append(getParameter(descriptor, attributes)).append(") ");

        if (attributes.has(AttributeExceptions.class)) {
            writer.append(getExceptions(attributes.get(AttributeExceptions.class)));
        }

        if (attributes.has(AttributeCode.class)) {
            AttributeCode attributeCode = attributes.get(AttributeCode.class);

            CodeVisitor codeVisitor = new CodeVisitor(classFile, settings);
            codeVisitor.visit(attributeCode);

            for (String lines : codeVisitor.getLines()) {
                writer.indent().tab().append(lines);
            }
        }

        writer.finish();
    }

    private void visitAttributes(Method method) throws CorruptClassFileException { // TODO: 18.01.2020 Implement "RuntimeVisibleTypeAnnotations" and "RuntimeInvisibleTypeAnnotations"
        Attributes attributes = method.getAttributes();

        if (attributes.has(AttributeSynthetic.class)) {
            writer.append(AttributeUtil.getAttributeSynthetic()).indent();
        }

        if (attributes.has(AttributeDeprecated.class)) {
            writer.append(AttributeUtil.getAttributeDeprecated()).indent();
        }

        if (attributes.has(AttributeSignature.class)) {
            writer.append(AttributeUtil.getAttributeSignature(constantPool, attributes.get(AttributeSignature.class))).indent();
        }

        if (attributes.has(AttributeAnnotationDefault.class)) {
            writer.append(AttributeUtil.getAttributeAnnotationDefault(constantPool, attributes.get(AttributeAnnotationDefault.class))); // TODO: 01.02.2020 Correct implementation?
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

    private String getExceptions(AttributeExceptions attributeExceptions) throws CorruptClassFileException {
        StringBuilder builder = new StringBuilder("throws ");

        int[] exceptionIndexTable = attributeExceptions.getExceptionIndexTable();
        for (int i = 0, length = exceptionIndexTable.length; i < length; i++) {
            int index = exceptionIndexTable[i];
            String className = constantPool.getUTF8(constantPool.getClass(index).getNameIndex()).getValue();

            builder.append(className);
            if (i + 1 != length) builder.append(", ");
        }

        return builder.toString();
    }

    private String getParameter(String descriptor, Attributes attributes) throws CorruptClassFileException {
        return getParameter(getParameters(descriptor, attributes), attributes);
    }

    private String getParameter(List<String> parameters, Attributes attributes) throws CorruptClassFileException {
        StringBuilder builder = new StringBuilder();

        Map<Integer, List<String>> annotationMap = new HashMap<>();
        if (attributes.has(AttributeRuntimeVisibleParameterAnnotations.class)) {
            AttributeRuntimeVisibleParameterAnnotations attributeRuntimeVisibleParameterAnnotations = attributes.get(AttributeRuntimeVisibleParameterAnnotations.class);
            addAnnotations(annotationMap, attributeRuntimeVisibleParameterAnnotations.getParameterAnnotations());
        } else if (attributes.has(AttributeRuntimeInvisibleParameterAnnotations.class)) {
            AttributeRuntimeInvisibleParameterAnnotations attributeRuntimeInvisibleParameterAnnotations = attributes.get(AttributeRuntimeInvisibleParameterAnnotations.class);
            addAnnotations(annotationMap, attributeRuntimeInvisibleParameterAnnotations.getParameterAnnotations());
        }

        for (int i = 0, size = parameters.size(); i < size; i++) {
            if (annotationMap.containsKey(i)) {
                for (String string : annotationMap.get(i)) {
                    builder.append(string).append(" ");
                }
            }

            builder.append(parameters.get(i));
            if (i + 1 != size) builder.append(", ");
        }

        return builder.toString();
    }

    private List<String> getParameters(String descriptor, Attributes attributes) throws CorruptClassFileException {
        List<String> rawParameters = getParameters(descriptor);
        List<String> namedParameters = new ArrayList<>(rawParameters.size());

        if (attributes.has(AttributeMethodParameters.class)) {
            StringBuilder builder = new StringBuilder();
            AttributeMethodParameters attributeMethodParameters = attributes.get(AttributeMethodParameters.class);

            AttributeMethodParameters.Parameter[] parameters = attributeMethodParameters.getParameters();
            for (int i = 0, length = parameters.length; i < length; i++) {
                AttributeMethodParameters.Parameter parameter = parameters[i];

                AccessFlags accessFlags = new AccessFlags(parameter.getAccessFlags());
                if (accessFlags.isFinal()) builder.append("final ");
                if (accessFlags.isSynthetic()) builder.append("*synthetic* ");
                if (accessFlags.isMandated()) builder.append("*mandated*");

                builder.append(rawParameters.get(i)).append(" ").append(constantPool.getUTF8(parameter.getNameIndex()).getValue());

                namedParameters.add(builder.toString());
                builder.setLength(0);
            }
        } else if (attributes.has(AttributeCode.class)) {
            AttributeCode attributeCode = attributes.get(AttributeCode.class);

            if (attributeCode.getAttributes().has(AttributeLocalVariableTable.class)) {
                AttributeLocalVariableTable attributeLocalVariableTable = attributeCode.getAttributes().get(AttributeLocalVariableTable.class);
                AttributeLocalVariableTable.LocalVariable[] localVariableTable = attributeLocalVariableTable.getLocalVariableTable();

                if (localVariableTable != null && localVariableTable.length > rawParameters.size()) {
                    int offset = constantPool.getUTF8(localVariableTable[0].getNameIndex()).getValue().equals("this") ? 1 : 0;

                    for (int i = 0, length = rawParameters.size(); i < length; i++) {
                        AttributeLocalVariableTable.LocalVariable localVariable = localVariableTable[i + offset];
                        String localVariableName = constantPool.getUTF8(localVariable.getNameIndex()).getValue();

                        String namedParameter;
                        if (i + offset == localVariable.getIndex()) {
                            namedParameter = rawParameters.get(i).concat(" ").concat(localVariableName);
                        } else {
                            namedParameter = rawParameters.get(i);
                        }

                        namedParameters.add(namedParameter);
                    }
                } else {
                    namedParameters.addAll(rawParameters);
                }
            }
        } else {
            namedParameters.addAll(rawParameters);
        }

        return namedParameters;
    }

    private void addAnnotations(Map<Integer, List<String>> annotationMap, ParameterAnnotation[] parameterAnnotations) throws CorruptClassFileException {
        for (int i = 0, length = parameterAnnotations.length; i < length; i++) {
            ParameterAnnotation parameterAnnotation = parameterAnnotations[i];

            int annotationsLength = parameterAnnotation.getAnnotations().length;
            if (annotationsLength > 0) {
                for (Annotation annotation : parameterAnnotation.getAnnotations()) {
                    String string = AnnotationUtil.getAnnotation(constantPool, annotation);

                    if (annotationMap.containsKey(i)) {
                        annotationMap.get(i).add(string);
                    } else {
                        List<String> list = new ArrayList<>();
                        list.add(string);
                        annotationMap.put(i, list);
                    }
                }
            }
        }
    }

    private List<String> getParameters(String descriptor) {
        List<String> parameters = new ArrayList<>();
        StringBuilder builder = new StringBuilder();
        boolean array = false, reference = false;

        for (int i = 1, length = descriptor.indexOf(")"); i < length; i++) {
            char c = descriptor.charAt(i);
            if (c == ')') break;

            if (c == '[') {
                array = true;
            } else if (reference) {
                if (c == ';') {
                    if (array) builder.append("[]");
                    parameters.add(builder.toString());

                    builder.setLength(0);
                    array = reference = false;
                } else builder.append(c);
            } else if (c == 'L') {
                reference = true;
            } else {
                builder.append(ClassFileUtil.getPrimitiveType(c));
                if (array) builder.append("[]");

                parameters.add(builder.toString());
                builder.setLength(0);
            }
        }

        return parameters;
    }

}
