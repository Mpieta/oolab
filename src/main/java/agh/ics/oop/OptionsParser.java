package agh.ics.oop;

public class OptionsParser {



    public static MoveDirection[] parse(String[] str) {
        int i = 0;
        int unknown_cnt = 0;
        MoveDirection[] dirArr = new MoveDirection[str.length];
        for(String s: str){
            switch(s) {
                case "f" -> dirArr[i] = MoveDirection.FORWARD;
                case "r" -> dirArr[i] = MoveDirection.RIGHT;
                case "l" -> dirArr[i] = MoveDirection.LEFT;
                case "b" -> dirArr[i] = MoveDirection.BACKWARD;
                case "forward" -> dirArr[i] = MoveDirection.FORWARD;
                case "backward" -> dirArr[i] = MoveDirection.BACKWARD;
                case "left" -> dirArr[i] = MoveDirection.LEFT;
                case "right" -> dirArr[i] = MoveDirection.RIGHT;
                default -> {
                    dirArr[i] = MoveDirection.UNKNOWN;
                    unknown_cnt++;
                }
            }
            i++;
        }

        MoveDirection[] ret = new MoveDirection[str.length-unknown_cnt];
        i=0;
        for(MoveDirection mv: dirArr) {
            if(mv != MoveDirection.UNKNOWN) {
                ret[i] = mv;
                i++;
            }
        }

        return ret;



    }
}
