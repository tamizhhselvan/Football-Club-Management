package com.ClubManagement.ManUTD;

import java.sql.*;

public class DBCode {
    private static Connection con;

    DBCode() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        con= DriverManager.getConnection("jdbc:mysql://localhost:3306/man_utd","root","6385450731tamil");
        System.out.println("Connected Successfully");
    }

    public boolean login(String userId,String pass) throws SQLException {
        String q = "SELECT * FROM login";
        Statement s = con.createStatement();
        ResultSet rs = s.executeQuery(q);

        while(rs.next()){
            if(userId.equals(rs.getString("userId"))&&pass.equals(rs.getString("pass"))){
                return true;
            }

        }
        System.out.println("Entered Credentials are wrong");
        return false;
    }

    public boolean playerLogin(String userId,String pass) throws SQLException {
        String q = "SELECT * FROM playerlogin";
        Statement s = con.createStatement();
        ResultSet rs = s.executeQuery(q);

        while(rs.next()){
            if(userId.equals(rs.getString("userId"))&&pass.equals(rs.getString("pass"))){
                return true;
            }

        }
        System.out.println("Entered UserID or Password is wrong");
        return false;
    }

    public int adminCreatingAnotherAdminAccount(String userId, String pass) throws SQLException {
        String q = "INSERT INTO login (userId,pass) VALUES(?,?)";
        PreparedStatement ps = con.prepareStatement(q);
        ps.setString(1,userId);
        ps.setString(2,pass);

        return ps.executeUpdate();
    }


    public int insert(String name,int jerseyNum,String nationality,int age,String position,int appearance,int mins,int goals,float ps_percent,int motm,float rating,String secretkey) throws SQLException {
        String q = "INSERT INTO playerstats (name,jerseyNum,nationality,age,position,appearance,mins,goals,ps_percent,motm,rating,secretkey) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
        PreparedStatement ps = con.prepareStatement(q);
        ps.setString(1,name);
        ps.setInt(2,jerseyNum);
        ps.setString(3,nationality);
        ps.setInt(4,age);
        ps.setString(5,position);
        ps.setInt(6,appearance);
        ps.setInt(7,mins);
        ps.setInt(8,goals);
        ps.setFloat(9,ps_percent);
        ps.setInt(10,motm);
        ps.setFloat(11,rating);
        ps.setString(12,secretkey);

        return ps.executeUpdate();
    }

    public int createCredentialsForPlayers(String user,String pass,int jerseyNum,String secretkey) throws SQLException {
        String q = "INSERT INTO playerlogin(userId,pass,jerseyNum) VALUES(?,?,?,?)";
        PreparedStatement ps = con.prepareStatement(q);
        ps.setString(1,user);
        ps.setString(2,pass);
        ps.setInt(3,jerseyNum );
        ps.setString(4,secretkey);

        return ps.executeUpdate();
    }

    public int adminModifyingAdminCredentials(String oldUserId,String oldPass,String newUserId,String newPass) throws SQLException {
        String q = "UPDATE login SET userId=? ,pass=? WHERE userId = ? AND pass=? ";
        PreparedStatement ps = con.prepareStatement(q);
        ps.setString(1,newUserId);
        ps.setString(2,newPass);
        ps.setString(3,oldUserId);
        ps.setString(4,oldPass);

        return ps.executeUpdate();
    }
    public int playerModifyingCredentials(String userId,String pass,int jerseyNum,String secretkey) throws SQLException {
        String q = "UPDATE playerlogin SET userId =? ,pass=? WHERE jerseyNum=? And secretkey=? ";
        PreparedStatement ps = con.prepareStatement(q);
        ps.setString(1,userId);
        ps.setString(2,pass);
        ps.setInt(3,jerseyNum);
        ps.setString(4,secretkey);

        return ps.executeUpdate();
    }

    public int playerModifyingName(String name,int jerseyNum,String secretkey) throws SQLException {
        String q = "UPDATE playerstats SET name=? WHERE jerseyNum=? AND secretkey=? ";
        PreparedStatement ps = con.prepareStatement(q);
        ps.setString(1,name);
        ps.setInt(2,jerseyNum);
        ps.setString(3,secretkey);

        return ps.executeUpdate();
    }

    public void adminView() throws SQLException {
        String q = "SELECT * FROM playerstats";
        PreparedStatement ps = con.prepareStatement(q);
        ResultSet rs = ps.executeQuery();
        while (rs.next()){
            System.out.println(rs.getInt("id")+"\t"+
                    rs.getString("name")+"\t"+
                    rs.getInt("jerseyNum")+"\t"+
                    rs.getString("nationality")+"\t"+
                    rs.getInt("age")+"\t"+
                    rs.getString("position")+"\t"+
                    rs.getInt("appearance")+"\t"+
                    rs.getInt("mins")+"\t"+
                    rs.getInt("goals")+"\t"+
                    rs.getFloat("ps_percent")+"\t"+
                    rs.getInt("motm")+"\t"+
                    rs.getFloat("rating")+"\t"
            );
        }
    }

    public void playerView(int jerseyNum,String secretkey) throws SQLException{
        String q = "SELECT * FROM playerstats WHERE jerseyNum=? AND secretkey=? ";
        PreparedStatement ps = con.prepareStatement(q);
        ps.setInt(1,jerseyNum);
        ps.setString(2,secretkey);
//        ps.executeUpdate();
        ResultSet rs = ps.executeQuery();
        while (rs.next()){
            System.out.println(rs.getInt("id") +"\t"+
                    rs.getString("name")+"\t"+
                    rs.getInt("jerseyNum")+"\t"+
                    rs.getString("nationality")+"\t"+
                    rs.getInt("age")+"\t"+
                    rs.getString("position")+"\t"+
                    rs.getInt("appearance")+"\t"+
                    rs.getInt("mins")+"\t"+
                    rs.getInt("goals")+"\t"+
                    rs.getFloat("ps_percent")+"\t"+
                    rs.getInt("motm")+"\t"+
                    rs.getFloat("rating")+"\t"
            );
        }

    }

    public int updateAfterEveryMatch(int appearance,int mins,int goals,float ps_percent,int motm,int jerseyNum) throws SQLException{
        String q = "UPDATE playerstats SET appearance=?,mins=?,goals=?,ps_percent=?,motm=? WHERE jerseyNum=? ";
        PreparedStatement ps = con.prepareStatement(q);
        ps.setInt(1,appearance);
        ps.setInt(2,mins);
        ps.setInt(3,goals);
        ps.setFloat(4,ps_percent);
        ps.setInt(5,motm);
        ps.setInt(6,jerseyNum);

        return ps.executeUpdate();
    }
    public int updateAge(int age,int jerseyNum) throws SQLException{
        String q = "UPDATE playerstats SET age=? WHERE jerseyNum=? ";
        PreparedStatement ps = con.prepareStatement(q);
        ps.setInt(1,age);
        ps.setInt(2,jerseyNum);

        return ps.executeUpdate();
    }
    public int updatePosition(String position,int jerseyNum) throws SQLException{
        String q = "UPDATE playerstats SET position=? WHERE jerseyNum=? ";
        PreparedStatement ps = con.prepareStatement(q);
        ps.setString(1,position);
        ps.setInt(2,jerseyNum);

        return ps.executeUpdate();
    }

    public int  delete(int jerseyNum) throws SQLException{
//        String q = "DELETE FROM playerstats WHERE jerseyNum=? "; OG code
        String q = "DELETE playerstats,playerlogin FROM playerstats JOIN playerlogin ON playerstats.jerseyNum = playerlogin.jerseyNum WHERE playerstats.jerseyNum=?";
        PreparedStatement ps = con.prepareStatement(q);
        ps.setInt(1,jerseyNum);

        return ps.executeUpdate();
        /*
            DELETE players, player_info
            FROM players
            JOIN player_info ON players.player_id = player_info.player_id
            WHERE players.jersey_number = 3;

         */
    }

    public void conClose() throws SQLException{
        con.close();
        System.out.println("logged out successfully");

    }
}
