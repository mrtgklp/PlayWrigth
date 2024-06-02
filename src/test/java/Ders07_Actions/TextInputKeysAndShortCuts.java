package Ders07_Actions;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.SelectOption;

import java.awt.*;

public class TextInputKeysAndShortCuts {
    public static void main(String[] args) throws InterruptedException {
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int width = (int) dimension.getWidth();
        int height= (int) dimension.getHeight();

        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));

        Page page = browser.newPage();
        page.setViewportSize(width,height);

        page.navigate("https://www.ebay.com/");

        //Select Actions
        Locator selecCatagory = page.getByLabel("Select a category for search");
        Thread.sleep(3000);

        //Select By Value
        selecCatagory.selectOption("2984");//Baby
        Thread.sleep(3000);

        //Select By Label
        selecCatagory.selectOption(new SelectOption().setLabel("Consumer Electronics"));
        Thread.sleep(3000);

        page.close();
        browser.close();
        playwright.close();


    }
}
