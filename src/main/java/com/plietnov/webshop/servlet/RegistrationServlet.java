package com.plietnov.webshop.servlet;

import com.plietnov.webshop.Path;
import com.plietnov.webshop.constant.Constants;
import com.plietnov.webshop.entity.User;
import com.plietnov.webshop.mapper.UserUtils;
import com.plietnov.webshop.service.UserService;
import com.plietnov.webshop.strategy.CaptchaStrategy;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;

@WebServlet("/Registration")
@MultipartConfig(fileSizeThreshold = RegistrationServlet.FILE_SIZE_THRESHOLD,
        maxFileSize = RegistrationServlet.MAX_FILE_SIZE,
        maxRequestSize = RegistrationServlet.MAX_REQUEST_SIZE)
public class RegistrationServlet extends HttpServlet {
    private static final Logger LOGGER = Logger.getLogger(RegistrationServlet.class);
    static final int FILE_SIZE_THRESHOLD = 1024 * 1024;
    static final int MAX_FILE_SIZE = 1024 * 1024 * 5;
    static final int MAX_REQUEST_SIZE = 1024 * 1024 * 5 * 5;
    private static final String UPLOAD_DIRECTORY = "images\\avatars";
    private UserUtils userUtils = new UserUtils();
    private UserService userService;
    private String uploadPath;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LOGGER.info("Registration start process");
        uploadPath = request.getServletContext().getRealPath("") + File.separator + UPLOAD_DIRECTORY;
        HttpSession session = request.getSession();
        User user = userUtils.userMapping(request);
        String forward;
        if (!((CaptchaStrategy) this.getServletContext().getAttribute(Constants.CAPTCHA_STRATEGY))
                .isContainsCaptcha(request) || isInvalidInput(request, response, user)) {
            forward = getNewCaptcha(request, response);
        } else {
            userService = (UserService) this.getServletContext().getAttribute(Constants.USER_SERVICE);
            String avaURI = uploadPath(user.getName(), request);
            user.setAva(avaURI);
            userService.addUser(user);
            LOGGER.info("User with login:" + user.getLogin() + " added");
            session.setAttribute(Constants.USER, user);
            session.setAttribute("userAva", UPLOAD_DIRECTORY + "\\" + user.getAva());
            forward = Path.PAGE_MAIN;
        }
        request.getRequestDispatcher(forward).forward(request, response);
    }

    private boolean isInvalidInput(HttpServletRequest request, HttpServletResponse response, User user) {
        CaptchaStrategy cs = ((CaptchaStrategy) request.getServletContext()
                .getAttribute(Constants.CAPTCHA_STRATEGY));
        UserService us = ((UserService) request.getServletContext().getAttribute(Constants.USER_SERVICE));
        LOGGER.info(!cs.checkCaptcha(request, response));
        LOGGER.info(!userUtils.userIsValid(user));
        LOGGER.info(us.isUserExist(user));
        return !cs.checkCaptcha(request, response) ||
                !userUtils.userIsValid(user) ||us.isUserExist(user);
    }

    private String getNewCaptcha(HttpServletRequest request, HttpServletResponse response) {
        User user = new User();
        request.setAttribute(Constants.USER_LOGIN_RESPONSE, user.getLogin());
        request.setAttribute(Constants.USER_NAME_RESPONSE, user.getName());
        request.setAttribute(Constants.USER_PHONE_RESPONSE, user.getPhoneNumber());
        request.setAttribute(Constants.USER_EMAIL_RESPONSE, user.getEmail());
        request.getSession().setAttribute(Constants.USER, user);
        return new LoginServlet.CaptchaProcess().execute(request, response);
    }

    private String uploadPath(String userName, HttpServletRequest request) {
        File uploadDir = new File(uploadPath);
        String fileName = StringUtils.EMPTY;
        if (!uploadDir.exists()) uploadDir.mkdir();
        try {
            Part part = request.getPart("userAvatar");
            fileName = getFileName(part);
            part.write(uploadPath + File.separator + fileName);
        } catch (IOException | ServletException e) {
            LOGGER.info(e);
        }
        return fileName;
    }

    private String getFileName(Part part) {
        for (String content : part.getHeader("content-disposition").split(";")) {
            if (content.trim().startsWith("filename"))
                return content.substring(content.indexOf("=") + 2, content.length() - 1);
        }
        return "def.jpg";
    }
}
