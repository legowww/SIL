`CORS`: 서버 자원을 보호하기 위한 규칙

`Origin`: URL 의 프로토콜/호스트/포트가 같으면 출처가 같다고 할 수 있다.

`Cross-Origin-Request`: 다른 출처의 자원을 사용하기 위한 요청
 
 `SOP`: 웹 브라우저가 기본적으로 사용하는 단일 출처 정책

### SOP
같은 출처에 있는 자원만 사용할 수 있도록 웹 브라우저가 기본적으로 사용하는 정책이다.

XSS, CSRF 같은 공격이 발생하여 쿠키나 토큰같은 개인정보가 들어있는 정보를 다른 출처의 주소로 요청을 보내게 될 경우 보안 이슈가 발생할 수 있다. 

그렇기 때문에 웹 브라우저는 출처가 다른 서버에 요청을 보내거나 응답을 받는 것을 차단한다.

---
### CORS
CORS 는 웹 브라우저의 `SOP` 를 위반하기 때문에 발생하는 현상이다. 

서버는 요청에 대해 정상적으로 응답을 해주지만 웹 브라우저 자체적으로 에러를 발생시킨다.


웹 브라우저에서 출처가 `localhost:3000` 인 HTML 파일을 통해 `localhost:4000` 의 자원을 요청할 경우 CORS 에러가 발생한다. 

하지만 `localhost:3000` 서버에서 `localhost:4000` 에 CURL 요청을 보낼 경우에는 정상적으로 응답이 반환된다. 




---
### 클라이언트-서버 Header

1. 
`Origin`: http://localhost:3000 `Access-Control-Allow-Origin`: http://localhost:3000 

웹 브라우저는 요청 HTTP 의 Origin 값이 응답 HTTP 의 Access-Control-Allow-Origin 헤더에 존재할 경우 교차 출처 요청을 허용한다.


2. 
`Access-Control-Request-Headers`: Foo `Access-Control-Allow-Headers`: Foo
웹 브라우저는 예비 요청의 Access-Control-Request-Headers 헤더에 사용된 헤더가 응답 HTTP 의 Access-Control-Allow-Headers 에 존재할 경우 교차 출처 요청을 허용한다.


3. 
`Access-Control-Request-Methods`: PUT `Access-Control-Allow-Methods`: PUT
웹 브라우저는 예비 요청의 Access-Control-Request-Methods 헤더에 사용된 메서드가 응답 HTTP 의 Access-Control-Allow-Method 에 존재할 경우 교차 출처 요청을 허용한다.

4. 
`Access-Control-Max-Age`: 5
5초 안에 다시 요청하면 예비 요청이 발생하지 않는다. 예비 요청을 캐싱하는 기능을 수행한다.

---
### 단순 요청

- GET, POST, HEAD
- 안전한 헤더
    - Accept
    - Accept-Language
    - Content-Language
    - Content-Type

위 조건을 충족할 경우 예비 요청을 사용하지 않고도 CORS 통신이 가능하다. 

하지만 `application/x-www-form-urlencoded`, `multipart/form-data`, `text/plain` Content-Type 만을 허용하기 때문에 `application/json` 을 사용하는 대부분의 요청에서는 사용될 수 없다. 

따라서 대부분의 요청은 예비 요청을 사용한다고 이해하면 된다. 

---
### 예비 요청
OPTIONS 라는 HTTP 메서드를 통해 요청한다.
`204 NO CONTENT` 상태 코드를 가진다.

Access-Control-Request-Headers, Access-Control-Request-Methods 등의 헤더를 예비 요청에 담아서 서버에 전송한다. 예비 요청을 최소화 하기 위해 서버 측에서는 `Access-Control-Max-Age` 를 설정할 수 있다.

---
### 인증된 요청
웹 브라우저는 별도의 요청 없이는 쿠키와 같은 인증 데이터를 요청 데이터에 넣지 않는다. 이때 클라이언트 요청이 쿠키를 통해서 인증을 해야 하는 경우 클라이언트 측에서는 credentials 옵션을 설정하고, 서버 측에서는 `Access-Control-Allow-Credentials : true` 를 설정한다.

---
> REF
- https://www.youtube.com/watch?v=zA_YAuwPrcA&t=1025s
- https://jeonghwan-kim.github.io/2023/12/12/cors
- https://inpa.tistory.com/entry/WEB-📚-CORS-💯-정리-해결-방법-👏#
