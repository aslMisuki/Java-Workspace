package PA1;

import javax.imageio.ImageIO;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.*; 
import java.net.*;



public class TCPClient{
	
	//header
	String status;
	int bodyByteOffset,bodyByteLength;


	//
	Socket clientSocket;
	String request;
	String response;
	String hostname;
	int port;

	public TCPClient(String hostname, int port) throws UnknownHostException, IOException{
		clientSocket = new Socket(hostname, port); //creates a socket to use
		this.hostname = hostname;
		this.port = port;

		request = "";
		response = "";

	}
	public TCPClient(){ // empty client overload

	}

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
		request = "Redsox.jpg";
		System.out.println("Sending Get Request");
		
		DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
		//send request to server
		System.out.println("Writing request out to server: " + "GET: " +request+ '\n' );
		outToServer.writeBytes("GET "+request+ '\n');
		outToServer.flush();

		System.out.println("Getting InputStream from Server");

		int bSize = 0;
		boolean eoh = false; // boolean for end of file
		String[] lengthArray;

		DataInputStream inFromServer = new DataInputStream(new BufferedInputStream(clientSocket.getInputStream()));
		
		System.out.println(inFromServer.readLine()); // status
		
		while(!eoh){
			eoh = parseHeaderLine(inFromServer.readLine());
		}
		
		Socket peerSocket = new Socket("date,cs.umass.edu",19876);
		
		
		byte[] buffer = new byte[1024*5];
		

		//writing to file
		System.out.println("Writing to .jpg File now");
		FileOutputStream fileOut = new FileOutputStream("Redsox.jpg");

		BufferedOutputStream fileBuffer = new BufferedOutputStream(fileOut);
		System.out.println("Making .jpg File");

		int count;

		while ((count = inFromServer.read(buffer)) != -1) { // while not end of datastream
			fileBuffer.write(buffer, 0, count); // remember that count can be less than buffer.length
			System.out.println("Data Received : " + count);
			fileBuffer.flush();
		}

		fileBuffer.close();
		fileOut.close();
		// just closing the socket
		System.out.println("Closing outToServer");
		outToServer.close();
		System.out.println("Closing inFromServer");
		inFromServer.close();
		System.out.println("Closing clientSocket");
		clientSocket.close();

		//				200 OK		-> status code
		//				BODY_BYTE_OFFSET_IN_FILE: 0 	-> byte off set(always 0 for CS)
		//				BODY_BYTE_LENGTH: 58241			-> byte length ()
		//				\n\n							-> two new lines to separate header from payload
		//				<bytes of body follow here>
		//				

	}
	
	private void computeChecksum(){

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
			mode = "CS";
			ip = "test";
			port = 18765;
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
			System.out.println("Not Implemented Yet!");
			break;
		default:
			System.out.println("wrong mode!");
			break;
		}

	}
}
