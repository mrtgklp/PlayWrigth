package Ders05_İframe;

import com.microsoft.playwright.*;
import com.microsoft.playwright.Frame;

import java.awt.*;

import java.awt.Frame.*;
import java.util.List;

public class iframe_List {
    public static void main(String[] args) {
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int width = (int) dimension.getWidth();
        int height= (int) dimension.getHeight();

        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));

        Page page = browser.newPage();
        page.setViewportSize(width,height);

        page.navigate("https://the-internet.herokuapp.com/iframe");

        Locator title = page.locator("//h3");
        System.out.println("title " + title.innerText());

        List<Frame> frames = page.frames();
        System.out.println("Frames: " + frames.size());

        for(Frame frame: frames){
            System.out.println("frame url() = " + frame.url());
        }

        //Frame By Url
        Frame frame = page.frameByUrl("about:blank");

        FrameLocator frameLocator = page.frameLocator("mce_0_ifr");

        Locator body = frameLocator.getByText("Your content goes here.");
        body.click();
        body.clear();

        Locator inpuText = frameLocator.getByLabel("Rich Text Area. Press ALT-0 for help.");
        inpuText.fill("Hello World");

        page.close();
        browser.close();
        playwright.close();

    }
}
