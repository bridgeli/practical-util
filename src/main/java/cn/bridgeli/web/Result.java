package cn.bridgeli.web;

public class Result {

    public static final Result SUCCESS = new Result(true);
    public static final Result FAILED = new Result(false);

    private boolean success;// 结果
    private String msg;// 描述
    private Object data;// data

    public Result(boolean success) {
        this.success = success;
    }

    public boolean isSuccess() {
        return success;
    }

    public Result setSuccess(boolean success) {
        this.success = success;
        return this;
    }

    public String getMsg() {
        return msg;
    }

    public Result setMsg(String msg) {
        this.msg = msg;
        return this;
    }

    public Object getData() {
        return data;
    }

    public Result setData(Object data) {
        this.data = data;
        return this;
    }

}
