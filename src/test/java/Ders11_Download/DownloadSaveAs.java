package Ders11_Download;

import com.microsoft.playwright.*;

import java.awt.*;
import java.nio.file.Paths;

public class DownloadSaveAs {
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

        String filePath = System.getProperty("user.home") + "/OneDrive/Desktop/Downloads/file.jpg";
        download.saveAs(Paths.get(filePath, download.suggestedFilename()));

        page.close();
        browser.close();
        playwright.close();
    }
}
