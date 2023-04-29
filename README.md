# spring-firstweek
# Spring Lv.1

<aside>
🏁 **Goal:  "스프링 부트로 로그인 기능이 없는 나만의 항해 블로그 백엔드 서버 만들기"**

</aside>

- 학습 과제를 끝내고 나면 할 수 있어요!
    1. Java를 활용하여 필요한 클래스를 구상할 수 있어요.
    2. Lombok과 JPA를 이용하여 원하는 데이터베이스를 만들고 활용할 수 있어요.
    3. Spring Boot를 기반으로 CRUD(Create, Read, Update, Delete) 기능이 포함된 REST API를 만들 수 있어요.

<aside>
🤔 **notification : 과제 주의사항**

</aside>

1. Entity를 그대로 반환하지 말고, DTO에 담아서 반환해주세요!
2. 프론트엔드와 백엔드가 느슨하게 결합하는 환경이 ”최근에는” 더 일반적이라고 말씀드렸죠? 앞으로 남은 강의 예제와 실습에서는 html/css/js 즉 뷰도 같이 반환 하겠지만, 과제에는 여러분들이 서버 로직에 더 집중하실 수 있도록 JSON을 반환하는 API형태로 진행하려고 합니다.
3. 눈으로 직접 확인 할 수 있었던 view와는 다르게, 여러분들이 과제를 진행하시려면 서버가 반환하는 결과값을 더 쉽게 확인 하실 수 있는 도구가 필요합니다. 바로 PostMan 입니다. 사용법은 아래 페이지에서 꼭 확인해주세요!

[PostMan 사용법](https://www.notion.so/PostMan-c890eba10f484460b28aa89c354193bd)

<aside>
🚩 **Requirement:  과제에 요구되는 사항이에요.**

</aside>

<aside>
⚠️ **아래의 요구사항에 맞는 API 명세서를 작성해 보고 프로젝트를 생성해 직접 구현해 보세요!**

</aside>

<aside>
✅ 서비스 완성 요구사항

</aside>

1. 아래의 요구사항을 기반으로 Use Case 그려보기
    - 손으로 그려도 됩니다.
    - cf. [https://narup.tistory.com/70](https://narup.tistory.com/70)
2. 전체 게시글 목록 조회 API
    - 제목, 작성자명, 작성 내용, 작성 날짜를 조회하기
    - 작성 날짜 기준 내림차순으로 정렬하기
3. 게시글 작성 API 
    
    **`월요일 17:00 까지 완료`**
    
    - 제목, 작성자명, 비밀번호, 작성 내용을 저장하고
    - 저장된 게시글을 Client 로 반환하기
4. 선택한 게시글 조회 API 
    - 선택한 게시글의 제목, 작성자명, 작성 날짜, 작성 내용을 조회하기 
    (검색 기능이 아닙니다. 간단한 게시글 조회만 구현해주세요.)
5. 선택한 게시글 수정 API
**`화요일 17:00까지 완료`**
    - 수정을 요청할 때 수정할 데이터와 비밀번호를 같이 보내서 서버에서 비밀번호 일치 여부를 확인 한 후
    - 제목, 작성자명, 작성 내용을 수정하고 수정된 게시글을 Client 로 반환하기
6. 선택한 게시글 삭제 API
**`수요일 17:00까지 완료`**
    - 삭제를 요청할 때 비밀번호를 같이 보내서 서버에서 비밀번호 일치 여부를 확인 한 후
    - 선택한 게시글을 삭제하고 Client 로 성공했다는 표시 반환하기

<aside>
📌 개발한 API 테스트 해보기!

</aside>

- Postman을 이용해 HTTP 메서드 요청을 시도해 보세요!

<aside>
🔥 꼭 직접 API 명세서를 작성해 본 다음에 확인하세요!!

</aside>

- API 명세서 예시
    
    
    | Method | URL | Request | Response |
    | --- | --- | --- | --- |
    | GET | /api/posts | - | {
    {
    "createdAt": "2022-07-25T12:43:01.226062”,
    "modifiedAt": "2022-07-25T12:43:01.226062”,
    "id": 1,
    "title": "title2",
    "content": "content2",
    "author": "author2"
    },
    {
    "createdAt": "2022-07-25T12:43:01.226062”,
    "modifiedAt": "2022-07-25T12:43:01.226062”,
    "id": 2,
    "title": "title",
    "content": "content",
    "author": "author"
    }
    …
    } |
    | GET | /api/post/{id} | - | {
    "createdAt": "2022-07-25T12:43:01.226062”,
    "modifiedAt": "2022-07-25T12:43:01.226062”,
    "id": 1,
    "title": "title2",
    "content": "content2",
    "author": "author2"
    } |
    | POST | /api/post | {
    "title" : "title",
    "content" : "content",
    "author" : "author",
    "password" : "password"
    } | {
    "createdAt": "2022-07-25T12:43:01.226062”,
    "modifiedAt": "2022-07-25T12:43:01.226062”,
    "id": 1,
    "title": "title",
    "content": "content",
    "author": "author"
    } |
    | PUT | /api/post/{id} | {
    "title" : "title2",
    "content" : "content2",
    "author" : "author2",
    "password" :"password2"
    } | {
    "createdAt": "2022-07-25T12:43:01.226062”,
    "modifiedAt": "2022-07-25T12:43:01.226062”,
    "id": 1,
    "title": "title2",
    "content": "content2",
    "author": "author2"
    } |
    | DELETE | /api/post/{id} | {
    "password" :"password"
    } | {
    "success": true,
    } |

<aside>
❓ **Why: 과제 제출시에는 아래 질문을 고민해보고 답변을 함께 제출해주세요.**

</aside>

1. 수정, 삭제 API의 request를 어떤 방식으로 사용하셨나요? (param, query, body)
2. 어떤 상황에 어떤 방식의 request를 써야하나요?
3. RESTful한 API를 설계했나요? 어떤 부분이 그런가요? 어떤 부분이 그렇지 않나요?
4. 적절한 관심사 분리를 적용하였나요? (Controller, Repository, Service)
5. API 명세서 작성 가이드라인을 검색하여 직접 작성한 API 명세서와 비교해보세요!

<aside>
⚠️ **Warning : 꼭 지켜야 할 것과 하지 않아도 되는 것!**

</aside>

- 배포는 아직 배우지 않았어요. 프로젝트 Github 링크를 제출해주세요. (배포는 다음주에 배울 예정이에요!)
- 이것은 꼭 지켜주세요(Do's)
    - 과제 요구 사항은 모두 지켜주세요. 특정 기능을 임의로 배제하면 안 됩니다!
- 이것은 하지 않으셔도 돼요(Don'ts)
    - 과제 추가 기획 때문에 고민하지 마세요. 위에 작성된 과제 요구 사항만 지키면 됩니다!
        
        → 좋은 예 (⭕ ): "아하, 결국 Spring Boot로 기본 CRUD 가 가능한 서비스를 만들면 되는거구나!"
        
        → 나쁜 예 (❌ ): "로그인 없어도 되나? 다른 기능이 더 있어야 하지 않을까?"
