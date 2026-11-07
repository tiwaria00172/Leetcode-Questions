import java.util.*;

class Solution {
    private static class Server {
        long weight;
        int index;
        long freeTime;

        Server(long weight, int index, long freeTime) {
            this.weight = weight;
            this.index = index;
            this.freeTime = freeTime;
        }
    }

    public int[] assignTasks(int[] servers, int[] tasks) {
        int n = servers.length;
        int m = tasks.length;
        int[] ans = new int[m];

        PriorityQueue<Server> free = new PriorityQueue<>((a, b) -> {
            if (a.weight != b.weight) return Long.compare(a.weight, b.weight);
            return Integer.compare(a.index, b.index);
        });

        PriorityQueue<Server> used = new PriorityQueue<>((a, b) -> {
            if (a.freeTime != b.freeTime) return Long.compare(a.freeTime, b.freeTime);
            if (a.weight != b.weight) return Long.compare(a.weight, b.weight);
            return Integer.compare(a.index, b.index);
        });

        for (int i = 0; i < n; i++) {
            free.offer(new Server(servers[i], i, 0));
        }

        long currentTime = 0;
        for (int j = 0; j < m; j++) {
            currentTime = Math.max(currentTime, j);

            // Move servers that have finished their tasks back to the free heap
            while (!used.isEmpty() && used.peek().freeTime <= currentTime) {
                free.offer(used.poll());
            }

            // If no free servers are available, jump time to the earliest server release time
            if (free.isEmpty() && !used.isEmpty()) {
                currentTime = used.peek().freeTime;
                while (!used.isEmpty() && used.peek().freeTime <= currentTime) {
                    free.offer(used.poll());
                }
            }

            Server s = free.poll();
            ans[j] = s.index;
            s.freeTime = currentTime + tasks[j];
            used.offer(s);
        }

        return ans;
    }
}
