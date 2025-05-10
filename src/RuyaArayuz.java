import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

class RuyaArayuz extends JPanel {
    private final PanelKutulari inputArea;
    private final PanelKutulari resultArea;
    private final IRuyaYorumcu analyzer;
    private final JButton yorumlaButton;

    public RuyaArayuz(JPanel mainPanel, CardLayout layout) {
        this.analyzer = new RuyaYorumcu();

        setLayout(null);
        setBackground(new Color(8, 8, 16));

        JLabel label = new JLabel("💤 Rüyanızı yazın Bacım:");
        label.setFont(new Font("Segoe UI Emoji", Font.BOLD, 18));
        label.setForeground(new Color(130, 130, 130));
        add(label);

        inputArea = new PanelKutulari();
        add(inputArea);

        yorumlaButton = new JButton("✨ Yorumla Bacım");
        yorumlaButton.setFont(new Font("Segoe UI Emoji", Font.BOLD, 16));
        yorumlaButton.setBackground(new Color(100, 100, 100));
        yorumlaButton.setForeground(Color.WHITE);
        yorumlaButton.setFocusPainted(false);
        yorumlaButton.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200), 2));
        yorumlaButton.addActionListener(this::onAnalyze);
        add(yorumlaButton);

        resultArea = new PanelKutulari();
        resultArea.setEditable(false);
        add(resultArea);

        JButton geriButton = new JButton("↩️ Geri");
        geriButton.setBackground(new Color(100, 100, 100));
        geriButton.setForeground(Color.WHITE);
        geriButton.addActionListener(e -> layout.show(mainPanel, "BaciEkrani"));
        add(geriButton);

        // Dinamik konumlandırma
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                int genislik = getWidth();
                int bosluk = 100;

                label.setBounds(bosluk, 30, genislik - 2 * bosluk, 30);
                inputArea.setBounds(bosluk, 70, genislik - 2 * bosluk, 120);
                yorumlaButton.setBounds((genislik- 160) / 2, 210, 160, 40);
                resultArea.setBounds(bosluk, 270, genislik - 2 * bosluk, getHeight() - 320);
                geriButton.setBounds(20, getHeight() - 50, 100, 30);
            }
        });
    }

    private void onAnalyze(ActionEvent e) {
        String metin = inputArea.getText().trim();
        if (metin.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Bacım bir rüya girmedin!", "Uyarı", JOptionPane.WARNING_MESSAGE);
            return;
        }
        String sonuc = analyzer.analyze(metin);
        resultArea.setText("🔮 Yorum:\n" + sonuc);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        for (int i = 0; i < 150; i++) {
            int x = (int) (Math.random() * getWidth());
            int y = (int) (Math.random() * getHeight());

            int size = 1 + (int) (Math.random() * 1.5);
            Color glitterColor = new Color(
                    220 + (int) (Math.random() * 35),
                    220 + (int) (Math.random() * 35),
                    220 + (int) (Math.random() * 35),
                    120
            );
            g2.setColor(glitterColor);
            g2.fillOval(x, y, size, size);
        }
    }
}
class PanelKutulari extends JTextArea {
    public PanelKutulari() {
        setOpaque(false);
        setForeground(new Color(250, 250, 250));
        setFont(new Font("Dialog", Font.PLAIN, 16));
        setLineWrap(true);
        setWrapStyleWord(true);
        setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15));
        setCaretColor(Color.WHITE);
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.25f));
        g2.setColor(new Color(192, 192, 192));
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), 30, 30);
        g2.dispose();
        super.paintComponent(g);
    }
}
