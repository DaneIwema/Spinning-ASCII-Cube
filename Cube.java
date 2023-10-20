public class Cube {
    public static int cubeSize = 3;

    public static int height = 30; 
    public static int width = 70;

    public static int [] center = {width/2, height/2};

    public static char [] faces = {'@', '#', '$', '%', '&', '*'};

    public static void main(String [] args){
        String [][] display = new String[width][height];
        display[center[0]][center[1]] = "0";
        for (int i = 0; i < 361; i++)
            display[rotateX(i)][rotateY(i)] = "x";
        System.out.print(toString(display));
        System.out.printf("Center: %d, %d%n", center[0], center[1]);
        System.out.printf("point1: %d, %d%n", rotateX(45), rotateY(45));
    }

    public static int rotateX(int rotateAmount){
        return center[0] + ((cubeSize*2) * (int)Math.cos(Math.toRadians(rotateAmount)));
    }

    public static int rotateY(int rotateAmount){
        return center[1] + (cubeSize * (int)Math.sin(Math.toRadians(rotateAmount)));
    }

    // public int drawVector(int x, int y){
        
    // }

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
