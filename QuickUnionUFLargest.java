/*
Union find with find
 */
public class QuickUnionUFLargest {
    public int[] id;
    public int[] size;
    public int[] large;

    public QuickUnionUFLargest(int N) {
        id = new int[N];
        size = new int[N];
        large = new int[N];
        for (int i = 0; i < N; i++) {
            id[i] = i;
            size[i] = 1;
            large[i] = i;
        }
    }

    public int root(int i) {
        while (i != id[i]) {
            id[i] = id[id[i]];
            i = id[i];
        }
        return i;
    }

    public int find(int i) {
        return large[root(i)];
    }

    public boolean connected(int p, int q) {
        return root(p) == root(q);
    }

    public void union(int p, int q) {
        if (connected(p, q)) return;
        int rootp = root(p);
        int rootq = root(q);
        int largep = large[rootp];
        int largeq = large[rootq];

        if (size[rootp] <= size[rootq]) {
            id[rootp] = rootq;
            size[rootq] += size[rootp];

            if (largep > largeq) large[rootq] = largep;
        } else {
            id[rootq] = rootp;
            size[rootp] += size[rootq];

            if (largeq > largep) large[rootp] = largeq;
        }
    }


    public static void main(String[] args) {
        QuickUnionUFLargest uf = new QuickUnionUFLargest(10);
        uf.union(1, 2);
        System.out.println(uf.find(1) == 2);
        uf.union(2, 6);
        System.out.println(uf.find(1) == 6);
    }
}





