package org.zorkrip.engine;

public record Command(String commandWord, String secondWord) {


    public boolean hasSecondWord() {
        return secondWord != null;
    }
}
