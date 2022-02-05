package data.instruction.branch;

import data.instruction.IfInstruction;
import data.instruction.Opcode;

import java.io.DataInputStream;
import java.io.IOException;

@Opcode(opcode = 0x9D, mnemonic = "ifgt")
public final class IfgtInstruction extends IfInstruction {

    public IfgtInstruction(int branchTarget) {
        super(branchTarget);
    }

    public static IfgtInstruction read(DataInputStream in) throws IOException {
        return new IfgtInstruction(in.readUnsignedShort());
    }

    @Override
    public boolean test(int value) {
        return value > 0;
    }

}
