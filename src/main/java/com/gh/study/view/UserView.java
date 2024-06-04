package com.gh.study.view;

import com.gh.study.model.UserModel;
import com.gh.study.session.UserSession;

import java.util.HashMap;
import java.util.Map;

import static com.gh.study.container.Container.*;

public class UserView {
    UserModel loginUser;

    public void userJoin() {
        if(!isLogin()) {
            System.out.println("===== join! =====");

            String userId = getUserIdInput();
            String userPw = getUserPasswordInput();
            String userNickname = getUserNicknameInput();

            joinController.join(userId, userPw, userNickname);
            System.out.println("===== success! =====");
        } else {
            System.out.println("===== logout first! =====");
        }
    }

    public void userLogin() {
        if(!isLogin()) {
            System.out.println("===== login! =====");

            while(true) {
                String userId = getUserInput("ID");

                if(checkHomeCommand(userId)) {
                    return;
                }

                Integer key_loginUser = loginController.checkHasIdAndGetKey(userId);

                if(key_loginUser != null) {
                    if(checkUserPw(key_loginUser)) {
                        System.out.println("===== success! =====");
                        break;
                    } else {
                            System.out.println("===== failed! =====");
                    }
                } else {
                    System.out.println("===== non-existent ID! ===== ");
                }
            }
        } else {
            System.out.println("===== logout first! =====");
        }
    }

    public void userWhoami() {
        if(isLogin()) {
            System.out.println(loginUser);
        } else {
            System.out.println("===== login first! =====");
        }
    }

    public void userModify(String cmd) {
        if(isLogin()) {
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
                        String whatChange = getUserInput("change");

                        if(whatChange.equals("id")) {
                            System.out.println("===== can't change ID! =====");
                        } else if(whatChange.equals("pw")) {
                            while(true) {
                                String newPassword = getUserInput("new PASSWORD");
                                String check_newPassword = getUserInput("check new PASSWORD");

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
                                String newNickname = getUserInput("new NICKNAME");

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
                        } else if(checkHomeCommand("/home")) {
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
        } else {
            System.out.println("===== login first! =====");
        }
    }

    public void userLogout() {
        if(isLogin()) {
            System.out.println("===== success! =====");
            UserSession.setIsLogin(false);
            UserSession.setLoginUser(null);
        } else {
            System.out.println("===== login first! =====");
        }
    }

    public void userList() {
        for(Integer userNo : userModelMap.keySet()) {
            UserModel user = userModelMap.get(userNo);
            System.out.printf("%d | %s | %s | %s\n", user.getUserNo(), user.getUserId(), user.getUserPw(), user.getUserNickname());
        }
    }

    private boolean isLogin() {
        if(UserSession.getIsLogin()) {
            loginUser = UserSession.getLoginUser();
            return true;
        } else {
            loginUser = null;
            return false;
        }
    }

    private String getUserIdInput() {
        while (true) {
            String userId = getUserInput("ID");

            if (joinController.checkHasId(userId)) {
                System.out.println("===== ID is already existent! =====");
            } else {
                return userId;
            }
        }
    }

    private String getUserPasswordInput() {
        while (true) {
            String userPw = getUserInput("PASSWORD");
            String check_userPw = getUserInput("check PASSWORD");

            if (joinController.checkEqualPw(userPw, check_userPw)) {
                return userPw;
            } else {
                System.out.println("===== PASSWORD does not match! =====");
            }
        }
    }

    private String getUserNicknameInput() {
        while (true) {
            String userNickname = getUserInput("NICKNAME");

            if (joinController.checkHasNickname(userNickname)) {
                System.out.println("===== NICKNAME is already existent! =====");
            } else {
                return userNickname;
            }
        }
    }

    private String getUserInput(String input) {
        System.out.print(input + ") ");
        return scanner.nextLine();
    }

    private boolean checkHomeCommand(String input) {
        if (input.equals("/home")) {
            System.out.println("===== home! =====");
            return true;
        }
        return false;
    }

    private boolean checkUserPw(Integer key_loginUser) {
        while(true) {
            String userPw = getUserInput("PASSWORD");

            if(checkHomeCommand(userPw)) {
                return false;
            }

            String checkPw = loginController.getKeyLoginUserPw(key_loginUser);
            boolean checkEqualPw = loginController.checkEqualPw(userPw, checkPw);

            if(checkEqualPw) {
                loginController.changeSession(key_loginUser);
                return true;
            } else {
                System.out.println("===== PASSWORD does not match! =====");
            }
        }
    }
}