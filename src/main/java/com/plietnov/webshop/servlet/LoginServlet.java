package com.plietnov.webshop.servlet;

import com.plietnov.webshop.Path;
import com.plietnov.webshop.bean.CaptchaBean;
import com.plietnov.webshop.constant.Constants;
import com.plietnov.webshop.entity.User;
import com.plietnov.webshop.mapper.UserUtils;
import com.plietnov.webshop.service.UserService;
import com.plietnov.webshop.strategy.CaptchaStrategy;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Locale;
import java.util.Optional;
import java.util.ResourceBundle;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private static final Logger LOGGER = Logger.getLogger(LoginServlet.class);
    private static final String UPLOAD_DIRECTORY = "images\\avatars";
    private static final UserUtils USER_UTILS = new UserUtils();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Locale locale = new Locale((String) request.getAttribute(Constants.LANGUAGE));
        HttpSession session = request.getSession();
        User userIncoming = USER_UTILS.userMapping(request);
        UserService userService = (UserService) request.getSession().getAttribute(Constants.USER_SERVICE);
        String forward;
        LOGGER.info("Request parameter login ==>" + userIncoming.getLogin());
        Optional<User> user = Optional.ofNullable(((UserService) this.getServletContext().getAttribute(Constants.USER_SERVICE))
                .userAuthorization(userIncoming.getLogin(), userIncoming.getPassword()));
        if (!user.isPresent() || !USER_UTILS.isValidLoginAndPassword(userIncoming)) {
            user.ifPresent(value -> userService.incrementAuthorizationFailedField(value.getId()));
            forward = Path.PAGE_ERROR;
        } else {
            User result = user.get();
            session.setAttribute("user", result);
            session.setAttribute("userAva", UPLOAD_DIRECTORY + "\\" + user.get().getAva());
            LOGGER.info("user:" + user.get().getLogin() + " was found");
            forward = Path.PAGE_MAIN;
        }
        ResourceBundle bundle = ResourceBundle.getBundle("resources", locale);
        session.setAttribute(Constants.ERROR_MESSAGE, bundle.getString("ERROR"));
        request.getRequestDispatcher(forward).forward(request, response);
    }

    public static class CaptchaProcess {
        private static final Logger LOGGER = Logger.getLogger(CaptchaProcess.class);

        public String execute(HttpServletRequest request, HttpServletResponse response) {
            LOGGER.info("Captcha Process start");
            CaptchaBean captchaBean = new CaptchaBean();
            ((CaptchaStrategy) request.getServletContext().getAttribute(Constants.CAPTCHA_STRATEGY))
                    .saveCaptcha(captchaBean, request, response);
            return Path.PAGE_REGISTRATION;
        }
    }
}
