package poly.app.ui.test;

import java.awt.AWTKeyStroke;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.KeyboardFocusManager;
import java.awt.event.ActionEvent;
import java.util.HashSet;
import java.util.Set;
import javax.swing.AbstractAction;
import javax.swing.InputMap;
import javax.swing.JComponent;
import javax.swing.JTabbedPane;
import javax.swing.KeyStroke;

/**
 * La classe ClosableTabbedPane extends la classe JTabbedPane permettant ainsi
 * au JTabbedPane d'avoir un système de fermeture des onglets.
 */
public class ClosableTabbedPane extends JTabbedPane {

    private static final long serialVersionUID = 1L;
    private TabCloseUI closeUI = new TabCloseUI(this);

    public void paint(Graphics g) {
        super.paint(g);
        closeUI.paint(g);
    }

    /**
     * On réimplémente la méthode addTab de JTabbedPane
     */
    @Override
    public void addTab(String title, Component component) {
        super.addTab(title + "  ", component);
    }

    /**
     * La methode getTabTitleAt, permet de récupérer le nom de l'onglet qui se
     * situe à l'index donné en paramètre.
     *
     * @param index
     * @return : renvoie le nom de l'onglet qui se situe a l'index donné.
     */
    public String getTabTitleAt(int index) {
        return super.getTitleAt(index).trim();
    }

    public boolean tabAboutToClose(int tabIndex) {
        return true;
    }

    /**
     * La methode setupTabTraversalKeys permet d'utiliser les raccourcis : ctrl+
     * tab et ctrl + shift + tab afin de naviguer entre les onglets. Elle permet
     * également d'utiliser le raccourci ctrl+w afin de fermer l'onglet
     * selectionné.
     *
     * @param tabbedPane : le systeme d'onglets à qui on veut attribuer la
     * methode
     */
    public void setupTabTraversalKeys(final JTabbedPane tabbedPane) {
        KeyStroke ctrlTab = KeyStroke.getKeyStroke("ctrl TAB");
        KeyStroke ctrlShiftTab = KeyStroke.getKeyStroke("ctrl shift TAB");
        KeyStroke controlW = KeyStroke.getKeyStroke("control W");
        /*
* Un Set est une collection qui n'accepte pas les doublons. Les Set sont particulièrement adaptés pour
* manipuler une grande quantité de données. La classe AWTKeyStroke correspond à une action sur le clavier. 
* Cette classe correpsond seulement à une pression ou un relachement d'une touche. (KEY_PRESSED and KEY_RELEASED)
* > getFocusTraversalKeys permet de récupérer les touches par défaut du composant.
* > on retire les touches ctrl+t et ctrl+w de la liste des touches du composant.
         */
        Set forwardKeys = new HashSet(tabbedPane.getFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS));
        forwardKeys.remove(ctrlTab);
        tabbedPane.setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, forwardKeys);
        Set backwardKeys = new HashSet(tabbedPane.getFocusTraversalKeys(KeyboardFocusManager.BACKWARD_TRAVERSAL_KEYS));
        backwardKeys.remove(ctrlShiftTab);
        tabbedPane.setFocusTraversalKeys(KeyboardFocusManager.BACKWARD_TRAVERSAL_KEYS, backwardKeys);
        /* On va alors ajouter une touche au composant JTabbedPane. InputMap fournit une liaison entre un événement 
* d'entrée et un objet. On utilise la classe ActionMap, afin de déterminer une action à effectuer lorsqu'
* une touche est enfoncée. 
         */
        AbstractAction closeTabAction = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (tabbedPane.getTabCount() > 0) {
                    tabbedPane.remove(tabbedPane.getSelectedIndex());
                }
            }
        };
        /* On récupère l'entréee du composant utilisé, et on utilise ces constantes. 
On ajoute avec le put la liaison entre le keystroke et l'action du même nom
Pour le ctrl+w on ajoute la liaison à l'action anonyme.
         */
        InputMap inputMap = tabbedPane.getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
        inputMap.put(ctrlTab, "navigateNext");
        inputMap.put(ctrlShiftTab, "navigatePrevious");
        inputMap.put(controlW, "closeTab");
        tabbedPane.getActionMap().put("closeTab", closeTabAction);
    }
}
