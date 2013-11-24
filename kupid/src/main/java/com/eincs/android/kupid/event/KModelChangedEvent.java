package com.eincs.android.kupid.event;

/**
 * 특정 모델이 데이터베이스에서 변경되었을때 발생하는 이벤트
 */
public class KModelChangedEvent {

	private final Class<?> modelType;
	
	public KModelChangedEvent(Class<?> modelType) {
		this.modelType = modelType;
	}

	public Class<?> getModelType() {
		return modelType;
	}
}
