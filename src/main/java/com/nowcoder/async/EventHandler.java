package com.nowcoder.async;

import java.util.List;

/**
 * 统一事件接口
 * Created by CycloneBoy on 2017/9/3.
 */
public interface EventHandler {
    void doHandler(EventModel model);

    List<EventType> getSupportEventTypes();
}
