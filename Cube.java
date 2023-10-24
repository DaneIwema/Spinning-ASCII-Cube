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
        drawLine(corners[3], corners[1]);
        // connectCorners();
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
        for(int i = 0; i < 3; i++)
            drawLine(corners[i], corners[i+1]);
        drawLine(corners[3], corners[0]);
    }

    public static void drawLine(double [] coordOne, double [] coordTwo){
        double m = (coordTwo[1] - coordOne[1])/(coordTwo[0] - coordOne[0]);
        double b = coordOne[1] - (m*coordOne[0]);
        if (coordOne[0] < coordTwo[0] && coordOne[1] < coordTwo[1]){
            for (double x = coordOne[0]; x <= coordTwo[0]; x++){ 
                double y = (m*x) + b; 
                display[rti(x)][rti(y)] = "x"; 
            } 
        }
        else if(coordTwo[0] < coordOne[0] && coordTwo[1] < coordOne[1]){
            for (double x = coordTwo[0]; x <= coordOne[0]; x++){ 
                double y = (m*x) + b; 
                display[rti(x)][rti(y)] = "x"; 
            } 
        }
        
    }

    private static void LeftToRight(){

    }

    private static void UpToDown(){
        
    }

    public static int rotateX(int xC, int rotateAmount, double radius){
        return xC + (int)Math.round((radius*2) * Math.cos(Math.toRadians(rotateAmount)));
    }

    public static int rotateY(int yC, int rotateAmount, double radius){
        return yC + (int)Math.round(radius * Math.sin(Math.toRadians(rotateAmount)));
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

    public static int rti(double num){
        return (int)Math.round(num);
    }
}
