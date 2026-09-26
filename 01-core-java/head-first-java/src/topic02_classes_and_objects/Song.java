package topic02_classes_and_objects;

/*
 * Topic    : How objects behave (Head First Java, chapter 4)
 * Key idea : instance variables live in each object, so song1 and song2
 *            keep their own title/artist. Methods use that state.
 * Run      : java -cp out topic02_classes_and_objects.Song
 * Try this : add a 'duration' field and print it in play().
 */
public class Song {

    // instance variables: one copy per Song object
    private String title;
    private String artist;

    public void setTitle(String title) {
        this.title = title;   // 'this.title' is the field, 'title' is the parameter
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public void play() {
        System.out.println("Playing \"" + title + "\" by " + artist);
    }

    public static void main(String[] args) {
        Song song1 = new Song();
        song1.setTitle("Unstoppable");
        song1.setArtist("Dino James");

        Song song2 = new Song();
        song2.setTitle("My Way");
        song2.setArtist("S Pistols");

        song1.play();   // same method, different object -> different output
        song2.play();
    }
}
