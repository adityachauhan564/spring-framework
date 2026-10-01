package topic08_classes_and_objects;

/*
 * Topic    : How objects behave (from Head First Java, chapter 4)
 * Key idea : Instance variables live INSIDE each object. So song1 and song2 each keep
 *            their own title and artist - like two songs in your Spotify playlist.
 *            When you call play(), it uses the data of THAT object only.
 * Run      : java -cp out topic08_classes_and_objects.Song
 * Try this : Add a 'duration' field and print it inside play().
 */
public class Song {

    // instance variables: every Song object gets its own copy of these
    private String title;
    private String artist;

    // setter: a method to change a private field from outside
    public void setTitle(String title) {
        this.title = title;   // 'this.title' = the field of this object, 'title' = the value passed in
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
