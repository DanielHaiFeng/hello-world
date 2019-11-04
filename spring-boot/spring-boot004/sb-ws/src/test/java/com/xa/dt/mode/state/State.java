package com.xa.dt.mode.state;

/**
 * @author DangTing[dangting@boco.com.cn]
 * @date 2019-11-01 18:07
 * @version: 1.0
 * @description: TODO
 */
public interface State {

    void startTag();

    void endTag();

    void closeTag();

    void setContext(SimpleXmlParserContext context);
}
