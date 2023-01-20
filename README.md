# til


임시 메모

`@Component`가 붙은 클래스들은 `@ComponentScan`를 통해 스프링 컨테이너에 싱글톤 빈으로 생성과 동시에 의존성 주입이 이뤄진다.
```
1. 스캔으로 컴포넌트 찾는다.
Identified candidate component class: file [...\core\out\production\classes\hello\core\member\MemberServiceImpl.class]
Identified candidate component class: file [...\core\out\production\classes\hello\core\common\MyLogger.class]
...

2. 스프링빈이 컨테이너에 생성된다.
Creating shared instance of singleton bean 'memberServiceImpl'
Creating shared instance of singleton bean 'myLogger'
...
이때 의존성 주입이 필요한 스프링 빈이라면 의존성 주입이 이뤄진다.
Creating shared instance of singleton bean 'orderServiceImpl'
Autowiring by type from bean name 'orderServiceImpl' via constructor to bean named 'memoryMemberRepository'
Autowiring by type from bean name 'orderServiceImpl' via constructor to bean named 'rateDiscountPolicy'
```


