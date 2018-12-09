package lotto;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ThreadLocalRandom;

import lotto.data.LotteryData;
import lotto.type.AllOrNothing;

public abstract class AbstractLottery {

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
        final List<Integer> selectedBalls = new CopyOnWriteArrayList<>();
        if (!balls.isEmpty()) {
            final List<Integer> selectedIndexes = new CopyOnWriteArrayList<>();
            for (;;) {
                final int index = random.nextInt(balls.size());
                if (!selectedIndexes.contains(Integer.valueOf(index))) {
                    selectedIndexes.add(Integer.valueOf(index));
                    selectedBalls.add(balls.get(index));
                }
                if (selectedBalls.size() == noOfBalls) {
                    break;
                }
            }
        }
        return selectedBalls;
    }

    protected int drawsPerWeek;

    private final int countOfRegBalls;

    private final int countOfSplBalls;

    private final int noOfRegNos;

    private final int noOfSpecialNos;

    private final List<Integer> regBalls = new CopyOnWriteArrayList<>();

    private final List<Integer> splBalls = new CopyOnWriteArrayList<>();

    private final Ticket winningTicket;

    protected AbstractLottery(final int noOfRegNosArg, final int noOfSpecialNosArg, final int minRegBallValArg,
            final int maxRegBallValArg, final int minSplBallValArg, final int maxSplBallValArg) {
        noOfRegNos = noOfRegNosArg;
        noOfSpecialNos = noOfSpecialNosArg;
        countOfRegBalls = maxRegBallValArg;
        countOfSplBalls = maxSplBallValArg;

        for (int i = minRegBallValArg; i <= countOfRegBalls; i++) {
            regBalls.add(Integer.valueOf(i));
        }

        for (int i = minSplBallValArg; i < countOfSplBalls; i++) {
            splBalls.add(Integer.valueOf(i));
        }

        // Pick a number combination as a winning ticket
        winningTicket = this.quickpick(true);
    }

    protected AbstractLottery(final int noOfRegNosArg, final int noOfSpecialNosArg, final int countOfRegBallsArg,
            final int countOfSplBallsArg) {
        this(noOfRegNosArg, noOfSpecialNosArg, 1, countOfRegBallsArg, 1, countOfSplBallsArg);
    }

    long calcCombin(final int allOutcomes, final int desiredOutcomes) {
        long returnVal = 1;
        if (desiredOutcomes > allOutcomes) {
            returnVal = 0;
        } else {
            final int diff = allOutcomes - desiredOutcomes;
            int desOutcome = desiredOutcomes;
            if (diff > desiredOutcomes) {
                desOutcome = diff;
            }

            for (int index = allOutcomes; index > desOutcome; index--) {
                returnVal *= index;
            }
            for (int index = allOutcomes - desOutcome; index > 1; index--) {
                returnVal /= index;
            }
        }
        return returnVal;
    }

    public long calcOdds() {
        return calcCombin(countOfRegBalls, noOfRegNos) * calcCombin(countOfSplBalls, noOfSpecialNos);
    }

    public double calcProbOfWinningAtleast(final long prizeAmount) {
        double prob = 0;
        long favorableOutcomes = 0;
        final long allOutcomes = calcOdds();

        final long[][] prize = getPrizes();
        for (int iIndex = 0; iIndex < prize.length; iIndex++) {
            for (int jIndex = 0; jIndex < prize[iIndex].length; jIndex++) {
                if (prize[iIndex][jIndex] >= prizeAmount) {
                    favorableOutcomes = calcCombin(countOfRegBalls - noOfRegNos, noOfRegNos - jIndex)
                            * calcCombin(noOfRegNos, jIndex) * calcCombin(noOfSpecialNos, iIndex)
                            * calcCombin(countOfSplBalls - noOfSpecialNos, noOfSpecialNos - iIndex);
                    prob += (double) favorableOutcomes / allOutcomes;
                }
            }
        }

        return prob;
    }

    public double calcProbOfWinningMillions() {
        return calcProbOfWinningAtleast(1000000);
    }

    public Ticket checkTicket(final Ticket ticket) {
        // Count how many balls match with the winning ticket
        final int rMatched = checkBalls(this.winningTicket.getPickedRegBalls(), ticket.getPickedRegBalls());
        final int sMatched = checkBalls(this.winningTicket.getPickedSplBalls(), ticket.getPickedSplBalls());

        // Set the results on the ticket
        ticket.setSplMatched(sMatched);
        ticket.setRegMatched(rMatched);
        ticket.setWin(this.getPrize(sMatched, rMatched));

        if (this.noOfRegNos == 0 && this instanceof AllOrNothing) {
            ticket.hitJackpot(true);
        }

        if (this.noOfRegNos == rMatched && this.noOfSpecialNos == sMatched) {
            ticket.hitJackpot(true);
        }

        return ticket;
    }

    public abstract double getCost();

    public int getDrawsPerWeek() {
        return drawsPerWeek;
    }

    public int getNoOfRegularBalls() {
        return noOfRegNos;
    }

    public int getNoOfSpecialBalls() {
        return noOfSpecialNos;
    }

    public abstract long getPrize(int splMatchCount, int regMatchCount);

    protected abstract long[][] getPrizes();

    protected abstract String getPrizesHeader();

    public Ticket getWinningTicket() {
        return winningTicket;
    }

    public final Ticket quickpick(final boolean winning) {
        final Ticket ticket = new Ticket();

        Random random = null;
        if (winning) {
            random = new Random();
        } else {
            random = ThreadLocalRandom.current();
        }
        ticket.addRegular(pickballs(regBalls, noOfRegNos, random));
        ticket.addSpecial(pickballs(splBalls, noOfSpecialNos, random));
        ticket.sortBalls();
        return ticket;
    }

    public void printOddsForAllPrizes() {
        double prob = 0;
        long favorableOutcomes = 0;
        final long allOutcomes = calcOdds();

        final NumberFormat numFmt = NumberFormat.getInstance();
        numFmt.setGroupingUsed(true);

        List<LotteryData> lottoDataList = new ArrayList<>();
        final long[][] prize = getPrizes();
        for (int iIndex = prize.length - 1; iIndex >= 0; iIndex--) {
            for (int jIndex = prize[iIndex].length - 1; jIndex >= 0; jIndex--) {
                favorableOutcomes = calcCombin(countOfRegBalls - noOfRegNos, noOfRegNos - jIndex)
                        * calcCombin(noOfRegNos, jIndex) * calcCombin(noOfSpecialNos, iIndex)
                        * calcCombin(countOfSplBalls - noOfSpecialNos, noOfSpecialNos - iIndex);

                if (prize[iIndex][jIndex] > 0) {
                    LotteryData lottoData = new LotteryData(this.getClass().getSimpleName(), prize[iIndex][jIndex],
                            (double) favorableOutcomes / allOutcomes);
                    lottoDataList.add(lottoData);

                    prob += (double) favorableOutcomes / allOutcomes;
                }
            }
        }

        System.out.println(
                this.getClass().getSimpleName() + " - Overall --> 1 in " + numFmt.format(1 / prob));
        Collections.sort(lottoDataList, new LotteryData());
        for (LotteryData lottoData : lottoDataList) {
            System.out.println(lottoData.printOdds());
        }
        System.out.println();
    }

    public double calcProbOfWinningAtleast(long winAmount, double investmentAmount) {
        int noOfTickets = (int) Math.ceil(investmentAmount / getCost());
        return calcProbOfWinningAtleast(winAmount) * noOfTickets;
    }
}
