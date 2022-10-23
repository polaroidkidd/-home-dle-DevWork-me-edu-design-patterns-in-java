package dev.dle.solid.ocp;


import java.util.List;
import java.util.stream.Stream;

enum Color {
    RED, GREEN, BLUE
}

enum Size {
    SMALL, MEDIUM, LARGE, HUGE
}

class Product {
    public String name;
    public Color color;
    public Size size;

    public Product(String name, Color color, Size size) {
        this.name = name;
        this.color = color;
        this.size = size;
    }
}

/**
 * This product filter is bad because it only allows us to filter by one charateristic.
 * If we want to filter by more, we will have to jump back into the product filter
 * and change how it works, this violating the OpenClose Principle
 */
class ProductFilter {
    public Stream<Product> filterByColor(List<Product> products, Color color) {
        return products.stream().filter(p -> p.color == color);
    }
}

/**
 * This interface defines the functionality which we need to pass into a filter for it to remain generic
 * @param <T>
 */
interface Specification<T> {
    boolean isSatisfied(T item);
}

/**
 * This interface allows us to specify what a filter needs to implement in order to work correctly
 * @param <T>
 */
interface Filter<T> {
    Stream<T> filter(List<T> list, Specification<T> spec);
}

/**
 * Example Specification for Colors
 */
class ColorSpecification implements Specification<Product> {

    private final Color color;

    public ColorSpecification(Color color) {
        this.color = color;
    }

    @Override
    public boolean isSatisfied(Product item) {
        return item.color == color;
    }
}

/**
 * Example implementation for Sizes
 */
class SizeSpecification implements Specification<Product> {

    private final Size size;

    public SizeSpecification(Size size) {
        this.size = size;
    }

    @Override
    public boolean isSatisfied(Product item) {
        return item.size == size;
    }
}

/**
 * Example implementation for a filter which doesn't violate the open close principle.  We can use this to filter by any
 * specification which is passed into the filter function
 */
class BetterFilter implements Filter<Product> {

    @Override
    public Stream<Product> filter(List<Product> items, Specification<Product> spec) {
        return items.stream().filter(spec::isSatisfied);
    }
}

/**
 * This Specification allows us to define a list of specs by which to filter
 * @param <T>
 */
class AndSpecification<T> implements Specification<T> {
    private final List<Specification<T>> specifications;

    public AndSpecification(List<Specification<T>> specifications) {
        this.specifications = specifications;
    }

    @Override
    public boolean isSatisfied(T item) {
        return specifications.stream().allMatch(p -> p.isSatisfied(item));
    }
}

public class OpenClosePrinciple {

    public void runDemo() {
        Product apple = new Product("Apple", Color.GREEN, Size.SMALL);
        Product tree = new Product("Tree", Color.GREEN, Size.LARGE);
        Product house = new Product("House", Color.BLUE, Size.LARGE);

        List<Product> productList = List.of(apple, tree, house);
        ProductFilter pf = new ProductFilter();
        System.out.println("Green Products (old):");
        pf.filterByColor(productList, Color.GREEN).forEach(p -> System.out.println(" - " + p.name + " is green"));

        BetterFilter bf = new BetterFilter();
        System.out.println("Green Products (new):");
        bf.filter(productList, new ColorSpecification(Color.GREEN)).forEach(p -> System.out.println(" - " + p.name + " is green"));

        System.out.println("Large Blue items: ");
        bf.filter(productList, new AndSpecification<>(List.of(
                new ColorSpecification(Color.BLUE),
                new SizeSpecification(Size.LARGE)
        ))).forEach(p -> System.out.println(" - " + p.name + " is Large and Blue"));
    }
}
