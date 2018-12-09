package lotto.type;

import lotto.AbstractLottery;

public class Cash4Life extends AbstractLottery {

    // 5 + Cash Ball
    // 1 in: 21,846,048
    // $1,000 a day for Life

    // 5
    // 1 in: 7,282,016
    // $1,000 a week for Life

    // 4 + Cash Ball
    // 1 in: 79,440
    // $2,500

    // 4
    // 1 in: 26,480
    // $500

    // 3 + Cash Ball
    // 1 in: 1,471
    // $100

    // 3
    // 1 in: 490
    // $25

    // 2 + Cash Ball
    // 1 in: 83
    // $10

    // 2
    // 1 in: 28
    // $4

    // 1 + Cash Ball
    // 1 in: 13
    // $2

    static private final double COST = 2;

    private final long[][] prize = { { 0, 0, 4, 25, 500, 1043000 }, { 0, 2, 10, 100, 2500, 7300000 } };

    public Cash4Life() {
        // noOfRegBalls, noOfSplBalls, minRegBallVal, maxRegBallVal, minSplBallVal, maxSplBallVal
        super(5, 1, 1, 60, 1, 4);
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
        return "[[0, 0, 4, 25, 500, 1043000], [0, 2, 10, 100, 2500, JACKPOT]]";
    }
}
