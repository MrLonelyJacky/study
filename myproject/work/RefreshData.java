package work;

import java.io.*;

/**
 * @description:
 * @author: jacky
 * @create: 2022-11-22 15:03
 **/
public class RefreshData {
    public static final String columnName = "cust_main_id";
    public static final String tableName = "zl_contract_settle";

    public static final String newFilePath = "E:\\workDoc\\合同结算表.txt";

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new FileReader("E:\\workDoc\\脚本.txt"));

        File file = new File(newFilePath);
        if (!file.exists()){
            file.createNewFile();
        }

        BufferedWriter bw = new BufferedWriter(new FileWriter(newFilePath));

        String line = null;
        while((line = br.readLine())!=null){
            line = line.replaceAll("\t"," ");
            line = line.replaceAll(" +", " ");
            System.out.println(line);
            String[] s = line.split(" ");
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("update ").append(tableName).append(" set ").append(columnName).append("= ").append(s[0].trim())
                    .append(" where ").append(columnName).append("= ").append(s[1].trim()).append(";");
            bw.write(stringBuilder.toString());//输出字符串
            bw.newLine();//换行
            bw.flush();
        }
        br.close();

    }
}
