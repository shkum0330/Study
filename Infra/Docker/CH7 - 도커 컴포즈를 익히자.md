# CH7 - 도커 컴포즈를 익히자

# 1. 도커 컴포즈란?

- 명령어를 입력하는데 익숙해져도 여러 개의 컨테이너로 구성된 시스템을 실행하기는 조금 귀찮게 느껴진다. 인자나 옵션도 많을뿐더러 볼륨이나 네트워크까지 설정해야 하기 때문이다.
    - 종료 및 삭제도 마찬가지다.
- 시스템 구축과 관련된 명령어를 하나의 텍스트 파일(정의 파일)에 기재해 명령어 한 번에 시스템 전체를 실행하고 종료와 폐기까지 한 번에 하도록 도와주는 도구가 바로 **도커 컴포즈**다.

### 도커 컴포즈의 구조

- 도커 컴포즈는 시스템 구축에 필요한 설정을 YAML 포맷으로 기재한 저의 파일을 이용해 전체 시스템을 일괄 실행(run) 또는 일괄 종료 및 삭제(down)할 수 있는 도구다.
- 정의 파일에는 컨테이너나 볼륨을 어떠한 설정으로 만들지에 대한 항목이 기재되어 있다.
    - 도커 명령어와 비슷하지만 도커 명령어는 아니다.
- **up 커맨드**는 정의 파일에 기재된 내용대로 이미지를 내려받고 컨테이너를 생성 및 실행한다.
    - 네트워크나 볼륨에 대한 정의도 기재할 수 있어서 주변 환경을 한꺼번에 생성할 수 있다.
- **down 커맨드**는 컨테이너와 네크워크를 정지 및 삭제한다.
    - 볼륨과 이미지는 삭제하지 않는다.
    - 삭제 없이 종료만 하고 싶다면 **stop 커맨드**를 사용한다.

### 도커 컴포즈와 Dockerfile 스크립트의 차이점

- **도커 컴포즈:** **컨테이너와 주변 환경**을 생성한다. 네트워크와 볼륨까지 함게 만들 수 있다.
- **Dockerfile 스크립트:** **이미지**를 만들기 위한 것으로 네트워크나 볼륨은 만들 수 없다.

# 2. 도커 컴포즈의 설치와 사용법

## 도커 컴포즈 설치

- 윈도우나 macOS에서는 도커 데스크톱과 함께 도커 컴포즈가 설치된다.
- 리눅스에서는 도커 컴포즈와 파이썬3 및 필요 도구를 설치해야 한다.
    - 도커 컴포즈는 파이썬으로 작성된 프로그램이기 때문에 파이썬 런타임이 필요하다.

```bash
sudo apt insatll -y python3 python3-php
sudo pip3 install docker-compose
docker-compose -version // 설치가 잘 되었는지 확인
```

## 도커 컴포즈의 사용법

- 호스트 컴퓨터에 폴더를 만들고 정의 파일(YAML 파일)을 배치한다.
    - 정의 파일은 한 폴더에 하나만 있을 수 있다. 그래서 여러 개의 정의 파일을 사용하려면 그 개수만큼 폴더를 만들어야 한다.
    - 컨테이너 생성에 필요한 이미지 파일이나 HTML 파일 역시 컴포즈가 사용할 폴더에 함게 둔다.
- 정의 파일의 이름은 미리 정해진 docker-compose.yml이라는 이름을 사용해야 한다.
- 파일은 호스트 컴퓨터에 배치되지만 명령어는 똑같이 도커 엔진에 전달되며, 만들어진 컨테이너도 동일하게 도커 엔진 위에서 동작한다.
- 도커 컴포즈에서 컨테이너가 모인 것을 **서비스**라고 부른다.

# 3. 도커 컴포즈 파일을 작성하는 법

## 도커 컴포즈 정의 파일의 내용 살펴보기

- 아파치 컨테이너의 컴포즈 파일

```yaml
version: "3"

services:
	apa000ex2:
		image: httpd
		ports:
			- 8080:80
		restart: always
```

- 워드프레스 컨테이너의 컴포즈 파일

