package GetAndImportDBFtoMySQL;

public class Get {

    public static void main(String[] args) {
//
//        GetREG regDBF = new GetREG();
//        regDBF.GetIt("E:\\CBRTest\\tk_spr3193\\reg.DBF");
//
//        GetUER uerDBF = new GetUER();
//        uerDBF.GetIt("E:\\CBRTest\\tk_spr3193\\uer.DBF");
//
//        GetPZN pznDBF = new GetPZN();
//        pznDBF.GetIt("E:\\CBRTest\\tk_spr3193\\pzn.DBF");
//
//        GetTNP tnpDBF = new GetTNP();
//        tnpDBF.GetIt("E:\\CBRTest\\tk_spr3193\\tnp.DBF");

        GetBNKSEEK bnkseek = new GetBNKSEEK();
        bnkseek.GetIt("E:\\CBRTest\\tk_spr3193\\bnkseek.DBF");


//        String dbfInfo = DbfProcessor.readDbfInfo(new File("E:\\CBRTest\\tk_spr3193\\pzn.DBF"));
//        System.out.println(dbfInfo);

    }

}
