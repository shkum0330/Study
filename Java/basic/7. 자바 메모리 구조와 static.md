# 7. 자바 메모리 구조와 static

# 자바 메모리 구조

<img width="753" alt="Untitled" src="https://github.com/shkum0330/study/assets/102662024/17ed01ad-fb93-44f9-b340-dd0eccf8cb95">

- **메서드 영역(Method Area):** 메서드 영역은 프로그램을 실행하는데 필요한 공통 데이터를 관리한다. 이 영역은 **프로그램의 모든 영역에서 공유**한다.
    - 클래스 정보: 클래스의 실행 코드(바이트 코드), 필드, 메서드와 생성자 코드등 모든 실행 코드가 존재한다.
    - static 영역: `static` 변수들을 보관한다.
    - 런타임 상수 풀: 프로그램을 실행하는데 필요한 공통 리터럴 상수를 보관한다.
        - 예를 들어 `“hello”`라는 리터럴 문자가 있으면 이런 문자를 공통으로 묶어서 관리한다.
        - 이외에도 프로그램을 효율적으로 관리하기 위한 상수들을 관리한다.
- **스택 영역(Stack Area):** 자바 실행 시, 하나의 실행 스택이 생성된다. 각 스택 프레임은 지역 변수, 중간 연산 결과, 메서드 호출 정보 등을 포함한다.
    - 스택 프레임: 스택 영역에 쌓이는 네모 박스가 하나의 스택 프레임이다. 메서드를 호출할 때 마다 하나의 스택 프레임이 쌓이고, 메서드가 종료되면 해당 스택 프레임이 제거된다.
    - 스택 영역은 더 정확히는 각 쓰레드별로 하나의 실행 스택이 생성된다. 따라서 쓰레드 수 만큼 스택 영역이생성된다. 지금은 쓰레드를 1개만 사용하므로 스택 영역도 하나이다.
- **힙 영역(Heap Area):** 객체(인스턴스)와 배열이 생성되는 영역이다. 가비지 컬렉션(GC)이 이루어지는 주요 영역이며, 더 이상 참조되지 않는 객체는 GC에 의해 제거된다.

### 메서드 코드는 메서드 영역에

<img width="753" alt="Untitled 1" src="https://github.com/shkum0330/study/assets/102662024/61327671-eb46-4ab0-9a14-7d0b577d638d">

- 자바에서 특정 클래스로 100개의 인스턴스를 생성하면, 힙 메모리에 100개의 인스턴스가 생긴다. 각각의 인스턴스는 내부에 변수와 메서드를 가진다.
- 같은 클래스로 부터 생성된 객체라도 인스턴스 내부의 변수 값은 서로 다를 수 있지만, 메서드는 공통된 코드를 공유한다.
- 따라서 객체가 생성될 때, 인스턴스 변수에는 메모리가 할당되지만, **메서드에 대한 새로운 메모리 할당은 없다.** 메서드는 메서드 영역에서 공통으로 관리되고 실행된다.
- 정리하면 인스턴스의 메서드를 호출하면 실제로는 메서드 영역에 있는 코드를 불러서 수행한다.

# 스택 영역

```java
public static void main(String[] args) {
	method1(10);
}

static void method1(int m1) {
	int cal = m1 * 2;
	method2(cal);
}

static void method2(int m2) {

}
```

### 호출

<img width="759" alt="Untitled 2" src="https://github.com/shkum0330/study/assets/102662024/532c70b0-8e3b-4c5e-a319-05ef663f2865">

1. 처음 자바 프로그램을 실행하면 `main()`을 실행한다. 이때 `main()`을 위한 스택 프레임이 하나 생성된다.
    - `main()` 스택 프레임은 내부에 `args`라는 매개변수를 가진다.
2. `main()`은 `method1()`을 호출한다. `method1()` 스택 프레임이 생성된다.
    - `method1()`는 `m1`, `cal` 지역 변수(매개변수 포함)를 가지므로 해당 지역 변수들이 스택 프레임에 포함된다.
3. `method1()`은 `method2()`를 호출한다. `method2()` 스택 프레임이 생성된다.
    - `method2()`는 `m2` 매개변수를 가지므로 해당 매개변수가 스택 프레임에 포함된다.
    - 

