package com.neighborpil.socket;

import java.io.*;
import java.net.*;

public class JavaSocketServer {

	public static void main(String[] args) {
		try {
			int portNumber = 5001;
			
			System.out.println("Starting java server");
			ServerSocket aServerSocket = new ServerSocket(portNumber);
			System.out.println("Listening at port " + portNumber + "...");
			
			while(true) {
				Socket sock = aServerSocket.accept();
				InetAddress clientHost =sock.getLocalAddress();
				int clientPort = sock.getPort();
				System.out.println("A client connected. host : " + clientHost + ", port : " + clientPort);
				//ObjectInputStream instream = new ObjectInputStream(sock.getInputStream());
				DataInputStream instream = new DataInputStream(sock.getInputStream());
				String inMessage = instream.readUTF();
				System.out.println("Input : " + inMessage);
				
				//ObjectOutputStream outstream = new ObjectOutputStream(sock.getOutputStream());
				DataOutputStream outstream = new DataOutputStream(sock.getOutputStream());
				outstream.writeUTF(inMessage + " from Server");
				outstream.flush();
				sock.close();
			}
		}catch(Exception ex) {
			ex.printStackTrace();
		}

	}

}
