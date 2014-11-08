package PA2;

import javax.imageio.ImageIO;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.*; 
import java.net.*;

public class TCPClient{

	//header data
	int bodyByteOffset,bodyByteLength;
	String status;

	String request,response,hostname;
	int port;

	Socket clientSocket;
	Socket peerSocket;

	public TCPClient(String hostname, int port) throws UnknownHostException, IOException{
		clientSocket = new Socket(hostname, port); //creates a socket to use
		this.hostname = hostname;
		this.port = port;

	}
	
	public TCPClient(){} // empty client overload

	// parses the header and returns false if not end of header, and true otherwise
	private boolean parseHeaderLine(String line){

		//		format: 
		//
		//		200 OK
		//		BODY_BYTE_OFFSET_IN_FILE: 20000
		//		BODY_BYTE_LENGTH: 10000
		//
		//		<bytes of body follow here>		

		//assume that status code is already parsed
		String l = line.replace(":", "").toUpperCase();
		String[] spliter = line.split(" ");

		switch(spliter[0]){
		//response to request for torrent metadata
		case "BODY_BYTE_OFFSET_IN_FILE":
			bodyByteOffset = Integer.parseInt(spliter[1]);
			break;
		case "BODY_BYTE_LENGTH":
			bodyByteLength = Integer.parseInt(spliter[1]);
			break;
		case "": // either empty or a carriage return
			return true;
		default:
			return false;
		}
		return false;
	}

	public void talkToLocalServer() throws UnknownHostException, IOException{

		//request segment
		BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in)); // buffer to read user input
		DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream()); //creates an obj to send
		BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream())); //buffer for server reply

		request = inFromUser.readLine();  // reads the client buffer
		outToServer.writeBytes(request + '\n'); // sends to the server
		//request segment end

		response = inFromServer.readLine();  // reads from server buffer
		System.out.println("FROM SERVER: " + response); 

		clientSocket.close();
	}

	public void talkToTestServer() throws UnknownHostException, IOException{
		request = "IAM 25004174";

		DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream()); //creates an obj to send
		outToServer.writeBytes(request + '\n'); // sends to the server

		BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream())); //buffer for server reply

		// closing 
		outToServer.close();
		inFromServer.close();
		clientSocket.close();
	}

	public void talkToPearServer() throws IOException{

		System.out.println("Sending IAM Request");
		
		DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
		DataInputStream inFromServer = new DataInputStream(new BufferedInputStream(clientSocket.getInputStream()));
		
		//contact grading server
		System.out.println("contacting grading server: " + hostname +":"+ port);
		request = "IAM 25004174";
		
		outToServer.writeBytes(request + '\n');
		outToServer.flush();
		
		System.out.println("IAM response: \n" + inFromServer.readLine()); 
		
		//requestig metadata
		request = "Redsox.jpg";
		System.out.println("Opening Socket for MetaData: " + hostname + ":" + 19876);
		
		Socket peerSocket = new Socket(hostname, 19876); // connection refused
		
		System.out.println("Writing request for MetaData: " + "GET: " +request+ ".torrent" + '\n');
		System.out.println("Writing request to " + hostname + ":" + 19876);
		
		
		DataOutputStream outToPeer = new DataOutputStream(peerSocket.getOutputStream());
		DataInputStream inFromPeer = new DataInputStream(new BufferedInputStream(peerSocket.getInputStream()));
		
		System.out.println("GET "+request+".torrent\n");
		outToPeer.writeBytes("GET "+request+".torrent\n");
		outToPeer.flush();

		System.out.println("Getting InputStream(metadata) from Server");
		System.out.println(inFromPeer.readLine());
		
		outToPeer.close();
		inFromPeer.close();
		peerSocket.close();

//		int bSize = 0;
//		boolean eoh = false; // boolean for end of file
//		boolean eOMD = false;
//		MetaData meta = new MetaData(); // calling this multiple times will yield different peers lets start with calling this 3 times
//		String[] lengthArray;    
		
//		while(!eOMD){
//			System.out.println("trying to parse metadata");
//			eOMD = meta.parseMetaData(inFromServer.readLine());
//		}
//		
//		System.out.println(meta.toString());
		
		//TODO: we are going to assume this works, meta data is retrieved and parsed
		
		
		//now we need to store the meta data 
		//status = inFromServer.readLine(); // should be the status


		//		while(!eoh){
		//			eoh = parseHeaderLine(inFromServer.readLine());
		//		}

//		byte[] buffer = new byte[1024*5];
//
//		System.out.println("Writing to .jpg File now");
//		FileOutputStream fileOut = new FileOutputStream("Redsox.jpg");
//
//		BufferedOutputStream fileBuffer = new BufferedOutputStream(fileOut);
//		System.out.println("Making .jpg File");
//
//		int count;
//
//		while ((count = inFromServer.read(buffer)) != -1) { // while not end of file
//
//			fileBuffer.write(buffer, 0, count);
//			System.out.println("Data Received : " + count);
//			fileBuffer.flush();
//		}

		// just closing the socket
		System.out.println("Closing outToServer");
		outToServer.close();
		System.out.println("Closing inFromServer");
		inFromServer.close();
		System.out.println("Closing clientSocket");
		clientSocket.close();		

	}


	private void computeChecksum(){
		// total bytes / 4 = # of words, then we XOR 1st byte of Word1 with word2, word3... etc.
	}

	public static void main(String args[]) throws Exception {

		String run = "IDE"; // "IDE" or "shell" TODO: change to shell for submission
		String mode = "";
		String ip = "";
		int port = -1;
		TCPClient client = new TCPClient();

		switch(run){
		case "shell":
			mode = args[0]; // will have 3 parts, Mode, IP, Port
			ip = args[1];
			port = Integer.parseInt(args[2]);
			client = new TCPClient(ip, port);
			break;
		case "IDE":
			mode = "P2P";
			ip = "grade";
			break;
		default:
			System.out.println("Bad run method");
			break;
		}


		mode = mode.toUpperCase();
		ip = ip.toLowerCase();

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
			switch(ip){
			case "pear.cs.umass.edu" :
			case "plum.cs.umass.edu" :
				if(port == 19876){
					client.talkToPearServer();
				}
				break;
			case "test":
				TCPClient toPear = new TCPClient("pear.cs.umass.edu", 19876);
				toPear.talkToPearServer();
				break;
			case "grade" :
				TCPClient toGrade = new TCPClient("date.cs.umass.edu", 20001);
				toGrade.talkToPearServer();
				break;
			default:
				System.out.println("Wrong IP Address and/or port");
				break;
			}
			break;
		default:
			System.out.println("wrong mode!");
			break;
		}
	}
}
