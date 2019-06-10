package org.lanqiao.util;

import org.lanqiao.entity.Student;

import java.sql.*;

//通用的数据库操作方法,减少代码的冗余
public class DBUtil {
    private static final String URL = "jdbc:mysql://localhost:3306/javaWeb?characterEncoding=utf-8";
    private static final String USERNAME = "root";
    private static final String PWD = "123456";
    public static Connection connection = null;
    public static PreparedStatement pstmt = null;
    public static ResultSet rs = null;

    public static Connection getConnection() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.jdbc.Driver");
        return DriverManager.getConnection(URL, USERNAME, PWD);
    }
    public static PreparedStatement createPreparedStatement(String sql,Object[] params) throws SQLException, ClassNotFoundException {
        pstmt = getConnection().prepareStatement(sql);

        //pstmt.setInt(1,sno);setXxx()方法的个数依赖于？的个数，？的个数和数组params的个数保持一致,setObject通配
        if(params!=null) {
            for (int i = 0; i < params.length; i++) {
                pstmt.setObject(i + 1, params[i]);
            }
        }
        return pstmt;
    }

    public static void closeAll(ResultSet rs,Statement stmt,Connection connection){
        try {
                if(rs!=null)rs.close();
                if (pstmt != null) pstmt.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
    }
    //通用的增删改
    public static boolean executeUpdate(String sql, Object[] params) {

        try {
//            Class.forName("com.mysql.jdbc.Driver");
//            connection = DriverManager.getConnection(URL, USERNAME, PWD);

            //Object[] objs={no,name,age,address}
            //String sql="delete from xxx where sno=?";
//            pstmt = getConnection().prepareStatement(sql);
//
//            //pstmt.setInt(1,sno);setXxx()方法的个数依赖于？的个数，？的个数和数组params的个数保持一致,setObject通配
//            if(params!=null) {
//                for (int i = 0; i < params.length; i++) {
//                    pstmt.setObject(i + 1, params[i]);
//                }
//            }方法重构：提取出通用的代码块
            pstmt=createPreparedStatement(sql,params);
            int count = pstmt.executeUpdate();
            if (count > 0) {
                return true;
            } else {
                return false;
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return false;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        finally {
            closeAll(null,pstmt,connection);
        }
    }

    //通用的查:返回值是一个集合
    public static ResultSet executeQuery(String sql, Object[] params) {//select xxx from xx where name=? or id=?
        Student student = null;
        try {
//            Class.forName("com.mysql.jdbc.Driver");
//            connection = DriverManager.getConnection(URL, USERNAME, PWD);

//            pstmt = getConnection().prepareStatement(sql);//代替了前两句
//            if(params!=null){
//                for (int i = 0; i < params.length; i++) {
//                    pstmt.setObject(i + 1, params[i]);
//                }
//            }
            pstmt=createPreparedStatement(sql,params);//代替了前一段
            rs = pstmt.executeQuery();
            return rs;

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
//        finally {
//                try {
//                    if (rs != null) rs.close();
//                    if (pstmt != null) pstmt.close();
//                    if (connection != null) connection.close();
//                    }
//                    catch (SQLException e) {
//                    e.printStackTrace();
//                    }
//
//                 }这里只得到了rs,先不关闭，但查询完后要关闭
                }

                public static int getTotalCount(String sql){
                    int count=-1;
                    try{
                        pstmt=createPreparedStatement(sql,null);
                        rs=pstmt.executeQuery();
                        if(rs.next()){
                            count=rs.getInt(1);
                        }

                    }catch (SQLException e) {
                        e.printStackTrace();
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }catch (Exception e) {
                        e.printStackTrace();
                    }finally {
                        closeAll(rs,pstmt,connection);
                    }
                    return count;

                }
}
