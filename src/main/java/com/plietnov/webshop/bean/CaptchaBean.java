package com.plietnov.webshop.bean;

import org.javalite.activeweb.Captcha;

import java.io.Serializable;
import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.Base64;

public class CaptchaBean implements Serializable {

    private String captchaId;
    private String captchaText;
    private String captchaImg;
    private LocalDateTime localDateTime;

    public CaptchaBean() {
        newCaptcha();
    }

    public String getCaptchaId() {
        return captchaId;
    }

    public void setCaptchaId(String captchaId) {
        this.captchaId = captchaId;
    }

    public String getCaptchaText() {
        return captchaText;
    }

    public void setCaptchaText(String captchaText) {
        this.captchaText = captchaText;
    }

    public String getCaptchaImg() {
        return captchaImg;
    }

    public void setCaptchaImg(String captchaImg) {
        this.captchaImg = captchaImg;
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public void setLocalDateTime(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }

    private void newCaptcha() {
        captchaId = getRandom();
        captchaText = Captcha.generateText();
        captchaImg = Base64.getEncoder().encodeToString(Captcha.generateImage(captchaText));
        localDateTime = LocalDateTime.now();
    }

    private String getRandom() {
        SecureRandom random = new SecureRandom();
        return String.valueOf(random.nextInt(Integer.MAX_VALUE));
    }
}
