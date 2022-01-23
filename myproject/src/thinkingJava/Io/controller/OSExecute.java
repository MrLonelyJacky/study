package thinkingJava.Io.controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Created by 15151 on 2019/5/11.
 */
public class OSExecute {
    public static void command(String command) {
        boolean err = false;
        try {
            Process process = new ProcessBuilder(command.split("-")).start();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String s;
            while ((s = bufferedReader.readLine()) != null) {
                System.out.println(s);
            }
            BufferedReader errors = new BufferedReader(new InputStreamReader(process.getErrorStream()));
            while ((s = errors.readLine()) != null) {
                System.out.println(s);
                err = true;
            }
        } catch (Exception e) {
            if (!command.startsWith("CMD /C")) {
                command("CMD /C" + command);
            } else {
                throw new RuntimeException(e);
            }
        }
        if (err) {
            throw  new OSExecuteException("Error excuting .....");
        }

    }
}
