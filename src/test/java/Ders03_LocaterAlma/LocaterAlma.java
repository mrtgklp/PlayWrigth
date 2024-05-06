package Ders03_LocaterAlma;

import com.microsoft.playwright.*;

import java.awt.*;

import static com.microsoft.playwright.options.AriaRole.BUTTON;
import static com.microsoft.playwright.options.AriaRole.TEXTBOX;

public class LocaterAlma {
    public static void main(String[] args) throws InterruptedException {
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int width = (int) dimension.getWidth();
        int height = (int) dimension.getHeight();

        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        Page page = browser.newPage();

        page.navigate("https://www.getir.com/");
        System.out.printf("Title= " + page.title());

        //getByText()
        Locator loginText = page.getByText("Giriş yap veya kayıt ol");
        System.out.println("1.text = " + loginText.innerText());

        //Locater in Shadow Dom
        Locator shawdowDom = page.locator("div",new Page.LocatorOptions().setHasText("Giriş yap veya kayıt ol")).last();
        System.out.println("ShadowDom: " + shawdowDom.innerText());
        //Locator getRole()
        Locator phoneNumber = page.getByRole(TEXTBOX, new Page.GetByRoleOptions().setName("Telefon Numarası"));
        System.out.println("Phone Number= " + phoneNumber.innerText());
        phoneNumber.click();
        phoneNumber.fill("537-634-22-11");

        Thread.sleep(4000);

        //getByPlaceholder
        Locator phoneNumber1 = page.getByPlaceholder("Telefon Numarası");
        System.out.println("2. phone number: " +  phoneNumber1.innerText());

        //getBylabel
        Locator phoneContinueButton = page.getByLabel("login Button");
        System.out.println("4. phoneContinueButton " + phoneContinueButton.innerText());

        //click login button
        //getByRole
        Locator loginButton = page.getByRole(BUTTON, new Page.GetByRoleOptions().setName("Giriş yap"));
        loginButton.click();

        //test id
        Locator loginPhoneNumber = page.getByTestId("modal").getByPlaceholder("Telefon Numarası");
        System.out.println("5. login phone number" + loginPhoneNumber.innerText());
        loginPhoneNumber.click();
        loginPhoneNumber.fill("535");

        Locator cancelButton = page.getByTestId("modal").getByTestId("button").first();
        cancelButton.click();

        page.setViewportSize(width,height);
       // Thread.sleep(3000);

       // page.close();
        //browser.close();
       // playwright.close();

    }
}
