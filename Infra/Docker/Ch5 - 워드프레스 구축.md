# Ch5 - 워드프레스 구축

# 1. 워드프레스 구축

## 워드프레스 사이트 구성 및 구축

- **워드프레스**는 웹 사이트를 만들기 위한 소프트웨어로, 서버에 설치해 사용한다.
- 워드프레스 공식 이미지는 워드프레스 프로그램 본체와 Apache, PHP 런타임을 함께 포함하고 있어서 매우 편리하다. 이 컨테이너와 MySQL 컨테이너가 있으면 워드프레스를 사용할 수 있다.
    - 데이터베이스 서버를 도커 외부에 두는 방식도 가능하다.

## 도커 네트워크 생성/삭제

- 워드프레스는 간단히 말해 블로그 생성 도구와 같은 것으로, 웹 사이트 작성자가 작성한 내용을 DB에 저장하고, 웹 사이트 열람자의 요청에 따라 웹 페이지를 보여준다.
    - 즉 프로그램이 MySQL에 저장된 데이터를 읽고 쓸 수 있어야 하기 때문에 두 컨테이너가 연결되어 있어야 한다.
- 그저 컨테이너를 2개 만들기만 해서는 두 컨테이너가 연결되지 않으므로 가상 네트워크를 만들고 이 네트워크에 2개의 컨테이너를 소속시켜 연결한다.
- 이 가상 네트워크를 만드는 커맨드가 `docker network create`다. 커맨드 뒤로 네트워크 이름을 기재하면 된다.
- 네트워크를 삭제할 때는 `docker network rm`, 네트워크 목록을 출력할 때는 `docker network ls` 커맨드를 사용한다.

### 그 외 네트워크 관련 커맨드

| 커맨드 | 내용 | 생략 가능 여부 | 주요 옵션 |
| --- | --- | --- | --- |
| connect | 네트워크에 컨테이너를 새로이 접속 | X | 거의 사용하지 않음 |
| disconnect | 네트워크에 컨테이너의 접속을 끊음 | X | 거의 사용하지 않음 |
| inspect | 네트워크의 상세 정보를 확인 | X | 거의 사용하지 않음 |
| prune | 현재 아무 컨테이너도 접속하지 않은 네트워크를 모두 삭제 | X | 거의 사용하지 않음 |

## MySQL 컨테이너 실행 시에 필요한 옵션과 인자

```bash
docker run --name mysql000ex11 -dit --net=wordpress000net1 -e MYSQL_ROOT_PASSWORD=myrootpass
-e MYSQL_DATABASE=wordpress000db -e MYSQL_USER=wordpress000kum -e MYSQL_PASSWORD=wkumpass
mysql --character-set-server=utf8mb4 --collation-server=utf8mb4_unicode_ci
--default-authentication-plugin=mysql_native_password
```

### 사용된 옵션

- —net은 컨테이너를 연결할 도커 네트워크, 나머지는 모두 -e 옵션으로 **환경변수**를 설정하기 위해 사용한다.
- **환경변수**란 운영체제에서 다양한 설정값을 저장하는 장소를 가리킨다.
- 패스워드는 `루트 패스워드`와 `사용자 패스워드` 두 가지를 설정한다.
    - **루트**는 모든 권한을 가진 사용자로, 이 권한이 없으면 할 수 없는 작업이 있지만 매번 루트 사용자로 접속할 경우 보안 측면에서 문제가 생기기 때문에 제한된 권한을 가진 일반 사용자로 전환하는 것이 보통이다.
    - 또한 실제 패스워드를 사용할 때는 무작위 문자열을 사용해야 한다.

### 사용된 인자

| 항목 | 인자 | 값 | 의미 |
| --- | --- | --- | --- |
| 문자 인코딩 | --character-set-server= | utf8mb4 | 문자 인코딩으로 UTF8을 사용 |
| 정렬 순서 | --collation-server= | utf8mb4_unicode_ci | 정렬 순서로 UTF8을 따름 |
| 인증 방식 | --default-authentication-plugin= | mysql_native_password | 인증 방식을 예전 방식(native)으로 변경 |
- 세 번째에 나온 인증 방식은 MySQL이 5.7에서 8.0으로 넘어오면서 외부 소프트웨어가 MySQL에 접속하는 인증 방식을 바꾸었기(SHA2로 바뀌었다.) 때문이다.
    - 이 새로운 인증 방식은 아직 지원하지 않는 소프트웨어가 많다. 다시 말해 워드프레스는 MySQL 8.0에 접속할 수 없다.

## 워드프레스 컨테이너 실행 시 필요한 옵션과 인자

- 워드프레스 컨테이너도 옵션이 10가지 사용된다.
    - 대부분은 DB 접속과 관련된 정보다.
    - 인자는 없다.

```bash
docker run --name wordpress000ex12 -dit --net=wordpress000net1 -p 8085:80 
-e WORDPRESS_DB_HOST=mysql000ex11 -e WORDPRESS_DB_NAME=wordpress000db 
-e WORDPRESS_DB_USER=wordpress000kum -e WORDPRESS_DB_PASSWORD=wkumpass wordpress
```

