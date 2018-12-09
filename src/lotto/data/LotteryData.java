package lotto.data;

import java.text.NumberFormat;
import java.util.Comparator;

public class LotteryData implements Comparator<LotteryData> {
    String lottoName;

    Double probability;

    Long WinAmount;

    public LotteryData() {
        // Only to be used for comparators instances
    }

    public LotteryData(String lottoNameArg, long leastWinAmountArg, double probabilityArg) {
        lottoName = lottoNameArg;
        WinAmount = leastWinAmountArg;
        probability = probabilityArg;
    }

    @Override
    public int compare(LotteryData o1, LotteryData o2) {
        int returnVal = 0;
        if (o1.WinAmount > o2.WinAmount) {
            returnVal = 1;
        } else if (o1.WinAmount < o2.WinAmount) {
            returnVal = -1;
        } else {
            if (o1.probability < o2.probability) {
                returnVal = 1;
            } else if (o1.probability > o2.probability) {
                returnVal = -1;
            } else {
                returnVal = 0;
            }
        }
        return returnVal;
    }

    public Long getWinAmount() {
        return WinAmount;
    }

    public String printOdds() {
        final NumberFormat numFmt = NumberFormat.getInstance();

        final NumberFormat pctFmt = NumberFormat.getPercentInstance();
        pctFmt.setMinimumFractionDigits(8);

        return lottoName + " - win $" + numFmt.format(WinAmount) + " --> 1 in " + numFmt.format(1 / probability);
    }

    @Override
    public String toString() {
        final NumberFormat numFmt = NumberFormat.getInstance();

        final NumberFormat pctFmt = NumberFormat.getPercentInstance();
        pctFmt.setMinimumFractionDigits(8);

        return lottoName + " | " + numFmt.format(1 / probability) + " | " + pctFmt.format(probability);
    }
}
