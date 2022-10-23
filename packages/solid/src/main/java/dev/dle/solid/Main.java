package dev.dle.solid;

import dev.dle.solid.srp.Journal;

public class Main {
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String TEXT_IN_BOLD = "\033[0;1m";
    public static void main(String[] args) {

        Journal journal = new Journal();


        journal.addEntry("I cried today");
        journal.addEntry("I ate a bug");

        System.out.println(TEXT_IN_BOLD + ANSI_PURPLE + journal + ANSI_RESET);
    }
}