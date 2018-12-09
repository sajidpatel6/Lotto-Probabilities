package lotto.type;

import lotto.AbstractLottery;

public class Gopher5 extends AbstractLottery {

    // Match Win Approx. Odds
    // 5 of 5 JACKPOT* 1 in 1,533,939
    // 4 of 5 $500 1 in 7,304
    // 3 of 5 $15 1 in 178
    // 2 of 5 $1 1 in 13

    static private final double COST = 1;

    private final long[][] prize = { { 0, 0, 1, 15, 500, 100000 } };

    public Gopher5() {
        // noOfRegBalls, noOfSplBalls, minRegBallVal, maxRegBallVal, minSplBallVal, maxSplBallVal
        super(5, 0, 1, 47, 0, 0);
        drawsPerWeek = 3;
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
        return "[[0, 0, 1, 15, 500, 100000]]";
    }
}
