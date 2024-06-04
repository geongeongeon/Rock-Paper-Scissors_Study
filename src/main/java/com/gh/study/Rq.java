package com.gh.study;

import java.util.Map;

public class Rq {
    private Map<String, String> params;
    private String urlPath;

    public void setCommand(String url) {
        params = Util.getParamsFromUrl(url);
        urlPath = Util.getUrlPathFromUrl(url);
    }

    public Map<String, String> getParams() {
        return params;
    }

    public String getUrlPath() {
        return urlPath;
    }
}
