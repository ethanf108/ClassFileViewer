package edu.rit.csh.intraspect.data.instruction.math.shl;

import edu.rit.csh.intraspect.data.instruction.Opcode;
import edu.rit.csh.intraspect.edit.assemble.AssembleInject;

import java.io.DataInputStream;

@Opcode(opcode = 0x79, mnemonic = "lshl")
public final class LShlInstruction extends ShlInstruction<Long> {

    @AssembleInject
    public LShlInstruction() {

    }

    public static LShlInstruction read(final DataInputStream in) {
        return new LShlInstruction();
    }
}
