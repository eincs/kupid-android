package com.eincs.android.kupid.controller;

import com.eincs.android.kupid.model.KCredentialModel;
import com.google.common.util.concurrent.ListenableFuture;

/**
 * 실제 뷰에서 일어나는 사용자 액션 등 여러가지 이벤트를 처리하는 컨트롤러. 실제 구현체는 여러개가 올수 있는데, 이 인터페이스를 구현하면
 * 된다. 컨트롤러는 완벽하게 비동기적으로 동작하므로, 실제 UI-Thread는 절대 점유하지 않는다. ListenableFuture을
 * 이용하여 비동기적인 처리가 끝난 후 결과를 리턴했을때 처리를 할 수 있다.
 */
public interface Controller {

	/**
	 * 로그인을 한다. 내부적으로는 KUPID 아이디, 비밀번호를 받아 서버로 요청을 보내고 아이디, 패스워드가 서버측 비밀키로 암호화된
	 * 액세스토큰을 받아 클라이언트 내부 저장소에 저장한다.
	 * @param id KUPID 아이디
	 * @param password KUPID 패스워드
	 * @return
	 */
	public ListenableFuture<KCredentialModel> startLogin(String id, String password);

	/**
	 * 앱에서 사용자를 로그아웃 시킨다. 
	 */
	public ListenableFuture<Void> startLogout();

}
