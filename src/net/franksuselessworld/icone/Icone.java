/*
 * Copyright 2017-2018 Francis Chabot
 *
 * This file is part of Icone <https://github.com/MonstroMart/Icone>.
 *
 * Icone is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Icone is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Oncoupe. If not, see <http://www.gnu.org/licenses/>.
 */

package net.franksuselessworld.icone;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * <h1>Icone</h1>
 *
 * <p>Icone is a simple icons maker letting you create icons from any png, jpg or svg image files.</p>
 *
 * @author  Francis Chabot
 * @version 0.1.0
 */
public class Icone extends Application {
    public Icone() {
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("Icone.fxml"));
        primaryStage.setTitle("Icone");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}