package week02.boj_1620;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class CJW {
    public static void main(String[] args) throws IOException {
        Map<String, Integer> data = new HashMap<>();
        Map<Integer, String> reverse_data = new HashMap<>();
        int idx = 1;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int vocab_size, command;
        vocab_size = Integer.parseInt(st.nextToken());
        command = Integer.parseInt(st.nextToken());
        for (int i = 0; i < vocab_size; i++) {
            String key = br.readLine();
            data.put(key, idx);
            reverse_data.put(idx++, key);
        }

        for (int i = 0; i < command; i++) {
            String cmd = br.readLine();
            if (cmd.matches("-?\\d+")) {
                System.out.println(reverse_data.get(Integer.parseInt(cmd)));
            }else{
                System.out.println(data.get(cmd));
            }
        }
    }
}
