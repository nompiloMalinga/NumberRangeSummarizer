import numberrangesummarizer.DelimitedList;
import org.junit.jupiter.api.Test;


import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class DelimitedListTests {

    @Test
    //testing if input has duplicates the return value has no duplicates
    public void testCollectWithDuplicates() {
        DelimitedList delimitedList = new DelimitedList();
        List<Integer> TestInput = new ArrayList<>(delimitedList.collect("1,3,4,4,5,5,6,7"));
        List<Integer> expected = Arrays.asList(1,3,4,5,6,7);
        assertEquals(expected, TestInput);
    }

    @Test
    //testing if null input returns empty set
    public void testCollectWithNullInput() {
        DelimitedList delimitedList = new DelimitedList();
        Collection<Integer> result = delimitedList.collect(null);
        assertEquals(Collections.emptySet(), result);
    }

    @Test
    //test if empty string returns empty set
    public void testCollectWithEmptyInput() {
        DelimitedList delimitedList = new DelimitedList();
        Collection<Integer> result = delimitedList.collect("");
        assertEquals(Collections.emptySet(), result);
    }



    @Test
    //testing that string input containing non-numeric characters,
    // throws RuntimeException .
    public void testCollectWithInvalidInput() {
        DelimitedList delimitedList = new DelimitedList();
        assertThrows(RuntimeException.class, () -> {
            delimitedList.collect("1,2,3,4,a");
        });
    }

    @Test
    //tests if an exception is thrown if input collection is empty
    public void testSummarizeCollectionWithEmptyInput() {
        DelimitedList delimitedList = new DelimitedList();
        Collection<Integer> result =  Collections.emptyList();
        assertThrows(IndexOutOfBoundsException.class, () -> {
            delimitedList.summarizeCollection(result);
        });
    }

    @Test
    //tests the case where the input collection contains only a single number,
    // which should result in the output string being that number alone,
    // without any ranges
    public void testSummarizeCollectionWithSingleNumber() {
        DelimitedList delimitedList = new DelimitedList();
        String result = delimitedList.summarizeCollection(Collections.singleton(1));
        assertEquals("1", result);
    }



    @Test
//test that  output string will contain the
//summarized version of the input collection in ascending order.
    public void testSummarizeCollection() {
        DelimitedList summarizer = new DelimitedList();
        Collection<Integer> input = summarizer. collect("1,3,6,7,8,12,13,14,15,21,22,23,24,31");
        String expected = "1,3,6-8,12-15,21-24,31";
        String actual = summarizer.summarizeCollection(input);
        assertEquals(expected, actual);
    }
    @Test
   //test that even if input contains negatives output string will contain the
  //summarized version of the input collection in ascending order.
    public void testSummarizeCollectionWithNegatives() {
        DelimitedList summarizer = new DelimitedList();
        Collection<Integer> input = summarizer. collect("-3,1,5,-8,10");
        String expected = "1,3,5,8,10";
        String actual = summarizer.summarizeCollection(input);
        assertEquals(expected, actual);
    }
    @Test
    //test that even if input contains negatives output string will contain the
    //summarized version of the input collection in ascending order with correct range.
    public void testSummarizeCollectionWithNegativesForRange() {
        DelimitedList summarizer = new DelimitedList();
        Collection<Integer> input = summarizer. collect("-3,-1,-2,8,10");
        String expected = "1-3,8,10";
        String actual = summarizer.summarizeCollection(input);
        assertEquals(expected, actual);
    }

}
