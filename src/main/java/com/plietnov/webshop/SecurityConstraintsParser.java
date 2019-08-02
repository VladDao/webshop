package com.plietnov.webshop;

import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

public class SecurityConstraintsParser {

    private static final Logger LOGGER = Logger.getLogger(SecurityConstraintsParser.class);

    private static final String CONSTRAINT_TAG = "constraint";

    private static final String URL_TAG = "url-pattern";

    private static final String ROLE_TAG = "role";

    public static Map<String, List<String>> parse(String filePath) {
        Map<String, List<String>> accessMap = new HashMap<>();
        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(filePath);
            doc.getDocumentElement().normalize();
            NodeList nList = doc.getElementsByTagName(CONSTRAINT_TAG);
            for (int temp = 0; temp < nList.getLength(); temp++) {
                Node nNode = nList.item(temp);
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;
                    String urls = eElement
                            .getElementsByTagName(URL_TAG)
                            .item(0)
                            .getTextContent();
                    String role = eElement
                            .getElementsByTagName(ROLE_TAG)
                            .item(0)
                            .getTextContent();
                    accessMap.put(role, asList(urls));
                }
            }
        } catch (Exception ex) {
            LOGGER.error(ex.getMessage());
        }
        return accessMap;
    }

    private static List<String> asList(String str) {
        List<String> list = new ArrayList<>();
        StringTokenizer st = new StringTokenizer(str);
        while (st.hasMoreTokens()) {
            list.add(st.nextToken());
        }
        return list;
    }
}
