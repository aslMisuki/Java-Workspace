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

	public void splitHeader(){ // split header by with delimiter: \n\n

	}


	public int getBlockSize(){ //gets the size of the block from the reply header

		return 0;
	}

	public void talkToLocalServer() throws UnknownHostException, IOException{
	

		BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in)); // buffer to read user input
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
		
		BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream())); //buffer for server reply
		
		// closing 
		outToServer.close(); // probably not needed
		inFromServer.close();// probably not needed
		clientSocket.close();// probably not needed
	}

	public void talkToPlumServer() throws IOException{
		request =  "GET Redsox.jpg";
		
		DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
		outToServer.writeBytes(request + '\n'); //("GET "+request+" HTTP/1.1\r\n");
		//outToServer.writeBytes("Host: "+hostname+":"+ port +"\r\n\r\n");
		outToServer.flush();
		
		DataInputStream inFromServer = new DataInputStream(clientSocket.getInputStream());
		
		
		
		OutputStream dos = new FileOutputStream("Redsox.jpg"); // used to make the .jpg file
		int count;
		//BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream())); //buffer for server reply
		
		byte[] buffer = new byte[2048];// buffer is too small
		boolean eohFound = false;
		while ((count = inFromServer.read(buffer)) != -1)
		{
		    if(!eohFound){
		        String string = new String(buffer, 0, count);
		        int indexOfEOH = string.indexOf("\r\n\r\n");
		        if(indexOfEOH != -1) {
		            count = count-indexOfEOH-4;
		            buffer = string.substring(indexOfEOH+4).getBytes();
		            eohFound = true;
		        } else {
		            count = 0;
		        }
		    }
		  dos.write(buffer, 0, count);
		  dos.flush();
		}
		inFromServer.close();

	}

	public void talkToPearServer() throws IOException{
		request =  "Redsox.jpg";
		// dataoutputstream writes to clientSocket.getoutputstrteam()
		DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
		//send request to server
		outToServer.writeBytes("GET "+request+ '\n');
		// empty stream
		outToServer.flush();
		
		System.out.println("Getting InputStream from Server");
		DataInputStream inFromServer = new DataInputStream(clientSocket.getInputStream());
		
		OutputStream dos = new FileOutputStream("Redsox.jpg");
		int count;
		byte[] buffer = new byte[60000];
		boolean eohFound = false;
		
		
		while ((count = inFromServer.read(buffer)) != -1)
		{
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
		  System.out.println("Writing to File now");
		  dos.write(buffer, 0, count);
		  System.out.println("Done Writing to File");
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

	public static void main(String args[]) throws Exception {

				String run = "hardwired"; // "hardwired" or "shell" TODO: change to shell for submission
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
				case "hardwired":
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


//		TCPClient toLocal = new TCPClient ("localhost", 6789);
//		TCPClient toPlum = new TCPClient("plum.cs.umass.edu", 18765);
//		TCPClient toTest = new TCPClient("date.cs.umass.edu", 20001); 



		//toTest.talkToTestServer();
		//toLocal.talkToLocalServer();


	}
}
