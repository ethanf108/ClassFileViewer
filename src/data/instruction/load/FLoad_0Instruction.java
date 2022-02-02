package data.instruction.load;

import data.instruction.Opcode;

import java.io.DataInputStream;
import java.io.IOException;

@Opcode(opcode = 0x22, mnemonic = "fload_0")
public final class FLoad_0Instruction extends FLoadInstruction {

    public FLoad_0Instruction() {
        super(0);
    }

    public static FLoad_0Instruction read(DataInputStream in) throws IOException {
        return new FLoad_0Instruction();
    }
}
