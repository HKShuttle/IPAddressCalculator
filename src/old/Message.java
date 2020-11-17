package old;

public class Message {
    static void finish(){
        System.out.println("This Program will be Finish.");
    }
    static void exception(){
        System.out.println("An Exception has Occurred!");
    }
    static void prefixIsNaN(){
        System.out.println("Prefix seems Not a Number!");
    }
    static void prefixRangeError(){
        System.out.println("Prefix seems a Wrong Value of Range!");
    }
    static void showIPv4Address(int iPv4Address){
        System.out.println("IPv4 Address: " + iPv4Address);
    }
    static void showIPv4Address(String iPv4Address){
        System.out.println("IPv4 Address: " + iPv4Address);
    }
    static void showPrefixLength(int prefixLength){
        System.out.println("Prefix Length: " + prefixLength);
    }
}
