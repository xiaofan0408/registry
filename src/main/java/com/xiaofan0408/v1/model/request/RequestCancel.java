package com.xiaofan0408.v1.model.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RequestCancel {

    private String env;

    private String appId;

    private String hostname;

    private long latestTimestamp;

    private boolean replication;

}
