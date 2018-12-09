package lotto.type;

import lotto.AbstractLottery;

public class LuckyForLife extends AbstractLottery {

    // Match Win Approx. Odds
    // 5 of 5 + Lucky Ball $7,000/wk for life*+ 1 in 30,821,472
    // 4 of 5 + Lucky Ball $5,000* 1 in 143,356
    // 3 of 5 + Lucky Ball $150 1 in 3,413
    // 2 of 5 + Lucky Ball $25 1 in 250
    // 1 of 5 + Lucky Ball $6 1 in 50
    // Lucky Ball Only $4 1 in 32
    // 5 of 5 $25,000/yr for life*+ 1 in 1,813,028
    // 4 of 5 $200 1 in 8,433
    // 3 of 5 $20 1 in 201
    // 2 of 5 $3 1 in 15

    static private final double COST = 2;

    private final long[][] prize = { { 0, 0, 3, 20, 200, 25000 }, { 4, 6, 25, 150, 5000, 7000000 } };

    public LuckyForLife() {
        // noOfRegBalls, noOfSplBalls, minRegBallVal, maxRegBallVal, minSplBallVal, maxSplBallVal
        super(5, 1, 1, 48, 1, 18);
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
