package com.gh.study;

import static com.gh.study.container.Container.random;
import static com.gh.study.container.Container.scanner;

public class App {

    public void run() {

        while(true) {
            System.out.print("command) ");
            String cmd = scanner.nextLine();

            action(cmd);
        }

    }

    private void action(String cmd) {
        if(cmd.equals("/app/start")) {
            System.out.println("start!");

            while(true) {
                System.out.print("you) ");
                String yourChoice = scanner.nextLine();

                int computerChoice = random.nextInt(3) + 1;

                //1:rock 2:scissor 3:paper
                if(yourChoice.equals("rock")) {
                    switch(computerChoice) {
                        case 1 :
                            System.out.println("computer) rock");
                            System.out.println("===== draw! =====");
                            break;
                        case 2 :
                            System.out.println("computer) scissor");
                            System.out.println("===== win! =====");
                            break;
                        case 3 :
                            System.out.println("computer) paper");
                            System.out.println("===== lose! =====");
                            break;
                    }
                } else if(yourChoice.equals("scissor")) {
                    switch(computerChoice) {
                        case 1:
                            System.out.println("computer) rock");
                            System.out.println("===== lose! =====");
                            break;
                        case 2:
                            System.out.println("computer) scissor");
                            System.out.println("===== draw! =====");
                            break;
                        case 3:
                            System.out.println("computer) paper");
                            System.out.println("===== win! =====");
                            break;
                    }
                } else if(yourChoice.equals("paper")) {
                    switch(computerChoice) {
                        case 1:
                            System.out.println("computer) rock");
                            System.out.println("===== win! =====");
                            break;
                        case 2:
                            System.out.println("computer) scissor");
                            System.out.println("===== lose! =====");
                            break;
                        case 3:
                            System.out.println("computer) paper");
                            System.out.println("===== draw! =====");
                            break;
                    }
                } else if(yourChoice.equals("/stop")) {
                    System.out.println("stop!");
                    break;
                } else {
                    System.out.println("check your choice!");
                }

            }
        } else if(cmd.equals("/app/ranking")) {
            System.out.println("ranking!");
        } else if(cmd.equals("/usr/join")) {
            System.out.println("join!");
        } else if(cmd.equals("/usr/login")) {
            System.out.println("login!");
        } else if(cmd.equals("/usr/edit_profile")) {
            System.out.println("edit profile!");
        } else if(cmd.equals("/usr/logout")) {
            System.out.println("logout!");
        } else if(cmd.equals("/app/exit")) {
            System.out.println("exit!");
            scanner.close();
            System.exit(0);
        } else {
            System.out.println("unknown command!");
        }
    }
}
