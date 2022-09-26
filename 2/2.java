import java.io.*;
import java.util.ArrayList;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
    static ArrayList<LinkedList> viewedBoxes = new ArrayList<>();

    public static void main(String[] args) {
        MyScanner sc = new MyScanner();
       // PrintWriter out = new PrintWriter(new BufferedOutputStream(System.out));

        int n = sc.nextInt();
        int m = sc.nextInt();

        LinkedList prev = null;


        for (int i = 0; i < n; i++) {
            LinkedList linkedList = new LinkedList(prev, sc.nextInt());
            if (prev != null)
                prev.next = (linkedList);
            prev = linkedList;
            if (i == 0)
                viewedBoxes.add(linkedList);
        }



        for (int i = 0; i < m; i++) {
            LinkedList cur = viewedBoxes.get(viewedBoxes.size() - 1);
            int s = sc.nextInt();
            if (s == 1) {
                if ((cur.next != null)&&(cur.prev == null)) {
                    viewedBoxes.add(cur.next);
                    cur.next.prev = null;
                } else if((cur.prev != null)&&(cur.next == null))  {
                    viewedBoxes.add(cur.prev);
                    cur.prev.next = null;
                }else if((cur.prev != null)&&(cur.next != null)) {
                    viewedBoxes.add(cur.next);
                    cur.prev.next = (cur.next);
                    cur.next.prev = (cur.prev);
                }

            } else if (s == 2) {
                int sec = sc.nextInt();
                LinkedList newLink = new LinkedList(cur, sec);
                if (cur.next != null) {
                    LinkedList next = cur.next;
                    cur.next = newLink;
                    newLink.next = next;
                    next.prev = newLink;
                }

            } else if (s == 3) {
                if (cur.next != null)
                    viewedBoxes.add(cur.next);

            } else if (s == 4) {
                if (cur.prev != null)
                    viewedBoxes.add(cur.prev);
            }

        }
        printPrevSmaller();
        printBigger();

        LinkedList max = viewedBoxes.get(0);

        for (LinkedList viewedBox : viewedBoxes) {
            if (viewedBox.score > max.score)
                max = viewedBox;
            else if ((viewedBox.score == max.score) && (viewedBox.value > max.value))
                max = viewedBox;
        }
        System.out.println((max.value));


    }

    static void printPrevSmaller() {
        Stack<LinkedList> S = new Stack<>();

        for (int i = 1; i < viewedBoxes.size(); i++) {
            while (!S.empty() && S.peek().value >= viewedBoxes.get(i).value) {
                S.pop();
            }
            if (!S.empty())
                S.peek().score += 1;

            S.push(viewedBoxes.get(i));
        }
    }

    static void printBigger() {
        Stack<LinkedList> S = new Stack<>();

        for (int i = viewedBoxes.size() - 1; i > 0; i--) {
            while (!S.empty() && S.peek().value <= viewedBoxes.get(i).value) {
                S.pop();
            }
            if (!S.empty())
                S.peek().score += 2;

            S.push(viewedBoxes.get(i));
        }
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


class LinkedList {

    LinkedList prev = null;
    LinkedList next = null;

    int value = 0;
    int score = 0;

    public LinkedList(LinkedList prev, int value) {
        this.prev = prev;
        this.value = value;
    }


}



