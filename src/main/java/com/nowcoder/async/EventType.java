package com.nowcoder.async;

/**
 * Created by CycloneBoy on 2017/9/3.
 */
public enum EventType {
    LIKE(0),
    COMMENT(1),
    LOGIN(2),
    MAIL(3);

    private int value;
    EventType(int value){
        this.value = value;
    }

    public int getValue(){
        return this.value;
    }
}
