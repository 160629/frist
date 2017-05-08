package bokeyuan;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.Iterator;
import java.util.List;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.dom.DOMDocument;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
public class Dom4jDemo extends DOMDocument {
	
	public void createXml(String fileName) { 
		Document document = DocumentHelper.createDocument();
		Element employees = document.addElement("employees");
		Element employee = employees.addElement("employee");
		Element name = employee.addElement("name");
		name.setText("ddvip");
		Element sex = employee.addElement("sex");
		sex.setText("yes");
		Element age = employee.addElement("age");
		age.setText("29");
		try {
			Writer fileWriter = new FileWriter(fileName);
			XMLWriter xmlWriter = new XMLWriter(fileWriter);
			xmlWriter.write(document);
			xmlWriter.close();
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}

	public void parserXml(String fileName) {
		File inputXml = new File(fileName);
		SAXReader saxReader = new SAXReader();
		try {
			Document document = saxReader.read(inputXml);
			Element employees = document.getRootElement();
			for (Iterator i = employees.elementIterator(); i.hasNext();) {
				Element employee = (Element) i.next();
				for (Iterator j = employee.elementIterator(); j.hasNext();) {
					Element node = (Element) j.next();
					System.out.println(node.getName() + ":" + node.getText());
				}
			}
		} catch (DocumentException e) {
			System.out.println(e.getMessage());
		}
		System.out.println("dom4j parserXml");
	}
	
	
	//修改xml文件
    public boolean modifigXML(String oldFileName,String newFileName){
        boolean isOk = false;
        try{
            SAXReader reader = new SAXReader();
            Document doc = reader.read(new File(oldFileName));
            /**修改内容之一: 如果employee节点中sex属性的内容为yes,则修改成no*/
            List list = doc.selectNodes("/employees/employee/@sex");
            Iterator iter = list.iterator();
            while(iter.hasNext()){
                Attribute attr = (Attribute)iter.next();
                if(attr.getValue().equals("yes"))
                    attr.setValue("no");
            }
            /**
             *修改内容之二: 把name项内容改为Tshinghua
             *并在name节点中加入date节点,
             * date节点的内容为2004-09-11,
             * 还为date节点添加一个属性type
             */
            list = doc.selectNodes("/employees/employee/name");
            iter = list.iterator();
            if(iter.hasNext()){
                Element owner = (Element)iter.next();
                owner.setText("Tshinghua");
                Element date = owner.addElement("date");
                date.setText("2006-07-30");
                date.addAttribute("type", "Gregorian calendar");
            }
           /**修改内容之三: 若age内容为29,则删除该节点*/
            list = doc.selectNodes("/employees/employee");
            iter = list.iterator();
            while(iter.hasNext()){
                Element elem = (Element)iter.next();
                Iterator iterElem = elem.elementIterator("age");
                if(iterElem.hasNext()){
                    Element remTitle = (Element)iterElem.next();
                    if(remTitle.getText().trim().equals("29"))
                        elem.remove(remTitle);
                }
            }
            //将doc中的内容写入文件中
            try{
                FileWriter newFile = new FileWriter(new File(newFileName));
                XMLWriter newWriter = new XMLWriter(newFile);
                newWriter.write(doc);
                newWriter.close();
            }catch(Exception e){
                e.printStackTrace();
            }
            isOk = true;
        }catch(Exception e){
            e.printStackTrace();
        }
        return isOk;
    }
	
    public boolean formatXML(String fileName){
        boolean isOk = false;
        try{
            SAXReader reader = new SAXReader();
            Document doc = reader.read(new File(fileName));
            XMLWriter formatWriter = null;
            /**
             *格式化输出,类型IE浏览一样
             *指定XML编码
             */
            OutputFormat format = OutputFormat.createPrettyPrint();
            format.setEncoding("gb2312");
            formatWriter = new XMLWriter(new FileWriter(new File(fileName)), format);
            formatWriter.write(doc);
            formatWriter.close();

            isOk = true;
        }catch(Exception e){
            e.printStackTrace();
        }
        return isOk;
    }
	public static void main(String[] args) {
		long start = System.currentTimeMillis();//开始计时
		Dom4jDemo dom4jDemo = new Dom4jDemo();
		//new Dom4jDemo().parserXml("G:\\code\\myUtil\\WebContent\\WEB-INF\\web.xml");
		//new Dom4jDemo().createXml("abc");
		//System.out.println(new Dom4jDemo().modifigXML("G:\\code\\myUtil\\abc.xml","G:\\code\\myUtil\\abc.xml"));

		String fileName = "G:\\code\\myUtil\\abc.xml";
		String newFileName = "G:\\code\\myUtil\\abcd.xml";
        System.out.println("正在格式化中...");
        boolean isOk_format = dom4jDemo.formatXML(fileName);
        if(isOk_format) System.out.println("恭喜,格式化完成!");
        else System.out.println("格式化失败!请检查后重新再试!");
        
        System.out.println("正在读取文件 "+fileName+" ...");
        System.out.println("------------------------------------------------");
        dom4jDemo.parserXml(fileName);
        System.out.println("------------------------------------------------");
        System.out.println(fileName+"读取成功!");
        System.out.println("正在读取文件 "+newFileName+" ...");
        System.out.println("------------------------------------------------");
        dom4jDemo.parserXml(newFileName);
        System.out.println("------------------------------------------------");
        System.out.println(fileName+"读取成功!");
        System.out.println("一共耗时： "+(System.currentTimeMillis()-start)+"毫秒!");
	}
}
