package com.plietnov.webshop.thread;

import com.plietnov.webshop.bean.CaptchaBean;
import com.plietnov.webshop.constant.Constants;
import com.plietnov.webshop.strategy.CaptchaStrategy;
import org.apache.log4j.Logger;

import javax.servlet.ServletContext;
import java.time.LocalDateTime;
import java.util.List;

public class CaptchaMonitorThread extends Thread {

    private static final Logger LOGGER = Logger.getLogger(CaptchaMonitorThread.class);
    private static final long TIMEOUT_MIN = 30;
    private static final long SLEEP_MILLIS = 60_1000;
    private ServletContext sc;

    public CaptchaMonitorThread(ServletContext context) {
        this.sc = context;
    }

    @Override
    public void run() {
        boolean work = true;
        while (work) {
            ((List<CaptchaBean>) sc.getAttribute(Constants.CAPTCHA_LIST))
                    .stream().filter(captchaBean -> (captchaBean.getLocalDateTime()
                    .isBefore(LocalDateTime.now().minusMinutes(TIMEOUT_MIN))))
                    .forEach(captchaBean -> ((CaptchaStrategy) sc.getAttribute(Constants.CAPTCHA_STRATEGY))
                            .captchaRemove(captchaBean.getCaptchaId()));
            try {
                sleep(SLEEP_MILLIS);
            } catch (InterruptedException e) {
                work = false;
                LOGGER.error(e);
            }
        }
    }
}
