import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    private static int N, C;
    private static int [] houses;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        houses = new int[N];
        for (int i = 0; i < N; i++) {
            houses[i] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(houses);

        int low = 1;
        int high = houses[N - 1] - houses[0] + 1;
        while (high > low) {
            int mid = (high + low) / 2;
            if (search(mid) < C) high = mid;
            else low = mid + 1;
        }
        System.out.println(low - 1);
    }

    private static int search(int dist) {
        int count = 1;
        int last = houses[0];

        for (int i = 1; i < N; i++) {
            if (houses[i] - last < dist) continue;
            count++;
            last = houses[i];
        }
        return count;
    }
}