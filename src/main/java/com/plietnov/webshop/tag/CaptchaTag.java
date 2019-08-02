package com.plietnov.webshop.tag;

import com.plietnov.webshop.bean.CaptchaBean;
import com.plietnov.webshop.constant.Constants;
import com.plietnov.webshop.strategy.CaptchaStrategy;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;

public class CaptchaTag extends SimpleTagSupport {

    private static final Logger LOGGER = Logger.getLogger(CaptchaTag.class);

    @Override
    public void doTag() {
        PageContext pageContext = (PageContext) getJspContext();
        JspWriter jspWriter = pageContext.getOut();
        CaptchaBean captchaBean = new CaptchaBean();
        saveMyCaptcha(captchaBean, (HttpServletRequest) pageContext.getRequest(), (HttpServletResponse) pageContext.getResponse());
        StringBuilder result = new StringBuilder();
        result.append("<img style=\"margin-bottom: 10px\" src=\"data:image/png;base64,");
        result.append(captchaBean.getCaptchaImg());
        result.append("\">");
        try {
            jspWriter.write(result.toString());
        } catch (IOException ex) {
            LOGGER.error(ex);
        }
    }

    private void saveMyCaptcha(CaptchaBean captchaBean, HttpServletRequest request, HttpServletResponse response) {
        ((CaptchaStrategy) request.getServletContext().getAttribute(Constants.CAPTCHA_STRATEGY))
                .saveCaptcha(captchaBean, request, response);
    }
}