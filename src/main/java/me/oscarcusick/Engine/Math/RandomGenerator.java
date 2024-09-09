package me.oscarcusick.Engine.Math;

import java.util.Random;

public class RandomGenerator {

    Random Rand = new Random();

    public int RollAStandardDice() {
        return RollADice(1, 6);
    }

    // rolls a dice a specified AmountOfTimes With specified amount of Faces and sums the results together for each roll
    public int RollADice(int AmountOfTimes, int Faces) {
        int ReturnTotal = 0;
        for (int i = 0; i < AmountOfTimes; i++) {
            ReturnTotal += GetNewInteger(1, Faces);
        }
        return ReturnTotal;
    }

    // Include Negative Numbers Refers To AnyNumber Being Opposite
    public int GetNewInteger(int LowerLimitInclusive, int UpperLimitInclusive, boolean IncludeNegativeNumbers) {
        int RandomNumber = Rand.nextInt();
        if (!IncludeNegativeNumbers) {
            RandomNumber = Math.abs(RandomNumber);
        }

        return ((RandomNumber % (UpperLimitInclusive - LowerLimitInclusive + 1)) + (LowerLimitInclusive));
    }
    // Get a standard integer from LowerLimit(Inclusive) to UpperLimit(Inclusive)
    public int GetNewInteger(int LowerLimitInclusive, int UpperLimitInclusive) {
        return GetNewInteger(LowerLimitInclusive, UpperLimitInclusive, false);
    }


}
