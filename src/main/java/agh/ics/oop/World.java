package agh.ics.oop;



public class World {

    public static Direction[] convert(String[] args)
    {
        Direction[] dirArr = new Direction[args.length];
        int i = 0;
        for(String s: args)
        {
            switch (s)
            {
                case "f" -> dirArr[i] = Direction.FORWARD;
                case "r" -> dirArr[i] = Direction.RIGHT;
                case "l" -> dirArr[i] = Direction.LEFT;
                case "b" -> dirArr[i] = Direction.BACKWARD;
                default -> dirArr[i] = Direction.UNKNOWN;
            }
            i=i+1;
        }

        return dirArr;
    }
    public static void main(String[] args)
    {
        System.out.println("system wystartowal");
        run(convert(args));
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
                    System.out.println("Zwierzak idzie do tylu");
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
