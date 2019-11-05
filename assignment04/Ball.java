public class Ball {

  private static double radius = 4.45;
  private static double friction = 0.99;
  public double xVel;
  public double yVel;
  public double xPos;
  public double yPos;

  public Ball (double[] args) {
    this.xPos = args[0];
    this.yPos = args[1];
    this.xVel = args[2];
    this.yVel = args[3];
  }

  public void moveBall (double sec) {
    this.xPos += this.xVel * sec;
    this.yPos += this.yVel * sec;
    this.xVel = this.xVel * Math.pow(this.friction, sec);
    this.yVel = this.yVel * Math.pow(this.friction, sec);
  }

  public String toString () {
      return "Ball Position: [ " + this.xPos + " , " + this.yPos + " ]";
  }

  public double getRadius () {
    return this.radius;
  }

  public boolean isMoving () {
    return Math.abs(this.xVel) >= 0.5 || Math.abs(this.yVel) >= 0.5;
  }

  // public static double[] convertArgs (String[] args) {
  //   double[] newArgs = new double[args.length];
  //   for (int i = 0; i < args.length; i++) {
  //     newArgs = Double.parseDouble(args[i]);
  //   }
  //   return newArgs;
  // }
  //
  // public void validateArgs (double[] args) {
  //   for (int i = 0; i < 2; i++) {
  //     if (args[i] < ) {
  //
  //     }
  //   }
  //   throw new InvalidArgumentException("please produce valid ball positioning and velocity");
  // }

  public static void main (String[] args) {
    System.out.println("------ New Soccer Simulation ------");
    // double doubleArgs = convertArgs(args);
    // Ball ball = new Ball(doubleArgs);
    // // double doubleArgs = convertArgs(args);
    // ball.validateArgs(doubleArgs);


  }
}
