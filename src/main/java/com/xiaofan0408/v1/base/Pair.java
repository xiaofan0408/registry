package com.xiaofan0408.v1.base;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Pair<T,V> {

    private T t1;

    private V t2;

    public Pair(T t1, V t2) {
        this.t1 = t1;
        this.t2 = t2;
    }
}
