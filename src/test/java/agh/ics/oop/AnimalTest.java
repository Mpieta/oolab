package agh.ics.oop;

import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

class AnimalTest {

    @Test
    void parseTest() {
        String[][] input = {
                {"f", "l","r","b"},
                {"forward", "left", "right","backward"},
                {"F","L","R","B", "f"},
                {"FORWARD","LEFT","RIGHT","BACK", "f", "forward", "left", "f", "f"},
                {"fo","le","ri","ba"},
                {"xyz","f","forward","abcforward","xyzleft","left"}
        };

        MoveDirection[][] ans = {
                {MoveDirection.FORWARD, MoveDirection.LEFT, MoveDirection.RIGHT, MoveDirection.BACKWARD},
                {MoveDirection.FORWARD, MoveDirection.LEFT, MoveDirection.RIGHT, MoveDirection.BACKWARD},
                {MoveDirection.FORWARD},
                {MoveDirection.FORWARD, MoveDirection.FORWARD, MoveDirection.LEFT, MoveDirection.FORWARD, MoveDirection.FORWARD},
                {},
                {MoveDirection.FORWARD,MoveDirection.FORWARD,MoveDirection.LEFT}

        };

        for(int i = 0;i< input.length;i++){
            MoveDirection[] output = new OptionsParser().parse(input[i]);
            assertEquals(output.length, ans[i].length);
            for(int j = 0;j<output.length;j++) {
                assertEquals(output[j], ans[i][j]);
            }
        }

    }
    @Test
    void moveDirectionTest() {
        String[][] input = {
                {"f", "f"},
                {"b", "b"},
                {"l", "l", "l", "r", "l", "r", "r", "l"},
                {"l", "l", "f", "f", "b"},
                {"l", "f", "f", "l", "f"},
                {"r", "f", "f", "r", "f", "r"},
                {"l", "b", "b", "r", "r"}
        };

        Vector2d[] pos = {
                new Vector2d(2,4),
                new Vector2d(2,0),
                new Vector2d(2,2),
                new Vector2d(2,1),
                new Vector2d(0,1),
                new Vector2d(4,1),
                new Vector2d(4,2)
        };

        MapDirection[] dir = {
                MapDirection.NORTH,
                MapDirection.NORTH,
                MapDirection.SOUTH,
                MapDirection.SOUTH,
                MapDirection.SOUTH,
                MapDirection.WEST,
                MapDirection.EAST
        };

        for(int i = 0;i<input.length;i++) {
            Animal a = moveInput(input[i]);
            assertTrue(a.isAt(pos[i]));
            assertTrue(a.isFacing(dir[i]));
        }

    }

    @Test
    void outOfMapTest() {
        String[][] input = {
                {"f", "f", "f"},
                {"l", "f", "f", "f"},
                {"r", "f", "f", "f"},
                {"b","b","b"},
                {"f", "f", "f", "f", "f", "f", "f", "r", "f", "f", "f", "f"},
                {"b","b","b","b","b","b","l","f", "f", "f", "f","l","f", "f","r"},
                {"f", "f","f", "f","l", "f", "f","f", "f","r", "f", "f","l", "l"},
                {"b", "b","b", "b","l", "b", "b","b", "b", "l", "f", "f", "f"}
        };

        Vector2d[] pos = {
                new Vector2d(2,4),
                new Vector2d(0,2),
                new Vector2d(4,2),
                new Vector2d(2,0),
                new Vector2d(4,4),
                new Vector2d(0,0),
                new Vector2d(0,4),
                new Vector2d(4,0)
        };

        MapDirection[] dir = {
                MapDirection.NORTH,
                MapDirection.WEST,
                MapDirection.EAST,
                MapDirection.NORTH,
                MapDirection.EAST,
                MapDirection.WEST,
                MapDirection.SOUTH,
                MapDirection.SOUTH
        };

        for(int i = 0;i<input.length;i++) {
            Animal a = moveInput(input[i]);
            assertTrue(a.isAt(pos[i]));
            assertTrue(a.isFacing(dir[i]));
        }
    }

    static Animal moveInput(String[] inp) {
        Animal a1 = new Animal();
        MoveDirection[] in = new OptionsParser().parse(inp);
        for(MoveDirection mv: in) {
            a1.move(mv);
        }

        return a1;
    }


}