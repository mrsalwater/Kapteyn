package com.mrsalwater.kapteyn.decompiler.classfile;

import com.mrsalwater.kapteyn.decompiler.classfile.attribute.*;
import com.mrsalwater.kapteyn.decompiler.classfile.attribute.annotation.Annotation;
import com.mrsalwater.kapteyn.decompiler.classfile.attribute.annotation.ParameterAnnotation;
import com.mrsalwater.kapteyn.decompiler.classfile.attribute.annotation.TypeAnnotation;
import com.mrsalwater.kapteyn.decompiler.classfile.attribute.annotation.targetinfo.*;
import com.mrsalwater.kapteyn.decompiler.classfile.attribute.annotation.typepath.TypePath;
import com.mrsalwater.kapteyn.decompiler.classfile.attribute.elementvalue.ElementValue;
import com.mrsalwater.kapteyn.decompiler.classfile.attribute.elementvalue.ElementValuePair;
import com.mrsalwater.kapteyn.decompiler.classfile.attribute.elementvalue.value.*;
import com.mrsalwater.kapteyn.decompiler.classfile.attribute.stackmapframe.*;
import com.mrsalwater.kapteyn.decompiler.classfile.attribute.stackmapframe.verificationtype.*;
import com.mrsalwater.kapteyn.decompiler.classfile.constantpool.*;
import com.mrsalwater.kapteyn.decompiler.classfile.field.Field;
import com.mrsalwater.kapteyn.decompiler.classfile.method.Method;
import com.mrsalwater.kapteyn.decompiler.exception.ClassFileException;
import com.mrsalwater.kapteyn.decompiler.exception.CorruptClassFileException;
import com.mrsalwater.kapteyn.decompiler.exception.ParseClassFileException;

public final class ClassFileParser {

    private final ClassFileReader classFileReader;

    private ConstantPool constantPool;

    public ClassFileParser(byte[] bytes) {
        classFileReader = new ClassFileReader(bytes);
    }

    public ClassFile parse() throws ClassFileException {
        if (classFileReader.readU4() != 0xCAFEBABE) {
            throw new CorruptClassFileException("Data has not the class file format: Invalid magic item");
        }

        int minorVersion = classFileReader.readU2();
        int majorVersion = classFileReader.readU2();

        if (ClassFileVersion.match(majorVersion) == ClassFileVersion.UNKNOWN) {
            throw new ParseClassFileException("Version of the class file can not be parsed (Unknown version)");
        } else if (ClassFileVersion.match(majorVersion) != ClassFileVersion.JAVA_8) {
            throw new ParseClassFileException("Version of the class file can not be parsed (only Java 8 supported)");
        }

        constantPool = parseConstantPool();

        int accessFlags = classFileReader.readU2();
        int thisClass = classFileReader.readU2();
        int superClass = classFileReader.readU2();

        int[] interfaces = parseInterfaces();
        Field[] fields = parseFields();
        Method[] methods = parseMethods();
        Attribute[] attributes = parseAttributes();

        return new ClassFile(minorVersion, majorVersion, constantPool, accessFlags, thisClass, superClass, interfaces, fields, methods, new Attributes(attributes));
    }

