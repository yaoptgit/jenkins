package com.gomro.utlis;
/**
 * @author yaopengtao
 * @data 2019年12月3日 上午11:47:01
 */

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import com.gomro.factory.BaseTester;


public class ScreemshotUitls {
    public static Logger log = Logger.getLogger(ScreemshotUitls.class);

    /*
     *	操作文件 增删改查 File
     *	操作文件的内容 读写IO流
     *	传入一个路径，往这个下存储一张浏览器截图
     *	path：图片存储路径
     */
    public static File screenshot(String path) {
        File file = null;
        //1、截图生成了图片File
        if (BaseTester.driver instanceof ChromeDriver) {
            ChromeDriver chromeDriver = (ChromeDriver) BaseTester.driver;
            file = chromeDriver.getScreenshotAs(OutputType.FILE);
        } else if (BaseTester.driver instanceof FirefoxDriver) {
            FirefoxDriver firefoxDriver = (FirefoxDriver) BaseTester.driver;
            file = firefoxDriver.getScreenshotAs(OutputType.FILE);
        } else if (BaseTester.driver instanceof InternetExplorerDriver) {
            InternetExplorerDriver internetExplorerDriver = (InternetExplorerDriver) BaseTester.driver;
            file = internetExplorerDriver.getScreenshotAs(OutputType.FILE);
        }
        //2、拷贝到指定的位置 拷贝 == 读一个文件，写到另一个文件
        File destFile = new File(path);
        try {
            FileUtils.copyFile(file, destFile);
        } catch (Exception e) {
            e.printStackTrace();
            log.info("图片拷贝失败");
        }
        return destFile;
    }
}
