# Cookie & Session

## http protocol의 특징

- 클라이언트가 서버에 요청
- 서버는 요청에 대한 처리를 한 후 클라이언트에 응답
- 응답 후 연결을 해제 → stateless
    - 지속적인 연결로 인한 자원 낭비를 줄이기 위해 연결을 해제한다.
    - 그러나 클라이언트와 서버가 연결 상태를 유지해야하는 경우 문제가 발생(로그인 정보 등..)
    - 즉 클라이언트 단위로 상태 정보를 유지해야 하는 경우 **Cookie와 Session**이 사용된다 → http protocol의 약점을 보완하기 위해 사용

## Cookie

### javax.servlet.http.Cookie

- 서버에서 사용자에 컴퓨터에 저장하는 정보 파일
- 사용자가 별도의 요청을 하지 않아도 브라우저는 request시 Request Header에 넣어 자동으로 서버에 전송
- key와 value로 구성되고 String 형태로 이루어져 있음
- 브라우저마다 저장되는 쿠키는 다르다.(서버에서는 브라우저가 다르면 다른 사용자로 인식)

### 사용 목적

- 세션 관리: 사용자 아이디, 접속 시간, 장바구니 등 서버가 알아야 할 정보 저장
- 트래킹: 사용자의 행동과 패턴을 분석하고 기록
- 개인화: 사용자마다 다르게 그 사람에 적절한 페이지를 보여줄 수 있다.

### 사용 예

- 자동로그인, 일주일 간 다시 보지 않기, 검색한 상품 광고에 추천, 장바구니 등

### 구성 요소

- 이름: 여러 개의 쿠키가 client의 컴퓨터에 저장되므로 각 쿠키를 구별하는 데 사용되는 이름
- 값: 쿠키의 이름과 매핑되는 값
- 유효기간: 쿠키의 유효기간
- 도메인: 쿠키를 전송할 도메인
- 경로: 쿠키를 전송할 요청 경로

### 동작 순서

- 클라이언트가 페이지를 요청
- WAS는 쿠키를 생성
- http 헤더에 쿠키를 넣어 응답
- 브라우저는 넘겨받은 쿠키를 PC에 저장하고, 다시 WAS가 요청할 때 요청과 함께 쿠키를 전송
- 브라우저가 종료되어도 쿠키의 만료 기간이 남아있다면 클라이언트는 계속 보관
- 동일 사이트 재방문시 클라이언트의 PC에 해당 쿠키가 있는 경우, 요청 페이지와 함께 쿠키를 전송

### 특징

- 이름, 값, 만료일(저장 기간 설정), 경로 정보로 구성되어 있다.
- 클라이언트에 총 300개의 쿠키를 저장할 수 있다.
- 하나의 도메인 당 20개의 쿠키를 가질 수 있다.
- 하나의 쿠키는 4KB까지 저장 가능하다.

### 설정

- name이 userid인 쿠키의 값은 ssafy이고, 유효기간은 2020년 10월 15일이다.

