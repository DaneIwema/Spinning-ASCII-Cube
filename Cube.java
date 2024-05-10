import java.util.LinkedList;
import java.util.Queue;

public class Cube {
    public static int r = 7;

    public static float A, B, C;

    public static int height = r * 5 + 1;
    public static int width = (int)Math.round(r * 12.5f) + 1;

    public static int posX = width/2;
    public static int posY = height/2;

    public static char [][] buffer;
    public static char [] faces = {'@', '#', '$', '%', '&', '*'};
    public static Queue<int[]> line1;
    public static Queue<int[]> line2;

    public static long now;
    public static long updateTime;
    public static long wait;

    public static int targetFPS = 16;

    public static int [][] cubeVerticies = new int [][] {
        {-r, r, -r},         {r, r, -r},   // 0, 1

            {-r, r, r},        {r, r, r},  // 2, 3

        {-r, -r, -r},       {r, -r, -r},   // 4, 5

            {-r, -r, r},        {r, -r, r} // 6, 7
    };
    public static int[][] cubeFaces = {
        {0, 1, 2, 3}, {4 ,5 ,6 ,7}, {4, 6, 0, 2}, {1, 3, 5, 7}, {2, 3, 6, 7}, {0, 1, 4, 5}
    };
    public static float[][] faceNormals = {

    };

    public static void main(String [] args) throws InterruptedException{
        
        
        wait = 34;
        while (true){

            now = System.currentTimeMillis();
            if (wait > targetFPS) {
                buffer = new char [height][width];
                A += 0.05f;
                B += 0.05f;
                C += 0.01f;
                for (int i = 0; i < 6; i++) {
                    drawFace( cubeFaces[i], faces[i]);
                }
                System.out.print(toDisplay(buffer));
                wait = 0;
            }

            updateTime = System.currentTimeMillis();
            wait += updateTime - now;
        }
    }

    public static int rotateX(int [] v){
        return (int)Math.round(v[1] * Math.sin(A) * Math.sin(B) * Math.cos(C) - v[2] * Math.cos(A) * Math.sin(B) * Math.cos(C) +
         v[1] * Math.cos(A) * Math.sin(C) + v[2] * Math.sin(A) * Math.sin(C) + v[0] * Math.cos(B) * Math.cos(C));
    }

    public static int rotateY(int [] v) {
        return (int)Math.round(v[1] * Math.cos(A) * Math.cos(C) + v[2] * Math.sin(A) * Math.cos(C) -
               v[1] * Math.sin(A) * Math.sin(B) * Math.sin(C) + v[2] * Math.cos(A) * Math.sin(B) * Math.sin(C) -
               v[0] * Math.cos(B) * Math.sin(C));
    }

    /* TODO drawFace method */
    public static void drawFace(int[] face, char color) {

        /* Draws from vec0 to vec1 and vec2 to vec3 */
        int[] vec0 = {rotateX(cubeVerticies[face[0]]) * 2 + posX, rotateY(cubeVerticies[face[0]]) + posY}; //vec0
        int[] vec1 = {rotateX(cubeVerticies[face[1]]) * 2 + posX, rotateY(cubeVerticies[face[1]]) + posY}; //vec1
        int[] vec2 = {rotateX(cubeVerticies[face[2]]) * 2 + posX, rotateY(cubeVerticies[face[2]]) + posY}; //vec2
        int[] vec3 = {rotateX(cubeVerticies[face[3]]) * 2 + posX, rotateY(cubeVerticies[face[3]]) + posY}; //vec3

        line1 = new LinkedList<>();
        line2 = new LinkedList<>();

        drawVector(vec0, vec1, color, line1);
        drawVector(vec2, vec3, color, line2);

        while (!line1.isEmpty() && !line2.isEmpty()) {
            int[] pair1 = line1.poll();
            int[] pair2 = line2.poll();
            drawVector(pair1, pair2, color, null);
        }
    }

    public static void drawVector (int[] pair1, int[] pair2, char color, Queue<int[]> q) {
        int x0 = pair1[0], y0 = pair1[1];
        int x1 = pair2[0], y1 = pair2[1];

        int dx = Math.abs(x1 - x0);
        int sx = x0 < x1 ? 1 : -1;
        int dy = -Math.abs(y1 - y0);
        int sy = y0 < y1 ? 1 : -1;
        int error = dx + dy;
        
        while (true){
            if (q == null) {
                buffer[y0][x0] = color;
            } else {
                int[] pair = {x0, y0};
                q.add(pair);
            }
            if (x0 == x1 && y0 == y1) break;
            int e2 = 2 * error;
            if (e2 >= dy){
                if (x0 == x1) break;
                error = error + dy;
                x0 = x0 + sx;
            }
            if (e2 <= dx){
                if (y0 == y1) break;
                error = error + dx;
                y0 = y0 + sy;
            }
        }
    }

    public static String  toDisplay(char [][] display) {
        StringBuilder builder = new StringBuilder();
        builder.append("\033[2J");
        builder.append("\033[H");
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
               if (buffer[i][j] == '\0')
                    builder.append(" ");
                else
                    builder.append(buffer[i][j]);
            }
            builder.append("\n");
        }
        return builder.toString();
    }
}