    private ConstantPool parseConstantPool() throws CorruptClassFileException {
        int constantPoolCount = classFileReader.readU2();
        Constant[] constantPool = new Constant[constantPoolCount];

        for (int i = 1; i < constantPoolCount; i++) {
            ConstantPoolTag tag = ConstantPoolTag.match(classFileReader.readU1());

            switch (tag) {
                case CLASS:
                    constantPool[i] = new ConstantClass(classFileReader.readU2());
                    break;
                case FIELD_REFERENCE:
                    constantPool[i] = new ConstantFieldReference(classFileReader.readU2(), classFileReader.readU2());
                    break;
                case METHOD_REFERENCE:
                    constantPool[i] = new ConstantMethodReference(classFileReader.readU2(), classFileReader.readU2());
                    break;
                case INTERFACE_METHOD_REFERENCE:
                    constantPool[i] = new ConstantInterfaceMethodReference(classFileReader.readU2(), classFileReader.readU2());
                    break;
                case STRING:
                    constantPool[i] = new ConstantString(classFileReader.readU2());
                    break;
                case INTEGER:
                    constantPool[i] = new ConstantInteger(classFileReader.readU4());
                    break;
                case FLOAT:
                    constantPool[i] = new ConstantFloat(classFileReader.readU4());
                    break;
                case LONG:
                    constantPool[i++] = new ConstantLong(classFileReader.readU4(), classFileReader.readU4());
                    break;
                case DOUBLE:
                    constantPool[i++] = new ConstantDouble(classFileReader.readU4(), classFileReader.readU4());
                    break;
                case NAME_AND_TYPE:
                    constantPool[i] = new ConstantNameAndType(classFileReader.readU2(), classFileReader.readU2());
                    break;
                case UTF_8:
                    constantPool[i] = new ConstantUTF8(classFileReader.readUTF8());
                    break;
                case METHOD_HANDLE:
                    constantPool[i] = new ConstantMethodHandle(classFileReader.readU1(), classFileReader.readU2());
                    break;
                case METHOD_TYPE:
                    constantPool[i] = new ConstantMethodType(classFileReader.readU2());
                    break;
                case INVOKE_DYNAMIC:
                    constantPool[i] = new ConstantInvokeDynamic(classFileReader.readU2(), classFileReader.readU2());
                    break;
                case UNKNOWN:
                    throw new CorruptClassFileException("Invalid constant pool tag");
            }
        }

        return new ConstantPool(constantPool);
    }

    private int[] parseInterfaces() {
        int interfacesCount = classFileReader.readU2();
        int[] interfaces = new int[interfacesCount];

        for (int i = 0; i < interfacesCount; i++) {
            interfaces[i] = classFileReader.readU2();
        }

        return interfaces;
    }

    private Field[] parseFields() throws CorruptClassFileException {
        int fieldsCount = classFileReader.readU2();
        Field[] fields = new Field[fieldsCount];

        for (int i = 0; i < fieldsCount; i++) {
            int accessFlags = classFileReader.readU2();
            int nameIndex = classFileReader.readU2();
            int descriptorIndex = classFileReader.readU2();
            Attribute[] attributes = parseAttributes();

            fields[i] = new Field(accessFlags, nameIndex, descriptorIndex, new Attributes(attributes));
        }

        return fields;
    }

    private Method[] parseMethods() throws CorruptClassFileException {
        int methodsCount = classFileReader.readU2();
        Method[] methods = new Method[methodsCount];

        for (int i = 0; i < methodsCount; i++) {
            int accessFlags = classFileReader.readU2();
            int nameIndex = classFileReader.readU2();
            int descriptorIndex = classFileReader.readU2();
            Attribute[] attributes = parseAttributes();

            methods[i] = new Method(accessFlags, nameIndex, descriptorIndex, new Attributes(attributes));
        }

        return methods;
    }

