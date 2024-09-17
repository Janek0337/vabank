import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        Frame frame = new Frame();
        MenuGlowne menuGlowne = new MenuGlowne(frame);
        frame.addToFrame(menuGlowne, "menu główne");
        frame.showPanel("menu główne");
        SetUpGry setup = new SetUpGry(frame);
        frame.addToFrame(setup, "setup");
    }
}
