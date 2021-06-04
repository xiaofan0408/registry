package com.xiaofan0408.v1.model.request;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class RequestRenew {

    private String env;

    private String appId;

    private String hostname;

    private long dirtyTimestamp;

    private boolean replication;

}
