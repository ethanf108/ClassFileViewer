package data.attribute.stackmaptable;

import data.attribute.stackmaptable.verificationtypeinfo.VerificationTypeInfo;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.OutputStream;
import static util.Util.*;

public final class AppendFrame extends StackMapFrame {

    private int offsetDelta;
    private VerificationTypeInfo[] locals;

    public AppendFrame(int tag) {
        super(tag);
    }

    @Override
    StackMapFrame readInternal(final DataInputStream in) throws IOException {

        offsetDelta = in.readUnsignedShort();

        locals = new VerificationTypeInfo[251];

        for (int i = 0; i < locals.length; i++) {
            locals[i] = VerificationTypeInfo.read(in);
        }

        return this;
    }

    @Override
    public void write(OutputStream out) throws IOException {
        out.write(tag);
        writeShort(out, offsetDelta);

        for (VerificationTypeInfo local : locals) {
            local.write(out);
        }

    }

    @Override
    public int getDataLength() {
        int dataLength = 3;

        for (VerificationTypeInfo local : locals) {
            dataLength += local.getDataLength();
        }

        return dataLength;
    }
}
