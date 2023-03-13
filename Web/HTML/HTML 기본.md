# HTML 기본

## 웹 표준이란?

- 모든 브라우저에서 웹 서비스가 정상적으로 보여질 수 있도록 하는 것
- W3C(World Wide Web Consortium) - http://www.w3.org
- W3C에서 HTML5를 웹 표준으로 권고하고 웹 브라우저는 이를 따름

## HTML - 웹 페이지 문서 담당(구조)

- Hypertext Markup Language의 약자
- 웹에서 사용하는 문서 양식
- 문서에 하이퍼텍스트, 표, 목록, 비디오 등을 포함할 수 있는 tag를 사용
- 문서를 웹 브라우저에 표현할 때 tag를 사용

![Untitled](https://user-images.githubusercontent.com/102662024/224723844-9528f7f5-eb32-4d2d-be76-64eb1acc5bdb.png)

![Untitled 1](https://user-images.githubusercontent.com/102662024/224723789-afa736b3-97d1-4abb-977a-29e127b8621e.png)

## HTML5 특징

- HTML5는 지금도 개발 중에 있고, 다양한 기능이 추가됨
- 멀티미디어 용도 재생
    - 멀티미디어 요소를 별도의 플러그인 없이도 재생 가능
- 서버와 통신
    - 서버와 클라이언트 사이에 소켓 통신이 가능
- Semantic tag 추가
- 웹 사이트를 검색 엔진이 좀 더 빠르게 검색할 수 있도록 하기 위해 특정 tag에 의미를 부여하는 방식

## HTML 문서 구조

![Untitled 2](https://user-images.githubusercontent.com/102662024/224723812-bd3b669d-ea57-4460-a1a9-c774f9b7cadb.png)

## Web & HTML 작동 원리

- 서버는 클라이언트의 요청 내용을 분석하여 결과값을 HTML로 전송
- 서버는 결과값을 전송한 후 클라이언트와 연결 종료
- 클라이언트는 서버로부터 전달받은 HTML을 웹 브라우저에 표시
- 각 웹 브라우저는 브라우저 엔진이 내장되어 있고, 이 엔진이 tag를 해석하여 화면에 표현

![Untitled 3](https://user-images.githubusercontent.com/102662024/224723817-d60dbd81-2996-4cc6-a084-8cbb400b77d3.png)

## tag와 속성

- HTML 문서는 ‘tag’로 만들어진다.
- HTML 문서의 전체 구성은 html, head, body tag로 구성
- tag는 시작 tag와 종료 tag로 쌍을 이루거나 시작 tag만 존재하는 tag도 있다.
- 시작 tag와 종료 tag는 ‘/’로 구분하여 중첩되지 않도록 한다.
- 각각의 tag는 속성과 속성의 값이 존재한다.

![Untitled 4](https://user-images.githubusercontent.com/102662024/224723831-595bbd42-ae36-4b37-98be-911dfe0ae252.png)

- HTML tag에는 어느 tag에나 넣어서 사용할 수 있는 글로벌 속성(global attribute)이 있다.

![Untitled 5](https://user-images.githubusercontent.com/102662024/224723840-2e7e4941-dec8-47ff-9baa-a773a22473cf.png)

## 주석

```html
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8" />
    <title>주석</title>
  </head>
  <body>
    <h1>주석예제입니다.</h1>
    이부분은 보이죠 ^^~
    <!-- 
		주석은 내용에 포함되지 않습니다.
    -->
  </body>
</html>
```

## <html>

- HTML 문서 전체를 정의
- head와 body로 구성

## <head>

- 브라우저에게 HTML 문서의 머리 부분임을 인식
- <title>, <meta>, <style>, <script>, <lint> tag를 포함 가능
- <title> tag는 문서의 제목을 의미, 브라우저의 제목 표시줄에 tag 내용이 나타남
- <title> tag 이외에 다른 tag로 표현한 정보는 화면에 출력 X

### 메타 데이터(meta)

- 문서의 작성자, 날짜, 키워드 등 브라우저의 본문에 나타나지 않는 일반 정보를 나타냄
- name과 content 속성을 이용하여 다양한 정보를 나타냄
- http-equiv 속성을 이용하여 문서 이동 및 새로고침이 가능
- charset 속성을 이용하여 문서의 인코딩 정보를 결정

```html
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8" />
		<!-- 인코딩 정보를 설정 -->
    <title>브라우저 제목 표시줄에 나타남.</title>
    <meta name="author" content="troment" />
		<!-- 
		name 속성: description(문서의 요약), keyword(검색어 입력, 콤마로 분리), author(제작자) 등
		-->
    <meta name="description" content="간단한 설명." />
    <meta name="keyword" content="html5, web" />

		<meta http-equiv="refresh" content="30" />
		<!-- http-equiv 속성: refresh(문서를 자동으로 업데이트), content-type(인코딩 설정) 등. -->
    <script type="text/javascript"></script>
    <style type="text/css"></style>
    <link rel="stylesheet" href="test.css" />
  </head>
  <body></body>
</html>
```

## <body>

- 웹 브라우저에 보여질 문서의 내용을 작성
- <head> tag 다음에 위치하고 <head> 내부에 위치하는 tag과 <html>을 제외한 모든 tag
- id 속성을 이용해서 문서 내에서 tag를 유일하게 식별 가능(id 속성은 중복 X)
- class 속성을 이용하여 여러 tag에 공통적인 특성(CSS)을 부여(class속성은 중복 O)

```html
<html>
<head>
    <meta charset="UTF-8">
    <title>body tag</title>
    <style type="text/css">
        .common{
            background-color: steelblue;
            color: white;
        }
        #private{
            background-color: magenta;
        }
    </style>
</head>
<body>
    <h3>문단의 제목이 들어간다</h3>
    <span class="common">공통부분 1(같은 속성)</span>
    <span class="common">공통부분 2(같은 속성)</span>
    <span class="common">공통부분 3(같은 속성)</span>
    <span class="common">공통부분 4(같은 속성)</span>
    <p id="private">독립속성(중복 X) </p>
</body>
</html>
```

### heading

- 문단의 제목을 지정할 때 사용. <h1>부터 <h6>까지 구분. 숫자가 커질수록 글자는 작아짐
- <section> tag를 이용하면 같은 tag를 서로 다르게 표현
- 문서 구조를 <section> tag를 이용하여 구분하면 각 문단의 제목을 하나의 tag로 작성 가능

```html
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
</head>
<body>
    <h1>문단 제목 h1</h1>
    <h2>문단 제목 h1</h2>
    <h3>문단 제목 h1</h3>
    <h4>문단 제목 h1</h4>
    <h5>문단 제목 h1</h5>
    <h6>문단 제목 h1</h6>
    <section>
        <h1>섹션 문단 제목 1번째</h1>
        <section>
            <h1>섹션 문단 제목 1-1번째</h1>
            <section>
                <h1>섹션 문단 제목 1-2번째</h1>
            </section>
        </section>
        <h1>섹션 문단 제목 2번째</h1>
    </section>
</body>
</html>
```

## 특수문자

![Untitled 6](https://user-images.githubusercontent.com/102662024/224723842-ea79b63e-7aee-41b2-bf63-4c09ee6d53c1.png)
