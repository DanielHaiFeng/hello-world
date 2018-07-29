package com.xa.dt.jersey.bootjersey;

import com.xa.dt.jersey.bootjersey.beans.UserBean;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = BootJerseyApplication.class)
@WebAppConfiguration
public class BootJerseyApplicationTests {

    private static String serverUri = "http://localhost:8081/demo";

    @Test
    public void noParamGet() {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(serverUri + "/city");
        Response response = target.request()
                .buildGet()
                .invoke();
        String readEntity = response.readEntity(String.class);
        System.out.println(readEntity);
        response.close();
    }

    @Test
    public void paramGet() {
        Client client1 = ClientBuilder.newClient();
        WebTarget target = client1.target(serverUri + "/token?systemId=boco&systemKey=1001");
        Response response = target.request()
                .buildGet()
                .invoke();
        String readEntity = response.readEntity(String.class);
        System.out.println(readEntity);
        response.close();
    }

    @Test
    public void paramPostString() {
        Client client1 = ClientBuilder.newClient();
        WebTarget target = client1.target(serverUri + "/acceptBeanString");

        UserBean userBean = new UserBean();
        userBean.setId("111");
        userBean.setUserName("aaaaaa");
        Entity<UserBean> entity = Entity.entity(userBean, MediaType.APPLICATION_JSON);
        Response response = target.request()
                .buildPost(entity)
                .invoke();

        UserBean readEntity = response.readEntity(UserBean.class);
        System.out.println(readEntity.toString());
        response.close();
    }

    @Test
    public void paramPostBean() {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(serverUri + "/acceptBean");

        UserBean userBean = new UserBean();
        userBean.setId("111");
        userBean.setUserName("aaaaaa");
        Entity<UserBean> entity = Entity.entity(userBean, MediaType.APPLICATION_JSON);
        Response response = target.request()
                .buildPost(entity)
                .invoke();

        UserBean readEntity = response.readEntity(UserBean.class);
        System.out.println(readEntity.toString());
        response.close();
    }

    @Test
    public void paramPostBeanUrl() {
        Client client1 = ClientBuilder.newClient();
        WebTarget target = client1.target(serverUri + "/acceptBeanAndUrl?systemId=boco&systemKey=1001");

        UserBean ub1 = new UserBean();
        ub1.setId("111");
        ub1.setUserName("aaaaaa");
        Entity<UserBean> entity = Entity.entity(ub1, MediaType.APPLICATION_JSON);
        Response response = target.request()
                .buildPost(entity)
                .invoke();

        UserBean readEntity = response.readEntity(UserBean.class);
        System.out.println(readEntity.toString());
        response.close();
    }
}
