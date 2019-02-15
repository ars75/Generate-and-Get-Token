package org.gettoken;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class App {

    private static final String GENERATE_MFA_TOKENS_URL = "https://saml.trimble.com/access/MFA.php";
    private static final String CHROME_DRIVE_PATH = "C:"+ File.separator +"Program Files"+ File.separator + "ChromeDriver" + File.separator +"chromedriver.exe";
    private static final String FILEPATH = "C:" + File.separator + "Users" + File.separator + "adityasatalkar" + File.separator + "dev" + File.separator + "SecurityToken" + File.separator + "tokens.txt";

    public void updateFile(String input) throws IOException {
        File file = new File(FILEPATH);
        FileWriter fileWriter = new FileWriter(file);
        fileWriter.write(input);
        fileWriter.close();
    }

    public void generateTokens(List<Credentials> credentials) throws Exception{
        // Create a new instance of the html unit driver
        // Notice that the remainder of the code relies on the interface,
        // not the implementation.
        System.setProperty("webdriver.chrome.driver", CHROME_DRIVE_PATH);
        WebDriver driver = new ChromeDriver();

        // And now use this to visit GENERATE_MFA_TOKENS_URL
        driver.get(GENERATE_MFA_TOKENS_URL);

        String username = credentials.get(0).getUsername();
        String password = credentials.get(0).getPassword();

        // Username
        driver.findElement(By.name("username")).sendKeys(username);

        // Password
        driver.findElement(By.name("password")).sendKeys(password);

        // select dropdown as PEOPLENETONLINE
        WebElement mySelectElement = driver.findElement(By.name("domain"));
        Select dropdown = new Select(mySelectElement);
        dropdown.selectByVisibleText("PEOPLENETONLINE");

        // click on login
        driver.findElement(By.xpath("/html/body/div[2]/form/p/input")).submit();

        // navigate to backupcodes page
        driver.get("https://saml.trimble.com/access/backupCodes.php");

        // generate new codes
        driver.findElement(By.name("newCodes")).click();
        System.out.println("Page title is: " + driver.getTitle());

        // select all text on webpage
        driver.findElement(By.cssSelector("body")).sendKeys(Keys.CONTROL +"a");

        // copy selected text which contains backup codes
        driver.findElement(By.cssSelector("body")).sendKeys(Keys.CONTROL +"c");

        // print contents of clipboard to console
        String data = (String) Toolkit.getDefaultToolkit().getSystemClipboard().getData(DataFlavor.stringFlavor);
        System.out.println(data);

        // update file with codes copied from webpage
        App app = new App();
        app.updateFile(data);
    }
}
