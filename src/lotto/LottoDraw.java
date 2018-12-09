package lotto;

import java.io.PrintStream;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletionService;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import lotto.type.AllOrNothing;
import lotto.type.Cash4Life;
import lotto.type.Gopher5;
import lotto.type.HotLotto;
import lotto.type.LuckyForLife;
import lotto.type.MegaMillions;
import lotto.type.NYLotto;
import lotto.type.NorthstarCash;
import lotto.type.Powerball;
import lotto.type.Take5;

final class LottoDraw {

    static String[] name = {
            "AllOrNothing",
            "Cash4Life",
            "Gopher5",
            "HotLotto",
            "LuckyForLife",
            "MegaMillions",
            "NYLotto",
            "NorthstarCash",
            "Powerball",
            "Take5"
    };

    static long[] odds = {
            new AllOrNothing().calcOdds(),
            new Cash4Life().calcOdds(),
            new Gopher5().calcOdds(),
            new HotLotto().calcOdds(),
            new LuckyForLife().calcOdds(),
            new MegaMillions().calcOdds(),
            new NYLotto().calcOdds(),
            new NorthstarCash().calcOdds(),
            new Powerball().calcOdds(),
            new Take5().calcOdds()
    };

    static int[] weeklyDraws = {
            new AllOrNothing().getDrawsPerWeek(),
            new Cash4Life().getDrawsPerWeek(),
            new Gopher5().getDrawsPerWeek(),
            new HotLotto().getDrawsPerWeek(),
            new LuckyForLife().getDrawsPerWeek(),
            new MegaMillions().getDrawsPerWeek(),
            new NYLotto().getDrawsPerWeek(),
            new NorthstarCash().getDrawsPerWeek(),
            new Powerball().getDrawsPerWeek(),
            new Take5().getDrawsPerWeek()
    };

    static double[] ticketCosts = {
            new AllOrNothing().getCost(),
            new Cash4Life().getCost(),
            new Gopher5().getCost(),
            new HotLotto().getCost(),
            new LuckyForLife().getCost(),
            new MegaMillions().getCost(),
            new NYLotto().getCost(),
            new NorthstarCash().getCost(),
            new Powerball().getCost(),
            new Take5().getCost()
    };

    static LottoDraw drawAllOrNothing(final int noOfTickets, final PrintStream psOut) {
        final AbstractLottery lotto = new AllOrNothing();
        final LottoDraw drawResult = new LottoDraw(lotto, noOfTickets, psOut);

        return drawResult;
    }

    static LottoDraw drawCash4Life(final int noOfTickets, final PrintStream psOut) {
        final AbstractLottery lotto = new Cash4Life();
        final LottoDraw drawResult = new LottoDraw(lotto, noOfTickets, psOut);

        return drawResult;
    }

    static LottoDraw drawGopher5(final int noOfTickets, final PrintStream psOut) {
        final AbstractLottery lotto = new Gopher5();
        final LottoDraw drawResult = new LottoDraw(lotto, noOfTickets, psOut);

        return drawResult;
    }

    static LottoDraw drawHotLotto(final int noOfTickets, final PrintStream psOut) {
        final AbstractLottery lotto = new HotLotto();
        final LottoDraw drawResult = new LottoDraw(lotto, noOfTickets, psOut);

        return drawResult;
    }

    static LottoDraw drawLuckyForLife(final int noOfTickets, final PrintStream psOut) {
        final AbstractLottery lotto = new LuckyForLife();
        final LottoDraw drawResult = new LottoDraw(lotto, noOfTickets, psOut);

        return drawResult;
    }

    static LottoDraw drawMegaMillions(final int noOfTickets, final PrintStream psOut) {
        final AbstractLottery lotto = new MegaMillions();
        final LottoDraw drawResult = new LottoDraw(lotto, noOfTickets, psOut);

        return drawResult;
    }

