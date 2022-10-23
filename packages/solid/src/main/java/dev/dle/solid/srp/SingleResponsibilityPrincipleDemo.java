package dev.dle.solid.srp;

import java.io.FileNotFoundException;

public class SingleResponsibilityPrincipleDemo {


    private final Journal journal;

    public SingleResponsibilityPrincipleDemo() {
        this.journal = new Journal();
    }

    public void runDemo(boolean shouldRun) {
        if (shouldRun) {
            journal.addEntry("I cried today");
            journal.addEntry("I ate a bug");


            System.out.println(journal);

            Persistence persistence = new Persistence();
            String filename = "/home/dle/DevWork/me/edu/design-patterns-in-java/packages/solid/src/main/resources/journal.txt";
            try {

                persistence.saveToFile(journal, filename, true);
            } catch (FileNotFoundException e) {
                System.out.println(e.getMessage());
            }

        }

    }


}
