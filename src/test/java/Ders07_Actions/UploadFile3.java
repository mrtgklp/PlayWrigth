package Ders07_Actions;

import com.microsoft.playwright.*;

import java.awt.*;
import java.nio.file.Paths;

public class UploadFile3 {
    public static void main(String[] args) throws InterruptedException {
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int width = (int) dimension.getWidth();
        int height= (int) dimension.getHeight();

        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));

        Page page = browser.newPage();
        page.setViewportSize(width,height);

        page.navigate("http://autopract.com/selenium/upload2/");

        //uploaded single file chooser(without input tag)
        Locator dosyaSecButton = page.locator("a[id='pickfiles']");

        String dosyaYolu = System.getProperty("user.home") + "/IdeaProjects/PlayWrigth/src/test/java/utilities/End.bat";

        FileChooser fileChooser = page.waitForFileChooser(()->{
            dosyaSecButton.click();
        }) ;
        fileChooser.setFiles(Paths.get(dosyaYolu));

        Thread.sleep(4000);

        page.close();
        browser.close();
        playwright.close();

    }
}
