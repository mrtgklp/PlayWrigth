package Ders11_Download;

import com.microsoft.playwright.*;

import java.awt.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DownloadDelete {
    public static void main(String[] args) throws IOException {
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

        /*String date = new SimpleDateFormat("_hh_mm_ss_ddMMyy").format(new Date());
        String filePath = System.getProperty("user.home") + "/OneDrive/Desktop/Downloads/"+date+"file.jpg";

         */
        String filePath = System.getProperty("user.home") + "/OneDrive/Desktop/Downloads/";
        download.saveAs(Paths.get(filePath,download.suggestedFilename()));

        boolean isFileDownloadad = Files.exists(Paths.get(filePath,download.suggestedFilename()));
        assert isFileDownloadad;
        System.out.println("dosya durumu : " + isFileDownloadad);

       // download.delete();

        Files.deleteIfExists(Paths.get(filePath,download.suggestedFilename()));

        page.close();
        browser.close();
        playwright.close();

    }
}
