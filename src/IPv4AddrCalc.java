import java.io.BufferedReader;
import java.io.InputStreamReader;

public class IPv4AddrCalc {
    public static void main(String[] args) {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        // address receive from stdin
        String inputIPv4Address;
        int inputPrefixLength;
        try {
            System.out.println("Input IPv4 Address.");
            System.out.println("e.g.) 133.17.0.1");
            inputIPv4Address = bufferedReader.readLine();
            System.out.println("Input IPv4 Address Prefix.(Integer Only)");
            String buf = bufferedReader.readLine();
            inputPrefixLength = Integer.parseInt(buf);
        } catch (NumberFormatException e){
            System.out.println("Prefix seems not a number!");
            System.out.println("This Program will be Finish.");
            return;
        } catch (Exception e){
            System.out.println("An Exception has Occurred!");
            System.out.println("This Program will be Finish.");
            return;
        }

        System.out.println("IPv4 Address is " + inputIPv4Address);
        System.out.println("Prefix Length is " + inputPrefixLength);

        // implement address parser
        String[] parsedIPv4Address;
        parsedIPv4Address = inputIPv4Address.split("\\.", 4);

        // temporary
        for (String iPv4Address : parsedIPv4Address) {
            System.out.println(iPv4Address);
        }
    }
}
