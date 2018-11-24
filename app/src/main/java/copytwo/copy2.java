package copytwo;


import android.util.Log;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 *同步类
 *
 */
public class copy2 {
    List<Student> li;


    /**
     * 连接数据库把数据
     * 放进list集合里
     */
    public  void  jdbc() {
        li = new ArrayList<Student>();

        String connectionUrl = "jdbc:jtds:sqlserver://192.168.165.180;databaseName=PZG;user=TechCent_PZG;password=12345678";
        Connection con = null;



        try {
            con = DriverManager.getConnection(connectionUrl);
        } catch (SQLException e) {
            e.printStackTrace();
        }




        try {
            Statement stmt = con.createStatement();

            String sql = "select * from guest.Student";

            ResultSet rs = stmt.executeQuery(sql);

                while (rs.next()) {
                    int age = rs.getInt("age");
                    String name = rs.getString("name");
                    Student st = new Student();
                    st.setAge(age);
                    st.setName(name);
                    li.add(st);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }


    }

    /**
     * 把数据从list集合取出
     * 插入别的表中
     */
    public  void test()  {
        Connection con = null;
        String connectionUrl = "jdbc:jtds:sqlserver://192.168.165.180;databaseName=PZG;user=TechCent_PZG;password=12345678";


        try {
            con = DriverManager.getConnection(connectionUrl);
        } catch (SQLException e) {
            e.printStackTrace();
        }


        for (Student u:li
             ) {
            int age2= u.getAge();
            String  name2=u.getName();
            Log.e("gong",name2+age2);


                try {
                    String sql="insert into guest.Teacher(age, name) values(?,?)";
                    PreparedStatement stmt = con.prepareStatement(sql);
                    stmt.setInt( 1,age2);
                    stmt.setString(2,name2);



                    int oo= stmt.executeUpdate();


                } catch (Exception e) {
                e.printStackTrace();
            }
        }




    }

    /**
     * 多表同步
     * 最后清空list集合
     */
    public  void test1()  {
        Connection con = null;
        String connectionUrl = "jdbc:jtds:sqlserver://192.168.165.180;databaseName=PZG;user=TechCent_PZG;password=12345678";


        try {
            con = DriverManager.getConnection(connectionUrl);
        } catch (SQLException e) {
            e.printStackTrace();
        }


        for (Student u:li
                ) {
            int age2= u.getAge();
            String  name2=u.getName();



            try {
                String sql="insert into guest.Teacher1(age, name) values(?,?)";
                PreparedStatement stmt = con.prepareStatement(sql);
                stmt.setInt( 1,age2);
                stmt.setString(2,name2);



                int oo= stmt.executeUpdate();


            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        li.clear();


    }


}
