package data.instruction.math;

import data.instruction.MathInstruction;
import data.instruction.math.mul.DMulInstruction;
import data.instruction.math.mul.FMulInstruction;
import data.instruction.math.mul.IMulInstruction;
import data.instruction.math.mul.LMulInstruction;

public sealed abstract class MulInstruction<T extends Number> extends MathInstruction<T> permits DMulInstruction, FMulInstruction, IMulInstruction, LMulInstruction {

    @Override
    @SuppressWarnings("unchecked")
    public final T apply(final T a, final T b) {

        if (a instanceof Integer)
            return (T) (Integer) (a.intValue() * b.intValue());

        if (a instanceof Long)
            return (T) (Long) (a.longValue() * b.longValue());

        if (a instanceof Float)
            return (T) (Float) (a.floatValue() * b.floatValue());

        if (a instanceof Double)
            return (T) (Double) (a.doubleValue() * b.doubleValue());

        throw new IllegalStateException();
    }
}
