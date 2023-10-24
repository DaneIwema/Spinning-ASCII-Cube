public class Cube {
    public static double cubeSize = 5;

    public static int height = 20; 
    public static int width = 40;

    public static int [] center = {width/2, height/2};

    public static int [][] corners = new int [4][2];

    public static char [] faces = {'@', '#', '$', '%', '&', '*'};

    public static String [][] display = new String[width][height];

    public static void main(String [] args){
        initializeCorners();
        drawCorners();
        display[center[0]][center[1]] = "0";
        drawLine(center[0], center[1], corners[0][0], corners[0][1]);
        // drawLine(corners[1][0], corners[1][1], corners[2][0], corners[2][1]);
        System.out.print(toString(display));
        System.out.printf("Center: %d, %d%n", center[0], center[1]);
        System.out.printf("point 1: %d, %d%n", rotateX(center[0], 45, cubeSize), rotateY(center[1], 45, cubeSize));
        System.out.printf("point 2: %d, %d%n", rotateX(center[0], 135, cubeSize), rotateY(center[1], 135, cubeSize));
        System.out.printf("Distance: %d%n", distance(corners[1][0], corners[1][1], corners[2][0], corners[2][1]));
        System.out.printf("angle: %d%n", (int)getAngle(corners[1][0], corners[1][1], corners[2][0], corners[2][1]));
    }

    public static void initializeCorners(){
        int j = 45;
        for (int i = 0; i < 4; i++){
            corners[i][0] = rotateX(center[0], j, cubeSize);
            corners[i][1] = rotateY(center[1], j, cubeSize);
            j = j + 90;
        }
    }

    public static void drawCorners(){
        for (int i = 0; i < 4; i++){
            StringBuilder builder = new StringBuilder();
            builder.append(i);
            display[corners[i][0]][corners[i][1]] = builder.toString();
        }
    }

    public static void drawLine(int xOne, int yOne, int xTwo, int yTwo){
        if (xOne < xTwo && yOne < yTwo){

        }
        else{
            
        }
        int m = (yTwo - yOne) / (xTwo - xOne); 
        for (int x = xOne; x <= xTwo; x++){ 
            
            // Assuming that the round function finds 
            // closest integer to a given float. 
            int y = Math.round((m*x) + c); 
            display[x][y] = "x"; 
        } 
    }

    public static int rotateX(int xC, int rotateAmount, double radius){
        return xC+ (int)Math.round((radius*2) * Math.cos(Math.toRadians(rotateAmount)));
    }

    public static int rotateY(int yC, int rotateAmount, double radius){
        return yC + (int)Math.round(radius * Math.sin(Math.toRadians(rotateAmount)));
    }

    public static int getAngle(int x, int y, int xTwo, int yTwo){
        return (int)Math.round(Math.atan2(yTwo - y, xTwo - x) * 180 / Math.PI);
    }

    public static int distance(int xOne, int yOne, int xTwo, int yTwo){
        return (int)Math.round(Math.sqrt(((Math.pow((xTwo - xOne), 2)) + (Math.pow((yTwo - yOne), 2)))));
    }

    public static String toString(String [][] display){
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
            builder.append(i);
            builder.append("\n");
        }
        return builder.toString();
    }
}
