package lotto.type;

import lotto.AbstractLottery;

public class NorthstarCash extends AbstractLottery {

    // Match Win Odds
    // 5 of 5 numbers JACKPOT* 1 in 169,911
    // 4 of 5 numbers $50 1 in 1,307
    // 3 of 5 numbers $5 1 in 52
    // 2 of 5 numbers $1 1 in 7

    static private final double COST = 1;

    private final long[][] prize = { { 0, 0, 1, 5, 50, 25000 } };

    public NorthstarCash() {
        // noOfRegBalls, noOfSplBalls, minRegBallVal, maxRegBallVal, minSplBallVal, maxSplBallVal
        super(5, 0, 1, 31, 0, 0);
        drawsPerWeek = 7;
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
        return "[[0, 0, 1, 5, 50, JACKPOT]]";
    }
}
