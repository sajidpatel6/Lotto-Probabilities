package lotto.type;

import lotto.AbstractLottery;

public class Powerball extends AbstractLottery {

    // Match Win Approx. Odds
    // 5 of 5 + Powerball JACKPOT* 1 in 292,201,338
    // 4 of 5 + Powerball $50,000 1 in 913,129
    // 3 of 5 + Powerball $100 1 in 14,494
    // 2 of 5 + Powerball $7 1 in 701
    // 1 of 5 + Powerball $4 1 in 92
    // Powerball ONLY $4 1 in 38
    // 5 of 5 numbers $1,000,000 1 in 11,688,054
    // 4 of 5 numbers $100 1 in 36,525
    // 3 of 5 numbers $7 1 in 580

    static private final double COST = 2;

    private final long[][] prize = { { 0, 0, 0, 7, 100, 1000000 }, { 4, 4, 7, 100, 50000, 50000000 } };

    public Powerball() {
        // noOfRegBalls, noOfSplBalls, minRegBallVal, maxRegBallVal, minSplBallVal, maxSplBallVal
        super(5, 1, 1, 69, 1, 26);
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
        return "[[0, 0, 0, 7, 100, 1000000], [4, 4, 7, 100, 50000, JACKPOT]]";
    }
}
