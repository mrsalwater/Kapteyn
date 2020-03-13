# Kapteyn
A Java library for **reverse engineering** Java class files and jars. It contains a class file parser for **accurate** class file representation and a **modern** and **fast** bytecode decompiler. It is currently under development. 

> Note: Currently only capable of parsing Java 8 files

## Features

* Full support of the [Java 8 classfile format](https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-4.html) and the [Java 8 instruction set](https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-6.html) 
* Fast loading time and accurate representation of the classfile
* Easy to use API

## How to build

```
git clone https://github.com/mrsalwater/Kapteyn.git
cd Kapteyn
./gradlew build
```

## How to use

#### Class File Decompiling

````java
public static String decompile(byte[] bytes) throws ClassFileException {
    ClassFileParser classFileParser = new ClassFileParser(bytes);
    ClassFile classFile = classFileParser.parse();

    ByteCodeParser byteCodeParser = new ByteCodeParser(classFile);
    ByteCodeFile byteCodeFile = byteCodeParser.parse();

    return byteCodeFile.getSource();
}
````

#### Class File Parsing

````java
ClassFileParser classFileParser = new ClassFileParser(bytes);
ClassFile classFile = classFileParser.parse();
````

#### Class File Version

````java
if (ClassFileVersion.match(classFile.getMajorVersion()) == ClassFileVersion.JAVA_8) {
    /* ... */
}
````

#### Constant Pool

````java
ConstantPool constantPool = classFile.getConstantPool();
String thisClassName = constantPool.getUTF8(classFile.getThisClass()).getValue();
String superClassName = constantPool.getUTF8(classFile.getSuperClass()).getValue();
````

#### Access Flags

````java
AccessFlags accessFlags = new AccessFlags(classFile.getAccessFlags());
if (accessFlags.isPublic() && accessFlags.isStatic()) {
    /* ... */
}
````

#### Interfaces

````java
for (int index : classFile.getInterfaces()) {
    String interfaceName = constantPool.getUTF8(constantPool.getClass(index).getNameIndex()).getValue();
    /* ... */
}
````

#### Fields

````java
for (Field field : classFile.getFields()) {
    String fieldName = constantPool.getUTF8(field.getNameIndex()).getValue();
    String fieldDescriptor = constantPool.getUTF8(field.getDescriptorIndex()).getValue();
    /* ... */
}
````

#### Methods

````java
for (Method method : classFile.getMethods()) {
    String methodName = constantPool.getUTF8(method.getNameIndex()).getValue();
    String methodDescriptor = constantPool.getUTF8(method.getDescriptorIndex()).getValue();
    /* ... */
}
````

#### Attributes

````java
Attributes classAttributes = classFile.getAttributes();
if (classAttributes.has(AttributeRuntimeVisibleAnnotations.class)) {
    AttributeRuntimeVisibleAnnotations annotations = classAttributes.get(AttributeRuntimeVisibleAnnotations.class);
    /* ... */
}
````

## References

* [Java documentation](https://docs.oracle.com/javase/specs/jvms/se8/html/index.html)  
    The official defining document of the Java Virtual Machine  
    Authors: Tim Lindholm, Frank Yellin, Gilad Bracha, Alex Buckley (2015-02-13)
* [Fernflower](https://github.com/JetBrains/intellij-community/tree/master/plugins/java-decompiler/engine)  
    "Fernflower is the first actually working analytical decompiler for Java and probably for a high-level programming language in general"  
    Author: JetBrains
* [Procyon](https://github.com/ststeiger/procyon)  
    "Procyon is a suite of Java metaprogramming tools focused on code generation and analysis"  
    Author: Stefan Steiger
* [JD-Core](https://github.com/java-decompiler/jd-core)  
    "JD-Core is a Java decompiler written in Java"  
    Author: Emmanuel Dupuy

---

_This software is licensed under the [MIT License](https://opensource.org/licenses/MIT)_  
_Copyright 2020 mrsalwater_
