# DFS

### 0. while + dfs
```python
class Solution:
    def findItinerary(self, tickets: List[List[str]]) -> List[str]:        
        d = defaultdict(list)
        for u, v in tickets:
            d[u].append(v)

        for u in d:
            d[u].sort(reverse=True)

        def dfs(src):
            while d[src]:
                dest = d[src].pop()
                dfs(dest)
            result.append(src)
        
        
        result = []
        dfs('JFK')

        return result[::-1]

"""
이 문제는 무조건 방문하는 경우만 주어지므로 예외를 생각하지 말자.

방문한 src 노드가 다른 노드로의 출구가 없는 경우라면, 이 src 노드를 마지막에 방문해야만한다.
`[A,B],[B,C],[C,D]` 라면 A->B->C->D 순으로 방문할 것이다.

dfs 호출 특성에 맞게 마지막에 방문한 노드먼저 result 에 추가할 수 밖에 없으므로
마지막에 리버스 연산을 수행해준다.


                     dfs(D) D 추가
              dfs(C) dfs(C) dfs(C) C 추가
       dfs(B) dfs(B) dfs(B) dfs(B) dfs(B) B 추가
dfs(A) dfs(A) dfs(A) dfs(A) dfs(A) dfs(A) dfs(A) A추가

lst = [D, C, B, A]
lst[::-1] = [A, B, C, D]
"""    
```
- https://leetcode.com/problems/reconstruct-itinerary/






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
### 2. 포함/불포함(모든 경우의 수를 트리로 표현할 때, 이진 트리 구조를 가지는 경우)
```
먼저 문제를 읽고 포함/불포함의 이진 트리로 그림이 그려지고, idx 가 20언저리라면 이 방식을 사용하자.

주어진 입력값 arr의 모든 인덱스를 탐색하면 종료되는 백트래킹 구조를 가진다.
dfs(idx, cnt, ..):
    if idx == len(arr):


가능한 모든 경우의 수를 트리 형태로 나타낼 때 현재 idx를 포함, 불포함 두 가지의 형태를 가지는 이진 트리 구조를 가지는 문제
N의 범위가 중요하다. 2^20은 대략적으로 백만이다.

```
[백준 1182번: 부분수열의 합](https://velog.io/@legowww/%EB%B0%B1%EC%A4%80-1182%EB%B2%88-%EB%B6%80%EB%B6%84%EC%88%98%EC%97%B4%EC%9D%98-%ED%95%A9)

[백준 1759번: 암호 만들기](https://velog.io/@legowww/%EB%B0%B1%EC%A4%80-1759%EB%B2%88-%EC%95%94%ED%98%B8-%EB%A7%8C%EB%93%A4%EA%B8%B0)

[백준 1941번: 소문난 칠공주](https://velog.io/@legowww/%EB%B0%B1%EC%A4%80-1941%EB%B2%88-%EC%86%8C%EB%AC%B8%EB%82%9C-%EC%B9%A0%EA%B3%B5%EC%A3%BC)


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

---
### 6. 순열
```python
import sys
from collections import defaultdict


def dfs(cnt, path):
    if cnt == m and path:
        result.add(tuple(path))
        return

    for i in range(len(arr)):
        if not visited[i]:
            visited[i] = True
            dfs(cnt+1, path + [arr[i]])
            visited[i] = False

result = set()
n, m = map(int, sys.stdin.readline().rstrip().split())
arr = list(map(int, sys.stdin.readline().rstrip().split()))
visited = defaultdict(bool)

dfs(0, [])
for i in sorted(result):
    print(*i)
```
[[백준 15663번] N과 M(9)](https://velog.io/@legowww/%EB%B0%B1%EC%A5%B0-15663%EB%B2%88-N%EA%B3%BC-M9)

---
### 7. 조합
```python
import sys
from collections import defaultdict


def dfs(idx, path):
    if len(path) == 6:
        print(*path)
        return

    for i in range(idx, len(arr)):
        dfs(i+1, path + [arr[i]])

while True:
    k, *arr = list(map(int, sys.stdin.readline().rstrip().split()))
    visited = defaultdict(bool)
    if k == 0:
        break

    dfs(0, [])
    print()
```
[6603번: 로또](https://www.acmicpc.net/problem/6603)

#### 중복 조합
```python
class Solution:
    def combinationSum(self, candidates: List[int], target: int) -> List[List[int]]:
        def dfs(idx, path, S):
            if S > target:
                return
            
            if S == target:
                result.append(path)
                return 

            for i in range(idx, len(candidates)):
                dfs(i, path + [candidates[i]], S+candidates[i]) 


        result = []
        dfs(0, [], 0)

        return result

1. 
조합 문제이지만 dfs(i+1...) 이 아닌 dfs(i...) 방식이다.
중복 조합이기 때문에 현재 i 를 계속하여 사용할 수 있기 때문이다.

2.
c = [2, 3, 5]
for i in range(idx, len(candidates))

i=0 -> [2, 3, 5] 사용하여 만들 수 있는 모든 경우
i=1 -> [3, 5] 사용하여 만들 수 있는 모든 경우
for 문에서 매번 i=0부터 시작할 필요 없이 idx 에서 시작해도 모든 경우를 탐색할 수 있다.
```
- https://leetcode.com/problems/combination-sum/submissions/

---
### 8. 백트래킹(체스판)
```
/ 대각선: 같은 대각선안에 모든 (i,j) 좌표값들은 i+j 값이 같다.
\ 대각선: 같은 대각선안에 모든 (i,j) 좌표값들은 i-j 값이 같다.

대각선에 해당하는 룩업테이블을 참조하여 해당 대각선 사용 여부를 판단하는 문제이다.
```
- [1799번: 비숍](https://www.acmicpc.net/problem/1799)
- [9663번: N-QUEEN](https://www.acmicpc.net/problem/9663)
- https://www.youtube.com/watch?v=Op0dtMMyeyg

### 9. 2차원 좌표에 N개의 사물을 설치하는 문제

```
1. 조건이 작으면 놓을 수 있는 장소를 모두 확인한 후, combination으로 각 케이스 수 구하기
    예시) 연구소: https://www.acmicpc.net/problem/14502


2.
    예시2) 사다리조작: https://www.acmicpc.net/problem/15684

	for i in range(n*h):
	    q, r = divmod(i, n)
	    print(i, q, r) 

	- n = 열
	- h = 행
	- n*h = 열*행 = 총 원소 개수


	n=5, h=6	
	[1, 0, 0, 0, 0]
	[0, 0, 1, 0, 0]
	[0, 1, 0, 0, 0]
	[0, 0, 0, 0, 0]
	[1, 0, 0, 1, 0]
	[0, 0, 0, 0, 0]

	0 1 2 3 4 
	5 6 7 8 9

	graph[0][4] = 0, 4 = divmod(4, 5) = 4
	graph[1][4] = 1, 4 = divmod(9, 5) = 9
```