    static LottoDraw drawNYLotto(final int noOfTickets, final PrintStream psOut) {
        final AbstractLottery lotto = new NYLotto();
        final LottoDraw drawResult = new LottoDraw(lotto, noOfTickets, psOut);

        return drawResult;
    }

    static LottoDraw drawNorthstarCash(final int noOfTickets, final PrintStream psOut) {
        final AbstractLottery lotto = new NorthstarCash();
        final LottoDraw drawResult = new LottoDraw(lotto, noOfTickets, psOut);

        return drawResult;
    }

    static LottoDraw drawPowerBall(final long noOfTickets, final PrintStream psOut) {
        final AbstractLottery lotto = new Powerball();
        final LottoDraw drawResult = new LottoDraw(lotto, noOfTickets, psOut);

        return drawResult;
    }

    static LottoDraw drawTake5(final long noOfTickets, final PrintStream psOut) {
        final AbstractLottery lotto = new Take5();
        final LottoDraw drawResult = new LottoDraw(lotto, noOfTickets, psOut);

        return drawResult;
    }

    public static long getDraws(final int lotto, final int weekFactor, final int weeksIn50Years) {
        final int realWeekFactor = weekFactor > weeklyDraws[lotto] ? weeklyDraws[lotto] : weekFactor;

        return weeksIn50Years * realWeekFactor;
    }

    static String getHeaders() {
        return "TotalTickets|Winners|TotalTicketsWorth|TotalWinning|winnersHisto";
    }

    public static LottoDraw process(final int lottery, final int ticketsPurchased, final PrintStream psOut) {
        LottoDraw drawResult = null;
        switch (lottery) {
        case 0:
            drawResult = drawAllOrNothing(ticketsPurchased, psOut);
            break;
        case 1:
            drawResult = drawCash4Life(ticketsPurchased, psOut);
            break;
        case 2:
            drawResult = drawGopher5(ticketsPurchased, psOut);
            break;
        case 3:
            drawResult = drawHotLotto(ticketsPurchased, psOut);
            break;
        case 4:
            drawResult = drawLuckyForLife(ticketsPurchased, psOut);
            break;
        case 5:
            drawResult = drawMegaMillions(ticketsPurchased, psOut);
            break;
        case 6:
            drawResult = drawNYLotto(ticketsPurchased, psOut);
            break;
        case 7:
            drawResult = drawNorthstarCash(ticketsPurchased, psOut);
            break;
        case 8:
            drawResult = drawPowerBall(ticketsPurchased, psOut);
            break;
        case 9:
            drawResult = drawTake5(ticketsPurchased, psOut);
            break;
        default:
            break;
        }
        return drawResult;
    }

    private final AbstractLottery lotto;

    private final long noOfTickets;

    private final PrintStream psOut;

    private long totalTickets;

    private long totalTicketsWorth;

    private double totalWinning;

    private long winners;

    private final List<List<Integer>> winnersHisto = new CopyOnWriteArrayList<>();

    LottoDraw(final AbstractLottery lottoArg, final long noOfTicketsArg, final PrintStream psArg) {
        lotto = lottoArg;

        noOfTickets = noOfTicketsArg;

        psOut = psArg;

        submitTicGenNChk();
        calculateHisto();
    }

    // Based on the ways to win prizes, count and collate the winning details
    private void calculateHisto() {
        for (int i = 0; i < winnersHisto.size(); i++) {
            final List<Integer> winnerSplCount = winnersHisto.get(i);
            for (int j = 0; j < winnerSplCount.size(); j++) {
                final Integer winnersCount = winnerSplCount.get(j);
                final long prizeValue = lotto.getPrize(i, j);
                setTotalWinning(getTotalWinning() + prizeValue * winnersCount);
            }
        }
    }

    public long getTotalTickets() {
        return totalTickets;
    }

    long getTotalTicketsWorth() {
        return totalTicketsWorth;
    }

    double getTotalWinning() {
        return totalWinning;
    }

    public long getWinners() {
        return winners;
    }

    public void setTotalTickets(final long totalTickets) {
        this.totalTickets = totalTickets;
    }

