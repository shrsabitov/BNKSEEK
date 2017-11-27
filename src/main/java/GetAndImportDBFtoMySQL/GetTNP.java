package GetAndImportDBFtoMySQL;

import model.*;

import java.nio.charset.Charset;

//заполнение таблицы tnp данными из tnp.DBF
public class GetTNP extends AbstractDBFMappingAlgorithm {
    @Override
    public IDBFMarkUp parseDefiniteTable(Object[] row) {

        entTnpEntity tnpDbf = new entTnpEntity();

        String tnp = new String((byte[]) row[1], Charset.forName("Cp866"));
        String fullname = new String((byte[]) row[2], Charset.forName("Cp866"));
        String shortname = new String((byte[]) row[3], Charset.forName("Cp866"));

        tnpDbf.setupAllTNPfields(tnp,fullname,shortname);

        return tnpDbf;


    }



}
