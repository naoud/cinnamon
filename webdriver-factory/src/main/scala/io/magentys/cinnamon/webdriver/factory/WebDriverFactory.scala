package io.magentys.cinnamon.webdriver.factory

import java.net.URL

import io.appium.java_client.AppiumDriver
import io.appium.java_client.android.AndroidDriver
import io.appium.java_client.remote.AutomationName
import io.github.bonigarcia.wdm.{BrowserManager, WebDriverManager}
import io.magentys.cinnamon.webdriver.capabilities.DriverBinaryConfig
import org.openqa.selenium.remote.RemoteWebDriver
import org.openqa.selenium.remote.DesiredCapabilities
import org.openqa.selenium.WebDriver

import scala.util.Try

// helper interface around statics used in WebDriverManager
private[factory] trait WebDriverManagerFactory {
  def driverManagerClass(driverClass: Class[_ <: WebDriver]): BrowserManager = WebDriverManager.getInstance(driverClass)
  def webDriver(capabilities: DesiredCapabilities): WebDriver = DriverRegistry.locals.newInstance(capabilities)
}

class WebDriverFactory(factory: WebDriverManagerFactory) {

  /**
    * Create a new instance of a web driver
    *
    * @param capabilities the browser capabilities
    * @param hubUrl       optional hub url
    * @param binaryConfig optional driver binary configuration
    * @return
    */
  def getDriver(capabilities: DesiredCapabilities, hubUrl: Option[String], automationName: Option[String], binaryConfig: Option[DriverBinaryConfig]): WebDriver = {

    // if a hub url has been passed in then ignore WDM and return an instance of remote web driver
    if (hubUrl.isDefined && !hubUrl.get.isEmpty && automationName.contains("appium")) {

      //      System.out.println("hubUrl :"+hubUrl);
      //      capabilities.setCapability("platformVersion", "8.0.0")
      //      capabilities.setCapability("platformName", "android")
      //      capabilities.setCapability("deviceName", "Nexus_1")
      //      capabilities.setCapability("app", "/Users/Attila/Documents/cinnamon-framework/TubeMate-2.4.2.712_cnet.apk")
      //    System.out.println("platformName :"+capabilities.getCapability("platformName"))

      return new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"),capabilities);
    }
    if (hubUrl.isDefined && !hubUrl.get.isEmpty && automationName.equals("remote")) {

      return new RemoteWebDriver(new URL(hubUrl.get), capabilities)
    }

    val driverClass = DriverRegistry.getDriverClass(capabilities)

    if (driverClass.isDefined) {
      binaryConfig match {
        case Some(binConfig) => Try(factory.driverManagerClass(driverClass.get).setup(binConfig.arch, binConfig.version))
        case None => Try(factory.driverManagerClass(driverClass.get).setup())
      }
    }

    if (!driverClass.isDefined) {
      System.out.println("binaryConfig :"+binaryConfig);



    }


    factory.webDriver(capabilities)
  }

}

object WebDriverFactory {
  def apply() = new WebDriverFactory(new WebDriverManagerFactory {})
}