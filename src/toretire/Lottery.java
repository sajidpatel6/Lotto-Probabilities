package toretire;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import lotto.Ticket;

abstract class Lottery {

    private static int checkBalls(final List<Integer> lottoBalls, final List<Integer> ticketBalls) {
        int matched = 0;
        for (final Integer lotBall : lottoBalls) {
            for (final Integer ticBall : ticketBalls) {
                if (lotBall.intValue() == ticBall.intValue()) {
                    matched++;
                    break;
                }
            }
        }
        return matched;
    }

    private static List<Integer> pickballs(final List<Integer> balls, final int noOfBalls, final Random random) {
        Collections.shuffle(balls, random);
        return balls.subList(0, noOfBalls);
    }

    private final int maxRegBallValue;

    private final int maxSplBallValue;

    private final int minRegBallValue;

    private final int minSplBallValue;

    private final int noOfRegularBalls;

    private final int noOfSpecialBalls;

    private final List<Integer> regBalls = new ArrayList<Integer>();

    private final List<Integer> splBalls = new ArrayList<Integer>();

    private final Ticket winningTicket;

    // private static List<Integer> pickballsv2(List<Integer> balls, int
    // noOfBalls) {
    // List<Integer> selectedBalls = new ArrayList<Integer>();
    // for (int i = 0; i < noOfBalls; i++) {
    // int index = ThreadLocalRandom.current().nextInt(balls.size());
    // selectedBalls.add(balls.remove(index));
    // }
    //
    // return selectedBalls;
    // }

    Lottery(final int noOfRegBallsArg, final int noOfSplBallsArg, final int minRegBallValArg,
            final int maxRegBallValArg, final int minSplBallVal, final int maxSplBallValArg) {
        noOfRegularBalls = noOfRegBallsArg;
        noOfSpecialBalls = noOfSplBallsArg;
        minRegBallValue = minRegBallValArg;
        maxRegBallValue = maxRegBallValArg;
        minSplBallValue = minSplBallVal;
        maxSplBallValue = maxSplBallValArg;

        for (int i = minRegBallValue; i <= maxRegBallValue; i++) {
            regBalls.add(Integer.valueOf(i));
        }

        for (int i = minSplBallValue; i < maxSplBallValue; i++) {
            splBalls.add(Integer.valueOf(i));
        }

        // Pick a number combination as a winning ticket
        winningTicket = this.quickpick(true);
    }

    Ticket checkTicket(final Ticket ticket) {
        Ticket wticket = null;

        // Count how many balls match with the winning ticket
        final int rMatched = checkBalls(this.winningTicket.getPickedRegBalls(), ticket.getPickedRegBalls());
        final int sMatched = checkBalls(this.winningTicket.getPickedSplBalls(), ticket.getPickedSplBalls());

        // Set the results on the ticket
        wticket = ticket;
        wticket.setSplMatched(sMatched);
        wticket.setRegMatched(rMatched);
        wticket.setWin(this.getPrize(sMatched, rMatched));

        // if all the balls matched, we have hit a jackpot!
        if ((this.noOfRegularBalls == rMatched) && (this.noOfSpecialBalls == sMatched)) {
            wticket.hitJackpot(true);
        }

        return wticket;
    }

    abstract int getCost();

    int getNoOfRegularBalls() {
        return noOfRegularBalls;
    }

    int getNoOfSpecialBalls() {
        return noOfSpecialBalls;
    }

    abstract long getPrize(int splMatchCount, int regMatchCount);

    abstract String getPrizesHeader();

    Ticket getWinningTicket() {
        return winningTicket;
    }

    final Ticket quickpick(final boolean winning) {
        final Ticket ticket = new Ticket();

        Random random = null;
        if (winning) {
            random = new Random();
        } else {
            random = ThreadLocalRandom.current();
        }
        ticket.addRegular(pickballs(regBalls, noOfRegularBalls, random));
        ticket.addSpecial(pickballs(splBalls, noOfSpecialBalls, random));
        ticket.sortBalls();
        return ticket;
    }
}
