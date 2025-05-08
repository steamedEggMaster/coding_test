import java.io.*;
import java.util.*;
public class Main {
    public static int N, M;
    public static List<Position> cctvs = new ArrayList<>();

    // 시계 방향
    // 1. 이동 방향 정의
    public static int[] dy = {-1, 0, 1, 0};
    public static int[] dx = {0, 1, 0, -1};

    // 2. 각 CCTV 별 이동 방향 조합
    public static int[][][] directions = {
        {{0}}, // index 맞추기 용
        {{0}, {1}, {2}, {3}},
        {{0, 2}, {1, 3}},
        {{0, 1}, {0, 3}, {1, 2}, {2, 3}},
        {{0, 1, 3}, {0, 1, 2}, {1, 2, 3}, {0, 2, 3}},
        {{0, 1, 2, 3}}
    };
    
    public static int min = 64;
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        int[][] ground = new int[N][M];

        for(int i = 0; i < N; i++) {
            String[] str = br.readLine().split(" ");
            for(int k = 0; k < M; k++) {
                ground[i][k] = Integer.parseInt(str[k]);
                if (ground[i][k] > 0 && ground[i][k] < 6) {
                    cctvs.add(new Position(ground[i][k], i, k));
                }
            }
        }

        Collections.sort(cctvs, (a, b) -> Integer.valueOf(b.value).compareTo(a.value));
        
        dfs(ground, 0);
        // 1. 한 좌표 당, 그림을 그린다
        // 2. directions의 크기만큼 반복한다
        //    - 이때 각 배열은 DeepCopy 되어야 한다
        // 3. 각 DFS가 8번까지 도착하면, 0을 샌다
        // 4. 0이 max보다 작다 -> 뒤로 돌아가서 그 for문 2번째로 넘어간다

        bw.write(String.valueOf(min));

        br.close();
        bw.close();
    }

    public static void dfs(int[][] ground, int index) {
        // 끝까지 갔을 때 0개수 세고 비교하기
        if(index == cctvs.size()) {
            int count = zeroCount(ground);
            // printGround(ground);
            min = Math.min(count, min);
            return;
        }


        Position p = cctvs.get(index);
        int value = p.value;
        int y = p.y;
        int x = p.x;

        int[][] direction = directions[value];
        for(int d = 0; d < direction.length; d++) {
            // 1. 그리기 위한 ground 만들기
            int[][] cloneGround = new int[N][M];
            for(int k = 0; k < N; k++) {
                cloneGround[k] = ground[k].clone();
            }

            // 2. 복사된 ground 위에서 -1 처리하기
            int[] dir = direction[d];
            for(int k = 0; k < dir.length; k++) {
                int dirV = dir[k];
                watch(cloneGround, y + dy[dirV], x + dx[dirV], dirV);
            }

            // 3. 처리된 ground를 다름 dfs로 넘기기
            dfs(cloneGround, index + 1);
        }
        // 복사해놓은 ground에다가 -1 처리를 해야함
    }

    public static void watch(int[][] ground, int y, int x, int dirV) {
        if(y >= N || y < 0 || x >= M || x < 0) return;
        if(ground[y][x] == 6) return;
        if(ground[y][x] == 0) {
            ground[y][x] = -1;
        }

        watch(ground, y + dy[dirV], x + dx[dirV], dirV);
    }

    public static int zeroCount(int[][] ground) {
        int count = 0;
        for(int i = 0; i < N; i++) {
            for(int k = 0; k < M; k++) {
                if(ground[i][k] == 0) count++;
            }
        }
        return count;
    }

    public static void printGround(int[][] ground) {
        for(int i = 0; i < N; i++) {
            System.out.println(Arrays.toString(ground[i]));
        }

        System.out.println();
    }
}

class Position {
    int value;
    int y;
    int x;

    public Position(int value, int y, int x) {
        this.value = value;
        this.y = y;
        this.x = x;
    }
}
// 이상태에서 시간초과 발생함
// 안할 수 있는 부분을 제거하기
// => dfs 안의 for문을 넣은게 문제였음
//    왜지???
//    다시 돌아갈 필요가 없었나??
//    아 이미 내부에서 각 direction에 대한 반복을 진행하기에..?


// K개의 CCTV가 설치되어져 있음
// 5종류
// 벽 통과 불가 / CCTV 통과는 가능
// 해당 선 모두 관찰
// 감시 불가 영역 : 사각지대
// 회전 가능 : 90도
// 감시 원하는 방향이 가로 또는 세로 방향
// CCTV 최대 개수 : 8개
//      없을 수도 있음
// 공간 최대 크기 : 8 * 8
// -> 브루트포스 알고리즘 각?

// 1. 각 CCTV의 좌표를 구한다
// 2. CCTV 방식대로 하나씩 변경한다
// 3. 최대가 되는 것을 구한다

// 0 : 빈칸, 6 : 벽, 1~5 : CCTV 번호

// 구해야 하는 것 : 사각 지대의 최소 크기

// 겹친다는 것은, 일직선 상에 있다는 것
// 가는 길에, CCTV가 있다
// -> 해당 방향으로 설정하지 않기
// -> 최대한 겹치지 않게 하는 것이 중요!
// 중요도 : 5 -> 1번 순서

// 최대 일때만 다음으로 넘어가게끔 하자
// (백트래킹)
// 최대인 경우는, 5 -> 1번 순서로 갈때, 안겹치는 방법밖에없음
// 5번이 1직선 상에 있는 경우 제외하면 2번 겹칠 수가 없음
// 2번 겹치면 그만하기
// 1번은 1번 겹치면 그만하기

// #으로 바꾸는데, 바꾸는 과정에서 # 이 방향에 2번뜨면 그만하기
// 5번 제외