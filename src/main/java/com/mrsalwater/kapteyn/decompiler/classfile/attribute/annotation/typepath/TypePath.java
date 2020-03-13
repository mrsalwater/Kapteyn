package com.mrsalwater.kapteyn.decompiler.classfile.attribute.annotation.typepath;

public final class TypePath {

    private final Path[] path;

    public TypePath(Path[] path) {
        this.path = path;
    }

    public Path[] getPath() {
        return path;
    }

    public static final class Path {

        private final int typePathKind;
        private final int typeArgumentKind;

        public Path(int typePathKind, int typeArgumentKind) {
            this.typePathKind = typePathKind;
            this.typeArgumentKind = typeArgumentKind;
        }

        public int getTypePathKind() {
            return typePathKind;
        }

        public int getTypeArgumentKind() {
            return typeArgumentKind;
        }

    }

}
