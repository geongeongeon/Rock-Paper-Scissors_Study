package com.gh.study.view;

import com.gh.study.model.UserModel;
import com.gh.study.session.UserSession;

import java.util.List;
import java.util.Map;

import static com.gh.study.container.Container.*;

public class UserView {
    //여기부터 명령어 별 메서드
    public void userJoin() {
        if(!UserSession.getIsLogin()) {
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
        if(!UserSession.getIsLogin()) {
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
        if(UserSession.getIsLogin()) {
            System.out.println(UserSession.getLoginUser());
        } else {
            System.out.println("===== login first! =====");
        }
    }

    public void userModify() {
        if (!UserSession.getIsLogin()) {
            System.out.println("===== login first! =====");
            return;
        }

        Map<String, String> checkIdPwMap = rq.getParams();

        if (!modifyController.checkContainsKeyIdAndPw(checkIdPwMap)) {
            System.out.println("===== need your ID and PASSWORD! =====");
            return;
        }

        if (!modifyController.checkEqualIdAndPw(checkIdPwMap)) {
            System.out.println("===== ID or PASSWORD is not matched! ======");
            return;
        }

        System.out.println("===== modify! =====");

        while (true) {
            String whatChange = getUserInput("change");

            if (checkHomeCommand(whatChange)) {
                return;
            }

            String checkWhatChange = modifyController.checkWhatChange(whatChange);
            if (checkWhatChange == null) {
                System.out.println("===== unknown command! =====");
                continue;
            }

            processChange(checkWhatChange);
        }
    }

    public void userLogout() {
        if(UserSession.getIsLogin()) {
            System.out.println("===== success! =====");
            logoutController.setUserSession();
        } else {
            System.out.println("===== login first! =====");
        }
    }

    public void userList() {
        List<UserModel> userList = listController.getUserInfo();
        for(UserModel user : userList) {
            System.out.printf("%d | %s | %s | %s\n", user.getUserNo(), user.getUserId(), user.getUserPw(), user.getUserNickname());
        }
    }

    //여기부터 중복 제거를 위한 메서드
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

    // 적절한 처리 메서드 호출을 위한 분기 처리
    private void processChange(String checkWhatChange) {
        switch (checkWhatChange) {
            case "id":
                System.out.println("===== can't change ID! =====");
                break;

            case "pw":
                processPasswordChange();
                break;

            case "nickname":
                processNicknameChange();
                break;
        }
    }

    // 비밀번호 변경 처리
    private void processPasswordChange() {
        while (true) {
            String newPassword = getUserInput("new PASSWORD");
            String checkNewPassword = getUserInput("check new PASSWORD");

            if (modifyController.checkEqualPwAndNewPw(newPassword, checkNewPassword)) {
                System.out.println("===== success! =====");
                modifyController.changeUserPw(newPassword);
                break;
            } else {
                System.out.println("===== PASSWORD does not match! =====");
            }
        }
    }

    // 닉네임 변경 처리
    private void processNicknameChange() {
        while (true) {
            String newNickname = getUserInput("new NICKNAME");

            if (modifyController.checkHasNickname(newNickname)) {
                System.out.println("===== NICKNAME does already exist! =====");
            } else {
                System.out.println("===== success! =====");
                modifyController.changeUserNickname(newNickname);
                break;
            }
        }
    }
}