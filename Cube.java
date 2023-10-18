public class Cube {
    public static char [] faces = {'@', '#', '$', '%', '&', '*'};
    public static int rotateAmount = 5;
    public static double distanceFromCam = 10;
    public static void main(String [] args){
        while(true){
            StringBuilder stringbuilder = new StringBuilder();
            
        }
    }

    public int rotateX(int x, int y){
        return (x*(int)Math.cos(Math.toRadians(rotateAmount))) - (y*(int)Math.sin(Math.toRadians(rotateAmount)));
    }

    public int rotateY(int x, int y){
        return (x*(int)Math.sin(Math.toRadians(rotateAmount))) - (y*(int)Math.cos(Math.toRadians(rotateAmount)));
    }

    // public int drawVector(int x, int y){

    // }
}
