package Ders02_TamEkranAcma;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.Page;

import java.awt.*;

public class TamEkranAcma {
    public static void main(String[] args) throws InterruptedException {

        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int width = (int) dimension.getWidth();
        int height = (int) dimension.getHeight();

        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        Page page = browser.newPage();

        page.navigate("https://www.hepsiburada.com/");
        //page.setViewportSize(12800,720);//Ekran Genişligini Almak İcin Kullanırız
        page.setViewportSize(width,height);
        Thread.sleep(3000);

        page.close();
        browser.close();
        playwright.close();

    }
}
