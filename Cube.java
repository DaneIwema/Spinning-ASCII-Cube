public class Cube {
    public static double cubeSize = 5;

    public static int height = 20; 
    public static int width = 40;

    public static int [] center = {width/2, height/2};

    public static char [] faces = {'@', '#', '$', '%', '&', '*'};

    public static void main(String [] args){
        String [][] display = new String[width][height];
        display[center[0]][center[1]] = "0";
        display[rotateX(45)][rotateY(45)] = "x";
        display[rotateX(135)][rotateY(135)] = "x";
        display[rotateX(225)][rotateY(225)] = "x";
        display[rotateX(315)][rotateY(315)] = "x";
        System.out.print(toString(display));
        System.out.printf("Center: %d, %d%n", center[0], center[1]);
        System.out.printf("point1: %d, %d%n", rotateX(45), rotateY(45));
        System.out.printf("angle: %d%n", (int)getAngle(rotateX(45), rotateY(45), rotateX(135), rotateY(135)));
    }

    public static void drawVector(int x, int y, int xTwo, int yTwo){
        
    }

    public static int rotateX(int rotateAmount){
        return center[0] + (int)Math.round((cubeSize*2) * Math.cos(Math.toRadians(rotateAmount)));
    }

    public static int rotateY(int rotateAmount){
        return center[1] + (int)Math.round(cubeSize * Math.sin(Math.toRadians(rotateAmount)));
    }

    public static double getAngle(int x, int y, int xTwo, int yTwo){
        return Math.atan2(yTwo - y, xTwo - x) * 180 / Math.PI;
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
