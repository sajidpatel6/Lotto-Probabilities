package toretire;

import java.util.concurrent.Callable;

import lotto.Ticket;

class TicketGenerator implements Callable<Ticket> {
    Lottery lotto;

    TicketGenerator(final Lottery lottoArg) {
        lotto = lottoArg;
    }

    @Override
    public Ticket call() throws Exception {
        return lotto.quickpick(false);
    }
}
