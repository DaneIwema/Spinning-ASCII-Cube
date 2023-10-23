public class Cube {
    public static double cubeSize = 5;

    public static int height = 20; 
    public static int width = 40;

    public static int [] center = {width/2, height/2};

    public static char [] faces = {'@', '#', '$', '%', '&', '*'};

    public static void main(String [] args){
        String [][] display = new String[width][height];
        display[center[0]][center[1]] = "0";
        display[rotateX(45, cubeSize)][rotateY(45, cubeSize)] = "1";
        display[rotateX(135, cubeSize)][rotateY(135, cubeSize)] = "2";
        display[rotateX(225, cubeSize)][rotateY(225, cubeSize)] = "3";
        display[rotateX(315, cubeSize)][rotateY(315, cubeSize)] = "4";
        System.out.print(toString(display));
        System.out.printf("Center: %d, %d%n", center[0], center[1]);
        System.out.printf("point1: %d, %d%n", rotateX(45, cubeSize), rotateY(45, cubeSize));
        System.out.printf("angle: %d%n", (int)getAngle(rotateX(45, cubeSize), rotateY(45, cubeSize), rotateX(135, cubeSize), rotateY(135, cubeSize)));
    }

    public static void drawLine(int x, int y, int xTwo, int yTwo){
        for(double i = 0; i < distance(x, y, xTwo, yTwo); i++){
            display[rotateX(getAngle(x, y, xTwo, yTwo), i)][] = "x";
        }
    }

    public static int rotateX(int rotateAmount, double radius){
        return center[0] + (int)Math.round((radius*2) * Math.cos(Math.toRadians(rotateAmount)));
    }

    public static int rotateY(int rotateAmount, double radius){
        return center[1] + (int)Math.round(radius * Math.sin(Math.toRadians(rotateAmount)));
    }

    public static double getAngle(int x, int y, int xTwo, int yTwo){
        return Math.atan2(yTwo - y, xTwo - x) * 180 / Math.PI;
    }

    public static int distance(int x, int y, int xTwo, int yTwo){
        return (int)Math.round(Math.sqrt(((xTwo - x)^2) + ((yTwo - y)^2)));
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
