package GetAndImportDBFtoMySQL;

import model.entPznEntity;
import java.nio.charset.Charset;
import java.util.Date;

//заполнение таблицы PZN данными из PZN.DBF
public class GetPZN extends AbstractDBFMappingAlgorithm {
    @Override
    public IDBFMarkUp parseDefiniteTable(Object[] row) {

        // Optional<Integer> yearDifference
        //int diff = yearDifference.isPresent() ? yearDifference.get() : 0;

        entPznEntity pznDBF = new entPznEntity();


        String pzn = new String((byte[]) row[1], Charset.forName("Cp866"));
        String imy = new String((byte[]) row[2], Charset.forName("Cp866"));
        String name = new String((byte[]) row[3], Charset.forName("Cp866"));

        Date cbDate = (Date) row[4];
        Date ceDate = (Date) row[5];

        if (ceDate.getYear() - (new Date()).getYear() > 200) {
            ceDate = null;
        }


        if (cbDate.getYear() - (new Date()).getYear() > 200) {
            cbDate = null;
        }

        pznDBF.setupAllPZNfields(pzn,imy,name,cbDate,ceDate);

        return pznDBF;
    }
}
