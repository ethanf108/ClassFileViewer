package data.instruction;

import data.ClassFile;
import data.instruction.store.*;

public sealed abstract class StoreInstruction extends Instruction permits IStoreInstruction, LStoreInstruction, FStoreInstruction, DStoreInstruction, AStoreInstruction {

    protected final int localVariableIndex;

    protected StoreInstruction(int lvi) {
        this.localVariableIndex = lvi;
    }

    public final int getLocalVariableIndex() {
        return this.localVariableIndex;
    }

    @Override
    public int getNumOperands() {
        return 1;
    }

    @Override
    public int[] getOperands() {
        return new int[]{this.localVariableIndex & 0xFF};
    }

    @Override
    public boolean isValid(ClassFile ref) {
        //TODO implements local variable index checking
        return true;
    }

    public abstract Class<?> getType();

}
