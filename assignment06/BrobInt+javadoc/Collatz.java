/** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * File name  :  Collatz.java
 * @author    :  Michael Reza
 * Date       :  12-12-2019
 *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
public class Collatz {

  // Fields contained here
  private BrobInt bint;
  private int steps = 0;

  /**
   *  Constructor takes a string and creates a new BrobInt with that value,
   *  @param  integer  String value to be passed into new BrobInt instance
   */
  public Collatz (String integer) {
    this.bint = new BrobInt(integer);
  }

  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  Method to loop algorithm multiplying by 3 and adding 1 when odd, and dividing
   *  by 2 when even, until the value is 1
   *  @return int which is the number of "steps" taken in sequence before reaching 1
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
  public int reduce () {
    BrobInt me = this.bint;
    BrobInt oddEven = me.remainder(me.TWO);
    while (me.internalValue != "1") {
      if (Integer.parseInt(oddEven.internalValue) == 0) {
        me = me.divide(me.TWO);
      } else if (Integer.parseInt(oddEven.internalValue) == 1) {
        me = me.multiply(me.THREE);
        me = me.add(me.ONE);
      }
      oddEven = me.remainder(me.TWO);
      this.steps++;
    }
    return this.steps;
  }

  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  The main method contains tests for each method.
   *  @param  args  String array which contains command line arguments
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
  public static void main (String[] args) {
    System.out.println("----- New Collatz -----");


    System.out.println( "\n    Finding steps from 35 to 1: " );
    try { System.out.println( "      expecting: 13\n        and got: " + new Collatz("35").reduce() ); }
    catch( Exception e ) { System.out.println( "        Exception thrown:  " + e.toString() ); }

    System.out.println( "\n    Finding steps from 3 to 1: " );
    try { System.out.println( "      expecting: 7\n        and got: " + new Collatz("3").reduce() ); }
    catch( Exception e ) { System.out.println( "        Exception thrown:  " + e.toString() ); }

    System.out.println( "\n    Finding steps from 21 to 1: " );
    try { System.out.println( "      expecting: 7\n        and got: " + new Collatz("21").reduce() ); }
    catch( Exception e ) { System.out.println( "        Exception thrown:  " + e.toString() ); }
  }
}
