package PA1;

import java.io.*; 
import java.net.*; 

public class TCPClient{
	
	Socket clientSocket;
	String request; 
	String response;
	
	public TCPClient(String hostname, int port) throws UnknownHostException, IOException{
		clientSocket = new Socket(hostname, port); //creates a socket to use
		request = "";
		response = "";
	}
	
	public void talkToLocalServer() throws UnknownHostException, IOException{
 

		BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in)); // buffer for client user

		DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream()); //creates an obj to send

		BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream())); //buffer for server reply

		request = inFromUser.readLine();  // reads the client buffer

		outToServer.writeBytes(request + '\n'); // sends to the server

		response = inFromServer.readLine();  // reads from server buffer

		System.out.println("FROM SERVER: " + response); 

		clientSocket.close();
	}
	
	public void talkToPlumServer() throws IOException{
		// needs to use GET Redsox.jpg\n
		request = "GET Redsox.jpg";
		
		byte serverR;
		DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream()); //creates an obj to send
		
		outToServer.writeBytes(request + '\n'); // sends to the server
		
		BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream())); //buffer for server reply
		
		// reads the whole thing >.<
		response = inFromServer.readLine();  // reads from server buffer
//		200 OK
//		BODY_BYTE_OFFSET_IN_FILE: 0
//		BODY_BYTE_LENGTH: 58241
//		\n\n
//		<bytes of body follow here>
//		
//		note: for client server, the entire file in the body

		clientSocket.close();
		
	}
	
	public void talkToPearServer() throws IOException{
		
	}

	public static void main(String args[]) throws Exception {
		
//		String mode = args[0]; // will have 3 parts, Mode, IP, Port
//		String ip = args[1];
//		int port = Integer.parseInt(args[2]);
//		TCPClient client = new TCPClient(ip, port);
//		
//		if(mode.toUpperCase().equals("CS")){ // client-server
//			if((	ip.toLowerCase().equals("pear.cs.umass.edu") || 
//					ip.toLowerCase().equals("plum.cs.umass.edu") || 
//					ip.toLowerCase().equals("date.cs.umass.edu")) && port == 18765){
//						
//				client.talkToPearServer();
//			}
//			else if(ip.toLowerCase().equals("localhost") && port == 6789){
//				client.talkToLocalServer();
//			}
//			else{
//				System.out.println("Wrong IP Address and/or port");
//			}
//			
//		}
//		else if(mode.toUpperCase().equals("P2P")){
//			System.out.println("Not Implemented Yet!");
//		}
//		else{
//			System.out.println("wrong mode!");
//		}
		
		TCPClient toLocal = new TCPClient ("localhost", 6789);
		TCPClient toPear = new TCPClient("pear.cs.umass.edu", 18765);
		TCPClient toPlum = new TCPClient("plum.cs.umass.edu", 18765);
		TCPClient toTest = new TCPClient("date.cs.umass.edu", 19876); // used to test the server
		
		
		//toLocal.talkToLocalServer();
		

	}
}
