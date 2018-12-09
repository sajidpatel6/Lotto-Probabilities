package lotto.type;

import lotto.AbstractLottery;

public class AllOrNothing extends AbstractLottery {

    // Match Win Approx. Odds
    // 12 of 12 $100,000 1 in 2,704,156
    // 11 of 12 $1,000 1 in 18,779
    // 10 of 12 $20 1 in 621
    // 9 of 12 $5 1 in 56
    // 8 of 12 $1 1 in 11
    // 4 of 12 $1 1 in 11
    // 3 of 12 $5 1 in 56
    // 2 of 12 $20 1 in 621
    // 1 of 12 $1,000 1 in 18,779
    // 0 of 12 $100,000 1 in 2,704,156

    static private final double COST = 1;

    private final long[][] prize = { { 100000, 1000, 20, 5, 1, 0, 0, 0, 1, 5, 20, 1000, 100000 } };

    public AllOrNothing() {
        // noOfRegBalls, noOfSplBalls, minRegBallVal, maxRegBallVal, minSplBallVal, maxSplBallVal
        super(12, 0, 1, 24, 0, 0);
        drawsPerWeek = 14;
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
