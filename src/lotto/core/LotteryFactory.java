package lotto.core;

import lotto.AbstractLottery;
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

public class LotteryFactory {
    public static AbstractLottery getLottery(final String lottoName) {
        AbstractLottery returnVal = null;

        switch (lottoName.toLowerCase()) {
        case "allornothing":
            returnVal = new AllOrNothing();
            break;
        case "gopher5":
            returnVal = new Gopher5();
            break;
        case "hotlotto":
            returnVal = new HotLotto();
            break;
        case "luckyforlife":
            returnVal = new LuckyForLife();
            break;
        case "megamillions":
            returnVal = new MegaMillions();
            break;
        case "northstarcash":
            returnVal = new NorthstarCash();
            break;
        case "powerball":
            returnVal = new Powerball();
            break;
        case "cash4life":
            returnVal = new Cash4Life();
            break;
        case "nylotto":
            returnVal = new NYLotto();
            break;
        case "take5":
            returnVal = new Take5();
            break;
        }
        return returnVal;
    }
}
