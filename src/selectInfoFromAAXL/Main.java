package selectInfoFromAAXL;

public class Main {
	public static void main(String[] args){
		String hardwareFile = null;
		String softwareFile = null;
		String destinationDirectory = null;
		if(args.length==0){

			System.out.println("usage: the args should be in order like hardwareFile, softwareFile, destinationDirectory");
			
		}
		else if(args.length==2){
			hardwareFile = args[0];
			softwareFile = args[1];
			destinationDirectory = args[2];
			
		}
		
		
//		转换核心需要两方面的信息
//			构件的category
//			同一个category出现了多少次
//		用hashmap来实现
		InfoExtractor infoExtractor =  new InfoExtractor(hardwareFile, softwareFile, destinationDirectory);
		infoExtractor.infoExtract();
	}
}
