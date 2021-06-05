package hw1;

import java.io.IOException;
import java.util.Arrays;

public class ArrayListTest {
	public static void main(String[] args) throws IOException {
//		hw1.ArrayList arrayList = new ArrayList();
//		System.out.println(arrayList);
//		arrayList.addAtIndex(0, "Jiwon");
//		arrayList.addAtIndex(1, "Tina");
//		arrayList.addAtIndex(2, "Sewon");
//
//		System.out.println(arrayList.getBackingArray());
//		System.out.println(arrayList.get(0));
//		System.out.println(arrayList.get(1));
//		System.out.println(arrayList.get(2));
//		
//		arrayList.removeFromFront();
//		System.out.println(arrayList.get(0));
		String s1 = new String("MINOS LABS");
		String s2 = new String("MINOS LABS ");
		
		if (s1 == s2) {
			System.out.print("a");
		} else { 
			System.out.println("b");
		}
		int x = 2;
		int y = 12;
		int z = 10;

		System.out.println(++z + y - y + z + x++);
		
		String[] words = { "Java", "React", "Angular", "Vue"}; 
		Arrays.sort(words);
		System.out.println(words[1]);
	}
}
