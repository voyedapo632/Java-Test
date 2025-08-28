public class Example {

    private final static double PI = 3.141;

    public static void main(String[] args) {
        // Declear floating point number called "myNumber"
        double myNumber;

        /**
         * Assignment statement
         * Assigns 45 to "myNumber"
        */
        myNumber = 45.0;

        // Print number in degrees
        System.out.println("Degrees: " + myNumber);

        /**
         * Assignment Statement Convert number from degrees to radians
        */
        myNumber = toRad(myNumber);

        // Print number in degrees
        System.out.println("Radians: " + myNumber);
    }

    /**
     * @Test Converts degrees to radians.
     * @param deg type double refers to degrees
     */
    private static double toRad(double deg) {
        return deg * PI / 180.0;
    }
}