- 설정한 정보는 모두 MySQL 컨테이너의 설정값과 동일한 값이어야 한다. 값이 다르면 연동이 잘 되지 않으며 워드프레스도 사용할 수 없다.

# 2. 워드프레스 및 MySQL 컨테이너 생성과 연동

## 실습 내용

- 워드프레스 컨테이너와 MySQL 컨테이너를 생성하고 실행
- MySQL 컨테이너를 먼저 생성하고 네트워크를 생성해야 한다.

### network create

```bash
docker network create wordpress000net1
```

### MySQL 컨테이너 생성 및 실행

- [https://www.notion.so/Ch5-7a7e0b6124a84d2da92554fb914fb4aa?pvs=4#bd955ac2a9164cfb87b76b7c297bad4c](https://www.notion.so/Ch5-7a7e0b6124a84d2da92554fb914fb4aa?pvs=21)

### 워드프레스 컨테이너 생성 및 실행

- [https://www.notion.so/Ch5-7a7e0b6124a84d2da92554fb914fb4aa?pvs=4#4edd5331061f462c8ec8f61c5b3e3888](https://www.notion.so/Ch5-7a7e0b6124a84d2da92554fb914fb4aa?pvs=21)

### 뒷정리

- 컨테이너 종료 → 컨테이너 삭제 → 이미지 삭제 → 네트워크 삭제

# 3. 명령어를 직접 작성하자

## 소프트웨어와 데이터베이스의 관계

- Apache, PHP, MySQL에 리눅스를 합친 조합을 **LAMP 스택**이라고 부른다.
- 소프트웨어가 발전하면서 Apache가 nginx로 바뀌기도 하고, MySQL이 MariaDB나 PostgreSQL로 바뀐 조합도 나타났지만 `리눅스+웹 서버+프로그래밍 언어 런타임+데이터베이스`의 조합임은 변함이 없다.
- 컨테이너도 워드프레스와 마찬가지로 `프로그램 본체+프로그램 런타임+웹 서버` 컨테이너와 `데이터베이스` 컨테이너로 구성해 운영하는 사례를 흔히 볼 수

## run 커맨드를 직접 작성하는 방법

- 명령어는 하이픈이 한 개이거나 두 개인것도 있고, 등호 기호가 들어가는 옵션도 있고 아닌 옵션도 있다.
- 이들 기호를 사용하는 데 특별한 법칙은 없다. [공식문서](https://docs.docker.com/reference/)를 확인하자.

# 4. 레드마인 및 MariaDB 컨테이너를 대상으로 연습하자

## 레드마인 및 MySQL 컨테이너 생성

- 레드마인은 티켓(누구에게 어떤 업무를 맡길지를 나타내는 todo)을 관리하는 소프트웨어이다.
- 레드마인 역시 워드프레스와 포트 번호 정도만 다르고 거의 같은 구성을 띤다.
- 다만 **옵션의 이름은 다르다**는 점에 주의해야 한다.

### 네트워크 생성

```bash
docker network create redmine000net2
```

### MySQL 컨테이너 생성 및 실행

```bash
docker run --name mysql000ex13 -dit --net=redmine000net2 -e MYSQL_ROOT_PASSWORD=myrootpass 
-e MYSQL_DATABASE=redmine000db -e MYSQL_USER=redmine000kum -e MYSQL_PASSWORD=rkumpass mysql 
--character-set-server=utf8mb4 --collation-server=utf8mb4_unicode_ci
--default-authentication-plugin=mysql_native_password
```

### 레드마인 컨테이너 생성 및 실행

```bash
docker run -dit --name redmine000ex14 --network redmine000net2 -p 8086:3000
-e REDMINE_DB_MYSQL=mysql000ex13 -e REDMINE_DB_DATABASE=redmine000db 
-e REDMINE_DB_USERNAME=redmine000kum -e REDMINE_DB_PASSWORD=rkumpass redmine
```

## 레드마인 및 MariaDB 컨테이너 만들기

- 이번엔 데이터베이스를 MariaDB로 바꿔보자.
- MariaDB 컨테이너는 MYSQL_ROOT_PASSWORD, MYSQL_DATABASE와 같이 옵션 이름에 MYSQL이 들어간다.

### 네트워크 생성

```bash
docker network create redmine000net3
```

### MariaDB 컨테이너 생성 및 실행

```bash
docker run --name mariadb000ex15 -dit --net=redmine000net3 
-e MYSQL_ROOT_PASSWORD=mariarootpass -e MYSQL_DATABASE=redmine000db 
-e MYSQL_USER=redmine000kum -e MYSQL_PASSWORD=rkumpass mariadb 
--character-set-server=utf8mb4 --collation-server=utf8mb4_unicode_ci --default-authentication-plugin=mysql_native_password
```

### 레드마인 컨테이너 생성 및 실행

```bash
docker run -dit --name redmine000ex16 --network redmine000net3 -p 8087:3000
-e REDMINE_DB_MYSQL=mariadb000ex15 -e REDMINE_DB_DATABASE=redmine000db 
-e REDMINE_DB_USERNAME=redmine000kum -e REDMINE_DB_PASSWORD=rkumpass redmine
```