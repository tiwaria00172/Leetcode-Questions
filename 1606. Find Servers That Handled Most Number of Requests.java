import java.util.*;

class Solution {
    public List<Integer> busiestServers(int k, int[] arrival, int[] load) {
        // TreeSet to store indices of available servers
        TreeSet<Integer> available = new TreeSet<>();
        for (int i = 0; i < k; i++) {
            available.add(i);
        }

        // Min-heap to store busy servers: pairs of [freeTime, serverId]
        PriorityQueue<int[]> busy = new PriorityQueue<>((a, b) -> Integer.compare(a[0], b[0]));

        int[] requestsHandled = new int[k];
        int maxHandled = 0;

        for (int i = 0; i < arrival.length; i++) {
            int currTime = arrival[i];
            int duration = load[i];

            // Free up servers whose job finishes at or before currTime
            while (!busy.isEmpty() && busy.peek()[0] <= currTime) {
                int[] freed = busy.poll();
                available.add(freed[1]);
            }

            // If no servers are available, drop the request
            if (available.isEmpty()) {
                continue;
            }

            // Try to find the first available server >= (i % k)
            Integer serverId = available.ceiling(i % k);

            // If none found >= (i % k), wrap around and pick the first available server from the beginning
            if (serverId == null) {
                serverId = available.first();
            }

            // Assign the request to this server
            available.remove(serverId);
            busy.add(new int[]{currTime + duration, serverId});
            requestsHandled[serverId]++;
            maxHandled = Math.max(maxHandled, requestsHandled[serverId]);
        }

        // Collect all servers that handled the maximum number of requests
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < k; i++) {
            if (requestsHandled[i] == maxHandled) {
                result.add(i);
            }
        }

        return result;
    }
}
