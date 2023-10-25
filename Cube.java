public class Cube {
    public static double cubeRad = 10;

    public static double A, B;

    public static int height = (int)(cubeRad*2.5);
    public static int width = (int)cubeRad*5;
    public static int length = (int)cubeRad*5;

    public static int [] center = {width/2, height/2, length/2};

    public static double [][] corners = new double [8][2];

    public static String [] greyScale = {".", ":", "-", "=", "+", "*", "#", "%", "@"};

    public static String [][] display;

    public static void main(String [] args) throws InterruptedException{
        double[] coord = new double[] {0, 0};
        display = new String[width][height];
        display[center[0]][center[1]] = "0";

        for (coord[0] = 1; coord[0] < 180; coord[0] = coord[0] + 2){
            // double slot = (rotateZ(coord));
            // System.out.println(rti(slot / ((cubeRad*2)/greyScale.length)) + 4);
            toDisplay(rotateX(coord),rotateY(coord), rotateZ(coord));
            // System.out.print(toString(display));
            Thread.sleep(50);
        }
    }

    public static void initializeCorners(){
        
    }

    public static void drawLine(double [] coordOne, double [] coordTwo){
        
    }

    public static double rotateX(double [] coord){
        return cubeRad * Math.sin(Math.toRadians(coord[0])) * Math.cos(Math.toRadians(coord[1]));
    }

    public static double rotateY(double [] coord){
        return cubeRad * Math.sin(Math.toRadians(coord[0])) * Math.sin(Math.toRadians(coord[1]));
    }

    public static double rotateZ(double [] coord){
        return cubeRad * Math.cos(Math.toRadians(coord[0]));
    }

    private static void toDisplay(double x, double y, double z){




        int depth = rti(z / ((cubeRad*2)/greyScale.length)) + 4;
        System.out.println(depth+4);
        // display[(center[0] + (rti((x))*2))][center[1] + rti(y)] = greyScale[depth+4];
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
