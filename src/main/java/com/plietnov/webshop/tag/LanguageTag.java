package com.plietnov.webshop.tag;

import org.apache.log4j.Logger;

import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;

public class LanguageTag extends TagSupport {

    private static final Logger LOGGER = Logger.getLogger(LanguageTag.class);

    @Override
    public int doStartTag() {
        try {
            StringBuilder tag = new StringBuilder();
            tag.append("<form id='en' method='get' class='left'>");
            tag.append("<input type='hidden' name='language' value='en'>");
            tag.append("<input type='submit' value='' class='en_background'>");
            tag.append("</form>");
            tag.append("<form id='ru' method='get'>");
            tag.append("<input type='hidden' name='language' value='ru'>");
            tag.append("<input type='submit' value='' class='ru_background'>");
            tag.append("</form>");
            pageContext.getOut().write(tag.toString());
        } catch (IOException ex) {
            LOGGER.error(ex.getMessage());
        }
        return SKIP_BODY;
    }
}