public class Cube {
    public static double cubeSize = 5;

    public static int height = 20; 
    public static int width = 40;

    public static int [] center = {width/2, height/2};

    public static double [][] corners = new double [4][2];

    public static char [] faces = {'@', '#', '$', '%', '&', '*'};

    public static String [][] display;

    public static void main(String [] args){
        display = new String[width][height];
        initializeCorners();
        drawCorners();
        connectCorners();
        System.out.print(toString(display));
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
            display[(int)Math.round(corners[i][0])][(int)Math.round(corners[i][1])] = builder.toString();
        }
    }

    public static void connectCorners(){
        for(int i = 0; i < 4; i++){
            drawLine(corners[i][0], corners[i][1], corners[i+1][0], corners[i+1][1]);
        }
    }

    public static void drawLine(double xOne, double yOne, double xTwo, double yTwo){
        double m = (yTwo - yOne)/(xTwo - xOne);
        double b = yOne - (m*xOne);
        if (xOne < xTwo){
            for (int x = (int)Math.round(xOne); x <= xTwo; x++){ 
                int y = (int)Math.round((m*x) + b); 
                display[x][y] = "x"; 
            } 
        }
        else{
            for (int x = (int)Math.round(xTwo); x <= xOne; x++){ 
                int y = (int)Math.round((m*x) + b); 
                display[x][y] = "x"; 
            } 
        }
        
    }

    public static double rotateX(int xC, int rotateAmount, double radius){
        return xC+ Math.round((radius*2) * Math.cos(Math.toRadians(rotateAmount)));
    }

    public static double rotateY(int yC, int rotateAmount, double radius){
        return yC + Math.round(radius * Math.sin(Math.toRadians(rotateAmount)));
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
            builder.append("\n");
        }
        return builder.toString();
    }

    public static int getAngle(int x, int y, int xTwo, int yTwo){
        return (int)Math.round(Math.atan2(yTwo - y, xTwo - x) * 180 / Math.PI);
    }

    public static int distance(int xOne, int yOne, int xTwo, int yTwo){
        return (int)Math.round(Math.sqrt(((Math.pow((xTwo - xOne), 2)) + (Math.pow((yTwo - yOne), 2)))));
    }
}
