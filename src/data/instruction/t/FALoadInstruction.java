package data.instruction.t;

import data.instruction.Opcode;
import data.instruction.load.ArrayLoadInstruction;

import java.io.DataInputStream;
import java.io.IOException;

@Opcode(opcode = 0x30, mnemonic = "faload")
public final class FALoadInstruction extends ArrayLoadInstruction {

    public FALoadInstruction() {
    }

    public static FALoadInstruction read(DataInputStream in) throws IOException {
        return new FALoadInstruction();
    }

    @Override
    public Class<?> getType() {
        return float.class;
    }
}
