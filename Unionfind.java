public class Unionfind {
    public int[] id;
    public int[] size;

    public void QuickUnionUF(int N) {
        id = new int[N];
        size = new int[N];
        for (int i = 0; i < N; i++) {
            id[i] = i;
            size[i] = 1;
        }
    }

    public int root(int i) {
        while (i != id[i]) {
            id[i] = id[id[i]];
            i = id[i];
        }
        return i;
    }

    public boolean connected(int p, int q) {
        return root(p) == root(q);
    }
    
    public void union(int p, int q) {
        int i = root(p);
        int j = root(q);
        if (i == j) return;
        if (size[i] <= size[j]) {
            id[i] = j;
            size[j] += size[i];
        } else {
            id[j] = i;
            size[i] += size[j];
        }
    }

    public int earliestAcq(int[][] logs, int n) {
        this.QuickUnionUF(n);
        for (int[] log : logs) {
            union(log[1], log[2]);
            if (size[root(log[1])] == n || size[root(log[2])] == n) {
                return log[0];
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        Unionfind uf = new Unionfind();
        int[][] logs = {
                {20190101, 0, 1},
                {20190104, 3, 4},
                {20190107, 2, 3},
                {20190211, 1, 5},
                {20190224, 2, 4},
                {20190301, 0, 3},
                {20190312, 1, 2},
                {20190322, 4, 5}
        };
        int n = 6;
        int ans = uf.earliestAcq(logs, n);
        System.out.println(ans);
    }
}
