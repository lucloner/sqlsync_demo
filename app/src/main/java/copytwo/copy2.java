package copytwo;


import android.util.Log;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class copy2 {
    List<Student> li;


    public  void  jdbc() {

        li = new ArrayList<Student>();

        try {
            String sql = "select * from guest.Student";

            String connectionUrl = "jdbc:jtds:sqlserver://192.168.165.180;databaseName=PZG;user=TechCent_PZG;password=12345678";
            try (Connection con = DriverManager.getConnection(connectionUrl); Statement stmt = con.createStatement();) {

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

        } catch (Exception e) {

            e.printStackTrace();
            System.out.print("出现异常");


        }
    }
    public  void test(){


        for (Student u:li
             ) {
            int age2= u.getAge();
            String  name2=u.getName();


            Log.e("gong",name2+age2);

               String connectionUrl = "jdbc:jtds:sqlserver://192.168.165.180;databaseName=PZG;user=TechCent_PZG;password=12345678";
                try (Connection con = DriverManager.getConnection(connectionUrl); Statement stmt = con.createStatement();) {


                    String sql="insert into guest.Teacher values(" +u.getAge()+",'"+ u.getName()+"')";



                    stmt.executeUpdate(sql);
                    }


            catch (Exception e) {
                e.printStackTrace();
            }
        }

        li.clear();

    }










}
