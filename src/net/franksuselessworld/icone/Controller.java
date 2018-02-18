package net.franksuselessworld.icone;

import com.google.common.net.MediaType;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import org.apache.tika.Tika;
import java.io.*;
import java.util.Properties;

public class Controller {
    private static final String SETTINGS_FILE_NAME = "icone.propertie";
    private static final String LANG_PROPERTY_NAME = "lang";

    public ComboBox<String> langComboBox;
    public TabPane mainWindow;
    public Button saveSettingsButton;
    public GridPane imagesGridPane;
    public Button addImageButton;

    private String os;
    private Runtime rt;
    private Properties settings;
    private Tika tikaFacade;

    public Controller() {
        os = System.getProperty("os.name").toLowerCase();
        rt = Runtime.getRuntime();
        settings = new Properties();
        tikaFacade = new Tika();
    }

    @FXML
    public void initialize() {
        File settingsFile = new File(SETTINGS_FILE_NAME);
        boolean settingsFileCreated;

        if (!settingsFile.exists()) {
            try {
                settingsFileCreated = settingsFile.createNewFile();

                if (settingsFileCreated) {
                    saveSettingsToFile("English");
                } else {
                    System.err.println("Can't create settings file");
                }
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }
        }

        loadSettingsFromFile();

        langComboBox.valueProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            if (saveSettingsButton.isDisabled()) saveSettingsButton.setDisable(false);
        });
    }

    private boolean isWindows() {
        return os.contains("win");
    }

    private boolean isLinux() {
        return os.contains("nix") || os.contains("nux");
    }

    private boolean isMac() {
        return os.contains("mac");
    }

    private void openWebpage(String pUrl) {
        try {
            if (isWindows()) {
                rt.exec("rundll32 url.dll, FileProtocolHandler " + pUrl);
            } else if (isMac()) {
                rt.exec("open " + pUrl);
            } else if (isLinux()) {
                rt.exec("xdg-open " + pUrl);
            } else {
                System.err.println("OS unknown : " + os);
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    private void saveSettingsToFile(String pLang) {
        OutputStream settingsFile = null;

        try {
            settingsFile = new FileOutputStream(SETTINGS_FILE_NAME);

            settings.setProperty(LANG_PROPERTY_NAME, pLang);

            settings.store(settingsFile, "Icone settings");
        } catch (FileNotFoundException fnfe) {
            System.err.println("Settings file doesn't exist");
        } catch (IOException ioe) {
            ioe.printStackTrace();
        } finally {
            if (settingsFile != null) {
                try {
                    settingsFile.close();
                    saveSettingsButton.setDisable(true);
                } catch (IOException ioe) {
                    ioe.printStackTrace();
                }
            }
        }
    }

    private void loadSettingsFromFile() {
        InputStream settingsFile = null;

        try {
            settingsFile = new FileInputStream(SETTINGS_FILE_NAME);

            settings.load(settingsFile);

            langComboBox.setValue(settings.getProperty(LANG_PROPERTY_NAME));
        } catch (FileNotFoundException fnfe) {
            System.err.println("Settings file doesn't exist");
        } catch (IOException ioe) {
            ioe.printStackTrace();
        } finally {
            if (settingsFile != null) {
                try {
                    settingsFile.close();
                    saveSettingsButton.setDisable(true);
                } catch (IOException ioe) {
                    ioe.printStackTrace();
                }
            }
        }
    }

    private VBox createImageContainer() {
        Text imageLabel = new Text("Add an image");
        imageLabel.getStyleClass().add("paragraph");

        Button removeImageButton = new Button("-");
        removeImageButton.setOnAction(this::removeImage);

        FlowPane imageHeader = new FlowPane(10, 10);
        imageHeader.getChildren().add(imageLabel);
        imageHeader.getChildren().add(removeImageButton);

        ImageView image = new ImageView();
        image.setFitHeight(256);
        image.setFitWidth(256);
        image.setPickOnBounds(true);
        image.setPreserveRatio(true);
        image.getStyleClass().add("addImage");
        image.setOnMouseClicked(this::openImage);

        VBox imageContainer = new VBox(10);
        imageContainer.getChildren().add(imageHeader);
        imageContainer.getChildren().add(image);

        return imageContainer;
    }

    private void rearrangeImagesGridPane() {
        int nbColumns = imagesGridPane.getColumnConstraints().size();

        int indexRow = 0;
        int indexColumn = 0;

        for (Node imageContainer : imagesGridPane.getChildren()) {
            GridPane.setRowIndex(imageContainer, indexRow);
            GridPane.setColumnIndex(imageContainer, indexColumn);

            if (indexColumn == (nbColumns - 1)) {
                indexColumn = 0;
                indexRow++;
            } else {
                indexColumn++;
            }
        }
    }

    private void showAlertDialog(String pTitle, String pMsg) {
        Alert alertDialog = new Alert(Alert.AlertType.WARNING);
        DialogPane alertDialogPane = alertDialog.getDialogPane();

        alertDialogPane.getStylesheets().add(getClass().getResource("Icone.css").toExternalForm());
        alertDialog.setTitle(pTitle);
        alertDialog.setHeaderText(null);
        alertDialog.setContentText(pMsg);

        alertDialog.showAndWait();
    }

    public void goToUrl(MouseEvent mouseEvent) {
        openWebpage(((Text) mouseEvent.getTarget()).getText());
    }

    public void goToGitHub(MouseEvent mouseEvent) {
        openWebpage("https://github.com/MonstroMart/Icone/");
    }

    public void goToReleasesHistory(MouseEvent mouseEvent) {
        openWebpage("https://github.com/MonstroMart/Icone/releases/");
    }

    public void saveSettings(ActionEvent actionEvent) {
        saveSettingsToFile(langComboBox.getValue());
    }

    public void openImage(MouseEvent mouseEvent) {
        FileChooser imageFileChooser = new FileChooser();
        imageFileChooser.setTitle("Choose an image file");

        File imageFile = imageFileChooser.showOpenDialog(mainWindow.getScene().getWindow());

        if (imageFile != null && imageFile.exists()) {
            Image imageObject = new Image(imageFile.toURI().toString());

            String imageMimeType = null;

            try {
                imageMimeType = tikaFacade.detect(imageFile);
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }

            if (imageMimeType != null && (MediaType.parse(imageMimeType).equals(MediaType.PNG) || MediaType.parse(imageMimeType).equals(MediaType.BMP)) && imageObject.getHeight() == imageObject.getWidth()) {
                ImageView imageViewObject = (ImageView) mouseEvent.getTarget();

                imageViewObject.setFitHeight(imageObject.getHeight());
                imageViewObject.setFitWidth(imageObject.getWidth());
                imageViewObject.setImage(imageObject);

                VBox imageContainer = (VBox) imageViewObject.getParent();
                FlowPane imageHeader = (FlowPane) imageContainer.getChildren().get(0);
                Text imageLabel = (Text) imageHeader.getChildren().get(0);

                imageLabel.setText(String.valueOf((int) imageObject.getWidth()) + "x" + String.valueOf((int) imageObject.getHeight()) + " (" + MediaType.parse(imageMimeType).subtype() + ")");
            } else {
                showAlertDialog("Wrong image file type and/or size!", "You can only create icons from png and bmp image files having the same width and height.");
            }
        }
    }

    public void addImage(ActionEvent actionEvent) {
        int nbImages = imagesGridPane.getChildrenUnmodifiable().size();

        if (nbImages < 12) {
            imagesGridPane.getChildren().add(createImageContainer());

            rearrangeImagesGridPane();

            if (nbImages == 11) {
                addImageButton.setDisable(true);
            }
        } else {
            showAlertDialog("Too many images!", "You can't add more than 12 images.");
        }
    }

    public void removeImage(ActionEvent actionEvent) {
        imagesGridPane.getChildren().remove(((Button) actionEvent.getTarget()).getParent().getParent());

        rearrangeImagesGridPane();

        addImageButton.setDisable(false);
    }

    public void saveIcon(ActionEvent actionEvent) {
        // TODO : saveIcon
    }
}