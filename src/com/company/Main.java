package com.company;

public class Main {

    public static void main(String[] args) {
        Runda zero = new Runda();
        zero.pierwsza();
        zero.printAll();
        Runda jeden = zero.iteracja(zero);
        jeden.printAll();
	// write your code here
    }
}
