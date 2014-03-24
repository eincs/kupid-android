package com.eincs.android.kupid.event;

import de.greenrobot.event.EventBus;

public final class KEventBus {
    private KEventBus() {
    }

    private static final EventBus DEFAULT_EVENTBUS = new EventBus();

    public static EventBus getDefaultEventBus() {
        return DEFAULT_EVENTBUS;
    }
}
