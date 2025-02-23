import com.workspace.multithreading.Company;
import com.workspace.multithreading.Consumer;
import com.workspace.multithreading.Producer;
import jdk.swing.interop.SwingInterOpUtils;

import java.util.*;

import static com.workspace.WorkUtility.mergeAlternatively;

public class Main {
    record Place(String name, int distance){
        @Override
        public String toString() {
            return String.format("%s (%d)",name,distance);
        }
    };
    public static void main(String[] args) {
//        Company company = new Company();
//        Producer p = new Producer(company);
//        Consumer c = new Consumer(company);
//        p.start();
//        c.start();
//        int num = 1;
//        num = ++num + 1+ ++num + num++ + ++num;// op- 14 how? -> 2+1+3+3+5
//        System.out.println(num);
//        num = num++ + ++num;
//        System.out.println(num);
//        num = ++num;
//        System.out.println(num);
//        num = num++;
//        System.out.println(num);
//        int num1 = 5;
//        System.out.println(num1++ == ++num);
//        int x = 5;
//
//        int y = 10;
//
//        int z = (x++ > 5 && y-- < 10) ? x-- : y;
//        int a = 3;
//
//        int b = 6;
//
//        int result = (~a & b) | (a & ~b);
//
//        System.out.println(result);
//        int i, j;
//
//        i = 100;
//
//        j = 300;
//
//        while(++i < --j);
//
//        System.out.println(i);
        List<Integer> arr = Arrays.asList(2,4,6,3,2);
        System.out.println(sumSubArrayAndReturnElementCount(arr));



    }

    private static int sumSubArrayAndReturnElementCount(List<Integer> arr) {
        //on2 order of o(n2) how we can convert it into o(n)
//        List<Integer> newArr = new ArrayList<>();
//        for(int i =0 ; i<arr.size(); i++) {
//            int sum = 0;
//            int j = i;
//            while(true){
//                sum += arr.get(j);
//                if(sum%2!=0){
//                   newArr.add(sum);
//                }
//                if(j+1<arr.size()){
//                    j++;
//                }else {
//                    break;
//                }
//            }
//        }
        //OR
//        int sum = 0;
//        int i=0;
//        int j = i;
//        while(true) {
//           sum+=arr.get(j);
//           if(sum%2!=0){
//               newArr.add(sum);
//           }
//           if(j+1<arr.size()) {
//               j++;
//           }else{
//               if(i+1<arr.size()) {
//                   i++;
//                   j = i;
//                   sum = 0;
//               } else {
//                   break;
//               }
//           }
//        }
//        return newArr.size();

        // below is prefix sum and parity tracking approach for o(n)
        /*
            why we initialize evenCount = 1
            Initially, the prefix sum before processing elements is 0 (even).
            We start by assuming we’ve already seen one even sum (the initial 0).
            When sum = 1 (odd), we immediately count it using evenCount = 1, ensuring subArrays starting at index 0 are counted.
         */
        int oddCount = 0, evenCount = 1, sum = 0, result = 0;

        for (int num : arr) {
            sum += num;
            if (sum % 2 != 0) {
                result += evenCount;  // Odd sum is formed using previous even sums
                oddCount++;
            } else {
                result += oddCount;   // Even sum is formed using previous odd sums
                evenCount++;
            }
        }
        return result;
    }




    private static void addPlace(LinkedList<Place> list, Place place) {

        if (list.contains(place)) {
            System.out.println("Found duplicate: " + place);
            return;
        }

        for (Place p : list) {
            if (p.name().equalsIgnoreCase(place.name())) {
                System.out.println("Found duplicate: " + place);
                return;
            }
        }

        int matchedIndex = 0;
        for (var listPlace: list) {
            if (place.distance() < listPlace.distance()) {
                list.add(matchedIndex, place);
                return;
            }

            matchedIndex++;
        }

        list.add(place);
    }

    public void bristleCone() {
        System.out.println("hello world");
        System.out.println(mergeAlternatively("f","beebaeca"));
// bristlecone question.
        String s1 = "abc";
        String s2 = s1;
        s1+="d";
//        s2=s1;
        String s3 = s2;
        System.out.println(s1);
        System.out.println(s2);
        System.out.println(s3);

        LinkedList<Place> placesToVisit = new LinkedList<>();

        Place adelaide = new Place("Adelaide", 1374);
        addPlace(placesToVisit, adelaide);
        addPlace(placesToVisit, new Place("adelaide", 1374));
        addPlace(placesToVisit, new Place("Brisbane", 917));
        addPlace(placesToVisit, new Place("Perth", 3923));
        addPlace(placesToVisit, new Place("Alice Springs", 2771));
        addPlace(placesToVisit, new Place("Darwin", 3972));
        addPlace(placesToVisit, new Place("Melbourne", 877));

        placesToVisit.addFirst(new Place("Sydney", 0));
        System.out.println(placesToVisit);
    }
}
