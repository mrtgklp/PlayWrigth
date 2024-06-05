package Ders07_Actions;

import com.microsoft.playwright.*;

import java.awt.*;

public class ActionsMouseClick {
    public static void main(String[] args) throws InterruptedException {
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int width = (int) dimension.getWidth();
        int height= (int) dimension.getHeight();

        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));

        Page page = browser.newPage();
        page.setViewportSize(width,height);

        page.navigate("https://demoqa.com/buttons");

        //Genetic Click
        Locator clickMe = page.getByText("Click Me").nth(2);
        clickMe.click();
        Thread.sleep(3000);

        //Double Click
        Locator doubleClickMe = page.getByText("Double Click Me");
        doubleClickMe.click();

        Thread.sleep(3000);

        //Hover over element
        page.getByText("Right Click Me").hover();



        page.close();
        browser.close();
        playwright.close();




    }
}
