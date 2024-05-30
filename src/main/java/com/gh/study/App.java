package com.gh.study;

import com.gh.study.model.UserModel;

import java.util.HashMap;
import java.util.Map;

import static com.gh.study.container.Container.random;
import static com.gh.study.container.Container.scanner;

public class App {
    Map<Integer, UserModel> userModelMap = new HashMap<>();

    int userNo = 0;

    public void run() {
        while(true) {
            System.out.print("command) ");
            String cmd = scanner.nextLine();

            action(cmd);
        }
    }

    private void action(String cmd) {
        if(cmd.equals("/app/start")) {
            System.out.println("===== start! =====");

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
                    System.out.println("===== stop! =====");
                    break;
                } else {
                    System.out.println("===== unknown choice! =====");
                }

            }
        } else if(cmd.equals("/app/ranking")) {
            System.out.println("===== ranking! =====");
        } else if(cmd.equals("/usr/join")) {
            System.out.println("===== join! =====");

            String userId;
            String userPw;
            String userNickname;

            while(true) {
                System.out.print("ID) ");
                userId = scanner.nextLine();

                boolean hasId = false;

                for(Integer no : userModelMap.keySet()) {
                    UserModel user = userModelMap.get(no);
                    if(userId.equals(user.getUserId())) {
                        hasId = true;
                        break;
                    }
                }

                if(hasId) {
                    System.out.println("===== ID is already existent! =====");
                } else {
                    break;
                }
            }

            while(true) {
                System.out.print("PASSWORD) ");
                userPw = scanner.nextLine();

                System.out.print("check PASSWORD) ");
                String check_userPw = scanner.nextLine();

                if(userPw.equals(check_userPw)) {
                    break;
                } else {
                    System.out.println("===== PASSWORD does not match! =====");
                }
            }

            while(true) {
                System.out.print("NICKNAME) ");
                userNickname = scanner.nextLine();

                boolean hasNickname = false;

                for(Integer no : userModelMap.keySet()) {
                    UserModel user = userModelMap.get(no);
                    if(userNickname.equals(user.getUserNickname())) {
                        hasNickname = true;
                        break;
                    }
                }

                if(hasNickname) {
                    System.out.println("===== NICKNAME is already existent! =====");
                } else {
                    break;
                }
            }

            UserModel userModel = new UserModel(++userNo, userId, userPw, userNickname);
            userModelMap.put(userNo, userModel);

            System.out.println("===== success! =====");

            for(Integer no : userModelMap.keySet()) {
                UserModel user = userModelMap.get(no);
                System.out.printf("%d | %s | %s | %s\n", user.getUserNo(), user.getUserId(), user.getUserPw(), user.getUserNickname());
            }
        } else if(cmd.equals("/usr/login")) {
            System.out.println("===== login! =====");
        } else if(cmd.equals("/usr/edit_profile")) {
            System.out.println("===== edit profile! =====");
        } else if(cmd.equals("/usr/logout")) {
            System.out.println("===== logout! =====");
        } else if(cmd.equals("/app/exit")) {
            System.out.print("===== exit! =====");
            scanner.close();
            System.exit(0);
        } else {
            System.out.println("===== unknown command! =====");
        }
    }
}
