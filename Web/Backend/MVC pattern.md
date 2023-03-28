# MVC pattern

## Web Application Architecture

- JSP를 이용하여 구성할 수 있는 Web Application Architecture는 크게 model1과 model2로 나뉜다.
- JSP가 client의 요청에 대한 logic 처리와 response page(view)에 대한 처리를 모두 하느냐, 아니면 response page(view)에 대한 처리만 하는지가 가장 큰 차이점이다,
- Model2 구조는 MVC 패턴을 웹 개발에 도입한 구조를 말한다.

![Untitled](https://user-images.githubusercontent.com/102662024/228277058-db770eed-6a21-4e8c-8627-8aceec11d90a.png)

## Model1 구조

- view와 logic을 JSP 페이지 하나에서 처리하는 구조를 말한다.
- 클라이언트로부터 요청이 들어오게 되면 JSP 페이지는 java beans(dao 등)나 별도의 service class를 이용하여 작업을 처리, 결과를 출력한다.
- 간단한 page를 구성하기 위해 과거에 가장 많이 사용되었던 architecture

![Untitled 1](https://user-images.githubusercontent.com/102662024/228277016-1ea6763a-147d-461a-add2-796cc8cec2bc.png)

### 장점

- 구조가 단순하며 직관적이기 때문에 배우기 쉽다.
- 개발 시간이 비교적 짧기 떄문에 개발 비용이 감소

### 단점

- 출력을 위한 view(html) 코드와 로직 처리를 위한 java 코드가 섞여 있기 때문에 JSP 코드 자체가 복잡해진다
- JSP 코드에 Backend(Developer)와 Frontend(Designer)가 혼재되기 때문에 분업이 힘들어진다.
- 프로젝트의 규모가 커지게 되면 코드가 복잡해지므로 유지보수 하기가 어려워진다.
- 확장성(신기술 도입, framework 등)이 나쁘다.

## Model2 구조(MVC pattern)

- 모든 처리를 JSP 페이지에서 하는 것이 아니라, 클라이언트 요청에 대한 처리는 서블릿이, 로직 처리는 자바 클래스(Service, Dao, …)가, 클라이언트에게 출력하는 response page를 JSP가 담당한다.

![Untitled 2](https://user-images.githubusercontent.com/102662024/228277040-4538d21d-6d78-4c7f-81f6-6479c01de47c.png)

![Untitled 3](https://user-images.githubusercontent.com/102662024/228277049-527ffe05-5fa0-4556-af6f-46b51c073645.png)

### 장점

- 출력을 위한 view 코드와 로직 처리를 위한 java 코드가 분리되었기 때문에 JSP는 Model1에 비해 코드가 복잡하지 않다.
- 화면단과 로직단이 분리되었기 때문에 분업이 용이하다.
- 기능에 따라 코드가 분리되었기 때문에 유지보수가 쉬워졌다.
- 확장성이 뛰어나다.

### 단점

- 구조가 복잡하여 초기 진입이 어렵다.
- 개발 시간의 증가로 개발 비용 증가
