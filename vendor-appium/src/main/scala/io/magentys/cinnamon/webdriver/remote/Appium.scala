package io.magentys.cinnamon.webdriver.remote

import com.typesafe.config.Config
import org.openqa.selenium.remote.DesiredCapabilities

class Appium extends CinnamonRemote {

  override val name: String = "appium"

  override def matchesHubUrl(url: String): Boolean = url.contains(":4723/wd/hub")

  override def capabilities(browserProfile: String, config: Config): DesiredCapabilities = {
    val mainRemoteCaps: DesiredCapabilities = super.capabilities(browserProfile,config)

    System.out.println("HIT");

    val additionalRemoteCaps = new DesiredCapabilities
//    additionalRemoteCaps.setCapability("platformVersion", "9.1")
//    additionalRemoteCaps.setCapability("platformName", "ios")
//    additionalRemoteCaps.setCapability("deviceName", "iphone")
//    additionalRemoteCaps.setCapability("app", "C:\\Users\\abranyik\\Documents\\git-repositories\\cinnamon-framework\\demo.ipa")

    additionalRemoteCaps.setCapability("appiumVersion", "1.6.5")
    additionalRemoteCaps.setCapability("deviceName", "Nexus_1")
    additionalRemoteCaps.setCapability("platformVersion", "8.0")
    additionalRemoteCaps.setCapability("platformName", "Android")
    additionalRemoteCaps.setCapability("app", "/Users/Attila/Documents/cinnamon-framework/TubeMate-2.4.2.712_cnet.apk")
    additionalRemoteCaps.setCapability("browserType", "android")
    mainRemoteCaps.merge(additionalRemoteCaps)
  }


}
