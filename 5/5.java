
import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        MyScanner sc = new MyScanner();
        int[] numbers = new int[11];
        int[] bottom = new int[4];
        int num = 0;
        for (int i = 0; i <= 10; i++) {
            num = sc.nextInt();
            numbers[i] = num;
        }
        bottom[0] = num;
        for (int i = 1; i < 3; i++) {
            num = sc.nextInt();
            bottom[i] = num;
        }
        // Nodes node = new Nodes(num, 0);
        for (int i = 0; i < 101; i++) {
            num = sc.nextInt();
            Nodes node = new Nodes(num, i);
        }
        int first = num;
        int second = sc.nextInt();

        Graph graph = new Graph(100, 10000);
        int n = 0;
        for (int i = 0; i < 10; i++) {
            if (numbers[i] == 1) {
                for (int j = 0; j < 100; j++) {
                    graph.edge[n].src = j;
                    graph.edge[n].dest = i;
                    graph.edge[n].weight = 1 + Nodes.getNodeByName(i).size;
                    //  System.out.println("1) " + n + " " + graph.edge[n].src + " -- " + graph.edge[n].dest + " == " + graph.edge[n].weight);
                    n++;
                }
            }

        }

        if (bottom[0] == 1) {
            for (int j = 1; j < 10; j++) {
                for (int k = 0; k < 10; k++) {
                    for (int i = 0; i < 100; i++) {
                        if (numbers[j] == 1) {
                            if (numbers[k] == 1) {
                                graph.edge[n].src = i;
                                graph.edge[n].dest = j * 10 + k;
                                graph.edge[n].weight = 3 + Nodes.getNodeByName(j * 10 + k).size;
                                // System.out.println("2)" + n + " " + graph.edge[n].src + " -- " + graph.edge[n].dest + " == " + graph.edge[n].weight);
                                n++;

                            }
                        }
                    }
                }
            }
        }

        if (bottom[1] == 1) {
            for (int i = 0; i < 99; i++) {
                graph.edge[n].src = i;
                graph.edge[n].dest = i + 1;
                graph.edge[n].weight = 1 + Nodes.getNodeByName(i + 1).size;
                //  System.out.println("3)" + n + " " + graph.edge[n].src + " -- " + graph.edge[n].dest + " == " + graph.edge[n].weight);
                n++;
            }
            graph.edge[n].src = 99;
            graph.edge[n].dest = 0;
            graph.edge[n].weight = 1 + Nodes.getNodeByName(0).size;
            // System.out.println("3)" + n + " " + graph.edge[n].src + " -- " + graph.edge[n].dest + " == " + graph.edge[n].weight);
            n++;
        }

        if (bottom[2] == 1) {
            graph.edge[n].src = 0;
            graph.edge[n].dest = 99;
            graph.edge[n].weight = 1 + Nodes.getNodeByName(99).size;
            //  System.out.println("4)" + n + " " + graph.edge[n].src + " -- " + graph.edge[n].dest + " == " + graph.edge[n].weight);
            n++;
            for (int i = 1; i < 100; i++) {
                graph.edge[n].src = i;
                graph.edge[n].dest = i - 1;
                graph.edge[n].weight = 1 + Nodes.getNodeByName(i - 1).size;
                // System.out.println("4)" + n + " " + graph.edge[n].src + " -- " + graph.edge[n].dest + " == " + graph.edge[n].weight);
                n++;
            }

        }

        ShortestPath t = new ShortestPath();
        t.dijkstra(second, first);

    }


}

class Graph {
    int V, E;
    static Edge[] edge;

    Graph(int v, int e) {
        V = v;
        E = e;
        edge = new Edge[E];
        for (int i = 0; i < e; ++i)
            edge[i] = new Edge();
    }

    public static Edge getWeightBySrc(int src, int dest) {
        for (int i = 0; i < edge.length; i++) {
            if (edge[i].src == src && edge[i].dest == dest)
                return edge[i];
        }
        return null;
    }

    class Edge implements Comparable<Edge> {
        int src, dest, weight;

        public int compareTo(Edge compareEdge) {
            return this.weight - compareEdge.weight;
        }
    }
}

class ShortestPath {
    static final int V = 99;
    OutputStream out = new BufferedOutputStream(System.out);
    int minDistance(int dist[], Boolean sptSet[]) {
        int min = Integer.MAX_VALUE, min_index = -1;
        for (int v = 0; v < V; v++)
            if (sptSet[v] == false && dist[v] <= min) {
                min = dist[v];
                min_index = v;
            }
        return min_index;
    }


    public void dijkstra(int src, int dest) throws IOException {
        int dist[] = new int[V];
        Boolean sptSet[] = new Boolean[V];
        for (int i = 0; i < V; i++) {
            dist[i] = Integer.MAX_VALUE;
            sptSet[i] = false;
        }

        dist[dest] = 0;
        int small = Integer.MAX_VALUE;
        for (int count = 0; count < V - 1; count++) {
            int u = minDistance(dist, sptSet);
            sptSet[u] = true;
            for (int v = 0; v < V; v++) {
                Graph.Edge e = Graph.getWeightBySrc(u, v);
                if (e != null) {
                    int w = Graph.getWeightBySrc(u, v).weight;
                     if (!sptSet[v] && w != 0 && dist[u] != Integer.MAX_VALUE && dist[u] + w < dist[v]) {
                  //  if (dist[u] + w < dist[v]) {
                        dist[v] = dist[u] + w;
                    }

                }
            }
        }
        if (small != dist[src]) {
            out.write((dist[src] + Nodes.getNodeByName(dest).size + "\n").getBytes());
            out.flush();
        } else {
            out.write((-1 + "\n").getBytes());
            out.flush();
        }

    }


}

class Nodes {
    int size;
    int name;
    static ArrayList<Nodes> allNodes = new ArrayList<>();

    public Nodes(int size, int name) {
        this.size = size;
        this.name = name;
        allNodes.add(this);
    }

    public static Nodes getNodeByName(int nam) {
        for (Nodes node : allNodes) {
            if (node.name == nam)
                return node;
        }
        return null;
    }
}


class MyScanner {
    BufferedReader br;
    StringTokenizer st;

    public MyScanner() {
        br = new BufferedReader(new InputStreamReader(System.in));
    }

    String next() {
        while (st == null || !st.hasMoreElements()) {
            try {
                st = new StringTokenizer(br.readLine());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return st.nextToken();
    }

    int nextInt() {
        return Integer.parseInt(next());
    }


}





