import java.io.*;
import java.util.*;

public class Main {
    public static int N, M, H;
    public static boolean[][] ground;
    public static boolean[] visited;
    public static int answer = 4;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());

        ground = new boolean[H + 1][N + 1];
        visited = new boolean[N + 1];

        for(int m = 0; m < M; m++) {
            String[] str = br.readLine().split(" ");
            int y = Integer.parseInt(str[0]);
            int x = Integer.parseInt(str[1]);

            ground[y][x] = true;
            // 왜 x + 1부분은 true를 안하는가?
            // 이동하는 경우는 해당 y를 기준으로 true면 움직인 후,
            // x는 다음으로 넘어가고, y도 다음으로 넘어가기에, 신경쓰지 않아도 관계가 성립함
                // 근데 x + 1부분을 true설정하면,
                // 이중 정보가 생긴다고함...
                // 무조건 1군데에서 true로 해야하는게 원칙!
        }

        backTracking(0, 1, 1);
        
        bw.write(String.valueOf(answer == 4 ? -1 : answer));
        br.close();
        bw.close();
    }

    // 1. 현재 사다리로 i -> i되는지 확인하는 함수
    // 모든 i번째에서 확인해야함
    public static boolean isValid() {
        for(int n = 1; n <= N; n++) {
            int pos = n;
            for(int h = 1; h <= H; h++) {
                if(ground[h][pos]) { // 오른쪽으로 이동
                    pos++;
                } else if (pos > 1 && ground[h][pos - 1]) {
                    // 왼쪽으로 이동
                    pos--;
                }
            }
            if(pos != n) return false;
        }
        return true;
    }

    // 2. 백트레킹 + 가지치기
    public static void backTracking(int depth, int y, int x) {
        if(depth >= answer) return;

        if(isValid()) {
            answer = depth;
            return;
        }
        // 3개일때, isValid()가 실패하면
        if(depth == 3) return;
        // 이후 더 추가가 될게 뻔하니
        // 3이면 그냥 돌아가서 다른거 찾기

        for (int i = y; i <= H; i++) {
            for (int j = 1; j < N; j++) {
                if (ground[i][j] || ground[i][j - 1] || ground[i][j + 1]) continue;

                ground[i][j] = true;
                backTracking(depth + 1, i, j);
                ground[i][j] = false;
            }
        }
    }
}

// N : 세로 선 개수
// M : 가로 선 개수
// H : 세로 선 마다 가로 선을 놓을 수 있는 위치의 개수
// M개의 줄에는 가로선의 정보
    // a b 로 나타내며, b번과 b+1번 세로 선을 a번 점선 위치에서 연결했다는 의미
// 구해야 하는 것 : i번이 i번으로 갈 수 있도록 추가해야하는 선의 개수

// 같은 라인에 가로선이 짝수개여야함
// 처음 다른 줄로 넘어가고 동일한 라인의 다음 줄이 시작될때, 해당 라인에 있어야 함
// 가로선은 연속하거나 접하면 안됨

// 3보다 크면 -1
// 될 수 없는 경우에도 -1

