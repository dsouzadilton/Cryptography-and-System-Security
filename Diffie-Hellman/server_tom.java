import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;

import java.net.Socket;
import java.net.SocketException;
import java.util.Scanner;
public class server_tom {
public static void main(String Args[]) {
try {
ServerSocket ss1 = new ServerSocket(6666); // connection to client 1

Socket s1 = ss1.accept();
ServerSocket ss2 = new ServerSocket(8080); // connection to client 2

Socket s2 = ss2.accept();
Scanner sc = new Scanner(System.in);
DataInputStream disAlice = new DataInputStream(s1.getInputStream());
DataOutputStream dosAlice = new DataOutputStream(s1.getOutputStream());
DataInputStream disBob = new DataInputStream(s2.getInputStream());
DataOutputStream dosBob = new DataOutputStream(s2.getOutputStream());

int n = disAlice.readInt(); // reading from clietn 1
int g = disAlice.readInt();
dosBob.writeInt(n);
dosBob.writeInt(g);
System.out.println("This is Tom ");
System.out.println("value of n: " + n + " & g: " + g);
System.out.println("Select Random No. C");
int c = sc.nextInt();
int Tom_A = ((int) Math.pow(g, c)) % n;
System.out.println("A: " + Tom_A); // A=g^c mod n
System.out.println("Select Random No. D");
int d = sc.nextInt();
int Tom_B = ((int) Math.pow(g, d)) % n;
System.out.println("B: " + Tom_B);

dosBob.writeInt(Tom_A);
dosAlice.writeInt(Tom_B);
Tom_A = disAlice.readInt();
System.out.println("New A: " + Tom_A);
Tom_B = disBob.readInt();
System.out.println("New B: " + Tom_B);
int k1 = ((int) Math.pow(Tom_B, c)) % n;
System.out.println("K1: " + k1);
int k2 = ((int) Math.pow(Tom_A, d)) % n;
System.out.println("K2: " + k2);
if (disAlice.readInt() == -1) {
s1.close();
ss1.close();
sc.close();
System.exit(0);
}
if (disBob.readInt() == -1) {
s2.close();
ss2.close();
sc.close();
System.exit(0);
}
} catch (SocketException e) {
System.exit(0);
} catch (Exception e) {
System.out.println();
}
}
}
