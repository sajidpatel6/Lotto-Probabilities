package lotto.type;

import lotto.AbstractLottery;

public class MegaMillions extends AbstractLottery {

    // Match Win Approx. Odds
    // 5 of 5 + Mega Ball JACKPOT* 1 in 258,890,850
    // 4 of 5 + Mega Ball $5,000 1 in 739,688
    // 3 of 5 + Mega Ball $50 1 in 10,720
    // 2 of 5 + Mega Ball $5 1 in 473
    // 1 of 5 + Mega Ball $2 1 in 56
    // Mega Ball ONLY $1 1 in 21
    // 5 of 5 $1,000,000 1 in 18,492,204
    // 4 of 5 $500 1 in 52,835
    // 3 of 5 $5 1 in 766

    static private final double COST = 1;

    private final long[][] prize = { { 0, 0, 0, 5, 500, 1000000 }, { 1, 2, 5, 50, 5000, 50000000 } };

    public MegaMillions() {
        // noOfRegBalls, noOfSplBalls, minRegBallVal, maxRegBallVal, minSplBallVal, maxSplBallVal
        super(5, 1, 1, 75, 1, 15);
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
        return "[[0, 0, 0, 5, 500, 1000000], [1, 2, 5, 50, 5000, JACKPOT]]";
    }
}
