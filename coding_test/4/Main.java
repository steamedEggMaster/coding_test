import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static int N;
    public static int L;
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        
        Integer[] integers = new Integer[N];

        String[] strs = br.readLine().split(" ");
        for(int i = 0; i < N; i++) {
            integers[i] = Integer.valueOf(strs[i]);
        }

        Arrays.sort(integers);

        double diff;
        double value = integers[0] - 0.5;
        int count = 0;
        int answer = 0;
        for(int i = 1; i < integers.length; i++) {
            diff = (double) integers[i] - value;
            count++;
            if(diff >= L) {
                value = integers[i];
                count = 0;
                answer++;
                if(i == integers.length - 1) {
                    count++;
                }
            }
        }

        if(count != 0) {
            answer++;
        }

        bw.write(String.valueOf(answer));

        br.close();
        bw.close();
    }
}

// 같은게 반복될 수 있ㅣ 때ㄴㅔ 길이로 해야함