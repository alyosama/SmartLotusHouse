package com.smarthouseclient;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client  {
	Socket clientSocket;
	String IP="192.168.1.5";
	int portNumber=4000;
	DataOutputStream outStream;
	DataInputStream inStream;
	public Client(String IP){
		this.IP=IP;
		try {
			clientSocket= new Socket(IP,portNumber);
			outStream= new DataOutputStream(clientSocket.getOutputStream());
			inStream= new DataInputStream(clientSocket.getInputStream());
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	public void sendDataToServer(int data){
		try {
			outStream.write(data);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void close() {
		try{
		outStream.close();
		inStream.close();
		clientSocket.close();
		}
		catch(Exception ex){
			
		}
	}

}
