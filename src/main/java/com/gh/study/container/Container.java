package com.gh.study.container;

import java.util.Random;
import java.util.Scanner;

public class Container {

    public static Scanner scanner;
    public static Random random;

    static {
        scanner = new Scanner(System.in);
        random = new Random();
    }
}
