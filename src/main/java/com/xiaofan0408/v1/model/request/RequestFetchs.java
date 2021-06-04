package com.xiaofan0408.v1.model.request;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class RequestFetchs {

    private String env;

    private List<String> appId;

    private int status;
}
