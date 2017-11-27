package CRUD;

import HibStuff.HibernateUtil;
import com.mysql.jdbc.exceptions.MySQLIntegrityConstraintViolationException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.util.StringConverter;
import model.*;
import org.hibernate.Session;
import org.hibernate.query.Query;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

import static CRUD.HibDAO.showAlert;

public class Controller {

    //    private final Configuration cfg = new Configuration().configure("hibernate.cfg.xml");
//    private final StandardServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(cfg.getProperties()).build();
//    private final SessionFactory sf = cfg.configure().buildSessionFactory(serviceRegistry);

    private HibDAO hibDAO;

    //для удаления
    private entBnkseekEntity currentBNKSEEK = null;

    @FXML
    private Button editButton;
    @FXML
    private Button newButton;
    @FXML
    private Button deleteButton;
    @FXML
    private Button clearButton;

    private ObservableList<entUerEntity> uerComboData;
    private ObservableList<entPznEntity> pznComboData;
    private ObservableList<entRegEntity> regComboData;
    private ObservableList<entTnpEntity> tnpComboData;

    @FXML
    private CheckBox rgnCheckBox;

    @FXML
    private CheckBox pznCheckBox;

    @FXML
    private Button filterButton;

    @FXML
    private Button populateTableByBNKSEEKsNAMEPbutton;

    private ObservableList<entBnkseekEntity> bnkseekTableData;
    @FXML
    private TableView<entBnkseekEntity> bnkseekEntityTable;
    @FXML
    private TableColumn<entBnkseekEntity, String> NAMEPcolumn;

    @FXML
    private ComboBox<entUerEntity> uerCombo;

    @FXML
    private ComboBox<entPznEntity> pznCombo;

    @FXML
    private ComboBox<entRegEntity> regCombo;

    @FXML
    private ComboBox<entTnpEntity> tnpCombo;

    @FXML
    private TextField real2;

    @FXML
    private TextField ind;

    @FXML
    private TextField nnp;

    @FXML
    private TextField adr;

    @FXML
    private TextField rkc;

    @FXML
    private TextField namep;

    @FXML
    private TextField newnum;

    @FXML
    private TextField rkcFilter;

    @FXML
    private ComboBox<entPznEntity> pznFilter;

    @FXML
    private ComboBox<entRegEntity> rgnFilter;


