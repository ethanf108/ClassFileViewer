package edu.rit.csh.intraspect.data.instruction.math.neg;

import edu.rit.csh.intraspect.data.instruction.Opcode;
import edu.rit.csh.intraspect.edit.assemble.AssembleInject;

import java.io.DataInputStream;
import java.io.IOException;

@Opcode(opcode = 0x77, mnemonic = "dneg")
public final class DNegInstruction extends NegInstruction<Double> {

    @AssembleInject
    public DNegInstruction() {
    }

    public static DNegInstruction read(final DataInputStream in) throws IOException {
        return new DNegInstruction();
    }

}
