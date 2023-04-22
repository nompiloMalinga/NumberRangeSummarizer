package numberrangesummarizer;

import java.util.*;

public class DelimitedList implements NumberRangeSummarizer {

    /**
     * in this method I initialize an empty set(set doesn't allow duplicates),
     * I then check if the input string is null or empty if it is the method returns
     * an empty list to prevent the method from throwing a NullPointerException or trying
     * to perform operations on an empty list.
     * Then i take the input split it with a comma,take each input string and convert it
     * into an integer. if conversion process failed program throws an exception.
     * if everything has been successfully converted ,stores number in a list
     * and return the list.
     */
    @Override
    public Collection<Integer> collect(String input) {

        Set<Integer> ListOfNumbers = new HashSet<>();
        if (input == null || input.isEmpty()) {
            return ListOfNumbers;
        }
        String[] numberInput = input.split(",");
        for (String str : numberInput) {
            try {
                int number = Integer.parseInt(str);
                ListOfNumbers.add(number);
            } catch (NumberFormatException e) {
                throw new RuntimeException(e);
            }
        }

        return ListOfNumbers;
    }

    @Override
    public String summarizeCollection(Collection<Integer> input) {

        // Check if the input collection is empty
        if (input.isEmpty()) {
            throw new IndexOutOfBoundsException("Input collection is empty");
        }
        //creating a numberValues array that consists of input collection
        List<Integer> numberValues = new ArrayList<>(input);
        //replaces all negative numbers with their absolute values.
        numberValues.replaceAll(Math::abs);
        // sort values in ascending order
        Collections.sort(numberValues);
        //creating a StringBuilder to store summarized results
        StringBuilder results = new StringBuilder();
        // initialising 2 integers to the first element of sorted list
        int initialValue = numberValues.get(0);
        int end = initialValue;


        //loops through each element in the sorted list starting at index1
        for (int i = 1; i < numberValues.size(); i++) {
            //checks whether the next number in the sorted list is exactly one more than the current number
            if (numberValues.get(i) == end + 1) {
                //If it is, the end value is updated to the next number.
                //If the current number is not one more than the previous number,
                //then we know we have reached the end of a range of consecutive numbers.
                end = numberValues.get(i);
            } else {
                    /*Here I check if the initialValue is equal to the "end" variable.
                        If they are equal, that means there was only one number in the range,
                       so I add that number to the results
                      */
                if (initialValue == end) {
                    results.append(initialValue).append(",");
                } else {
                        /* If "initialValue" and "end" are not equal, that means there was
                         a range of consecutive numbers, so I add the range to the "results" StringBuilder
                         in the format "initialValue-end," followed by a comma.
                        */
                    results.append(initialValue).append("-").append(end).append(",");
                }
                    /* update the "initialValue" and "end" variables to the current number ,
                     so I can start looking for the next range of consecutive numbers.
                    */
                initialValue = end = numberValues.get(i);

            }

        }

            /*checks if the range of consecutive numbers is just a single number.
             If it is, I append only that number to the 'results' StringBuilder.
             If it is not just a single number, I append the range in the format
             'initialValue-end' to the 'results' StringBuilder.
            */
        if (initialValue == end) {
            results.append(initialValue);

        } else {
            results.append(initialValue).append("-").append(end);
        }


        return results.toString();//return summarized numbers as a string


    }
}
