package org.example.desing_pattern.decorator;

/**
 * 장식자 패턴은 동일한 인터페이스를 구현한 컴포넌트(객체)와 장식자 객체로 이루어집니다.
 * 타깃 클래스와 장식자 사이에 공유 인터페이스를 두고
 * 장식자는 인터페이스를 구현한 인스턴스의 참조체를 지니고 있어야 한다.
 */
public interface Order {
    double getPrice(); //장식자와
    String getLabel(); //타겟이 공용으로 사용할 인터페이스

}
