package GetAndImportDBFtoMySQL;

import model.*;
import org.hibernate.Session;
import org.hibernate.query.Query;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.nio.charset.Charset;
import java.util.Date;

//заполнение таблицы BNKSEEK данными из BNKSEEK.DBF
@SuppressWarnings("Duplicates")
public class GetBNKSEEK extends AbstractDBFMappingAlgorithm {

    //только для BNKSEEK
    //для преобразований значений pzn,uer ... в id-шники
    public static <C> C getIdFromName(Class<C> c, String fieldName, String fieldValue, Session session) {

        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<C> query = builder.createQuery(c);
        Root<C> root = query.from(c);
        query.select(root).where(builder.equal(root.get(fieldName), fieldValue));
        Query<C> q = session.createQuery(query);
        C entity = null;

        try {
            entity = q.getSingleResult();
        } catch (javax.persistence.NoResultException e) {
            e.printStackTrace();
            System.out.println("контроль нулёвости entity!!!!!!!!");
        }
        return entity;
    }

    @Override
    public IDBFMarkUp parseDefiniteTable(Object[] row) {

        // Optional<Integer> yearDifference
        //int diff = yearDifference.isPresent() ? yearDifference.get() : 0;

        //перевести названия в id из таблиц pzn, uer, reg, tnp

        entBnkseekEntity bnkseekEntity = new entBnkseekEntity();
        Session session = getCurrentSession();

        String real2 = new String((byte[]) row[1], Charset.forName("Cp866"));

        String ind = new String((byte[]) row[5], Charset.forName("Cp866"));

        String uer = new String((byte[]) row[3], Charset.forName("Cp866"));
        entUerEntity uerEntity = getIdFromName(entUerEntity.class, "uer", uer, session);
        Integer uerId =  uerEntity.equals(null)?null:uerEntity.getId();

        String pznName = new String((byte[]) row[2], Charset.forName("Cp866"));
        entPznEntity pznEntity = getIdFromName(entPznEntity.class, "pzn", pznName, session);
        Integer pznId = pznEntity.equals(null)?null: pznEntity.getId();

        String rgn = new String((byte[]) row[4], Charset.forName("Cp866"));
        entRegEntity regEntity = getIdFromName(entRegEntity.class, "rgn", rgn, session);
        Integer regId = (regEntity==null)?null:regEntity.getId();

        String tnp = new String((byte[]) row[6], Charset.forName("Cp866"));
        entTnpEntity tnpEntity= getIdFromName(entTnpEntity.class, "tnp", tnp, session);
        Integer tnpId=(tnpEntity == null)?null:tnpEntity.getId();

        String nnp = new String((byte[]) row[7], Charset.forName("Cp866"));
        System.out.println("nnp="+nnp);
        String adr = new String((byte[]) row[8], Charset.forName("Cp866"));
        String rkc = new String((byte[]) row[9], Charset.forName("Cp866"));
        String namep = new String((byte[]) row[10], Charset.forName("Cp866"));
        String newnnum = new String((byte[]) row[12], Charset.forName("Cp866"));
        String telef = new String((byte[]) row[18], Charset.forName("Cp866"));
        String regn = new String((byte[]) row[19], Charset.forName("Cp866"));
        String okpo = new String((byte[]) row[20], Charset.forName("Cp866"));
        Date dtIzm = (Date) row[21];
        String ksnp = new String((byte[]) row[23], Charset.forName("Cp866"));
        Date dateIn = (Date) row[24];
        Date dateCh = (Date) row[25];
//
        if (dtIzm.getYear() - (new Date()).getYear() > 200) {
            dtIzm = null;
        }

        if (dateIn.getYear() - (new Date()).getYear() > 200) {
            dateIn = null;
        }

        if (dateCh.getYear() - (new Date()).getYear() > 200) {
            dateCh = null;
        }

        bnkseekEntity.setReal2(real2);
        bnkseekEntity.setInd(ind);
        bnkseekEntity.setUer(uerId);
        bnkseekEntity.setPzn(pznId);
        bnkseekEntity.setTnp(tnpId);
        bnkseekEntity.setRgn(regId);
        bnkseekEntity.setNnp(nnp);
        bnkseekEntity.setAdr(adr);
        bnkseekEntity.setRkc(rkc);
        bnkseekEntity.setNamep(namep);
        bnkseekEntity.setNewnnum(newnnum);
        bnkseekEntity.setTelef(telef);
        bnkseekEntity.setRegn(regn);
        bnkseekEntity.setOkpo(okpo);
        bnkseekEntity.setDtIzm(dtIzm);
        bnkseekEntity.setKsnp(ksnp);
        bnkseekEntity.setDateIn(dateIn);
        bnkseekEntity.setDateCh(dateCh);
        return bnkseekEntity;
    }
}
