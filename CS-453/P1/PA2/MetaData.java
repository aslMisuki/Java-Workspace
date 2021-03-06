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
		l = l.replace("DATE/", "");
		l = l.replace("\n", "\\\n");
		String[] spliter = l.split(":");
		String[] temp;
		for(String s : spliter){
			temp = s.split(" ");
			for(String t: temp){
				System.out.println(t);
			}
		}

	
//			switch(temp[0]){
//			//response to request for torrent metadata
//			case "NUM_BLOCKS":
//				System.out.println("Match NUM_BLOCKS");
//				numBlocks = Integer.parseInt(temp[1]);
//				break;
//			case "FILE_SIZE":
//				System.out.println("Match FILE_SIZE");
//				fileSize = Integer.parseInt(temp[1]);
//				break;
//			case "IP1":
//				System.out.println("Match IP1");
//				ip1 = temp[1];
//				break;
//			case "PORT1":
//				System.out.println("Match Port1");
//				port1 = Integer.parseInt(temp[1]);
//				break;
//			case "IP2":
//				System.out.println("Match IP2");
//				ip2 = temp[1];
//				break;
//			case "PORT2":
//				System.out.println("Match Port2");
//				port2 = Integer.parseInt(temp[1]);
//				break;
//			case "":
//				System.out.println("empty Case");
//				eOMD = false;
//				break;
//			default:
//				System.out.println("Default Case");
//				eOMD = false;
//				break;
//			}
//		}
		eOMD = true;
		return eOMD;
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

