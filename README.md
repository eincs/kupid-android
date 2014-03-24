## KUPID-Notifier

![ScreenShot](https://raw2.github.com/eincs/kupid-android/master/docs/screenshot.jpg)

이 프로젝트는 고려대학교의 지식기반 포탈 시스템(KUPID)에 새로 올라오는 공지사항, 과제물 등에 대해
푸시 알림을 주는 것을 시뮬레이션하는 안드로이드 어플리케이션 입니다.
기본적으로는 UI을 어떻게 가져갈지 일환으로 만들어진 것으로,
고려대학교 정보통신대학 컴퓨터통신공학부에서 2013년 2학기 과목으로 개설된 인간과 컴퓨터 상호작용 입문의 텀프로젝트 결과물입니다.

### Setting up a develop environment

본 안드로이드 프로젝트는 Maven기반으로 구성되어 있습니다. Maven기반으로 구성된 프로젝트지만 Maven으로 빌드는 안합니다. 이클립스 ADT를 이용하여 빌드하세요. Maven은 그냥 디펜던시 해결과 몇 가지 플러그인의 도움을 받기위해 사용합니다.

#### 개발 환경 구성을 시작하기 전에 필요한 것들

이 프로젝트의 개발 환경을 구성하기 위해서는 다음과 같은 것들이 필요합니다.

- [Eclipse]: 본 프로젝트는 이클립스 기반으로 만들어졌습니다.(*그냥 ADT에 포함된 이클립스를 사용해도 됨)
- [Android SDK]: 안드로이드 개발하기 위해 필요한 SDK입니다.
- [Android Developer Tools](http://developer.android.com/sdk/installing/installing-adt.html) (자세한 방법은 링크를 참고)
- [Git]: 몇 가지 오픈소스를 가져다 쓰는데 이클립스 ADT가 aar포맷을 지원하지 않으므로 git에서 제공하는 submodule을 통해 가져다 쓰고 있습니다.
- [m2e]: 종속성을 해결하고 여러가지 기능을 위해 Maven 프로젝트로 구성되어 있습니다. 따라서 m2e 플러그인을 이클립스에 설치하셔야 합니다. Eclipse 다운로드 할때 J2EE를 다운받으셨으면 m3e가 기본 내장되어 있으므로 따로 신경 쓰지 않아도 됩니다.
- [m2e-android]: 안드로이드 프로젝트를 Maven기반으로 구성하였을때 이클립스에 로드하기 위한 이클립스 플러그인 입니다.

#### 소스를 이클립스로 Import하기

현재 리포지터리의 소스들을 이클립스로 Import하여 개발을 하기 위한 방법은 다음과 같습니다.

1. master 브렌치를 클론 받습니다.

	git clone -b master https://github.com/eincs/kupid-android.git
		
2. 서브모듈을 설정합니다.

	cd kupid-android
	git submodule init
	git submodule update 
		
3. 이클립스에 Maven 프로젝트들을 Import 합니다.
	- Package Explorer 오른쪽 클릭하여 Import를 선택
	- Existing Maven Project를 선택하여, 소스를 클론 받은 폴더를 선택
	- 그냥 모든 프로젝트를 Import하면 됩니다.
		
5. 필요한 경우, kupid-android 프로젝트의 Target API를 최신의 Google API로 바꾸어 줍니다.

### Notice

- 이 프로그램은 실제로 동작하지 않습니다. 과목의 텀프로젝트 요구사항에 맞게 UI등 껍데기만 만들어져 있다고 생각하시면 됩니다.
- 조금 더 자세한 것은 `docs`폴더 내의 문서를 확인하세요.
- 본 프로젝트에서 사용하는 오픈소스에 대한 라이센스는 각 리포지터리 혹은 submodule update 이후 각 폴더에 있는 라이센스 파일을 확인하세요.
- 프로젝트 설정을 보시면 사설 public repository를 이용하고 있는데, 함부로 배포는 하지 말아주세요.
- 대학교 텀 프로젝트로 만든 것으로 프로그램의 완성도에 대한 희망은 갖지 말아주세요.
- 가져다 쓰는건 맘대로지만 그 외 사용상 여러 가지 문제점에 대한 것은 신경쓰지 않습니다.

### License

별도의 표기가 없는 경우 아래의 라이센스에 따라 코드를 사용해주세요.

	/*
	 *             DO WHAT THE FUCK YOU WANT TO PUBLIC LICENSE 
	 *                     Version 2, December 2004
	 * 
	 *  Copyright (C) 2013 James Lee <roth2520@gmail.com>
	 *   
	 *  Everyone is permitted to copy and distribute verbatim or modified
	 *  copies of this license document, and changing it is allowed as long
	 *  as the name is changed.
	 * 
	 *             DO WHAT THE FUCK YOU WANT TO PUBLIC LICENSE
	 *    TERMS AND CONDITIONS FOR COPYING, DISTRIBUTION AND MODIFICATION
	 * 
	 *   0. You just DO WHAT THE FUCK YOU WANT TO. 
	 */
	 
[git]: http://git-scm.com/
[m2e]: http://www.eclipse.org/m2e/
[m2e-android]: http://rgladwell.github.io/m2e-android/
[eclipse]: http://www.eclipse.org/downloads/
[Android SDK]: http://developer.android.com/sdk/index.html
