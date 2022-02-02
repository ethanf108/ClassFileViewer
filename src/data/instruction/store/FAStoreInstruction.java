package data.instruction.store;

import data.instruction.Opcode;

import java.io.DataInputStream;
import java.io.IOException;

@Opcode(opcode = 0x51, mnemonic = "fastore")
public final class FAStoreInstruction extends ArrayStoreInstruction {

    public FAStoreInstruction() {
    }

    public static FAStoreInstruction read(DataInputStream in) throws IOException {
        return new FAStoreInstruction();
    }

    @Override
    public Class<?> getType() {
        return float.class;
    }
}
