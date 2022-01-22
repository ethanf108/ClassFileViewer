package data.attribute;

import data.AttributeDesc;
import data.AttributeName;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

@AttributeName("Module")
public class ModuleAttribute implements AttributeDesc {

    private final int attributeNameIndex;

    private final int moduleNameIndex;
    private final int moduleFlags;
    private final int moduleVersionIndex;
    private final RequiresEntry[] requires;
    private final ExportsEntry[] exports;
    private final OpensEntry[] opens;
    private final int[] usesIndex;
    private final ProvidesEntry[] provides;

    public ModuleAttribute(final int attributeNameIndex,
            final int moduleNameIndex,
            final int moduleFlags,
            final int moduleVersionIndex,
            final RequiresEntry[] requires,
            final ExportsEntry[] exports,
            final OpensEntry[] opens,
            final int[] usesIndex,
            final ProvidesEntry[] provides) {
        this.attributeNameIndex = attributeNameIndex;
        this.moduleNameIndex = moduleNameIndex;
        this.moduleFlags = moduleFlags;
        this.moduleVersionIndex = moduleVersionIndex;
        this.requires = requires;
        this.exports = exports;
        this.opens = opens;
        this.usesIndex = usesIndex;
        this.provides = provides;
    }

    @Override
    public int getAttributeNameIndex() {
        return this.attributeNameIndex;
    }

    @Override
    public int getDataLength() {
        return 14 + requires.length * 6 + exports.length * 8 + opens.length * 8 + provides.length * 6 + usesIndex.length * 2;
    }

    @Override
    public void write(final DataOutputStream out) throws IOException {
        out.writeShort(this.attributeNameIndex);
        out.writeInt(this.getDataLength());

        out.writeShort(moduleNameIndex);

        out.writeShort(moduleFlags);

        out.writeShort(moduleVersionIndex);

        out.writeShort(requires.length);

        // Requires write
        for (RequiresEntry entry : requires) {
            entry.write(out);
        }

        out.writeShort(exports.length);

        // Exports write
        for (ExportsEntry entry : exports) {
            entry.write(out);
        }

        // Opens write
        out.writeShort(opens.length);
        for (OpensEntry entry : opens) {
            entry.write(out);
        }

        out.writeShort(usesIndex.length);
        for (int index : usesIndex) {
            out.writeShort(index);
        }

        // Provides write
        out.writeShort(provides.length);
        for (ProvidesEntry entry : provides) {
            entry.write(out);
        }
    }

    public static ModuleAttribute read(final int ani, final DataInputStream in) throws IOException {
        in.readInt();   // Ignore

        final int moduleNameIndex = in.readUnsignedShort();

        final int moduleFlags = in.readUnsignedShort();

        final int moduleVersionIndex = in.readUnsignedShort();

        // Requires Entry
        final int requiresCount = in.readUnsignedShort();
        final RequiresEntry[] requires = new RequiresEntry[requiresCount];
        for (int i = 0; i < requires.length; i++) {
            requires[i] = RequiresEntry.read(in);
        }

        // Exports Entry
        final int exportsCount = in.readUnsignedShort();
        final ExportsEntry[] exports = new ExportsEntry[exportsCount];
        for (int i = 0; i < exports.length; i++) {
            exports[i] = ExportsEntry.read(in);
        }

        // Opens Entry
        final int opensCount = in.readUnsignedShort();
        final OpensEntry[] opens = new OpensEntry[opensCount];
        for (int i = 0; i < opens.length; i++) {
            opens[i] = OpensEntry.read(in);
        }

        final int usesCount = in.readUnsignedShort();
        final int[] usesIndex = new int[usesCount];
        for (int i = 0; i < usesIndex.length; i++) {
            usesIndex[i] = in.readUnsignedShort();
        }

        // Provides Entry
        final int providesCount = in.readUnsignedShort();
        final ProvidesEntry[] provides = new ProvidesEntry[providesCount];
        for (int i = 0; i < provides.length; i++) {
            provides[i] = ProvidesEntry.read(in);
        }

        return new ModuleAttribute(ani, moduleNameIndex, moduleFlags, moduleVersionIndex, requires, exports, opens, usesIndex, provides);
    }

    private static record RequiresEntry(int requiresIndex, int requiresFlags, int requiresVersionIndex) {

        public static RequiresEntry read(final DataInputStream in) throws IOException {
            return new RequiresEntry(in.readUnsignedShort(), in.readUnsignedShort(), in.readUnsignedShort());
        }

        public void write(DataOutputStream out) throws IOException {
            out.writeShort(this.requiresIndex);
            out.writeShort(this.requiresFlags);
            out.writeShort(this.requiresVersionIndex);
        }



    }

    private static record ExportsEntry(int exportsIndex, int exportsFlags, int[] exportsToIndex) {

        public static ExportsEntry read(final DataInputStream in) throws IOException {
            final int exportsIndex = in.readUnsignedShort();
            final int exportsFlags = in.readUnsignedShort();
            final int exportsToCount = in.readUnsignedShort();

            final int[] exportsToIndex = new int[exportsToCount];
            for (int j = 0; j < exportsToIndex.length; j++) {
                exportsToIndex[j] = in.readUnsignedShort();
            }
            return new ExportsEntry(exportsIndex, exportsFlags, exportsToIndex);
        }

        public void write(DataOutputStream out) throws IOException {
            out.writeShort(this.exportsIndex);
            out.writeShort(this.exportsFlags);
            out.writeShort(this.exportsToIndex.length);

            for (final int export : this.exportsToIndex) {
                out.writeShort(export);
            }
        }

    }

    private static record OpensEntry(int opensIndex, int opensFlags, int[] opensToIndex) {

        public static OpensEntry read(final DataInputStream in) throws IOException {
            final int opensIndex = in.readUnsignedShort();
            final int opensFlags = in.readUnsignedShort();
            final int opensToCount = in.readUnsignedShort();

            final int[] opensToIndex = new int[opensToCount];
            for (int j = 0; j < opensToIndex.length; j++) {
                opensToIndex[j] = in.readUnsignedShort();
            }
            return new OpensEntry(opensIndex, opensFlags, opensToIndex);
        }

        public void write(DataOutputStream out) throws IOException {
            out.writeShort(this.opensIndex);
            out.writeShort(this.opensFlags);
            out.writeShort(this.opensToIndex.length);

            for (final int export : this.opensToIndex) {
                out.writeShort(export);
            }
        }

    }

    private static record ProvidesEntry(int providesIndex, int[] providesWithIndex) {


        public static ProvidesEntry read(final DataInputStream in) throws IOException {
            final int providesIndex = in.readUnsignedShort();
            final int providesWithCount = in.readUnsignedShort();

            final int[] providesWithIndex = new int[providesWithCount];
            for (int j = 0; j < providesWithIndex.length; j++) {
                providesWithIndex[j] = in.readUnsignedShort();
            }

            return new ProvidesEntry(providesIndex, providesWithIndex);
        }

        public void write(DataOutputStream out) throws IOException {
            out.writeShort(this.providesIndex);
            out.writeShort(this.providesWithIndex.length);

            for (int provides : this.providesWithIndex) {
                out.writeShort(provides);
            }
        }

    }
}
