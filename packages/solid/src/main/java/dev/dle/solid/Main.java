package dev.dle.solid;

import dev.dle.solid.isp.InterfaceSegregationPrinciple;
import dev.dle.solid.lsp.LiskovSubstitutionPrinciple;
import dev.dle.solid.ocp.OpenClosePrinciple;
import dev.dle.solid.srp.SingleResponsibilityPrincipleDemo;

public class Main {

    public static void main(String[] args) {
//        new SingleResponsibilityPrincipleDemo().runDemo();
//        new OpenClosePrinciple().runDemo();
//        new LiskovSubstitutionPrinciple().runDemo();
        new InterfaceSegregationPrinciple().runDemo();

    }
}