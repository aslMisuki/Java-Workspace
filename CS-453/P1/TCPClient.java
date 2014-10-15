package PA1;

import javax.imageio.ImageIO;

import java.awt.Image;
import java.io.*; 
import java.net.*; 



public class TCPClient{

	Socket clientSocket;
	String request; 
	String response;
	byte[] container;
	int blockSize;

	public TCPClient(String hostname, int port) throws UnknownHostException, IOException{
		clientSocket = new Socket(hostname, port); //creates a socket to use
		request = "";
		response = "";

	}

	public void splitHeader(){ // split header by with delimiter: \n\n

	}


	public int getBlockSize(){ //gets the size of the block from the reply header

		return 0;
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

	public void talkToTestServer() throws UnknownHostException, IOException{
		request = "IAM 25004174";

		DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream()); //creates an obj to send
		outToServer.writeBytes(request + '\n'); // sends to the server

		outToServer.close(); // closing server stream
		clientSocket.close();
	}

	public void talkToPlumServer() throws IOException{
		//... I like pears

	}

	public void talkToPearServer() throws IOException{
		// needs to use GET Redsox.jpg\n

		//out
		request = "GET Redsox.jpg";
		
		DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream()); //creates an obj to send

		
		//in
		outToServer.writeBytes(request + '\n'); // sends to the server
		
		BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream())); //buffer for server reply
		
		String header = inFromServer.readLine(); // stores input from server as a string
		inFromServer.readLine(); // extra charage return
		String body = inFromServer.readLine(); // stores the body
		
		
		inFromServer.close();
		outToServer.close();
		clientSocket.close();
		//byte[] serverR = new byte[60000];
		// reads the whole thing >.<
		//response = inFromServer.readLine();  // reads from server buffer

		// probably need to parse this
		//				200 OK		-> status code
		//				BODY_BYTE_OFFSET_IN_FILE: 0 	-> byte off set(always 0 for CS)
		//				BODY_BYTE_LENGTH: 58241			-> byte length ()
		//				\n\n							-> two new lines to separate header from payload
		//				<bytes of body follow here>
		//				
		//				note: for client server, the entire file in the body

		//use this code
		//URL url = new URL("http://www.yahoo.com/image_to_read.jpg"); // delete


		//				ByteArrayOutputStream out = new ByteArrayOutputStream();
		//				
		//				byte[] buf = new byte[60000];
		//				
		//				int n = 0;
		//				while(inFromServer.readLine() != null){
		//					inFromServer.readLine();	
		//					out.write(buf,0,n);
		//					n++;
		//				}

		// while there are still stuff in the bufer...
		//				while (-1 != (n=inFromServer.readLine()))
		//				{
		//				   out.write(buf, 0, n);
		//				}


		//out.close();
		//clientSocket.close();
		//byte[] response = out.toByteArray();

		// saving the image
		//http://stackoverflow.com/questions/5882005/how-to-download-image-from-any-web-page-in-java
		//				FileOutputStream fos = new FileOutputStream("C://borrowed_image.jpg");
		//				fos.write(response);
		//				fos.close();

	}

	public static void main(String args[]) throws Exception {

		//		String mode = args[0]; // will have 3 parts, Mode, IP, Port
		//		String ip = args[1];
		//		int port = Integer.parseInt(args[2]);
		//		TCPClient client = new TCPClient(ip, port);
		//		
		//		if(mode.toUpperCase().equals("CS")){ // client-server
		//			if((	ip.toLowerCase().equals("pear.cs.umass.edu") || 
		//					ip.toLowerCase().equals("plum.cs.umass.edu")) && port == 18765){
		//						
		//				client.talkToPearServer();
		//			}
		//			else if (ip.toLowerCase().equals("date.cs.umass.edu") && port == 20001){
		//				client.talkToTestServer();
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
		TCPClient toTest = new TCPClient("date.cs.umass.edu", 20001); //19876 = UDP port // used to test the server

		toPear.talkToPearServer();

		//toTest.talkToTestServer();
		//toLocal.talkToLocalServer();


	}
}
