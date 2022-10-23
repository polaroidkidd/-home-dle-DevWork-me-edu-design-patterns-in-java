package dev.dle.solid.srp;

import java.io.FileNotFoundException;

public class SingleResponsibilityPrincipleDemo {
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String TEXT_IN_BOLD = "\033[0;1m";

    private final Journal journal;

    public SingleResponsibilityPrincipleDemo() {
        this.journal = new Journal();
    }

    public void runDemo() {


        journal.addEntry("I cried today");
        journal.addEntry("I ate a bug");


        System.out.println(TEXT_IN_BOLD + ANSI_PURPLE + journal + ANSI_RESET);

        Persistence persistence = new Persistence();
        String filename = "/home/dle/DevWork/me/edu/design-patterns-in-java/packages/solid/src/main/resources/journal.txt";
        try {

            persistence.saveToFile(journal, filename, true);
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }


    }


}
