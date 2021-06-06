import java.awt.event.*;

public class MouseHandler implements MouseListener, MouseMotionListener {
    int mouseX, mouseY;
    boolean mouseClicked;

    @Override
    public void mouseDragged(MouseEvent e) {
        System.out.println("working");

    }

    @Override
    public void mouseMoved(MouseEvent e) {
        mouseX = e.getX();
        mouseY = e.getY();
        System.out.println("working");

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        System.out.println("working");

    }

    @Override
    public void mousePressed(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseExited(MouseEvent e) {
        // TODO Auto-generated method stub

    }

}
