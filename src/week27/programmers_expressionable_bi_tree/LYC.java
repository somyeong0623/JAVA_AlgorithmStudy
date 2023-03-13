import java.util.*;

class Solution {
    private boolean isRight;
    
    public int[] solution(long[] numbers) {
        int[] answer = new int[numbers.length];
        for (int i = 0; i < numbers.length; i++) {
            long number = numbers[i];
            char[] binary = Long.toString(number, 2).toCharArray();
            isRight = true;
            canRepresentCompleteBinaryTree(binary);
            if (isRight) answer[i] = 1;
            else answer[i] = 0;
        }
        return answer;
    }
    
    private void canRepresentCompleteBinaryTree(char[] binary) {
        char[] newBinary = makeProperLength(binary);
        search(0, newBinary.length - 1, newBinary);
    }
    
    private char[] makeProperLength(char[] binary) {
        int len = binary.length;
        int length = 1;
        while (length < len) length = (length + 1) * 2 - 1;
        char[] newBinary = new char[length];
        Arrays.fill(newBinary, '0');
        for (int i = length - len; i < length; i++) {
            newBinary[i] = binary[i - length + len];
        }
        return newBinary;
    }
    
    private char search(int start, int end, char[] newBinary) {
        if (start == end) return newBinary[start];
        int mid = (start + end) / 2;
        if (newBinary[mid] == '0') {
            if (search(start, mid - 1, newBinary) == '0' && search(mid + 1, end, newBinary) == '0') {
            } else {
                isRight = false;
            }
        } 
        search(start, mid - 1, newBinary);
        search(mid + 1, end, newBinary);
        return newBinary[mid];
    }
}