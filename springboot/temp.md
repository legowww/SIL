
둘다 프록시를 사용하지만 의도에 따라 프록시 패턴과 데코레이터 패턴으로 구분한다.
1. 프록시 패턴: 접근 제어가 목적
2. 데코레이터 패턴: 부가 기능 추가


프록시 객체는 스프링 컨테이너가 관리하고 자바 힙 메모리에도 올라간다. 반면에 실제 객체는 자바 힙
메모리에는 올라가지만 스프링 컨테이너가 관리하지는 않는다.



- JDK 동적 프록시: 인터페이스에만 프록시 적용이 가능하다. `InvocationHandler`를 구현하여 사용한다. 생성된 프록시는 `com.sun.proxy.$Proxy1
`형식의 클래스 이름을 가진다.

- CGLIB: 구체 클래스와 인터페이스 모두 프록시 적용이 가능하다. `MethodInterceptor`를 구현하여 사용한다. 생성된 프록시는 `대상클래스$$EnhancerByCGLIB$$임의코드`
형식의 클래스 이름을 가진다.

---
# inner class 스프링 빈 등록 TEST

## 1. outer class - inner static class

```java
public class AspectV5Order {

    @Order(2)
    @Aspect
    @Component
    public static class LogAspect {
        // 포인트컷
        @Around("hello.aop.order.aop.Pointcuts.allOrder()")
        // 어드바이스
        public Object doLog(ProceedingJoinPoint joinPoint) throws Throwable {
            log.info("[log] {}", joinPoint.getSignature()); // join point 시그니처 * hello.aop.order..*(..) -> void hello.aop.order.OrderService.orderItem(String)
            return joinPoint.proceed();
        }
    }
```
outer class 에 `@Component`를 붙이지 않고, inner static class 에만 `@Component`를 붙여도 스프링 빈으로 등록이 된다. 

static class 는 outer class 없이도 생성할 수 있기 때문이다.

## 2. outer class - inner class
```java
public class AspectV5OrderNonStatic {

    @Order(2)
    @Aspect
    @Component
    public class LogAspect {
        // 포인트컷
        @Around("hello.aop.order.aop.Pointcuts.allOrder()")
        // 어드바이스
        public Object doLog(ProceedingJoinPoint joinPoint) throws Throwable {
            log.info("[log:non-static] {}", joinPoint.getSignature()); // join point 시그니처 * hello.aop.order..*(..) -> void hello.aop.order.OrderService.orderItem(String)
            return joinPoint.proceed();
        }
    }
```
어드바이스가 작동하지 않는다. 즉, inner class 는 스프링 빈으로 등록되지 않았다.

```java
@Component
public class AspectV5OrderNonStatic {

    @Order(2)
    @Aspect
    @Component
    public class LogAspect {
        // 포인트컷
        @Around("hello.aop.order.aop.Pointcuts.allOrder()")
        // 어드바이스
        public Object doLog(ProceedingJoinPoint joinPoint) throws Throwable {
            log.info("[log:non-static] {}", joinPoint.getSignature()); // join point 시그니처 * hello.aop.order..*(..) -> void hello.aop.order.OrderService.orderItem(String)
            return joinPoint.proceed();
        }
    }
```
어드바이스가 작동한다. outer class, inner class 둘 다 @Conponent 가 필수적으로 붙어있어야 inner class 들이 정상적으로 스프링 빈에 등록된다.


> 결론: static class 는 outer class 없이도 생성할 수 있기 때문에, inner class 로 스프링 빈 등록을 하고 싶다면 꼭 static 속성을 붙여주자.