    private Attribute[] parseAttributes() throws CorruptClassFileException {
        int attributesCount = classFileReader.readU2();
        Attribute[] attributes = new Attribute[attributesCount];

        for (int i = 0; i < attributesCount; i++) {
            int attributeNameIndex = classFileReader.readU2();
            int attributeLength = classFileReader.readU4();

            String attributeName = constantPool.getUTF8(attributeNameIndex).getValue();
            switch (attributeName) {
                case "ConstantValue":
                    if (attributeLength != 2) throw new CorruptClassFileException("Invalid attribute length");
                    attributes[i] = new AttributeConstantValue(classFileReader.readU2());
                    break;
                case "Code":
                    int maxStack = classFileReader.readU2();
                    int maxLocals = classFileReader.readU2();

                    int codeLength = classFileReader.readU4();
                    int[] code = new int[codeLength];

                    for (int j = 0; j < codeLength; j++) {
                        code[j] = classFileReader.readU1();
                    }

                    int exceptionTableLength = classFileReader.readU2();
                    AttributeCode.CodeException[] exceptionTable = new AttributeCode.CodeException[exceptionTableLength];

                    for (int j = 0; j < exceptionTableLength; j++) {
                        exceptionTable[j] = new AttributeCode.CodeException(classFileReader.readU2(), classFileReader.readU2(), classFileReader.readU2(), classFileReader.readU2());
                    }

                    Attribute[] codeAttributes = parseAttributes();
                    attributes[i] = new AttributeCode(maxStack, maxLocals, code, exceptionTable, new Attributes(codeAttributes));
                    break;
                case "StackMapTable":
                    int numberOfEntries = classFileReader.readU2();
                    StackMapFrame[] entries = new StackMapFrame[numberOfEntries];

                    for (int j = 0; j < numberOfEntries; j++) {
                        int frameType = classFileReader.readU1();

                        if (frameType >= 0 && frameType <= 63) {
                            entries[j] = new StackMapFrameSame(frameType);
                        } else if (frameType >= 64 && frameType <= 127) {
                            entries[j] = new StackMapFrameSameLocals1StackItem(frameType, parseVerificationTypeInfo());
                        } else if (frameType == 247) {
                            entries[j] = new StackMapFrameSameLocals1StackItemExtended(frameType, classFileReader.readU2(), parseVerificationTypeInfo());
                        } else if (frameType >= 248 && frameType <= 250) {
                            entries[j] = new StackMapFrameChop(frameType, classFileReader.readU2());
                        } else if (frameType == 251) {
                            entries[j] = new StackMapFrameSameExtended(frameType, classFileReader.readU2());
                        } else if (frameType >= 252 && frameType <= 254) {
                            int offsetDelta = classFileReader.readU2();
                            int k = frameType - 251;
                            VerificationTypeInfo[] locals = new VerificationTypeInfo[k];

                            for (int l = 0; l < k; l++) {
                                locals[l] = parseVerificationTypeInfo();
                            }

                            entries[j] = new StackMapFrameAppend(frameType, offsetDelta, locals);
                        } else if (frameType == 255) {
                            int offsetDelta = classFileReader.readU2();

                            int numberOfLocals = classFileReader.readU2();
                            VerificationTypeInfo[] locals = new VerificationTypeInfo[numberOfLocals];

                            for (int k = 0; k < numberOfLocals; k++) {
                                locals[k] = parseVerificationTypeInfo();
                            }

                            int numberOfStackItems = classFileReader.readU2();
                            VerificationTypeInfo[] stack = new VerificationTypeInfo[numberOfStackItems];

                            for (int k = 0; k < numberOfStackItems; k++) {
                                stack[k] = parseVerificationTypeInfo();
                            }

                            entries[j] = new StackMapFrameFull(frameType, offsetDelta, locals, stack);
                        }
                    }

                    attributes[i] = new AttributeStackMapTable(entries);
                    break;
                case "Exceptions":
                    int numberOfExceptions = classFileReader.readU2();
                    int[] exceptionIndexTable = new int[numberOfExceptions];

                    for (int j = 0; j < numberOfExceptions; j++) {
                        exceptionIndexTable[j] = classFileReader.readU2();
                    }

                    attributes[i] = new AttributeExceptions(exceptionIndexTable);
                    break;
                case "InnerClasses":
                    int numberOfClasses = classFileReader.readU2();
                    AttributeInnerClasses.Class[] classes = new AttributeInnerClasses.Class[numberOfClasses];

                    for (int j = 0; j < numberOfClasses; j++) {
                        classes[j] = new AttributeInnerClasses.Class(classFileReader.readU2(), classFileReader.readU2(), classFileReader.readU2(), classFileReader.readU2());
                    }

                    attributes[i] = new AttributeInnerClasses(classes);
                    break;
                case "EnclosingMethod":
                    if (attributeLength != 4) throw new CorruptClassFileException("Invalid attribute length");
                    attributes[i] = new AttributeEnclosingMethod(classFileReader.readU2(), classFileReader.readU2());
                    break;
                case "Synthetic":
                    if (attributeLength != 0) throw new CorruptClassFileException("Invalid attribute length");
                    attributes[i] = new AttributeSynthetic();
                    break;
                case "Signature":
                    if (attributeLength != 2) throw new CorruptClassFileException("Invalid attribute length");
                    attributes[i] = new AttributeSignature(classFileReader.readU2());
                    break;
                case "SourceFile":
                    if (attributeLength != 2) throw new CorruptClassFileException("Invalid attribute length");
                    attributes[i] = new AttributeSourceFile(classFileReader.readU2());
                    break;
                case "SourceDebugExtension":
                    int[] debugExtension = new int[attributeLength];

                    for (int j = 0; j < attributeLength; j++) {
                        debugExtension[j] = classFileReader.readU1();
                    }

                    attributes[i] = new AttributeDebugExtension(debugExtension);
                    break;
                case "LineNumberTable":
                    int lineNumberTableLength = classFileReader.readU2();
                    AttributeLineNumberTable.LineNumber[] lineNumberTable = new AttributeLineNumberTable.LineNumber[lineNumberTableLength];

                    for (int j = 0; j < lineNumberTableLength; j++) {
                        lineNumberTable[j] = new AttributeLineNumberTable.LineNumber(classFileReader.readU2(), classFileReader.readU2());
                    }

                    attributes[i] = new AttributeLineNumberTable(lineNumberTable);
                    break;
                case "LocalVariableTable":
                    int localVariableTableLength = classFileReader.readU2();
                    AttributeLocalVariableTable.LocalVariable[] localVariableTable = new AttributeLocalVariableTable.LocalVariable[localVariableTableLength];

                    for (int j = 0; j < localVariableTableLength; j++) {
                        localVariableTable[j] = new AttributeLocalVariableTable.LocalVariable(classFileReader.readU2(), classFileReader.readU2(), classFileReader.readU2(), classFileReader.readU2(), classFileReader.readU2());
                    }

                    attributes[i] = new AttributeLocalVariableTable(localVariableTable);
                    break;
                case "LocalVariableTypeTable":
                    int localVariableTypeTableLength = classFileReader.readU2();
                    AttributeLocalVariableTypeTable.LocalVariableType[] localVariableTypeTable = new AttributeLocalVariableTypeTable.LocalVariableType[localVariableTypeTableLength];

                    for (int j = 0; j < localVariableTypeTableLength; j++) {
                        localVariableTypeTable[j] = new AttributeLocalVariableTypeTable.LocalVariableType(classFileReader.readU2(), classFileReader.readU2(), classFileReader.readU2(), classFileReader.readU2(), classFileReader.readU2());
                    }

                    attributes[i] = new AttributeLocalVariableTypeTable(localVariableTypeTable);
                    break;
                case "Deprecated":
                    if (attributeLength != 0) throw new CorruptClassFileException("Invalid attribute length");
                    attributes[i] = new AttributeDeprecated();
                    break;
                case "RuntimeVisibleAnnotations":
                    attributes[i] = new AttributeRuntimeVisibleAnnotations(parseAnnotations());
                    break;
                case "RuntimeInvisibleAnnotations":
                    attributes[i] = new AttributeRuntimeInvisibleAnnotations(parseAnnotations());
                    break;
                case "RuntimeVisibleParameterAnnotations":
                    attributes[i] = new AttributeRuntimeVisibleParameterAnnotations(parseParameterAnnotations());
                    break;
                case "RuntimeInvisibleParameterAnnotations":
                    attributes[i] = new AttributeRuntimeInvisibleParameterAnnotations(parseParameterAnnotations());
                    break;
                case "RuntimeVisibleTypeAnnotations":
                    attributes[i] = new AttributeRuntimeVisibleTypeAnnotations(parseTypeAnnotations());
                    break;
                case "RuntimeInvisibleTypeAnnotations":
                    attributes[i] = new AttributeRuntimeInvisibleTypeAnnotations(parseTypeAnnotations());
                    break;
                case "AnnotationDefault":
                    attributes[i] = new AttributeAnnotationDefault(parseElementValue());
                    break;
                case "BootstrapMethods":
                    int numBootstrapMethods = classFileReader.readU2();
                    AttributeBootstrapMethods.BootstrapMethod[] bootstrapMethods = new AttributeBootstrapMethods.BootstrapMethod[numBootstrapMethods];

                    for (int j = 0; j < numBootstrapMethods; j++) {
                        int bootstrapMethodRef = classFileReader.readU2();
                        int numBootstrapArguments = classFileReader.readU2();
                        int[] bootstrapArguments = new int[numBootstrapArguments];

                        for (int k = 0; k < numBootstrapArguments; k++) {
                            bootstrapArguments[k] = classFileReader.readU2();
                        }

                        bootstrapMethods[j] = new AttributeBootstrapMethods.BootstrapMethod(bootstrapMethodRef, bootstrapArguments);
                    }

                    attributes[i] = new AttributeBootstrapMethods(bootstrapMethods);
                    break;
                case "MethodParameters":
                    int parametersCount = classFileReader.readU1();
                    AttributeMethodParameters.Parameter[] parameters = new AttributeMethodParameters.Parameter[parametersCount];

                    for (int j = 0; j < parametersCount; j++) {
                        parameters[j] = new AttributeMethodParameters.Parameter(classFileReader.readU2(), classFileReader.readU2());
                    }

                    attributes[i] = new AttributeMethodParameters(parameters);
                    break;
                default:
                    throw new CorruptClassFileException("Invalid attribute name");
            }

        }

        return attributes;
    }