### 종료

<img width="751" alt="Untitled 3" src="https://github.com/shkum0330/study/assets/102662024/1646062f-1b26-4617-a9a3-50d01909a486">

1. `method2()`가 종료된다. 이때 `method2()` 스택 프레임이 제거되고, 매개변수 `m2`도 제거된다. 
    - 프로그램은 `method1()`으로 돌아간다. `method1()`을 처음부터 시작하는 것이 아니라 `method1()`에서 `method2()`를 호출한 지점으로 돌아간다.
2. `method1()`이 종료된다. 이때 `method1()` 스택 프레임이 제거되고, 지역 변수 `m1`, `cal`도 제거된다.
    - 프로그램은 `main()`으로 돌아간다.
3. `main()`이 종료된다. 더 이상 호출할 메서드가 없고, 스택 프레임도 완전히 비워졌다.
4. 자바는 프로그램을 정리하고 종료한다.

# 스택 영역과 힙 영역

```java
public class Data {
	private int value;

	public Data(int value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}
}
```

```java
public static void main(String[] args) {
	method1();
}

static void method1() {
	Data data1 = new Data(10);
	method2(data1);
}

static void method2(Data data2) {
	System.out.println("data.value=" + data2.getValue());
}
```

## 동작 과정

### 호출

<img width="408" alt="Untitled 4" src="https://github.com/shkum0330/study/assets/102662024/65c71724-f0cd-42d4-87a9-3711449286e8">

- 처음 `main()` 메서드를 실행한다. `main()` 스택 프레임이 생성된다.

<img width="760" alt="Untitled 5" src="https://github.com/shkum0330/study/assets/102662024/73717870-91bc-4e5b-a636-80b03830a4b6">

- `main()`에서 `method1()`을 실행한다. `method1()` 스택 프레임이 생성된다.
- `method1()`은 지역 변수로 `Data data1`을 가지고 있다. 이 지역 변수도 스택 프레임에 포함된다.
- `method1()`은 `new Data(10)`을 사용해서 힙 영역에 `Data` 인스턴스를 생성하고, 참조값을 `data1`에 보관한다.

<img width="750" alt="Untitled 6" src="https://github.com/shkum0330/study/assets/102662024/59f173a6-abb4-440b-9339-a78b53300d41">

- `method1()`은 `method2()`를 호출하면서 `Data data2` 매개변수에 `x001` 참조값을 넘긴다.
- `method1()`에 있는 `data1`과 `method2()`에 있는 `data2` 지역 변수(매개변수)는 둘 다 같은 `x001` 인스턴스를 참조한다.

### 종료

<img width="749" alt="Untitled 7" src="https://github.com/shkum0330/study/assets/102662024/c71cc7cd-f7ea-42b9-9794-500616454745">

- `method2()`가 종료된다. `method2()`의 스택 프레임이 제거되면서 매개변수 `data2`도 함께 제거된다.

<img width="749" alt="Untitled 8" src="https://github.com/shkum0330/study/assets/102662024/a30641e0-2bc9-4ac9-a677-8339d121f782">

- `method1()`이 종료된다. `method1()`의 스택 프레임이 제거되면서 매개변수 `data1`도 함께 제거된다.

<img width="744" alt="Untitled 9" src="https://github.com/shkum0330/study/assets/102662024/1fbdae6f-dd75-4285-81f1-f2bcb4fafa6a">

- `method1()`이 종료된 직후의 상태를 보면, `x001` 참조값을 가진 `Data` 인스턴스를 참조하는 곳이 더는 없다.
- 참조하는 곳이 없으므로 사용되는 곳도 없다. 결과적으로 프로그램에서 더는 사용하지 않는 객체인 것이다. 이런객체는 메모리만 차지하게 된다.
- GC(가비지 컬렉션)은 이렇게 참조가 모두 사라진 인스턴스를 찾아서 메모리에서 제거한다.

> 힙 영역 외부가 아닌, 힙 영역 안에서만 인스턴스끼리 서로 참조하는 경우에도 GC의 대상이 된다.
> 

# static 변수

- `static` 키워드는 주로 멤버 변수와 메서드에 사용된다.
- 특정 클래스에서 공용으로 함께 사용할 수 있는 변수이다.

