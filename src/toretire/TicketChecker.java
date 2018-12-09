package toretire;

import java.util.concurrent.Callable;

import lotto.Ticket;

class TicketChecker implements Callable<Ticket> {
    Lottery lotto;

    Ticket ticket;

    TicketChecker(final Lottery lottoArg, final Ticket ticketArg) {
        lotto = lottoArg;
        ticket = ticketArg;
    }

    @Override
    public Ticket call() throws Exception {
        return lotto.checkTicket(ticket);
    }
}
