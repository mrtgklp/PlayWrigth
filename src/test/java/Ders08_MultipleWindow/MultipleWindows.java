package Ders08_MultipleWindow;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

import java.awt.*;
import java.util.List;

public class MultipleWindows {
    public static void main(String[] args) throws InterruptedException {
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int width = (int) dimension.getWidth();
        int height= (int) dimension.getHeight();

        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));

        Page page = browser.newPage();
        page.setViewportSize(width,height);


        page.navigate("https://demoqa.com/browser-windows");

        //Multiple pages
        // Get page after a specific action (e.g. clicking a link)
        Page popUp = page.waitForPopup(new Page.WaitForPopupOptions().setPredicate(p -> p.context().pages().size() == 2), ()-> {
            page.getByText("New Window").first().click(); // Opens a new tab
        });
        List<Page> pages = popUp.context().pages();
        System.out.println("Sayfa sayısı = " + pages.size());

        pages.forEach(tab-> {
            tab.waitForLoadState();
            System.out.println(tab.title());
        });

        System.out.println("İlk Sayfanın Url'li:" + pages.get(0).url());
        System.out.println("İkinci Sayfanın Url'li :" + pages.get(1).url());

        /*
         Page fPAge = null;
         Page sPAge = null;

        if(pages.get(0).url().equals("https://demoqa.com/browser-windows")) {
            fPAge = pages.get(0);
        }
        else {
             sPAge = pages.get(1);
        }

        Thread.sleep(5000);
        page.bringToFront();

         */

        Page fPAge = null;
        Page sPAge = null;

        if(pages.get(0).url().equals("DEMOQA")) {
             fPAge = pages.get(0);
        }
        else {
            sPAge = pages.get(1);
        }

        page.close();
        browser.close();
        playwright.close();
    }
}
