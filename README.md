# withpet-android
강서구 빅데이터 활용 공모전 팀 WithPet입니다.

https://www.thinkcontest.com/thinkgood/user/contest/view.do?querystr=GzRHbI4zt1hCORdUh%2F66j0iG4IAijLib1TwtvRBRMJQ%3D

<image src = "https://www.thinkcontest.com/thinkgood/common/display.do?filepath=contest_poster/image/&filename=5c35056e7c70d60b393a728803a2bdf368534959.jpg"/>

# 서비스 소개
반려동물을 기르는 강서구 주민들을 위한 올바른 반려동물 양육문화 확산과, 반려동물과 강서구민의 편리한 인프라를 위한 강서구 지역기반 반려동물 커뮤니티 플랫폼(강서멍냥)입니다.

# 서비스 스크린샷
![image](https://user-images.githubusercontent.com/70135188/232315879-f57d54ce-276f-4a1d-99e0-f2cbe8d95f9d.png)

![image](https://user-images.githubusercontent.com/70135188/232316018-9d515a5c-9ef1-4e1a-904c-4ac7e787f5d0.png)

# 프로젝트 구조
targetSdk 33

minSdk 23

구글 권장 안드로이드 아키텍처를 사용했다. 

![image](https://user-images.githubusercontent.com/70135188/232317717-39f6a7ad-1b94-46be-8cae-8f7599692533.png)

# 사용된 라이브러리
`Tool & Language`
- Android Framework
- Kotlin

`Jetpack`

- Lifecycles
- Navigation
- ViewModel

`UI`

- ConstraintLayout
- BottomSheet

`Third Party`

- Coroutine
- Flow
- Naver Map API
- Firebase Realtime Database

# 개발하며 공부했던 부분

Fireabase Realtime Database를 사용하면서 데이터를 요청했을 때, 실시간으로 데이터의 변화를 감지하여 콜백으로 데이터들이 들어왔다. 처음 async, await으로 값을 대기하여 수신하려고 했지만, 계속 받아오지 못했다. 그 이유는 데이터를 받는 부분이 callback 형식으로 이루어져 있었다. callback을 데이터를 받는 비동기 설정을 해주려면, `callbackFlow`를 사용해줘야한다. 

```kotlin
    fun getMapResponse(): Flow<DataSnapshot> = callbackFlow {
        withContext(ioDispatcher) {
            val mapResponse = RealtimeDatabase.getDatabase().getReference("map")

            mapResponse.addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    trySend(dataSnapshot)
                }

                override fun onCancelled(error: DatabaseError) {
                    close(error.toException())
                }
            })
        }
        awaitClose()
    }
```




# 탈락 및 회고
정말 아쉽게도(?) 출품작에 선정되지 못했다고 한다.ㅎㅎ 

강서구에선 반려동물관련 정책들을 많이 시행하고 있었다. 반려동물 관련된 기사를 많이 찾아보고, 기사와 정책 기반으로 사용자들의 니즈를 파악에 강서멍냥 앱을 기획과 개발을 진행했다.

데이터 분석 결과 시각화도 잘 나왔고, 분석 보고서도 괜찮게 작성했다고 생각했는데 탈락한 이유가 궁금했다.

정말 잘 작성했다고 생각하는 분석보고서 : https://www.notion.so/dev-jiwon/d6dc19fc4e7a4fbd81f851d385e0786d?pvs=4

![image](https://user-images.githubusercontent.com/70135188/232319787-4a9cc7fb-1e3b-4739-8fd7-a0c687f1e273.png) 진상 한명 추가요~ㅋ

탈락해서 아쉽지만 준비하면서 부족한 부분을 공부했고 추억을 많이 쌓았다. `callbackFlow`를 알아간만 것으로 지식 + 1!! 

첫 공모전이었는데, 욕심과 기대한만큼 결과가 잘 안나와서 아쉬웠지만, 재밌게 잘 즐긴것 같다!!
