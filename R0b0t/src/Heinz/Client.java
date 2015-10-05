package Heinz;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import Data.Data;

public class Client { // Handels the Networking Client

	Socket s;
	String ip;
	Heinz heinz;

	// List<Data> stack;

	Thread t = new Thread() {
		@Override
		public synchronized void start() {
			while (true) {
				try {
					if ((s.getInputStream() != null)
							&& (s.getInputStream().available() > 0)) {

						// stack.add((Data) new
						// ObjectInputStream(s.getInputStream()).readObject());

						heinz.processData((Data) new ObjectInputStream(s
								.getInputStream()).readObject());//lesen und verarbeiten der Internet Daten.

					}
				} catch (IOException e) {
					e.printStackTrace();
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
			}
		}
	};

	public Client(String ip, int port, Heinz heinz) {
		this.heinz = heinz;
		this.ip = ip;
		try {
			s = new Socket(ip, port);
			System.out.println("Connected: " + s.isConnected());
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		// stack = new ArrayList<Data>();
		t.start();
	}

	public boolean isConnected() {
		return s.isClosed();
	}

}
