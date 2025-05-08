import java.io.*;
import java.util.*;

public class Main {
    public static int N;
    public static int[][] iList;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());

        iList = new int[N][2];

        for(int n = 0; n < N; n++) {
            String[] str = br.readLine().split(" ");
            int startTime = Integer.parseInt(str[0]);
            int endTime = Integer.parseInt(str[1]);

            iList[n] = new int[]{startTime, endTime};
        }

        Arrays.sort(iList, (a, b) -> {
            if(a[0] == b[0]) {
                return a[1] - b[1];
            }
            return a[0] - b[0];
        });
        
        //printList();
        // int count = 0;
        // for(int i = 0; i < iList.length; i++) {
        //     if(iList[i] == null) continue;
        //     int[] node = iList[i];
        //     int endTime = node[1];
        //     iList[i] = null;
        //     count++;
        //     for(int k = 0; k < iList.length; k++) {
        //         if(i == k || iList[k] == null) continue;
        //         int[] nextNode = iList[k];
        //         int startTime = nextNode[0];
        //         if(endTime <= startTime) {
        //             endTime = nextNode[1];
        //             iList[k] = null;
        //         }
        //     }
        // }

        // bw.write((String.valueOf(count)));

        // bw.close();
        // br.close();

        Arrays.sort(iList, (a, b) -> {
            if (a[1] == b[1]) return a[0] - b[0]; // 종료시간 같으면 시작시간 오름차순
            return a[1] - b[1];
        });

        int count = 0;
        int lastEnd = 0;

        for (int i = 0; i < N; i++) {
            if (iList[i][0] >= lastEnd) {
                lastEnd = iList[i][1];
                count++;
            }
        }

        System.out.println(count);
        br.close();
    }

    public static void printList() {
        for(int i = 0; i < iList.length; i++) {
            System.out.println(Arrays.toString(iList[i]));
        }
    }
}

// 동시에 두 개 이상의 회의 진행 불가
// 회의 한번 시작되면 중간 멈춤 X
// 한 회의 끝남과 동시에 시작 가능