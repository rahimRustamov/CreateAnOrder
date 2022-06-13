import java.io.*;
import java.util.*;
public class readFromFile {
    public static List<String[]> readFromCSV(String str) throws IOException{
        List<String[]> result = new ArrayList<>();
        String path = "C:\\Users\\Rahim\\Documents\\SQAE\\OrderCreation\\src\\MOCK_DATA.csv";
        try {
            BufferedReader br = new BufferedReader(new FileReader(path));
            String strConv;
            while ((strConv = br.readLine()) != null) {
                String[] eachrow = strConv.split(",");
                result.add(eachrow);
            }
        }
        catch (IOException e){
            System.out.println("Exception");
        }
        return result;
    }
}
