import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class KahvePanelApp extends JPanel {
    private CardLayout cardLayout;
    private JPanel mainPanel;
    private CardLayout outerLayout;
    public static List<File> yeniFallar = new ArrayList<>();

    // Ovalleştirme için oluşturulan class.
    class RoundedBorder implements Border {
        private int radius;

        RoundedBorder(int radius) {
            this.radius = radius;
        }

        public Insets getBorderInsets(Component c) {
            return new Insets(this.radius + 2, this.radius + 2, this.radius + 2, this.radius + 2);
        }

        public boolean isBorderOpaque() {
            return false;
        }

        public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
            Graphics2D g2 = (Graphics2D) g;
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(new Color(200, 180, 160)); // Açık krem tonlu kenarlık
            g2.drawRoundRect(x, y, width - 1, height - 1, radius, radius);
        }
    }

    // Tasarım ve fare hareketi yakalama kısmı.
    private void styleButton(JButton button) {
        button.setFont(new Font("Georgia", Font.BOLD, 18));
        button.setBackground(new Color(111, 78, 55));
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorder(new RoundedBorder(25));
        button.setContentAreaFilled(false);
        button.setOpaque(true);
        button.setMargin(new Insets(8, 20, 8, 20));

        Color normalBg = button.getBackground();
        Color hoverBg = new Color(131, 98, 75);
        Color pressedBg = new Color(91, 65, 45);

        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(hoverBg);
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(normalBg);
            }

            public void mousePressed(java.awt.event.MouseEvent evt) {
                button.setBackground(pressedBg);
            }

            public void mouseReleased(java.awt.event.MouseEvent evt) {
                if (button.contains(evt.getPoint())) {
                    button.setBackground(hoverBg);
                } else {
                    button.setBackground(normalBg);
                }
            }
        });

    }

    // Paramatreli Yapıcısı
    public KahvePanelApp(JPanel mainPanel, CardLayout outerLayout) {

        this.mainPanel = mainPanel;
        this.outerLayout = outerLayout;
        this.setLayout(new CardLayout());
        this.cardLayout = (CardLayout) this.getLayout();


        JPanel menuPanel = createMenuPanel();
        JPanel canliFalPanel = createCanliFalPanel("Canlı Fal Sayfası");
        JPanel hizliPanel = createHiziliFalPanel("Hızlı Fal Sayfası");
        JPanel gelenKutusuPanel = createGelenKutusuPanel("Gelen Fallar");

        this.add(menuPanel, "menu");
        this.add(canliFalPanel, "canli");
        this.add(hizliPanel, "hızlı");
        this.add(gelenKutusuPanel, "gelenler");

        cardLayout.show(this, "menu");
    }

    // Ana menünün(kahve kısmı) tasarım ve işlev yeri.
    private JPanel createMenuPanel() {
        JPanel panel = new JPanel(null);
        panel.setBackground(new Color(43, 29, 22));
        panel.setName("menu");
        JLabel title = new JLabel("Kahve Falı ", SwingConstants.CENTER);
        title.setFont(new Font("Georgia", Font.BOLD, 32));
        title.setForeground(new Color(255, 248, 220));
        title.setBounds(150, 50, 500, 50);
        panel.add(title);

        JButton b1 = new JButton("Canlı Fal ");
        b1.setBounds(250, 140, 300, 55);
        styleButton(b1);
        panel.add(b1);

        JButton b2 = new JButton(" Hızlı Fal ");
        b2.setBounds(250, 210, 300, 55);
        styleButton(b2);
        panel.add(b2);

        JButton b4 = new JButton("Gelen Kutusu ");
        b4.setBounds(250, 280, 300, 55);
        styleButton(b4);
        panel.add(b4);

        JButton b3 = new JButton("Menüye Dön ");
        b3.setBounds(250, 350, 300, 55);
        styleButton(b3);
        panel.add(b3);

        b1.addActionListener(e -> cardLayout.show(this, "canli"));
        b2.addActionListener(e -> cardLayout.show(this, "hızlı"));
        b3.addActionListener(e -> outerLayout.show(mainPanel, "BaciEkrani"));
        b4.addActionListener(e -> cardLayout.show(this, "gelenler"));

        return panel;
    }

    // Canlı fal kısmının bloğu içinde kullanıcıdan fotoraf  alıp falcıya ulaştırma var.
    private JPanel createCanliFalPanel(String labelText) {
        JPanel panel = new JPanel(null);
        panel.setBackground(new Color(145, 130, 112));

        JLabel label = new JLabel("Kahve fotoğrafını yükle:", SwingConstants.CENTER);
        label.setBounds(100, 40, 600, 30);
        label.setFont(new Font("Times New Roman", Font.BOLD, 30));
        panel.add(label);

        JLabel previewLabel = new JLabel();
        previewLabel.setBounds(250, 90, 300, 200);
        previewLabel.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
        panel.add(previewLabel);

        JButton uploadButton = new JButton("Fotoğraf Yükle");
        uploadButton.setBounds(250, 310, 300, 50);
        styleButton(uploadButton);
        panel.add(uploadButton);

        JButton backButton = new JButton("Menüye Dön");
        backButton.setBounds(250, 380, 300, 50);
        styleButton(backButton);
        panel.add(backButton);

        uploadButton.addActionListener(e -> {
            String userName = JOptionPane.showInputDialog(this, "Lütfen isminizi girin:");
            if (userName != null && !userName.trim().isEmpty()) {
                JFileChooser fileChooser = new JFileChooser();
                int result = fileChooser.showOpenDialog(this);
                if (result == JFileChooser.APPROVE_OPTION) {
                    try {
                        File selectedFile = fileChooser.getSelectedFile();
                        File photosDir = new File("KıvırBacıPhotos");
                        if (!photosDir.exists()) photosDir.mkdirs();

                        String sanitizedUserName = userName.trim().replaceAll("[^a-zA-Z0-9]", "_");
                        File destinationFile = new File(photosDir, sanitizedUserName + ".jpg");

                        Files.copy(selectedFile.toPath(), destinationFile.toPath(), java.nio.file.StandardCopyOption.REPLACE_EXISTING);

                        ImageIcon originalIcon = new ImageIcon(destinationFile.getAbsolutePath());
                        Image scaledImage = originalIcon.getImage().getScaledInstance(300, 200, Image.SCALE_SMOOTH);
                        previewLabel.setIcon(new ImageIcon(scaledImage));
                        KahvePanelApp.yeniFallar.add(destinationFile);
                        JOptionPane.showMessageDialog(this, "Fotoğraf yüklendi: " + destinationFile.getName());
                    } catch (Exception ex) {
                        ex.printStackTrace();
                        JOptionPane.showMessageDialog(this, "Fotoğraf yüklenirken hata oluştu.");
                    }
                }
            } else {
                JOptionPane.showMessageDialog(this, "İsim boş olamaz.", "Uyarı", JOptionPane.WARNING_MESSAGE);
            }
        });

        backButton.addActionListener(e -> {cardLayout.show(this, "menu");
                                                               previewLabel.getDisabledIcon();});
        return panel;
    }

    // Hızlı fal kısmının bloğu bu kısım da içinde fotoraf alıyor ama falcıya atma yok 10 sn sonra otomaatik bir fal atıyor.
    private JPanel createHiziliFalPanel(String labelText) {
        JPanel panel = new JPanel(null);
        panel.setBackground(new Color(145, 130, 112));

        JLabel label = new JLabel(labelText, SwingConstants.CENTER);
        label.setBounds(200, 40, 400, 30);
        label.setFont(new Font("Times New Roman", Font.BOLD, 30));
        panel.add(label);

        JLabel previewLabel = new JLabel();
        previewLabel.setBounds(250, 80, 300, 200);
        previewLabel.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
        panel.add(previewLabel);

        JButton uploadButton = new JButton("Fotoğraf Yükle");
        uploadButton.setBounds(250, 300, 300, 50);
        styleButton(uploadButton);
        panel.add(uploadButton);

        JButton submitButton = new JButton("Gönder");
        submitButton.setBounds(250, 360, 300, 50);
        submitButton.setFont(new Font("Times New Roman", Font.BOLD, 30));
        styleButton(submitButton);
        panel.add(submitButton);

        uploadButton.addActionListener(e -> {
            String userName = JOptionPane.showInputDialog(this, "Lütfen isminizi girin:");
            if (userName != null && !userName.trim().isEmpty()) {
                JFileChooser fileChooser = new JFileChooser();
                int result = fileChooser.showOpenDialog(this);
                if (result == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();
                    try {
                        ImageIcon originalIcon = new ImageIcon(selectedFile.getAbsolutePath());
                        Image scaledImage = originalIcon.getImage().getScaledInstance(300, 200, Image.SCALE_SMOOTH);
                        previewLabel.setIcon(new ImageIcon(scaledImage));

                        JOptionPane.showMessageDialog(this, "Fotoğraf başarıyla yüklendi.");
                    } catch (Exception ex) {
                        ex.printStackTrace();
                        JOptionPane.showMessageDialog(this, "Fotoğraf yüklenirken hata oluştu.");
                    }
                }
            } else {
                JOptionPane.showMessageDialog(this, "İsim boş olamaz.", "Uyarı", JOptionPane.WARNING_MESSAGE);
            }
        });

        submitButton.addActionListener(e -> {
            String name = JOptionPane.showInputDialog(this, "Lütfen isminizi tekrar girin:");
            if (name != null && !name.trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Teşekkürler " + name + ", falınız hazırlanıyor...");
                Timer timer = new Timer(10000, new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent evt) {
                        String secilenFal = FalDeposu.rastgeleFalGetir();
                        JOptionPane.showMessageDialog(null, secilenFal, "Falınız", JOptionPane.INFORMATION_MESSAGE);
                    }
                });
                timer.setRepeats(false);
                timer.start();
            } else {
                JOptionPane.showMessageDialog(this, "İsim boş bırakılamaz.", "Uyarı", JOptionPane.WARNING_MESSAGE);
            }
        });

        JButton backButton = new JButton("Menüye Dön");
        backButton.setBounds(250, 420, 300, 50);
        backButton.setFont(new Font("Times New Roman", Font.BOLD, 30));
        styleButton(backButton);
        panel.add(backButton);

        backButton.addActionListener(e -> {cardLayout.show(this, "menu");
                                                                   previewLabel.setIcon(null);});
        return panel;
    }

    // Falcıdan çekilen mesajın gösterildiği ekran.
    private JPanel createGelenKutusuPanel(String labelText) {

        JPanel panel = new JPanel(null);
        panel.setBackground(new Color(255, 248, 220));

        JLabel label = new JLabel("Gelen Falınız", SwingConstants.CENTER);
        label.setFont(new Font("Georgia", Font.BOLD, 28));
        label.setBounds(150, 30, 500, 40);
        panel.add(label);

        JTextArea falMetni = new JTextArea();
        falMetni.setFont(new Font("Serif", Font.ITALIC, 20));
        falMetni.setLineWrap(true);
        falMetni.setWrapStyleWord(true);
        falMetni.setEditable(false);
        falMetni.setBackground(new Color(251, 255, 255));

        JScrollPane scrollPane = new JScrollPane(falMetni);
        scrollPane.setBounds(100, 90, 600, 350);
        panel.add(scrollPane);
        try {
            Path path = Paths.get("src/gelen_fal.txt");
            String content = Files.readString(path, StandardCharsets.UTF_8);
            falMetni.setText(content);
        } catch (IOException e) {
            falMetni.setText("Fal dosyası okunamadı veyahut daha fal bakılmadı.");
            e.printStackTrace();
        }

        JButton backButton = new JButton("Menüye Dön");
        backButton.setBounds(250, 460, 300, 50);
        styleButton(backButton);
        panel.add(backButton);
        backButton.addActionListener(event -> cardLayout.show(this, "menu"));
        return panel;
    }

}


