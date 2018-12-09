package lotto;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ThreadLocalRandom;

import lotto.core.LotteryFactory;
import lotto.data.LotteryData;

class LottoMain {

    static int weeksIn50Years = 2607;

    // Print probabilities in sorted order for range of winnings possible
    public static void main3(final String[] args) {
        final NumberFormat numFmt = NumberFormat.getInstance();
        // List of all lotteries
        // final String[] lotteries = { "AllOrNothing", "Cash4Life", "Gopher5", "HotLotto", "LuckyForLife",
        // "MegaMillions",
        // "NorthstarCash", "NYLotto", "Powerball", "Take5" };

        // List of NY lotteries
        final String[] lotteries = { "MegaMillions", "Powerball", "Cash4Life", "NYLotto", "Take5" };
        List<Long> winAmountsToTest = new ArrayList<>();
        winAmountsToTest.add((long) 1);
        winAmountsToTest.add((long) 2);
        winAmountsToTest.add((long) 5);
        winAmountsToTest.add((long) 10);
        winAmountsToTest.add((long) 50);
        winAmountsToTest.add((long) 100);
        winAmountsToTest.add((long) 500);
        winAmountsToTest.add((long) 1000);
        winAmountsToTest.add((long) 5000);
        winAmountsToTest.add((long) 50000);
        winAmountsToTest.add((long) 100000);
        winAmountsToTest.add((long) 500000);
        winAmountsToTest.add((long) 1000000);
        winAmountsToTest.add((long) 10000000);

        double investmentAmount = 2.0;
        List<LotteryData> lottoDataList = new ArrayList<>();
        for (long winAmount : winAmountsToTest) {
            // final long winAmount = (long) Math.pow(2, i);

            for (final String lottoName : lotteries) {
                final AbstractLottery lotto = LotteryFactory.getLottery(lottoName);
                if (winAmount == 0) {
                    lotto.printOddsForAllPrizes();
                }

                final double probability = lotto.calcProbOfWinningAtleast(winAmount, investmentAmount);
                LotteryData lottoData = new LotteryData(lottoName, winAmount, probability);
                lottoDataList.add(lottoData);
            }
        }

        System.out.println("Lottery, Odds, Probability%");
        Collections.sort(lottoDataList, new LotteryData());
        long winAmount = 0;
        for (LotteryData lottoData : lottoDataList) {
            if (lottoData.getWinAmount() != winAmount) {
                winAmount = lottoData.getWinAmount();
                System.out.println("\n ---- " + numFmt.format(winAmount) + " ---- ");
            }
            System.out.println(lottoData);
        }
    }

    // Simulate lottery playing for specified period with specified number of tickets every draw
    public static void main(final String[] args) {
        try {
//            final ThreadLocalRandom random = ThreadLocalRandom.current();
            final File summFile = new File("LotteryStats_" + System.currentTimeMillis() + ".csv");
            final PrintStream summPS = new PrintStream(new FileOutputStream(summFile));
            summPS.println(
                    "Iteration,LotteryName,NoOfDraws,TicketsPerDraw,TotalTickets,TotalWinners,Percent#,TotalMoneySpent,TotalMoneyWon,percent$");

            final int maxRepeat = 50;
            final int maxInvestment = 20;
            final String[] lotteries = { "MegaMillions", "Powerball", "Cash4Life", "NYLotto", "Take5" };
            for (int repeat = 1; repeat <= maxRepeat; repeat++) {
                for (int investment = 2; investment <= maxInvestment; investment += 2) {
                    // for (int weekFactor = startingWFactor; weekFactor <
                    // endingWFactor; weekFactor *= 2) {
                    // for (int lotto = 0; lotto < LottoDraw.name.length; lotto++) {
                    for (String lottoName : lotteries) {
                        // 3850 times; was 17500 times
                        summPS.print(repeat + ",");
                        // final long draws = LottoDraw.getDraws(lotto, random.nextInt(14) + 1, weeksIn50Years);
                        // lets play only once per week
                        final long draws = 2607; // weeks in about 50 years
                        // draws = random.nextLong(draws) + draws / 2;

                        int lotto = Arrays.asList(LottoDraw.name).indexOf(lottoName);
                        System.out.print("\nRepeat: " + repeat + "/" + maxRepeat + " Investment: " + investment + "/"
                                + maxInvestment // + " Factor: " + weekFactor + "/" + maxWFactor
                                + " Draws: " + draws + " Lotto: " + LottoDraw.name[lotto] + "   ");
                        int tickets = LottoDraw.getTicketsToBuy(lotto, investment);
                        oneRound(lotto, draws, tickets, summPS);
                    }
                    // }
                }
            }
            summPS.close();
        } catch (final IOException e) {
            e.printStackTrace();
        }
    }

