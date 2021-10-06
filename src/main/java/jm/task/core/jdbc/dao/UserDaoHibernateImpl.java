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
//        Transaction transaction = session.beginTransaction();

        session.createSQLQuery("CREATE TABLE IF NOT EXISTS users (" +
                "id INTEGER not NULL AUTO_INCREMENT," +
                "name VARCHAR(255)," +
                "lastName VARCHAR (255)," +
                "age SMALLINT ," +
                "PRIMARY KEY (id))").executeUpdate();
        session.flush() ;
    }

    @Override
    public void dropUsersTable() {
        session.createSQLQuery("DROP TABLE IF EXISTS users ").executeUpdate();
        session.flush() ;
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        session.beginTransaction();
        User user = new User(name, lastName, age);
        session.save(user);
        session.getTransaction().commit();
//        session.flush() ;
    }

    @Override
    public void removeUserById(long id) {
//        User user = (User) session.load(User.class, id);
//        session.delete(user);
        User user = (User)session.get(User.class,id);
        session.delete(user);
        session.flush() ;
    }

    @Override
    public List<User> getAllUsers() {
        Criteria criteria;
        criteria = session.createCriteria(User.class);
        session.flush() ;
        return criteria.list();
    }

    @Override
    public void cleanUsersTable() {
        session.createSQLQuery("Truncate table users").executeUpdate();
        session.flush() ;
    }
}
