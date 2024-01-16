# 자바 컬렉션

# 자바 컬렉션이란?

다수의 데이터를 편리하게 다룰 수 있는 표준화된 API를 제공하는 클래스의 집합입니다.

데이터를 탐색, 삽입, 삭제 할 수 있는 표준화된 API 를 제공하며 Collection, List, Set, Map 등의 인터페이스와 ArrayList, HashMap 같은 구현체 클래스들로 이루어져 있습니다.

결과적으로 (컬렉션) 덕분에 개발자는 직접 자료구조를 구현할 필요 없으며  반복적으로 재사용되는 API 를 통해 큰 학습도 필요없게 되어, 비지니스 로직 개발에만 집중할 수 있습니다.

Collection 을 상속한 List, Set 인터페이스는 순회자 패턴인 Iteratble 을 사용하여 컬렉션을 탐색할 수 있습니다. 반면에 연관 배열의 자료구조인 Map 는 순회할 수 없습니다.

- Collection API: contain, add, isEmpty 등등

# List 

리스트는 순서가 존재하고 중복을 허용하는 추상 자료형입니다. 

자바에서 이를 구현한 자료구조로는 ArrayList, LinkedList 등이 있습니다. 멀티스레드 환경에서 동기화를 보장하는 Vector 같은 클래스도 있지만 기존 코드와의 호환성을 위해 남겨졌고 deprecated 되어 ArrayList 를 사용하는것이 좋습니다.

ArrayList 는 동적 배열입니다. 내부적으로 연속된 메모리 공간을 가지며, 인덱스를 통한 O(1) 검색이 가능합니다.하지만 삽입 삭제 과정에서 값들이 쉬프트될 수 있어 O(N) 시간복잡도를 가질 수 있고, 처음 할당된 크기를 모두 사용하면 기존 크기에 성장인자 값을 곱한만큼 새로운 메모리 크기를 할당받습니다. 이 과정을 더블링이라고 하며, 자바에서는 1.5로 설정되어 있습니다. 이 과정은 기존 배열의 값들을 복사한 후, 새 배열에 복사한 후, 기존배열을 GC 하는 과정으로 진행됩니다. 이 더블링 과정이 ArrayList의 큰 단점입니다.

LinkedList 는 값을 추가할때마다 동적으로 불연속적인 메모리 공간을 할당받습니다. 불필요한 메모리 공간을 사용하지 않고 사용할 만큼만 효울적으로 사용할 수 있고, 더블링 또한 없습니다. 검색에는 O(N), 삽입 삭제의 경우에도 `이동O(N)+삭제O(1)` 이 발생할 수 있습니다. 

맨 앞 혹은 맨 뒤에 데이터 삽입이 자주 일어난다면 LinkedList, 인덱스를 통한 데이터 검색이 주된 기능이라면 ArrayList 를 사용하면 좋습니다.

-ArrayList 를 동기화 하는법? → Collections.synchronizedList 메서드 사용

# SET

집합은 순서가 없고 중복을 허용하지 않는 추상 자료형입니다.

- 해시 기반
    
    **hashCode()와 equals() 메소드를 사용하여 중복 여부  확인**
    
    - HashSet: 내부적으로 HashMap 의 Key 에 저장하고, Value 는 **NULL** 저장, HashMap<Key, Null>
    - LinkedHashSet(저장 순서 유지)
- 이진 탐색 트리 기반
    - TreeSet(정렬된 순서로 저장)

# MAP

- 해시 기반
    
    **hashCode()와 equals() 메소드를 사용하여 중복 여부  확인**
    
    - HashMap: 탐색, 삽입, 삭제가 해시함수에 따라 O(1), 개별 체이닝 방식, 키밸류 NULL 허용
    - LinkedHashMap: 순서 존재
    
     멀티 스레드 환경에서 Thread-Safe 하려면?
    
    - HashTable: 구시대, Thread-Safe, 키밸류 NULL 미허용
    - SynchronizedHashMap: Thread-Safe, 동기화 방법으로 synchronized 를 통한 상호배제 사용
    - **ConcuurentHashMap**: Thread-Safe, 동기화 방법으로 아토믹 연산인 CAS 사용
        - 아토믹 연산: CPU 에 의해 원자적 수행을 보장받는 native 메서드를 통해 동작
        - concuurent 패키지, since JDK 1.5

- 이진 탐색 트리 기반
    - TreeMap: 순서 존재
