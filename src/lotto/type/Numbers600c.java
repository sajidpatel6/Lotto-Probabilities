package lotto.type;

import lotto.AbstractLottery;

//Not implemented - not a typical lottery
@Deprecated
public class Numbers600c extends AbstractLottery {

    // StraightPlay
    // ODDS | PAYOFF FOR 50¢ | PAYOFF FOR $1 PLAY
    // Match the winning number drawn exactly in order
    // 1000:1 | $250 | $500

    // BoxPlays
    // Match the winning numbers drawn in any order.
    // 3-WAY BOX : Select a 3-digit number with 2 identical digits.
    // ODDS | PAYOFF FOR 50¢ | PAYOFF FOR $1 PLAY
    // 333:1 | $80 | $160
    // 6-WAY BOX : Select a 3-digit number with 3 unique digits.
    // ODDS | PAYOFF FOR 50¢ | PAYOFF FOR $1 PLAY
    // 167:1 | $40 | $80

    // Straight/BoxPlays
    // Win both the straight and box prizes if you match the winning numbers drawn in their exact order. Win only the
    // box prize if you match the winning numbers drawn in any order.
    // 3-WAY STRAIGHT / BOX : Select a 3-digit number with 2 identical digits.
    // ODDS | PAYOFF FOR $1 PLAY
    // Match exact in order => 333:1 | $330
    // Match in any other order => $80
    // 6-WAY STRAIGHT / BOX : Select a 3-digit number with 3 unique digits.
    // ODDS | PAYOFF FOR $1 PLAY
    // Match exact in order => 167:1 | $290
    // Match in any other order => $40

    // CombinationPlays
    // A convenient way to play all possible Straight combinations on one ticket. You win of your numbers match the
    // winning numbers in any order.
    // 3-WAY COMBINATION : Select a 3-digit number with 3 unique digits.
    // ODDS | PAYOFF FOR $1.50 PLAY | PAYOFF FOR $3.00 PLAY
    // 333:1 | $250 | $500
    // 6-WAY COMBINATION Select a 4-digit number with 2 identical pairs.
    // ODDS | PAYOFF FOR $3.00 PLAY | PAYOFF FOR $6.00 PLAY
    // 167:1 | $250 | $500

    // CombinationPlays
    // Select a 2-digit number. Match either the first 2 or last 2 digits of the winning number drawn, in order. This is
    // a separate bet option.
    // FRONT PAIR
    // ODDS | PAYOFF FOR 50¢ | PAYOFF FOR $1 PLAY
    // 100:1 | $25 | $50
    // BACK PAIR
    // ODDS | PAYOFF FOR 50¢ | PAYOFF FOR $1 PLAY
    // 100:1 | $25 | $50
    static private final double COST = 1;

    private final long[][] prize = { { 0, 0, 1, 25, 508, 57575 } };

    public Numbers600c() {
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
