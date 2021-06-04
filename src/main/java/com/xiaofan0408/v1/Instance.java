package com.xiaofan0408.v1;

import com.xiaofan0408.v1.model.request.RequestRegister;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class Instance {

    private String env;

    private String appId;

    private String hostname;

    private List<String> addrs;

    private String version;

    private int status;

    private long regTimestamp;

    private long upTimestamp;

    private long renewTimestamp;

    private long dirtyTimestamp;

    private long latestTimestamp;

    public Instance() {
    }

    public Instance(RequestRegister requestRegister) {
        long now = System.nanoTime();
        this.env = requestRegister.getEnv();
        this.appId = requestRegister.getAppId();
        this.hostname = requestRegister.getHostname();
        this.addrs = requestRegister.getAddrs();
        this.status = requestRegister.getStatus();
        this.regTimestamp = now;
        this.upTimestamp = now;
        this.renewTimestamp = now;
        this.dirtyTimestamp = now;
        this.latestTimestamp = now;
    }

    @Override
    public String toString() {
        return "Instance{" +
                "env='" + env + '\'' +
                ", appId='" + appId + '\'' +
                ", hostname='" + hostname + '\'' +
                ", addrs=" + addrs +
                ", version='" + version + '\'' +
                ", status=" + status +
                ", regTimestamp=" + regTimestamp +
                ", upTimestamp=" + upTimestamp +
                ", renewTimestamp=" + renewTimestamp +
                ", dirtyTimestamp=" + dirtyTimestamp +
                ", latestTimestamp=" + latestTimestamp +
                '}';
    }
}
