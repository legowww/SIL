# DFS

### 1-A. 하나로 묶인 집합의 수와 집합별 넓이 구하기
[백준 1926번](https://www.acmicpc.net/problem/1926)
```python
import sys

def dfs(x, y):
    global width
    if not (0 <= x < n and 0 <= y < m):
        return

    if graph[x][y] == 1:
        graph[x][y] = 'x'
        width += 1
        dfs(x-1, y)
        dfs(x+1, y)
        dfs(x, y-1)
        dfs(x, y+1)

sys.setrecursionlimit(500000)
n, m = map(int, sys.stdin.readline().rstrip().split())
graph = [list(map(int, sys.stdin.readline().rstrip().split())) for _ in range(n)]
cnt = 0
answer = 0
width = 0

for i in range(n):
    for j in range(m):
        if graph[i][j] == 1:
            dfs(i, j)
            answer = max(answer, width)
            cnt += 1
            width = 0
print(cnt)
print(answer)
```
