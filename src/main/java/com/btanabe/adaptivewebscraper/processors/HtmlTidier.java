package com.btanabe.adaptivewebscraper.processors;

import org.w3c.dom.Document;
import org.w3c.tidy.Tidy;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

/**
 * Created by Brian on 4/15/16.
 */
public class HtmlTidier {
    private static Tidy htmlTidy = new Tidy();

    static {
        htmlTidy.setQuiet(true);
        htmlTidy.setShowWarnings(false);
        htmlTidy.setInputEncoding("UTF-8");
        htmlTidy.setOutputEncoding("UTF-8");
        htmlTidy.setXHTML(true);
        htmlTidy.setMakeClean(true);
        htmlTidy.setWrapSection(false);
        htmlTidy.setWraplen(Integer.MAX_VALUE);
        htmlTidy.setOnlyErrors(true);
        htmlTidy.setFixBackslash(true);
    }

    public static String tidyHtmlAndConvertToXhtml(String dirtyHtml) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        Document xmlDocument = htmlTidy.parseDOM(new ByteArrayInputStream(dirtyHtml.getBytes()), outputStream);
        htmlTidy.pprint(xmlDocument, outputStream);
        return replaceAllNbspWithActualSpaces(new String(outputStream.toByteArray()));

    }

    private static String replaceAllNbspWithActualSpaces(String inputString) {
        return inputString.replaceAll("&nbsp;", " ");
    }
}