    @FXML
    public void initialize() {

        hibDAO = new HibDAO();

        fillAndSetCombos();

        bnkseekEntityTable.setEditable(true);
        bnkseekTableData = FXCollections.observableArrayList(readBNKSEEKtotally());
        bnkseekEntityTable.setItems(bnkseekTableData);

        bnkseekEntityTable.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                clearBNKSEEKtextfields();

                entBnkseekEntity entity = bnkseekEntityTable.getSelectionModel().getSelectedItem();
                currentBNKSEEK = entity;
                real2.setText(entity.getReal2());

                Integer pznId = entity.getPzn();
                entPznEntity thisBNKSEEKsPZN = hibDAO.getNameById(entPznEntity.class, pznId, "id");

                if (!(thisBNKSEEKsPZN == null)) {
                    pznCombo.setValue(thisBNKSEEKsPZN);
                }

                Integer uerId = entity.getUer();
                entUerEntity thisBNKSEEKsUER = hibDAO.getNameById(entUerEntity.class, uerId, "id");
                if (!(thisBNKSEEKsUER == null)) {
                    uerCombo.setValue(thisBNKSEEKsUER);
                }

                Integer rgnId = entity.getRgn();
                entRegEntity thisBNKSEEKsRGN = hibDAO.getNameById(entRegEntity.class, rgnId, "id");
                if (!(thisBNKSEEKsRGN == null)) {
                regCombo.setValue(thisBNKSEEKsRGN);
                }

                ind.setText(entity.getInd());

                Integer tnpId = entity.getTnp();
                entTnpEntity thisBNKSEEKsTNP = hibDAO.getNameById(entTnpEntity.class, tnpId, "id");
                if (!(thisBNKSEEKsTNP == null)) {
                    tnpCombo.setValue(thisBNKSEEKsTNP);
                }

                nnp.setText(entity.getNnp());
                adr.setText(entity.getAdr());
                rkc.setText(entity.getRkc());
                namep.setText(entity.getNamep());
                newnum.setText(entity.getNewnnum());
            }
        });

        NAMEPcolumn.setCellValueFactory(new PropertyValueFactory<entBnkseekEntity, String>("namep"));

    }



    @SuppressWarnings("Duplicates")
    public List<entBnkseekEntity> readBNKSEEKtotally() {
        return hibDAO.readAll(entBnkseekEntity.class);
    }

    //для фильтра

    @SuppressWarnings("Duplicates")
    public List<entBnkseekEntity> readBNKSEEKwithFilters() {

        String rkcString = rkcFilter.getText();
        entRegEntity rgnFiltrationEEntity = rgnFilter.getSelectionModel().getSelectedItem();
        entPznEntity pznFiltrationEntity = pznFilter.getSelectionModel().getSelectedItem();

        boolean pzn=pznCheckBox.isSelected();
        boolean rgn = rgnCheckBox.isSelected();

        List<entBnkseekEntity> bnkseekEntities = hibDAO.readAllWithFilters(rgnFiltrationEEntity, pznFiltrationEntity, rkcString, pzn, rgn);

        return bnkseekEntities;
    }

    public void fillAndSetCombos() {

        uerComboData = FXCollections.observableArrayList(hibDAO.readAll(entUerEntity.class));
        uerCombo.setItems(uerComboData);
        uerCombo.setConverter(new StringConverter<entUerEntity>() {

            @Override
            public String toString(entUerEntity object) {
                return object.getUername();
            }

            @Override
            public entUerEntity fromString(String string) {
                return uerCombo.getItems().stream().filter(ap ->
                        ap.getUername().equals(string)).findFirst().orElse(null);
            }
        });


        pznComboData = FXCollections.observableArrayList(hibDAO.readAll(entPznEntity.class));
        pznCombo.setItems(pznComboData);
        pznCombo.setConverter(new StringConverter<entPznEntity>() {

            @Override
            public String toString(entPznEntity object) {
                return object.getName();
            }

            @Override
            public entPznEntity fromString(String string) {
                return pznCombo.getItems().stream().filter(ap ->
                        ap.getName().equals(string)).findFirst().orElse(null);
            }
        });

        //такой же pzn комбо, но для фильтра
        pznFilter.setItems(FXCollections.observableArrayList(hibDAO.readAll(entPznEntity.class)));
        pznFilter.setConverter(new StringConverter<entPznEntity>() {

            @Override
            public String toString(entPznEntity object) {
                return object.getName();
            }

            @Override
            public entPznEntity fromString(String string) {
                return pznFilter.getItems().stream().filter(ap ->
                        ap.getName().equals(string)).findFirst().orElse(null);
            }
        });

        tnpComboData = FXCollections.observableArrayList(hibDAO.readAll(entTnpEntity.class));
        tnpCombo.setItems(tnpComboData);
        tnpCombo.setConverter(new StringConverter<entTnpEntity>() {

            @Override
            public String toString(entTnpEntity object) {
                return object.getFullname();
            }

            @Override
            public entTnpEntity fromString(String string) {
                return tnpCombo.getItems().stream().filter(ap ->
                        ap.getFullname().equals(string)).findFirst().orElse(null);
            }
        });

        regComboData = FXCollections.observableArrayList(hibDAO.readAll(entRegEntity.class));
        regCombo.setItems(regComboData);
        regCombo.setConverter(new StringConverter<entRegEntity>() {

            @Override
            public String toString(entRegEntity object) {
                return object.getName();
            }

            @Override
            public entRegEntity fromString(String string) {
                return regCombo.getItems().stream().filter(ap ->
                        ap.getName().equals(string)).findFirst().orElse(null);
            }
        });

        rgnFilter.setItems(FXCollections.observableArrayList(hibDAO.readAll(entRegEntity.class)));
        rgnFilter.setConverter(new StringConverter<entRegEntity>() {

            @Override
            public String toString(entRegEntity object) {
                return object.getName();
            }

            @Override
            public entRegEntity fromString(String string) {
                return rgnFilter.getItems().stream().filter(ap ->
                        ap.getName().equals(string)).findFirst().orElse(null);
            }
        });
    }


    public void clearBNKSEEKtextfields() {
        real2.clear();
        ind.clear();
        nnp.clear();
        adr.clear();
        rkc.clear();
        namep.clear();
        newnum.clear();
        uerCombo.getSelectionModel().clearSelection();
        pznCombo.getSelectionModel().clearSelection();
        regCombo.getSelectionModel().clearSelection();
        pznCombo.getSelectionModel().clearSelection();
    }


    //фильтрация

    public void performFiltration() {
        bnkseekTableData = FXCollections.observableArrayList(readBNKSEEKwithFilters());
        bnkseekEntityTable.setItems(bnkseekTableData);
        clearBNKSEEKtextfields();
    }


    public void checkAndSetFieldsINentBNKSEEKentity(entBnkseekEntity bnk) {

        bnk.setReal2(real2.getText());
        try {
            bnk.setPzn(pznCombo.getSelectionModel().getSelectedItem().getId());
        } catch (NullPointerException e) {
            bnk.setPzn(null);
        }
        try {
            bnk.setUer(uerCombo.getSelectionModel().getSelectedItem().getId());
        } catch (NullPointerException e) {
            showAlert("UER не должно быть нулем!");
            return;
        }
        try {
            bnk.setRgn(regCombo.getSelectionModel().getSelectedItem().getId());
        } catch (NullPointerException e) {
            showAlert("RGN не должно быть нулем!");
            return;
        }
        bnk.setInd(ind.getText());
        try {
            bnk.setTnp(tnpCombo.getSelectionModel().getSelectedItem().getId());
        } catch (NullPointerException e) {
            bnk.setTnp(null);
        }
        bnk.setNnp(nnp.getText());
        bnk.setAdr(adr.getText());
        bnk.setRkc(rkc.getText());


        if (namep.getText().trim().isEmpty()) {
            showAlert("Поле NAMEP не должно быть нулем!");
            return;
        } else {
            bnk.setNamep(namep.getText());
        }

        bnk.setNewnnum(newnum.getText());
    }


    public void addNewentBnkseekEntity() throws MySQLIntegrityConstraintViolationException {
        entBnkseekEntity bnk = new entBnkseekEntity();
        checkAndSetFieldsINentBNKSEEKentity(bnk);
        hibDAO.create(bnk);
        performFiltration();
    }

    public void deleteCurrentBnkseekEntity() {

        if (currentBNKSEEK == null) {
            showAlert("Не выбрана запись для удаления!");
            return;
        }
        hibDAO.delete(currentBNKSEEK);

        currentBNKSEEK = null;
        performFiltration();
    }

    public void editCurrentBnkseekEntity() {
        if (currentBNKSEEK == null) {
            showAlert("Не выбрана запись для редактирования!");
            return;
        } else {
            checkAndSetFieldsINentBNKSEEKentity(currentBNKSEEK);
            hibDAO.update(currentBNKSEEK);
        }

    }

}
