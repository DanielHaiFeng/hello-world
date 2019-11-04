package com.xa.dt.mode.state;

/**
 * @author DangTing[dangting@boco.com.cn]
 * @date 2019-11-01 18:08
 * @version: 1.0
 * @description: TODO
 */
public abstract class AbstractState implements State {

    protected SimpleXmlParserContext context;

    public void setContext(SimpleXmlParserContext context) {
        this.context = context;
    }

    public void endTag(){
    }

}
