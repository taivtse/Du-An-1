package poly.app.ui.test;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.Rectangle;
import javax.swing.plaf.basic.BasicTabbedPaneUI;

/**
 * La classe Tabbed permet de modifier l'apparence des onglets du JtabbedPane et
 * elle extends la class BasicTabbedPaneUI.
 */
public class Tabbed extends BasicTabbedPaneUI {

    private FontMetrics boldFontMetrics;
    private Font boldFont;

    /**
     * La méthode paintTabBackground permet de changer la couleur de fond des
     * onglets, et notamment l'arrière plan.
     */
    protected void paintTabBackground(Graphics g, int tabPlacement,
            int tabIndex, int x, int y, int w, int h, boolean isSelected) {
        Rectangle rect = new Rectangle();
        g.setColor(new Color(170, 180, 179));
        g.fillRect(x, y, w, h);
        if (isSelected) {
            g.setColor(new Color(62, 193, 189));
            g.fillRect(x, y, w, h);
        }
    }

    /**
     * La méthode paintTabBorder permet de changer l'apparence des bordures des
     * onglets, elle prend les mêmes paramètres que la méthode
     * paintTabBackground
     */
    protected void paintTabBorder(Graphics g, int tabPlacement,
            int tabIndex, int x, int y, int w, int h, boolean isSelected) {
        Rectangle rect = getTabBounds(tabIndex, new Rectangle(x, y, w, h));
        g.setColor(new Color(19, 19, 19));
        g.drawRect(x, y, w, h);
        if (isSelected) {
            g.setColor(new Color(121, 134, 133));
        }
    }

    /**
     * Permet de redéfinir le focus, quand on a le focus sur un onglet un petit
     * rectangle en pointillé apparaît en redéfinissant la méthode on peut
     * supprimer ce comportement.
     */
    protected void paintFocusIndicator(Graphics g, int tabPlacement, Rectangle[] rects, int tabIndex, Rectangle iconRect, Rectangle textRect, boolean isSelected) {
    }

    /**
     * Calcul de la hauteur des onglets
     */
    protected int calculateTabHeight(int tabPlacement, int tabIndex, int fontHeight) {
        int vHeight = fontHeight;
        if (vHeight % 2 > 0) {
            vHeight += 2;
        }
        return vHeight;
    }

    /**
     * Calcul de la largeur des onglets
     */
    protected int calculateTabWidth(int tabPlacement, int tabIndex, FontMetrics metrics) {
        return super.calculateTabWidth(tabPlacement, tabIndex, metrics)
                + metrics.getHeight() + 15;
    }

    /**
     * Définit l'emplacement ou se trouvera le texte, on peut ainsi le décaler,
     * ici on le laisse a 0. Le titre sera donc au centre de l'onglet.
     */
    protected int getTabLabelShiftY(int tabPlacement, int tabIndex, boolean isSelected) {
        return 0;
    }

    /**
     * Permet d'affecter une marge (en haut, a gauche, en bas, a droite) entre
     * les onglets et l'affichage du contenu.
     */
    protected Insets getContentBorderInsets(int tabPlacement) {
        return new Insets(0, 0, 0, 0);
    }

    /**
     * Permet de définir des points précis comme par exemple :
     * tabAreaInsest.left => on décale les onglets de l'espace que l'on souhaite
     * ici on le laisse a 0, donc les onglets commenceront exactement au bord
     * gauche du JtabbedPane.
     */
    protected void installDefaults() {
        super.installDefaults();
        tabAreaInsets.left = 0;
        selectedTabPadInsets = new Insets(10, 10, 10, 10);
        tabInsets = selectedTabPadInsets;
        boldFont = tabPane.getFont().deriveFont(Font.BOLD);
        boldFontMetrics = tabPane.getFontMetrics(boldFont);
    }

    /**
     * On peut choisir la couleur du texte, et son font.
     */
    protected void paintText(Graphics g, int tabPlacement, Font font, FontMetrics metrics, int tabIndex, String title, Rectangle textRect, boolean isSelected) {
        if (isSelected) {
            int vDifference = (int) (boldFontMetrics.getStringBounds(title, g).getWidth())
                    - textRect.width;
            textRect.x -= (vDifference / 2);
            super.paintText(g, tabPlacement, boldFont, boldFontMetrics, tabIndex,
                    title, textRect, isSelected);
        } else {
            super.paintText(g, tabPlacement, font, metrics, tabIndex, title, textRect,
                    isSelected);
        }
    }

    /**
     * Permettent de redéfinir la bordure du haut des onglets.
     */
    protected void paintContentBorderTopEdge(Graphics g, int tabPlacement, int selectedIndex, int x, int y, int w, int h) {
    }

    /**
     * Permettent de redéfinir la bordure de droite des onglets.
     */
    protected void paintContentBorderRightEdge(Graphics g, int tabPlacement, int selectedIndex, int x, int y, int w, int h) {
    }

    /**
     * Permettent de redéfinir la bordure de gauche des onglets.
     */
    protected void paintContentBorderLeftEdge(Graphics g, int tabPlacement, int selectedIndex, int x, int y, int w, int h) {
    }

    /**
     * Permettent de redéfinir la bordure du bas des onglets.
     */
    protected void paintContentBorderBottomEdge(Graphics g, int tabPlacement, int selectedIndex, int x, int y, int w, int h) {
    }
}
