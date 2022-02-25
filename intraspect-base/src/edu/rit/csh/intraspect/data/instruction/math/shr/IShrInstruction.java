package edu.rit.csh.intraspect.data.instruction.math.shr;

import edu.rit.csh.intraspect.data.instruction.Opcode;
import edu.rit.csh.intraspect.edit.assemble.AssembleInject;

import java.io.DataInputStream;

@Opcode(opcode = 0x7a, mnemonic = "ishr")
public final class IShrInstruction extends ShrInstruction<Integer> {

    @AssembleInject
    public IShrInstruction() {

    }

    public static IShrInstruction read(final DataInputStream in) {
        return new IShrInstruction();
    }
}
