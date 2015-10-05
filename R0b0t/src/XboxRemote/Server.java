package XboxRemote;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import Data.Data;

public class Server {
	
	ServerSocket ss;
	
	Socket s;
	
	public Server(int port){
		try {
			ss = new ServerSocket(port); //bricht ab, wenn Port belegt ist
			s = ss.accept();
			System.out.println("connected");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	void sendTcp(Data d){
		try {
			if (s.getOutputStream() != null){
				ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
				oos.writeObject(d);
				oos.flush(); //wirklich abschicken
				try {
					Thread.sleep(5); 
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	void close(){
		try {
			s.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
