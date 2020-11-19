import java.util.stream.Stream;

public class IPv4 {
    private int address;
    private int prefix;
    private int netMask;

    public IPv4(int address, int prefix) {
        setAddress(address);
        setPrefix(prefix);
        calculateNetMask();
    }

    public IPv4(String address, int prefix) {
        setAddress(address);
        setPrefix(prefix);
        calculateNetMask();
    }

    public void setAddress(int address) {
        this.address = address;
    }

    public void setAddress(String address) {
        String[] splitAddress = address.split("\\.", 4);
        int[] parsedAddress = Stream.of(splitAddress).mapToInt(x -> {
            try {
                return Integer.parseInt(x);
            } catch (Exception e) {
                return 0;
            }
        }).toArray();
        this.address = (parsedAddress[0] << 24) + (parsedAddress[1] << 16) + (parsedAddress[2] << 8) + parsedAddress[3];
    }

    public void setPrefix(int prefix) {
        this.prefix = prefix;
        calculateNetMask();
    }

    public int getAddress() {
        return address;
    }

    public int getPrefix() {
        return prefix;
    }

    public int getNetworkAddress() {
        return address & netMask;
    }

    public int getNetMask() {
        return netMask;
    }

    public int getBroadcastAddress() {
        return address | ~netMask;
    }

    private void calculateNetMask() {
        netMask = 1;
        for (int i = 0; i < prefix - 1; i++) {
            netMask = netMask << 1;
            netMask += 1;
        }
        for (int i = 0; i < 32 - prefix; i++) {
            netMask = netMask << 1;
        }
    }
}
