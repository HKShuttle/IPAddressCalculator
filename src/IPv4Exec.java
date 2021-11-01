import old.Message;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.stream.Stream;

public class IPv4Exec {
    static String inputData;
    public static void main(String[] args) {
        String inputIPv4Address;
        int inputPrefixLength;

        if(args.length != 0){
            // address receive from argument
            inputData = args[0];
        } else {
            // address receive from stdin
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
            if (dialogue(bufferedReader)) {
                return;
            }
        }

        String[] splitPrefix = inputData.split("/", 2);
        inputIPv4Address = splitPrefix[0];
        try{
            inputPrefixLength = Integer.parseInt(splitPrefix[1]);
        } catch (NumberFormatException e){
            System.out.println("Error: Prefix is NaN");
            return;
        }

        // check prefix range
        if (inputPrefixLength < 1 || inputPrefixLength > 30) {
            System.out.println("Error: Invalid Prefix Length");
            return;
        }

        IPv4 iPv4 = new IPv4(inputIPv4Address, inputPrefixLength);
        System.out.println("IPv4 Address: " + buildAddress(iPv4.getAddress()));
        System.out.println("Prefix Length: " + iPv4.getPrefix());

        // show subnet mask
        System.out.println("Subnet Mask: " + buildAddress(iPv4.getNetMask()));

        // snow network address
        System.out.println("Network Address: " + buildAddress(iPv4.getNetworkAddress()));

        // calculate and show broadcast address
        System.out.println("Broadcast Address: " + buildAddress(iPv4.getBroadcastAddress()));

        // show number of hosts
        int numberOfHosts = ~iPv4.getNetMask() - 1;
        System.out.println("Number of Hosts: " + numberOfHosts);
    }

    private static boolean dialogue(BufferedReader bufferedReader) {
        try {
            System.out.println("Input IPv4 Address and Prefix.");
            System.out.println("e.g.) 133.17.0.1/24");
            inputData = bufferedReader.readLine();
        } catch (Exception e) {
            System.out.println("Error");
            return true;
        }
        return false;
    }

    private static String buildAddress(int address) {
        return (address >>> 24) + "." + ((address >>> 16) & 0xff) + "." +
                ((address >>> 8) & 0xff) + "." + (address & 0xff);
    }
}