    public static void main1(final String[] args) {
        try {
            final ThreadLocalRandom random = ThreadLocalRandom.current();
            final File summFile = new File("LotteryStats_" + System.currentTimeMillis() + ".csv");
            final PrintStream summPS = new PrintStream(new FileOutputStream(summFile));
            summPS.println(
                    "Iteration,LotteryName,NoOfDraws,TicketsPerDraw,TotalTickets,TotalWinners,Percent#,TotalMoneySpent,TotalMoneyWon,percent$");

            final int maxRepeat = 50;
            final int maxTickets = 11;
            for (int repeat = 1; repeat <= maxRepeat; repeat++) {
                for (int tickets = 1; tickets < maxTickets; tickets++) {
                    // for (int weekFactor = startingWFactor; weekFactor <
                    // endingWFactor; weekFactor *= 2) {
                    for (int lotto = 0; lotto < LottoDraw.name.length; lotto++) {
                        // 3850 times; was 17500 times
                        summPS.print(repeat + ",");
                        final long draws = LottoDraw.getDraws(lotto, random.nextInt(14) + 1, weeksIn50Years);
                        // draws = random.nextLong(draws) + draws / 2;
                        System.out.print("\nRepeat: " + repeat + "/" + maxRepeat + " Tickets: " + tickets + "/"
                                + (maxTickets - 1)
                                // + " Factor: " + weekFactor + "/" + maxWFactor
                                + " Draws: " + draws + " Lotto: " + LottoDraw.name[lotto] + "   ");
                        oneRound(lotto, draws, tickets, summPS);
                    }
                    // }
                }
            }
            summPS.close();
        } catch (final IOException e) {
            e.printStackTrace();
        }
    }

    public static void main2(final String[] args) {
        try {
            final File summFile = new File("LotteryStats_" + System.currentTimeMillis() + ".csv");
            final PrintStream summPS = new PrintStream(new FileOutputStream(summFile));
            for (int lotto = 0; lotto < LottoDraw.name.length; lotto++) {
                oneRound(lotto, LottoDraw.odds[lotto], 1, summPS);
            }
            summPS.close();
        } catch (final IOException e) {
            e.printStackTrace();
        }
    }

    private static void oneRound(final int lottery, final long noOfDraws, final int tickets, final PrintStream summPS)
            throws IOException {
        final NumberFormat intFormat = NumberFormat.getIntegerInstance();
        final NumberFormat currencyFormat = NumberFormat.getCurrencyInstance();
        final NumberFormat percentFormat = NumberFormat.getPercentInstance();
        percentFormat.setMinimumFractionDigits(2);

        final File outFile = new File(
                LottoDraw.name[lottery] + "_" + noOfDraws + "_" + tickets + "_" + System.currentTimeMillis() + ".txt");
        final PrintStream psOut = new PrintStream(new FileOutputStream(outFile));
        final long start = System.currentTimeMillis();
        psOut.println("Number of draws: " + intFormat.format(noOfDraws));
        psOut.println("Tickets purchased: " + intFormat.format(tickets));
        summPS.print(LottoDraw.name[lottery] + "," + noOfDraws + "," + tickets + ",");

        long totalMoneySpent = 0;
        long totalMoneyWon = 0;
        long totalTickets = 0;
        long totalWinners = 0;

        int per25Comp = 0;
        int per25Printed = 0;
        final List<List<Integer>> winnersHisto = new CopyOnWriteArrayList<>();

        for (long i = 0; i < noOfDraws; i++) {
            final LottoDraw result = LottoDraw.process(lottery, tickets, psOut);

            result.updateHisto(winnersHisto);
            totalMoneySpent += result.getTotalTicketsWorth();
            totalMoneyWon += result.getTotalWinning();
            totalTickets += result.getTotalTickets();
            totalWinners += result.getWinners();
            per25Comp = (int) ((i + 1) * 25 / noOfDraws);
            if (per25Comp > per25Printed) {
                // System.out.print(percentComp + " ");
                for (int j = 0; j < per25Comp - per25Printed; j++) {
                    System.out.print(".");
                }
                per25Printed = per25Comp;
            }
        }

        double percent = (double) totalWinners / totalTickets;
        psOut.println(" Total -> Tickets: " + intFormat.format(totalTickets) + " | Winners: "
                + intFormat.format(totalWinners) + " | " + percentFormat.format(percent));
        summPS.print(totalTickets + "," + totalWinners + "," + percent + ",");

        percent = (double) totalMoneyWon / totalMoneySpent;
        psOut.println(" Total -> Money Spent: " + currencyFormat.format(totalMoneySpent) + " | Money Won: "
                + currencyFormat.format(totalMoneyWon) + " | " + percentFormat.format(percent));
        summPS.print(totalMoneySpent + "," + totalMoneyWon + "," + percent);

        for (int i = 0; i < winnersHisto.size(); i++) {
            final List<Integer> winnerSplCount = winnersHisto.get(i);
            for (int j = 0; j < winnerSplCount.size(); j++) {
                final Integer winnersCount = winnerSplCount.get(j);
                if (winnersCount > 0) {
                    if (i > 0) {
                        psOut.print(intFormat.format(i) + " special balls & ");
                    }
                    psOut.println(intFormat.format(j) + " regular balls: " + intFormat.format(winnersCount) + " times");
                }
            }
        }
        psOut.println("Took " + intFormat.format((System.currentTimeMillis() - start) / 1000) + " secs to process.");

        psOut.close();
        summPS.print("\n");
    }

}