    private Annotation[] parseAnnotations() throws CorruptClassFileException {
        int numAnnotations = classFileReader.readU2();
        Annotation[] annotations = new Annotation[numAnnotations];

        for (int i = 0; i < numAnnotations; i++) {
            annotations[i] = parseAnnotation();
        }

        return annotations;
    }

    private ParameterAnnotation[] parseParameterAnnotations() throws CorruptClassFileException {
        int numParameters = classFileReader.readU1();
        ParameterAnnotation[] parameterAnnotations = new ParameterAnnotation[numParameters];

        for (int i = 0; i < numParameters; i++) {
            parameterAnnotations[i] = parseParameterAnnotation();
        }

        return parameterAnnotations;
    }

    private TypeAnnotation[] parseTypeAnnotations() throws CorruptClassFileException {
        int numAnnotations = classFileReader.readU2();
        TypeAnnotation[] annotations = new TypeAnnotation[numAnnotations];

        for (int i = 0; i < numAnnotations; i++) {
            annotations[i] = parseTypeAnnotation();
        }

        return annotations;
    }

    private Annotation parseAnnotation() throws CorruptClassFileException {
        return new Annotation(classFileReader.readU2(), parseElementValuePairs());
    }

    private ParameterAnnotation parseParameterAnnotation() throws CorruptClassFileException {
        return new ParameterAnnotation(parseAnnotations());
    }

