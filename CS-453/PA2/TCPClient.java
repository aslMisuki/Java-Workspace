package PA2;

import javax.imageio.ImageIO;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.*; 
import java.net.*;



public class TCPClient{
	
	//header data
	int numBlocks,fileSize,bodyOffset,bodyLength;
	String status;
	
	
	//
	Socket clientSocket;
	String request,response,hostname;
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

	// parses the header and returns the end of header index
	private int parseHead(){
		
		return 0;
	}

	public int getBlockSize(){ //gets the size of the block from the reply header

		return 0;
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

	public void talkToPlumServer() throws IOException{
		request =  "Redsox.jpg";
		// dataoutputstream writes to clientSocket.getoutputstrteam()
		DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
		//send request to server
		outToServer.writeBytes("GET "+request+ '\n');
		// empty stream
		outToServer.flush();

		System.out.println("Getting InputStream from Server");
		DataInputStream inFromServer = new DataInputStream(clientSocket.getInputStream());

		FileOutputStream dos = new FileOutputStream("Redsox.jpg");

		int count = -1;
		byte[] buffer = new byte[1024];
		boolean eohFound = false;

		//		
		while ((count = inFromServer.read(buffer)) != -1){ // count = # of bytes read
			if(!eohFound){
				String string = new String(buffer, 0, count);
				int indexOfEOH = string.indexOf("\n\n");
				System.out.println("Index of end of header " + indexOfEOH);
				if(indexOfEOH != -1) {
					count = count-indexOfEOH-2;
					buffer = string.substring(indexOfEOH+2).getBytes();
					eohFound = true;
				} else {
					count = 0;
				}
			}
			System.out.println(" number of bytes read is : " + count);
			dos.write(buffer, 0, count);
			dos.flush();
		}

		System.out.println("Closing outToServer");
		outToServer.close();
		System.out.println("Closing inFromServer");
		inFromServer.close();
		System.out.println("Closing clientSocket");
		clientSocket.close();

		// probably need to parse this
		//				200 OK		-> status code
		//				BODY_BYTE_OFFSET_IN_FILE: 0 	-> byte off set(always 0 for CS)
		//				BODY_BYTE_LENGTH: 58241			-> byte length ()
		//				\n\n							-> two new lines to separate header from payload
		//				<bytes of body follow here>
		//

	}

	public void talkToPearServer() throws IOException{
		request = "Redsox.jpg";
		System.out.println("Sending Get Request");
		// dataoutputstream writes to clientSocket.getoutputstrteam()
		DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
		//send request to server
		System.out.println("Writing request out to server: " + "GET: " +request+ '\n' );
		outToServer.writeBytes("GET "+request+ ".torrent" + '\n');
		outToServer.flush();

		System.out.println("Getting InputStream from Server");

		int bSize = 0;
		boolean eoh = false; // boolean for end of file
		String[] lengthArray;

		DataInputStream inFromServer = new DataInputStream(new BufferedInputStream(clientSocket.getInputStream()));
		OutputStream outFromServer = clientSocket.getOutputStream();   


		System.out.println(inFromServer.readLine()); // status
		System.out.println(inFromServer.readLine()); // offset
		String byteLenStr = inFromServer.readLine(); // length
		System.out.println(byteLenStr);
		lengthArray = byteLenStr.split(" ");
		bSize = Integer.parseInt(lengthArray[1]);
		System.out.println(inFromServer.readLine()); // newline

		byte[] buffer = new byte[1024*5];

		
		
		System.out.println("Writing to .jpg File now");
		FileOutputStream fileOut = new FileOutputStream("Redsox.jpg");

		BufferedOutputStream fileBuffer = new BufferedOutputStream(fileOut);
		System.out.println("Making .jpg File");

		int count;

		while ((count = inFromServer.read(buffer)) != -1) { // while not end of file
			// abouve should be producers
			//======================
			// this should start consumers
			//writing to file
			fileBuffer.write(buffer, 0, count);
			System.out.println("Data Received : " + count);
			fileBuffer.flush();
		}

		// just closing the socket
		System.out.println("Closing outToServer");
		outToServer.close();
		System.out.println("Closing inFromServer");
		inFromServer.close();
		System.out.println("Closing clientSocket");
		clientSocket.close();

		//Response Format
		
//		NUM_BLOCKS: 6
//		FILE_SIZE: 58241
//		IP1: 128.119.245.20
//		PORT1: 3456
//		IP2: 128.119.245.20
//		PORT2: 4321			

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
			ip = "test";
			port = 19876;
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
				TCPClient toPear = new TCPClient("plum.cs.umass.edu", 18765);
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
				default:
					System.out.println("Wrong IP Address and/or port");
					break;
			}
		default:
			System.out.println("wrong mode!");
			break;
		}
	}
}
