package site.kpokogujl.drivers;

import com.codeborne.selenide.WebDriverProvider;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.remote.AutomationName;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import site.kpokogujl.config.MobileConfig;

import javax.annotation.CheckReturnValue;
import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

//import static org.apache.commons.io.FileUtils.copyInputStreamToFile;
import static org.apache.commons.io.FileUtils.copyInputStreamToFile;
import static org.openqa.selenium.remote.CapabilityType.APPLICATION_NAME;

@ParametersAreNonnullByDefault
public class LocalMobileDriver implements WebDriverProvider {
    @Override
    @CheckReturnValue
    @Nonnull
    public WebDriver createDriver(Capabilities capabilities) {
        File app = downloadApk();

        MobileConfig config = ConfigFactory.create(MobileConfig.class, System.getProperties());

        UiAutomator2Options options = new UiAutomator2Options();
        options.merge(capabilities);
        options.setAutomationName(AutomationName.ANDROID_UIAUTOMATOR2);
        options.setPlatformName(config.platformName());
        options.setDeviceName(config.deviceName());
        options.setCapability(APPLICATION_NAME, "Appium");
        options.setApp(app.getAbsolutePath());
        options.setLocale("en");
        options.setLanguage("en");
        options.setAppPackage("org.wikipedia.alpha");
        options.setAppActivity("org.wikipedia.main.MainActivity");

        try {
            return new AndroidDriver(new URL("http://localhost:4723/wd/hub"), options);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

    private File downloadApk() {
        File apk = new File("src/test/resources/apk/app-alpha-universal-release.apk");
        if (!apk.exists()) {
            String url = "https://github.com/wikimedia/apps-android-wikipedia/" +
                    "releases/download/latest/app-alpha-universal-release.apk?raw=true";
            try (InputStream in = new URL(url).openStream()) {
                copyInputStreamToFile(in, apk);
            }
            catch (IOException e) {
                throw new AssertionError("Failed to download apk", e);
            }
        }
        return apk;
    }
}
