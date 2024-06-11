package Ders12_VideoRecording;

import com.microsoft.playwright.*;

import java.awt.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

import static com.microsoft.playwright.options.AriaRole.TEXTBOX;

public class Video {
    public static void main(String[] args) throws InterruptedException {
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int width = (int) dimension.getWidth();
        int height = (int) dimension.getHeight();

        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));

        String tarih = new SimpleDateFormat("_hh_mm_ss_ddMMyy").format(new Date());
        String filePath =System.getProperty("user.dir") + "/src/test/java/utilities/video/" + tarih;

        BrowserContext context = browser.newContext(new Browser.NewContextOptions().setRecordVideoDir(Paths.get(filePath)).setViewportSize(640,480));

        Page page = context.newPage();

        page.navigate("https://getir.com/");

        //getByRole
        Locator phoneNumber = page.getByRole(TEXTBOX, new Page.GetByRoleOptions().setName("Telefon Numarası"));
        System.out.println("2. Phone Number " + phoneNumber.innerText());
        phoneNumber.click();
        phoneNumber.fill("537-671");

        Thread.sleep(3000);

        Path path = page.video().path();
        System.out.println(path);

        context.close();
        page.close();
       // page.video().saveAs(Paths.get(filePath + "test"));
        //page.video().delete();
        playwright.close();





    }
}
