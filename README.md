싸피인의 소통을 위한 커뮤니티 서비스

## 프로젝트 소개 및 기능

저희 프로젝트는 싸피인의 소통을 위한 커뮤니티 서비스입니다

주요 기능은 게시판, 투표, 모임 기능입니다. 이러한 기능을 통해 싸피인들에게 지속적인 교류활동과 다양한 네트워크를 형성할 수 있는 공간을 마련하고자 합니다.

## 기대효과

- 동기 및 선후배간 네트워크 활성화
- 싸피 수료 후에도 지속적인 유대감 형성
- 선호도 조사를 통한 개발직군의 분위기 파악 용이
- 함께할 프로젝트 팀원 모집 시 용이

## 주요 기능

- 게시판
    - 자유게시판
    - 질문게시판
    - 꿀팁게시판
- 놀이터
    - 밸런스 게임
    - 선호도 조사

# 팀원소개

강동석: 팀장, BE, 선호도조사

김종우: FE, 공지사항, 게시판

박소율: BE, 게시판

임형준: BE, 밸런스게임, CI/CD

정언용: FE, 회원관리

최정민: BE, 회원관리, CI/CD


# 기술 스택

- **Front-End**
    
     **VS Code**
    
    **JavaScript**
    
    **React 18.0**
    
    **Redux 4.2**
    
    **React Router 6.3**
    
    **Axios**
    
- back
    
    Spring Boot(Java) 2.6.2
    
    Amazon RDS
    
    Intellij
    
    JPA
    
    JWT
    
    Postman
    
    Amazon S3
    
- ci/cd
    
    Gitlab
    
    AWS EC2 Ubuntu 20.04.4
    
    Docker 20.10.12
    
    Docker-Compose 1.29.2
    
    Nginx 1.18.0
    
    Jenkins 2.332.3
    
- 협업툴
    
    JIRA
    
    Notion
    
    Gitlab

시스템 구성도


# ERD 및 테이블 소개

![Untitled](./backend/ssayeon_erd.png)

1. 사용자 정보를 저장하는 `User` 테이블
2. 사용자 간 메시지를 저장하기 위한 `Message` 테이블
3. 사용자가 쓴 게시글에 달린 덧글이나 좋아요의 기록을 저장하기 위한 `Alarm` 테이블
4. 자유게시판, 질문게시판, 꿀팁게시판을 분류하기 위한 `Board` 테이블
5. 게시글 작성 시 카테고리 선택을 위한 `Category` 테이블(ex. CS지식, 알고리즘)
6. 해시태그를 위한 `Tag` 테이블(ex. Python, java, javascript)
7. 선호도 조사를 위한 `Preference` 테이블
    1. 선호도 조사 선택지를 저장하기 위한 `PreferenceOptions` 테이블
    2. 유저가 선택한 선호도 조사 선택지를 저장하기 위한 `PreferenceOptionsUserSelected` 테이블
    3. 선호도 조사 덧글을 저장하기 위한 `PreferenceComments` 테이블
    4. 선호도 조사 덧글 좋아요를 위한 `PreferenceCommentsLikes` 테이블
8. 밸런스 게임을 위한 `Balance` 테이블
    1. 유저가 선택한 선호도 조사 선택지를 저장하기 위한 `BalanceSelected` 테이블
    2. 선호도 조사 덧글을 저장하기 위한 `BalanceComments` 테이블
    3. 선호도 조사 덧글 좋아요를 위한 `BalanceCommentsLikes` 테이블
9. 싸피생 인증을 위한 교육생 정보가 저장된 `UserCertification` 테이블
10. 공지사항을 저장하기 위한 `Notification` 테이블


# 기능 상세 설명

