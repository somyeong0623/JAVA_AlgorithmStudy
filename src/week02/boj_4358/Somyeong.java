package week02.boj_4358;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;

//4358. 생태학 
/* 풀이 
 * <Strign, Integer>형 hashmap에 <나무이름, 수> 를 저장해놓는다. 
 * 입력이 있는동안 나무의 <이름,수> 정보를 hashmap에 put 한다(처음 등장한 나무의 경우).
 * 이때 이미 저장된 나무면 해당 key를 통해 value값을 ++하여 put해준다 (key는 중복되지 않으므로 해당 key에 해당하는 value값만 업데이트 된다)
 * 전체 나무의수를 count변수로 기록하고 각 '종'이 차지하는 비율을 나타낼때 이용한다. 
 * 
 */
public class Somyeong {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		HashMap<String, Integer> hashmap = new HashMap<>();
		
		int count=0;
		String line;
		while(true) {
			line=br.readLine();
			if(line==null || line.length()==0) //line.length()==0도 해줘야 종료된다. 
				break;
			count++;
			if(hashmap.containsKey(line)) { //이미 종이 있는경우 value+1하여 hashmap에 put하기 
				hashmap.put(line,hashmap.get(line)+1);
			}else { //종이 없는 경우 value를 1로 지정하여  put하기 
				hashmap.put(line, 1);
			}
			
		}
		
		Object[] mapkey= hashmap.keySet().toArray(); //keySey()함수를 통해 map에 저장되어있는 키 들을 Object배열에 반환받기 
		Arrays.sort(mapkey);
		
		for(Object key : mapkey) {
			System.out.print(key+" "); // 각 종의 이름 
			System.out.println(String.format("%.4f", (double)hashmap.get(key)/(double)count*100)); //각 종이 차지하는 비율 
			
		}
	}
}
