JPA의 성능 최적화 기능

1. 1차 캐시와 동일성 보장
2. 트랜잭션을 지원하는 쓰기 지연
3. 지연 로딩




## CASCADE PERSIST
```java
//Parent 는 @OneToMany 로 Child 필드에 CASCADE 조건을 적용해놓은 상태이다.

Parent parent = new Parent();
Child child1 = new Child();
Child child2 = new Child();
parent.addChild(child1);
parent.addChild(child2);
em.persist(parent);
```

### 1. 연관관계 메서드 사용
```java
public void addChild(Child child) {
    childList.add(child);
    child.setParent(this);
}


//결과
SELECT * FROM PARENT;
ID  	NAME  
1	null

SELECT * FROM CHILD;
ID  	NAME  	PARENT_ID  
2	null	1
3	null	1
```
### 2. 연관관계 메서드 미사용
```java
public void addChild(Child child) {
    childList.add(child);
}

//결과
SELECT * FROM PARENT;
ID  	NAME  
1	null

SELECT * FROM CHILD;
ID  	NAME  	PARENT_ID  
2	null	null
3	null	null
```
CASCADE 의 적용대상인 child 객체들은 영속성 컨텍스트에 들어가 커밋되어 DB에 저장되는 것은 둘 다 동일하지만, child 객체들은 parent 객체의 id 값을 수동으로 넣지 않으면
알 수 없다.

## CASCADE DELETE
```java
Hibernate: 
    /* delete hellojpa.example.cascade.Child */ delete 
        from
            Child 
        where
            id=?
Hibernate: 
    /* delete hellojpa.example.cascade.Parent */ delete 
        from
            Parent 
        where
            id=?
```
@ManyToOne 즉, 외래키를 가진 Child(자식 테이블=참조하는 쪽)이 먼저 제거된 후, Parent(부모 테이블=참조되는 쪽)이 제거된다. 

무결성 제약조건이 적용된다.
