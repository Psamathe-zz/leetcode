package com.example.leetcode.medium;

/**
 * There are n flights, and they are labeled from 1 to n.
 *
 * We have a list of flight bookings.  The i-th booking bookings[i] = [i, j, k] means that we booked k seats from flights labeled i to j inclusive.
 *
 * Return an array answer of length n, representing the number of seats booked on each flight in order of their label.
 *
 *
 */
public class CorporateFlightBookings {
    public static void main(String[] args) {
        int[][] bookings = new int[][]{
                {1,2,10},
                {2,3,20},
                {2,5,25}
        };
        int n = 5;
        CorporateFlightBookings corporateFlightBookings = new CorporateFlightBookings();
        int[] result = corporateFlightBookings.corpFlightBookingsV1(bookings,n);
        result.toString();
    }

    public int[] corpFlightBookings(int[][] bookings, int n) {
        int[] sets = new int[n];

        for(int[] book : bookings){
            for(int i = book[0]; i <= book[1]; i++){
                sets[i - 1] += book[2];
            }
        }
        return sets;
    }

    /**
     * faster solution
     * @param bookings
     * @param n
     * @return
     */
    public int[] corpFlightBookingsV1(int[][] bookings, int n) {
        //Arrays.sort(bookings, (a,b) -> a[0] -b[0] );
        int[]res = new int[n];
        for(int[]book: bookings){
            res[book[0]-1] += book[2];
            if(book[1]<n)
                res[book[1]] -= book[2];
        }
        for(int i=1;i<n;i++){
            res[i]+= res[i-1];
        }
        return res;

    }
}
