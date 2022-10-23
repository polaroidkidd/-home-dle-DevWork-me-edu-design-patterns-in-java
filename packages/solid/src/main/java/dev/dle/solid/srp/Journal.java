package dev.dle.solid.srp;

import java.util.ArrayList;
import java.util.List;

public class Journal {
    public Journal() {
        System.out.println("Hello From SingleResponsibilityPrinciple");
    }

    private final List<String> entries = new ArrayList<>();
    private static int count = 0;

    /**
     * Adds an entry
     *
     * @param text Text to add to journal
     */
    public void addEntry(String text) {
        entries.add("" + (++count) + ": " + text);
    }


    /**
     * Removes the entry at index
     *
     * @param index index of entry to remove
     */
    public void removeEntry(int index) {
        entries.remove(index);
    }

    @Override
    public String toString() {
        return String.join(System.lineSeparator(), entries);
    }


}
