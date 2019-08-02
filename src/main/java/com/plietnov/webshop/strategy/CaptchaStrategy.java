package com.plietnov.webshop.strategy;

import com.plietnov.webshop.bean.CaptchaBean;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.Serializable;

public abstract class CaptchaStrategy implements Serializable {

    protected static final String CAPTCHA_VALUE = "captchaValue";
    protected static final String CAPTCHA = "captchaKey";
    protected static final String USER_CAPTCHA = "userCaptcha";

    public abstract void saveCaptcha(CaptchaBean request, HttpServletRequest response, HttpServletResponse httpServletResponse);

    public abstract boolean checkCaptcha(HttpServletRequest request, HttpServletResponse response);

    public abstract void captchaRemove(String captchaName);

    public boolean isContainsCaptcha(HttpServletRequest request) {
        return request.getParameter(USER_CAPTCHA) != null;
    }
}