[API 명세서](https://documenter.getpostman.com/view/11279067/UyxbsAPd)

## 회원

### 회원 인증

- 회원 가입(싸피생 인증, 이메일 인증, 닉네임 중복 검사, 비밀번호 확인)
- 로그인
- 토큰 및 유저 정보 localStorage 저장

### 회원 관리

- 프로필 조회
- 기술 스택 및 회사 검색
- 회원 정보 수정, 비밀번호 변경
- 알람 및 쪽지
- 회원 검색 및 탈퇴

## 게시판

### 자유 게시판

- 게시글 생성, 삭제, 수정
    - 댓글 기능
    - 좋아요 기능

### 꿀팁 게시판

- 게시글 생성, 삭제, 수정

> 질문 게시판은 추후 구현 예정
> 

## 밸런스게임

- 밸런스 게임 생성, 삭제
    - 밸런스 게임 투표에 따른 투표 결과 반환

## 선호도조사

- 선호도조사 생성, 삭제
- 원형그래프를 통해 투표 결과를 한 눈에 파악
- 선호도조사 질문 검색


폴더 구조

- Back-end
    
    ```
    📦backend
     ┣ 📂src
     ┃ ┣ 📂main
     ┃ ┃ ┣ 📂java
     ┃ ┃ ┃ ┗ 📂a204
     ┃ ┃ ┃ ┃ ┗ 📂ssayeon
     ┃ ┃ ┃ ┃ ┃ ┣ 📂api
     ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂controller
     ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜ArticleController.java
     ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜AuthController.java
     ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜BalanceController.java
     ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜NotificationController.java
     ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜PreferenceController.java
     ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜UserController.java
     ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂request
     ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂article
     ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜ArticleAnswerCreateReq.java
     ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜ArticleAnswerUpdateReq.java
     ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜ArticleCreateReq.java
     ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜ArticleUpdateReq.java
     ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜CommentCreateReq.java
     ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂auth
     ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜AuthDuplicateNicknameReq.java
     ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜AuthJoinReq.java
     ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜AuthLoginReq.java
     ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜AuthVerifyEmailReq.java
     ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜AuthVerifyUserReq.java
     ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂balance
     ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜BalanceCommentsReq.java
     ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜ModifyBalanceReq.java
     ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜RegisterBalanceReq.java
     ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜RegisterPollReq.java
     ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂notification
     ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜NotificationCreateReq.java
     ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜NotificationUpdateReq.java
     ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂preference
     ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜PreferenceApiRequest.java
     ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜PreferenceCommentsApiRequest.java
     ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📂user
     ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜UserAlarmReadReq.java
     ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜UserEditPasswordReq.java
     ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜UserEditUserReq.java
     ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜UserFindUserRes.java
     ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜UserSendMessageReq.java
     ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂response
     ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂article
     ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜ArticleAnswerRes.java
     ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜ArticleCommentsRes.java
     ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜ArticleRes.java
     ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜BoardRes.java
     ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜CategoryRes.java
     ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜TagRes.java
     ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂auth
     ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜AuthJoinRes.java
     ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂balance
     ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜BalanceCommentsRes.java
     ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜GetAllBalanceRes.java
     ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜GetBalanceRes.java
     ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜GetBalanceStaticsRes.java
     ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂notification
     ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜NotificationShowListRes.java
     ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂preference
     ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜PreferenceApiResponse.java
     ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜PreferenceCommentsApiResponse.java
     ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜PreferenceOptionsApiResponse.java
     ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📂user
     ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜UserShowAlarmRes.java
     ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜UserShowMessageDetailRes.java
     ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜UserShowMessageListRes.java
     ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜UserShowMessageListView.java
     ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜UserShowMyPageRes.java
     ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜UserShowUserActivityRes.java
     ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜UserShowUserRes.java
     ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜UserUnreadMessageCntRes.java
     ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📂service
     ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜AlarmService.java
     ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜ArticleService.java
     ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜AuthService.java
     ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜BalanceService.java
     ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜EmailService.java
     ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜NotificationService.java
     ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜PreferenceCommentsLikesService.java
     ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜PreferenceCommentsService.java
     ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜PreferenceOptionsService.java
     ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜PreferenceOptionsUserSelectedService.java
     ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜PreferenceService.java
     ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜UserService.java
     ┃ ┃ ┃ ┃ ┃ ┣ 📂common
     ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂exceptions
     ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂handler
     ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜ErrorResponse.java
     ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜ExceptionsHandler.java
     ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜AlreadyExistException.java
     ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜CustomException.java
     ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜ForbiddenException.java
     ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜InternalServerException.java
     ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜NotExistException.java
     ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜NotJoinedUserException.java
     ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜UnAuthorizedException.java
     ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📂model
     ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂enums
     ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜ErrorMessage.java
     ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜Status.java
     ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📂response
     ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜AdvancedResponseBody.java
     ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜BaseResponseBody.java
     ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜PaginationResponseBody.java
     ┃ ┃ ┃ ┃ ┃ ┣ 📂config
     ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂auth
     ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜CurrentUser.java
     ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜CustomAuthenticationProvider.java
     ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜PrincipalDetails.java
     ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜PrincipalDetailsService.java
     ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂aws
     ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜AwsS3Config.java
     ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜S3Util.java
     ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂jwt
     ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜JwtAccessDeniedHandler.java
     ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜JwtAuthenticationEntryPoint.java
     ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜JwtFilter.java
     ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜TokenProvider.java
     ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜EmailConfig.java
     ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜QuerydslConfiguration.java
     ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜SecurityConfig.java
     ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜TomcatWebCustomConfig.java
     ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜WebConfig.java
     ┃ ┃ ┃ ┃ ┃ ┣ 📂db
     ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂entity
     ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂article
     ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜Article.java
     ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜ArticleAnswer.java
     ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜ArticleComments.java
     ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜ArticleCommentsLikes.java
     ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜ArticleHasTag.java
     ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜ArticleLikes.java
     ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜ArticleScrap.java
     ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜Board.java
     ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜Category.java
     ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜CategoryHasBoard.java
     ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜Tag.java
     ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂balance
     ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜Balance.java
     ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜BalanceComments.java
     ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜BalanceCommentsLikes.java
     ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜BalanceSelected.java
     ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜Poll.java
     ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂notification
     ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜Notification.java
     ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂preference
     ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜Preference.java
     ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜PreferenceComments.java
     ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜PreferenceCommentsLikes.java
     ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜PreferenceOptions.java
     ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜PreferenceOptionsUserSelected.java
     ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂user
     ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜Alarm.java
     ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜Message.java
     ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜Scrap.java
     ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜TechStack.java
     ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜User.java
     ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜UserCertification.java
     ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜UserHasTechStack.java
     ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜BaseEntity.java
     ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜Pagination.java
     ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📂repository
     ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂Notification
     ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜NotificationRepository.java
     ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂article
     ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜ArticleAnswerRepository.java
     ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜ArticleCommentsLikesRepository.java
     ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜ArticleCommentsRepository.java
     ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜ArticleHasTagRepository.java
     ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜ArticleLikesRepository.java
     ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜ArticleRepository.java
     ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜ArticleScrapRepository.java
     ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜BoardRepository.java
     ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜CategoryRepository.java
     ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜CommentRepository.java
     ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜TagRepository.java
     ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂preference
     ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜PreferenceCommentsLikesRepository.java
     ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜PreferenceCommentsRepository.java
     ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜PreferenceOptionsRepository.java
     ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜PreferenceOptionsUserSelectedRepository.java
     ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜PreferenceRepository.java
     ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂user
     ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜AlarmRepository.java
     ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜MessageRepository.java
     ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜TechStackRepository.java
     ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜UserCertificationRepository.java
     ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜UserHasTechStackRepository.java
     ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜UserRepository.java
     ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜BalanceCommentsLikesRepository.java
     ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜BalanceCommentsRepository.java
     ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜BalanceRepository.java
     ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜BalanceSelectedRepository.java
     ┃ ┃ ┃ ┃ ┃ ┗ 📜SsayeonApplication.java
     ┃ ┃ ┗ 📂resources
     ┃ ┃ ┃ ┣ 📜application.properties
     ┃ ┃ ┗ 📜email.properties
     ┣ 📜Dockerfile
     ┗ 📜build.gradle
    ```
    
- Front-end
    
    ```
    📦frontend
     ┣ 📂nginx
     ┃ ┗ 📜default.conf
     ┣ 📂public
     ┃ ┣ 📜background.jpeg
     ┃ ┣ 📜index.html
     ┃ ┣ 📜manifest.json
     ┃ ┗ 📜robots.txt
     ┣ 📂src
     ┃ ┣ 📂components
     ┃ ┃ ┣ 📂common
     ┃ ┃ ┣ 📂images
     ┃ ┃ ┣ 📂main
     ┃ ┃ ┃ ┣ 📂accounts
     ┃ ┃ ┃ ┣ 📂balance
     ┃ ┃ ┃ ┃ ┗ 📂css
     ┃ ┃ ┃ ┣ 📂boards
     ┃ ┃ ┃ ┃ ┣ 📂frees
     ┃ ┃ ┃ ┃ ┣ 📂notice
     ┃ ┃ ┃ ┃ ┣ 📂polls
     ┃ ┃ ┃ ┃ ┣ 📂qna
     ┃ ┃ ┃ ┃ ┗ 📂tips
     ┃ ┃ ┃ ┣ 📂preference
     ┃ ┃ ┃ 📂search
     ┃ ┣ 📂services
     ┃ ┣ 📂user
     ┃ ┣ 📜App.js
     ┃ ┣ 📜index.css
     ┃ ┣ 📜index.js
     ┃ ┣ 📜reportWebVitals.js
     ┃ ┣ 📜serviceWorker.js
     ┃ ┣ 📜setupTests.js
     ┃ ┗ 📜store.js
     ┣ 📜.env
     ┣ 📜.gitignore
     ┣ 📜Dockerfile
     ┣ 📜README.md
     ┣ 📜package-lock.json
     ┗ 📜package.json
    ```
