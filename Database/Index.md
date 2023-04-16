# Index

- 책의 찾아보기(index, 색인)와 같이 원하는 내용을 바로 찾을 수 있도록 지원
- 테이블의 데이터 조회 시 동작 속도를 높여주는 자료구조
- 데이터의 위치를 빠르게 찾아주는 역할
- 테이블 내의 1개의 컬럼, 혹은 여러 개의 컬럼을 이용하여 생성될 수 있다.
- 인덱스를 저장하는 데 필요한 디스크 공간은 보통 테이블을 저장하는 데 필요한 디스크 공간보다 작다.
    - 컬럼의 값과 레코드가 저장된 주소를 키와 값의 쌍으로 만들어 두고, 테이블의 다른 세부 항목들은 갖고 있지 않다.
- 고유 인덱스는 중복된 항목이 등록되는 것을 금지하기 때문에 인덱스의 대상인 테이블에서 고유성이 보장된다.
- MYI(MySQL Index) 파일에 인덱스 저장

![Untitled](https://user-images.githubusercontent.com/102662024/232319712-0809dad1-5346-4699-878b-4a97b885fde1.png)

### 문제점

- 필요없는 인덱스를 만들면 데이터베이스가 차지하는 공간만 늘어나고, 인덱스를 이용하여 데이터를 찾는 것이 전체 테이블을 찾는 것보다 느려짐
- 추가적인 공간 필요(DB 크기의 10%정도의 추가 공간 필요)
- 처음 인덱스를 생성하는데 많은 시간 소요
- 데이터의 변경 작업(insert, update, delete)이 자주 일어나는 경우 오히려 성능 저하가 일어날 수 있음

## Index의 종류

### 클러스터형 인덱스(clustered index)

- 특정 나열된 데이터들을 일정 기준으로 정렬해주는 인덱스(ex: 영어사전)
- 클러스터형 인덱스 생성시 데이터 페이지 전체가 다시 정렬 → 이미 대용량의 데이터가 입력된 상태라면 클러스터형 인덱스 생성시 심각한 부하가 발생
- 테이블당 하나만 생성 가능. 어느 열에 클러스터형 인덱스를 생성하는지에 따라 시스템의 성능이 달라짐
- 보조 인덱스보다 검색 속도는 더 빠르다. 단, 입력/수정/삭제는 더 느림
- MySQL의 경우 primary key가 있다면 이것을 클러스터형 인덱스로, 없다면 unique하면서 not null인 컬럼을, 그것도 없으면 임의로 보이지 않는 컬럼을 만들어 클러스터형 인덱스로 지정

### 보조 인덱스(secondary index)

- 개념적으로 후보키에만 부여 가능한 인덱스
    - 후보키: 각 데이터를 인식할 수 있는 최소한의 고유 식별 속성 집합
- 보조 인덱스 생성시 페이지는 그냥 둔 상태에서 별도의 페이지에 인덱스를 구성(자동 정렬되지 않음. ex: 책의 색인)
- 데이터가 위치하는 주소 값(RID)
- 클러스터형 인덱스보다 검색 속도는 느리지만 데이터의 입력/수정/삭제 시 성능 부하가 적음
- 테이블당 여러 개 생성 가능(너무 많이 생성시 오히려 성능 저하)

## Index 생성 전략

- 인덱스는 열 단위에 생성
- where 절에서 사용되는 열에 생성
- where 절에 사용되는 열이라도 자주 사용해야 가치가 있음
- 데이터 중복도가 높은 열에는 인덱스를 만들어도 효과가 없음
    - 중복도가 낮은 열에 생성
- 외래키를 설정한 열에는 자동으로 외래키 인덱스가 생성됨
- 조인에 자주 사용되는 열에는 인덱스를 생성하는 것이 좋음
- 데이터 변경(삽입, 수정, 삭제) 작업이 얼마나 자주 일어나는지를 고려해야 함
- 사용하지 않는 인덱스는 제거

## 자동 생성 Index

### 클러스터형 인덱스

- primary key를 설정하면 자동으로 해당 열에 클러스터형 인덱스가 생성

```sql
create table test_tbl1
(
		a int primary key,
		b int,
		c int
);
show index from test_tb1;
```

![Untitled 1](https://user-images.githubusercontent.com/102662024/232319703-27516946-37f8-4dec-a286-2fee65c4d146.png)

- 기본키와 unique 제약조건 설정

```sql
create table test_tbl2
(
		a int primary key,
		b int unique,
		c int unique
);
show index from test_tb2;
```

![Untitled 2](https://user-images.githubusercontent.com/102662024/232319705-5a9f80e8-8a6d-402a-8ac4-8a6de809e4b0.png)

- 기본키 없이 unique 제약조건만 설정

```sql
create table test_tbl3
(
		a int unique,
		b int unique,
		c int unique
);
show index from test_tb3;
```

![Untitled 3](https://user-images.githubusercontent.com/102662024/232319707-1f9e59b6-b5ad-4069-9a44-d29a96503354.png)

- unique 제약조건을 설정한 열 중 하나에 클러스터형 인덱스 생성

```sql
create table test_tbl4
(
		a int unique not null,
		b int unique,
		c int unique,
		d int
);
show index from test_tb4;
```

![Untitled 4](https://user-images.githubusercontent.com/102662024/232319708-6f29ea44-8ccc-441a-aaf3-776ffbeef3c6.png)

- a 열에는 unique 제약 조건에 not null을 설정하고 d 열에는 기본키 설정

 

```sql
create table test_tbl5
(
		a int unique not null,
		b int unique,
		c int unique,
		d int primary key
);
show index from test_tb5;
```

![Untitled 5](https://user-images.githubusercontent.com/102662024/232319710-ae329c16-1655-418f-8d0c-2ebeff5c46d1.png)

## Index 활용

### Index 생성

- create index 문으로 인덱스를 만들면 보조 인덱스가 생성
- create index 문으로는 클러스터형 인덱스를 만들 수 없으며, 클러스터형 인덱스를 만들려면 alter table을 사용해야 함
- create index 문의 unique 옵션은 고유한 인덱스를 만들 때 사용
- ASC, DESC로 정렬 방식 지정
- index_type은 생략 가능하며, 생략할 경우 기본값인 B-Tree 형식 사용

```sql
// 인덱스 확인
show index from 테이블명;
// 단순 보조 인덱스 생성
create index Key_name on 테이블명(컬럼명);
// 고유 보조 인덱스 생성
create unique index Key_name on 테이블명(컬럼명);
// 컬럼 2개 인덱스 생성
create index Key_name on 테이블명(컬럼1,컬럼2);
```

- 고유 보조 인덱스를 만들고 같은 속성을 갖는 레코드를 추가하면 에러가 발생한다.

### 인덱스를 활용한 select

- full table scan

- 클러스터형 인덱스 참조

- 보조 인덱스 참조

### Index 삭제

- 인덱스를 모두 삭제할 때는 보조 인덱스부터 삭제
- 인덱스를 많이 생성해놓은 테이블의 경우 각 인덱스의 용도를 확인한 후 활용도가 떨어지는 인덱스를 삭제

```sql
drop index 인덱스이름 on 테이블이름;
```

- 자동으로 생성된 클러스터형 인덱스 삭제

```sql
alter table 테이블이름 drop primary key;
```
