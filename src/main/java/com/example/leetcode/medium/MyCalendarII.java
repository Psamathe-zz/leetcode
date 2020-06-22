package com.example.leetcode.medium;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;

/**
 * Implement a MyCalendarTwo class to store your events. A new event can be added if adding the event will not cause a triple booking.
 *
 * Your class will have one method, book(int start, int end). Formally, this represents a booking on the half open interval [start, end),
 * the range of real numbers x such that start <= x < end.
 *
 * A triple booking happens when three events have some non-empty intersection (ie., there is some time that is common to all 3 events.)
 *
 * For each call to the method MyCalendar.book, return true if the event can be added to the calendar successfully without causing a triple booking.
 * Otherwise, return false and do not add the event to the calendar.
 *
 * Your class will be called like this: MyCalendar cal = new MyCalendar(); MyCalendar.book(start, end)
 * Example 1:
 *
 * MyCalendar();
 * MyCalendar.book(10, 20); // returns true  10 - 19
 * MyCalendar.book(50, 60); // returns true
 * MyCalendar.book(10, 40); // returns true
 * MyCalendar.book(5, 15); // returns false
 * MyCalendar.book(5, 10); // returns true
 * MyCalendar.book(25, 55); // returns true
 * Explanation:
 * The first two events can be booked.  The third event can be double booked.
 * The fourth event (5, 15) can't be booked, because it would result in a triple booking.
 * The fifth event (5, 10) can be booked, as it does not use time 10 which is already double booked.
 * The sixth event (25, 55) can be booked, as the time in [25, 40) will be double booked with the third event;
 * the time [40, 50) will be single booked, and the time [50, 55) will be double booked with the second event.
 * ["MyCalendarTwo","book","book","book","book","book","book","book","book","book","book"]
 * [[],[24,40],[43,50],[27,43],[5,21],[30,40],[14,29],[3,19],[3,14],[25,39],[6,19]]
 */
public class MyCalendarII {

    /**
     * Your MyCalendarTwo object will be instantiated and called as such:
     * MyCalendarTwo obj = new MyCalendarTwo();
     * boolean param_1 = obj.book(start,end);
     */
    public static void main(String[] args) {
        MyCalendarII myCalendarII = new MyCalendarII();
        boolean b1 = myCalendarII.book(24,40);
        boolean b2= myCalendarII.book(43,50);
        boolean b3 = myCalendarII.book(27,43);
        boolean b4 = myCalendarII.book(5,21);
        boolean b5 = myCalendarII.book(30,40);
        boolean b6 = myCalendarII.book(14,29);
        boolean b7 = myCalendarII.book(3,19);
        boolean b8 = myCalendarII.book(3,14);
        boolean b9 = myCalendarII.book(25,39);
        boolean b10 = myCalendarII.book(6,19);
        System.out.println(b10);
    }

    Map<Range,Integer> myMap = new HashMap<>();
    public MyCalendarII() {

    }

    public boolean book(int start, int end) {
        boolean isReset = false;
        int doubleStart ;
        int doubleEnd ;
        int singleStart ;
        int singleEnd ;
        Map<Range,Integer> myMapTemp = new HashMap<>();
        end = end - 1;
        Iterator<Map.Entry<Range, Integer>> iterator = myMap.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<Range, Integer> entry = iterator.next();
            Range rangeInMap = entry.getKey();
            if(start <= rangeInMap.end && end >= rangeInMap.start && entry.getValue()!=-1){
                if(entry.getValue() == 2)
                    return false;
                else{
                    myMapTemp.put(rangeInMap,-1);
                    doubleStart = start>rangeInMap.start?start:rangeInMap.start;
                    doubleEnd = end<rangeInMap.end?end:rangeInMap.end;
                    singleStart = start<rangeInMap.start?start:rangeInMap.start;
                    singleEnd = end>rangeInMap.end?end:rangeInMap.end;
                    myMapTemp.put(new Range(doubleStart,doubleEnd),2);
                    if(singleStart<doubleStart)
                        myMapTemp.put(new Range(singleStart,doubleStart - 1),1);
                    if(doubleEnd<singleEnd)
                        myMapTemp.put(new Range(doubleEnd + 1,singleEnd),1);
                    isReset = true;
                }
            }
        }

        if(!isReset)
            myMap.put(new Range(start,end),1);
        else {
            myMap.putAll(myMapTemp);
        }
        return true;
    }

    public class Range{
        int start;
        int end;

        public Range(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Range range = (Range) o;
            return start == range.start &&
                    end == range.end;
        }

        @Override
        public int hashCode() {
            return Objects.hash(start, end);
        }
    }
}
