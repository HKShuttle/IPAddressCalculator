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
            System.out.println("e.g.) 2001:db8:3700::fc01/56");
            inputAddress = br.readLine();
        } catch (IOException e){
            System.out.println("An Exception has Occurred!");
            System.out.println("This Program will be Finish.");
            return;
        }

        // split by slash

        // temporary
        System.out.println(inputAddress);

        int[] internalAddress = {0, 0, 0, 0, 0, 0, 0, 0};
        /*
        separate normalized ipv6 address by 16bits
        2001::1 to {8193, 0, 0, 0, 0, 0, 0, 1}
        ::1 to {0, 0, 0, 0, 0, 0, 0, 1}
        2001:db8:: to {8193, 3512, 0, 0, 0, 0, 0, 0}
        */

        // implement ipv6 address parser
        String[] parsedAddress = inputAddress.split("::", 2);
        if (parsedAddress.length > 1) { // with Zero-Padding
            if (!parsedAddress[0].equals("")) {
                String[] tmpAddress = parsedAddress[0].split(":", 8);
                for (int i = 0; i < tmpAddress.length; i++) {
                    internalAddress[i] = Integer.parseInt(tmpAddress[i],16);
                }
            }
            if (!parsedAddress[1].equals("")){
                String[] tmpAddress = parsedAddress[1].split(":", 8);
                int count = 0;
                for (int i = 7; i > 7 - tmpAddress.length; i--) {
                    internalAddress[i] = Integer.parseInt(tmpAddress[count],16);
                    count++;
                }
            }
        }

        // temporary (ugokuka tesuto suru yatsu)
        for(int var : internalAddress){
            System.out.print(var + ":");
        }
        System.out.println();
        System.out.println(parsedAddress.length);
    }
}
