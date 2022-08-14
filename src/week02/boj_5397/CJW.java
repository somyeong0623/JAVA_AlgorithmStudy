package week02.boj_5397;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class CJW {
    static class Node {
        public Node prev;
        public Character val;
        public Node next;

        public Node(Node prev, Character val, Node next) {
            this.prev = prev;
            this.val = val;
            this.next = next;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for (int tc = 0; tc < T; tc++) {
            char[] input = br.readLine().toCharArray();
            Node root = new Node(null, null, null);
            Node pres = root;

            for (int cnt = 0; cnt < input.length; cnt++) {
                char chr = input[cnt];

                switch (chr) {
                    case '<':
                        if (pres.prev != null)
                            pres = pres.prev;
                        break;
                    case '>':
                        if (pres.next != null)
                            pres = pres.next;
                        break;
                    case '-':
                        if (pres.prev != null) {
                            // 현재의 이전의 다음을 현재의 다음으로 링크
                            pres.prev.next = pres.next;
                            if (pres.next != null)
                                // 현재의 다음의 이전을 현재의 이전으로 링크.
                                pres.next.prev = pres.prev;
                            pres = pres.prev;
                        }
                        break;
                    default: {
                        // 만약 현재 원소의 다음이 있다면
                        if (pres.next != null) {
                            // 새로운 노드를 만들어서 이전, 값, 현재의 다음값과 연결시켜준다.
                            Node temp = new Node(pres, chr, pres.next);
                            // 현재의 다음의 이전은 새 노드를 링크하고
                            pres.next.prev = temp;
                            // 현재의 다음은 새로운 노드가 된다.
                            pres.next = temp;
                            // 그리고 현재는 새로운 노드로 변경된다.
                            pres = temp;
                        } else {
                            // 만약 현재 원소의 다음이 없다면 새로운 노드를 생성
                            Node temp = new Node(pres, chr, null);
                            // 현재의 다음은 새 노드가 되고
                            pres.next = temp;

                            // 현재를 새 노드로 링크한다.
                            pres = temp;
                        }
                    }
                }
            }
            StringBuilder sb = new StringBuilder();
            while (root.val == null)
                root = root.next;

            do {
                sb.append(root.val);
                root = root.next;
            } while (root != null);
            System.out.println(sb);
        }
    }
}
