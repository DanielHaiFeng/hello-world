package com.xa.dt.principle.lod;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * @author DangTing
 * @date 2019-11-04 09:29
 * @version: 1.0
 * @description: 体育委员
 */
public class GroupLeader {

    private static Logger logger = LoggerFactory.getLogger(GroupLeader.class);

    private List<Girl> listGirls;

    public GroupLeader(List<Girl> listGirls) {
        this.listGirls = listGirls;
    }

    public void countGirls() {
        logger.info("JimmyZhang {}", "女生的数量:" + listGirls.size());
    }
}

