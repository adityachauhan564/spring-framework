package topic04_control_flow;

/*
 * Topic    : Loops and conditions (from Head First Java, chapter 1)
 * Key idea : - A while loop keeps repeating as long as its condition is true.
 *              Here: keep singing while there are bottles left.
 *            - if/else picks the right word for each verse ("bottle" or "bottles").
 * Run      : java -cp out topic04_control_flow.BottleSong
 * Try this : Rewrite the while loop as a for loop. The output should not change.
 */
public class BottleSong {

    public static void main(String[] args) {
        int bottles = 10;

        // repeat this block while bottles is more than 0
        while (bottles > 0) {
            System.out.println(bottles + " green " + bottleWord(bottles) + ", hanging on the wall");
            System.out.println(bottles + " green " + bottleWord(bottles) + ", hanging on the wall");
            System.out.println("And if one green bottle should accidentally fall,");

            bottles--;          // one bottle fell, so reduce the count by 1. Without this the loop never ends

            if (bottles > 0) {
                System.out.println("There'll be " + bottles + " green " + bottleWord(bottles) + ", hanging on the wall");
            } else {
                System.out.println("There'll be no green bottles, hanging on the wall");
            }
            System.out.println();
        }
    }

    // English grammar: "1 bottle" but "2 bottles". We check this fresh for every number
    private static String bottleWord(int count) {
        return count == 1 ? "bottle" : "bottles";
    }
}
