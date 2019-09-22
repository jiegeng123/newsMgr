package util;

/**
 * 分页工具类
 */
public class PageUtil {
    public static final int PAGE_SIZE = 4;

    /**
     * 计算总页数
     */
    public static int getTotalPage(int count, int pageSize) {
        return count % pageSize == 0 ? count / pageSize : count / pageSize + 1;
    }
}
