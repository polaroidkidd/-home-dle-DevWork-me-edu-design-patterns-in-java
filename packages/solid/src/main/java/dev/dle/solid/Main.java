package dev.dle.solid;

import dev.dle.solid.ocp.OpenClosePrinciple;
import dev.dle.solid.srp.SingleResponsibilityPrincipleDemo;

public class Main {

    public static void main(String[] args) {
//        SingleResponsibilityPrincipleDemo srp = new SingleResponsibilityPrincipleDemo();
//        srp.runDemo();
        OpenClosePrinciple ocp = new OpenClosePrinciple();
        ocp.runDemo();
    }
}