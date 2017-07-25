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
                        }
                        String aSplit = split[i];
                        String[] nSplit = aSplit.split(",");
                        for (int i1 = 0; i1 < nSplit.length; i1++) {
                            String aNSplit = nSplit[i1];
                            System.out.println(aNSplit);
                        }
                    }
                    System.out.println("New string " + builder.toString());
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
