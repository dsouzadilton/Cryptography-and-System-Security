import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.net.SocketException;
import java.util.Scanner;
public class client2_bob {
public static void main(String[] args) {
String key;
try {
while (true) {
Socket c = new Socket("localhost", 8080);
DataInputStream dis = new
DataInputStream(c.getInputStream());

DataOutputStream dos = new
DataOutputStream(c.getOutputStream());

Scanner sc = new Scanner(System.in);
int n = dis.readInt();
int g = dis.readInt();
System.out.println("This is client 2 - bob ");
System.out.println("n: " + n + " & g: " + g);
System.out.println("Select Random No. b");
int b = sc.nextInt();
int B = ((int) Math.pow(g, b)) % n;
System.out.println("Private key for Bob is B: " + B);
dos.writeInt(B);
int A = dis.readInt();
System.out.println("A recieved from person: " + A);
int k2 = ((int) Math.pow(A, b)) % n;
System.out.println("K2: " + k2);
System.out.println("\n\nPress any key to exit");
key = sc.next();
dos.writeInt(-1);
c.close();
}
} catch (SocketException e) {
System.exit(0);
} catch (Exception e) {
System.out.println(e);
}
}
}
