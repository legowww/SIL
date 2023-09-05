# SRP
클래스는 단일 책임을 가져야 한다.


만약 클래스가 다양한 책임(A, B, C)을 가진다면 요구사항이 변경될 때 코드를 수정해야 할 가능성이 높다.
하지만 하나의 책임(A)만을 가진다면 A에 대한 요구사항이 발생했을때만 코드를 수정하면 된다.
또한 하나의 책임만 수행하는 클래스는 높은 응집도를 가진다.

# OCP
애플리케이션에 새로운 요구사항이 추가될 때 기존의 코드에 영향을 미치지 않고 기능을 추가할 수 있다.

-> 추상화(인터페이스, 추상클래스)에 의존하면 지킬 수 있다.

- 추상화를 통해 확장을 가능하게 한다.
- 추상화에 대한 의존은 폐쇄를 가능하게 한다.
```java
//새로운 요구사항: 네이버 로그인 기능 추가
class WebService {
    private Login login; //폐쇄: NaverLogin 이 추가되더라도 WebService 는 영향 받지 않는다.
}
Login login = new GoogleLogin(); //폐쇄: 기존 기능(GoogleLogin)은 추상화에 의존하기 때문에 기능 확장에 영향받지 않는다. 
Login login = new NaverLogin(); //확장: 추상화의 구현체를 추가하면, 기존 코드에 영향을 주지 않고 기능을 확장할 수 있다. 
login.login(); //다형성: 로그인하라는 동일한 메세지를 전달받아도 다르게(구글로그인, 네이버로그인) 반응할 수 있다.
```

# LSP
자식클래스는 부모클래스를 대체할 수 있다.

-> 추상화-구현체 관계를 사용한다면 다형성을 통해 경우 자연스럽게 지켜진다.
```java
Login login = new GoogleLogin();
login.login() //로그인

GoogleLogin login = new GoogleLogin();
login.login() //동일한 로그인 작업 수행가능
```

# ISP
인터페이스를 분리하는 것이 좋다.
인터페이스를 분리할 경우 책임이 나눠지기 때문에 변경에 강한 코드를 작성하게 된다.
구현체는 꼭 필요한 메서드에만 의존해야 한다. 이용하지 않는 메서드에는 의존하지 않는다.
```java
interface LoginAndLogout {} //로그인 기능만 사용하고 싶어도 로그아웃 메서드까지 override 해야함. 
->
interface Login {}
interface Logout {}
```

# DIP
WebService 는 웹 관련 기능을 수행하는 책임을 가지는 고수준 모듈이다.
고수준 모듈은 여러 하위 기능으로 나눌 수 있다(로그인, 로그아웃 등)
저수준 모듈은 하위 기능을 실제로 구현(NaverLogin)한 것이다.
```java
//고수준모듈: WebService, Login
//저수준모듈: NaverLogin
class WebService {
    private NaverLogin login //고수준 모듈인 WebService 가 저수준 모듈인 NaverLogin 에 의존한다.
}
```
저수준 모듈에 의존하는 것의 문제점:
저수준모듈은 변경 가능성이 높다. 저수준 모듈이 변경될때마다 고수준모듈의 코드도 수정되야 한다.
(고수준모듈에 의존하면 인터페이스/구현 분리 덕분에 구현 변경에는 영향받지 않는다.)
DIP
-> 의존성의 방향은 항상 고수준모듈로 향해야(=의존해야) 한다.
```java
class WebService {
    private Login login; 
}
//상속 또한 의존의 형태이다. 
//Login 을 상속한 NaverLogin 형태도 고수준모듈에 의존하는 것
```

