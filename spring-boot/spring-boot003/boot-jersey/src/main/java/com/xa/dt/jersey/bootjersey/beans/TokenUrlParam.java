package com.xa.dt.jersey.bootjersey.beans;

import javax.ws.rs.QueryParam;

public class TokenUrlParam {

    @QueryParam("systemId")
    private String systemId;

    @QueryParam("systemKey")
    private String systemKey;

    public String getSystemId() {
        return systemId;
    }

    public void setSystemId(String systemId) {
        this.systemId = systemId;
    }

    public String getSystemKey() {
        return systemKey;
    }

    public void setSystemKey(String systemKey) {
        this.systemKey = systemKey;
    }
}
