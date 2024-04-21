
//DS Assignment 5: Implement token ring based mutual exclusion algorithm.

import java.util.*;

public class TokenRing {

    private int n; // Number of processes
    private int[] state;
    private boolean token;

    public TokenRing(int n) {
        this.n = n;
        state = new int[n];
    }

    public void run() {
        // Initialize the ring
        for (int i = 0; i < n; i++) {
            state[i] = 0; // not in critical section
        }

        // Randomly select the process that will hold the token initially
        int curr = new Random().nextInt(n);
        token = true;

        while (true) {
            if (token) {
                if (state[curr] == 0) { // not in critical section
                    enterCritical(curr);
                    state[curr] = 1; // in critical section
                } else { // in critical section
                    exitCritical(curr);
                    state[curr] = 0; // not in critical section

                    curr = (curr + 1) % n; // pass the token
                    token = true;
                }
            }
        }
    }

    private void enterCritical(int id) {
        // Enter critical section
        System.out.println("Process " + id + " enters critical section");
    }

    private void exitCritical(int id) {
        // Exit critical section
        System.out.println("Process " + id + " exits critical section");
        token = false;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter Number of Processes To Be Created: ");
        int n = sc.nextInt();
        sc.close();

        TokenRing tr = new TokenRing(n); // Instantiate a ring of n processes
        tr.run(); // Run the token ring algorithm
    }
}
/*Commands:
denzil@denzil-VirtualBox:~/Documents/assignments$ javac TokenRing.java

denzil@denzil-VirtualBox:~/Documents/assignments$ java TokenRing*/


/* Output: 

Enter Number of Processes To Be Created: 8
Process 5 enters critical section
Process 5 exits critical section
Process 6 enters critical section
Process 6 exits critical section
Process 7 enters critical section
Process 7 exits critical section
Process 0 enters critical section
Process 0 exits critical section
Process 1 enters critical section
Process 1 exits critical section
Process 2 enters critical section
Process 2 exits critical section
Process 3 enters critical section
Process 3 exits critical section
Process 4 enters critical section
Process 4 exits critical section
Process 5 enters critical section
Process 5 exits critical section
Process 6 enters critical section
Process 6 exits critical section
Process 7 enters critical section
Process 7 exits critical section
Process 0 enters critical section
Process 0 exits critical section
Process 1 enters critical section
Process 1 exits critical section
Process 2 enters critical section
Process 2 exits critical section
.
.
.
.
(program runs infinitely to exit press Ctrl + C)
*/