    private TypeAnnotation parseTypeAnnotation() throws CorruptClassFileException {
        int targetType = classFileReader.readU1();

        TargetInfo targetInfo;
        switch (targetType) {
            case 0x00:
            case 0x01:
                targetInfo = new TargetInfoTypeParameter(classFileReader.readU1());
                break;
            case 0x10:
                targetInfo = new TargetInfoSupertype(classFileReader.readU2());
                break;
            case 0x11:
            case 0x12:
                targetInfo = new TargetInfoTypeParameterBound(classFileReader.readU1(), classFileReader.readU1());
                break;
            case 0x13:
            case 0x14:
            case 0x15:
                targetInfo = new TargetInfoEmpty();
                break;
            case 0x16:
                targetInfo = new TargetInfoFormalParameter(classFileReader.readU1());
                break;
            case 0x17:
                targetInfo = new TargetInfoThrows(classFileReader.readU2());
                break;
            case 0x40:
            case 0x41:
                int tableLength = classFileReader.readU2();
                TargetInfoLocalvar.Table[] table = new TargetInfoLocalvar.Table[tableLength];

                for (int i = 0; i < tableLength; i++) {
                    table[i] = new TargetInfoLocalvar.Table(classFileReader.readU2(), classFileReader.readU2(), classFileReader.readU2());
                }

                targetInfo = new TargetInfoLocalvar(table);
                break;
            case 0x42:
                targetInfo = new TargetInfoCatch(classFileReader.readU2());
                break;
            case 0x43:
            case 0x44:
            case 0x45:
            case 0x46:
                targetInfo = new TargetInfoOffset(classFileReader.readU2());
                break;
            case 0x47:
            case 0x48:
            case 0x49:
            case 0x4A:
            case 0x4B:
                targetInfo = new TargetInfoTypeArgument(classFileReader.readU2(), classFileReader.readU1());
                break;
            default:
                throw new CorruptClassFileException("Invalid target type tag");
        }

        int pathLength = classFileReader.readU1();
        TypePath.Path[] path = new TypePath.Path[pathLength];

        for (int i = 0; i < pathLength; i++) {
            path[i] = new TypePath.Path(classFileReader.readU1(), classFileReader.readU1());
        }

        TypePath typePath = new TypePath(path);
        int typeIndex = classFileReader.readU2();
        ElementValuePair[] elementValuePairs = parseElementValuePairs();

        return new TypeAnnotation(targetType, targetInfo, typePath, typeIndex, elementValuePairs);
    }

