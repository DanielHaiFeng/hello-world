package com.xa.dz.ims.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.xa.dz.ims.mapper.MenuMapper;
import com.xa.dz.ims.mapper.UserMapper;
import com.xa.dz.ims.model.Accordion;
import com.xa.dz.ims.model.Menu;
import com.xa.dz.ims.model.User;
import com.xa.dz.ims.model.UserExample;
import com.xa.dz.ims.service.AccordionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author DangTing[dangting@boco.com.cn]
 * @CreateDate 2018/11/10 10:58
 * @Description
 * @Copyright Copyright 2018/11/10 10:58 BOCO. All rights reserved
 */
@Service("accordionService")
public class AccordionServiceImpl implements AccordionService {

    private final static Logger logger = LoggerFactory.getLogger(AccordionServiceImpl.class);

    @Autowired
    UserMapper userMapper;

    @Autowired
    MenuMapper menuMapper;

    @Override
    public List<Accordion> getAccordion(String userName) {
        UserExample userExample = new UserExample();
        userExample.createCriteria().andLoginnameEqualTo(userName);
        User ub = userMapper.selectByExample(userExample).get(0);
        List<Menu> mblist = menuMapper.getAuthorizeMenus(ub.getUid());
        Accordion ab = new Accordion();
        ab.setMenuid(0);
        ab.setIcon("icon-ok");
        ab.setMenuname("root");
        ab.setUrl("root");
        ab.setVisible(true);
        this.assembleAccordion(ab, mblist);
        logger.info("获取到的手风琴原始json数据为：{}", JSONArray.toJSONString(ab.getMenus()));
        return ab.getMenus();
    }

    private void assembleAccordion(Accordion ab, List<Menu> mblist) {
        if (mblist.size() != 0) {
            if (ab.getMenus() == null) {
                ab.setMenus(new ArrayList<Accordion>());
            }
            int id = ab.getMenuid();
            List<Accordion> ablist = ab.getMenus();
            for (int i = mblist.size() - 1; i >= 0; i--) {
                Menu mb = mblist.get(i);
                if (mb.getPid() == id) {
                    Accordion abt = new Accordion();
                    abt.setMenuid(mb.getMid());
                    abt.setMenuname(mb.getMname());
                    abt.setIcon(mb.getMicon());
                    abt.setUrl(mb.getUrl());
                    abt.setVisible(Boolean.valueOf(mb.getChecked()));
                    mblist.remove(i);
                    assembleAccordion(abt, mblist);
                    ablist.add(abt);
                }
            }
        }
    }
}
