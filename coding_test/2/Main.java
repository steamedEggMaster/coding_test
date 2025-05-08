// Q3.
// 1~N까지의 카드를 무작위로 나열하고, N ( N - 1 )  / 2 만큼의 힌트를 준다. 힌트는 a b 형태로 주는데, 이는 a번째 카드가 b번째 카드보다 크다는 것을 의미한다. 이때, 첫번째 카드와 마지막 카드의 크기를 비교하기 위해서는 몇번째 힌트까지 필요한가

// 첫번째 줄에는 N이 주어지고, 그 이후로는 힌트 a b가 주어진다.

// 예제 :

// 3
// 1 2
// 1 3
// 3 2


// 답 : 2

// 예제 : 

// 4
// 2 1
// 1 3
// 2 3
// 2 4
// 4 3
// 4 1


// 답 : 6

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main {
    public static int N;
    public static int A;
    public static int B;
    public static int[][] board;
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());

        int index = (N * (N - 1)) / 2;

        board = new int[index][2];

        for(int i = 0; i < index; i++) {
            String[] str = br.readLine().split(" ");
            for(int j = 0; j < 2; j++) {
                board[i][j] = Integer.parseInt(str[j]);
            }
        }

        LinkedHashMap<Integer, LinkedList<Integer>> map = new LinkedHashMap<>();

        for(int i = 1; i <= N; i++) {
            map.put(i, new LinkedList<>());
        }

        int answer = -1;
        for(int i = 0; i < index; i++) {
            int a = board[i][0];
            int b = board[i][1];

            map.get(b).add(a);

            if(map.get(N).contains(a)) {
                answer = i + 1;
                break;
            }
        }

        bw.write(String.valueOf(answer));

        br.close();
        bw.close();
    }
}