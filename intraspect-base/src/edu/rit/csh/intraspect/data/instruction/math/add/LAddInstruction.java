package edu.rit.csh.intraspect.data.instruction.math.add;

import edu.rit.csh.intraspect.data.instruction.Opcode;
import edu.rit.csh.intraspect.edit.assemble.AssembleInject;

import java.io.DataInputStream;

@Opcode(opcode = 0x61, mnemonic = "ladd")
public final class LAddInstruction extends AddInstruction<Long> {

    @AssembleInject
    public LAddInstruction() {

    }

    public static LAddInstruction read(final DataInputStream in) {
        return new LAddInstruction();
    }
}
