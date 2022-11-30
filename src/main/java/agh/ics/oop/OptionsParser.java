package agh.ics.oop;

public class OptionsParser {



    public MoveDirection[] parse(String[] str) throws IllegalArgumentException{
        int i = 0;
        int unknown_cnt = 0;
        MoveDirection[] dirArr = new MoveDirection[str.length];
        for(String s: str){
            switch(s) {
                case "f", "forward":
                    dirArr[i] = MoveDirection.FORWARD;
                    i++;
                    break;
                case "r", "right":
                    dirArr[i] = MoveDirection.RIGHT;
                    i++;
                    break;
                case "l", "left":
                    dirArr[i] = MoveDirection.LEFT;
                    i++;
                    break;
                case "b", "backward":
                    dirArr[i] = MoveDirection.BACKWARD;
                    i++;
                    break;
                default:
                    throw new IllegalArgumentException(s + " is not a legal move specification");

            }
        }

        MoveDirection[] ret = new MoveDirection[str.length-unknown_cnt];
        i=0;
        for(MoveDirection mv: dirArr) {
            ret[i] = mv;
            i++;
        }

        return ret;
    }
}
