package dev.dle.solid.dip;


import org.javatuples.Triplet;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

enum Relationship {
    PARENT,
    CHILD,
    SIBLING
}

class Person {
    public String name;

    public Person(String name) {
        this.name = name;
    }


}

/**
 * Low Level - Doesn't have any business logic
 */
class Relationships implements RelationshipBrowser {
    public List<Triplet<Person, Relationship, Person>> getRelations() {
        return relations;
    }

    private List<Triplet<Person, Relationship, Person>> relations = new ArrayList<>();


    public void addParentAndChild(Person parent, Person child) {
        relations.add(new Triplet<>(parent, Relationship.PARENT, child));
        relations.add(new Triplet<>(child, Relationship.CHILD, parent));
    }

    @Override
    public List<Person> findAllChildrenOf(String name) {
        return relations.stream()
                .filter(x -> x.getValue0().name.equals("John") && x.getValue1() == Relationship.PARENT)
                .map(Triplet::getValue2)
                .collect(Collectors.toList());
    }
}

/**
 * High Level - allows us to perform operations on low level constructs, is closer to the end user
 * This should not depend on low level modules (which it does because it takes Relations as a constructor dependency
 */
class Research {
//    public Research(Relationships relationships) {
//        List<Triplet<Person, Relationship, Person>> relations = relationships.getRelations();
//        relations.stream()
//                .filter(x -> x.getValue0().name.equals("John") && x.getValue1() == Relationship.PARENT)
//                .forEach(ch -> System.out.println("John has a child called " + ch.getValue2().name));
//    }

    public Research(RelationshipBrowser browser){
        List<Person> allChildrenOf = browser.findAllChildrenOf("John");
        for (Person person : allChildrenOf) {
            System.out.println("John has a child called " + person.name);
        }
    }
}

public class DependencyInversionPrinciple {
    public void runDemo(boolean shouldRun) {
        if (shouldRun) {
            Person parent = new Person("John");
            Person child1 = new Person("Chris");
            Person child2 = new Person("Matt");
            Relationships relationships = new Relationships();
            relationships.addParentAndChild(parent, child1);
            relationships.addParentAndChild(parent, child2);
            Research research = new Research(relationships);


        }

    }
}

/**
 * Abstraction that the low level module should implement
 */
interface RelationshipBrowser {
    List<Person> findAllChildrenOf(String name);
}
