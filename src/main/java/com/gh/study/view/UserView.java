package com.gh.study.view;

import com.gh.study.App;
import com.gh.study.model.UserModel;
import com.gh.study.session.UserSession;

import java.util.HashMap;
import java.util.Map;

import static com.gh.study.container.Container.scanner;
import static com.gh.study.container.Container.userModelMap;

public class UserView {
    public void userJoin() {
        System.out.println("===== join! =====");

        String userId;
        String userPw;
        String userNickname;

        while(true) {
            System.out.print("ID) ");
            userId = scanner.nextLine();

            boolean hasId = false;

            for(Integer userNo : userModelMap.keySet()) {
                UserModel user = userModelMap.get(userNo);
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

            for(Integer userNo : userModelMap.keySet()) {
                UserModel user = userModelMap.get(userNo);
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
        int userNextNo = App.getUserNextNo();

        UserModel userModel = new UserModel(userNextNo, userId, userPw, userNickname);
        userModelMap.put(userNextNo, userModel);

        App.setUserNextNo(++userNextNo);

        System.out.println("===== success! =====");
    }

    public void userLogin() {
        System.out.println("===== login! =====");

        String userId;
        String userPw;
        int key_loginUser = 0;

        while(true) {
            System.out.print("ID) ");
            userId = scanner.nextLine();

            if(userId.equals("/home")) {
                System.out.println("===== home! =====");
                break;
            }

            boolean hasId = false;

            for(Integer userNo : userModelMap.keySet()) {
                UserModel user = userModelMap.get(userNo);

                if(userId.equals(user.getUserId())) {
                    hasId = true;
                    key_loginUser = user.getUserNo();
                    break;
                }
            }

            if(hasId) {
                while(true) {
                    System.out.print("PASSWORD) ");
                    userPw = scanner.nextLine();

                    if(userPw.equals("/home")) {
                        System.out.println("===== home! =====");
                        return;
                    }

                    UserModel user = userModelMap.get(key_loginUser);
                    String checkPw = user.getUserPw();

                    if(userPw.equals(checkPw)) {
                        UserSession.setIsLogin(true);
                        UserSession.setLoginUser(userModelMap.get(key_loginUser));

                        System.out.println("===== success! =====");
                        break;
                    } else {
                        System.out.println("===== failed! =====");
                    }
                }
                break;
            } else {
                System.out.println("===== non-existent ID! ===== ");
            }
        }
    }

    public void userWhoami() {
        UserModel loginUser = UserSession.getLoginUser();
        System.out.println(loginUser);
    }

    public void userModify(String cmd) {
        boolean isLogin = UserSession.getIsLogin();
        UserModel loginUser = UserSession.getLoginUser();

        if(!isLogin) {
            System.out.println("===== login first! =====");
        } else {
            String cmdSplit = cmd.split("\\?")[1];
            String[] splitAnd = cmdSplit.split("&", 2);

            Map<String, String> checkIdPwMap = new HashMap<>();

            for(String param : splitAnd) {
                String[] splitEqual = param.split("=", 2);
                String key_checkIdPwMap = splitEqual[0];
                String value_checkIdPwMap = splitEqual[1];
                checkIdPwMap.put(key_checkIdPwMap, value_checkIdPwMap);
            }

            boolean hasKey_id = false;
            boolean hasKey_pw = false;

            for(String key : checkIdPwMap.keySet()) {
                if(key.equals("id")) {
                    hasKey_id = true;
                } else if (key.equals("pw")) {
                    hasKey_pw = true;
                }
            }
            if(hasKey_id && hasKey_pw) {
                System.out.println("===== modify! =====");

                if(checkIdPwMap.get("id").equals(loginUser.getUserId()) && checkIdPwMap.get("pw").equals(loginUser.getUserPw())) {
                    while(true) {
                        System.out.print("change) ");
                        String whatChange = scanner.nextLine();

                        if(whatChange.equals("id")) {
                            System.out.println("===== can't change ID! =====");
                        } else if(whatChange.equals("pw")) {
                            while(true) {
                                System.out.print("new PASSWORD) ");
                                String newPassword = scanner.nextLine();

                                System.out.print("check new PASSWORD) ");
                                String check_newPassword = scanner.nextLine();

                                if(newPassword.equals(check_newPassword)) {
                                    System.out.println("===== success! =====");
                                    loginUser.setUserPw(newPassword);
                                    break;
                                } else {
                                    System.out.println("===== PASSWORD does not match! =====");
                                }
                            }
                        } else if(whatChange.equals("nickname")) {
                            while(true) {
                                System.out.print("new NICKNAME) ");
                                String newNickname = scanner.nextLine();

                                boolean hasNewNickname = false;

                                for(Integer userNo : userModelMap.keySet()) {
                                    UserModel user = userModelMap.get(userNo);
                                    if(newNickname.equals(user.getUserNickname())) {
                                        hasNewNickname = true;
                                    }
                                }

                                if(hasNewNickname) {
                                    System.out.println("===== NICKNAME does already existent! =====");
                                } else {
                                    System.out.println("===== success! =====");
                                    loginUser.setUserNickname(newNickname);
                                    break;
                                }
                            }
                        } else if(whatChange.equals("/home")) {
                            System.out.println("===== home! =====");
                            return;
                        } else {
                            System.out.println("===== unknown command! =====");
                        }
                    }
                } else {
                    System.out.println("===== ID or PASSWORD is not matched! ======");
                }
            } else {
                System.out.println("===== unknown command! =====");
            }
        }
    }

    public void userLogout() {
        boolean isLogin = UserSession.getIsLogin();
        UserModel loginUser = UserSession.getLoginUser();
        if(!isLogin) {
            System.out.println("===== login first! =====");
        } else {
            System.out.println("===== success! =====");
            UserSession.setIsLogin(false);
            UserSession.setLoginUser(loginUser);
        }
    }

    public void userList() {
        for(Integer userNo : userModelMap.keySet()) {
            UserModel user = userModelMap.get(userNo);
            System.out.printf("%d | %s | %s | %s\n", user.getUserNo(), user.getUserId(), user.getUserPw(), user.getUserNickname());
        }
    }
}
