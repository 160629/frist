package arithmetic;

public class Tree <T>{
	 public TreeNode<T> root;
	  public void addNode(TreeNode<T> node, T newNode){
			//增加根节点
	      if(null == node){
	           if(null == root){
	              root = new TreeNode(newNode);
	           }
	      }else{
	   	   	TreeNode<T> temp = new TreeNode(newNode);
	   	   	node.nodelist.add(temp);
	       }
	   }
		
		  /*    查找newNode这个节点 */
		public TreeNode<T> search(TreeNode<T> input, T newNode){
			TreeNode<T> temp = null;
		        
		    if(input.t.equals(newNode)){
		             return input;
		    }
		        
		    for(int i = 0; i < input.nodelist.size(); i++){
		             
		    	temp = search(input.nodelist.get(i), newNode);
		             
		        if(null != temp){
		        	break;
		        }    
		         
		      }
		        
		    return temp;
		}
		
	   public TreeNode<T> getNode(T newNode){
	       return search(root, newNode);
	   }
	    
	   
	   public void showNode(TreeNode<T> node){
	       if(null != node){
	           //循环遍历node的节点
	           System.out.println(node.t.toString());
	           
	           for(int i = 0; i < node.nodelist.size(); i++){
	               showNode(node.nodelist.get(i));
	           } 
	       }
	   }
	   
	   


	public static void main(String[] args) {
	      // TODO Auto-generated method stub
	      /*简单实现一个树的结构，后续完善解析xml             */
	      /*写得满烂的，后续查阅一些其他代码                2012-3-12    */
	      
		  Tree<String> tree = new Tree();
		  String s = "String";
		  		  
		  for (int i = 0; i < 8; i++) {
			  
			  if(i == 0){
				  tree.addNode(null, s+i);
			  }
			  
			  tree.addNode(tree.getNode(s+(i-1)), s+i);
			  if(i%2!=0){
				  
				  tree.addNode(tree.getNode(s+(i-1)), s+i+"单数");
			  }
			  
						  
		  }

		  tree.showNode(tree.root); 
	      System.out.println("end of the test");
	  }
}
