package data.attribute;

import data.AttributeDesc;
import data.AttributeName;
import data.AttributeReader;
import data.ClassFile;
import java.io.IOException;
import java.io.DataInputStream;
import java.io.OutputStream;
import static util.Util.readInt;
import static util.Util.readShort;
import static util.Util.writeInt;
import static util.Util.writeShort;

@AttributeName("Code")
public class CodeAttribute implements AttributeDesc {

    public static record ExceptionDesc(short startPc, short endPc, short handlerPc, short catchType) {

    }

    private final short attributeNameIndex;
    private final short maxStack;
    private final short maxLocals;
    private final byte[] code;
    private final ExceptionDesc[] exceptionTable;
    private final AttributeDesc[] attributes;

    public CodeAttribute(short attributeNameIndex, short maxStack, short maxLocals, byte[] code, ExceptionDesc[] exceptionTable, AttributeDesc[] attributes) {
        this.attributeNameIndex = attributeNameIndex;
        this.maxStack = maxStack;
        this.maxLocals = maxLocals;
        this.code = code;
        this.exceptionTable = exceptionTable;
        this.attributes = attributes;
    }

    @Override
    public short getAttributeNameIndex() {
        return attributeNameIndex;
    }

    public short getMaxStack() {
        return maxStack;
    }

    public short getMaxLocals() {
        return maxLocals;
    }

    public byte[] getCode() {
        return code;
    }

    public ExceptionDesc[] getExceptionTable() {
        return exceptionTable;
    }

    public AttributeDesc[] getAttributes() {
        return attributes;
    }

    public static CodeAttribute read(short ani, DataInputStream in, ClassFile ref) throws IOException {
        final int length = readInt(in);
        final short maxStack = in.readUnsignedShort();
        final short maxLocals = in.readUnsignedShort();
        final int codeLength = readInt(in);
        final byte[] code = new byte[codeLength];
        in.read(code);
        final short exceptionTableLength = in.readUnsignedShort();
        final ExceptionDesc[] exceptions = new ExceptionDesc[exceptionTableLength];
        for (int i = 0; i < exceptionTableLength; i++) {
            exceptions[i] = new ExceptionDesc(in.readUnsignedShort(), in.readUnsignedShort(), in.readUnsignedShort(), in.readUnsignedShort());
        }
        final short attributesCount = in.readUnsignedShort();
        final AttributeDesc[] attributes = new AttributeDesc[attributesCount];
        for (int i = 0; i < attributesCount; i++) {
            attributes[i] = AttributeReader.read(in, ref);
        }
        return new CodeAttribute(ani, maxStack, maxLocals, code, exceptions, attributes);
    }

    @Override
    public int getDataLength() {
        int attributeLength = 0;
        for (AttributeDesc ad : this.attributes) {
            attributeLength += ad.getDataLength() + 6;
        }
        return 12 + this.code.length + 8 * this.exceptionTable.length + attributeLength;
    }

    @Override
    public void write(OutputStream out) throws IOException {
        writeShort(out, this.attributeNameIndex);
        writeInt(out, this.getDataLength());
        writeShort(out, this.maxStack);
        writeShort(out, this.maxLocals);
        writeInt(out, this.code.length);
        out.write(this.code);
        writeShort(out, (short) this.exceptionTable.length);
        for (ExceptionDesc ed : this.exceptionTable) {
            writeShort(out, ed.startPc);
            writeShort(out, ed.endPc);
            writeShort(out, ed.handlerPc);
            writeShort(out, ed.catchType);
        }
        writeShort(out, (short) this.attributes.length);
        for (AttributeDesc ad : this.attributes) {
            ad.write(out);
        }
    }
}
