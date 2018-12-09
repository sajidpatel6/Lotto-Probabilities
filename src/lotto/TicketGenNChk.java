package lotto;

import java.util.concurrent.Callable;

public class TicketGenNChk implements Callable<Ticket> {
    AbstractLottery lotto;

    public TicketGenNChk(final AbstractLottery lottoArg) {
        lotto = lottoArg;
    }

    @Override
    public Ticket call() {
        return lotto.checkTicket(lotto.quickpick(false));
    }

}
