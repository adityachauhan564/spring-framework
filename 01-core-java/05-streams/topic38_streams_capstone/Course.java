package topic38_streams_capstone;

/*
 * Topic    : Capstone - using everything together on real-looking data (in28minutes FP03 style)
 *            Each Course has a name, a category, a review score (out of 100) and how many students joined.
 * Read     : Course -> CourseAnalysis
 */
public record Course(String name, String category, int reviewScore, int noOfStudents) { }
