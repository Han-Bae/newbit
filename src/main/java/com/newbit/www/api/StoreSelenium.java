package com.newbit.www.api;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.newbit.www.vo.StoreVO;
import com.newbit.www.vo.WebDriverPathVO;

/**
 * 
 * @author 전다빈
 * @since	2022.07.14
 * @version v.1.0
 * 
 * 			작업이력 ] 2022.07.14 - 담당자 전다빈 : 셀레니움 핸들링 클래스
 * 					   2022.07.16 ~ 18 - 담당자 전다빈 : 속도 개선
 *
 */

public class StoreSelenium {
	
		// WebDriver
		// 속도 개선 1
		private WebDriver driver;
		
		// Properties
		public final static String WEB_DRIVER_ID = "webdriver.chrome.driver";
		
		// crawling 할 URL
		private String url;
		
		public List<StoreVO> mainPageselenium(String sort) {
			// System Property SetUp
			System.setProperty(WEB_DRIVER_ID, WebDriverPathVO.WEB_DRIVER_PATH);
			ChromeOptions options = new ChromeOptions();
			// 속도 개선 2
			options.setHeadless(true);
			options.addArguments("--disable-gpu");
			options.addArguments("--disable-infobars");
			options.addArguments("--disable-extensions");
			options.addArguments("--blink-setting=imagesEnable=false");
			options.addArguments("--user-agent=Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/103.0.0.0 Safari/537.36");
			options.addArguments("--lang=ko_KR");
			
			// Driver SetUp
			driver = new ChromeDriver(options);
			
			ArrayList<StoreVO> list = new ArrayList<StoreVO>();
			String url = "https://store.steampowered.com/search/?filter=topsellers";
			if(sort != null) {
				switch(sort) {
				case "":
					break;
				case "releasedDESC":
					url = "https://store.steampowered.com/search/?sort_by=Released_DESC&filter=topsellers";
					break;
				case "nameASC":
					url = "https://store.steampowered.com/search/?sort_by=Name_ASC&filter=topsellers";
					break;
				case "priceASC":
					url = "https://store.steampowered.com/search/?sort_by=Price_ASC&filter=topsellers";
					break;
				case "priceDESC":
					url = "https://store.steampowered.com/search/?sort_by=Price_DESC&filter=topsellers";
					break;
				case "reviewsDESC":
					url = "https://store.steampowered.com/search/?sort_by=Reviews_DESC&filter=topsellers";
					break;
				}
			}
			
			try {
				// 쿠키 추가
				Calendar cal = Calendar.getInstance();
				cal.add(cal.DATE, 3);
				Date date = new Date(cal.getTimeInMillis());
				Cookie lang = new Cookie("Steam_Language", "koreana", "store.steampowered.com", "/", date, true, false);
				
				WebDriverWait webDriverWait = new WebDriverWait(driver, 3);
				// get page (= 브라우저에서 url을 주소창에 넣은 후 request한 것과 같다)
				driver.get(url);
				driver.manage().addCookie(lang);
				driver.navigate().refresh();
				
				webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("search_resultsRows")));
				
				List<WebElement> elements = driver.findElement(By.id("search_resultsRows")).findElements(By.tagName("a"));
				for(WebElement element : elements) {
					StoreVO storeVO = new StoreVO();
					storeVO.setTitle(element.findElement(By.cssSelector("span.title")).getText());
					storeVO.setReleased(element.findElement(By.cssSelector("div.search_released")).getText());
					
					list.add(storeVO);
				}
			} catch(Exception e) {
				e.printStackTrace();
			} finally {
				driver.close();
				driver.quit();
			}
			
			return list;
		}
		
}
