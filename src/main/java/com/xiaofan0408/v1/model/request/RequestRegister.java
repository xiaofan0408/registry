package com.xiaofan0408.v1.model.request;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class RequestRegister {

    private String env;

    private String appId;

    private String hostname;

    private List<String> addrs;

    private int status;

    private String version;

    private long latestTimestamp;

    private long dirtyTimestamp;

    private boolean replication;
}
