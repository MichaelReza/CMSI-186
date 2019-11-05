public class SoccerSim {

  private double timer = 0;
  private double timeSlice = 1;
  public Pole[] poles;
  public Ball[] balls;

  public SoccerSim (Ball[] balls, int timeSlice, int numOfPoles) {
    this.balls = balls;
    this.timeSlice = timeSlice;
    this.randomPoleGen(numOfPoles);
  }

  public void randomPoleGen (int poles) {
    // double xPos;
    // double yPos;
    this.poles = new Pole[poles];
    for (int i = 0; i < poles; i++) {
      this.poles[i] = new Pole(Math.random() * 100, Math.random() * 100);
    }
  }

  public void moveBalls () {
    for (int i = 0; i < this.balls.length; i++) {
      this.balls[i].moveBall(this.timeSlice);
    }
    this.timer += this.timeSlice;
  }

  public boolean checkCollision () {
    for (int i = 0; i < this.balls.length; i++) {
      for (int j = 0; j < this.balls.length; j++) {
        if (Math.abs(this.balls[i].xPos - this.balls[j].xPos) <= (2 * this.balls[i].getRadius()) &&
            Math.abs(this.balls[i].yPos - this.balls[j].yPos) <= (2 * this.balls[i].getRadius()) &&
            !this.balls[i].equals(this.balls[j])) {
          System.out.println("\n\n****************************************************");
          System.out.println("Ball 1: " + this.balls[i].toString() + "\nBall 2: " + this.balls[j].toString());
          return true;
        }
      }
    }
    for (int i = 0; i < this.balls.length; i++) {
      for (int j = 0; j < this.poles.length; j++) {
        if (Math.abs(this.balls[i].xPos - this.poles[j].xPos) <= this.balls[i].getRadius() + this.poles[j].radius &&
            Math.abs(this.balls[i].yPos - this.poles[j].yPos) <= this.balls[i].getRadius() + this.poles[j].radius) {
          System.out.println("\n\n****************************************************");
          System.out.println("Ball: " + this.balls[i].toString() + "\nPole: " + this.poles[j].toString());
          return true;
        }
      }
    }
    return false;
  }

  // returns array of balls on the field
  public static Ball[] validateArgsAndSetup (String[] args) {
    if (args.length % 4 == 3) {
      throw new IllegalArgumentException("Please produce a valid number of arguments");
    } else {
      Ball[] balls = new Ball[args.length / 4];
      double[] doubleArgs = convertArgs(args);
      double[] ballArgs = new double[4];
      for (int i = 0; i <= doubleArgs.length - 4; i += 4) {
        for (int j = 0; j < 4; j++) {
          ballArgs[j] = doubleArgs[i + j];
          if ((i + j) >= doubleArgs.length - 1) { break; }
        }
        balls[i / 4] = new Ball(ballArgs);
      }
      return balls;
    }
  }

  public static double[] convertArgs (String[] args) {
    double[] newArgs = new double[args.length];
    for (int i = 0; i < args.length; i++) {
      newArgs[i] = (double)(Double.parseDouble(args[i]));
    }
    return newArgs;
  }

  public String printTimer () {
    return "\nTime: " + Math.floor(this.timer / 60 / 60) +
    ":" + Math.floor((this.timer / 60) % 60) +
    ":" + this.timer % 60;
  }

  private class Pole {

    public double radius = 1.0;
    public double xPos;
    public double yPos;


    public Pole (double x, double y) {
      this.xPos = x;
      this.yPos = y;
    }

    public String toString () {
      return "Pole Position: [ " + this.xPos + ", " + this.yPos + " ]";
    }
  }

  public static void main (String[] args) {
    System.out.println("------ New SoccerSim ------");
    int timeSlice = 1;
    int numOfPoles = 1;
    if (args.length % 4 == 1) { timeSlice = (int)Integer.parseInt(args[args.length - 1]); }
    if (args.length % 4 == 2) { timeSlice = (int)Integer.parseInt(args[args.length - 2]); numOfPoles = (int)Integer.parseInt(args[args.length - 1]); }
    Ball[] balls = validateArgsAndSetup(args);
    SoccerSim field = new SoccerSim(balls, timeSlice, numOfPoles);
    for (int i = 0; i < field.poles.length; i++) {
      System.out.println(field.poles[i].toString());
    }
    System.out.println("\n");
    for (int i = 0; i < field.balls.length; i++) {
      System.out.println(field.balls[i].toString());
    }

    int movingBalls = field.balls.length;
    while ( !field.checkCollision() ) {
      field.moveBalls();
      System.out.println("\n--------------------------------------------");
      System.out.println(field.printTimer());
      for (int i = 0; i < field.balls.length; i++) {
        System.out.println(field.balls[i].toString());
        if (!field.balls[i].isMoving()) { movingBalls--; }
        System.out.println("Num Of Moving Balls: " + movingBalls);
      }
      if (movingBalls == 0) { System.out.println("No Collision!"); System.exit(0); }
    }
    System.out.println("!!!!Collision!!!! " + field.printTimer());
    System.out.println("****************************************************");
  }
}