```yaml
version: "3"

services:
	wordpress000ex12:
		depends_on:
			-mysql000ex11
		image: wordpress
		networks:
			- wordpress000net1
		ports:
			- 8085:80
		restart: always
		environment:
			WORDPRESS_DB_HOST=mysql000ex11
			WORDPRESS_DB_NAME=wordpress000db
			WORDPRESS_DB_USER=username
			WORDPRESS_DB_PASSWORD=1234
```

## 컴포즈 파일을 작성하는 방법

- 맨 앞에 컴포즈 버전을 적고, 그 뒤로 services와 networks, volumes를 차례로 기재한다.
    - **service:** 컨테이너에 대한 내용이다. 도커 컴포즈와 쿠버네티스에서는 컨테이너의 집합체를 service라고 부른다.

```yaml
version: "3"

services:
	컨테이너_이름1:
		image: 이미지_이름
		networks: 
			- 네트워크_이름
		ports:
			- 포트_설정
	컨테이너_이름2:
networks:
	네트워크_이름1:
volumes:
	볼륨_이름1:
	볼륨_이름2:

```

### 컴포즈 파일 작성 요령 정리

- 첫 줄에 도커 컴포즈 버전을 기재
- 주 항목 services, networks, volumes 아래에 설정 내용을 기재
- 항목 간의 상하관계는 공백을 사용한 들여쓰기로 나타낸다.
- 들여쓰기는 같은 수의 배수만큼의 공백을 사용한다.
- 이름은 주 항목 아래에 들여쓰기한 다음 기재한다.
- 컨테이너 설정 내용은 이름 아래에 들여쓰기한 다음 기재한다.
- 여러 항목을 기재하려면 줄 앞에 ‘-’를 붙인다.
- 이름 뒤에는 콜론(:)을 붙인다.
- 콜론 뒤에는 반드시 공백이 와야 한다.
- # 뒤의 내용은 주석으로 간주된다.
- 문자열은 작은따옴표 또는 큰따옴표로 감싸 작성한다.
- 

### 컴포즈 파일의 항목

- 주 항목

| 항목 | 내용 |
| --- | --- |
| services | 컨테이너를 정의한다. |
| networks | 네트워크를 정의한다. |
| volumes | 볼륨을 정의한다. |
- 자주 나오는 정의 내용

| 항목 | docker run 커맨드의 해당 옵션 또는 인자 | 내용 |
| --- | --- | --- |
| image | 이미지 인자 | 사용할 이미지를 지정 |
| networks | —net | 접속할 네트워크를 지정 |
| volumes | -v,  --mount | 스토리지 마운트를 설정 |
| ports | -p | 포트 설정 |
| environment | -e | 환경변수 설정 |
| depends_on | 없음 | 다른 서비스에 대한 의존관계를 정의 |
| restart | 없음 | 컨테이너 종료 시 재시작 여부를 설정 |
- **depends_on:** 컨테이너를 생성하는 순서나 연동 여부를 정의(여기 정의된 컨테이너를 먼저 생성)
- restart의 설정값

| 항목 | 내용 |
| --- | --- |
| no | 재시작하지 않는다. |
| always | 항상 재시작한다. |
| on-failure | 프로세스가 0 외의 상태로 종료됐다면 재시작한다. |
| unless-stopped | 종료 시 재시작하지 않음. 그 외에는 재시작한다. |
- 이외에도 command, container_name, env_file, entrypoint 등의 정의 항목이 있다.
- 도커 컴포즈로 만든 컨테이너라도 도커 엔진을 통해 명령을 내릴 수 있지만, 컴포즈 파일까지 반영되지는 않는다.
    - 도커 엔진을 통래 컨테이너의 이름을 바꿔도 컴포즈 파일에서는 이를 알 수가 없기 때문에, 컴포즈 파일로 명령을 내릴 수 없다.
    - 그래서 컨테이너 이름은 섣불리 손대지 않는 것이 좋다.

## [실습] 컴포즈 파일 작성

