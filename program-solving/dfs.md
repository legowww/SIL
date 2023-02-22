# DFS

### 1. path 안에 중복 없이 원소 저장하기
https://www.youtube.com/watch?v=exwk905In0U&list=PLodgw23vNd_U66omABrprSwJBZhKPFGMM&index=17 강의 참조
```python
# 기존 방식
import sys

def dfs(idx, path):
    if idx == m:
        print(' '.join(path))
        return

    for i in range(len(arr)):
        if str(arr[i]) not in path:  # in 은 O(len(path) 만큼의 시간복잡도를 가진다.
            dfs(idx + 1, path + [str(arr[i])])
n, m = map(int, sys.stdin.readline().rstrip().split())
arr = list(range(1, n + 1))
dfs(0, [])
```

```python
# visited 리스트 사용
import sys

def dfs(idx, path):
    if idx == m:
        print(' '.join(path))
        return

    for i in range(len(arr)):
        if visited[i] == 0:  # O(1) 만큼의 시간복잡도만 가진다.
            visited[i] = 1 
            dfs(idx + 1, path + [str(arr[i])])
            visited[i] = 0

n, m = map(int, sys.stdin.readline().rstrip().split())
arr = list(range(1, n + 1))

visited = [0] * 9  
dfs(0, [])
```



---
### 2. 모든 경우의 수를 트리로 표현할 때, 이진 트리 구조를 가지는 경우
```
가능한 모든 경우의 수를 트리 형태로 나타낼 때 현재 idx를 포함, 미포함 두 가지의 형태를 가지는 이진 트리 구조를 가지는 문제
N의 범위가 중요하다. 2^20은 대략적으로 백만이다.
```
[백준 1182번: 부분수열의 합](https://velog.io/@legowww/%EB%B0%B1%EC%A4%80-1182%EB%B2%88-%EB%B6%80%EB%B6%84%EC%88%98%EC%97%B4%EC%9D%98-%ED%95%A9)

---
### 3. 집합(이동가능한 영역)의 개수와 집합별 넓이 구하기
[백준 2583번: 영역 구하기](https://velog.io/@legowww/%EB%B0%B1%EC%A4%80-2583%EB%B2%88-%EC%98%81%EC%97%AD-%EA%B5%AC%ED%95%98%EA%B8%B0)

---
### 4. Cycle
```
KEY 값의 방문 여부를 나타내는 visited 가 존재한다. 
싸이클 문제는 임의의 KEY 값을 한 번만 방문해도 해당 KEY 의 싸이클 포함/미포함 여부를 파악할 수 있게 설계되었다.

1   ->    2->5->3
        (싸이클 형성)

위의 예시처럼 DFS(1)로 시작한 탐색에서 1이 포함되지 않는 2->5->3 싸이클을 찾았을 경우
2, 5, 3을 싸이클로 추가하고 visited 를 통해 방문 여부를 갱신했기 때문에 다시 방문하지 않아도 된다.

주어진 N이 빠듯한 경우 이러한 특성을 살리는 풀이를 하자.
```
[[백준 9466번] 텀 프로젝트](https://velog.io/@legowww/%EB%B0%B1%EC%A4%80-9466%EB%B2%88-%ED%85%80-%ED%94%84%EB%A1%9C%EC%A0%9D%ED%8A%B8)

```
시간 복잡도가 여유로울 것 같으면 아래의 풀이들도 괜찮다.
```
[[백준 2668번] 숫자 고르기](https://velog.io/@legowww/%EB%B0%B1%EC%A4%80-2668%EB%B2%88-%EC%88%AB%EC%9E%90-%EA%B3%A0%EB%A5%B4%EA%B8%B0)

[[프로그래머스] 혼자 놀기의 달인](https://velog.io/@legowww/%ED%94%84%EB%A1%9C%EA%B7%B8%EB%9E%98%EB%A8%B8%EC%8A%A4-%ED%98%BC%EC%9E%90-%EB%86%80%EA%B8%B0%EC%9D%98-%EB%8B%AC%EC%9D%B8)

### 5. 비내림차순 증가
```python
def dfs(cnt, idx, path):
    ...
    for i in range(idx, N+1):
        dfs(cnt+1, i+1, path + [arr[i]]

```
`arr`이 내림차순으로 정렬일 경우에만 사용할 수 있다. path 는 감소하지 않는 형태의 수열을 가진다.
https://www.acmicpc.net/problem/15664

