package arithmetic;

import java.util.ArrayList;
import java.util.List;

public class TreeNode<T> {

	private TreeNode<T> parent;
	public T t;  
    public List<TreeNode<T>> nodelist; 
  
    
    
   public TreeNode() {
		super();
   }
   
   public TreeNode(T stype){         
	   t = stype;
       parent = null;
       nodelist = new ArrayList<TreeNode<T>>();
  
       
   } 
   public TreeNode<T> getParent() {
        return parent;
   } 
   
 
}
