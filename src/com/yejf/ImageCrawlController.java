package com.yejf;

import edu.uci.ics.crawler4j.crawler.CrawlConfig;
import edu.uci.ics.crawler4j.crawler.CrawlController;
import edu.uci.ics.crawler4j.fetcher.PageFetcher;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtConfig;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtServer;

/**
 * @author Yasser Ganjisaffar
 */
public class ImageCrawlController {

  public static void main(String[] args) throws Exception {


	  String storageFolder = "e:/data/crawl/root/image";
      int numberOfCrawlers = 7;
    String rootFolder = "e:/data/crawl/root";

    CrawlConfig config = new CrawlConfig();

    config.setCrawlStorageFolder(rootFolder);

    /*
     * Since images are binary content, we need to set this parameter to
     * true to make sure they are included in the crawl.
     */
    config.setIncludeBinaryContentInCrawling(true);

    String[] crawlDomains = {"http://www.bilibili.com"};

    PageFetcher pageFetcher = new PageFetcher(config);
    RobotstxtConfig robotstxtConfig = new RobotstxtConfig();
    RobotstxtServer robotstxtServer = new RobotstxtServer(robotstxtConfig, pageFetcher);
    CrawlController controller = new CrawlController(config, pageFetcher, robotstxtServer);
    for (String domain : crawlDomains) {
      controller.addSeed(domain);
    }

    ImageCrawler.configure(crawlDomains, storageFolder);

    controller.start(ImageCrawler.class, numberOfCrawlers);
  }
}