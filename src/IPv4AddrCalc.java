import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.stream.Stream;

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
        } catch (NumberFormatException e) {
            Message.prefixIsNaN();
            Message.finish();
            return;
        } catch (Exception e) {
            Message.exception();
            Message.finish();
            return;
        }

        // check prefix range
        if (inputPrefixLength < 1 || inputPrefixLength > 30) {
            Message.prefixRangeError();
            Message.finish();
        }

        // show input data
        Message.showIPv4Address(inputIPv4Address);
        Message.showPrefixLength(inputPrefixLength);

        // implement address parser
        String[] parsedIPv4Address;
        parsedIPv4Address = inputIPv4Address.split("\\.", 4);

        // address Str to int
        int[] iPv4AddressArray = Stream.of(parsedIPv4Address).mapToInt(x -> {
            try {
                return Integer.parseInt(x);
            } catch (Exception e) {
                return 0;
            }
        }).toArray();

        // convert prefix to subnet mask
        int[] subnetMaskArray = new int[4];
        if(inputPrefixLength <= 8){
            subnetMaskArray[0] = shiftNetMask(inputPrefixLength);
            subnetMaskArray[1] = 0;
            subnetMaskArray[2] = 0;
            subnetMaskArray[3] = 0;
        } else if (inputPrefixLength <= 16){
            subnetMaskArray[0] = 255;
            subnetMaskArray[1] = shiftNetMask(inputPrefixLength - 8);
            subnetMaskArray[2] = 0;
            subnetMaskArray[3] = 0;
        } else if (inputPrefixLength <= 24){
            subnetMaskArray[0] = 255;
            subnetMaskArray[1] = 255;
            subnetMaskArray[2] = shiftNetMask(inputPrefixLength - 16);
            subnetMaskArray[3] = 0;
        } else {
            subnetMaskArray[0] = 255;
            subnetMaskArray[1] = 255;
            subnetMaskArray[2] = 255;
            subnetMaskArray[3] = shiftNetMask(inputPrefixLength - 24);
        }

        // show subnet mask
        System.out.print("Subnet Mask: ");
        for(int var : subnetMaskArray){
            System.out.print(var + ".");
        }
        System.out.println();

        // calculate network address
        int[] networkAddressAllay = new int[4];
        for (int i = 0; i < 4; i++) {
            networkAddressAllay[i] = iPv4AddressArray[i] & subnetMaskArray[i];
        }
        // snow network address
        System.out.print("Network Address: ");
        for(int var : networkAddressAllay){
            System.out.print(var + ".");
        }
        System.out.println();

        // calculate broadcast address of host part
        int[] broadCastAddressOfHostPartArray = new int[4];
        for (int i = 0; i < 4; i++) {
            broadCastAddressOfHostPartArray[i] = 255 - subnetMaskArray[i];
        }
        // calculate and show broadcast address
        int[] broadCastAddressArray = new int[4];
        for (int i = 0; i < 4; i++) {
            broadCastAddressArray[i] = networkAddressAllay[i] + broadCastAddressOfHostPartArray[i];
        }
        System.out.print("Broadcast Address: ");
        for(int var : broadCastAddressArray){
            System.out.print(var + ".");
        }
        System.out.println();

        // show number of hosts
        long numberOfHosts =
                (long) (broadCastAddressOfHostPartArray[0] * Math.pow(2, 24) +
                        broadCastAddressOfHostPartArray[1] * Math.pow(2, 16) +
                        broadCastAddressOfHostPartArray[2] * Math.pow(2, 8) +
                        broadCastAddressOfHostPartArray[3] - 1);
        System.out.println("Number of Hosts: " + numberOfHosts);
    }

    private static int shiftNetMask(int prefixLength) {
        int tmp = 1;
        for (int i = 0; i < prefixLength - 1; i++) {
            tmp = tmp << 1;
            tmp += 1;
        }
        for (int i = 0; i < 8 - prefixLength; i++) {
            tmp = tmp << 1;
        }
        return tmp;
    }
}
