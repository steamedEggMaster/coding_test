import java.io.*;
import java.util.*;

public class Main {
    public static int N;
    public static boolean[][] ground = new boolean[101][101];
    public static int[] dx = {1, 0, -1, 0};
    public static int[] dy = {0, -1, 0, 1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());

        for(int n = 0; n < N; n++) {
            String[] str = br.readLine().split(" ");
            int x = Integer.parseInt(str[0]);
            int y = Integer.parseInt(str[1]);
            int d = Integer.parseInt(str[2]);
            int g = Integer.parseInt(str[3]);

            draw(x, y, d, g);
        }

        int answer = 0;
        for(int i = 0; i < 100; i++) {
            for(int k = 0; k < 100; k++) {
                if(ground[i][k] && ground[i][k + 1] && ground[i + 1][k] && ground[i + 1][k + 1]) {
                    answer++;
                }
            }
        }
        
        bw.write(String.valueOf(answer));
        br.close();
        bw.close();
    }

    public static void draw(int x, int y, int d, int g) {
        List<Integer> directions = new ArrayList<>();
        directions.add(d);

        for(int i = 0; i < g; i++) {
            for(int j = directions.size() - 1; j >= 0; j--) {
                int nextDir = (directions.get(j) + 1) % 4;
                directions.add(nextDir);
            }
        }

        ground[y][x] = true;
        for(int dir : directions) {
            x += dx[dir];
            y += dy[dir];
            if(x >= 0 && x <= 100 && y >= 0 && y <= 100) {
                ground[y][x] = true;
            }
        }
    }   
}

// 시작점
// 시작 방향
// 세대

// 크기 100 x 100
// 구해야 하는 것 : 크기가 1 x 1인 정사각형의 네 꼭짓점이 모두 드래곤 커브의 일부인 정사각형의 개수

// x, y는 드래곤 커브의 시작점
// d는 시작 방향
    // 방향 : 0, 1, 2, 3
    // 0 : x 증가
    // 1 : y 감소
    // 2 : x 감소
    // 3 : y 증가
// g는 세대

// 3 3 0 1
// 4 2 1 3
// 4 2 2 1

// 그나까
// 0 세대의 방향 값 0
// => 1세대의 방향 값 1
// => 2세대의 방향 값 2, 1 (2번)
    // 1세대, 0세대 순으로 순회하는데, 90도 꺾이기에
    // 1. [0, 1]
    // 2. [1, 0]
    // 3. [2, 1]
    // 4. [0, 1, 2, 1] <- 최종 2세대

// 