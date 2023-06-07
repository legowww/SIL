# 0. 기타

### 1. `GCD`

[[프로그래머스] 숫자 카드 나누기 (velog.io)](https://velog.io/@legowww/%ED%94%84%EB%A1%9C%EA%B7%B8%EB%9E%98%EB%A8%B8%EC%8A%A4-%EC%88%AB%EC%9E%90-%EC%B9%B4%EB%93%9C-%EB%82%98%EB%88%84%EA%B8%B0)

```python
# GCD 함수는 자주 사용되므로 암기
def gcd(a,b):
    while b:
        a, b = b, a % b
	return a

# N개의 수에 대한 최대공약수를 구하기
# gcd(arrayA=[14, 35, 119]) => 7  
max_a = arrayA[0]
for i in range(1, length):
    max_a = gcd(max_a, arrayA[i])
```

# 1. Stack
괄호  `[` 으로 열고 `]` 닫는 문제에서 자주 사용되는 코드 형식
```python
# https://velog.io/@legowww/%EB%A6%AC%ED%8A%B8%EC%BD%94%EB%93%9C-394.-Decode-String
stack = []
for char in s:
    if char != ']':  # 닫는 괄호는 스택에 넣지 않는다.
        stack.append(char)
    else:
        # 로직 작성
	temp_str = ''
        while stack and stack[-1] != '[':  # 빈 리스트 검사와 마지막 참조 또한 자주 사용 
            temp_str += stack.pop()
	temp_str = temp_str[::-1]  # 스택이므로 역순으로 변환
	stack.pop()  # '[' 제거
"""
1. ['a', '[', 'b', 'c', 'd']
2. 현재 char == ']'
3. ['a'] 
""" 
```
괄호 문제에서 시작 인덱스를 저장하는 코드 형식

```python
# https://velog.io/@legowww/%EB%B0%B1%EC%A4%80-2800%EB%B2%88-%EA%B4%84%ED%98%B8-%EC%A0%9C%EA%B1%B0
stack = []
for idx, char in enumerate(exp):
    if char == '(':
        stack.append(idx)
    elif char == ')':
        start = stack.pop()
        end = idx
```

---

# 2. Sliding Window, Two Pointer
투 포인터 문제 구조
```python
start=0
end=N-1
while start < end:
    k = lst[end]+lst[start]
    ...
```
- [https://velog.io/@legowww/백준-2230번-수-고르기](https://velog.io/@legowww/백준-20366번-같이-눈사람-만들래)
```python
start=0
end=0
while start < N and end < N:
    k = lst[start]-lst[end]
    ...
```
- https://velog.io/@legowww/백준-2230번-수-고르기

```python
import sys

N, M = map(int, input().split())
lst = list(map(int, sys.stdin.readline().rstrip().split()))

ans = 0
SUM = 0
end = 0
for start in range(N):
    while SUM < M and end < N:
        SUM += lst[end]
        end += 1
    if SUM == M:
        ans += 1
    SUM -= lst[start]
print(ans)
```
- [수들의 합2](https://www.acmicpc.net/problem/2003https://www.acmicpc.net/problem/2003) 대표적인 투 포인터 문제이다.
start를 for문의 변수로 사용할 경우 한칸씩 움직이는 슬라이딩 윈도우를 구현하려면  while문을 사용하여 end를 이동시켜야 한다. 

- [[프로그래머스] 할인 행사 (velog.io)](https://velog.io/@legowww/%ED%94%84%EB%A1%9C%EA%B7%B8%EB%9E%98%EB%A8%B8%EC%8A%A4-%ED%95%A0%EC%9D%B8-%ED%96%89%EC%82%AC) 
슬라이딩 윈도우가 고정된 경우, 위의 문제처럼 0번째 인덱스에서 윈도우의 init 값을 설정하고 체크, 그리고 1번째 인덱스부터 윈도우를 슬라이딩 시킨다.

- [코딩테스트 연습 - 택배 배달과 수거하기 | 프로그래머스 스쿨 (programmers.co.kr)](https://school.programmers.co.kr/learn/courses/30/lessons/150369)
1. `i ≥ 0` 를 통해 인덱스 참조 에러 방지
2. `i -= 1` 를 통해 건너뛰고 싶은 원소들을 넘어가고 필요한 원소 앞으로 이동해 놓는다.
```python
while i >= 0 and deliveries[i] == 0:
        i -= 1
```

---

# 3. 원형 큐
[코딩테스트 연습 - 연속 부분 수열 합의 개수 | 프로그래머스 스쿨 (programmers.co.kr)](https://school.programmers.co.kr/learn/courses/30/lessons/131701)

1. 선형적으로 접근하기 위해 원형 큐로 사용될 리스트를 **`*2`** 하여 사용한다. 
    
    원형 큐를 고려한 이동 위치는  `현재 인덱스(=j)` 를 시작값으로 두고 `move(=i)` 만큼 더한 `j+i` 값을 사용한다.
    

```python
def solution(elements):
    partial = list()
    temp_el = elements*2
    print(temp_el)
    ****for i in range(1,len(elements)+1):
        for j in range(len(elements)):
            print((j, j+i), sum(temp_el[j:j+i]))
            partial.append(sum(temp_el[j:j+i]))

    answer = len(set(partial))
    return answer

print(solution([7, 9, 1, 1, 4]))
```

 2. 

움직인 후의 인덱스 = (`현재인덱스` + `move` ) % `len(list)`  또는 (`현재인덱스` + `move` -1 ) % `len(list)` 

이 두가지 중  print() 해보고 더 활용하기 쉬운 코드를 활용하자.

```python
def solution(elements):
    answer = set([sum(elements[:])])
    length = len(elements)
    for i in range(1, length):
        print(f'==길이={i}==')
        for j in range(length):
            if j+i <= length:
                answer.add(sum(elements[j:j+i]))
                print((j, j + i))
            else:
                idx = (j + i) % length
                answer.add(sum(elements[j:]) + sum(elements[:idx]))
                print((j, j + i), idx)
    return len(answer)

print(solution([7, 9, 1, 1, 4]))
```
# 4. 스왑
[달리기 경주](https://velog.io/@legowww/%ED%94%84%EB%A1%9C%EA%B7%B8%EB%9E%98%EB%A8%B8%EC%8A%A4-%EB%8B%AC%EB%A6%AC%EA%B8%B0-%EA%B2%BD%EC%A3%BC)
```python
from collections import defaultdict

def solution(players, callings):
    d = defaultdict(int)
    for i in range(len(players)):
        d[players[i]] = i
    
    for c in callings:
        prev, curr = d[c]-1, d[c]
        d[players[prev]] = curr
        d[players[curr]] = prev
        players[prev], players[curr] = players[curr], players[prev]
    
    return players
```
