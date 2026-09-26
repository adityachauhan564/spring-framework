package topic01_loops_and_conditions;

/*
 * Topic    : Loops and conditions (Head First Java, chapter 1)
 * Key idea : a while loop repeats until its condition is false;
 *            if/else picks the right word for each verse.
 * Run      : java -cp out topic01_loops_and_conditions.BottleSong
 * Try this : rewrite the while loop as a for loop.
 */
public class BottleSong {

    public static void main(String[] args) {
        int bottles = 10;

        while (bottles > 0) {
            System.out.println(bottles + " green " + bottleWord(bottles) + ", hanging on the wall");
            System.out.println(bottles + " green " + bottleWord(bottles) + ", hanging on the wall");
            System.out.println("And if one green bottle should accidentally fall,");

            bottles--;

            if (bottles > 0) {
                System.out.println("There'll be " + bottles + " green " + bottleWord(bottles) + ", hanging on the wall");
            } else {
                System.out.println("There'll be no green bottles, hanging on the wall");
            }
            System.out.println();
        }
    }

    // "1 bottle" but "2 bottles": decided fresh for every number
    private static String bottleWord(int count) {
        return count == 1 ? "bottle" : "bottles";
    }
}