    private ElementValuePair[] parseElementValuePairs() throws CorruptClassFileException {
        int numElementValuePairs = classFileReader.readU2();
        ElementValuePair[] elementValuePairs = new ElementValuePair[numElementValuePairs];

        for (int i = 0; i < numElementValuePairs; i++) {
            elementValuePairs[i] = new ElementValuePair(classFileReader.readU2(), parseElementValue());
        }

        return elementValuePairs;
    }

    private ElementValue parseElementValue() throws CorruptClassFileException {
        int tag = classFileReader.readU1();

        switch (tag) {
            case 'B':
            case 'C':
            case 'D':
            case 'F':
            case 'I':
            case 'J':
            case 'S':
            case 'Z':
            case 's':
                return new ElementValue(tag, new ValueConstant(tag, classFileReader.readU2()));
            case 'e':
                return new ElementValue(tag, new ValueEnum(classFileReader.readU2(), classFileReader.readU2()));
            case 'c':
                return new ElementValue(tag, new ValueClass(classFileReader.readU2()));
            case '@':
                return new ElementValue(tag, new ValueAnnotation(parseAnnotation()));
            case '[':
                int numValues = classFileReader.readU2();
                ElementValue[] elementValues = new ElementValue[numValues];

                for (int i = 0; i < numValues; i++) {
                    elementValues[i] = parseElementValue();
                }

                return new ElementValue(tag, new ValueArray(elementValues));
            default:
                throw new CorruptClassFileException("Invalid element value tag");
        }
    }

    private VerificationTypeInfo parseVerificationTypeInfo() throws CorruptClassFileException {
        int tag = classFileReader.readU1();

        switch (tag) {
            case 0:
                return new VerificationTypeInfoTopVariable();
            case 1:
                return new VerificationTypeInfoIntegerVariable();
            case 2:
                return new VerificationTypeInfoFloatVariable();
            case 3:
                return new VerificationTypeInfoDoubleVariable();
            case 4:
                return new VerificationTypeInfoLongVariable();
            case 5:
                return new VerificationTypeInfoNullVariable();
            case 6:
                return new VerificationTypeInfoUninitializedThisVariable();
            case 7:
                return new VerificationTypeInfoObjectVariable(classFileReader.readU2());
            case 8:
                return new VerificationTypeInfoUninitializedVariable(classFileReader.readU2());
            default:
                throw new CorruptClassFileException("Invalid verification type info tag");
        }
    }

}