![Untitled](https://user-images.githubusercontent.com/102662024/229546018-a9ee5877-546f-4811-92e7-f07d02a583d5.png)

### 주요 기능

| 기능 | method |
| --- | --- |
| 생성 | Cookie cookie = new Cookie(String name, String value); |
| 값 변경/얻기 | cookie.setValue(String value); / String value = cookie.getValue(); |
| 사용 도메인 지정/얻기 | cookie.setDomain(String domain); / String domain = cookie.getDomain(); |
| 값 범위 지정/얻기 | cookie.setPath(String path); / String path = cookie.getPath(); |
| 쿠키의 유효기간 지정/얻기 | cookie.setMathAge(int expiry); / int expiry = cookie.getMaxAge();
cookie 삭제: cookie.setMaxAge(0); |
| 생성된 쿠키를 클라이언트에 전송 | response.addCookie(cookie); |
| 클라이언트에 저장된 쿠키 얻기 | Cookie cookies[] = request.getCookies(); |

## HttpSession

### javax.servlet.http.HttpSession

- 방문자가 웹 서버에 접속해 있는 상태를 하나의 단위로 보고 그것을 세션이라 한다.
- WAS의 메모리에 Object의 형태로 저장
- 메모리가 허용하는 용량까지 제한 없이 저장 가능

### 사용 예

- 사이트 내에서 화면을 이동해도 로그인이 풀리지 않고 유지, 장바구니 등

### 동작 순서

- 클라이언트가 페이지를 요청
- 서버로 접근한 클라이언트의 Request-Header 필드의 Cookie를 확인하여, 클라이언트가 해당 session-id를 보냈는지 확인
- session-id가 존재하지 않는다면, 서버는 session-id를 생성해 클라이언트에게 돌려준다.
- 서버에서 클라이언트로 돌려준 session-id를 쿠키를 사용해 서버에 저장. 쿠키 이름: JSESSIONID
- 클라이언트는 재접속 시, 이 쿠키(JSESSIONID)를 이용하여 session-id 값을 서버에 전달

### 특징

- 웹 서버에 웹 컨테이너의 상태를 유지하기 위한 정보를 저장
- 웹 서버에 저장되는 쿠키(=세션 쿠키)
- 브라우저를 닫거나, 서버에서 세션을 삭제했을 때만 삭제가 되므로, 쿠키보다 비교적 보안이 좋다.
- 저장 데이터에 제한이 없다.
- 각 클라이언트 고유 session id를 부여한다.
- session id로 클라이언트를 구분하여 각 클라이언트 요구에 맞는 서비스 제공

### 설정

- 브라우저 당 하나의 JSESSIONID를 할당받음
- 아이디 또는 닉네임과 같이 로그인을 했을 경우 자주 사용되는 정보를 session에 저장하면 DB를 접근할 필요가 없으므로 효율적이다.

![Untitled 1](https://user-images.githubusercontent.com/102662024/229546014-0c9d7f66-1fd9-4250-abb7-5695deb7041c.png)

### 주요 기능

| 기능 | method |
| --- | --- |
| 생성 | HttpSession session = request. getSession();
HttpSession session = request. getSession(false); |
| 값 저장 | session.setAttribute(String name, Object value); |
| 값 얻기 | Object obj = session.getAttribute(String name); |
| 값 제거 | session.removeAttribute(String name); // 특정 이름의 속성 제거
session.invalidate(); // binding되어있는 모든 속성 제거 |
| 생성시간 | long ct = session.getCreationTime(); |
| 마지막 접근 시간 | lont lat = session.getLastAccessedTime(); |

## Cookie & Session 정리

|  | Cookie | Session |
| --- | --- | --- |
| Type | javax.servlet.http.HttpSession (Interface) | javax.servlet.http.Cookie (class) |
| 저장 위치 | 서버의 메모리에 Object로 저장 | 클라이언트 컴퓨터에 파일로 저장 |
| 저장 형식 | Object는 모두 가능(일반적으로 DTO, List 등 저장) | 파일에 저장되기 때문에 String 형태 |
| 사용 예 | 로그인 시 사용자 정보, 장바구니 등 | 최근 본 상품 목록, 아이디 저장(자동로그인), 팝업 메뉴에서 “오늘은 그만 열기” 등 |
| 용량 제한 | 제한 없음 | 도메인당 20개, 쿠키당 4KB |
| 만료 시점 | 알 수 없음(클라이언트가 로그아웃하거나, 일정 시간동안 session에 접근하지 않을 경우[만료 시간은 web.xml에 설정]) | 쿠키 저장시 설정(설정이 없을 경우 브라우저 종료시 만료) |
| 공통 | 전역에 저장하기 때문에 프로젝트 내 모든 JSP에서 사용 가능
Map 형식으로 관리하기 때문에 key의 중복을 허용하지 않는다. |  |
