package Ders05_İframe;

import com.microsoft.playwright.*;

import java.awt.*;


public class İframe_Url {
    public static void main(String[] args) {
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int width = (int) dimension.getWidth();
        int height= (int) dimension.getHeight();

        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));

        Page page = browser.newPage();
        page.setViewportSize(width,height);

        page.navigate("https://the-internet.herokuapp.com/iframe");

        Locator title = page.locator("//h3");
        System.out.println("title " + title.innerText());

        //page closed
        Locator closed = page.locator("(//button[@type='button'])[16]");
        closed.click();

        //Frame Locator
        FrameLocator frameLocator = page.frameLocator("mce_0_ifr");

        Locator body = frameLocator.getByText("Your content goes here.");
        //body.click();
        body.clear();

        Locator inpuText = frameLocator.getByLabel("Rich Text Area. Press ALT-0 for help.");
        inpuText.fill("Hello World");

        //page.frameLocator("iframe[title=\"Rich Text Area\"]").getByText("Your content goes here.").click();
        //page.frameLocator("iframe[title=\"Rich Text Area\"]").getByLabel("Rich Text Area. Press ALT-0 for help.").fill("Hello World");

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        Locator elemantalSeleniumText = page.getByText("Elemantal Selenium");
        System.out.println(elemantalSeleniumText.innerText());

        page.close();
        browser.close();
        playwright.close();

    }
}
