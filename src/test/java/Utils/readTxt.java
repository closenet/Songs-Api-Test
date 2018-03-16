package Utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class readTxt {

    public String readFileToJson(String fileName)
    {
        String txtPath = "./src/test/resources/";
            StringBuilder sb = new StringBuilder();
            BufferedReader br = null;
            try {
                br = new BufferedReader(new FileReader(txtPath + fileName));
                String line;
                while ((line = br.readLine()) != null) {
                    if (sb.length() > 0) {
                        sb.append("\n");
                    }
                    sb.append(line);
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (br != null) {
                        br.close();
                    }
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
            String result = sb.toString();
            System.out.println(result);

        return  result;

    }
}
