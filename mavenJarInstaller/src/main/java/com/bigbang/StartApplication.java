/**
 * create at 2020年4月28日
 */
package com.bigbang;

import com.bigbang.controller.MainController;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * <b>Desc</b>: 应用启动类. <br/>
 *
 * @author pzj <br/>
 * 
 */
public class StartApplication extends Application {

	private Stage primaryStage;

	@Override
	public void start(Stage primaryStage) throws Exception {
		this.primaryStage = primaryStage;
		
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Main.fxml"));
		Parent root = loader.load();
		Scene scene = new Scene(root);
		primaryStage.setTitle("MavenJarInstaller");
		primaryStage.setResizable(false);
		primaryStage.setScene(scene);
		primaryStage.show();
		
		// 必须在stage show操作之后，不然该实例会是null
		MainController mainCtl = loader.getController();
		mainCtl.set(this);
	}

	public Stage getPrimaryStage() {
		return primaryStage;
	}

	public static void main(String[] args) {
		launch(args);
	}
}
