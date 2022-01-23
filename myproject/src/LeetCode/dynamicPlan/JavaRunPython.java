package LeetCode.dynamicPlan;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author segi
 * @date 2021/12/6
 * @description
 */
public class JavaRunPython {

    public static void main(String[] args) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Process proc;
                try {
                    String[] param = new String[] { "python", "E:\\py\\s.py" };
                    proc = Runtime.getRuntime().exec("python E:\\py\\gdal2tiles.py -l -p raster -z 0-7 -w none E:\\map.jpg E:\\titles4");
                    //proc = Runtime.getRuntime().exec(param);
                    //proc = Runtime.getRuntime().exec("python E:\\gdal2tiles.py");
                    BufferedReader in = new BufferedReader(new InputStreamReader(proc.getInputStream()));
                    String line = null;

                    while ((line = in.readLine()) != null) {
                        System.out.println(line);
                    }
                    in.close();
                    proc.waitFor();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                Process proc;
                try {
                    String[] param = new String[] { "python", "E:\\py\\s.py" };
                    proc = Runtime.getRuntime().exec("python E:\\py\\gdal2tiles.py -l -p raster -z 0-7 -w none E:\\map.jpg E:\\titles5");
                    //proc = Runtime.getRuntime().exec(param);
                    //proc = Runtime.getRuntime().exec("python E:\\gdal2tiles.py");
                    BufferedReader in = new BufferedReader(new InputStreamReader(proc.getInputStream()));
                    String line = null;

                    while ((line = in.readLine()) != null) {
                        System.out.println(line);
                    }
                    in.close();
                    proc.waitFor();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
