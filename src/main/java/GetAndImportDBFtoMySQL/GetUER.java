package GetAndImportDBFtoMySQL;

import model.entUerEntity;

import java.nio.charset.Charset;

//заполнение таблицы uer данными из uer.DBF
public class GetUER extends AbstractDBFMappingAlgorithm {
    @Override
    public IDBFMarkUp parseDefiniteTable(Object[] row) {

        entUerEntity uer = new entUerEntity();

        String uerValue = new String((byte[]) row[1], Charset.forName("Cp866"));
        String uername = new String((byte[]) row[2], Charset.forName("Cp866"));

        uer.setupAllUERfields(uerValue,uername);

        return uer;
    }
}
