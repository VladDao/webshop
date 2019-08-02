package com.plietnov.webshop.strategy;

import com.plietnov.webshop.bean.CaptchaBean;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class CaptchaStrategySession extends CaptchaStrategy {

    private static final int COOKIE_LIFETIME_SEC = 60 * 30;
    private HttpSession session;

    @Override
    public void saveCaptcha(CaptchaBean captchaBean, HttpServletRequest request, HttpServletResponse response) {
        session = request.getSession();
        session.setAttribute(captchaBean.getCaptchaId(), captchaBean.getCaptchaText());
        request.setAttribute(CAPTCHA_VALUE, captchaBean.getCaptchaImg());
        Cookie cookie = new Cookie(CAPTCHA, captchaBean.getCaptchaId());
        cookie.setMaxAge(COOKIE_LIFETIME_SEC);
        response.addCookie(cookie);
    }

    @Override
    public boolean checkCaptcha(HttpServletRequest request, HttpServletResponse response) {
        session = request.getSession();
        String captchaId = StringUtils.EMPTY;
        for (Cookie c : request.getCookies()) {
            if (c.getName().equals(CAPTCHA)) {
                captchaId = c.getValue();
            }
        }
        String formRequest = request.getParameter(USER_CAPTCHA);
        String fromSession = (String) session.getAttribute(captchaId);
        return formRequest.equals(fromSession);
    }

    @Override
    public void captchaRemove(String captchaName) {
        session.removeAttribute(captchaName);
    }
}
