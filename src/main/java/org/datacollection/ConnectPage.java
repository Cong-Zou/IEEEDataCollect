package org.datacollection;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.NicelyResynchronizingAjaxController;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

import org.apache.commons.logging.LogFactory;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;


import java.io.IOException;
import java.util.logging.Level;


public class ConnectPage {

    public static Document GetWebContent(String url) throws IOException {

        WebClient webClient = new WebClient(BrowserVersion.CHROME);

//        LogFactory.getFactory().setAttribute("org.apache.commons.logging.Log",
//                "org.apache.commons.logging.impl.NoOpLog");
//        java.util.logging.Logger.getLogger("com.gargoylesoftware").setLevel(Level.OFF);

        webClient.getOptions().setThrowExceptionOnScriptError(false);
        webClient.getOptions().setThrowExceptionOnFailingStatusCode(false);
        webClient.getOptions().setActiveXNative(false);
        webClient.getOptions().setCssEnabled(false);
        webClient.getOptions().setJavaScriptEnabled(true);
        webClient.setAjaxController(new NicelyResynchronizingAjaxController());

//        webClient.getOptions().setTimeout(5 * 1000);
        HtmlPage rootPage = webClient.getPage(url);

        webClient.waitForBackgroundJavaScript(10 * 1000);
        String html = rootPage.asXml();

        return Jsoup.parse(html);
    }
}

