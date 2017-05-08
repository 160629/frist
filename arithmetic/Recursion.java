package arithmetic;

import java.io.File;

public class Recursion {
	
	
	//查询指定目录下的文件夹和文件
	public static void getFileNum(String path){
		
		 File file=new File(path);
		 
		 File[] tempList = file.listFiles();
		 
		 System.out.println("该目录下对象个数："+tempList.length);
		  
		 for (int i = 0; i < tempList.length; i++) {
		  
			 if (tempList[i].isFile()) {
		    
			   System.out.println("文     件："+tempList[i]);
		  
			 }
			 if (tempList[i].isDirectory()) {
		    
			   System.out.println("文件夹："+tempList[i]);
			 }
		 }
		 
	}
	
	//查询指定目录下的所有文件
	public static void getAllFile(File f){
		
		if(f!=null){
            if(f.isDirectory()){
                File[] fileArray=f.listFiles();
                if(fileArray!=null){
                    for (int i = 0; i < fileArray.length; i++) {
                        //递归调用
                    	getAllFile(fileArray[i]);
                    }
                }
            }else{
                System.out.println(f);
            }
		}
		
	}
	
	public static void main(String[] args) {
		
		//getFileNum("C://");
		getAllFile(new File("C://"));
		
	}
}
