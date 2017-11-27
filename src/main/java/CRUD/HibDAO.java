package CRUD;

import HibStuff.HibernateUtil;
import javafx.scene.control.Alert;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.TransactionException;
import model.*;
import org.hibernate.query.Query;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("Duplicates")
public class HibDAO {

    public void delete(entBnkseekEntity currentBNKSEEK) {

        Transaction transaction = null;
        try (Session session = HibernateUtil.getSession()) {
            transaction = session.getTransaction();
            transaction.begin();
            session.delete(currentBNKSEEK);
            transaction.commit();
        } catch (TransactionException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } catch (Throwable c) {
            c.printStackTrace();
        }

        currentBNKSEEK = null;
    }

    public void update(entBnkseekEntity bnk) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSession()) {
            transaction = session.getTransaction();
            transaction.begin();
            session.update(bnk);
            transaction.commit();
            showAlert("Запись отредактирована!");
        } catch (TransactionException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } catch (Throwable c) {
            c.printStackTrace();
            showAlert("Поле Newnum должно быть уникальным!");
        }
    }

    public void create(entBnkseekEntity bnk) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSession()) {
            transaction = session.getTransaction();
            transaction.begin();

            session.save(bnk);
            transaction.commit();

            showAlert("Новая запись удачно создана!");
        } catch (TransactionException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } catch (Throwable c) {
            c.printStackTrace();
            showAlert("Поле Newnum должно быть уникальным!");
        }
    }


    public <C> List<C> readAll(Class c) {

        List<C> result = null;

        try (Session session = HibernateUtil.getSession()) {

            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<C> query = builder.createQuery(c);
            Root<C> root = query.from(c);
            query.select(root);
            result = session.createQuery(query).getResultList();

        } catch (Throwable c2) {
            c2.printStackTrace();
        }

        return result;

    }

    @SuppressWarnings("Duplicates")
    public List<entBnkseekEntity> readAllWithFilters(entRegEntity regEntity, entPznEntity pznEntity, String rkcString, boolean pzn, boolean rgn) {

        List<entBnkseekEntity> bnkseekEntities = null;

        try (Session session = HibernateUtil.getSession()) {

            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<entBnkseekEntity> query = builder.createQuery(entBnkseekEntity.class);
            Root<entBnkseekEntity> root = query.from(entBnkseekEntity.class);

            List<Predicate> predicates = new ArrayList<>();

            if (pzn && pznEntity != null)
                predicates.add(builder.equal(root.get("pzn"), pznEntity.getId()));

            if (rgn && regEntity != null) {
                predicates.add(
                        builder.equal(root.get("rgn"), regEntity.getId()));
            }
            if (!rkcString.trim().isEmpty()) {
                predicates.add(
                        builder.like(root.get("rkc"), "%" + rkcString + "%"));
            }

            query.select(root).where(predicates.toArray(new Predicate[]{}));

            bnkseekEntities = session.createQuery(query).getResultList();
        } catch (Throwable c) {
            c.printStackTrace();
        }

        return bnkseekEntities;
    }

    @SuppressWarnings("Duplicates")
    public <C> C getNameById(Class c, Integer id, String fieldName) {

        C entity = null;

        try (Session session = HibernateUtil.getSession()) {

            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<C> query = builder.createQuery(c);
            Root<C> root = query.from(c);
            query.select(root).where(builder.equal(root.get(fieldName), id));
            Query<C> q = session.createQuery(query);

            try {
                entity = q.getSingleResult();
            } catch (javax.persistence.NoResultException e) {
                System.out.println("контроль нулевости");
            }


        } catch (Throwable c2) {
            c2.printStackTrace();
        }

        return entity;

    }


    public static void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Информация");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

}
