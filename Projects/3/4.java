import java.io.*;
import java.util.StringTokenizer;

//geeks for geeks
public class Palindrom {
    static int p = 29;
    static int MOD = 1000000007;
    static int n;
    public static int max;

    public static void main(String[] args) throws IOException {
        MyScanner sc = new MyScanner();
        OutputStream out = new BufferedOutputStream(System.out);
        String str = sc.next();
        n = str.length();
        int len = 0;
        long[] power = new long[n + 1];
        long[] mmi = new long[n + 1];
        computePowers(power, n);
        for( int i = 0 ; i<= n ; i++) {
            mmi[i] =findMMI(power[i]);
        }
        long[] prefix = new long[n + 1];
        long[] suffix = new long[n + 1];
        computePrefixHash(str, n, prefix, power);
        computeSuffixHash(str, n, suffix, power);

        for (int x = 0; x < n - 2; x++) {
            if (str.charAt(x) == str.charAt(x + 1)) {
                max = 0;
                evenBinary(prefix, suffix, power,mmi, x, 0, Math.min(x, -x + n - 2));
                int l = 2 * max + 2;
                if (l > len) {
                    len = l;
                }
            }
        }
        for (int x = 0; x < n -1 ; x++) {
            max = 0;
            oddBinary(prefix, suffix, power,mmi, x, 0, Math.min(x, n - 1 - x));
            int l = 2 * max + 1;
            if (l > len) {
                len = l;
            }
        }
        out.write((len + "\n").getBytes());
        out.flush();
    }
    /*   static class Query {
           int L, R;

           public Query(int L, int R) {
               this.L = L;
               this.R = R;
           }
       }

       static boolean isPalindrome(String str, int L, int R) {
           while (R > L) {
               if (str.charAt(L++) != str.charAt(R--)) {
                   return (false);
               }
           }
           return (true);
       }

     */
    static long modPow(long base, long exponent) {
        if (exponent == 0)
            return 1;
        if (exponent == 1)
            return base;
        long temp = modPow(base, exponent / 2);
        if (exponent % 2 == 0)
            return (temp % MOD * temp % MOD) % MOD;
        else {
            return (((temp % MOD * temp % MOD) % MOD) * base % MOD) % MOD;
        }
    }

    static long findMMI(long n) {
        return modPow(n, MOD - 2);
    }

    static void computePrefixHash(String str, int n, long prefix[], long power[]) {
        prefix[0] = 0;
        prefix[1] = str.charAt(0);
        for (int i = 2; i <= n; i++) {
            prefix[i] = (prefix[i - 1] % MOD + (str.charAt(i - 1) % MOD * power[i - 1] % MOD) % MOD) % MOD;
        }
        return;
    }

    static void computeSuffixHash(String str, int n, long suffix[], long power[]) {
        suffix[0] = 0;
        suffix[1] = str.charAt(n - 1);
        for (int i = n - 2, j = 2; i >= 0 && j <= n; i--, j++) {
            suffix[j] = (suffix[j - 1] % MOD + (str.charAt(i) % MOD * power[j - 1] % MOD) % MOD) % MOD;
        }
        return;
    }


    /* static boolean queryResults(String str, Query q, int n, int prefix[], int suffix[], int power[]) {
         int L = q.L;
         int R = q.R;

         long hash_LR = ((prefix[R + 1] - prefix[L] + MOD) % MOD * findMMI(power[L]) % MOD) % MOD;
         long reverse_hash_LR = ((suffix[n - L] - suffix[n - R - 1] + MOD) % MOD * findMMI(power[n - R - 1]) % MOD) % MOD;
         if (hash_LR == reverse_hash_LR) {
             if (isPalindrome(str, L, R) == true) {
                 return true;
             } else {
                 return false;
             }
         } else {
             return false;
         }
     }

     */

    static void computePowers(long power[], int n) {
        power[0] = 1;
        for (int i = 1; i <= n; i++) {
            power[i] = (power[i - 1] % MOD * p % MOD) % MOD;
        }
        return;
    }

    static void evenBinary(long[] prefix, long[] suffix, long[] power,long[] mmi, int c, int l, int h) {
        if (l > h)
            return;
        int mid = (h + l) / 2;
        long suffi = ((suffix[n + mid - c] - suffix[-c - mid + n - 2] + MOD) % MOD * mmi[n - mid - 2 - c] % MOD) % MOD;
        long prefi = ((prefix[c + 2 + mid] - prefix[c - mid] + MOD) % MOD * mmi[c - mid]% MOD) % MOD;
        if (suffi == prefi) {
            max = mid;
            evenBinary(prefix, suffix, power,mmi, c, mid + 1, h);
        } else {
            evenBinary(prefix, suffix, power,mmi, c, l, mid - 1);
        }
    }

    static void oddBinary(long[] prefix, long[] suffix, long[] power,long[] mmi, int c, int l, int h) {
        if (l > h)
            return;
        int mid = (h + l) / 2;
        long suffi = ((suffix[n - c + mid] - suffix[n - 1 - c - mid] + MOD) % MOD * mmi[n - 1 - c - mid] % MOD) % MOD;
        long prefi = ((prefix[c + mid + 1] - prefix[c - mid] + MOD) % MOD * mmi[c - mid] % MOD) % MOD;
        if (suffi == prefi) {
            max = mid;
            oddBinary(prefix, suffix, power,mmi, c, mid + 1, h);
        } else {
            oddBinary(prefix, suffix, power,mmi, c, l, mid - 1);
        }
    }


    static class MyScanner {
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
}


