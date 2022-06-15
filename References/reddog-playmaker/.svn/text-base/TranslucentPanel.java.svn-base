/*
 * Copyright (c) 2007, Romain Guy
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *   * Redistributions of source code must retain the above copyright
 *     notice, this list of conditions and the following disclaimer.
 *   * Redistributions in binary form must reproduce the above
 *     copyright notice, this list of conditions and the following
 *     disclaimer in the documentation and/or other materials provided
 *     with the distribution.
 *   * Neither the name of the TimingFramework project nor the names of its
 *     contributors may be used to endorse or promote products derived
 *     from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT
 * LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR
 * A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT
 * OWNER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT
 * LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,
 * DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY
 * THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

import java.awt.AlphaComposite;
import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.*;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.*;

public class TranslucentPanel extends JPanel {
  BufferedImage image = null;
  float transparency = 0.0f;

  @Override
  public void paint(Graphics g) {
    if (image == null || image.getWidth() != getWidth() || image.getHeight() != getHeight()) {

      image = (BufferedImage) createImage(getWidth(), getHeight());
    }

    Graphics2D g2 = image.createGraphics();
    g2.setClip(g.getClip());
    super.paint(g2);
    g2.dispose();

    g2 = (Graphics2D) g.create();
    g2.setComposite(AlphaComposite.SrcOver.derive(transparency));
    g2.drawImage(image, 0, 0, null);
  }

 public TranslucentPanel(float trans){
	transparency = trans;
 }

  public static void main(String [] args) {

    SwingUtilities.invokeLater(new Runnable() {
      public void run() {
        JFrame f = new JFrame("Translucent Panel");

        f.setContentPane(new TranslucentPanel(0.4f));
        f.getContentPane().setLayout(new BorderLayout());


	ImageIcon img = new ImageIcon("/home/andy/Games/Playmaker/images/stadiums/CessnaStadium-Jets.png");
	JLabel l = new JLabel(img);


        JPanel p = new JPanel();
	p.add(l);

        f.add(p, BorderLayout.CENTER);

        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        f.setSize(400, 300);
        f.setLocationRelativeTo(null);
        f.setVisible(true);
      }
    });
  }
}
