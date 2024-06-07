package Ders09_Screenshot;

import com.microsoft.playwright.*;

import java.awt.*;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

public class Screenshot {
    public static void main(String[] args) throws InterruptedException {
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int width = (int) dimension.getWidth();
        int height= (int) dimension.getHeight();

        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));

        Page page = browser.newPage();
        page.setViewportSize(width,height);


        page.navigate("https://www.ebay.com/");

        //sayfanın resmini alalım
        String tarih = new SimpleDateFormat("_hh_mm_ss_ddMMyy").format(new Date());
        String dosyaYolu ="src/test/java/utilities/screenshot/ " + tarih + ".jpg";

        //Fotograf cekme
        //page.screenshot(new Page.ScreenshotOptions().setPath(Paths.get(dosyaYolu)));

        //page.screenshot(new Page.ScreenshotOptions().setPath(Paths.get(dosyaYolu)).setFullPage(true));
       // Thread.sleep(3000);

        //Elementin Fotografını Cekme
        Locator searchBox = page.getByPlaceholder("Search for anything");
        //searchBox.screenshot(new Locator.ScreenshotOptions().setPath(Paths.get(dosyaYolu)));

        page.screenshot(new Page.ScreenshotOptions().setPath(Paths.get(dosyaYolu)).setMask(Arrays.asList(searchBox)));
        Thread.sleep(5000);

        page.close();
        browser.close();
        playwright.close();

    }
}
