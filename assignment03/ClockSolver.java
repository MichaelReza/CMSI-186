/** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  File name     :  ClockSolver.java
 *  Purpose       :  The main program for the ClockSolver class
 *  @see
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

public class ClockSolver {
  /**
   *  Class field definitions go here
   */
   private final double MAX_SECONDS   = 43200.0;  // double-precision value of 12 hours in seconds
   private final double EPSILON_VALUE = 0.1;     // small value for double-precision comparisons
   private Clock clock;

  /**
   *  Constructor
   *  This just calls the superclass constructor, which is "Object"
   */
   public ClockSolver() {

   }

  /**
   *  Method to handle all the input arguments from the command line
   *   this sets up the variables for the simulation
   */
   public void handleInitialArguments( String args[] ) {
     // args[0] specifies the angle for which you are looking
     //  your simulation will find all the angles in the 12-hour day at which those angles occur
     // args[1] if present will specify a time slice value; if not present, defaults to 60 seconds
     // you may want to consider using args[2] for an "angle window"

      System.out.println( "\n   Hello world, from the ClockSolver program!!\n\n" ) ;
      if( 0 == args.length ) {
         System.out.println( "   Sorry you must enter at least one argument\n" +
                             "   Usage: java ClockSolver <angle> [timeSlice]\n" +
                             "   Please try again..........." );
         System.exit( 1 );
      }
      else if ( 1 == args.length ) { this.clock = new Clock(); }
      else if ( 2 == args.length ) { this.clock = new Clock(args[1]); }
   }

  /**
   *  The main program starts here
   *  remember the constraints from the project description
   *  @see  http://bjohnson.lmu.build/cmsi186web/homework04.html
   *  @param  args  String array of the arguments from the command line
   *                args[0] is the angle for which we are looking
   *                args[1] is the time slice; this is optional and defaults to 60 seconds
   */
   public static void main( String args[] ) {
      ClockSolver cse = new ClockSolver();
      double[] timeValues = new double[3];
      cse.handleInitialArguments( args );

      // setting variable to store each new angle value created by tick() and
      // double value of desired angles
      double desiredAngle = Double.parseDouble(args[0]);
      double angleComp;
      while( cse.clock.getTotalSeconds() <=  cse.MAX_SECONDS ) {
        angleComp = cse.clock.getHandAngle();
        // if (angleComp <=  desiredAngle + cse.EPSILON_VALUE && angleComp >= desiredAngle - cse.EPSILON_VALUE ||
        //     360 - angleComp <= desiredAngle + cse.EPSILON_VALUE && 360 - angleComp >= desiredAngle - cse.EPSILON_VALUE) {
        //   System.out.println("TIME: " + cse.clock.toString() + "  -----   " + angleComp + "  is equal to desiredAngle: " + desiredAngle + "-------------------------\n");
        // }
        if (Math.abs(angleComp - desiredAngle) <= cse.EPSILON_VALUE ||
            Math.abs(360 - angleComp - desiredAngle) <= cse.EPSILON_VALUE) {
          System.out.println("TIME: " + cse.clock.toString() + "  -----   " + angleComp + "  is equal to desiredAngle: " + desiredAngle + "-------------------------\n");
        }
        cse.clock.tick();
      }
      System.exit( 0 );
   }
}
