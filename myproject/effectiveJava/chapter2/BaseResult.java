package effectiveJava.chapter2;

import java.io.Serializable;

/**
 *
 */
public class BaseResult implements Serializable {
    private int code;
    private String message;
    private Object data;

    private enum BaseResultConstant {
        FAILED(0, "操作失败！"),
        SUCCESS(1, "操作成功"),
        VALIDATOR(2, "数据验证失败！");
        private int code;
        private String message;

        BaseResultConstant(int code, String message) {
            this.code = code;
            this.message = message;
        }

        public int getCode() {
            return code;
        }

        public String getMessage() {
            return message;
        }
    }

    private BaseResult() {
    }

    private BaseResult(String message) {
        this.message = message;
    }

    private BaseResult(int code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public static BaseResult failResultHasMess(String message) {
        return new BaseResult(0, message, null);
    }

    public static BaseResult failResultCreate() {
        return new BaseResult(BaseResultConstant.FAILED.getCode(), BaseResultConstant.FAILED.getMessage(), null);
    }

    public static BaseResult failResultHasData(String message, Object data) {
        return new BaseResult(BaseResultConstant.FAILED.getCode(), message, data);
    }


    @Override
    public String toString() {
        return "BaseResult{code=" + this.code + ", message='" + this.message + '\'' + ", data.txt=" + this.data + '}';
    }
}
