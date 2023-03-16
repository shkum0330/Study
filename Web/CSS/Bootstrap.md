# Bootstrap

- 빠르고 쉬운 웹 개발을 위해 구조를 미리 만들어준 무료 프론트엔드 프레임워크
- typography, forms, buttons, tables, modal, image 및 기타 여러 가지를 위한 HTML 및 CSS 기반 디자인 템플릿(CSS style과 layout 정렬을 class만으로 제어)과 선택적 자바스크립트 플러그인이 포함되어 있다.
- 반응형 디자인을 쉽게 만들 수 있는 기능을 제공한다

## 장점

- 사용하기 쉬움: HTML과 CSS에 대한 기본적인 지식만 있으면 누구나 부트스트랩을 사용할 수 있다.
- 반응형 기능: 부트스트랩의 반응형 CSS는 휴대폰, 태블릿 및 데스크톱에 맞게 조정된다.
- 모바일 우선 접근 방식
- 브라우저 호환성

### 예시

- CDN 설정에 따른 화면 변화
- 기본 Tag들의 여백이나 굵기도 설정되어 있음

```html
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <title>Bootstrap demo</title>
    <link
      href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css"
      rel="stylesheet"
      integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65"
      crossorigin="anonymous"
    />
  </head>
  <body>
    <h1>Hello, world!</h1>
    <script
      src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"
      integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4"
      crossorigin="anonymous"
    ></script>
  </body>
</html>
```

## Breakpoint

- 특정 viewport 또는 device 크기에서 layout을 조정할 수 있는 시기를 제어하는 데 사용
- 반응형 빌드를 위한 6개의 breakpoint를 제공

![Untitled](https://user-images.githubusercontent.com/102662024/225658238-954a80d2-bfef-493a-be02-6d7624576ffd.png)

## Container

- 주어진 장치 또는 viewport 내에서 컨텐츠를 포함, 패딩 및 정렬하는 기본 블록
- .container 클래스는 반응형 고정 너비 컨테이너를 제공
- .container-fluid 클래스는 viewport 전체 너비에 걸쳐 있는 전체 너비 컨테이너를 제공

[https://getbootstrap.com/docs/5.2/layout/containers/](https://getbootstrap.com/docs/5.2/layout/containers/)

## Grid System

- 부트스트랩의 그리드 시스템은 flexbox로 구축되어 페이지에 최대 12개의 열을 허용한다.
- 12개 열을 모두 개별적으로 사용하지 않으려면 열을 함께 그룹화하여 더 넓은 열을 만들 수 있다.

![Untitled 1](https://user-images.githubusercontent.com/102662024/225658186-425f1b7f-ce90-4489-9926-f33886b96041.png)

## Grid Class

- 클래스를 결합하여 보다 동적이고 유연한 레이아웃을 만들 수 있다.

![Untitled 2](https://user-images.githubusercontent.com/102662024/225658208-795f8f09-2960-4481-827c-7cb527ff4d09.png)

- 너비설정

```html
<h1 class="mt-3">동일 너비 열</h1>
    <div class="row">
      <div class="col bg-primary">컬럼 1</div>
      <div class="col bg-info">컬럼 2</div>
    </div>
    <div class="row">
      <div class="col bg-primary">컬럼 1</div>
      <div class="col bg-danger">컬럼 2</div>
      <div class="col bg-info">컬럼 3</div>
    </div>
    <h1 class="mt-3">하나의 열에 너비 설정</h1>
    <div class="row">
      <div class="col bg-primary">컬럼 1</div>
      <div class="col-6 bg-danger">컬럼 2</div>
      <div class="col bg-info">컬럼 3</div>
    </div>
    <h1 class="mt-3">컨텐츠에 따른 가변 너비 설정</h1>
    <div class="row">
      <div class="col bg-primary">컬럼 1</div>
      <div class="col-md-auto bg-danger">컨텐츠의 길이!!!!!!!</div>
      <div class="col col-lg-2 bg-info">컬럼 3</div>
    </div>
```

## Background Color

- bg-colorclassname

```html
<div class="container text-center">
  <h1>background color</h1>
  <div class="p-3 mb-2 bg-primary text-white">.bg-primary</div>
  <div class="p-3 mb-2 bg-secondary text-white">.bg-secondary</div>
  <div class="p-3 mb-2 bg-success text-white">.bg-success</div>
  <div class="p-3 mb-2 bg-danger text-white">.bg-danger</div>
  <div class="p-3 mb-2 bg-warning text-dark">.bg-warning</div>
  <div class="p-3 mb-2 bg-info text-dark">.bg-info</div>
  <div class="p-3 mb-2 bg-light text-dark">.bg-light</div>
  <div class="p-3 mb-2 bg-dark text-white">.bg-dark</div>
  <div class="p-3 mb-2 bg-body text-dark">.bg-body</div>
  <div class="p-3 mb-2 bg-white text-dark">.bg-white</div>
  <div class="p-3 mb-2 bg-transparent text-dark">.bg-transparent</div>
</div>
```

## Text color

- text-colorclassname

## Link color

- link-colorclassname

## Border

- border, border-{align}, border-{color}

## Spacing

- {property}-{sides}-{size}

![Untitled 3](https://user-images.githubusercontent.com/102662024/225658232-74071a10-7744-4ac2-83ec-602e47163ff6.png)

```html
<div class="container">
  <h1>spacing</h1>
  <div class="ps-5 mt-4 bg-primary text-white">ps-5 mt-4</div>
  <div class="pt-3 mb-5 bg-secondary text-white">pt-3 mb-5</div>
  <div class="pb-3 ms-2 bg-success text-white">pb-3 ms-2</div>
  <div class="p-3 mt-2 bg-danger text-white">p-3 mt-2</div>
  <div class="p-5 m-3 bg-warning text-dark">p-5 m-3g</div>
</div>
```
