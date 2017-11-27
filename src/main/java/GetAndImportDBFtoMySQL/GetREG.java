package GetAndImportDBFtoMySQL;

import model.entRegEntity;

import java.nio.charset.Charset;

//заполнение таблицы reg данными из reg.DBF
public class GetREG extends AbstractDBFMappingAlgorithm {
    @Override
    public IDBFMarkUp parseDefiniteTable(Object[] row) {
        entRegEntity region = new entRegEntity();

        String rgn = new String((byte[]) row[1], Charset.forName("Cp866"));
        String name = new String((byte[]) row[2], Charset.forName("Cp866"));
        String center = new String((byte[]) row[3], Charset.forName("Cp866"));
        String namet = new String((byte[]) row[4], Charset.forName("Cp866"));

        region.setupAllREGfields(rgn,name,center,namet);

        return region;
    }
}
