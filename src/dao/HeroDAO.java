package dao;

import bean.Hero;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class HeroDAO {
    public HeroDAO(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }
    }

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/lol?useSSL=false","root","123456");
    }

    public int getTotal(){
        int total = 0;
        try(Connection c = getConnection(); Statement s = c.createStatement()){
            String sql = "select count(*) from hero";
            ResultSet rs = s.executeQuery(sql);
            while (rs.next()){
                total = rs.getInt(1);
            }
            System.out.println(total);
        }catch (SQLException e){
            e.printStackTrace();
        }
        return total;
    }

    public void add(Hero hero){
        String sql = "insert into hero values(null,?,?,?)";
        try(Connection c = getConnection();PreparedStatement p = c.prepareStatement(sql);){
            p.setString(1,hero.name);
            p.setFloat(2,hero.hp);
            p.setInt(3,hero.damage);

            p.execute();

            ResultSet r = p.getGeneratedKeys();
            if(r.next()) {
                int id = r.getInt(1);
                hero.id = id;
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    public void update(Hero hero){
        String sql = "update hero set name = ?,hp = ?,damage = ? where id = ?";
        try(Connection c = getConnection();PreparedStatement p = c.prepareStatement(sql)){
            p.setString(1,hero.name);
            p.setFloat(2,hero.hp);
            p.setInt(3,hero.damage);
            p.setInt(4,hero.id);
            p.execute();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void delete(int id){
        try(Connection c = getConnection();Statement s = c.createStatement()){
            String sql = "delete from hero where id = " + id;
            s.execute(sql);
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public Hero get(int id){
        Hero hero = null;
        try(Connection c = getConnection();Statement s = c.createStatement()){
            String sql = "select * from hero where id = " + id;
            ResultSet r = s.executeQuery(sql);

            if(r.next()) {
                hero.setId(id);
                hero.setName(r.getString(2));
                hero.setHp(r.getFloat("hp"));
                hero.setDamage(r.getInt(4));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return hero;
    }

    public List<Hero> list(){
        return list(0,Short.MAX_VALUE);
    }

    public List<Hero> list(int start,int count){
        List<Hero> heros = new ArrayList<Hero>();

        String sql = "select * from hero order by id desc limit ?,?";

        try(Connection c = getConnection();PreparedStatement p = c.prepareStatement(sql);){
            p.setInt(1,start);
            p.setInt(2,count);

            ResultSet r = p.executeQuery();

            while(r.next()){
                Hero hero = new Hero();
                hero.id = r.getInt(1);
                hero.name = r.getString(2);
                hero.hp = r.getFloat("hp");
                hero.damage = r.getInt(4);

                heros.add(hero);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return  heros;
    }


}