```java
public class Data3 {
	public String name;
	public static int count; //static

	public Data3(String name) {
		this.name = name;
		count++;
	}
}
```

```java
public class DataCountMain3 {
	public static void main(String[] args) {
		Data3 data1 = new Data3("A");
		System.out.println("A count=" + Data3.count); // 1
		Data3 data2 = new Data3("B");
		System.out.println("B count=" + Data3.count); // 2
		Data3 data3 = new Data3("C");
		System.out.println("C count=" + Data3.count); // 3
	}
}
```

## 동작 과정

<img width="750" alt="Untitled 10" src="https://github.com/shkum0330/study/assets/102662024/301b9969-3736-4a41-9b70-bec6ecf82924">

- `static`이 붙은 멤버 변수는 메서드 영역에서 관리한다.
    - `static`이 붙은 멤버 변수 `count`는 정적 변수로 인스턴스 영역에 생성되지 않고, 메서드 영역에서 이 변수를 관리한다.
- `Data3("A")` 인스턴스를 생성하면 생성자가 호출된다.
    - 생성자에는 `count++` 코드가 있다. 메서드 영역에 있는 `count`의 값이 하나 증가된다.

<img width="751" alt="Untitled 11" src="https://github.com/shkum0330/study/assets/102662024/fa1114a3-61ff-49ee-969d-30b0f3efe08e">

- `Data3("B")` 인스턴스를 생성하면 마찬가지로 생성자가 호출되고, 메서드 영역에 있는 `count`의 값이 하나 증가된다.
- `Data3("C")` 인스턴스를 생성하는 경우에도 마찬가지로 메서드 영역에 있는 `count`의 값이 하나 증가된다.

<img width="748" alt="Untitled 12" src="https://github.com/shkum0330/study/assets/102662024/8c55f1e7-69fb-44de-8b0c-08e475572959">

- 최종적으로 메서드 영역에 있는 `count` 변수의 값은 3이 된다.
- `static`이 붙은 정적 변수에 접근하려면 `Data3.count` 와 같이 클래스명 + `.` + 변수명으로 접근하면 된다.
- `Data3`의 생성자와 같이 자신의 클래스에 있는 정적 변수라면 클래스명을 생략할 수 있다.

## 용어 정리

### 멤버 변수(필드)의 종류

- **인스턴스 변수:** `static`이 붙지 않은 멤버 변수, 예) name
    - 인스턴스를 생성해야 사용할 수 있고, 인스턴스에 소속되어 있다.
    - 인스턴스를 만들 때마다 새로 만들어진다.
- **클래스 변수:** `static`이 붙은 멤버 변수, 예) count
    - 클래스 변수, 정적 변수, static 변수등으로 부른다. 용어를 모두 사용하니 주의하자.
    - 인스턴스와 무관하게 클래스에 바로 접근해서 사용할 수 있고, 클래스 자체에 소속되어 있다. 따라서 클래스 변수라 한다.
    - 자바 프로그램을 시작할 때 딱 1개가 만들어진다. 인스턴스와는 다르게 보통 여러곳에서 공유하는 목적으로 사용된다.

## 변수와 생명주기

- **지역 변수(매개변수 포함):** 지역 변수는 스택 영역에 있는 스택 프레임 안에 보관된다. 메서드가 종료되면 스택 프레임도 제거 되는데 이때 해당 스택 프레임에 포함된 지역 변수도 함께 제거된다. 따라서 생존 주기가 짧다.
- **인스턴스 변수:** 인스턴스에 있는 멤버 변수를 인스턴스 변수라 한다. 인스턴스 변수는 힙 영역을 사용한다. 힙 영역은 GC(가비지 컬렉션)가 발생하기 전까지는 생존하기 때문에 보통 지역 변수보다 생존 주기가 길다.
- **클래스 변수:** 클래스 변수는 메서드 영역의 static 영역에 보관되는 변수이다. 메서드 영역은 프로그램 전체에서 사용하는 공용 공간이다. 클래스 변수는 해당 클래스가 JVM에 로딩 되는 순간 생성된다. 그리고 JVM이 종료될 때까지 생명주기가 어어진다. 따라서 가장 긴 생명주기를 가진다.
- 인스턴스 변수는 동적으로 생성되고 제거되지만, static 변수는 거의 프로그램 실행 시점에 만들어지고, 프로그램 종료 시점에 제거되기 때문에 정적이라고 한다.

