package com.eincs.android.kupid.event;

/**
 * 특정 모델이 데이터베이스에서 변경되었을때 발생하는 이벤트
 */
public class KModelChangedEvent {

    private final Class<?>[] modelTypes;

    public KModelChangedEvent(Class<?>[] types) {
        this.modelTypes = types;
    }

    public Class<?>[] getModelTypes() {
        return modelTypes;
    }

    public boolean isChangedType(Class<?> type) {
        for (Class<?> modelType : modelTypes) {
            if (modelType.equals(type)) {
                return true;
            }
        }
        return false;
    }
}
