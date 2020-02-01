/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assign1;


import java.util.*;

public class AStar {


int n,sn,gn;
    int graph[][] = null;
    int heuristics[] = null;
    int parent[] = null;
    int path[] = null;
    int g[] = null;

    PriorityQueue<Integer> pq = null;
    ArrayList<Integer> visited = null;
    Scanner sc = null;
   
public AStar() {
sc = new Scanner(System.in);
System.out.println("Enter no. of nodes");
n = sc.nextInt();
       
        graph = new int[n+1][n+1];
        parent = new int[n+1];
        heuristics = new  int[n+1];
        path = new int[n+1];
        g = new int[n+1];
       
        visited = new ArrayList<Integer>();

        System.out.println("Enter Graph");
       
        for (int i = 1 ;i <= n ; i++) {
            for (int j = 1; j <= n; j++) {
            graph[i][j] = sc.nextInt();
            }
        }

        System.out.println("Enter heuristics value");
       
        for (int i = 1; i <= n; i++) {
            heuristics[i] = sc.nextInt();
        }
       
        System.out.println("Enter start node");
        sn = sc.nextInt();
        System.out.println("Enter end node");
        gn = sc.nextInt();
       
        pq = new PriorityQueue<Integer>(n, new Comparator<Integer>() {
        @Override
       
        public int compare(Integer a,Integer b) {
        if ((heuristics[a] + g[a]) == (heuristics[b] + g[b] )) {
        return 0;
        } else if ((heuristics[a] + g[a]) > (heuristics[b] + g[b] )) {
        return 1;
        } else {
        return -1;
        }
        }
});
       
       
        parent[sn] = 0; // start node has no parent
        g[sn] = 0; // 0 cost from start to start
       
        // calculate gn
//        for (int i = 1; i <= n; i++) {
//         for (int j = 1; j <= n; j++) {
// if (graph[i][j] != 0) {
// parent[j] = i; // if path --> i to j then i is parent of j
//
// if (g[j] == 0 && j != sn) { // if not initialized yet and is not start node
// g[j] = graph[i][j] + g[parent[j]];
// } else {
// g[j] = Math.min(g[j],graph[i][j] + g[parent[j]]);
// }
// }
// }
//
// }
       
//        System.out.println("g(n) : ");
//        for (int i = 1; i <= n ; i++) {
// System.out.println(i + " -- > " + g[i]);
// }
       
        pq.add(sn);
       
}

public void search () {

int currentNode = -1;

while (pq.size() != 0) {

// System.out.println("-------------------");
//     System.out.println("Current Node  -- > " + currentNode);
//     System.out.println("PQ");
//     for(int i : pq) {
//     System.out.println(i);
//     }
//     System.out.println();
// System.out.println("-------------------");

currentNode = pq.poll();

visited.add(currentNode);

if (currentNode == gn) break;

else {
for (int i = 1; i <= n; i++) {

if (graph[currentNode][i] != 0 && !visited.contains(i)) {

parent[i] = currentNode;
if (g[i] == 0 && i != sn) { // if not initialized yet and is not start node
g[i] = graph[currentNode][i] + g[parent[i]];
} else {
g[i] = Math.min(g[i],graph[currentNode][i] + g[parent[i]]);
}

pq.add(i);
}
}
}
}

// trace path
currentNode = gn;
int i = 0 ;

while(currentNode != sn) {
path[i++] = currentNode;
currentNode = parent[currentNode];
}
path[i] = sn;
System.out.println();
System.out.println("Path");

for (int j = i ; j>=0;j--) {
System.out.print(path[j] + "-->");
}

System.out.println();
}

public static void main(String[] args) {
AStar a = new AStar();
a.search();
}
}
/*
0 4 3 0 0 0 0
0 0 0 0 12 5 0
0 0 0 7 10 0 0
0 0 0 0 2 0 0
0 0 0 0 0 0 5
0 0 0 0 0 0 16
0 0 0 0 0 0 0

14 12 11 6 4 11 0
 */