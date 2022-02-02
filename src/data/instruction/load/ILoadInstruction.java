package data.instruction.load;

import data.ClassFile;
import data.instruction.LoadInstruction;
import data.instruction.Opcode;

import java.io.DataInputStream;
import java.io.IOException;

@Opcode(opcode = 0x15, mnemonic = "iload")
public sealed class ILoadInstruction extends LoadInstruction permits ILoad_0Instruction, ILoad_1Instruction, ILoad_2Instruction, ILoad_3Instruction {

    public ILoadInstruction(int lvi) {
        super(lvi);
    }

    public static ILoadInstruction read(DataInputStream in) throws IOException {
        return new ILoadInstruction(in.readUnsignedByte());
    }

    @Override
    public boolean isValid(ClassFile ref) {
        //TODO Check if local variable is of int type
        return super.isValid(ref);
    }

    @Override
    public Class<?> getType() {
        return int.class;
    }
}
