package algo.jeu;

import algo.rpg.Game;
import algo.rpg.enemy.Enemy;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;

public class GameControlleurr implements Initializable
{
    @FXML
    Label battleLogLabel;
    @FXML
    Label currentHeroLabel, currentHeroHPLabel, currentHeroResourcesLabel;
    @FXML
    ImageView hero1ImageView, hero2ImageView,hero3ImageView,hero4ImageView;
    @FXML
    ImageView bossImageView, basic1ImageView, basic2ImageView, basic3ImageView;

    @FXML
    ListView<String> enemyList;

    Game game = Selectionneur.game;

    int currentHeroInList = 0;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("Init game");

        setImages();
        setCurrentHeroInfo(0);

        game.generateEnemies();
        setEnemyList();
    }

    public void setEnemyList()
    {
        String[] toEnemyList = new String[game.enemyList.size()];
        for (int e = 0; e < game.enemyList.size(); e++) { toEnemyList[e] = game.enemyList.get(e).getType(); }
        enemyList.getItems().clear();
        enemyList.getItems().addAll(toEnemyList);
        enemyList.getSelectionModel().select(0);
    }

    public void setImages()
    {
        // Met en place les images des héros
        if(game.party.size() >= 1) { hero1ImageView.setImage( new Image(getClass().getResourceAsStream("img/" + game.party.get(0).getType() + ".png")) ); }
        if(game.party.size() >= 2) { hero2ImageView.setImage( new Image(getClass().getResourceAsStream("img/" + game.party.get(1).getType() + ".png")) ); }
        if(game.party.size() >= 3) { hero3ImageView.setImage( new Image(getClass().getResourceAsStream("img/" + game.party.get(2).getType() + ".png")) ); }
        if(game.party.size() == 4) { hero4ImageView.setImage( new Image(getClass().getResourceAsStream("img/" + game.party.get(3).getType() + ".png")) ); }

        // Met en place les images des ennemis
        bossImageView.setImage( new Image(getClass().getResourceAsStream("img/Boss.png")) ); // Gilgamesh !
        Image basicImage = new Image(getClass().getResourceAsStream("img/Basic_Enemy.png"));
        if(game.party.size() >= 2) { basic1ImageView.setImage( basicImage ); }
        if(game.party.size() >= 3) { basic2ImageView.setImage( basicImage ); }
        if(game.party.size() >= 4) { basic3ImageView.setImage( basicImage ); }
    }

    public void setCurrentHeroInfo (int currentHeroInList)
    {
        String heroClass = game.party.get(currentHeroInList).getType();
        currentHeroLabel.setText(heroClass);
        currentHeroHPLabel.setText("Life Points : " + game.party.get(currentHeroInList).getHp());

        switch(heroClass){
            case ("Warrior"):
                currentHeroResourcesLabel.setText("");
                break;
            case ("Hunter"):
                currentHeroResourcesLabel.setText("Arrows : " + game.party.get(currentHeroInList).getArrows());
                break;
            case ("Mage"):
            case ("Healer"):
                currentHeroResourcesLabel.setText("Mana Points : " + game.party.get(currentHeroInList).getManaPoints() );
                break;
        }
    }



    public void onButtonClick() throws InterruptedException
    {
        this.currentHeroInList = (this.currentHeroInList + 1) % 4;
        setCurrentHeroInfo(currentHeroInList);


        if (currentHeroInList == 0) {
            for (Enemy e : game.enemyList)
            {
                if(game.party.size() <= 0)
                {
                    alertPopup("Défaite");
                }
                else
                {
                    Random random = new Random();
                    int selection = random.nextInt(game.party.size());
                    e.attack(game.party.get(selection));
                    battleLogLabel.setText(e.getType() + " deals " + e.getWeaponDamage() + " to " + game.party.get(selection).getType());

                    if (!game.party.get(selection).isAlive()) {
                        game.party.remove(selection);
                    }
                }
            }
        }



    }

    private <défaite> void alertPopup(String "défaite") {

    }

    @FXML
    public void onAttackButtonClick() throws InterruptedException {
        int selection = enemyList.getSelectionModel().getSelectedIndex();
        game.party.get(currentHeroInList).attack(game.enemyList.get(selection));

        String battleLog = currentHeroLabel.getText() + " infligé " + game.party.get(currentHeroInList).getWeaponDamage() + " damage !";
        if (game.enemyList.get(selection).IsAlive()) {
        } else {
            battleLog = battleLog + " Tu as battu un ennemi";
            game.enemyList.remove(selection);
            setEnemyList();
        }
        battleLogLabel.setText(battleLog);

        if(game.enemyList.size() <= 0)
        {
            alertPopup("Victoire !");
        }

        onButtonClick();
    }

    public void onDefendButtonClick() throws InterruptedException {
        game.party.get(currentHeroInList).defend();
        battleLogLabel.setText(currentHeroLabel + "defend");

        onButtonClick();
    }

    public void onConsumablesButtonClick() throws InterruptedException {
        battleLogLabel.setText("Nothing happens...");

        onButtonClick();
    }

    private static void alertPopup(String title)
    {
        Stage window = new Stage();

        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);
        window.setMinWidth(250);

        Label label = new Label();
        String message = new String();
        label.setText(message);
        Button closeButton = new Button("Ok");
        closeButton.setOnAction(e -> {
            window.close();
            MainApplication.window.close();
        });

        VBox layout = new VBox(10);
        layout.getChildren().addAll(label, closeButton);
        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();
    }

}
