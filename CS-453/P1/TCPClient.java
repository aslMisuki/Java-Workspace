package PA1;

import javax.imageio.ImageIO;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.*; 
import java.net.*; 



public class TCPClient{
	//options for storing image
	BufferedImage image;
	
	
	//
	Socket clientSocket;
	String request;
	String response;
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

		//request
		outToServer.writeBytes(request + '\n'); // sends to the server
		
		// stream from server
		BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream())); //buffer for server reply
		
		//reading from server buffer
		String header = inFromServer.readLine(); //read header 
		inFromServer.readLine(); // extra charage return
		String body = inFromServer.readLine(); // read the body which is 1 big line
		
		//Saving
		byte[] toSave = body.getBytes(); // convert to array of bytes
		FileOutputStream fos = new FileOutputStream("C:/Redsox.jpg");
		fos.write(toSave);
		fos.close();
		
		inFromServer.close();
		outToServer.close();
		clientSocket.close();


		// probably need to parse this
		//				200 OK		-> status code
		//				BODY_BYTE_OFFSET_IN_FILE: 0 	-> byte off set(always 0 for CS)
		//				BODY_BYTE_LENGTH: 58241			-> byte length ()
		//				\n\n							-> two new lines to separate header from payload
		//				<bytes of body follow here>
		//				

	}

	public static void main(String args[]) throws Exception {

				String mode = args[0]; // will have 3 parts, Mode, IP, Port
				String ip = args[1];
				
				mode = "CS";
				ip = "test";
				
				mode = mode.toUpperCase();
				ip = ip.toLowerCase();
				
				int port = Integer.parseInt(args[2]);
				TCPClient client = new TCPClient(ip, port);
				
				switch(mode){
					case "CS" :
						switch(ip){
							case "pear.cs.umass.edu" :
							case "plum.cs.umass.edu" :
								if(port == 18765){
									client.talkToPearServer();
								}
								break;
							case "date.cs.umass.edu" :
								if(port == 20001){	//19876 = UDP port // used to test the server
									client.talkToTestServer();
								}
								break;
							case "localhost":
								if(port == 6789){
									client.talkToLocalServer();
								}
								break;
							case "test" :
								TCPClient toPear = new TCPClient("pear.cs.umass.edu", 18765);
								toPear.talkToPearServer();
								break;
							default:
								System.out.println("Wrong IP Address and/or port");
								break;
						}
						break;
					case "P2P":
						System.out.println("Not Implemented Yet!");
						break;
					default:
						System.out.println("wrong mode!");
						break;
				}


//		TCPClient toLocal = new TCPClient ("localhost", 6789);
//		TCPClient toPlum = new TCPClient("plum.cs.umass.edu", 18765);
//		TCPClient toTest = new TCPClient("date.cs.umass.edu", 20001); 



		//toTest.talkToTestServer();
		//toLocal.talkToLocalServer();


	}
}
