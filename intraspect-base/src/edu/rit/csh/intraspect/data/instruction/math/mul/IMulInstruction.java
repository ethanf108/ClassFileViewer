package edu.rit.csh.intraspect.data.instruction.math.mul;

import edu.rit.csh.intraspect.data.instruction.Opcode;
import edu.rit.csh.intraspect.edit.assemble.AssembleInject;

import java.io.DataInputStream;

@Opcode(opcode = 0x68, mnemonic = "imul")
public final class IMulInstruction extends MulInstruction<Integer> {

    @AssembleInject
    public IMulInstruction() {

    }

    public static IMulInstruction read(final DataInputStream in) {
        return new IMulInstruction();
    }
}