## 정적 변수 접근법

- static 변수는 클래스를 통해 바로 접근할 수도 있고, 인스턴스를 통해서도 접근할 수 있다.

```java
//인스턴스를 통한 접근
Data3 data4 = new Data3("D");
System.out.println(data4.count); // 4

//클래스를 통합 접근
System.out.println(Data3.count); // 4
```

- 둘의 차이는 없다. 둘 다 결과적으로 정적 변수에 접근한다
- 하지만 정적 변수의 경우 인스턴스를 통한 접근은 추천하지 않는다. 코드를 읽을 때 마치 인스턴스 변수에 접근하는 것 처럼 오해할 수 있기 때문이다.
- 정적 변수는 클래스에서 공용으로 관리하기 때문에 클래스를 통해서 접근하는 것이 더 명확하다.
- 따라서 정적 변수에 접근할 때는 클래스를 통해서 접근하자.

# static 메서드

- static 메서드는 정적 변수처럼 인스턴스 생성 없이 클래스 명을 통해서 바로 호출할 수 있다.

`"hello"`라는 문자열 앞 뒤에 `*`을 붙여서 `"*hello*"` 와 같이 꾸며주는 기능을 만들어보자.

```java
public class DecoUtil {
	public **static** String deco(String str) {
		String result = "*" + str + "*";
		return result;
	}
}
```

```java
String s = "hello java";
String deco = DecoUtil.deco(s); // *hello java*
```

- `deco()`라는 기능은 멤버 변수도 없고, 단순히 기능만 제공한다.
- 인스턴스가 필요한 이유는 멤버 변수(인스턴스 변수)등을 사용하는 목적이 큰데, 정적 메서드 덕분에 불필요한 객체 생성 없이 편리하게 메서드를 사용할 수 있다.

### 클래스 메서드

- 메서드 앞에 `static`을 붙인 것을 **정적 메서드** 또는 **클래스 메서드**라 한다. **정적 메서드**라는 용어는 `static` 이 정적이라는 뜻이기 때문이고, **클래스 메서드**라는 용어는 인스턴스 생성 없이 마치 클래스에 있는 메서드를 바로 호출하는 것 처럼 느껴지기 때문이다.

### 인스턴스 메서드

- `static`이 붙지 않은 메서드는 인스턴스를 생성해야 호출할 수 있다. 이것을 **인스턴스 메서드**라 한다.

## 정적 메서드 사용법

- `static` 메서드는 `static`만 사용할 수 있다.
    - 클래스 내부의 기능을 사용할 때, 정적 메서드는 `static`이 붙은 정적 메서드나 정적 변수만 사용할 수 있다.
        - 인스턴스 변수나, 인스턴스 메서드를 사용할 수 없다.
- 반대로 모든 곳에서 `static`을 호출할 수 있다.
    - 정적 메서드는 공용 기능이므로 접근 제어자만 허락한다면 클래스를 통해 모든 곳에서 `static`을 호출할 수 있다.

```java
public class DecoData {
	private int instanceValue;
	private static int staticValue;

	public static void staticCall() {
		//instanceValue++; //인스턴스 변수 접근, compile error
		//instanceMethod(); //인스턴스 메서드 접근, compile error
		staticValue++; // 정적 변수 접근
		staticMethod(); // 정적 메서드 접근
	}

	public void instanceCall() {
		instanceValue++; // 인스턴스 변수 접근
		instanceMethod(); // 인스턴스 메서드 접근
		staticValue++; // 정적 변수 접근
		staticMethod(); // 정적 메서드 접근
	}

	private void instanceMethod() {
		System.out.println("instanceValue=" + instanceValue);
	}

	private static void staticMethod() {
		System.out.println("staticValue=" + staticValue);
	}
}
```

```java
System.out.println("1.정적 호출");
DecoData.staticCall(); // staticValue=1

System.out.println("2.인스턴스 호출1");
DecoData data1 = new DecoData();
data1.instanceCall(); // instanceValue=1, staticValue=2

System.out.println("3.인스턴스 호출2");
DecoData data2 = new DecoData();
data2.instanceCall(); // instanceValue=1, staticValue=3
```

