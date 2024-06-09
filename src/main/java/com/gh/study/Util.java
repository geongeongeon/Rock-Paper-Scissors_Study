package com.gh.study;

import java.util.HashMap;
import java.util.Map;

public class Util {
    //ex) cmd = /usr/modify?id=testId4&pw=testPw4
    //ex) cmd = /app/ranking?nickname=testNickname4
    public static Map<String, String> getParamsFromUrl(String url) {
        Map<String, String> params = new HashMap<>();

        String[] splitQuestionMark = url.split("\\?", 2);
        if(splitQuestionMark.length == 1) {
            return params;
        }

        String[] splitAmpersand = splitQuestionMark[1].split("&", 2);
        if(splitAmpersand.length == 1) {
            String[] length1param = splitAmpersand[0].split("=", 2);
            if(length1param.length == 1) {
                return params;
            }
            params.put(length1param[0], length1param[1]);
            return params;
        }

        for(String splitEqualSign : splitAmpersand) {
            String[] splitResult = splitEqualSign.split("=", 2);
            if(splitResult.length == 1) {
                continue;
            }
            params.put(splitResult[0], splitResult[1]);
        }
        return params;
    }

    public static String getUrlPathFromUrl(String url) {
        return url.split("\\?", 2)[0];
    }
}
