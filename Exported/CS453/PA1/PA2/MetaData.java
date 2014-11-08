package PA2;


public class MetaData{	
	
	// metadata objects to be accessed by producer threads
	//metaData
	int numBlocks,fileSize,bodyOffset,bodyLength,port1,port2;
	String ip1,ip2;
	
	boolean eOMD = false;
	
	public MetaData(){} // constructor
	
	public boolean parseMetaData(String line){

//		format:
//
//		NUM_BLOCKS: 6  		- is the number of blocks in the requested file
//		FILE_SIZE: 58241 	- size of the entire file in bytes - All blocks except the last block are of the same size, namely, floor[FILE_SIZE/NUM_BLOCKS]
//		IP1: 128.119.245.20	- identify the IP address and port number of the first peer
//		PORT1: 3456
//		IP2: 128.119.245.20	- IP2 and PORT2 identify the second peer. 
//		PORT2: 4321
		
		//delimiters using space
		String l = line.replace(":", "").toUpperCase();
		String[] spliter = line.split(" ");
		
		switch(spliter[0]){
		//response to request for torrent metadata
		case "NUM_BLOCKS":
			numBlocks = Integer.parseInt(spliter[1]);
			break;
		case "FILE_SIZE":
			fileSize = Integer.parseInt(spliter[1]);
			break;
		case "IP1":
			ip1 = spliter[1];
			break;
		case "PORT1":
			port1 = Integer.parseInt(spliter[1]);
			break;
		case "IP2":
			ip2 = spliter[1];
			break;
		case "PORT2":
			port2 = Integer.parseInt(spliter[1]);
			break;
		case "":
			return eOMD = true;
		default:
			return eOMD = false;
		}
		return eOMD = false;
	}
	
	//returns true for end of metadata
	public boolean isEnd(){
		return eOMD;
	}
	
	public int getBlockSize(){
		return (int)(fileSize/numBlocks);
	}
	
	public String toString(){
		StringBuilder s = new StringBuilder();
		
		s.append("NUM_BLOCKS: " + numBlocks + "\n");
		s.append("FILE_SIZE: " + fileSize + "\n");
		s.append("IP1: " + ip1 + "\n");
		s.append("PORT1: " + port1 + "\n");
		s.append("IP2: " + ip2 + "\n");
		s.append("PORT2: " + port2 + "\n");
		
		return s.toString();
	}
	
	
}

