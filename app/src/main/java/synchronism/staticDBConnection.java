package synchronism;

import java.sql.Connection;
import java.sql.DriverManager;

public class staticDBConnection {
    private String ip="jdbc:jtds:sqlserver://192.168.165.180";
    private String conNm = "TechCent_PZG";//数据库登录名
    private String pwd = "12345678";//密码

    public static staticDBConnection instance=new staticDBConnection();//实例
    public static Connection connection;//连接实例

    private staticDBConnection(){//构造函数
        connect();
        ip = "";
    }

    public void connect() {
        try {
            Class.forName("net.sourceforge.jtds.jdbc.Driver");//构造语句
            connection = DriverManager.getConnection("jdbc:jtds:sqlserver://"+ip+":1433;DatabaseName=*******" ,conNm, pwd);//初始化连接实例
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
