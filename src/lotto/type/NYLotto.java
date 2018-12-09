package lotto.type;

import lotto.AbstractLottery;

public class NYLotto extends AbstractLottery {

    // NUMBERS MATCHED
    // PRIZE LEVEL
    // CHANCES OF WINNING
    // On a $1 Play

    // Six
    // 1: 45,057,474.00
    // 1: 22,528,737.00

    // Five + bonus
    // 1: 7,509,579.00
    // 1: 3,754,789.50

    // Five
    // 1: 144,414.98
    // 1: 72,207.49

    // Four
    // 1: 2,179.85
    // 1: 1,089.92

    // Three
    // 1: 96.17
    // 1: 48.08

    // First Prize 75.00%
    // Second Prize 7.25%
    // Third Prize 5.50%
    // Fourth Prize 6.25%
    // Fifth Prize 6.00%

    // Minimum jackpot is $2 million
    // Calculations below are based on 2.625 million (75%)

    static private final double COST = 0.5;

    private final long[][] prize = { { 0, 0, 0, 210000, 218750, 227500, 2625000 },
            { 0, 0, 0, 210000, 218750, 253750, 2625000 } };

    public NYLotto() {
        // noOfRegNosArg, noOfSpecialNosArg, countOfRegBallsArg, countOfSplBallsArg
        super(6, 1, 59, 53);
        drawsPerWeek = 2;
    }

    @Override
    public double getCost() {
        return COST;
    }

    @Override
    public long getPrize(final int splMatchCount, final int regMatchCount) {
        return prize[splMatchCount][regMatchCount];
    }

    @Override
    protected long[][] getPrizes() {
        return prize;
    }

    @Override
    protected String getPrizesHeader() {
        return "[[0, 0, 3, 20, 200, 25000], [4, 6, 25, 150, 5000, JACKPOT]]";
    }
}
