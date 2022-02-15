package edu.rit.csh.intraspect.data.constant;

import edu.rit.csh.intraspect.data.ClassFile;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

/**
 * A ConstantDescriptor for Integer constants.
 */
public final class IntegerConstant implements ConstantDesc {

    private final int value;

    private IntegerConstant(final int val) {
        this.value = val;
    }

    public static IntegerConstant read(final DataInputStream in) throws IOException {
        return new IntegerConstant(in.readInt());
    }

    @Override
    public int getTag() {
        return 3;
    }

    public int getValue() {
        return this.value;
    }

    @Override
    public boolean isValid(ClassFile ref) {
        return true;
    }

    @Override
    public boolean isWide() {
        return false;
    }

    @Override
    public void write(final DataOutputStream out) throws IOException {
        out.writeByte(this.getTag());
        out.writeInt(this.value);
    }
}
