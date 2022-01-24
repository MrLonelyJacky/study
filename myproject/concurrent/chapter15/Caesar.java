package concurrent.chapter15;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.Scanner;
public class Caesar {
public static String defaultkeys;//����һ����ʼ����
public static String lineTxt2;
public static String miwen="";
 private static int[] getkeys(String keystring){//������Կ
	 int[] keys = new int[keystring.length()];
	 for(int i=0;i<keystring.length();i++){
		 keys[i]=(keystring.charAt(i))%26;//�������Ϊ��Կ����Ӧ���֣��ڼ��ܺ����б�ʾ��λ��
	 }
	 return keys;
 }
 public static void caesarjiami(String mingwen,String keystring){//�����Ľ��м���
	 int[] keys=getkeys(keystring);//��ȡ��Կ��λ������,��Ӧ�����е�ÿ���ַ�������ʾҪ��λ����
	
	 for(int i=0;i< mingwen.length();i++){
		 int key=keys[i%keys.length];//��ȡ��λ��
		 int c=mingwen.charAt(i);
		 if(c>='a'&&c<='z'){
			 c=(c-'a'+key)%26+'a';//�Է��ϵ������ַ���������λ����
		 }
		 if(c>='A'&&c<='Z'){
			 c=(c-'A'+key)%26+'A';//�Է��ϵ������ַ���������λ����
		 }
		 if(c>='0'&&c<='9'){
			 int k=key%10;
			 c=(c-'0'+k)%10+'0';//�Է��ϵ������ַ���������λ����
		 }
		 miwen =miwen+(char)c;//�γ�����
	 }
	  write1(miwen);
 }
 
 public static void caesarjiemi(String miwen,String keystring){//�����㷨
	 int[] keys=getkeys(keystring);
	 String result="";
	 for(int i=0;i<miwen.length();i++){
		 int key=keys[i%keys.length];
		 int c=miwen.charAt(i);
		 if(c>='a'&&c<='z'){
			 c=(26+c-'a'-key)%26+'a';
		 }
		 if(c>='A'&&c<='Z'){
			 c=(26+c-'A'-key)%26+'A';
		 }
		 if(c>='0'&&c<='9'){
				 int k=key%10;
				 c=(10+c-'0'-k)%10+'0';
		 }
		 result=result+(char)c;
	 }
	 write2(result);
 }
 public static void readTxtFile(String filePath){
     try {
             String encoding="GBK";
             File file=new File(filePath);
             if(file.isFile() && file.exists()){ //�ж��ļ��Ƿ����
                 InputStreamReader read = new InputStreamReader(
                 new FileInputStream(file),encoding);//���ǵ������ʽ
                 BufferedReader bufferedReader = new BufferedReader(read);
                 lineTxt2 = null;
                 while((lineTxt2 = bufferedReader.readLine()) != null){
                     System.out.println("�����ı�������������ǣ�"+lineTxt2);
                     caesarjiami(lineTxt2,defaultkeys);
                 }
                 read.close();
     }else{
         System.out.println("�Ҳ���ָ�����ļ�");
     }
     } catch (Exception e) {
         System.out.println("��ȡ�ļ����ݳ���");
         e.printStackTrace();
     }
 }
public static void write1(String mingwen){
	System.out.println("��������ļ�����·����");
	Scanner a=new Scanner(System.in);
	String path = a.next();
	
	try {
		File score_path = new File(path);
		
		if(!score_path.exists()){
			System.out.println("��·�������ڣ����Զ�Ϊ���ڸ�·���´���txt�ļ�");
			try {
				score_path.createNewFile();
			} 
			catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		PrintStream out = new PrintStream(new FileOutputStream(score_path));
		out.println(miwen);
	} 
	catch (FileNotFoundException e) {
		e.printStackTrace();
	}
}
public static void write2(String miwen){
	System.out.println("��������ļ�����·����");
	Scanner a=new Scanner(System.in);
	String path = a.next();
	
	try {
		File score_path = new File(path);
		
		if(!score_path.exists()){
			System.out.println("��·�������ڣ����Զ�Ϊ���ڸ�·���´���txt�ļ�");
			try {
				score_path.createNewFile();
			} 
			catch (IOException e) {
				e.printStackTrace();
			}
		}
		PrintStream out = new PrintStream(new FileOutputStream(score_path));
		out.println(miwen);
	} 
	catch (FileNotFoundException e) {
		e.printStackTrace();
	}
}
 public static void main(String args[]){
	 Scanner s=new Scanner(System.in);
	 System.out.println("������Կ:");
	 defaultkeys=s.nextLine();
	 String filePath ="E:\\casar\\caesar.txt";
	 readTxtFile(filePath);
	 caesarjiemi(miwen,defaultkeys);
 }
}
