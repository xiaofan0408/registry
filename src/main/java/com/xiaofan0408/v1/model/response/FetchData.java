package com.xiaofan0408.v1.model.response;

import com.xiaofan0408.v1.Instance;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class FetchData {

    private List<Instance> instances;

    private long latestTimestamp;

    public FetchData() {
        instances = new ArrayList<>();
        latestTimestamp = 0;
    }

    public FetchData(long latestTimestamp) {
        instances = new ArrayList<>();
        this.latestTimestamp = latestTimestamp;
    }

    public FetchData(List<Instance> instances, long latestTimestamp) {
        this.instances = instances;
        this.latestTimestamp = latestTimestamp;
    }

    @Override
    public String toString() {
        return "FetchData{" +
                "instances=" + instances +
                ", latestTimestamp=" + latestTimestamp +
                '}';
    }
}
