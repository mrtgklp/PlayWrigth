package Ders11_Download;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.Download;

import java.awt.*;
import java.nio.file.Paths;

public class DownloadPageUrlTitle {
    public static void main(String[] args) {
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int width = (int) dimension.getWidth();
        int height = (int) dimension.getHeight();

        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));

        Page page = browser.newPage();
        page.setViewportSize(width, height);


        page.navigate("https://demoqa.com/upload-download");

        // Wait for the download to start
        Download download = page.waitForDownload(() -> {
            // Perform the action that initiates download
            page.getByText("Download").last().click();
        });
        System.out.println("page title:" + download.page().title());
        System.out.println("url " + download.page().url());
        System.out.println("path " + download.path().toString());

        String filePath = System.getProperty("user.home") + "/OneDrive/Desktop/Downloads/file.jpg";



        // Wait for the download process to complete and save the downloaded file somewhere
        download.saveAs(Paths.get(filePath, download.suggestedFilename()));

        page.close();
        browser.close();
        playwright.close();

    }
}
