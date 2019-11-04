package com.xa.dt.mode.state;

import java.util.ArrayList;
import java.util.List;

/**
 * @author DangTing[dangting@boco.com.cn]
 * @date 2019-11-01 18:11
 * @version: 1.0
 * @description: TODO
 */
public class CloseTagState extends AbstractState {

    private static final List<String> TAGS = new ArrayList<String>();

    static {
        TAGS.add("div");
        TAGS.add("p");
    }

    @Override
    public void startTag() {
        this.context.setState(SimpleXmlParserContext.startTagState);
        this.context.startTag();
    }

    public void endTag(){
        this.context.setState(SimpleXmlParserContext.endTagState);
        this.context.endTag();
    }

    @Override
    public void closeTag() {
        System.out.print("结束标签\n");
        //判断下标签是否合规范
        if(!TAGS.contains(this.context.getTempString().toString())){
            this.context.flush(true);
        }else{
            this.context.flush(false);
        }

    }
}
