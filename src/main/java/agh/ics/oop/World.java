package agh.ics.oop;


public class World {

    public static void main(String[] args)
    {
        System.out.println("system wystartowal");
        Direction[] strArr = new Direction[args.length];
        int i = 0;
        for(String s: args)
        {
            switch (s)
            {
                case "f" -> strArr[i] = Direction.FORWARD;
                case "r" -> strArr[i] = Direction.RIGHT;
                case "l" -> strArr[i] = Direction.LEFT;
                case "b" -> strArr[i] = Direction.BACKWARD;
            }
            i=i+1;
        }
        run(strArr);
        System.out.println("system zakonczyl dzialanianie");
    }

    public static void run(Direction[] directions)
    {
        for(Direction d: directions)
        {
            switch(d)
            {
                case FORWARD:
                {
                    System.out.println("Zwierzak idzie do przodu");
                    break;
                }

                case BACKWARD:
                {
                    System.out.println("Zwierzak idzie do tyłu");
                    break;
                }

                case RIGHT:
                {
                    System.out.println("Zwierzak idzie w prawo");
                    break;
                }

                case LEFT:
                {
                    System.out.println("zwierzak idzie w lewo");
                }

                default: break;
            }
        }

    }
}
