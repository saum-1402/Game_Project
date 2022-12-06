package Scenes;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.badlogic.mygame.MyGame;

public class HUD {
    public Stage stage;
    public Viewport view_port;

    private Integer health1;
    private Integer health2;

    public HUD(SpriteBatch sb){
        Pixmap pixmap = new Pixmap(100, 20, Pixmap.Format.RGBA8888);
        pixmap.setColor(Color.RED);
        pixmap.fill();
        health1=100;
        health2=100;

        view_port=new FitViewport(MyGame.V_WIDTH,MyGame.V_HEIGHT,new OrthographicCamera());
        stage = new Stage(view_port,sb);

        Table table=new Table();
        table.top();
        table.setFillParent(true);

        Label health1_label = new Label(String.format("%06d", health1), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        Label health2_label = new Label(String.format("%06d", health2), new Label.LabelStyle(new BitmapFont(), Color.WHITE));

        table.row();
        table.add(health1_label).expandX();
        table.add(health2_label).expandX();

        stage.addActor(table);
    }

}
