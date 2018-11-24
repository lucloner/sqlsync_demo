package copytwo;

import android.support.v4.widget.TextViewCompat;
import android.util.Log;

import net.sourceforge.jtds.jdbc.JtdsConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class copy {
    List<Student> li;


    public  void  jdbc() {

        li = new ArrayList<Student>();

        try {
            String sql2 = "select * from guest.Student";

            String connectionUrl = "jdbc:jtds:sqlserver://192.168.165.180;databaseName=PZG;user=TechCent_PZG;password=12345678";
            try (Connection con = DriverManager.getConnection(connectionUrl); Statement stmt = con.createStatement();) {

                try (ResultSet rs = stmt.executeQuery(sql2)) {

                    while (rs.next()) {
                        int age = rs.getInt("age");
                        String name = rs.getString("name");
                        Student st = new Student();
                        st.setAge(age);
                        st.setName(name);
                        li.add(st);

                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

        } catch (Exception e) {

            e.printStackTrace();
            System.out.print("出现异常");


        }
    }
    public  void test(){

        for (int i=0;i<li.size();i++){
            int age2=  li.get(i).getAge();
            String  name2=li.get(i).getName();

            Log.e("gong",name2+age2);

           String connectionUrl = "jdbc:jtds:sqlserver://192.168.165.180;databaseName=PZG;user=TechCent_PZG;password=12345678";

                try {
                    String sql="insert into guest.Teacher(age, name) values(#{age2},#{name2})";
                    Connection con = DriverManager.getConnection(connectionUrl); PreparedStatement stmt = con.prepareStatement(sql);
                    stmt.setInt( 1,age2);
                    stmt.setString(2,name2);



             int oo= stmt.executeUpdate();





            }


            catch (Exception e) {
                e.printStackTrace();
            }
        }

li.clear();


    }







}

