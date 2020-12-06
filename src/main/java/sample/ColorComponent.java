package sample;

import com.almasb.fxgl.entity.component.Component;
import javafx.scene.paint.Color;

public class ColorComponent extends Component {
    Color c;

    public Color getColor() {
        return c;
    }

    public void setColor(Color c) {
        this.c = c;
    }
}
