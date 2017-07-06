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
    additionalRemoteCaps.setCapability("platformVersion", "9.1")
    additionalRemoteCaps.setCapability("platformName", "ios")
    additionalRemoteCaps.setCapability("deviceName", "iphone")
    additionalRemoteCaps.setCapability("app", "C:\\Users\\abranyik\\Documents\\git-repositories\\cinnamon-framework\\demo.ipa")
    mainRemoteCaps.merge(additionalRemoteCaps)
  }
}
