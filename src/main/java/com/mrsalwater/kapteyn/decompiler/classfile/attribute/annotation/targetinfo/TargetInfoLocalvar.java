package com.mrsalwater.kapteyn.decompiler.classfile.attribute.annotation.targetinfo;

public final class TargetInfoLocalvar implements TargetInfo {

    private final Table[] tables;

    public TargetInfoLocalvar(Table[] tables) {
        this.tables = tables;
    }

    public Table[] getTables() {
        return tables;
    }

    public static final class Table {

        private final int startPc;
        private final int length;
        private final int index;

        public Table(int startPc, int length, int index) {
            this.startPc = startPc;
            this.length = length;
            this.index = index;
        }

        public int getStartPc() {
            return startPc;
        }

        public int getLength() {
            return length;
        }

        public int getIndex() {
            return index;
        }

    }

}
