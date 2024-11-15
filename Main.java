import java.util.*;

public class Main {
    ArrayList[] nextGraph;
    ArrayList[] preGraph;

    public long minimumWeight(int n, int[][] edges, int src1, int src2, int dest) {
        System.out.println("Building the graph...");
        buildGraph(n, edges);

        long[] src1To = new long[n], src2To = new long[n], toDest = new long[n];
        Arrays.fill(src1To, -1);
        Arrays.fill(src2To, -1);
        Arrays.fill(toDest, -1);

        System.out.println("Starting Dijkstra from src1: " + src1);
        shortestPath(src1, src1To, nextGraph);
        System.out.println("Shortest paths from src1 completed. Distances: " + Arrays.toString(src1To));

        System.out.println("Starting Dijkstra from src2: " + src2);
        shortestPath(src2, src2To, nextGraph);
        System.out.println("Shortest paths from src2 completed. Distances: " + Arrays.toString(src2To));

        System.out.println("Starting Dijkstra to dest (reverse graph): " + dest);
        shortestPath(dest, toDest, preGraph);
        System.out.println("Shortest paths to dest completed. Distances: " + Arrays.toString(toDest));

        System.out.println("Evaluating minimum combined path...");
        long res = -1;
        for (int i = 0; i < n; i++) {
            long d1 = src1To[i], d2 = src2To[i], d3 = toDest[i];
            System.out.printf("Node %d: d1 = %d, d2 = %d, d3 = %d%n", i, d1, d2, d3);
            if (d1 >= 0 && d2 >= 0 && d3 >= 0) {
                long d = d1 + d2 + d3;
                System.out.printf("  -> Combined distance: %d%n", d);
                if (res == -1 || d < res) {
                    res = d;
                    System.out.printf("  -> Updated minimum distance: %d%n", res);
                }
            }
        }

        System.out.println("Final minimum weight: " + res);
        return res;
    }

    private void buildGraph(int n, int[][] edges) {
        nextGraph = new ArrayList[n];
        preGraph = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            nextGraph[i] = new ArrayList<>();
            preGraph[i] = new ArrayList<>();
        }

        System.out.println("Adding edges to graphs...");
        for (int[] edge : edges) {
            int from = edge[0], to = edge[1], weight = edge[2];
            System.out.printf("Edge from %d to %d with weight %d%n", from, to, weight);
            nextGraph[from].add(new int[]{to, weight});
            preGraph[to].add(new int[]{from, weight});
        }
        System.out.println("Graph building completed.");
    }

    private void shortestPath(int src, long[] srcTo, ArrayList<int[]>[] graph) {
        System.out.println("Initializing Dijkstra's algorithm from source: " + src);
        PriorityQueue<long[]> heap = new PriorityQueue<>((a, b) -> Long.compare(a[1], b[1]));
        heap.offer(new long[]{src, 0});

        while (!heap.isEmpty()) {
            long[] node = heap.poll();
            int to = (int) node[0];
            long dist = node[1];
            System.out.printf("Visiting node %d with current distance %d%n", to, dist);

            if (srcTo[to] != -1 && srcTo[to] <= dist) {
                System.out.printf("  -> Node %d already has a shorter distance. Skipping.%n", to);
                continue;
            }

            srcTo[to] = dist;
            System.out.printf("  -> Distance to node %d updated to %d%n", to, dist);

            for (int[] next : graph[to]) {
                System.out.printf("  -> Checking neighbor %d with edge weight %d%n", next[0], next[1]);
                heap.offer(new long[]{next[0], dist + next[1]});
            }
        }
        System.out.println("Dijkstra's algorithm completed for source: " + src);
    }

    public static void main(String[] args) {
        Main solution = new Main();

        // Example graph input
        int n = 6;
        int[][] edges = {
                {0, 2, 2},
                {0, 5, 6},
                {1, 0, 3},
                {1, 4, 5},
                {2, 1, 1},
                {2, 3, 3},
                {2, 3, 4},
                {3, 4, 2},
                {4, 5, 1}
        };
        int src1 = 0, src2 = 1, dest = 5;

        System.out.println("Calculating minimum weight...");
        long result = solution.minimumWeight(n, edges, src1, src2, dest);
        System.out.println("The minimum weight from src1, src2 to dest is: " + result);
    }
}
