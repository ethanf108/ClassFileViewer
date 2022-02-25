package edu.rit.csh.intraspect.data.instruction.store;

import edu.rit.csh.intraspect.data.instruction.Opcode;
import edu.rit.csh.intraspect.edit.assemble.AssembleInject;

import java.io.DataInputStream;
import java.io.IOException;

@Opcode(opcode = 0x52, mnemonic = "dastore")
public final class DAStoreInstruction extends ArrayStoreInstruction {

    @AssembleInject
    public DAStoreInstruction() {

    }

    public static DAStoreInstruction read(final DataInputStream in) throws IOException {
        return new DAStoreInstruction();
    }

    @Override
    public Class<?> getType() {
        return double.class;
    }
}
