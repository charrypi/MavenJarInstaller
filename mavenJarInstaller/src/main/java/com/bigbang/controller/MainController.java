/**
 * create at 2020年4月28日
 */
package com.bigbang.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ResourceBundle;

import com.bigbang.StartApplication;
import com.bigbang.model.JarUploadConfig;
import com.bigbang.util.CmdUtil;

import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * <b>Desc</b>: 主页面. <br/>
 *
 * @author pzj <br/>
 * 
 */
public class MainController implements Initializable {

	@FXML
	private AnchorPane container;

	@FXML
	private Button selectFileBtn;

	@FXML
	private Button submitBtn;

	@FXML
	private ChoiceBox<Boolean> skipTestChoiceBox;

	@FXML
	private ChoiceBox<String> packagingChoiceBox;

	@FXML
	private TextField jarPathField;

	@FXML
	private TextField groupIdField;

	@FXML
	private TextField artifactField;

	@FXML
	private TextField versionField;

	private Stage alertWin;

	private StartApplication startApp;

	private ObservableList<Boolean> skipTestSelect = FXCollections.observableArrayList(true, false);

	private ObservableList<String> packagingSelect = FXCollections.observableArrayList("jar");

	public void initialize(URL location, ResourceBundle resources) {
		// 初始化skipTest下拉选择项
		skipTestChoiceBox.getItems().addAll(skipTestSelect);
		skipTestChoiceBox.setValue(skipTestSelect.get(0));

		// 初始化packaging下拉选择项
		packagingChoiceBox.getItems().addAll(packagingSelect);
		packagingChoiceBox.setValue(packagingSelect.get(0));

		// jarpath设置tooltip
		jarPathField.textProperty().addListener(new ChangeListener<String>() {

			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				jarPathField.setTooltip(new Tooltip(newValue));
			}
		});

	}

	@FXML
	public void submit(Event event) {
		try {
			if (alertWin != null && alertWin.isShowing()) {
				alertWin.hide();
			}
			JarUploadConfig config = new JarUploadConfig();
			config.setPath(jarPathField.getText());
			config.setGroupId(groupIdField.getText());
			config.setArtifactId(artifactField.getText());
			config.setVersion(versionField.getText());
			config.setSkipTest(skipTestChoiceBox.getValue());
			config.setPackaging(packagingChoiceBox.getValue());
			Process process = CmdUtil.runInstall(config);
			alertInstallMsg(process);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void alertInstallMsg(final Process process) throws Exception {
		alertWin = new Stage();
		alertWin.setResizable(false);
		alertWin.setWidth(700);
		alertWin.setHeight(500);
		alertWin.setTitle("install msg");
		VBox vBox = new VBox();
		final ProgressIndicator loadingBar = new ProgressIndicator();
		loadingBar.setProgress(-1);
		Label msgLabel = new Label();
		msgLabel.setPadding(new Insets(15));
		msgLabel.setWrapText(true);
		final SimpleStringProperty msgProperty = new SimpleStringProperty();
		msgLabel.textProperty().bind(msgProperty);

		Task<Void> task = new Task<Void>() {
			@Override
			protected Void call() throws Exception {
				InputStream is = process.getInputStream();
				BufferedReader reader = new BufferedReader(new InputStreamReader(is));
				final StringBuffer msg = new StringBuffer();
				String lineStr = null;
				while ((lineStr = reader.readLine()) != null) {
					msg.append(lineStr);
					msg.append("\n");
					Platform.runLater(new Runnable() {
						@Override
						public void run() {
							msgProperty.set(msg.toString());
						}
					});
				}
				loadingBar.setVisible(false);
				reader.close();
				return null;
			}
		};
		Thread thread = new Thread(task);
		thread.setDaemon(true);
		thread.start();
		vBox.getChildren().addAll(loadingBar, msgLabel);
		Scene scene = new Scene(vBox);
		alertWin.setScene(scene);
		alertWin.show();
	}

	@FXML
	public void selectFile(Event event) {
		FileChooser fc = new FileChooser();
		fc.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("jar", "*.jar"));
		fc.setTitle("select a jar file");
		File file = fc.showOpenDialog(startApp.getPrimaryStage());
		if (file != null) {
			jarPathField.setText(file.getAbsolutePath());
		}
	}

	public void set(StartApplication startApplication) {
		this.startApp = startApplication;
	}

}
