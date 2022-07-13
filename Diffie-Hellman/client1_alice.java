import java.util.*;
import java.net.*;
import java.io.*;
public class client1_alice {
public static void main(String[] args) {
String key;
try {
while (true) {
Socket cA = new Socket("localhost", 6666);
DataInputStream dis = new
DataInputStream(cA.getInputStream());
DataOutputStream dos = new
DataOutputStream(cA.getOutputStream());

try (Scanner sc = new Scanner(System.in)) {
    System.out.println("This is client 1 - alice ");
    System.out.println("Select No. n and g : ");
    int n = sc.nextInt();
    int g = sc.nextInt();
    dos.writeInt(n);
    dos.writeInt(g);
    System.out.println("Select Random No. a");
    int a = sc.nextInt();
    int A = ((int) Math.pow(g, a)) % n;
    System.out.println("Private key for ALice is A: " + A);
    dos.writeInt(A);
    
    int B = dis.readInt();
    System.out.println("B recieved from person: " + B);
    int k1 = ((int) Math.pow(B, a)) % n;
    System.out.println("K1: " + k1);
    System.out.println("\n\nPress any key to exit");
    key = sc.next();
}
dos.writeInt(-1);
cA.close();
}
} catch (SocketException e) {
System.exit(0);
} catch (Exception e) {
System.out.println(e);
}
}
}
