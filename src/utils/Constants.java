package utils;

/**
 * @author mingoxu
 * @date 2020-06-09
 * @description 常数工具类
 */
public enum Constants {
    /**  自定义jsp前缀与后缀  **/
    PRE_FIX("/jsp/"),
    SUF_FIX(".jsp");

    private String value;

    Constants(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
