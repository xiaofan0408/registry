package com.xiaofan0408;

import com.xiaofan0408.v1.Application;
import com.xiaofan0408.v1.Instance;
import com.xiaofan0408.v1.Registry;
import com.xiaofan0408.v1.model.request.RequestRegister;
import com.xiaofan0408.v1.model.response.FetchData;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

public class RegistryTest {


//    var req = &model.RequestRegister{AppId: "com.xx.testapp", Hostname: "myhost", Addrs: []string{"http://testapp.xx.com/myhost"}, Status: 1}
//    func TestRegister(t *testing.T) {
//        r := model.NewRegistry()
//        instance := model.NewInstance(req)
//        app, _ := r.Register(instance, req.LatestTimestamp)
//        t.Log(app)
//    }

    @Test
    public void testTestRegister(){
        RequestRegister requestRegister = new RequestRegister();
        requestRegister.setAppId("com.xx.testapp");
        requestRegister.setHostname("myhost");
        requestRegister.setAddrs(Arrays.asList("http://testapp.xx.com/myhost"));
        requestRegister.setStatus(1);
        Registry registry = new Registry();
        Instance instance = new Instance(requestRegister);
        Application application = registry.register(instance,requestRegister.getLatestTimestamp());
        System.out.println(application);
    }

    @Test
    public void testFetch() {
        RequestRegister requestRegister = new RequestRegister();
        requestRegister.setAppId("com.xx.testapp");
        requestRegister.setHostname("myhost");
        requestRegister.setAddrs(Arrays.asList("http://testapp.xx.com/myhost"));
        requestRegister.setStatus(1);
        requestRegister.setEnv("test");
        Registry registry = new Registry();
        Instance instance = new Instance(requestRegister);
        Application application = registry.register(instance,requestRegister.getLatestTimestamp());
        System.out.println(application);
        FetchData fetchData = registry.fetch(requestRegister.getEnv(),requestRegister.getAppId(),requestRegister.getStatus(),0);
        Assert.assertNotNull(fetchData);
        System.out.println(fetchData);
    }

}
