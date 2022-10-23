package dev.dle.solid.isp;

public class InterfaceSegregationPrinciple {

    public void runDemo() {
        Document d = new Document("This is the document text");
        Printer printer = new JustAPrinter();
        Scanner scanner = new JustAScanner();
        printer.print(d);
        MultiFunctionDevice mfd = new PrinterScanner(printer, scanner);
        mfd.print(d);
        mfd.scan(d);
    }
}


class Document {

    private String text;

    public Document(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return text;
    }
}


interface Printer {
    void print(Document d);
}

interface Scanner {
    void scan(Document d);
}

interface MultiFunctionDevice extends Printer, Scanner {
}

class JustAPrinter implements Printer {

    @Override
    public void print(Document d) {
        System.out.println("Printing: " + d.toString());
    }
}

class JustAScanner implements Scanner{
    @Override
    public void scan(Document d) {
        System.out.println("Scanning this text: " + d.toString());
    }
}

class PrinterScanner implements MultiFunctionDevice {

    private final Printer printer;
    private final Scanner scanner;

    public PrinterScanner(Printer printer, Scanner scanner) {
        this.printer = printer;
        this.scanner = scanner;
    }

    @Override
    public void print(Document d) {
        printer.print(d);
    }

    @Override
    public void scan(Document d) {
        scanner.scan(d);
    }
}

