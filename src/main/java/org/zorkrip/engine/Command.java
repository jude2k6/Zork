package org.zorkrip.engine;

public record Command(String commandWord, String secondWord) {

    public boolean isUnknown() {
        return commandWord == null;
    }

    public boolean hasSecondWord() {
        return secondWord != null;
    }
}
