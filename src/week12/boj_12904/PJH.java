package Test;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class PJH {

	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		char[] origin = bf.readLine().toCharArray();
		char[] target = bf.readLine().toCharArray();
		while(origin.length != target.length) {
			if(target[target.length-1]=='B') {
				target = remove_B(target);
			}else {
				target = remove_A(target);
			}
		}
		if(check(origin,target)) {
			System.out.println(1);
		}else {
			System.out.println(0);
		}
	}
	static boolean check(char[] a,char[] b) {
		for(int i = 0 ; i < a.length;i++) {
			if(a[i]!=b[i]) {
				return false;
			}
		}
		return true;
	}

	static char[] remove_A(char[] target) {
		char[] result = new char[target.length-1];
		
		for(int i = 0 ; i < target.length-1;i++) {
			result[i] = target[i];
		}
		
		return result;
	}

	static char[] remove_B(char[] target) {
		char[] result = new char[target.length-1];
		for(int i = 0 ; i < target.length-1;i++) {
			result[i] = target[target.length-2-i];
		}
		return result;
	}
}