    private void setTotalTicketsWorth(final long totalTicketsWorth) {
        this.totalTicketsWorth = totalTicketsWorth;
    }

    private void setTotalWinning(final double totalWinning) {
        this.totalWinning = totalWinning;
    }

    public void setWinners(final long winners) {
        this.winners = winners;
    }

    public void submitTicGenNChk() {
        for (int i = 0; i <= lotto.getNoOfSpecialBalls(); i++) {
            final List<Integer> winnersSplCount = new CopyOnWriteArrayList<>();
            winnersHisto.add(winnersSplCount);
            for (int j = 0; j <= lotto.getNoOfRegularBalls(); j++) {
                winnersSplCount.add(Integer.valueOf(0));
            }
        }

        final int cores = Runtime.getRuntime().availableProcessors();
        // final ExecutorService executor = Executors.newFixedThreadPool(cores *
        // 4);

        final BlockingQueue<Runnable> queue = new ArrayBlockingQueue<>(10000);
        final ThreadPoolExecutor executor = new ThreadPoolExecutor(cores, cores * 4, 60, TimeUnit.SECONDS, queue);

        final CompletionService<Ticket> execCompService = new ExecutorCompletionService<>(executor);

        final Callable<Ticket> generator = new TicketGenNChk(lotto);
        for (int i = 0; i < noOfTickets; i++) {
            setTotalTickets(getTotalTickets() + 1);
            execCompService.submit(generator);
        }

        setTotalTicketsWorth((long) Math.ceil(getTotalTickets() * lotto.getCost()));
        for (int i = 0; i < noOfTickets; i++) {
            try {
                final Ticket chkTkt = execCompService.take().get();
                if (chkTkt != null) {
                    if (chkTkt.isWinner()) {
                        setWinners(getWinners() + 1);
                        // System.out.println("Winning Ticket: " +
                        // lotto.getWinningTicket() + " Checked Ticket: " +
                        // chkTkt
                        // + " - " + chkTkt.getRegMatched() + " / " +
                        // chkTkt.getSplMatched());
                        if (chkTkt.wasJackpotHit()) {
                            psOut.println(" ***Jackpot Winning Ticket***: " + lotto.getWinningTicket());
                        }
                        final List<Integer> winnersSplCount = winnersHisto.get(chkTkt.getSplMatched());
                        final Integer count = winnersSplCount.get(chkTkt.getRegMatched());
                        winnersSplCount.set(chkTkt.getRegMatched(), count + 1);
                    }
                }
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }

        executor.shutdown();
        // }
    }

    @Override
    public String toString() {
        final StringBuffer strBuf = new StringBuffer();

        strBuf.append(getTotalTickets()).append('|').append(getWinners()).append('|').append(getTotalTicketsWorth())
                .append('|').append(getTotalWinning()).append('|').append(winnersHisto);

        return strBuf.toString();
    }

    public void updateHisto(final List<List<Integer>> histo) {
        for (int i = 0; i < winnersHisto.size(); i++) {
            List<Integer> summSplCount = null;
            if (histo.size() > i) {
                summSplCount = histo.get(i);
            } else {
                summSplCount = new CopyOnWriteArrayList<>();
                histo.add(summSplCount);
            }
            final List<Integer> drawSplCount = winnersHisto.get(i);
            for (int j = 0; j < drawSplCount.size(); j++) {
                Integer summWinsCount = Integer.valueOf(0);
                if (summSplCount.size() > j) {
                    summWinsCount = summSplCount.get(j);
                } else {
                    for (int k = 0; k < drawSplCount.size(); k++) {
                        summSplCount.add(Integer.valueOf(0));
                    }
                }

                final Integer drawWinsCount = drawSplCount.get(j);
                summSplCount.set(j, summWinsCount + drawWinsCount);
            }
        }
    }

    public static int getTicketsToBuy(int lotto, int investment) {
        return (int) Math.ceil(investment / ticketCosts[lotto]);
    }
}
