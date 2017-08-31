package com.booking.core;

import com.booking.util.PropertiesCache;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.os.WindowsUtils;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;

import java.lang.invoke.MethodHandles;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import static com.booking.util.TestData.WebDriver.CHROME_PATH_IOS;
import static com.booking.util.TestData.WebDriver.CHROME_PATH_WIN;
import static com.booking.util.TestData.WebDriver.GECKO_DRIVER_PATH_IOS;
import static com.booking.util.TestData.WebDriver.GECKO_DRIVER_PATH_WIN;
import static com.booking.util.TestData.WebDriver.IMPLICIT_WAIT;
import static com.booking.util.TestData.WebDriver.LOAD_TIMEOUT;
import static com.booking.util.TestData.WebDriver.SCRIPT_TIMEOUT;
import static com.booking.util.TestData.WebDriver.WEB_DRIVER_CHROME;
import static com.booking.util.TestData.WebDriver.WEB_DRIVER_GECKO;

public abstract class WebDriverTestBase {

    private static final String OS = System.getProperty("os.name").toLowerCase(),
            BROWSER = System.getProperty("browser");
    private static final Logger LOG = LogManager.getLogger(MethodHandles.lookup().lookupClass());
    protected WebDriver driver;
    private DesiredCapabilities desiredCapabilities = new DesiredCapabilities();

    @BeforeClass
    public void setUp() {
        if (isBrowserSetUpFor(BrowserName.CHROME.name(), BROWSER)) {
            if (isWindows()) {
                System.setProperty(WEB_DRIVER_CHROME, getPath(CHROME_PATH_WIN));
            } else if (isIOS()) {
                System.setProperty(WEB_DRIVER_CHROME, getPath(CHROME_PATH_IOS));
            }
        } else if (isBrowserSetUpFor(BrowserName.FIREFOX.name(), BROWSER)) {
            if (isWindows()) {
                System.setProperty(WEB_DRIVER_GECKO, getPath(GECKO_DRIVER_PATH_WIN));
            } else if (isIOS()) {
                System.setProperty(WEB_DRIVER_GECKO, getPath(GECKO_DRIVER_PATH_IOS));
            }
        }
        initializeWebDriver();
    }

    private void initializeWebDriver() {
        try {
            if (isBrowserSetUpFor(BrowserName.CHROME.name(), BROWSER)) {
                ChromeOptions options = new ChromeOptions();
                options.addArguments("--disable-extensions");
                options.addArguments("start-fullscreen");
                driver = new ChromeDriver(options);
                desiredCapabilities.setBrowserName(BrowserName.CHROME.name());
            } else if (isBrowserSetUpFor(BrowserName.FIREFOX.name(), BROWSER)) {
                driver = new FirefoxDriver();
                desiredCapabilities.setBrowserName(BrowserName.FIREFOX.name());
                driver.manage().window().maximize();
            }
            driver.manage().timeouts().setScriptTimeout(Integer.valueOf(SCRIPT_TIMEOUT), TimeUnit.SECONDS);
            driver.manage().timeouts().pageLoadTimeout(Integer.valueOf(LOAD_TIMEOUT), TimeUnit.SECONDS);
            driver.manage().timeouts().implicitlyWait(Integer.valueOf(IMPLICIT_WAIT), TimeUnit.SECONDS);
        } catch (WebDriverException e) {
            System.out.println(e.getMessage());
            WindowsUtils.killByName(desiredCapabilities.getBrowserName() + "driver" + (isWindows() ? ".exe" : ""));
        }
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }

    private boolean isBrowserSetUpFor(String browserName, String browserSystemVeriable) {
        return StringUtils.isEmpty(BROWSER) || browserName.equalsIgnoreCase(browserSystemVeriable);
    }

    private boolean isWindows() {
        return OS.contains("win");
    }

    private boolean isIOS() {
        return OS.contains("nix") || OS.contains("nux") || OS.contains("aix") || OS.contains("mac");
    }

    public static String getProperty(String key) {
        return PropertiesCache.getProperty(key);
    }

    private String getPath(String s) {
        String path = null;
        URL url = WebDriverTestBase.class.getClassLoader().getResource(s);
        if (url != null) {
            path = url.getPath();
        } else {
            LOG.error("Not found resource");
        }
        return path;
    }
}