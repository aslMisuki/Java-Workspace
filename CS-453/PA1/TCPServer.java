package PA1;

import java.io.*; 
import java.net.*; 

class TCPServer { 

	public static void main(String argv[]) throws Exception { 
		String clientSentence; 
		String capitalizedSentence; 
		
		ServerSocket welcomeSocket = new ServerSocket(6789); 

		while(true){

			Socket connectionSocket = welcomeSocket.accept(); // accepts socket 

			BufferedReader inFromClient = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream())); 
			//sends to client in form of DataOutputStream obj
			DataOutputStream  outToClient = new DataOutputStream(connectionSocket.getOutputStream()); 
			
			clientSentence = inFromClient.readLine(); 
			
			capitalizedSentence = clientSentence.toUpperCase() + '\n'; 
			
		
			outToClient.writeBytes(capitalizedSentence); 

		}
	}
}