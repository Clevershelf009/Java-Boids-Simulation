import java.awt.*;

class Display extends Frame {

    Display() {
        setSize(300,300);//frame size 300 width and 300 height
        setVisible(true);//now frame will be visible, by default not visible
    }

    public static void main(String[] args){
        Display display = new Display();
    }
}