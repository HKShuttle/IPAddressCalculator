public class IPv6 {
    private int prefixLength;
    private long prefixID;
    private long interfaceID;

    public IPv6(long prefixID, long interfaceID, int prefixLength){
        this.prefixID = prefixID;
        this.interfaceID = interfaceID;
        this.prefixLength = prefixLength;
    }

    public IPv6(String address, int prefixLength){
        parseAddress(address);
        this.prefixLength = prefixLength;
    }

    private void parseAddress(String address){
        int[] internalAddress = {0, 0, 0, 0, 0, 0, 0, 0};
        String[] parsedAddress = address.split("::", 2);
        if (!parsedAddress[0].equals("")) {
            String[] tmpAddress = parsedAddress[0].split(":", 8);
            for (int i = 0; i < tmpAddress.length; i++) {
                internalAddress[i] = Integer.parseInt(tmpAddress[i],16);
            }
        }
        if (parsedAddress.length > 1) { // with Zero-Padding
            if (!parsedAddress[1].equals("")) {
                String[] tmpAddress = parsedAddress[1].split(":", 8);
                int count = 0;
                for (int i = 7; i > 7 - tmpAddress.length; i--) {
                    internalAddress[i] = Integer.parseInt(tmpAddress[count], 16);
                    count++;
                }
            }
        }
        prefixID = (internalAddress[0] << 48) + (internalAddress[1] << 32) + (internalAddress[2] << 16) + internalAddress[3];
        interfaceID = (internalAddress[4] << 48) + (internalAddress[5] << 32) + (internalAddress[6] << 16) + internalAddress[7];
    }
}
