package dev.dle.solid.lsp;

public class LiskovSubstitutionPrinciple {

    public void runDemo() {
        Rectangle rc = new Rectangle(2, 3);
        useIt(rc);

        /*
         * The square function silently sets width and height, even though it's a Rectangle. When you get
         * the wrong area for this
         */
        Rectangle sq = new Square();
        sq.setHeight(5);
        useIt(sq);

        /*
         * With this factory you can create shapes
         */
        Rectangle fc = RectangleFactory.newSquare(5);
        useIt(fc);

        Rectangle nfc = RectangleFactory.newRectangle(2, 7);
        useIt(nfc);
    }

    static void useIt(Rectangle r) {
        System.out.println("Area (old): " + r.getArea());
        r.setHeight(8);
//        r.setWidth(2);
        System.out.println("Area (new): " + r.getArea());


    }
}


class RectangleFactory {
    public static Rectangle newRectangle(int width, int height) {
        return new Rectangle(width, height);
    }

    public static Rectangle newSquare(int side) {
        return new Square(side);
    }
}

class Rectangle {
    protected int width, height;

    public Rectangle() {

    }

    public Rectangle(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getArea() {
        return width * height;
    }

    @Override
    public String toString() {
        return "Rectangle{" +
                "width=" + width +
                ", height=" + height +
                '}';
    }
}

class Square extends Rectangle {
    public Square() {
    }

    public Square(int size) {
        width = height = size;
    }

    @Override
    public void setWidth(int width) {
        super.setWidth(width);
        super.setHeight(width);
    }

    @Override
    public void setHeight(int height) {
        super.setHeight(height);
        super.setWidth(height);
    }
}