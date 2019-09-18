public class CountTheDays {

  private static long day1;
  private static long day2;
  private static long month1;
  private static long month2;
  private static long year1;
  private static long year2;

  // constructor
  public CountTheDays (String[] args) {
    month1 = Long.parseLong(args[0]);
    this.day1 = Long.parseLong(args[1]);
    this.year1 = Long.parseLong(args[2]);
    this.month2 = Long.parseLong(args[3]);
    this.day2 = Long.parseLong(args[4]);
    this.year2 = Long.parseLong(args[5]);
  }

  public static void main (String[] args) {
    CountTheDays ctd = new CountTheDays(args);
    long count = CalendarStuff.daysBetween(month1, day1, year1, month2, day2, year2);
  }
}
