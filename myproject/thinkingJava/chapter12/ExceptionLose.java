package thinkingJava.chapter12;

/**
 * Created by 15151 on 2020/3/19.
 */
public class ExceptionLose {
    class ImportantException extends Exception {
        @Override
        public String toString() {
            return "ImportantException{}";
        }
    }

    class HoHumException extends Exception {
        @Override
        public String toString() {
            return "HoHumException{}";
        }
    }

    void f() throws ImportantException {
        throw new ImportantException();
    }

    void dispose() throws HoHumException{
        throw new HoHumException();
    }

    /**
     * 异常丢失出现 这个问题已经被修正
     * @param args
     */
    public static void main(String[] args) {
        try{
            ExceptionLose lose = new ExceptionLose();
            try {
                lose.f();
            }finally {
                lose.dispose();
            }
        }catch (Exception e){
            System.out.println(e);
        }

    }
}
