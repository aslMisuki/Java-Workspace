import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Map;

/**
 * Created by liberato on 10/25/14.
 */
public class RejectionSampler {

    public static String readEntireFile(File f) {
        FileInputStream fin = null;
        try {
            fin = new FileInputStream(f);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        // tomfoolery relying on '\A' meaning "separate tokens using only the
        // beginning of the input as a boundary"
        java.util.Scanner scanner = new java.util.Scanner(fin,"UTF-8").useDelimiter("\\A");
        return scanner.hasNext() ? scanner.next() : "";
    }

    public static void main(String[] args) {
        String fileContents = readEntireFile(new File(args[0]));
        Map<String, Node> nodeMap = Node.nodesFromString(fileContents);
        System.out.println("Bayes Net:");
        System.out.println(nodeMap);
    }
}
