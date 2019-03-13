package dlnu.sdl.utils;

import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.Properties;

public class SqlHelper {
	// 定义需要的变量
	private static Connection ct = null;
	// 在大多数情况下，我们使用的是PreparedStatement来替代Statement
	// 这样可以防止sql注入。
	private static PreparedStatement ps = null;
	private static ResultSet rs = null;
	private static CallableStatement cs = null;

	// 连接数据库参数
	private static String url = "";
	private static String username = "";
	private static String driver = "";
	private static String password = "";

	// 读配置文件的
	private static Properties pp = null;
	private static InputStream fis = null;
	// 加载驱动，只需要一次，用静态代码块
	static {
		try {
			// 从dbinfo.properties文件中读取配置文件
			pp = new Properties();
			// fis = new FileInputStream("dbinfo.properties");//=>tomcat的主目录？
			// 当我们使用java web 的时候，读取文件要使用类加载器[因为类加载器去读取资源的时候，默认的主目录是src]
			fis = SqlHelper.class.getClassLoader().getResourceAsStream(
					"dbinfo.properties");
			pp.load(fis);
			url = pp.getProperty("url");
			username = pp.getProperty("username");
			driver = pp.getProperty("driver");
			password = pp.getProperty("password");

			Class.forName(driver);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				fis.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			fis = null;// 垃圾回收站上收拾
		}

	}

	// 得到连接
	public static Connection getConnection() {
		try {
			ct = DriverManager.getConnection(url, username, password);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ct;
	}

	// 分页问题
	public static ResultSet excuteQuery2() {
		return null;
	}

	// 调用存储过程,无返回参数
	// sql call 过程(?,?,?)
	// *************callPro1存储过程函数1*************
	public static void callPro1(String sql, String[] parameters) {
		try {
			ct = getConnection();
			cs = ct.prepareCall(sql);
			if (parameters != null) {
				for (int i = 0; i < parameters.length; i++) {
					cs.setObject(i + 1, parameters[i]);
				}
			}
			cs.execute();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		} finally {
			close(rs, cs, ct);
		}

	}

	// 调用存储过程，有返回Result
	// sql call 过程(?,?,?)
	// *******************callpro2存储过程2************************
	public static CallableStatement callPro2(String sql, String[] inparameters,
			Integer[] outparameters) {
		try {
			ct = getConnection();
			cs = ct.prepareCall(sql);
			if (inparameters != null) {
				for (int i = 0; i < inparameters.length; i++) {
					cs.setObject(i + 1, inparameters[i]);
				}
			}
			// cs.registerOutparameter(2,oracle.jdbc.OracleTypes.CURSOR);
			if (outparameters != null) {
				for (int i = 0; i < outparameters.length; i++) {
					cs.registerOutParameter(inparameters.length + 1 + i,
							outparameters[i]);
				}
			}
			cs.execute();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		} finally {

		}
		return cs;
	}

	// 对查询语句升级
	// 这样可以满足，这样的规则：哪里使用资源，哪里关闭资源
	@SuppressWarnings({"unchecked" })
	public static ArrayList executeQuery3(String sql, String[] parms) {
		PreparedStatement pstmt = null;
		Connection conn = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			// 对问号赋值
			if (parms != null && !parms.equals("")) {
				for (int i = 0; i < parms.length; i++) {
					pstmt.setString(i + 1, parms[i]);
				}
			}
			rs = pstmt.executeQuery();
			ArrayList al = new ArrayList();
			ResultSetMetaData rsmd = rs.getMetaData();
			int column = rsmd.getColumnCount();// 这里可以得到，你的查询语句中有多少列
			while (rs.next()) {
				Object[] ob = new Object[column];
				for (int i = 1; i <= column; i++) {
					ob[i - 1] = rs.getObject(i);
				}
				al.add(ob);
			}
			return al;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("executeSqlResultSet方法出错："
					+ e.getMessage());
		} finally {
			// try{
			close(rs, pstmt, conn);
			// }catch(Exception e){
			// e.printStackTrace();
			// throw new
			// RuntimeException("executeSqlResultSet方法出错："+e.getMessage());

			// }
		}

	}

	// 统一的select
	// ResultSet->ArrayList
	public static ResultSet executeQuery(String sql, String[] parameters) {
		try {
			ct = getConnection();
			ps = ct.prepareStatement(sql);
			if (parameters != null && !parameters.equals("")) {
				for (int i = 0; i < parameters.length; i++) {
					ps.setString(i + 1, parameters[i]);
				}
			}
			rs = ps.executeQuery();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		} finally {

		}
		return rs;
	}

	// 如果有多个 update/ delete/insert[需要考虑事务]
	public static void executeUpdate2(String[] sql, String[][] parameters) {
		try {
			// 核心
			// 1、获得连接
			ct = getConnection();
			//
			// 因为这时用户传入的可能是多个sql语句
			ct.setAutoCommit(false);
			// ....
			for (int i = 0; i < sql.length; i++) {

				if (parameters[i] != null) {
					ps = ct.prepareStatement(sql[i]);
					for (int j = 0; j < parameters[i].length; j++) {
						ps.setString(j + 1, parameters[i][j]);
					}
					ps.executeUpdate();
				}

			}

			ct.commit();

		} catch (Exception e) {
			e.printStackTrace();
			try {
				ct.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			throw new RuntimeException(e.getMessage());
		} finally {
			close(rs, ps, ct);
		}

	}

	// 先写一个update、delete、insert
	// sql格式：update 表名 set 字段名 =？where 字段=？
	// parameter神应该是（”abc“,23）返回是否成功
	public static boolean executeUpdate(String sql, String[] parameters) {
		// 1、创建一个ps
		boolean bo = false;
		try {
			ct = getConnection();
			ps = ct.prepareStatement(sql);
			if (parameters != null) {
				for (int i = 0; i < parameters.length; i++) {
					ps.setString(i + 1, parameters[i]);
				}

			}
			if(ps.executeUpdate() == 1)
				bo = true;
		} catch (Exception e) {
			e.printStackTrace();// 开发阶段
			// 抛出异常
			// 可以处理，也可以不处理
			throw new RuntimeException(e.getMessage());
		} finally {
			close(rs, ps, ct);
		}
		return bo; 
	}

	
	public static void close(ResultSet rs, Statement ps, Connection ct) {
		// 关闭资源(先开后关)
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			rs = null;
		}
		if (ps != null) {
			try {
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			ps = null;
		}
		if (null != ct) {
			try {
				ct.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			ct = null;
		}
	}

	public static Connection getCt() {
		return ct;
	}

	public static PreparedStatement getPs() {
		return ps;
	}

	public static ResultSet getRs() {
		return rs;
	}

	public static CallableStatement getCs() {
		return cs;
	}
}
