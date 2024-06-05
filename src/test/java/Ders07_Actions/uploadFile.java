package Ders07_Actions;

import com.microsoft.playwright.*;

import java.awt.*;
import java.nio.file.Path;
import java.nio.file.Paths;

public class uploadFile {
    public static void main(String[] args) {
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int width = (int) dimension.getWidth();
        int height= (int) dimension.getHeight();

        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));

        Page page = browser.newPage();
        page.setViewportSize(width,height);

        page.navigate("https://the-internet.herokuapp.com/upload");

        //Select one file
        Locator dosyaSecButton = page.locator("input[id='file-upload']");
        String dosyaYolu = System.getProperty("user.home") + "/IdeaProjects/PlayWrigth/src/test/java/utilities/resim.bmp";
        dosyaSecButton.setInputFiles(Paths.get(dosyaYolu));

        page.locator("input[id='file-submit']").click();

        page.close();
        browser.close();
        playwright.close();

    }
}
