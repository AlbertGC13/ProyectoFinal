import java.util.*;

public class Kruskal {
    public static List<Arista> kruskal(Grafo grafo) {
        List<Arista> mst = new ArrayList<>();

        List<Arista> aristas = grafo.getAristasOrdenadasPorPeso();


        DisjointSet ds = new DisjointSet(grafo.getAdjList().size());

        for (Arista arista : aristas) {
            int root1 = ds.find(arista.origen.getId());
            int root2 = ds.find(arista.destino.getId());
            if (root1 != root2) {
                mst.add(arista);
                ds.union(root1, root2);
            }
        }

        return mst;
    }

    public static class DisjointSet {
        private int[] parent;
        private int[] rank;

        public DisjointSet(int n) {
            parent = new int[n + 1];
            rank = new int[n + 1];
            for (int i = 0; i <= n; i++) {
                parent[i] = i;
            }
        }

        public int find(int i) {
            if (parent[i] != i) {
                parent[i] = find(parent[i]);
            }
            return parent[i];
        }

        public void union(int x, int y) {
            int rootX = find(x);
            int rootY = find(y);
            if (rootX != rootY) {
                if (rank[rootX] > rank[rootY]) {
                    parent[rootY] = rootX;
                } else if (rank[rootX] < rank[rootY]) {
                    parent[rootX] = rootY;
                } else {
                    parent[rootY] = rootX;
                    rank[rootX] = rank[rootX] + 1;
                }
            }
        }
    }

}
