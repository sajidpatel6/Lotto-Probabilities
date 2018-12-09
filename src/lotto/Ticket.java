package lotto;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class Ticket {
    private boolean jackpotFlag;

    private final List<Integer> pickedRegBalls = new CopyOnWriteArrayList<>();

    private final List<Integer> pickedSplBalls = new CopyOnWriteArrayList<>();

    private int rMatched;

    private int sMatched;

    private long win;

    public void addRegular(final List<Integer> pickballs) {
        getPickedRegBalls().addAll(pickballs);
    }

    public void addSpecial(final List<Integer> pickballs) {
        getPickedSplBalls().addAll(pickballs);
    }

    public List<Integer> getPickedRegBalls() {
        return pickedRegBalls;
    }

    public List<Integer> getPickedSplBalls() {
        return pickedSplBalls;
    }

    public int getRegMatched() {
        return rMatched;
    }

    public int getSplMatched() {
        return sMatched;
    }

    public void hitJackpot(final boolean hitJackpot) {
        this.jackpotFlag = hitJackpot;
    }

    public boolean isWinner() {
        boolean returnVal = false;
        if (win > 0) {
            returnVal = true;
        }
        return returnVal;
    }

    public void setRegMatched(final int rMatched) {
        this.rMatched = rMatched;
    }

    public void setSplMatched(final int sMatched) {
        this.sMatched = sMatched;
    }

    public void setWin(final long prize) {
        this.win = prize;
    }

    public void sortBalls() {
        Collections.sort(getPickedRegBalls());
        Collections.sort(getPickedSplBalls());
    }

    @Override
    public String toString() {
        String returnVal = this.getClass().getSimpleName();
        if (getPickedSplBalls().isEmpty()) {
            returnVal += " : " + getPickedRegBalls().toString();
        } else {
            returnVal += " : " + getPickedRegBalls().toString() + getPickedSplBalls().toString();
        }
        return returnVal;
    }

    public boolean wasJackpotHit() {
        return jackpotFlag;
    }
}
