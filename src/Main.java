import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // address receive from stdin
        String inputAddress;
        try {
            System.out.println("Input IPv6 Address and Prefix.");
            System.out.println("e.g.) 2001:b68:3700::fc01/56");
            inputAddress = br.readLine();
        } catch (IOException e){
            System.out.println("An Exception has Occurred!");
            System.out.println("This Program will be Finish.");
            return;
        }

        // temporary
        System.out.println(inputAddress);

        int[] internalAddress = new int[8];
        /*
        separate normalized ipv6 address by 16bits
        2001::1 to {8193, 0, 0, 0, 0, 0, 0, 1}
        ::1 to {0, 0, 0, 0, 0, 0, 0, 1}
        2001:db8:: to {8193, 3512, 0, 0, 0, 0, 0, 0}
        */

        // implement ipv6 address parser

    }
}
