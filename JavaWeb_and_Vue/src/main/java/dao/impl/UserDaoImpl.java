package dao.impl;

import dao.UserDao;
import pojo.User;
import utils.JDBCUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl implements UserDao {

    /**
     * 查询数据库中所有的用户信息
     */
    @Override
    public List<User> userList() throws ClassNotFoundException, SQLException {
        // 加载驱动
        Class.forName("com.mysql.cj.jdbc.Driver");
        // 获取数据库连接
        Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/user_demo", "root", "leesin");
        // sql 语句
        String sql = "select * from user_a";
        // 获取 sql 执行器
        PreparedStatement stmt = conn.prepareStatement(sql);
        // 执行 sql，获取结果集
        ResultSet rs = stmt.executeQuery();
        // 创建一个集合，存放数据库中查询的数据
        List<User> list = new ArrayList<>();
        while (rs.next()) {
            // 创建一个 User 对象，设置表中查到的属性
            User user = new User();
            user.setId(rs.getInt("id"));
            user.setUsername(rs.getString("username"));
            user.setType(rs.getString("type"));
            user.setAge(rs.getInt("age"));
            user.setSex(rs.getString("sex"));
            user.setAddress(rs.getString("address"));
            // 封装完成的对象，添加到集合中
            list.add(user);
        }
        // 释放数据资源
        rs.close();
        stmt.close();
        conn.close();
        // 返回 list 集合
        return list;
    }

    /**
     * 根据 id 删除用户
     */
    @Override
    public Integer deleteUserById(Integer id) throws SQLException {
        Connection conn = JDBCUtils.getConnection();
        String sql = "delete from user_a where id = ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1, id);
        int i = stmt.executeUpdate();
        // 释放数据资源
        stmt.close();
        conn.close();
        return i;


    }

    /**
     * 向数据库中添加用户
     */
    @Override
    public Integer addUser(User user) throws SQLException {
        Connection conn = JDBCUtils.getConnection();
        String sql = "insert into user_a(id, username, type, age, sex, address) values(null, ?, ?, ?, ?, ?)";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, user.getUsername());
        stmt.setString(2, user.getType());
        stmt.setInt(3, user.getAge());
        stmt.setString(4, user.getSex());
        stmt.setString(5, user.getAddress());
        int i = stmt.executeUpdate();
        // 释放数据资源
        stmt.close();
        conn.close();
        return i;
    }

    /**
     * 根据 id 修改用户信息
     */
    @Override
    public Integer UpdateUserById(User user) throws SQLException {
        Connection conn = JDBCUtils.getConnection();
        String sql = "update user_a set username = ?, type = ?, age = ?, sex = ?, address = ? where id = ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, user.getUsername());
        stmt.setString(2, user.getType());
        stmt.setInt(3, user.getAge());
        stmt.setString(4, user.getSex());
        stmt.setString(5, user.getAddress());
        stmt.setInt(6, user.getId());
        int i = stmt.executeUpdate();
        // 释放数据资源
        stmt.close();
        conn.close();
        return i;
    }
}
