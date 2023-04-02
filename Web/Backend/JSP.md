# JSP

- **JSP(Java Server Pages)**는 HTML 내에 자바 코드를  삽입하여 웹 서버에서 동적으로 웹 페이지를 생성하여 웹 브라우저에 돌려주는 언어이다.
- Java EE 스펙 중 일부로 웹 어플리케이션 서버에서 동작한다.
- JSP는 실행 시 자바 서블릿으로 변환된 후 실행되어서 서블릿과 유사하지만, 서블릿과는 달리 HTML 표준에 따라 작성되므로 웹 디자인하기 편리하다. 이와 비슷한 구조로 PHP, ASP, [ASP.NET](http://ASP.NET) 등이 있다.
- 아파치 스트럿츠나 자카르타 프로젝트의 JSTL 등의 JSP 태그 라이브러리를 사용하는 경우에는 자바 코딩없이 태그만으로 간략히 기술이 가능하므로 생산성을 높일 수 있다.

## JSP Process

![Untitled](https://user-images.githubusercontent.com/102662024/229360082-f589a2ea-1f60-446b-ab9b-4e0b376fa3b8.png)

## JSP 스크립팅 요소(Scripting Element)

### 1. 선언(Declaration)

멤버 변수 선언이나 메소드를 선언하는 영역

- `<%! 멤버변수와 method 작성; %>`

### 2. 스크립트릿(Scriptlet)

Client 요청 시 매번 호출하는 영역으로, Servlet으로 변환 시 service() method에 해당되는 영역. request, response에 관련된 코드 구현

- `<% java code; %>`

### 3. 표현식(Expression)

데이터를 브라우저에 출력할 때 사용

- `<%= 문자열 %>`
- 주의) 문자열 뒤 세미콜론 작성X
- `<%= 문자열 %>` == `<% out.print(문자열); %>`
- 변수의 값을 표시하는 것은 가능하지만 선언하는 것은 불가능하다. setXXX() 실행 불가

### 4. 주석(Comment)

코드에서 부가 설명을 작성

- `<%— 주석 할 code —%>`
- HTML 주석을 하게되면 자바 코드가 실행된다.

## JSP 지시자(Directive)

- JSP 페이지를 어떻게 처리할 것인지 설정하는 태그. JSP 페이지가 서블릿 프로그램에서 서블릿 클래스로 변환할 때 JSP 페이지와 관련된 정보를 JSP 컨테이너(톰캣)에 지시하는 메시지
1. **page Directive**
    - 컨테이너에게 현재 JSP 페이지를 어떻게 처리할 것인가에 대한 정보를 제공한다.
    - `<%@ page attr1=”val1” attr2=”val2” … %>`
    
    | 속성 | 기본값 | 설명 |
    | --- | --- | --- |
    | language | java | 스크립트에서 사용할 언어 지정 |
    | info |  | 현재 JSP 페이지에 대한 설명 |
    | contentType | text/html;charset=IOS-8859-1 | 브라우저로 내보내는 내용의 MIME 형식 및 문자 집합 지정 |
    | pageEncoding | ISO-8859-1 | 현재 JSP 페이지 문자집합 지정 |
    | import |  | 현재 JSP 페이지에서 사용할 Java 패키지나 클래스를 지정 |
    | session | true | 세션의 사용 유무 설정 |
    | errorPage |  | 에러가 발생할 때 대신 처리될 JSP 페이지 지정 |
    | isErrorPage | false | 현재 JSP 페이지가 에러 핸들링하는 페이지인지 지정하는 요소 |
    | buffer | 8KB | 버퍼의 크기 |
    | autoflush | true | 버퍼의 내용을 자동으로 브라우저로 보낼 지에 대한 설정 |
    | isThreadsafe | true | 현재 JSP 페이지가 멀티쓰레드로 동작해도 안전한지 여부를 설정하는 것으로 false인 경우 JSP 페이지는 싱글쓰레드로 서비스된다. |
    | extends | javax.servlet.jsp.HttpJspPage | 현재 JSP 페이지를 기본적인 클래스가 아닌 다른 클래스로부터 상속하도록 변경 |
2. **include Directive**
    - 특정 jsp file을 페이지에 포함
    - 여러 jsp 페이지에서 반복적으로 사용되는 부분을 jsp file로 만든 후 반복 영역에 include시켜 반복되는 코드를 줄일 수 있다
    - `<%@ include file=”/template/header.jsp” %>`
3. **taglib Directive**
    - JSTL 또는 사용자에 의해서 커스텀 태그를 이용할 때 사용되면 JSP 페이지 내의 불필요한 자바 코드를 줄일 수 있다.
    - `<%@ taglib prefix=”c” url=”http://java.sun.com/jsp/jstl/core” %>`

# JSP 기본 객체

| 기본 객체명 | Type | 설명 |
| --- | --- | --- |
| request | javax.servlet.http.HttpServletRequest | HTML 폼 요소의 선택 값 등 사용자 입력 정보를 읽어올 때 사용 |
| response | javax.servlet.http.HttpServletResponse | 사용자 요청에 대한 응답을 처리하기 위해 사용 |
| pageContext | javax.servlet.jsp.PageContext | 각종 기본 객체를 얻거나 forward 및 include 기능을 활용할 때 사용 |
| session | javax.servlet.http.HttpSession | 클라이언트에 대한 세션 정보를 처리하기 위해 사용. page directive의 session 속성을 false로 하면 내장 객체는 생성이 안 된다. |
| application | javax.servlet.ServletContent | 웹 서버의 애플리케이션 처리와 관련된 정보를 레퍼런스하기 위해 사용 |
| out | javax.servlet.jsp.JspWriter | 사용자에게 전달하기 위한 output 스트림을 처리할 때 사용 |
| config | javax.servlet.ServletConfig | 현재 JSP에 대한 초기화 환경을 처리하기 위해 사용 |
| page | java.lang.Object | 현재 JSP 페이지에 대한 참조 변수에 해당됨 |
| exception | java.lang.Exception | 전달된 오류 정보를 담고 있는 내장 객체. 에러를 처리하는 JSP에서 isErrorPage를 true로 설정하면 사용할 수 있다. |

## JSP 기본 객체의 영역(scope)

| 기본 객체 | 설명 |
| --- | --- |
| pageContext | 하나의 JSP 페이지를 처리할 때 사용되는 영역
한 번의 클라이언트 요청에 대하여 하나의 JSP 페이지가 호출되며, 이때 단 한개의 page 객체만 대응이 된다.
페이지 영역에 저장한 값은 페이지를 벗어나면 사라진다.
커스텀 태그에서 새로운 변수를 추가할 때 사용한다. |
| request | 하나의 HTTP 요청을 처리할 때 사용되는 영역
웹 브라우저가 요청을 할 때마다 새로운 request 객체가 생성됨.
request 영역에 저장한 속성은 해당 요청에 대한 응답이 완료되면 사라진다. |
| session | 하나의 웹 브라우저와 관련된 영역
같은 브라우저 내에서 요청되는 페이지들은 같은 session들을 공유하게 됨.
로그인 정보 등을 저장한다 |
| application | 하나의 웹 애플리케이션과 관련된 영역
애플리케이션당 1개의 application 객체가 생성됨
같은 애플리케이션에서 요청되는 페이지들은 같은 application 객체를 공유함. |

### 공통 method

- servlet과 jsp 페이지 간에 특정 정보를 주고 받거나 공유하기 위한 메소드를 지원

| method | 설명 |
| --- | --- |
| void setAttribute(String name, Object value) | 문자열 name 이름으로 Object형 데이터를 저장한다. Object형이므로 어떠한 Java 객체도 저장이 가능하다. |
| Object getAttribute(String name) | 문자열 name에 해당하는 속성값이 있다면 Object 형태로 가져오고 없으면 null을 return한다.
리턴 값에 대한 적절한 형 변환이 필요하다.  |
| Enumeration getAttributeNames() | 현재 객체에 저장된 속성들의 이름들을 Enumeration 형태로 가져온다. |
| void removeAttribute(String name) | 문자열 name에 해당하는 속성을 삭제한다. |

## WEB Page 이동

|  | foward(request, response) | sendRedirect(location) |
| --- | --- | --- |
| 사용 방법 | RequestDispatcher dispatcher=request.getRequestDispatcher(path);
dispatcher.forward(request, response); | response.sendRedirect(location); |
| 이동 범위 | 동일 서버(project)내 경로 | 동일 서버 포함 타 URL 가능 |
| location bar | 기존 URL 유지(실제 이동되는 주소 확인 불가) | 이동하는 page로 변경 |
| 객체 | 기존의 request와 response가 그대로 전달 | 기존의 request와 response는 소멸되고, 새롭게 생성된다. |
| 속도 | 비교적 빠름 | forward()에 비해 느림 |
| 데이터 유지 | request의 setAttribute(name, value)를 통해 전달 | request로는 data 저장 불가능.
session이나 cookie 이용 |
