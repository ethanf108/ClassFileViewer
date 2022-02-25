package edu.rit.csh.intraspect.data.instruction.store;

import edu.rit.csh.intraspect.data.instruction.Opcode;
import edu.rit.csh.intraspect.edit.assemble.AssembleInject;

import java.io.DataInputStream;
import java.io.IOException;

@Opcode(opcode = 0x50, mnemonic = "lastore")
public final class LAStoreInstruction extends ArrayStoreInstruction {

    @AssembleInject
    public LAStoreInstruction() {
    }

    public static LAStoreInstruction read(final DataInputStream in) throws IOException {
        return new LAStoreInstruction();
    }

    @Override
    public Class<?> getType() {
        return long.class;
    }
}
