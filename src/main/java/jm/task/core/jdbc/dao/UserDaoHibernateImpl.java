package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import javax.persistence.Query;
import java.util.List;

import static jm.task.core.jdbc.util.Util.getSessionFactory;

public class UserDaoHibernateImpl implements UserDao {
    public UserDaoHibernateImpl() {

    }


    @Override
    public void createUsersTable() {
        try (Session session = Util.getSession()) {
            session.beginTransaction();
            session.createSQLQuery("create table if not exists users (id bigint auto_increment, name varchar(50), lastName varchar(50), age int, primary key(id))").executeUpdate();
            session.getTransaction().commit();
            session.close();
        } catch (HibernateException ignore) {
        }
    }

    @Override
    public void dropUsersTable() {
        try (Session session = Util.getSession()) {
            Transaction transaction = session.beginTransaction();
            session.createSQLQuery("DROP TABLE IF EXISTS users").executeUpdate();
            transaction.commit();
        } catch (HibernateException ignore) {
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        Transaction transaction = null;
        try (Session session = Util.getSession()) {
            transaction = session.beginTransaction();
            session.save(new User(name, lastName, age));
            transaction.commit();
            session.close();
        } catch (HibernateException ignore) {
        }
    }

    @Override
    public void removeUserById(long id) {
        try (Session session = Util.getSession()) {
            User user = new User();
            user.setId(id);
            session.beginTransaction();
            session.delete(user);
            session.getTransaction().commit();
            session.close();
        } catch (HibernateException ignore) {
        }
    }

    @Override
    public List<User> getAllUsers() {
        List<User> result = null;
        try (Session session = Util.getSession()) {
            result = session.createQuery("from User", User.class).list();
            return result;
        } catch (HibernateException ignore) {
        }
        return result;
    }

    @Override
    public void cleanUsersTable() {
        try (Session session = Util.getSession()) {
            session.beginTransaction();
            String hql = "delete from User";
            Query query = session.createQuery(hql);
            query.executeUpdate();
            session.getTransaction().commit();
            session.close();
        } catch (HibernateException ignore) {

        }
    }
}
