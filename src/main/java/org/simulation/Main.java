package org.simulation;

import java.util.Scanner;

public class Main {
    private static boolean acceptRules = false;

    public static void main(String[] args) throws InterruptedException {
        Simulation simulation = new Simulation();
        Scanner scanner = new Scanner(System.in);


        printGuide(scanner);
        Thread menu = new Thread(new Runnable() {
            @Override
            public void run() {
                String input = "";
                System.out.println("Enter S for start");

                while (true) {
                    input = scanner.nextLine().toLowerCase();
                    if ("s".equals(input)) {
                        simulation.setPause(false);
                    }
                    if ("p".equals(input)) {
                        simulation.setPause(true);
                    }
                    if ("n".equals(input)) {
                        simulation.nextTurn();
                    }
                }
            }
        });

        while (acceptRules) {
            menu.start();
            simulation.startSimulation();
            menu.interrupt();
            scanner.close();
            System.exit(0);
        }
    }

    private synchronized static void printGuide(Scanner scanner) {
        System.out.println("For simulation control, you can enter on the keyboard: \n" +
                "s - for start simulation. \n" +
                "p - for pause simulation. \n" +
                "n - for turn next step of simulation \n" +
                "\n" +
                "Input YES, if everything is understandable");


        String input = "";
        while (!acceptRules) {
            input = scanner.nextLine().toLowerCase();
            if ("yes".equals(input)) {
                acceptRules = true;
            }
        }
    }
}
