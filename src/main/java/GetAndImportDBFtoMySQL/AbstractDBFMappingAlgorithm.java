package GetAndImportDBFtoMySQL;

import HibStuff.HibernateUtil;
import org.hibernate.Session;
import org.jamel.dbf.DbfReader;
import java.io.File;

@SuppressWarnings("Duplicates")
public abstract class AbstractDBFMappingAlgorithm {

    private Session seans;

    protected Session getCurrentSession() {
        return seans;
    }

    public void GetIt(String filename) {

        try (DbfReader reader = new DbfReader(new File(filename))) {

            try (Session session = HibernateUtil.getSession()) {
                seans = session;
                session.beginTransaction();

                Object[] row;
//                int k=0;
                while ((row = reader.nextRecord()) != null) {

                    IDBFMarkUp dbfEntity=parseDefiniteTable(row);
                    session.save(dbfEntity);
//                    k++;
//
//                    if (k == 2) {
//                        break;
//                    }
                }

                session.getTransaction().commit();
            } catch (Throwable c) {
                c.printStackTrace();
            }


        }
    }

    abstract public IDBFMarkUp parseDefiniteTable(Object[] row);

}
