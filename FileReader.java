import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class FileReader {

    /**
     * <p>
     * Reads the file at the URL passed in parameter. The file must be formated as follow for a matrix 3x3 :
     * </p>
     * <p>1,2,3</p>
     * <p>4,5,6</p>
     * <p>7,8,0</p>
     * <p>A comma between each number, a carret-return between each line, no-spaces between a number and a comma</p>
     * @param url The URL of the file in the computer's file system
     * @return The board corresponding to the matrix read in the file
     */
    public static Board readFile(String url){
        try {
            File file = new File(url);
            Scanner sc = new Scanner(file);
            String res = "";
            while(sc.hasNext()){
                res += sc.nextLine() + ',';
            }
            sc.close();
            return new Board(res.substring(0, res.length()-1));
        }
        catch(FileNotFoundException e){
            System.out.println("Error : the file has not been found");
        }
        catch(Exception e){
            System.out.println("Error : the file is not well formated");
        }
        return null;
    }

    public static void writeFile(Board b,String file_name,String dir){
       
        try {
            
            String path = "C:\\Users\\Matthias\\Documents\\GitHub\\AI_Project_Besnier_Romieu_Calvet";
            File d = new File(path + "\\" + dir + "\\" + file_name);
            PrintWriter writer = new PrintWriter(d, "UTF-8");
            String temp = "";
                for (int i=0; i< b.size; i++){
                    temp = temp + b.board[i][0];
                    for(int j=1; j< b.size; j++){
                        temp = temp + "," + b.board[i][j] ;
                    }
                    writer.println(temp);
                    temp = "";
                }
            writer.close();
            } catch (IOException e) {
              System.out.println("An error occurred.");
              e.printStackTrace();
            }
    }
}
