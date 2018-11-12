package com.xa.dz.ims.service;

import com.xa.dz.ims.model.Accordion;

import java.util.List;

/**
 * @Author DangTing[dangting@boco.com.cn]
 * @CreateDate 2018/11/10 10:57
 * @Description
 * @Copyright Copyright 2018/11/10 10:57 Daniel. All rights reserved
 */
public interface AccordionService {

    public List<Accordion> getAccordion(String userName);
}
