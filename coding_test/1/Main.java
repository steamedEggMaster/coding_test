import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

public class Main {
    public static int N, M;
    public static String[][] board;
    public static String[][] boardCloneVersion;
    public static Queue<int[]> q = new LinkedList<>();
    public static boolean[][] visited;
    public static int count;
    public static int minCount = 500000;

    public static String[] wString = {"W", "B", "W", "B", "W", "B", "W", "B"};
    public static String[] bString = {"B", "W", "B", "W", "B", "W", "B", "W"};

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        board = new String[N][M];

        for(int n = 0; n < N; n++) {
            board[n] = br.readLine().split("");
        }

        // 0, 0 시작
        // n, m 시작
        // n, 0 시작
        // 0, m 시작
        for(int n = 0; n + 8 <= N; n++) {
            for(int m = 0; m + 8 <= M; m++) {
                count = 0;
                // 시작이 B인 경우
                if(board[n][m].equals("B")) {
                    zCzCountB(n, m);
                }

                // 시작이 W인 경우
                if(board[n][m].equals("W")) {
                    zCzCountW(n, m);
                }

                minCount = Math.min(minCount, count);
            }
        }

        for(int n = N - 1; n - 8 >= 0; n--) {
            for(int m = M - 1; m - 8 >= 0; m--) {
                count = 0;
                // 시작이 B인 경우
                if(board[n][m].equals("B")) {
                    nCmCountB(n, m);
                }

                // 시작이 W인 경우
                if(board[n][m].equals("W")) {
                    nCmCountW(n, m);
                }

                minCount = Math.min(minCount, count);
            }
        }

        for(int n = N - 1; n - 8 >= 0; n--) {
            for(int m = 0; m + 8 <= M; m++) {
                count = 0;
                // 시작이 B인 경우
                if(board[n][m].equals("B")) {
                    nCzCountB(n, m);
                }

                // 시작이 W인 경우
                if(board[n][m].equals("W")) {
                    nCzCountW(n, m);
                }

                minCount = Math.min(minCount, count);
            }
        }

        for(int n = 0; n + 8 <= N; n++) {
            for(int m = M - 1; m - 8 >= 0; m--) {
                count = 0;
                // 시작이 B인 경우
                if(board[n][m].equals("B")) {
                    zCmCountB(n, m);
                }

                // 시작이 W인 경우
                if(board[n][m].equals("W")) {
                    zCmCountW(n, m);
                }

                minCount = Math.min(minCount, count);
            }
        }
        
        bw.write(String.valueOf(minCount));

        br.close();
        bw.close();
    }

    public static void zCzCountB(int startY, int startX) {
        for(int i = startY; i < startY + 8; i++) {
            for(int x = 0; x < 8; x++) {
                if(i % 2 == 0) {
                    if(!bString[x].equals(board[i][x + startX])) {
                        count++;
                    }
                } else {
                    if(!wString[x].equals(board[i][x + startX])) {
                        count++;
                    }
                }
            }
        }
    }

    public static void zCzCountW(int startY, int startX) {
        for(int i = startY; i < startY + 8; i++) {
            for(int x = 0; x < 8; x++) {
                if(i % 2 == 0) {
                    if(!wString[x].equals(board[i][x + startX])) {
                        count++;
                    }
                } else {
                    if(!bString[x].equals(board[i][x + startX])) {
                        count++;
                    }
                }
            }
        }
    }

    public static void nCmCountB(int startY, int startX) {
        for(int i = startY; i > startY - 8; i--) {
            for(int x = 7; x > -1; x--) {
                if(i % 2 == 0) {
                    if(!bString[x].equals(board[i][startX + x - 7])) {
                        count++;
                    }
                } else {
                    if(!wString[x].equals(board[i][startX + x - 7])) {
                        count++;
                    }
                }
            }
        }
    }

    public static void nCmCountW(int startY, int startX) {
        for(int i = startY; i > startY - 8; i--) {
            for(int x = 7; x > -1; x--) {
                if(i % 2 == 0) {
                    if(!wString[x].equals(board[i][startX + x - 7])) {
                        count++;
                    }
                } else {
                    if(!bString[x].equals(board[i][startX + x - 7])) {
                        count++;
                    }
                }
            }
        }
    }

    public static void nCzCountB(int startY, int startX) {
        for(int i = startY; i > startY - 8; i--) {
            for(int x = 0; x < 8; x++) {
                if(i % 2 == 0) {
                    if(!bString[x].equals(board[i][x + startX])) {
                        count++;
                    }
                } else {
                    if(!wString[x].equals(board[i][x + startX])) {
                        count++;
                    }
                }
            }
        }
    }

    public static void nCzCountW(int startY, int startX) {
        for(int i = startY; i > startY - 8; i--) {
            for(int x = 0; x < 8; x++) {
                if(i % 2 == 0) {
                    if(!wString[x].equals(board[i][x + startX])) {
                        count++;
                    }
                } else {
                    if(!bString[x].equals(board[i][x + startX])) {
                        count++;
                    }
                }
            }
        }
    }

    public static void zCmCountB(int startY, int startX) {
        for(int i = startY; i < startY + 8; i++) {
            for(int x = 7; x > -1; x--) {
                if(i % 2 == 0) {
                    if(!bString[x].equals(board[i][startX + x - 7])) {
                        count++;
                    }
                } else {
                    if(!wString[x].equals(board[i][startX + x - 7])) {
                        count++;
                    }
                }
            }
        }
    }

    public static void zCmCountW(int startY, int startX) {
        for(int i = startY; i < startY + 8; i++) {
            for(int x = 7; x > -1; x--) {
                if(i % 2 == 0) {
                    if(!wString[x].equals(board[i][startX + x - 7])) {
                        count++;
                    }
                } else {
                    if(!bString[x].equals(board[i][startX + x - 7])) {
                        count++;
                    }
                }
            }
        }
    }

    public static void printBoardClone() {
        for (int i = 0; i < N; i++) {
            System.out.println(Arrays.toString(boardCloneVersion[i]));
        }
        System.out.println();
    }
}
