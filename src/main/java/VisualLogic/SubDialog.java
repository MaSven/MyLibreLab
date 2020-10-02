/*
 * Copyright (C) 2020 MyLibreLab
 * Based on MyOpenLab by Carmelo Salafia www.myopenlab.de
 * Copyright (C) 2004  Carmelo Salafia cswi@gmx.de
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *
 */

package VisualLogic;

import java.awt.BorderLayout;
import java.awt.Dimension;

/**
 * @author Salafia
 */
public class SubDialog extends javax.swing.JDialog {

    private Basis basis;
    private Element element;

    /**
     * Creates new form SubDialog
     */
    public SubDialog(Element element, java.awt.Frame parent, boolean modal, Basis basis) {
        super(parent, modal);
        initComponents();

        this.element = element;

        this.basis = basis;

        VMObject vmObject = basis.getFrontBasis();

        // BasisPanel panelFront = new BasisPanel(vmObject);
        getContentPane().add(vmObject, BorderLayout.CENTER);

        setSize(new Dimension(vmObject.getWidth() + 10, vmObject.getHeight() + 30));

        vmObject.setDoubleBuffered(false);

        /*
         * try { setIconImage(basis.frameCircuit.getIconImage()); }catch(Exception ex) { }
         */
    }

    /**
     * This method is called from within the constructor to initialize the form. WARNING: Do NOT modify
     * this code. The content of this method is always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc=" Generated Code ">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        setBounds((screenSize.width - 400) / 2, (screenSize.height - 300) / 2, 400, 300);
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowClosing(java.awt.event.WindowEvent evt) {// GEN-FIRST:event_formWindowClosing
        element.subDialog = null;
        dispose();
    }// GEN-LAST:event_formWindowClosing

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
