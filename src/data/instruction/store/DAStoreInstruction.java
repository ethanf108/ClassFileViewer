package data.instruction.store;

import data.instruction.Opcode;

import java.io.DataInputStream;
import java.io.IOException;

@Opcode(opcode = 0x52, mnemonic = "dastore")
public final class DAStoreInstruction extends ArrayStoreInstruction {

    public DAStoreInstruction() {

    }

    public static DAStoreInstruction read(DataInputStream in) throws IOException {
        return new DAStoreInstruction();
    }

    @Override
    public Class<?> getType() {
        return double.class;
    }
}