```yaml
version: "3"
services:
  mysql000ex11:
    image: mysql:8
    networks:
      - wordpress000net1
    volumes:
      - mysql000vol11:/var/lib/mysql
    restart: always
    command: --character-set-server=utf8mb4 --collation-server=utf8mb4_unicode_ci --default-authentication-plugin=mysql_native_password
    environment:
      MYSQL_ROOT_PASSWORD: myrootpass
      MYSQL_DATABASE: wordpress000db
      MYSQL_USER: wordpress000kun
      MYSQL_PASSWORD: wkunpass

  wordpress000ex12:
    depends_on:
      - mysql000ex11
    image: wordpress
    networks:
      - wordpress000net1
    volumes:
      - wordpress000vol12:/var/www/html
    ports:
      - 8085:80
    restart: always
    environment:
      WORDPRESS_DB_HOST: mysql000ex11
      WORDPRESS_DB_NAME: wordpress000db
      WORDPRESS_DB_USER: wordpress000kun
      WORDPRESS_DB_PASSWORD: wkunpass
networks:
  wordpress000net1:
volumes:
  mysql000vol11:
  wordpress000vol12:

```

# 4. 도커 컴포즈 실행

## 도커 컴포즈 커맨드

- 도커 컴포즈는 **docker-compose 명령**을 사용한다.
- 자주 사용하는 커맨드는 up, down이고 stop도 가끔 사용한다.

### 컨테이너와 주변 환경을 생성하는 docker-compose up 커맨드

- 컴포즈 파일의 내용을 따라 컨테이너와 볼륨, 네트워크를 생성하고 실행한다.
- 컴포즈 파일의 경로는 -f 옵션을 사용해 지정한다.

```bash
docker-compose -f 정의_파일_경로 up 옵션
```

- 옵션 항목

| 옵션 | 내용 |
| --- | --- |
| -d | 백그라운드로 실행 |
| --no-color | 화면 출력 내용을 흑백으로 함 |
| --no-deps | 링크된 서비스를 실행하지 않음 |
| --force-recreate | 설정 또는 이미지가 변경되지 않더라도 컨테이너를 재생성 |
| --no-create | 컨테이너가 이미 존재할 경우 다시 생성하지 않음 |
| --no-build | 이미지가 없어도 이미지를 빌드하지 않음 |
| --build | 컨테이너를 실행하기 전에 이미지를 빌드 |
| --abort-on-container-exit | 컨테이너가 하나라도 종료되면 모든 컨테이너를 종료 |
| -t, --timeout | 컨테이너를 종료할 때의 타임아웃 설정. 기본은 10초. |
| --remove-orphans | 컴포즈 파일에 정의되지 않은 서비스의 컨테이너를 삭제 |
| --scale | 컨테이너의 수를 변경 |

### 컨테이너와 네트워크를 삭제하는 docker-compose up 커맨드

```bash
docker-compose -f 컴포즈_파일_경로 down 옵션
```

- 옵션 항목

| 옵션 | 내용 |
| --- | --- |
| --rmi 종류 | 삭제 시에 이미지도 삭제. 종류를 all로 하면 사용했던 모든 이미지를 삭제하고, loacl로 지정하면 커스텀 태그가 없는 이미지만 삭제 |
| -v, --volumes | volumes 항목에 기재된 볼륨을 삭제한다. 단, external로 지정된 볼륨은 삭제되지 않는다. |
| --remove-orphans | 컴포즈 파일에 정의되지 않은 서비스의 컨테이너도 삭제한다. |

### 컨테이너를 종료하는 docker-compose stop 커맨드

```bash
docker-compose -f 컴포즈_파일_경로 stop 옵션
```

### 그 밖의 주요 커맨드

- ps, config, port, logs, start, kill, exec, run…

## [실습] 도커 컴포즈 실행

### 컴포즈 파일의 내용 실행

```bash
docker-compose -f /Users/sehyun/com_folder/docker-compose.yml up -d
```

- [localhost:8085](http://localhost:8085)에 접근해 워드프레스 초기 화면이 나타나는지 확인한다.

### 컨테이너와 네트워크를 종료 및 삭제

```bash
docker-compose -f /Users/sehyun/com_folder/docker-compose.yml down
```

- down 커맨드를 사용해도 이미지와 볼륨은 삭제되지 않기 때문에 직접 삭제해야 한다.