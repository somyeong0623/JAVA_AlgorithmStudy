import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Objects;
import java.util.Queue;

public class Main {
    private static int N;
    private static int max = Integer.MIN_VALUE;
    private static char[] exp;
    private static boolean[] ret;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        exp = br.readLine().toCharArray();
        if (N == 1) {
            System.out.println(exp[0]);
            return;
        }
        ret = new boolean[N / 2];
        search(0);
        System.out.println(max);
    }

    private static void search(int idx) {
        if (idx == N / 2) {
            getResult();
            return;
        }
        search(idx + 1);
        if (idx == 0 || !ret[idx - 1]) {
            ret[idx] = true;
            search(idx + 1);
            ret[idx] = false;
        }
    }

    private static void getResult() {
        Queue<String> newExp = calculateBrackets();
        Queue<String> simpleExp = calculateMultiply(newExp);
        max = Math.max(max, calculateSimple(simpleExp));
    }

    private static Queue<String> calculateBrackets() {
        Queue<String> newExp = new ArrayDeque<>();
        for (int i = 0; i < ret.length; i++) {
            int idx = 2 * i + 1;
            if (ret[i]) {
                newExp.add(calculate(exp[idx - 1] - '0', exp[idx + 1] - '0', exp[idx]) + "");
            } else {
                if (i == 0 || !ret[i - 1]) newExp.add(String.valueOf(exp[idx - 1]));
                newExp.add(String.valueOf(exp[idx]));
                if (i == ret.length - 1) newExp.add(String.valueOf(exp[idx + 1]));
            }
        }
        return newExp;
    }

    private static Queue<String> calculateMultiply(Queue<String> oldExp) {
        Queue<String> newExp = new ArrayDeque<>();
        int left = Integer.parseInt(Objects.requireNonNull(oldExp.poll()));
        while (!oldExp.isEmpty()) {
            char op = oldExp.poll().charAt(0);
            if (op == '*') {
                left *= Integer.parseInt(Objects.requireNonNull(oldExp.poll()));
            } else {
                newExp.add(left + "");
                newExp.add(String.valueOf(op));
                left = Integer.parseInt(Objects.requireNonNull(oldExp.poll()));
            }
        }
        newExp.add(left + "");
        return newExp;
    }

    private static int calculateSimple(Queue<String> simpleExp) {
        int res = Integer.parseInt(Objects.requireNonNull(simpleExp.poll()));
        while (!simpleExp.isEmpty()) {
            char op = simpleExp.poll().charAt(0);
            int right = Integer.parseInt(Objects.requireNonNull(simpleExp.poll()));
            res = calculate(res, right, op);
        }
        return res;
    }

    private static int calculate(int left, int right, char op) {
        int res = 0;
        switch (op) {
            case '+':
                res = left + right;
                break;
            case '-':
                res = left - right;
                break;
            case '*':
                res = left * right;
                break;
        }
        return res;
    }
}