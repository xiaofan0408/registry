package com.xiaofan0408.v1;

import com.xiaofan0408.v1.base.Pair;
import com.xiaofan0408.v1.model.response.FetchData;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.beanutils.BeanUtils;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

@Slf4j
@Getter
@Setter
public class Application {

    private String appId;

    private Map<String,Instance> instances;

    private long latestTimestamp;

    private ReadWriteLock readWriteLock;

    public Application(String appId) {
        this.appId = appId;
        this.instances = new ConcurrentHashMap<>();
        this.readWriteLock = new ReentrantReadWriteLock();
    }

    public Pair<Instance,Boolean> addInstance(Instance instance, long latestTimestamp) {

        this.readWriteLock.writeLock().lock();
        boolean isNew = true;
        try {
            Instance appIns = this.instances.get(instance.getHostname());
            if (appIns != null) {
                isNew = false;
                instance.setUpTimestamp(appIns.getUpTimestamp());
                if (instance.getDirtyTimestamp() < appIns.getDirtyTimestamp()) {
                    log.info("register exist dirty timestamp ");
                    instance = appIns;
                }
            }
            this.instances.put(instance.getHostname(),instance);
            upLatestTimestamp(latestTimestamp);
            return new Pair<>(instance,isNew);
        }finally {
            this.readWriteLock.writeLock().unlock();
        }
    }

    //获取*Instance信息
//status=1 return up 实例
    public FetchData getInstance(int status, long latestTime) {
        readWriteLock.readLock().lock();
        FetchData fetchData = new FetchData(this.latestTimestamp);
        try {
            if (latestTime >= this.latestTimestamp) {
                return null;
            }
            boolean exists = false;
            for (Instance instance : instances.values()) {
                if ((status&instance.getStatus()) > 0) {
                    exists = true;
                }
                Instance newInstance = new Instance();
                BeanUtils.copyProperties(newInstance, instance);
                fetchData.getInstances().add(newInstance);
            }
            if (!exists){
                return null;
            }
        } catch (Exception e) {
           log.info("异常",e);
        } finally {
            readWriteLock.readLock().unlock();
        }
        return fetchData;
    }

    private void upLatestTimestamp(long latestTimestamp) {
        if (latestTimestamp <= this.latestTimestamp) {
            latestTimestamp = this.latestTimestamp + 1;
        }
        this.latestTimestamp = latestTimestamp;
    }

    @Override
    public String toString() {
        return "Application{" +
                "appId='" + appId + '\'' +
                ", instances=" + instances +
                ", latestTimestamp=" + latestTimestamp +
                '}';
    }


}
