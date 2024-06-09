import java.util.*;

//리스트 정렬
public class test {
    public static void main(String[] args) {
        List<Integer> testList_natural = new ArrayList<>();
        testList_natural.add(400);
        testList_natural.add(100);
        testList_natural.add(600);
        testList_natural.add(500);
        testList_natural.add(300);
        testList_natural.sort(Comparator.naturalOrder());
        System.out.println(testList_natural);

        List<Integer> testList_reverse = new ArrayList<>();
        testList_reverse.add(400);
        testList_reverse.add(100);
        testList_reverse.add(600);
        testList_reverse.add(500);
        testList_reverse.add(300);
        testList_reverse.sort(Comparator.reverseOrder());
        System.out.println(testList_reverse);
    }
}
