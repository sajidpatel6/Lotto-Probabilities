package lotto.type;

import lotto.AbstractLottery;

public class Take5 extends AbstractLottery {

    // First - 5 of 5 - 1:575,757 - $57,575.70
    // Second - 4 of 5 - 1:3,386.81 - $508.02
    // Third - 3 of 5 - 1:102.63 - $25.66
    // Fourth - 2 of 5 - 1:9.62 - Quick Pick Free Play
    // Overall Chances of Winning: 1 in 8.77

    static private final double COST = 1;

    private final long[][] prize = { { 0, 0, 1, 25, 508, 57575 } };

    public Take5() {
        // noOfRegBalls, noOfSplBalls, minRegBallVal, maxRegBallVal, minSplBallVal, maxSplBallVal
        super(5, 0, 1, 39, 0, 0);
        drawsPerWeek = 7; // TODO check
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
        return "[[100000, 1000, 20, 5, 1, 0, 0, 0, 1, 5, 20, 1000, 100000]]";
    }
}
