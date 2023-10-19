public class Cube {
    public static int cubeSize = 3;
    public static int height = cubeSize*4, width = cubeSize*8;
    public static int [] center = {height/2, width/2};
    public static char [] faces = {'@', '#', '$', '%', '&', '*'};
    public static int rotateAmount = 180;
    public static double distanceFromCam = 10;
    public static int [][] verticies = {
        {center[0] - cubeSize, center[1] - cubeSize*2}, 
        {center[0] - cubeSize, center[1] + cubeSize*2}, 
        {center[0] + cubeSize, center[1] - cubeSize*2}, 
        {center[0] + cubeSize, center[1] + cubeSize*2}
    };
    public static void main(String [] args){
            String [][] display = new String[height][width];
            display[center[0]][center[1]] = "*";
            for (int i = 0; i < verticies.length; i++){
                display[rotateY(verticies[i][1], verticies[i][0])][rotateX(verticies[i][1], verticies[i][0])] = "*";
            }
            System.out.print(toString(display));
    }

    public static int rotateX(int x, int y){
        return ((center[1]-x)*(int)Math.cos(Math.toRadians(rotateAmount))) - ((center[0]-y)*(int)Math.sin(Math.toRadians(rotateAmount)));
    }

    public static int rotateY(int x, int y){
        return ((center[1]-x)*(int)Math.sin(Math.toRadians(rotateAmount))) - ((center[0]-y)*(int)Math.cos(Math.toRadians(rotateAmount)));
    }

    // public int drawVector(int x, int y){
        
    // }

    public static String toString(String [][] display){
        StringBuilder builder = new StringBuilder();
        for(int i = 0; i < height; i++){
            for (int j = 0; j < width; j ++){
                if (display[i][j] != null)
                    builder.append(display[i][j]);
                else
                    builder.append(" ");
            }
            builder.append("\n");
        }
        return builder.toString();
    }
}
