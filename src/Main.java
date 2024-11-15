import com.workspace.multithreading.Company;
import com.workspace.multithreading.Consumer;
import com.workspace.multithreading.Producer;

import java.util.LinkedList;
import java.util.ListIterator;

import static com.workspace.WorkUtility.mergeAlternatively;

public class Main {
    record Place(String name, int distance){
        @Override
        public String toString() {
            return String.format("%s (%d)",name,distance);
        }
    };
    public static void main(String[] args) {
        Company company = new Company();
        Producer p = new Producer(company);
        Consumer c = new Consumer(company);
        p.start();
        c.start();
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
