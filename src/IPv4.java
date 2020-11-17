public class IPv4 {
    private int address;
    private int prefix;
    private int netMask;

    IPv4(int address, int prefix) {
        this.address = address;
        this.prefix = prefix;
        calculateNetMask();
    }

    public void setAddress(int address) {
        this.address = address;
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
        return address & netMask + ~netMask;
    }

    private void calculateNetMask() {
        netMask = 1;
        for (int i = 0; i < prefix - 1; i++) {
            netMask = netMask << 1;
            netMask += 1;
        }
        for (int i = 0; i < 24 - prefix; i++) {
            netMask = netMask << 1;
        }
    }
}
