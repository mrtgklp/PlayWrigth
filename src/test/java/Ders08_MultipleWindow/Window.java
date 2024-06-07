package Ders08_MultipleWindow;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

import java.awt.*;

public class Window {
    public static void main(String[] args) {
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int width = (int) dimension.getWidth();
        int height= (int) dimension.getHeight();

        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));

        Page page = browser.newPage();


        page.navigate("https://demoqa.com/browser-windows");

        // Get page after a specific action (e.g. clicking a link)
        Page popUp = page.context().waitForPage(() -> {
            page.getByText("New Window").first().click(); // Opens a new tab
        });
        popUp.waitForLoadState();
        page.setViewportSize(width,height);
        System.out.println("Yeni Sayfa" + popUp.title());

        page.close();
        browser.close();
        playwright.close();
    }
}
