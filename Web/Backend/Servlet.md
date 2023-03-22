# Servlet

# Web Architecture

![Untitled](https://user-images.githubusercontent.com/102662024/226918064-e195572f-a262-4ed3-b189-1c44a3c7d145.png)


# Servlet

- 자바 서블릿(Java Servlet)은 자바를 사용하여 웹페이지를 동적으로 생성하는 서버측 프로그램 혹은 그 사양을 말하며, 흔히 “서블릿”이라 불린다. 자바 서블릿은 웹 서버의 성능을 향상하기 위해 사용되는 자바 클래스의 일종이다. 서블릿은 JSP와 비슷한 점이 있지만, JSP가 HTML 문서 안에 Java 코드를 포함하고 있는 반면, 서블릿은 자바 코드 안에 HTML을 포함하고 있다는 차이점이 있다.

## Servlet 동작 흐름

![Untitled 1](https://user-images.githubusercontent.com/102662024/226918042-600ccbac-0920-4f3e-934b-5aba4019504b.png)

### page 이동 방법

- url 입력
- link
- form
    - GET, POST

## Servlet API

![Untitled 2](https://user-images.githubusercontent.com/102662024/226918046-3c1ebe0e-b764-49f9-b665-c08df8d91188.png)

                                                                  사용자 정의 서블릿의 상속 구조

# Servlet Life-Cycle

- Servlet class는 java SE에서의 class와는 다르게 main method가 없다. 즉 객체의 생성부터 사용(method call)의 주체가 사용자가 아닌 Servlet Container에게 있다.
- Client가 요청(request)을 하게 되면 Servlet Container는 Servlet 객체를 생성(한 번만)하고, 초기화(한 번만)하며 요청에 대한 처리(요청시마다 반복)를 하게 된다. 또한 Servlet 객체가 필요없게 되면 제거하는 일까지 Container가 담당하게 된다.

### 주요 method

![Untitled 3](https://user-images.githubusercontent.com/102662024/226918050-a8d172c8-b896-4be1-9cf6-5debbd4218a9.png)

# Servlet Parameter 처리

### GET과 POST 방식 비교

![Untitled 4](https://user-images.githubusercontent.com/102662024/226918055-4e6ee3a8-a18c-4db6-b59f-ce319cc87dac.png)

### URL 형식

![Untitled 5](https://user-images.githubusercontent.com/102662024/226918061-d679008d-0558-44cf-872b-45fee0d8ec48.png)
