package data.attribute;

import data.AttributeDesc;
import data.AttributeName;
import java.io.IOException;
import java.io.DataInputStream;
import java.io.OutputStream;
import static util.Util.*;

@AttributeName("EnclosingMethod")
public class EnclosingMethodAttribute implements AttributeDesc {

    private static final int ATTRIBUTE_LENGTH = 4;

    private final short attributeNameIndex;
    private final short classIndex;
    private final short methodIndex;

    private EnclosingMethodAttribute(final short attributeNameIndex, final short classIndex, final short methodIndex) {
        this.attributeNameIndex = attributeNameIndex;
        this.classIndex = classIndex;
        this.methodIndex = methodIndex;
    }

    public static EnclosingMethodAttribute read(final short ani, final DataInputStream in) throws IOException {
        if (readInt(in) != ATTRIBUTE_LENGTH) {
            throw new IllegalArgumentException("Enclosing Method Attribute length must be " + ATTRIBUTE_LENGTH);
        }
        return new EnclosingMethodAttribute(ani, in.readUnsignedShort(), in.readUnsignedShort());
    }

    public short getClassIndex() {
        return this.classIndex;
    }

    public short getMethodIndex() {
        return this.methodIndex;
    }

    @Override
    public short getAttributeNameIndex() {
        return this.attributeNameIndex;
    }

    @Override
    public void write(final OutputStream out) throws IOException {
        writeShort(out, this.attributeNameIndex);
        writeInt(out, this.getDataLength());
        writeShort(out, this.classIndex);
        writeShort(out, this.methodIndex);
    }

    @Override
    public int getDataLength() {
        return ATTRIBUTE_LENGTH;
    }
}
