import java.util.stream.Stream;

public class IPv4Test {
    public static void main(String[] args) {

        if (args.length <= 0) {
            System.out.println("Execute with Argument!");
            return;
        }

        String[] receivedAddress;
        int receivedPrefix;

        String[] tmp = args[0].split("/", 2);
        receivedAddress = tmp[0].split("\\.", 4);
        receivedPrefix = Integer.parseInt(tmp[1]);

        int[] parsedAddress = Stream.of(receivedAddress).mapToInt(x -> {
            try {
                return Integer.parseInt(x);
            } catch (Exception e) {
                return 0;
            }
        }).toArray();

        int processedAddress = (parsedAddress[0] << 24) + (parsedAddress[1] << 16) + (parsedAddress[2] << 8) + parsedAddress[3];

        IPv4 iPv4 = new IPv4(processedAddress, receivedPrefix);
        showAddress(iPv4.getAddress());
        showAddress(iPv4.getNetworkAddress());
        showAddress(iPv4.getNetMask());
        showAddress(iPv4.getBroadcastAddress());

    }

    static void showAddress(int address) {
        System.out.printf("%d.%d.%d.%d\n",
                address >>> 24,
                (address >>> 16) & 0xff,
                (address >>> 8) & 0xff,
                address & 0xff);
    }
}
