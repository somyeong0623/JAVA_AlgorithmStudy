import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    private static int removeIndex;
    private static ArrayList<Integer>[] tree;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        tree = new ArrayList[n];
        for (int i = 0; i < n; i++) tree[i] = new ArrayList<>();

        int root = -1;
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            int parent = Integer.parseInt(st.nextToken());
            if (parent == -1) {
                root = i;
                continue;
            }
            tree[parent].add(i);
        }
        removeIndex = Integer.parseInt(br.readLine());
        System.out.println(search(root));
    }

    private static int search(int cur) {
        if (cur == removeIndex) return 0;
        if (tree[cur].size() == 0) return 1;
        if (tree[cur].size() == 1 && tree[cur].get(0) == removeIndex) return 1;
        int ret = 0;
        for (Integer child : tree[cur]) {
            ret += search(child);
        }
        return ret;
    }
}