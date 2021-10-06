package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class UserDaoHibernateImpl implements UserDao {

    Session session;

    public UserDaoHibernateImpl() {
        try {
            session = Util.getSessionFactory().openSession();
        } catch (Exception e){
            e.printStackTrace();
        }
    }


    @Override
    public void createUsersTable() {
        Transaction transaction = session.beginTransaction();

        session.createSQLQuery("CREATE TABLE IF NOT EXISTS users (" +
                "id INTEGER not NULL AUTO_INCREMENT," +
                "name VARCHAR(255)," +
                "lastName VARCHAR (255)," +
                "age SMALLINT ," +
                "PRIMARY KEY (id))").executeUpdate();
        transaction.commit();
        //session.close() ;
    }

    @Override
    public void dropUsersTable() {
        Transaction transaction = session.beginTransaction();
        session.createSQLQuery("DROP TABLE IF EXISTS users ").addEntity(User.class).executeUpdate();
        transaction.commit();
        //session.close();

    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        Transaction transaction = session.beginTransaction();
        User user = new User(name, lastName, age);
        session.save(user);
        transaction.commit();
       // session.close();
    }

    @Override
    public void removeUserById(long id) {
//        User user = (User) session.load(User.class, id);
//        session.delete(user);
        Transaction transaction = session.beginTransaction();
        User user = (User)session.get(User.class,id);
        session.delete(user);
        transaction.commit();
        // session.close();
    }

    @Override
    public List<User> getAllUsers() {
        Criteria criteria;
        criteria = session.createCriteria(User.class);
        //session.close() ;
        return criteria.list();
    }

    @Override
    public void cleanUsersTable() {
        Transaction transaction = session.beginTransaction();
        session.createSQLQuery("Truncate table users").executeUpdate();
       transaction.commit();
      // session.close();
    }
}
