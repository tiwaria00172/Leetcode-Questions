class Solution {
    // Number of days in each month for a non-leap year
    private final int[] daysInMonth = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

    public int countDaysSpentTogether(String arriveAlice, String leaveAlice, String arriveBob, String leaveBob) {
        // Convert all MM-DD strings to absolute day of the year (1 to 365)
        int startA = convertToDayOfYear(arriveAlice);
        int endA = convertToDayOfYear(leaveAlice);
        int startB = convertToDayOfYear(arriveBob);
        int endB = convertToDayOfYear(leaveBob);

        // Find the overlap between the two intervals
        int latestArrival = Math.max(startA, startB);
        int earliestLeave = Math.min(endA, endB);

        // Calculate overlapping days
        int overlap = earliestLeave - latestArrival + 1;

        // If there is no overlap, return 0
        return Math.max(0, overlap);
    }

    private int convertToDayOfYear(String date) {
        int month = Integer.parseInt(date.substring(0, 2));
        int day = Integer.parseInt(date.substring(3, 5));

        int dayOfYear = 0;
        // Sum days of all previous months
        for (int i = 0; i < month - 1; i++) {
            dayOfYear += daysInMonth[i];
        }
        // Add the days of the current month
        dayOfYear += day;

        return dayOfYear;
    }
}
