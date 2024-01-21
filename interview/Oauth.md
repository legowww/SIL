## OAuth
OAuth 2.0은 애플리케이션, 즉 클라이언트가 리소스 소유자(Resource Owner)로부터 리소스 서버의 자원에 대해 인가받고, 접근하기 위해 사용되는 프로토콜입니다.

OAuth 프로토콜 덕분에 사용자는 애플리케이션 서버측에 가입된 서비스의 비밀번호를 제공하지 않아도 되어 서버에 대한 신뢰성 및 비밀번호 노출로 인한 보안 문제를 해결할 수 있는 장점이 있습니다.

## Oauth2.0 기능

2.0 버전부터는 scope 를 설정하여, 클라이언트가 리소스 서버의 자원에 대해 접근할 수 있는 범위를 설정할 수 있으며, OpenID Connect 를사용하면 리소스 서버로부터 발급받은 accessToken 에 사용자의 **신원 정보가** 담겨 있어 추가적으로 자원에 대한 요청을 보내지 않아도 되어, 클라이언트와 리소스 서버간 통신 횟수를 반으로 줄일 수 있습니다.

## Authorization Code Grant동작 방식

1. 리스소 오너는 로그인 페이지에 접속하여 로그인을 진행합니다.
2. 로그인에 성공하면 Authentication Code 가 기존에 설정한 redirectURL 을 통해 전송됩니다.
3. redirectURL 는 프론트엔드로 설정하여, Authentication Code 를 받은 후 클라이언트로 POST 요청에 담에 담아서 전송합니다.
4. 클라이언트는 Authentication Code 을 사용하여 Access Token Request 요청을 인증 서버에 전송합니다.
5. 인증 서버가 응답한 Access Token 을 통해 Resorce Server 의 API 를 통해 사용자의 자원을 받을 수 있게됩니다.
