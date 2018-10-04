package com.neighborpil.socket;

import java.io.*;
import java.net.Socket;

public class JavaSocketClicent {

	public static void main(String[] args) {
		try {
			int portNumber = 5001;
			Socket sock = new Socket("192.168.0.29", portNumber);
			ObjectOutputStream outstream = new ObjectOutputStream(sock.getOutputStream());
			outstream.writeObject("hello Android");
			outstream.flush();
			
			ObjectInputStream instream = new ObjectInputStream(sock.getInputStream());
			System.out.println(instream.readObject());
			sock.close();
		} catch(Exception ex) {
			ex.printStackTrace();
		}

	}

}
