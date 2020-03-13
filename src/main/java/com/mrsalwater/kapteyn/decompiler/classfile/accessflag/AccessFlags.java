package com.mrsalwater.kapteyn.decompiler.classfile.accessflag;

import java.util.ArrayList;
import java.util.List;

public final class AccessFlags {

    private final int flags;

    public AccessFlags(int flags) {
        this.flags = flags;
    }

    public boolean hasFlags() {
        return flags != 0;
    }

    public List<AccessFlag> getFlags(AccessFlagTarget target) {
        List<AccessFlag> accessFlags = new ArrayList<>();

        for (AccessFlag accessFlag : AccessFlag.values()) {
            if (accessFlag.isTarget(target) && isSet(accessFlag)) {
                accessFlags.add(accessFlag);
            }
        }

        return accessFlags;
    }

    public boolean isPublic() {
        return isSet(AccessFlag.PUBLIC);
    }

    public boolean isPrivate() {
        return isSet(AccessFlag.PRIVATE);
    }

    public boolean isProtected() {
        return isSet(AccessFlag.PROTECTED);
    }

    public boolean isStatic() {
        return isSet(AccessFlag.STATIC);
    }

    public boolean isFinal() {
        return isSet(AccessFlag.FINAL);
    }

    public boolean isSuper() {
        return isSet(AccessFlag.SUPER);
    }

    public boolean isSynchronized() {
        return isSet(AccessFlag.SYNCHRONIZED);
    }

    public boolean isVolatile() {
        return isSet(AccessFlag.VOLATILE);
    }

    public boolean isBridge() {
        return isSet(AccessFlag.BRIDGE);
    }

    public boolean isTransient() {
        return isSet(AccessFlag.TRANSIENT);
    }

    public boolean isVarargs() {
        return isSet(AccessFlag.VARARGS);
    }

    public boolean isNative() {
        return isSet(AccessFlag.NATIVE);
    }

    public boolean isInterface() {
        return isSet(AccessFlag.INTERFACE);
    }

    public boolean isAbstract() {
        return isSet(AccessFlag.ABSTRACT);
    }

    public boolean isStrict() {
        return isSet(AccessFlag.STRICT);
    }

    public boolean isSynthetic() {
        return isSet(AccessFlag.SYNTHETIC);
    }

    public boolean isAnnotation() {
        return isSet(AccessFlag.ANNOTATION);
    }

    public boolean isEnum() {
        return isSet(AccessFlag.ENUM);
    }

    public boolean isMandated() {
        return isSet(AccessFlag.MANDATED);
    }

    private boolean isSet(AccessFlag accessFlag) {
        return (flags & accessFlag.getFlag()) != 0;
    }

    public String toString(AccessFlagTarget target) {
        List<AccessFlag> flags = getFlags(target);
        StringBuilder builder = new StringBuilder();

        for (int i = 0, size = flags.size(); i < size; i++) {
            builder.append(flags.get(i).getName());
            if (i + 1 != size) builder.append(", ");
        }

        return builder.toString();
    }

    @Override
    public String toString() {
        return Integer.toHexString(flags);
    }

}
