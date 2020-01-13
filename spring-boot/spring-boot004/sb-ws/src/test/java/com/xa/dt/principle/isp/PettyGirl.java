package com.xa.dt.principle.isp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author DangTing
 * @date 2019-11-04 09:38
 * @version: 1.0
 * @description: TODO
 */
public class PettyGirl implements IGoodBodyGirl, IGreatTemperamentGirl {

    private static Logger logger = LoggerFactory.getLogger(PettyGirl.class);

    private String name;

    public PettyGirl(String name) {
        this.name = name;
    }

    @Override
    public void goodLooking() {
        logger.info("JimmyZhang {}", name + "---脸蛋很漂亮");
    }

    @Override
    public void greatTemperament() {
        logger.info("JimmyZhang {}", name + "---气质非常好");
    }

    @Override
    public void niceFigure() {
        logger.info("JimmyZhang {}", name + "---身材非常棒");
    }
}

