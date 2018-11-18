package poly.app.ui.test;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class TabCloseUI implements MouseListener, MouseMotionListener {

    private ClosableTabbedPane tabbedPane;
    private int closeX = 0, closeY = 0, meX = 0, meY = 0;
    private int selectedTab; // un int qui récupere l'onglet selectionné
    private final int width = 15, height = 15;  // largeur et hauteur de la croix
    private Rectangle rectangle = new Rectangle(0, 0, width, height); // la croix sera dessiné dans un rectangle

    /**
     * Le constructeur de la classe TabCloseUI prends un ClosableTabbedPane en
     * paramètre, car cela permet d'ajouter à ce JTabbedPane redéfinit la
     * fermeture des onglets.
     *
     * @param pane
     */
    public TabCloseUI(ClosableTabbedPane pane) {
        tabbedPane = pane;
        tabbedPane.addMouseMotionListener(this);
        tabbedPane.addMouseListener(this);
    }

    @Override
    public void mouseEntered(MouseEvent me) {
    }

    @Override
    public void mouseExited(MouseEvent me) {
    }

    @Override
    public void mousePressed(MouseEvent me) {
    }

    @Override
    public void mouseClicked(MouseEvent me) {
    }

    @Override
    public void mouseDragged(MouseEvent me) {
    }

    /**
     * Si on relache le bouton de la souris, on ferme l'onglet choisit.
     */
    public void mouseReleased(MouseEvent me) {
        if (closeUnderMouse(me.getX(), me.getY())) {
            boolean isToCloseTab = tabAboutToClose(selectedTab);
            if (isToCloseTab && selectedTab > -1) {
                tabbedPane.removeTabAt(selectedTab);
            }
            selectedTab = tabbedPane.getSelectedIndex();
        }
    }

    /**
     * La methode mouseMoved, permet si les coordonnes de la souris sont les
     * mêmes qu'un onglet d'afficher une infobulle et la croix permettant la
     * fermeture de l'onglet.
     */
    public void mouseMoved(MouseEvent me) {
        meX = me.getX();
        meY = me.getY();
        if (mouseOverTab(meX, meY)) {
            controlCursor();
            tabbedPane.repaint();
        }
    }

    /**
     * Contrôle de l'affichage du curseur, il y a possibilté d'afficher une
     * infobulle
     */
    private void controlCursor() {
        if (tabbedPane.getTabCount() > 0) {
            if (closeUnderMouse(meX, meY)) {
                tabbedPane.setCursor(new Cursor(Cursor.HAND_CURSOR));
                if (selectedTab > -1) {
                    tabbedPane.setToolTipTextAt(selectedTab, "Close "
                            + tabbedPane.getTitleAt(selectedTab));
                }
            } else {
                tabbedPane.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
                if (selectedTab > -1) {
                    tabbedPane.setToolTipTextAt(selectedTab, "");
                }
            }
        }
    }

    private boolean closeUnderMouse(int x, int y) {
        rectangle.x = closeX;
        rectangle.y = closeY;
        return rectangle.contains(x, y);
    }

    public void paint(Graphics g) {
        int tabCount = tabbedPane.getTabCount();
        for (int j = 0; j < tabCount; j++) {
            if (tabbedPane.getComponent(j).isShowing()) {
                int x = tabbedPane.getBoundsAt(j).x + tabbedPane.getBoundsAt(j).width - width - 5;
                int y = tabbedPane.getBoundsAt(j).y + 5;
                drawClose(g, x, y);
                break;
            }
        }
        if (mouseOverTab(meX, meY)) {
            drawClose(g, closeX, closeY);
        }
    }

    /**
     * La methode drawClose, permet d'afficher la croix voulu si la souris est
     * sur l'onglet
     *
     * @param g
     * @param x
     * @param y
     */
    private void drawClose(Graphics g, int x, int y) {
        if (tabbedPane != null && tabbedPane.getTabCount() > 0) {
            Graphics2D g2 = (Graphics2D) g;
            drawColored(g2, isUnderMouse(x, y) ? Color.GRAY : new Color(163, 163, 163), x, y);
        }
    }

    private void drawColored(Graphics2D g2, Color color, int x, int y) {
// change la taille de l'ombre de la croix
        g2.setStroke(new BasicStroke(3, BasicStroke.JOIN_ROUND, BasicStroke.CAP_ROUND));
        g2.setColor(Color.BLACK);
        g2.drawLine(x, y, x + width, y + height);
        g2.drawLine(x + width, y, x, y + height);
        g2.setColor(color);
        g2.setStroke(new BasicStroke(3, BasicStroke.JOIN_ROUND, BasicStroke.CAP_ROUND));
        g2.drawLine(x, y, x + width, y + height);
        g2.drawLine(x + width, y, x, y + height);
    }

    /**
     * La methode isUnderMouse renvoit un boolean permettant de savoir en
     * fonction des coordonnes de la souris et de celle passe en parametre, si
     * les coordonnes de la souris et de la croix de l'onglet concordent.
     *
     * @param x : abscisse de l'onglet
     * @param y : ordonnee de l'onglet
     * @return
     */
    private boolean isUnderMouse(int x, int y) {
        if (Math.abs(x - meX) < width && Math.abs(y - meY) < height) {
            return true;
        }
        return false;
    }

    private boolean mouseOverTab(int x, int y) {
        int tabCount = tabbedPane.getTabCount();
        for (int j = 0; j < tabCount; j++) {
            if (tabbedPane.getBoundsAt(j).contains(meX, meY)) {
                selectedTab = j;
                closeX = tabbedPane.getBoundsAt(j).x + tabbedPane.getBoundsAt(j).width - width - 5;
                closeY = tabbedPane.getBoundsAt(j).y + 5;
                return true;
            }
        }
        return false;
    }

    public boolean tabAboutToClose(int tabIndex) {
        return true;
    }
}
