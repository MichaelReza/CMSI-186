/** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  File name     :  Clock.java
 *  Purpose       :  Provides a class defining methods for the ClockSolver class
 *  @author       :  B.J. Johnson
 *  Date written  :  2017-02-28
 *  Description   :  This class provides a bunch of methods which may be useful for the ClockSolver class
 *                   for Homework 4, part 1.  Includes the following:
 *
 *  Notes         :  None right now.  I'll add some as they occur.
 *  Warnings      :  None
 *  Exceptions    :  IllegalArgumentException when the input arguments are "hinky"
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  Revision History
 *  ---------------
 *            Rev      Date     Modified by:  Reason for change/modification
 *           -----  ----------  ------------  -----------------------------------------------------------
 *  @version 1.0.0  2017-02-28  B.J. Johnson  Initial writing and release
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */

public class Clock {
  /**
   *  Class field definitions go here
   */
   private static final double INVALID_ARGUMENT_VALUE = -1.0;
   private static final double MAXIMUM_DEGREE_VALUE = 360.0;
   private static final double MAX_TIME_SLICE_IN_SECONDS  = 1800.00;
   private static final double HOUR_HAND_DEGREES_PER_SECOND = 0.00834;
   private static final double MINUTE_HAND_DEGREES_PER_SECOND = 0.1;

   private double timeSlice = 60.0;
   private double hourAngleVal = 0.0;
   private double minuteAngleVal = 0.0;

   private double totalSeconds = 0.0;
   private double totalMinutes = this.totalSeconds / 60.0;
   private double totalHours = this.totalMinutes / 60.0;

  /**
   *  Constructor goes here
   */
   public Clock(String timeSlice) {
     this.timeSlice = Double.parseDouble(timeSlice);
   }

   public Clock() {

   }

  /**
   *  Methods go here
   *
   *  Method to calculate the next tick from the time increment
   *  @return double-precision value of the total seconds elapsed
   */
   public double tick() {
     this.hourAngleVal += HOUR_HAND_DEGREES_PER_SECOND * this.timeSlice;
     this.minuteAngleVal += MINUTE_HAND_DEGREES_PER_SECOND * this.timeSlice;
     this.totalSeconds += this.timeSlice;
     this.totalMinutes = Math.floor(this.totalSeconds / 60.0);
     this.totalHours = Math.floor(this.totalMinutes / 60.0);
     return this.totalSeconds;
   }

  /**
   *  Method to validate the angle argument
   *  @param   argValue  String from the main programs args[0] input
   *  @return  double-precision value of the argument
   *  @throws  NumberFormatException
   */
   public static double validateAngleArg( String argValue ) throws NumberFormatException {
     double value = Double.parseDouble(argValue);
     if (value < 360.0 && value >= 0.0) {
       return value;
     }
     throw new NumberFormatException();
   }

  /**
   *  Method to validate the optional time slice argument
   *  @param  argValue  String from the main programs args[1] input
   *  @return double-precision value of the argument or -1.0 if invalid
   *  note: if the main program determines there IS no optional argument supplied,
   *         I have elected to have it substitute the string "60.0" and call this
   *         method anyhow.  That makes the main program code more uniform, but
   *         this is a DESIGN DECISION, not a requirement!
   *  note: remember that the time slice, if it is small will cause the simulation
   *         to take a VERY LONG TIME to complete!
   */
   public static double validateTimeSliceArg( String argValue ) {
     double arg = Double.parseDouble(argValue);
     if (arg < MAX_TIME_SLICE_IN_SECONDS && arg > 0.0) { return arg; }
     return INVALID_ARGUMENT_VALUE;
   }

  /**
   *  Method to calculate and return the current position of the hour hand
   *  @return double-precision value of the hour hand location
   */
   public double getHourHandAngle() {
      return this.hourAngleVal % 360.0;
   }

  /**
   *  Method to calculate and return the current position of the minute hand
   *  @return double-precision value of the minute hand location
   */
   public double getMinuteHandAngle() {
      return this.minuteAngleVal % 360.0;
   }

  /**
   *  Method to calculate and return the angle between the hands
   *  @return double-precision value of the angle between the two hands
   */
   public double getHandAngle() {
      return Math.abs(this.getMinuteHandAngle() - this.getHourHandAngle());
   }

  /**
   *  Method to fetch the total number of seconds
   *   we can use this to tell when 12 hours have elapsed
   *  @return double-precision value the total seconds private variable
   */
   public double getTotalSeconds() {
      return this.totalSeconds;
   }

  /**
   *  Method to return a String representation of this clock
   *  @return String value of the current clock
   */
   public String toString() {
     String sec = Double.toString(this.totalSeconds % 60.0);
     String min = Double.toString(this.totalMinutes % 60.0);
     String hrs = Double.toString(this.totalHours);
     return hrs + ":" + min + ":" + sec;
   }

  /**
   *  The main program starts here
   *  remember the constraints from the project description
   *  @see  http://bjohnson.lmu.build/cmsi186web/homework04.html
   *  be sure to make LOTS of tests!!
   *  remember you are trying to BREAK your code, not just prove it works!
   */
   public static void main( String args[] ) {

      System.out.println( "\nCLOCK CLASS TESTER PROGRAM\n" +
                          "--------------------------\n" );
      System.out.println( "  Creating a new clock: " );

      Clock clock = new Clock();
      System.out.println(clock.toString());
      System.out.println( "    New clock created: " + clock.toString() );
      System.out.println( "    Testing validateAngleArg()....");
      System.out.print( "      sending '  0 degrees', expecting double value   0.0" );
      try { System.out.println( (0.0 == clock.validateAngleArg( "0.0" )) ? " - got 0.0" : " - no joy" ); }
      catch( Exception e ) { System.out.println ( " - Exception thrown: " + e.toString() ); }
      // Testing validateTimeSliceArg();
      try { System.out.println( (clock.INVALID_ARGUMENT_VALUE == clock.validateTimeSliceArg( "1800.0" )) ? " - got correct return" : " - should not be a valid timeSlice" ); }
      catch( Exception e ) { System.out.println ( " - Exception thrown: " + e.toString() ); }
      // Testing tick();
      try { System.out.println( (60.0 == clock.tick()) ? " - got 60.0" : " - no joy" ); }
      catch( Exception e ) { System.out.println ( " - Exception thrown: " + e.toString() ); }
      // Testing get getHourHandAngle();
      try { System.out.println( ((60 * clock.HOUR_HAND_DEGREES_PER_SECOND) == clock.getHourHandAngle()) ? " - got 0.5004" : " - no joy" ); }
      catch( Exception e ) { System.out.println ( " - Exception thrown: " + e.toString() ); }
      // Testing get getMinuteHandAngle();
      try { System.out.println( ((60 * clock.MINUTE_HAND_DEGREES_PER_SECOND) == clock.getMinuteHandAngle()) ? " - got 6.0" : " - no joy" ); }
      catch( Exception e ) { System.out.println ( " - Exception thrown: " + e.toString() ); }
      // Testing get getHandAngle();
      try { System.out.println( ((Math.abs(clock.minuteAngleVal - clock.hourAngleVal)) == clock.getHandAngle()) ? " - got correct value" : " - no joy" ); }
      catch( Exception e ) { System.out.println ( " - Exception thrown: " + e.toString() ); }
      // Testing get getTotalSeconds();
      try { System.out.println( (clock.totalSeconds == clock.getTotalSeconds()) ? " - got 60.0" : " - no joy" ); }
      catch( Exception e ) { System.out.println ( " - Exception thrown: " + e.toString() ); }
   }
}
