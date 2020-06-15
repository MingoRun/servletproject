package utils;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import org.apache.log4j.Logger;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * JDBC工具类
 */
public class JDBCUtil {
    private static Logger logger = Logger.getLogger(JDBCUtil.class);

    private static DataSource ds;

    private JDBCUtil(){}

    static {
        try {
            ds = DruidDataSourceFactory.createDataSource(PropertiesUtil.getProperties());
        } catch (Exception e) {
            logger.error(e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * 获取数据源
     */
    public static DataSource getDataSource() {
        return ds;
    }

    /**
     * 获取连接
     * @return
     * @throws SQLException
     */
    public static Connection getConnection() throws SQLException {
        return ds.getConnection();
    }

    /**
     * 关闭资源
     */
    public static void close(Statement st,Connection conn) {
        if (st!=null) {
            try {
                st.close();
            } catch (SQLException e) {
                logger.error(e.getMessage());
                e.printStackTrace();
            }
        }

        if (conn!=null) {
            try {
                conn.close();
            } catch (SQLException e) {
                logger.error(e.getMessage());
                e.printStackTrace();
            }
        }
    }

    public static void close(ResultSet rs,Statement st,Connection conn) {
        if (rs!=null) {
            try {
                rs.close();
            } catch (SQLException e) {
                logger.error(e.getMessage());
                e.printStackTrace();
            }
        }

        if (st!=null) {
            try {
                st.close();
            } catch (SQLException e) {
                logger.error(e.getMessage());
                e.printStackTrace();
            }
        }

        if (conn!=null) {
            try {
                conn.close();
            } catch (SQLException e) {
                logger.error(e.getMessage());
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        String sql = "select * from student";
        List<Map<String,Object>> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = JDBCUtil.getConnection();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            ResultSetMetaData md = rs.getMetaData();
            int colnumCount = md.getColumnCount();
            while (rs.next()) {
                Map<String,Object> rowData = new HashMap<>();
                for (int i=1; i<=colnumCount; i++) {
                    rowData.put(md.getColumnName(i),rs.getObject(i));
                }
                list.add(rowData);
            }
            System.out.println(list.toString());
        } catch (SQLException e) {
            logger.error(e.getMessage());
            e.printStackTrace();
        } finally {
            JDBCUtil.close(rs,ps,conn);
        }
    }

}
