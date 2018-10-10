package orz.anzhy.data.quality.conn;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;

public class DBManager {

    private static final Logger LOG = LoggerFactory.getLogger(DBManager.class);


    /**
     * 获取connection连接
     *
     * @return connection
     */
    public static Connection getConn(String driver, String url,
                                     String user,
                                     String password) throws SQLException, ClassNotFoundException {
        Class.forName(driver);
        return DriverManager.getConnection(url, user, password);
    }


    public static synchronized void close(Object... objects) {

        Connection conn = null;
        PreparedStatement pstmt = null;
        Statement stmt = null;
        ResultSet rs = null;

        for (Object anObjArr : objects) {
            if (anObjArr instanceof Connection) {
                conn = (Connection) anObjArr;
            } else if (anObjArr instanceof PreparedStatement) {
                pstmt = (PreparedStatement) anObjArr;
            } else if (anObjArr instanceof Statement) {
                stmt = (Statement) anObjArr;
            } else if (anObjArr instanceof ResultSet) {
                rs = (ResultSet) anObjArr;
            }
        }
        resourceClose(conn, pstmt, stmt, rs);
    }

    private static void resourceClose(Connection conn, PreparedStatement pstmt, Statement stmt, ResultSet rs) {
        try {
            if (rs != null) {
                rs.close();
                LOG.debug("ResultSet Successful closed");
            }
        } catch (SQLException e) {
            LOG.warn("ResultSet close failed");
        } finally {
            try {
                if (pstmt != null) {
                    pstmt.close();
                    LOG.debug("PreparedStatement Successful closed");
                }
            } catch (SQLException e) {
                LOG.warn("PreparedStatement close failed");
            } finally {
                try {
                    if (stmt != null) {
                        stmt.close();
                        LOG.debug("Statement Successful closed");
                    }
                } catch (SQLException e) {
                    LOG.warn("Statement close failed");
                } finally {
                    try {
                        if (conn != null) {
                            conn.close();
                            LOG.debug("Connection Successful closed");
                        }
                    } catch (SQLException e) {
                        LOG.warn("Connection close failed");
                    }
                }
            }
        }
    }
}
