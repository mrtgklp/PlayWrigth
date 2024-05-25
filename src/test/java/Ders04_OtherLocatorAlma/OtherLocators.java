package Ders04_OtherLocatorAlma;

import com.microsoft.playwright.*;

import java.awt.*;

public class OtherLocators {
    public static void main(String[] args) throws InterruptedException {
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int width = (int) dimension.getWidth();
        int height = (int) dimension.getHeight();


        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        Page page = browser.newPage();
        page.setViewportSize(width,height);

        page.navigate("https://www.getir.com/");
        System.out.printf("Title= " + page.title());

        //css matching by.text
        //1. has text

        Locator loginText1 = page.locator("h5:has-text('Giriş yap veya kayıt ol')");
        System.out.println("1.login Text " + loginText1.innerText());

        //2 text
        Locator loginText2 = page.locator("h5:text('Giriş yap veya kayıt ol')");
        System.out.println("2. login Text " + loginText2.innerText());

        //css: element matching one of the conditions
        Locator continuLogin = page.locator("button:has-text('Telefon numarası ile devam et'),button:has-text('login button')");
        System.out.println("3. contınion button " + continuLogin.innerText());

        // Css: pick n-th match from the query result
        Locator loginButton = page.locator(":nth-match(:text('Giriş yap'), 1)");
        System.out.println("4. login button:" + loginButton.innerText());
        loginButton.click();
        Thread.sleep(3000);

        //id: data-testid, data-test-id , data-test selectors
        Locator loginPhoneNumber = page.locator("data-testid=modal").locator("data-testid=input");
        System.out.println("5. phone number login " + loginPhoneNumber.innerText());

        //xpath locators
        Locator loginContiunButton = page.locator("(//button[@type='submit'])[2]");
        System.out.println("6. login contiun button" + loginContiunButton.innerText());

        page.close();
        browser.close();
        playwright.close();




    }
}