<img width="748" alt="Untitled 13" src="https://github.com/shkum0330/study/assets/102662024/71bfbc3a-5268-40ae-8c86-0ed354a4fa2d">

### 정적 메서드가 인스턴스의 기능을 사용할 수 없는 이유

- 정적 메서드는 클래스의 이름을 통해 바로 호출할 수 있기 때문에, 인스턴스처럼 참조값 개념이 없다.
- 특정 인스턴스의 기능을 사용하려면 참조값을 알아야 하는데, 정적 메서드는 참조값 없이 호출한다.
- 따라서 정적 메서드 내부에서 인스턴스 변수나 인스턴스 메서드를 사용할 수 없다.
- 객체의 참조값을 직접 매개변수로 전달하면 정적 메서드도 인스턴스의 변수나 메서드를 호출할 수 있다.

```java
public static void staticCall(DecoData data) {
	data.instanceValue++;
	data.instanceMethod();
}
```

## 용어 정리

### 멤버 메서드의 종류

- **인스턴스 메서드:** `static`이 붙지 않은 멤버 메서드
    - 인스턴스를 생성해야 사용할 수 있다.
- **클래스 메서드:** `static`이 붙은 멤버 메서드
    - 클래스 메서드, 정적 메서드, `static` 메서드 등으로 부른다.
    - 인스턴스와 무관하게 클래스에 바로 접근해서 사용할 수 있고, 클래스 자체에 소속되어 있다.

### 정적 메서드 활용

- 정적 메서드는 객체 생성이 필요 없이 메서드의 호출만으로 필요한 기능을 수행할 때 주로 사용한다.
    - 예를 들어 간단한 메서드 하나로 끝나는 유틸리티성 메서드에 자주 사용한다.
        - 수학의 여러가지 기능을 담은 클래스를 만들 수 있는데, 이 경우 보통 인스턴스 변수 없이 입력한 값을 계산하고 반환하는 것이 대부분이다.

## 정적 메서드 접근법

- static 메서드는 static 변수와 마찬가지로 클래스를 통해 바로 접근할 수 있고, 인스턴스를 통해서도 접근할 수 있다.
- 그런데 인스턴스를 통한 접근은 추천하지 않는다. 왜냐하면 코드를 읽을 때 마치 인스턴스 메서드에 접근하는 것 처럼 오해할 수 있기 때문이다.
- 클래스에서 공용으로 관리하기 때문에 클래스를 통해서 접근하는 것이 더 명확하다. 따라서 클래스를 통해 접근하는 것이 좋다.

### static import

- 정적 메서드를 사용할 때 해당 메서드를 자주 호출해야 한다면 `static import` 기능을 고려하자.

```java
import static static2.DecoData.staticCall; // 특정 클래스의 정적 메서드 하나만 적용
import static static2.DecoData.*; // 특정 클래스의 모든 메서드에 적용

public class DecoDataMain {
	public static void main(String[] args) {
		DecoData.staticCall(); // 클래스명을
		staticCall(); // 이렇게 생략 가능
	}
}
```

- `import static`은 정적 메서드 뿐만 아니라 정적 변수에도 사용할 수 있다.

## main() 메서드는 정적 메서드

- 인스턴스 생성 없이 실행하는 가장 대표적인 메서드가 바로 `main()` 메서드이다.
- `main()` 메서드는 프로그램을 시작하는 시작점이다. 이것은 `static`이기 때문에 객체를 생성하지 않아도 작동한다.
- 정적 메서드는 같은 클래스 내부에서 정적 메서드만 호출할 수 있다. 따라서 정적 메서드인 `main()` 메서드가 같은 클래스에서 호출하는 메서드도 정적 메서드로 선언해서 사용했다.

# 문제와 풀이

## 문제1: 구매한 자동차 수

- [Car](https://github.com/shkum0330/java-practice/blob/main/java-basic/src/ch7/ex1/Car.java)

## 문제2: 수학 유틸리티 클래스

- [MathArrayUtils](https://github.com/shkum0330/java-practice/blob/main/java-basic/src/ch7/ex2/MathArrayUtils.java)