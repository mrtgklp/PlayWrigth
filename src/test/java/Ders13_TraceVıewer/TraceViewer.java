package Ders13_TraceVıewer;

import com.microsoft.playwright.*;

import java.awt.*;
import java.nio.file.Paths;

import static com.microsoft.playwright.options.AriaRole.TEXTBOX;

public class TraceViewer {
    public static void main(String[] args) throws InterruptedException {
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int width = (int) dimension.getWidth();
        int height = (int) dimension.getHeight();

        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));

        BrowserContext context = browser.newContext();

        //// Start tracing before creating / navigating a page.
        context.tracing().start(new Tracing.StartOptions()
                .setScreenshots(true)
                .setSnapshots(true)
                .setSources(true));

        Page page = context.newPage();
        page.setViewportSize(width,height);

        page.navigate("https://getir.com/");

        //getByRole
        Locator phoneNumber = page.getByRole(TEXTBOX, new Page.GetByRoleOptions().setName("Telefon Numarası"));
        System.out.println("2. Phone Number " + phoneNumber.innerText());
        phoneNumber.click();
        phoneNumber.fill("537-671");

        Thread.sleep(3000);

        // Stop tracing and export it into a zip archive.
        context.tracing().stop(new Tracing.StopOptions()
                .setPath(Paths.get("trace.zip")));

        context.close();
        page.close();
        playwright.close();
    }
}
