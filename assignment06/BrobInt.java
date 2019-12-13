/** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * File name  :  BrobInt.java
 * Purpose    :  Learning exercise to implement arbitrarily large numbers and their operations
 * @author    :  B.J. Johnson
 * Date       :  2017-04-04
 * Description:  @see <a href='http://bjohnson.lmu.build/cmsi186web/homework06.html'>Assignment Page</a>
 * Notes      :  None
 * Warnings   :  None
 *
 *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * Revision History
 * ================
 *   Ver      Date     Modified by:  Reason for change or modification
 *  -----  ----------  ------------  ---------------------------------------------------------------------
 *  1.0.0  2017-04-04  B.J. Johnson  Initial writing and begin coding
 *  1.1.0  2017-04-13  B.J. Johnson  Completed addByt, addInt, compareTo, equals, toString, Constructor,
 *                                     validateDigits, two reversers, and valueOf methods; revamped equals
 *                                     and compareTo methods to use the Java String methods; ready to
 *                                     start work on subtractByte and subtractInt methods
 *  1.2.0  2019-04-18  B.J. Johnson  Fixed bug in add() method that was causing errors in Collatz
 *                                     sequence.  Added some tests into the main() method at the bottom
 *                                     of the file to test construction.  Also added two tests there to
 *                                     test multiplication by three and times-3-plus-1 operations
 *  1.3.0  2019-11-17  A. Volosin    Include method for removing zeroes. Delet pressEnter.
 *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
import java.util.Arrays;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class BrobInt {

   public static final BrobInt ZERO     = new BrobInt(  "0" );      /// Constant for "zero"
   public static final BrobInt ONE      = new BrobInt(  "1" );      /// Constant for "one"
   public static final BrobInt TWO      = new BrobInt(  "2" );      /// Constant for "two"
   public static final BrobInt THREE    = new BrobInt(  "3" );      /// Constant for "three"
   public static final BrobInt FOUR     = new BrobInt(  "4" );      /// Constant for "four"
   public static final BrobInt FIVE     = new BrobInt(  "5" );      /// Constant for "five"
   public static final BrobInt SIX      = new BrobInt(  "6" );      /// Constant for "six"
   public static final BrobInt SEVEN    = new BrobInt(  "7" );      /// Constant for "seven"
   public static final BrobInt EIGHT    = new BrobInt(  "8" );      /// Constant for "eight"
   public static final BrobInt NINE     = new BrobInt(  "9" );      /// Constant for "nine"
   public static final BrobInt TEN      = new BrobInt( "10" );      /// Constant for "ten"

  /// Some constants for other intrinsic data types
  ///  these can help speed up the math if they fit into the proper memory space
   public static final BrobInt MAX_INT  = new BrobInt( Integer.valueOf( Integer.MAX_VALUE ).toString() );
   public static final BrobInt MIN_INT  = new BrobInt( Integer.valueOf( Integer.MIN_VALUE ).toString() );
   public static final BrobInt MAX_LONG = new BrobInt( Long.valueOf( Long.MAX_VALUE ).toString() );
   public static final BrobInt MIN_LONG = new BrobInt( Long.valueOf( Long.MIN_VALUE ).toString() );

  /// These are the internal fields
   public  String internalValue = "";        // internal String representation of this BrobInt
   public  byte   sign          = 0;         // "0" is positive, "1" is negative
  /// You can use this or not, as you see fit.  The explanation was provided in class
   private String reversed      = "";        // the backwards version of the internal String representation
   public byte[] byteVersion    = null;      // the byte version of the reversed String representation
   public byte[] forwardBytes   = null;      // the byte version of the internal String representation

   private static BufferedReader input = new BufferedReader( new InputStreamReader( System.in ) );
   private static final boolean DEBUG_ON = false;
   private static final boolean INFO_ON  = false;

  /**
   *  Constructor takes a string and assigns it to the internal storage, checks for a sign character
   *   and handles that accordingly;  it then checks to see if it's all valid digits, and reverses it
   *   for later use
   *  @param  value  String value to make into a BrobInt
   */
   public BrobInt( String value ) {
     this.internalValue = value;
     removeLeadingZeros(this);
     this.sign = (value.charAt(0) == '-') ? (byte)1 : (byte)0;
     StringBuffer sbf = new StringBuffer(value);
     sbf.reverse();
     this.reversed = (value.charAt(0) == '-' || value.charAt(0) == '+') ? sbf.substring(0, value.length() - 1) : sbf.toString();
     validateDigits();
     byteVersion = new byte[this.reversed.length()];
     forwardBytes = new byte[this.reversed.length()];
     for (int i = 0; i < byteVersion.length; i++) {
       byteVersion[i] = (byte)Character.getNumericValue(this.reversed.charAt(i));
     }
     for (int i = byteVersion.length - 1; i >= 0; i--) {
       forwardBytes[Math.abs(i - (byteVersion.length - 1))] = byteVersion[i];
     }
   }

  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  Method to validate that all the characters in the value are valid decimal digits
   *  @return  boolean  true if all digits are good
   *  @throws  IllegalArgumentException if something is hinky
   *  note that there is no return false, because of throwing the exception
   *  note also that this must check for the '+' and '-' sign digits
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
   public boolean validateDigits() throws IllegalArgumentException {
     char[] validChars = {'+', '-', '.', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
     char[] input = this.internalValue.toCharArray();
     for (char c : input) {
       boolean validation = false;
       for (int i = 0; i < validChars.length; i++) {
         if (validChars[i] == c) { validation = true; break; }
         if (i == validChars.length - 1 && !validation) { throw new IllegalArgumentException("Please input a valid number"); }
       }
     }
     return true;
   }

  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  Method to reverse the value of this BrobInt
   *  @return BrobInt that is the reverse of the value of this BrobInt
   *  NOTE: you can use this or not, as you see fit; explanation was given in class
   *  @see StringBuffer API page for an easy way to do this
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
   public BrobInt reverser() {
      throw new UnsupportedOperationException( "\n         Sorry, that operation is not yet implemented." );
   }

  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  Method to reverse the value of a BrobIntk passed as argument
   *  Note: static method
   *  @param  bint         BrobInt to reverse its value
   *  @return BrobInt that is the reverse of the value of the BrobInt passed as argument
   *  NOTE: you can use this or not, as you see fit; explanation was given in class
   *  @see StringBuffer API page for an easy way to do this
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
   public static BrobInt reverser( BrobInt bint ) {
      throw new UnsupportedOperationException( "\n         Sorry, that operation is not yet implemented." );
   }

  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  Method to add the value of a BrobInt passed as argument to this BrobInt
   *  @param  bint         BrobInt to add to this
   *  @return BrobInt that is the sum of the value of this BrobInt and the one passed in
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
   public BrobInt add( BrobInt bint ) {
     if (this.sign == 1 && bint.sign == 0) { this.sign = 0; this.internalValue = this.internalValue.substring(1, this.internalValue.length()); return bint.subtract(this); }
     else if (this.sign == 0 && bint.sign == 1) { bint.sign = 0; bint.internalValue = bint.internalValue.substring(1, bint.internalValue.length()); return subtract(bint); }
     else if (this.sign == 1 && bint.sign == 1) { bint.sign = 0; bint.internalValue = bint.internalValue.substring(1, bint.internalValue.length()); return subtract(bint);/*sumSign = '-';*/ }
     byte[] a = this.byteVersion;
     byte[] b = bint.byteVersion;
     byte[] c = new byte[Math.max(this.reversed.length(), bint.reversed.length()) + 2];
     int carry = 0;
     int sum = 0;
     int digit = 0;
     for (int i = 0; i < c.length; i++) {
       if (i < a.length && i < b.length) { sum = (int)a[i] + (int)b[i] + carry; }
       else if (i < a.length && i >= b.length) { sum = (int)a[i] + carry; }
       else if (i >= a.length && i < b.length) { sum = (int)b[i] + carry; }
       else { sum = carry; }
       carry = 0;
       if (sum >= 10) { digit = (sum % 10); carry = sum / 10; }
       else { digit = sum; }
       c[i] = (byte)digit;
     }
     String s = "";
     for (int i = c.length - 1; i >= 0; i--) {
       s += c[i];
     }
     return removeLeadingZeros(new BrobInt(s));
   }

  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  Method to subtract the value of a BrobIntk passed as argument to this BrobInt
   *  @param  bint         BrobInt to subtract from this
   *  @return BrobInt that is the difference of the value of this BrobInt and the one passed in
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
   public BrobInt subtract( BrobInt bint ) {
     if (this.sign == 1 && bint.sign == 0) {
       this.sign = 0;
       this.internalValue = this.internalValue.substring(1, this.internalValue.length());
       return new BrobInt("-" + add(bint).toString());
     }
     else if (this.sign == 0 && bint.sign == 1) {
       bint.sign = 0;
       bint.internalValue = bint.internalValue.substring(1, this.internalValue.length());
       return add(bint);
     }
     else if (this.sign == 1 && bint.sign == 1) {
       bint.sign = 0;
       bint.internalValue = bint.internalValue.substring(1, bint.internalValue.length());
       return add(bint);
     }
     else {
       if (Integer.parseInt(this.internalValue) < Integer.parseInt(bint.internalValue)) {
         return new BrobInt("-" + bint.subtract(this).toString());
       }
     }
     byte[] a = this.byteVersion;
     byte[] b = bint.byteVersion;
     byte[] c = new byte[Math.max(this.reversed.length(), bint.reversed.length()) + 2];
     int diff = 0;
     for (int i = 0; i < c.length; i++) {
       if (i < a.length && i < b.length) { diff = (int)a[i] - (int)b[i];}
       else if (i < a.length && i >= b.length) { diff = (int)a[i]; }
       if (diff < 0 && (i + 1) < a.length) {
         diff += 10;
         a[i + 1] -= 1;
       }
       c[i] = (byte)diff;
       diff = 0;
     }
     String d = "";
     for (int i = c.length - 1; i >= 0; i--) {
       d += c[i];
     }
     return removeLeadingZeros(new BrobInt(d));
   }

  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  Method to multiply the value of a BrobIntk passed as argument to this BrobInt
   *  @param  bint         BrobInt to multiply this by
   *  @return BrobInt that is the product of the value of this BrobInt and the one passed in
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
   public BrobInt multiply( BrobInt bint ) {
     if (this.sign == 1 && bint.sign == 0) {
       this.sign = 0;
       this.internalValue = this.internalValue.substring(1, this.internalValue.length());
       return new BrobInt("-" + multiply(bint).toString());
     }
     else if (this.sign == 0 && bint.sign == 1) {
       bint.sign = 0;
       bint.internalValue = bint.internalValue.substring(1, bint.internalValue.length());
       return new BrobInt("-" + multiply(bint).toString());
     }
     else if (this.sign == 1 && bint.sign == 1) {
       this.sign = 0; bint.sign = 0;
       this.internalValue = this.internalValue.substring(1, this.internalValue.length());
       bint.internalValue = bint.internalValue.substring(1, bint.internalValue.length());
       return multiply(bint);
     }
     else {
       if (Integer.parseInt(this.internalValue) < Integer.parseInt(bint.internalValue)) {
         return bint.multiply(this);
       }
     }
     byte[] a = this.byteVersion;
     byte[] b = bint.byteVersion;
     byte[][] c = new byte[a.length + b.length + 2][a.length + b.length + 2];
     int product = 0;
     int carry = 0;
     for (int i = 0; i < b.length; i++) {
       for (int j = 0; j < c[0].length; j++) {
         if (i > 0) {
           for (int n = 0; n < i; n++) {
             c[i][n] = 0;
           }
         }
         if (j < a.length && i < b.length) { product = a[j] * b[i] + carry; }
         else if (j < a.length && i >= b.length) { product = a[j] + carry; }
         else { product = carry; }
         if (product >= 10) {
           carry = product / 10;
           product = product % 10;
           if ((j + i) < c[0].length) {
             c[i][j + i] = (byte)product;
           }
         }
         else {
           if ((j + i) < c[0].length) {
             c[i][j + i] = (byte)product;
           }
           product = 0;
           carry = 0;
         }
         product = 0;
       }
     }
     String[] s = new String[b.length];
     for (int i = 0; i < b.length; i++) {
       s[i] = "";
       for (int j = c[0].length - 1; j >= 0; j--) {
         s[i] += c[i][j];
       }
     }
     BrobInt[] bints = new BrobInt[s.length];
     for (int i = 0; i < s.length; i++) { bints[i] = new BrobInt(s[i]); }
     for (int i = 1; i < bints.length; i++) { bints[0] = bints[0].add(bints[i]); }
     return removeLeadingZeros(bints[0]);
   }

  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  Method to divide the value of this BrobIntk by the BrobInt passed as argument
   *  @param  bint         BrobInt to divide this by
   *  @return BrobInt that is the dividend of this BrobInt divided by the one passed in
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
   public BrobInt divide( BrobInt bint ) {
     if (this.sign == 1 && bint.sign == 0) {
       this.sign = 0;
       this.internalValue = this.internalValue.substring(1, this.internalValue.length());
       return new BrobInt("-" + divide(bint).toString());
     }
     else if (this.sign == 0 && bint.sign == 1) {
       bint.sign = 0;
       bint.internalValue = bint.internalValue.substring(1, bint.internalValue.length());
       return new BrobInt("-" + divide(bint).toString());
     }
     else if (this.sign == 1 && bint.sign == 1) {
       this.sign = 0; bint.sign = 0;
       this.internalValue = this.internalValue.substring(1, this.internalValue.length());
       bint.internalValue = bint.internalValue.substring(1, bint.internalValue.length());
       return divide(bint);
     }
     else {
       if (Integer.parseInt(this.internalValue) < Integer.parseInt(bint.internalValue)) {
         return new BrobInt("0");
       }
       else if (Integer.parseInt(this.internalValue) == Integer.parseInt(bint.internalValue)) {
         return new BrobInt("1");
       }
       else if (Integer.parseInt(bint.internalValue) == 0) {
         throw new IllegalArgumentException();
       }
     }
     byte[] d1 = this.forwardBytes;
     byte[] d2 = bint.forwardBytes;
     BrobInt quotient = new BrobInt("0");
     BrobInt d3bint = new BrobInt("0");
     int n = 0;
     while (n < d1.length || Integer.parseInt(d3bint.internalValue) == 0) {
       d3bint = d3bint.add( new BrobInt("" + d1[n]) );
       if (Integer.parseInt(d3bint.internalValue) < Integer.parseInt(bint.internalValue)) {
         d3bint = d3bint.multiply(BrobInt.TEN);
         quotient = quotient.multiply(BrobInt.TEN);
         n++;
         continue;
       }
       while (Integer.parseInt(d3bint.internalValue) >= Integer.parseInt(bint.internalValue)) {
         d3bint = d3bint.subtract(bint);
         quotient = quotient.add(BrobInt.ONE);
       }
       if (n == d1.length - 1) { break; }
       d3bint = d3bint.multiply(BrobInt.TEN);
       quotient = quotient.multiply(BrobInt.TEN);
       n++;
     }
     return removeLeadingZeros(quotient);
   }

  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  Method to get the remainder of division of this BrobInt by the one passed as argument
   *  @param  bint         BrobInt to divide this one by
   *  @return BrobInt that is the remainder of division of this BrobInt by the one passed in
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
   public BrobInt remainder( BrobInt bint ) {
     BrobInt quotient = this.divide(bint);
     BrobInt product = quotient.multiply(bint);
     BrobInt remainder = this.subtract(product);
     return remainder;
   }

  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  Method to compare a BrobInt passed as argument to this BrobInt
   *  @param  bint  BrobInt to compare to this
   *  @return int   that is one of neg/0/pos if this BrobInt precedes/equals/follows the argument
   *  NOTE: this method does not do a lexicographical comparison using the java String "compareTo()" method
   *        It takes into account the length of the two numbers, and if that isn't enough it does a
   *        character by character comparison to determine
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
   public int compareTo( BrobInt bint ) {

     // remove any leading zeros because we will compare lengths
      String me  = removeLeadingZeros( this ).toString();
      String arg = removeLeadingZeros( bint ).toString();

     // handle the signs here
      if( 1 == sign && 0 == bint.sign ) {
         return -1;
      } else if( 0 == sign && 1 == bint.sign ) {
         return 1;
      } else if( 0 == sign && 0 == bint.sign ) {
        // the signs are the same at this point ~ both positive
        // check the length and return the appropriate value
        //   1 means this is longer than bint, hence larger positive
        //  -1 means bint is longer than this, hence larger positive
         if( me.length() != arg.length() ) {
            return (me.length() > arg.length()) ? 1 : -1;
         }
      } else {
        // the signs are the same at this point ~ both negative
         if( me.length() != arg.length() ) {
            return (me.length() > arg.length()) ? -1 : 1;
         }
      }

     // otherwise, they are the same length, so compare absolute values
      for( int i = 0; i < me.length(); i++ ) {
         Character a = Character.valueOf( me.charAt(i) );
         Character b = Character.valueOf( arg.charAt(i) );
         if( Character.valueOf(a).compareTo( Character.valueOf(b) ) > 0 ) {
            return 1;
         } else if( Character.valueOf(a).compareTo( Character.valueOf(b) ) < 0 ) {
            return (-1);
         }
      }
      return 0;
   }

  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  Method to check if a BrobInt passed as argument is equal to this BrobInt
   *  @param  bint     BrobInt to compare to this
   *  @return boolean  that is true if they are equal and false otherwise
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
   public boolean equals( BrobInt bint ) {
      return ( (sign == bint.sign) && (this.toString().equals( bint.toString() )) );
   }

  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  Method to return a BrobInt given a long value passed as argument
   *  @param  value    long type number to make into a BrobInt
   *  @return BrobInt  which is the BrobInt representation of the long
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
   public static BrobInt valueOf( long value ) throws NumberFormatException {
      BrobInt bi = null;
      try { bi = new BrobInt( Long.valueOf( value ).toString() ); }
      catch( NumberFormatException nfe ) { throw new NumberFormatException( "\n  Sorry, the value must be numeric of type long." ); }
      return bi;
   }

  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  Method to return a String representation of this BrobInt
   *  @return String  which is the String representation of this BrobInt
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
   public String toString() {
      return internalValue;
   }

  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  Method to remove leading zeros from a BrobInt passed as argument
   *  @param  bint     BrobInt to remove zeros from
   *  @return BrobInt that is the argument BrobInt with leading zeros removed
   *  Note that the sign is preserved if it exists
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
   public BrobInt removeLeadingZeros( BrobInt bint ) {
      Character sign = null;
      String returnString = bint.toString();
      int index = 0;

      if( allZeroDetect( bint ) ) {
         return bint;
      }
      if( ('-' == returnString.charAt( index )) || ('+' == returnString.charAt( index )) ) {
         sign = returnString.charAt( index );
         index++;
      }
      if( returnString.charAt( index ) != '0' ) {
         return bint;
      }

      while( returnString.charAt( index ) == '0' ) {
         index++;
      }
      returnString = bint.toString().substring( index, bint.toString().length() );
      if( sign != null ) {
         returnString = sign.toString() + returnString;
      }
      return new BrobInt( returnString );

   }

  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  Method to return a boolean if a BrobInt is all zeros
   *  @param  bint     BrobInt to compare to this
   *  @return boolean  that is true if the BrobInt passed is all zeros, false otherwise
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
   public boolean allZeroDetect( BrobInt bint ) {
      for( int i = 0; i < bint.toString().length(); i++ ) {
         if( bint.toString().charAt(i) != '0' ) {
            return false;
         }
      }
      return true;
   }

  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  Method to display an Array representation of this BrobInt as its bytes
   *  @param   d  byte array from which to display the contents
   *  NOTE: may be changed to int[] or some other type based on requirements in code above
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
   public void toArray( byte[] d ) {
      System.out.println( "Array contents: " + Arrays.toString( d ) );
   }

  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  The main method contains tests for each method.
   *  @param  args  String array which contains command line arguments
   *  NOTE:  we don't really care about these, since we test the BrobInt class with the BrobIntTester
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
   public static void main( String[] args ) {
      System.out.println( "\n  Hello, world, from the BrobInt program!!\n" );
      System.out.println( "\n  You should run your tests from the BrobIntTester...\n" );
      BrobInt b1 = null;
      try { System.out.println( "   Making a new BrobInt: " ); b1 = new BrobInt( "147258369789456123" ); }
      catch( Exception e ) { System.out.println( "        Exception thrown:  " ); }
      try { System.out.println( "   expecting: 147258369789456123\n     and got: " + b1.toString() ); }
      catch( Exception e ) { System.out.println( "        Exception thrown:  " ); }

      System.out.println( "\n    Adding 83746 to 7: ");
      try { System.out.println( "      expecting: 83753\n        and got: " + new BrobInt("83746").add( BrobInt.SEVEN ) ); }
      catch( Exception e ) { System.out.println( "        Exception thrown: " + e.toString() ); }

      System.out.println( "\n    Adding 83746 to 7324: ");
      try { System.out.println( "      expecting: 91070\n        and got: " + new BrobInt("83746").add( new BrobInt("7324") ) ); }
      catch( Exception e ) { System.out.println( "        Exception thrown: " + e.toString() ); }

      System.out.println( "\n    Adding -83746 to 7324: ");
      try { System.out.println( "      expecting: -76422\n        and got: " + new BrobInt("-83746").add( new BrobInt("7324") ) ); }
      catch( Exception e ) { System.out.println( "        Exception thrown: " + e.toString() ); }

      System.out.println( "\n    Subtracting 35 and 152: ");
      try { System.out.println( "      expecting: -117\n        and got: " + new BrobInt("35").subtract( new BrobInt("152") ) ); }
      catch( Exception e ) { System.out.println( "        Exception thrown: " + e.toString() ); }

      System.out.println( "\n    Subtracting 34 and 6: ");
      try { System.out.println( "      expecting: 28\n        and got: " + new BrobInt("34").subtract( BrobInt.SIX ) ); }
      catch( Exception e ) { System.out.println( "        Exception thrown: " + e.toString() ); }

      System.out.println( "\n    Subtracting 000001 and 28798: ");
      try { System.out.println( "      expecting: -28797\n        and got: " + new BrobInt("000001").subtract( new BrobInt("28798") ) ); }
      catch( Exception e ) { System.out.println( "        Exception thrown: " + e.toString() ); }

      System.out.println( "\n    Multiplying 823 by 3: " );
      try { System.out.println( "      expecting: 2469\n        and got: " + new BrobInt("823").multiply( BrobInt.THREE ) ); }
      catch( Exception e ) { System.out.println( "        Exception thrown:  " + e.toString() ); }

      System.out.println( "\n    Multiplying 823 by 21: " );
      try { System.out.println( "      expecting: 17283\n        and got: " + new BrobInt("823").multiply( new BrobInt("21") ) ); }
      catch( Exception e ) { System.out.println( "        Exception thrown:  " + e.toString() ); }

      System.out.println( "\n    Multiplying -2689 by 7318: " );
      try { System.out.println( "      expecting: -19678102\n        and got: " + new BrobInt("-2689").multiply( new BrobInt("7318") ) ); }
      catch( Exception e ) { System.out.println( "        Exception thrown:  " + e.toString() ); }

      System.out.println( "\n    Dividing 342 by 6: " );
      try { System.out.println( "      expecting: 57\n        and got: " + new BrobInt("342").divide( BrobInt.SIX ) ); }
      catch( Exception e ) { System.out.println( "        Exception thrown:  " + e.toString() ); }

      System.out.println( "\n    Dividing 1056 by 5: " );
      try { System.out.println( "      expecting: 211\n        and got: " + new BrobInt("1056").divide( BrobInt.FIVE ) ); }
      catch( Exception e ) { System.out.println( "        Exception thrown:  " + e.toString() ); }

      System.out.println( "\n    Dividing 9876543 by 1234567888: " );
      try { System.out.println( "      expecting: 0\n        and got: " + new BrobInt("9876543").divide( new BrobInt("1234567888") ) ); }
      catch( Exception e ) { System.out.println( "        Exception thrown:  " + e.toString() ); }

      System.out.println( "\n    Finding 9876545 modulo 5: " );
      try { System.out.println( "      expecting: 0\n        and got: " + new BrobInt("9876545").remainder( new BrobInt("5") ) ); }
      catch( Exception e ) { System.out.println( "        Exception thrown:  " + e.toString() ); }

      System.out.println( "\n    Finding 4557 modulo 8: " );
      try { System.out.println( "      expecting: 5\n        and got: " + new BrobInt("4557").remainder( new BrobInt("8") ) ); }
      catch( Exception e ) { System.out.println( "        Exception thrown:  " + e.toString() ); }
   }
}
