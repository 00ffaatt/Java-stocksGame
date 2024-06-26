package com.example;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * JavaFX App
 */
public class App extends Application {
    private Game game;
    private Label playerName;
    private Label playerCoins;
    private Label cardsInDeck;
    private ListView<Stock> handListView;
    private ListView<Stock> marketListView;
    private ListView<Stock> publicListView;
    private Button drawButton;
    private Button discardButton;
    private Button publicizeButton;
    private GameClient client;

    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        game = new Game(2);
        handListView = new ListView<>();
        marketListView = new ListView<>();
        publicListView = new ListView<>();

        BorderPane root = new BorderPane();
        VBox playerInfo = createPlayerInfoPane();
        VBox playerHand = createPlayerHandPane();
        VBox marketView = createMarketViewPane();
        VBox publicView = createPublicSharesViewPane();
        HBox controlPanel = createControlPanel();

        updateUI();

        root.setTop(playerInfo);
        root.setCenter(playerHand);
        root.setLeft(publicView);
        root.setRight(marketView);
        root.setBottom(controlPanel);

        scene = new Scene(root, 800, 600);
        stage.setTitle("Stock Market Game");
        stage.setScene(scene);
        stage.show();

        // Initialize and connect GameClient
        client = new GameClient("localhost", 12345, this);
        client.connect();
    }

    private VBox createPlayerInfoPane() {
        VBox playerInfo = new VBox();
        playerInfo.setPadding(new Insets(10));
        playerInfo.setSpacing(8);

        playerName = new Label("Player: " + game.getCurrentPlayer().getName());
        playerCoins = new Label("Coins: " + game.getCurrentPlayer().getMoney());
        cardsInDeck = new Label("Cards left in deck: " + game.getNumberCardsLeft());

        playerInfo.getChildren().addAll(playerName, playerCoins, cardsInDeck);

        return playerInfo;
    }

    private VBox createPlayerHandPane() {
        VBox playerHand = new VBox();
        playerHand.setPadding(new Insets(10));
        playerHand.setSpacing(8);

        Label handLabel = new Label("Player Hand");

        playerHand.getChildren().addAll(handLabel, handListView);

        return playerHand;
    }

    private VBox createMarketViewPane() {
        VBox marketView = new VBox();
        marketView.setPadding(new Insets(10));
        marketView.setSpacing(8);

        Label marketLabel = new Label("Market");

        marketView.getChildren().addAll(marketLabel, marketListView);

        return marketView;
    }

    private VBox createPublicSharesViewPane() {
        VBox publicView = new VBox();
        publicView.setPadding(new Insets(10));
        publicView.setSpacing(8);

        Label publicViewLabel = new Label("Your Public Shares");
        publicView.getChildren().addAll(publicViewLabel, publicListView);

        return publicView;
    }

    private HBox createControlPanel() {
        HBox controlPanel = new HBox();
        controlPanel.setPadding(new Insets(10));
        controlPanel.setSpacing(8);

        drawButton = new Button("Draw");
        drawButton.setOnAction(e -> handleDrawAction());

        discardButton = new Button("Discard to Market");
        discardButton.setOnAction(e -> handleDiscardAction());

        publicizeButton = new Button("Publicize");
        publicizeButton.setOnAction(e -> handlePublicizeAction());

        Button endTurnButton = new Button("End Turn");
        endTurnButton.setOnAction(e -> handleEndTurnAction());

        controlPanel.getChildren().addAll(drawButton, discardButton, publicizeButton, endTurnButton);

        return controlPanel;
    }

    private void handleDrawAction() {
        client.sendMessage("DRAW");
        disableButton(drawButton);
    }

    private void handleDiscardAction() {
        Stock selectedStock = handListView.getSelectionModel().getSelectedItem();
        if (selectedStock == null) {
            showError("No stock selected", "Please select a stock to discard.");
            return;
        }
        client.sendMessage("DISCARD " + selectedStock.getCompanyName());
        disableButton(publicizeButton);
        disableButton(discardButton);
    }

    private void handlePublicizeAction() {
        Stock selectedStock = handListView.getSelectionModel().getSelectedItem();
        if (selectedStock == null) {
            showError("No stock selected", "Please select a stock to discard.");
            return;
        }
        client.sendMessage("PUBLICIZE " + selectedStock.getCompanyName());
        disableButton(discardButton);
        disableButton(publicizeButton);
    }

    private void handleEndTurnAction() {
        client.sendMessage("ENDTURN");
        enableButton(discardButton);
        enableButton(drawButton);
        enableButton(publicizeButton);
    }

    private void showError(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public void updateUI() {
        Player currentPlayer = game.getCurrentPlayer();
        playerName.setText("Player: " + currentPlayer.getName());
        playerCoins.setText("Coins: " + currentPlayer.getMoney());
        cardsInDeck.setText("Cards left in Deck: " + game.getNumberCardsLeft());

        handListView.getItems().clear();
        handListView.getItems().addAll(currentPlayer.getHand().revealHand());

        marketListView.getItems().clear();
        marketListView.getItems().addAll(game.getMarket().getStocks());

        publicListView.getItems().clear();
        publicListView.getItems().addAll(currentPlayer.publicShares());
    }

    public void disableButton(Button pButton) {
        pButton.setDisable(true);
    }

    public void enableButton(Button pButton) {
        pButton.setDisable(false);
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
