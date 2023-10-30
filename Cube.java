public class Cube {
    public static float r = 5.0f;

    public static float A, B, C;

    public static int height = (int)(r * 5) + 1;
    public static int width = (int)Math.round(r*12.5f) + 1;

    public static int x = width/2;
    public static int y = height/2;

    public static String [][] buffer;
    public static float [][] zBuffer;
    public static String [] stringFaces = {"@", "#", "$", "%", "&", "*"};
    public static float [][] verticies;
    public static int [][] faces;
    //cube shape
    public static float [][] cubeVerticies = new float [][] {
        {-r, -r, -r}, {-r, -r, r}, {-r, r, -r}, {-r, r, r},
        {r, -r, -r}, {r, -r, r}, {r, r, -r}, {r, r, r}
    };
    public static int [][] cubeFaces = {
        {0, 1, 2, 3}, {}, {},
        {}, {}, {}
    };

    public static void main(String [] args) throws InterruptedException{
            verticies = cubeVerticies;
            faces = cubeFaces;
        // zBuffer = new float [width][height];
        while (true){
            buffer = new String [width][height];
            A += 0.05f;
            B += 0.05f;
            C += 0.01f;
            for (int i = 0; i < cubeFaces.length; i++) {
                drawVector(
                    (int)Math.round(rotateX(verticies[edges[i][0]])*2) + x,
                    (int)Math.round(rotateY(verticies[edges[i][0]])) + y,
                    (int)Math.round(rotateX(verticies[edges[i][1]])*2) + x,
                    (int)Math.round(rotateY(verticies[edges[i][1]])) + y
                );
            }
            System.out.print(toDisplay(buffer));
            Thread.sleep(30);
        }
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

    public static float rotateZ(float [] v) {//int i, int j, int k
        return (float)(v[2] * Math.cos(A) * Math.cos(B) - v[1] * Math.sin(A) * Math.cos(B) + v[0] * Math.sin(B));
    }

    public static void fill(int [][] face){

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
            buffer[x][y] = "\033[48;2;180;0;158m \033[0m";
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
            buffer[x][y] = "\033[48;2;180;0;158m \033[0m";
            if (D > 0) {
                x = x + xi;
                D = D + (2 * (dx - dy));
            }  
            else
                D = D + 2*dx;
        }
    }

    public static void plotPoint(int x, int y, float z, int face){
        if(zBuffer[x][y] == 0.0f){
            zBuffer[x][y] = z;
            buffer[x][y] = stringFaces[face];
        }
        else if (zBuffer[x][y] < z){

        }
    }

    public static String  toDisplay(String [][] display){
        StringBuilder builder = new StringBuilder();
        builder.append("\033[2J");
        builder.append("\033[H");
        for(int i = height-1; i > -1; i--){
            for (int j = 0; j < width; j++){
                if (display[j][i] != null)
                    builder.append(display[j][i]);
                else
                    builder.append(" ");
            }
            builder.append("\n");
        }
        return builder.toString();
    }
}
