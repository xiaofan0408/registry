package com.xiaofan0408.v1;

import com.xiaofan0408.v1.base.Pair;
import com.xiaofan0408.v1.model.response.FetchData;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

@Slf4j
@Getter
@Setter
public class Registry {

    private final String KEY_TEMPLATE = "%s-%s";

    private Map<String,Application> apps;

    private ReadWriteLock readWriteLock;

    public Registry() {
        this.apps = new ConcurrentHashMap<>();
        readWriteLock = new ReentrantReadWriteLock();
    }

    public Application register(Instance instance,long latestTimestamp){
        log.info("start register...");
        String key = getKey(instance.getAppId(),instance.getEnv());
        readWriteLock.readLock().lock();
        Application app = apps.get(key);
        readWriteLock.readLock().unlock();
        if (app == null) {
            app = new Application(instance.getAppId());
        }
        Pair<Instance,Boolean> isNew = app.addInstance(instance,latestTimestamp);
        if (isNew.getT2()) {

        }
        readWriteLock.writeLock().lock();
        apps.put(key,app);
        readWriteLock.writeLock().unlock();
        return app;
    }

    public FetchData fetch(String env,String appId,int status,long latestTime){
        Application application = getApplication(appId,env);
        if (application == null){
            return null;
        }
        return application.getInstance(status, latestTime);
    }

    public Application getApplication(String appId,String env){
        String key = getKey(appId,env);
        readWriteLock.readLock().lock();
        Application application = this.apps.get(key);
        readWriteLock.readLock().unlock();
        return application;
    }

//    func (r *Registry) Cancel(env, appid, hostname string, latestTimestamp int64) (*Instance, *errcode.Error) {
//        log.Println("action cancel...")
//        //find app
//        app, ok := r.getApplication(appid, env)
//        if !ok {
//            return nil, errcode.NotFound
//        }
//        instance, ok, insLen := app.Cancel(hostname, latestTimestamp)
//        if !ok {
//            return nil, errcode.NotFound
//        }
//        //if instances is empty, delete app from apps
//        if insLen == 0 {
//            r.lock.Lock()
//            delete(r.apps, getKey(appid, env))
//            r.lock.Unlock()
//        }
//        return instance, nil
//    }
//    func (app *Application) Cancel(hostname string, latestTimestamp int64) (*Instance, bool, int) {
//        newInstance := new(Instance)
//                app.lock.Lock()
//        defer app.lock.Unlock()
//        appIn, ok := app.instances[hostname]
//        if !ok {
//            return nil, ok, 0
//        }
//        //delete hostname
//        delete(app.instances, hostname)
//        appIn.LatestTimestamp = latestTimestamp
//        app.upLatestTimestamp(latestTimestamp)
//                *newInstance = *appIn
//        return newInstance, true, len(app.instances)
//    }

    private String getKey(String appId,String env){
        return String.join(KEY_TEMPLATE,appId,env);
    }

}
