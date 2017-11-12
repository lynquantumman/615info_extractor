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
		
		
//		ת��������Ҫ���������Ϣ
//			������category
//			ͬһ��category�����˶��ٴ�
//		��hashmap��ʵ��
		InfoExtractor infoExtractor =  new InfoExtractor(hardwareFile, softwareFile, destinationDirectory);
		infoExtractor.infoExtract();
	}
}
