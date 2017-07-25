import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Poloniex {
    public static void main(String[] args) {
        String fileName = "/home/alexml/Downloads/test.txt";

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {

            String line;
            while ((line = br.readLine()) != null) {
                if (line.contains("[\"t\"")){
                    String nLine = line.replaceAll("[]\\[]", "");
                    System.out.println(nLine);
                    String[] split = nLine.split("\"t\"");
                    StringBuilder builder = new StringBuilder();
                    for (int i = 0; i < split.length; i++) {
                        if(i==0){
                            String[] nSplit1 = split[i].split(",");
                            builder.append(nSplit1[0]);
                            i =1;
                        }
                        String aSplit = split[i].replace("\"", "");
                        String[] nSplit = aSplit.split(",");
                        builder.append(",").append(nSplit[1]);
                        builder.append(",").append(nSplit[3]);
                        builder.append(",").append(nSplit[5]);
                        System.out.println(builder.toString());
                        builder.delete(3,builder.length());
                    }
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}