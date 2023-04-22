package numberrangesummarizer;

import java.util.Collection;

public class Main {
    public static void main(String[] args) {

        DelimitedList delimitedList = new DelimitedList();
        String input = "1,3,6,7,8,12,13,14,15,21,22,23,24,31";
        Collection<Integer> numbers = delimitedList.collect(input);
        String summary = delimitedList.summarizeCollection(numbers);

        System.out.println("Input: " + input);
        System.out.println("Summary: " + summary);
    }
}