package com.lgz.crazy.logs;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by lgz on 2019/3/5.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class LogTest {
    private static final Logger LOG = LoggerFactory.getLogger(LogTest.class);
    @Test
    public void test(){
        LOG.trace("------trace-----");
        LOG.debug("------debug-----");
        LOG.info("------info-----");
        LOG.warn("------warn-----");
        LOG.error("------error-----");
    }
}
