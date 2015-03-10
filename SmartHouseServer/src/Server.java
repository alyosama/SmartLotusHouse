import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server implements Runnable {
	private ServerSocket serverSocket;
	private Socket clientSocket;
	int portNumber = 4000;
	private DataInputStream inStream;
	private DataOutputStream outStream;
	GUI window = null;
	public Server(GUI window) {
		this.window = window;
		try {
			serverSocket = new ServerSocket(portNumber);
			window.txtLog.append("Port Established\n");
			System.out.println();
		} catch (IOException e) {
			window.txtLog.append("Port Failed\n");
		}
	}
	public void run() {
		try {
			window.txtLog.append("Waiting for Connection\n");
			clientSocket = serverSocket.accept();
			window.txtLog.append("Connection established\n");
			inStream = new DataInputStream(clientSocket.getInputStream());
			outStream = new DataOutputStream(clientSocket.getOutputStream());
		} catch (IOException e) {
			window.txtLog.append("Connection Failed\n");
		}
		while (true) {
			try {
				int dataIn=inStream.read();
				window.setDataReceivedFromClient(dataIn);
				window.txtLog.append("Client Sent "+dataIn+"\n");
				System.out.println();
				outStream.writeUTF("Connection established");
			} catch (IOException e) {
				window.txtLog.append("Stream Failed\n");
				break;
			}
		}
		
	}
	public void close(){
		try {
//			clientSocket.close();
			window.txtLog.append("Connection Exit\n");
		} catch (Exception e) {
			window.txtLog.append("Connection Exit Failed\n");
		}
	}
}
