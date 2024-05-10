public class Cube {
    public static float r = 7.0f;

    public static float A, B, C;

    public static int height = (int)(r * 5) + 1;
    public static int width = (int)Math.round(r * 12.5f) + 1;

    public static int x = width/2;
    public static int y = height/2;

    public static char [] buffer;
    public static char [] faces = {'@', '#', '$', '%', '&', '*'};
    public static int [][] line1;
    public static int [][] line2;

    public static long now;
    public static long updateTime;
    public static long wait;

    public static int targetFPS = 16;

    public static void main(String [] args) throws InterruptedException{
        float [][] cubeVerticies = new float [][] {
            {-r, -r, -r}, {-r, -r, r}, {-r, r, -r}, {-r, r, r},
            {r, -r, -r}, {r, -r, r}, {r, r, -r}, {r, r, r}
        };
        int[][] cubeEdges = {
            {0, 1}, {1, 3}, {3, 2}, {2, 0},
            {4, 5}, {5, 7}, {7, 6}, {6, 4},
            {0, 4}, {1, 5}, {2, 6}, {3, 7}
        };
        
        wait = 34;
        while (true){

            now = System.currentTimeMillis();
            if (wait > targetFPS) {
                buffer = new char [width * height];
                A += 0.05f;
                B += 0.05f;
                C += 0.01f;
                for (int i = 0; i < 12; i++) {
                    drawVector(
                        (int)Math.round(rotateX(cubeVerticies[cubeEdges[i][0]])*2) + x,
                        (int)Math.round(rotateY(cubeVerticies[cubeEdges[i][0]])) + y,
                        (int)Math.round(rotateX(cubeVerticies[cubeEdges[i][1]])*2) + x,
                        (int)Math.round(rotateY(cubeVerticies[cubeEdges[i][1]])) + y
                    );
                }
                System.out.print(toDisplay(buffer));
                wait = 0;
            }

            updateTime = System.currentTimeMillis();
            wait += updateTime - now;
        }
    }

    public float [] rotate(float [] coord){
        float [] newCoord = {rotateX(coord), rotateY(coord), rotateZ(coord)};
        return newCoord;
    }

    public static float rotateX(float [] v){
        return (float)(v[1] * Math.sin(A) * Math.sin(B) * Math.cos(C) - v[2] * Math.cos(A) * Math.sin(B) * Math.cos(C) +
         v[1] * Math.cos(A) * Math.sin(C) + v[2] * Math.sin(A) * Math.sin(C) + v[0] * Math.cos(B) * Math.cos(C));
    }

    public static float rotateY(float [] v) {
        return (float)(v[1] * Math.cos(A) * Math.cos(C) + v[2] * Math.sin(A) * Math.cos(C) -
               v[1] * Math.sin(A) * Math.sin(B) * Math.sin(C) + v[2] * Math.cos(A) * Math.sin(B) * Math.sin(C) -
               v[0] * Math.cos(B) * Math.sin(C));
    }

    public static float rotateZ(float [] v) {
        return (float)(v[2] * Math.cos(A) * Math.cos(B) - v[1] * Math.sin(A) * Math.cos(B) + v[0] * Math.sin(B));
    }

    /* TODO drawFace(int[] face) method */
    public static void drawFace(int[] face) {

        line1 = new int[][2];

        /* Draws from vec0 to vec1 and vec2 to vec3 */
        int x0 = face[0], y0 = face[1]; //vec0
        int x1 = face[2], y1 = face[3]; //vec1
        int x2 = face[4], y2 = face[5]; //vec2
        int x3 = face[6], y3 = face[7]; //vec3

        /* Setup from vec0 to vec1 */
        int dx = Math.abs(x1 - x0);
        int sx = x0 < x1 ? 1 : -1;
        int dy = -Math.abs(y1 - y0);
        int sy = y0 < y1 ? 1 : -1;
        int error = dx + dy;

        /* Setup for vec2 to vec3 */
        int dx2 = Math.abs(x3 - x2);
        int sx2 = x2 < x3 ? 1 : -1;
        int dy2 = -Math.abs(y3 - y2);
        int sy2 = y2 < y3 ? 1 : -1;
        int error2 = dx2 + dy2;
        
        while (true) {

            buffer[y0 * width + x0] = '*';


            if (x0 == x1 && y0 == y1) break;
            int e2 = 2 * error;
            if (e2 >= dy) {
                if (x0 == x1) break;
                error = error + dy;
                x0 = x0 + sx;
            }
            if (e2 <= dx) {
                if (y0 == y1) break;
                error = error + dx;
                y0 = y0 + sy;
            }
        }



    }

    public static void drawVector(int x0, int y0, int x1, int y1){
        if (Math.abs(y1 - y0) < Math.abs(x1 - x0))
            if (x0 > x1)
                plotLineLow(x1, y1, x0, y0);
            else
                plotLineLow(x0, y0, x1, y1);
        else
            if (y0 > y1)
                plotLineHigh(x1, y1, x0, y0);
            else
                plotLineHigh(x0, y0, x1, y1);
    }

    public static void plotLineLow(int x0, int y0, int x1, int y1){
        int dx = x1 - x0;
        int dy = y1 - y0;
        int yi = 1;
        if (dy < 0){
            yi = -1;
            dy = -dy;
        }
        int D = (2 * dy) - dx;
        int y = y0;
        for (int x = x0; x < x1; x++){
            buffer[y*width+x] = '*';
            if (D > 0) {
                y = y + yi;
                D = D + (2 * (dy - dx));
            }
            else
                D = D + 2*dy;
        }
    }

    public static void plotLineHigh(int x0, int y0, int x1, int y1){
        int dx = x1 - x0;
        int dy = y1 - y0;
        int xi = 1;
        if (dx < 0) {
            xi = -1;
            dx = -dx;
        }
        int D = (2 * dx) - dy;
        int x = x0;
        for (int y = y0; y < y1; y++){
            buffer[y*width+x] = '*';
            if (D > 0) {
                x = x + xi;
                D = D + (2 * (dx - dy));
            }  
            else
                D = D + 2*dx;
        }
    }

    public static String  toDisplay(char [] display){
        StringBuilder builder = new StringBuilder();
        builder.append("\033[2J");
        builder.append("\033[H");
        for(int i = 0; i <height*width; i++){
            if(buffer[i]=='\0')
                builder.append(" ");
            else
                builder.append(buffer[i]);
            if ((i + 1)% width == 0)
                builder.append("\n");
        }
        return builder.toString();
    }
}
