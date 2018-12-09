package lotto.type;

import lotto.AbstractLottery;

public class HotLotto extends AbstractLottery {

    // Match Win Approx. Odds
    // 5 of 5 + Hot Ball JACKPOT* 1 in 29,144,841
    // 4 of 5 + Hot Ball $3,000 1 in 138,785
    // 3 of 5 + Hot Ball $50 1 in 3,385
    // 2 of 5 + Hot Ball $6 1 in 254
    // 1 of 5 + Hot Ball $3 1 in 52
    // Hot Ball ONLY $2 1 in 34
    // 5 of 5 $30,000 1 in 1,619,158
    // 4 of 5 $100 1 in 7,710
    // 3 of 5 $6 1 in 188

    static private final double COST = 1;

    private final long[][] prize = { { 0, 0, 0, 6, 100, 30000 }, { 2, 3, 6, 50, 3000, 5000000 } };

    public HotLotto() {
        // noOfRegBalls, noOfSplBalls, minRegBallVal, maxRegBallVal, minSplBallVal, maxSplBallVal
        super(5, 1, 1, 47, 1, 19);
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
        return "[[0, 0, 0, 6, 100, 30000], [2, 3, 6, 50, 3000, JACKPOT]]";
    }
}
