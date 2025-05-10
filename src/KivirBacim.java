import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.*;
import javax.swing.border.AbstractBorder;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class KivirBacim extends JFrame {
    private static JFrame frame;
    private static JPanel mainPanel;
    private static CardLayout cardLayout;

    private final static HashMap<String, String> falci = new HashMap<>();
    private final static HashMap<String, String> baci = new HashMap<>();
    static {
        // tanımlı kullanıcı ve falcılar
        falci.put("Ayça", "2005");
        falci.put("Ayşe Nur", "2003");
        falci.put("Betül", "2004");
        falci.put("Hasren", "2005");
        falci.put("Hürrem", "2003");

        baci.put("Leyla", "1995");
        baci.put("Ece", "1990");
        // Elif 1234 yeni kayıt
        // Eda 9876 yeni kayıt
        // Ezgi 777 yeni kayıt
    }
    public static class StyleUtil {
        public static final Color arkaplan = Color.decode("#3A0D1F");
        public static final Color buton = Color.decode("#800040");
        public static final Color metin = Color.decode("#F8E5D0");
        public static final Color metinkutusu = Color.decode("#800040");
        public static final Color kutusekli = new Color(151, 4, 76, 255);
        //arka plan style
        public static void applyAppBackground(JPanel panel) {
            panel.setBackground(arkaplan);
        }
        //başlık style
        public static void applyMainTitleStyle(JLabel label) {
            label.setFont(new Font("Serif", Font.BOLD, 48));
            label.setForeground(metin);
            label.setAlignmentX(Component.CENTER_ALIGNMENT);
            label.setBorder(BorderFactory.createEmptyBorder(0, 0, 40, 0));
        }
        //metin style
        public static void applyMainTextStyle(JLabel label) {
            label.setFont(new Font("Serif", Font.BOLD, 25));
            label.setForeground(metin);
            label.setAlignmentX(Component.CENTER_ALIGNMENT);
            label.setBorder(BorderFactory.createEmptyBorder(0, 0, 25, 0));
        }
        //buton style
        public static void applyButtonStyle(JButton button) {
            button.setFont(new Font("Serif", Font.BOLD, 24));
            button.setBackground(buton);
            button.setForeground(metin);
            button.setFocusPainted(false);
            button.setBorder(new RoundedBorder(30));
            button.setAlignmentX(Component.CENTER_ALIGNMENT);
            button.setCursor(new Cursor(Cursor.HAND_CURSOR));
            button.setContentAreaFilled(false);
            button.setOpaque(true);
            button.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent e) {
                    button.setBackground(kutusekli);
                }
                @Override
                public void mouseExited(MouseEvent e) {
                    button.setBackground(metinkutusu);
                }
            });
        }
        //kutu style
        public static void applyTextFieldStyle(JTextField textField) {
            textField.setBackground(metinkutusu);
            textField.setForeground(metin);
            textField.setCaretColor(metin);
            textField.setFont(new Font("Arial", Font.BOLD, 20));
            textField.setBorder(new RoundedBorder(10));
            textField.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent e) {
                    textField.setBackground(kutusekli);
                }
                @Override
                public void mouseExited(MouseEvent e) {
                    textField.setBackground(metinkutusu);
                }
            });
        }
        //kutu düzenlemesi
        static class RoundedBorder extends AbstractBorder {
            private final int radius;
            RoundedBorder(int radius) {
                this.radius = radius;
            }
            @Override
            public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
                Graphics2D g2 = (Graphics2D) g;
                g2.setColor(c.getForeground());
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.drawRoundRect(x, y, width - 1, height - 1, radius, radius);
            }
            @Override
            public Insets getBorderInsets(Component c) {
                return new Insets(radius + 1, radius + 1, radius + 2, radius);
            }
            @Override
            public Insets getBorderInsets(Component c, Insets insets) {
                insets.left = insets.right = radius + 1;
                insets.top = insets.bottom = radius + 1;
                return insets;
            }
        }
    }
    public KivirBacim ()  {
        JFrame frame = new JFrame("Kıvır Bacım");
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        Image icon = null;
        try {
            icon = ImageIO.read(Objects.requireNonNull(getClass().getResource("icon.png")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        frame.setIconImage(icon);

        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        mainPanel.add(Giris(), "Giris");
        mainPanel.add(FalciGirisi(),"FalciGirisi");
        mainPanel.add(BaciGirisi(), "BaciGirisi");
        mainPanel.add(YeniKayit(), "YeniKayit");
        mainPanel.add(FalciEkrani(), "FalciEkrani");
        mainPanel.add(BaciEkrani(), "BaciEkrani");
        mainPanel.add(new KahvePanelApp(mainPanel,cardLayout),"Kahve Falı");
        mainPanel.add(new TarotApp(mainPanel,cardLayout),"Tarot Falı");
        mainPanel.add(new RuyaArayuz(mainPanel,cardLayout),"Rüya Yorumu");
        mainPanel.add(new BurcHesaplama(mainPanel,cardLayout),"Burç Yorumu");
        frame.add(mainPanel);
        frame.setVisible(true);
    }
    //ana giriş paneli
    private static JPanel Giris() {
        JPanel panel = new JPanel(new GridBagLayout());
        StyleUtil.applyAppBackground(panel);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel title = new JLabel("Kıvır Bacım", JLabel.CENTER);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        panel.add(title, gbc);

        JButton falciButton = new JButton("Falcı Girişi");
        gbc.gridy = 1;
        panel.add(falciButton, gbc);
        falciButton.addActionListener(e -> cardLayout.show(mainPanel, "FalciGirisi"));

        JButton baciButton = new JButton("Bacı Girişi");
        gbc.gridy = 2;
        panel.add(baciButton, gbc);
        baciButton.addActionListener(e -> cardLayout.show(mainPanel, "BaciGirisi"));

        StyleUtil.applyMainTitleStyle(title);
        StyleUtil.applyButtonStyle(falciButton);
        StyleUtil.applyButtonStyle(baciButton);
        return panel;
    }
    //falcı için giriş paneli
    private static JPanel FalciGirisi() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        StyleUtil.applyAppBackground(panel);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(8, 10, 8, 10);
        gbc.weightx = 1.0;

        JLabel isim = new JLabel("Falcı adı:");
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(isim, gbc);

        JTextField isimgiris = new JTextField();
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.weightx = 1.0;
        panel.add(isimgiris, gbc);

        JLabel sifre = new JLabel("Şifre:");
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        panel.add(sifre, gbc);

        JPasswordField sifregiris = new JPasswordField();
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        gbc.weightx = 1.0;
        panel.add(sifregiris, gbc);

        JButton girisButton = new JButton("Giriş Yap");
        girisButton.setPreferredSize(new Dimension(100, 40));
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        gbc.weightx = 1.0;
        panel.add(girisButton, gbc);
        girisButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String kullanici = isimgiris.getText();
                String parola = new String(sifregiris.getPassword());

                if (falci.containsKey(kullanici) && falci.get(kullanici).equals(parola)) {
                    cardLayout.show(mainPanel, "FalciEkrani");
                }
                else {
                    JOptionPane.showMessageDialog(frame, "Hatalı giriş yaptınız!", "Hata", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        JButton geriButton = new JButton("Geri");
        geriButton.setPreferredSize(new Dimension(100, 40));
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        gbc.weightx = 1.0;
        panel.add(geriButton, gbc);
        geriButton.addActionListener(e -> cardLayout.show(mainPanel, "Giris"));

        StyleUtil.applyMainTextStyle(isim);
        StyleUtil.applyTextFieldStyle(isimgiris);
        StyleUtil.applyMainTextStyle(sifre);
        StyleUtil.applyTextFieldStyle(sifregiris);
        StyleUtil.applyButtonStyle(geriButton);
        StyleUtil.applyButtonStyle(girisButton);
        return panel;
    }
    //kullanıcı için giriş paneli
    private static JPanel BaciGirisi() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        StyleUtil.applyAppBackground(panel);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(8, 10, 8, 10);

        JLabel isim = new JLabel("Adın Bacım:");
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(isim, gbc);

        JTextField isimgiris = new JTextField();
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.weightx = 1.0;
        panel.add(isimgiris, gbc);

        JLabel sifre = new JLabel("Şifren Bacım:");
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        panel.add(sifre, gbc);

        JPasswordField sifregiris = new JPasswordField();
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        gbc.weightx = 1.0;
        panel.add(sifregiris, gbc);

        JButton kayitButton = new JButton("Yeni geliyorum abla, özür dilerim ;(");
        kayitButton.setPreferredSize(new Dimension(100, 40));
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 5;
        panel.add(kayitButton, gbc);

        JButton girisButton = new JButton("Giriş Yap");
        girisButton.setPreferredSize(new Dimension(100, 40));
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 3;
        panel.add(girisButton, gbc);
        girisButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String isim = isimgiris.getText();
                String sifre = new String(sifregiris.getPassword());
                Map<String, String> kullanicilar = KullaniciKaydet.kullanicilariOku();
                if (baci.containsKey(isim) && baci.get(isim).equals(sifre)) {
                    cardLayout.show(mainPanel, "BaciEkrani");
                }
                else if (kullanicilar.containsKey(isim) && kullanicilar.get(isim).equals(sifre)) {
                    cardLayout.show(mainPanel, "BaciEkrani");
                }
                else {
                    JOptionPane.showMessageDialog(frame, "Hatalı giriş yaptınız!", "Hata", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        girisButton.addActionListener(e -> {
            String yeni_isim = isimgiris.getText();
            String yeni_sifre = new String(sifregiris.getPassword());
            Map<String, String> kullanicilar = KullaniciKaydet.kullanicilariOku();

            if (kullanicilar.containsKey(yeni_isim) && kullanicilar.get(yeni_isim).equals(yeni_sifre)) {
                cardLayout.show(mainPanel, "BaciEkrani");
            }
        });
        kayitButton.addActionListener(e -> cardLayout.show(mainPanel, "YeniKayit"));

        JButton geriButton = new JButton("Geri");
        geriButton.setPreferredSize(new Dimension(100, 40));
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 3;
        panel.add(geriButton, gbc);
        geriButton.addActionListener(e -> cardLayout.show(mainPanel, "Giris"));

        StyleUtil.applyMainTextStyle(isim);
        StyleUtil.applyMainTextStyle(sifre);
        StyleUtil.applyTextFieldStyle(isimgiris);
        StyleUtil.applyTextFieldStyle(sifregiris);
        StyleUtil.applyButtonStyle(kayitButton);
        StyleUtil.applyButtonStyle(geriButton);
        StyleUtil.applyButtonStyle(girisButton);
        return panel;
    }
    //yeni kayıt için dosyaya yazma ve okuma fonksiyonları
    public static class KullaniciKaydet {
        public static void kayitEt(String isim, String sifre) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter("yeni.txt", true))) {
                writer.write(isim + "," + sifre);
                writer.newLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        public static Map<String, String> kullanicilariOku() {
            Map<String, String> kullanicilar = new HashMap<>();
            File dosya = new File("yeni.txt");
            try (BufferedReader reader = new BufferedReader(new FileReader(dosya))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] parts = line.split(",");
                    if (parts.length == 2) {
                        kullanicilar.put(parts[0], parts[1]);
                    }
                }
            }
            catch (IOException e) {
                e.printStackTrace();
            }
            return kullanicilar;
        }
    }
    //yeni kayıt için giriş paneli
    private static JPanel YeniKayit() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        StyleUtil.applyAppBackground(panel);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(8, 10, 8, 10);
        gbc.weightx = 1.0;

        JLabel isim = new JLabel("İsim Bacım:");
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(isim, gbc);

        JTextField isimgiris = new JTextField();
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        panel.add(isimgiris, gbc);

        JLabel sifre = new JLabel("Şifre Bacım:");
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        panel.add(sifre, gbc);

        JPasswordField sifregiris = new JPasswordField();
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        panel.add(sifregiris, gbc);

        JButton kayitButton = new JButton("Gel Bakalım Bacım");
        kayitButton.setPreferredSize(new Dimension(100, 40));
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 3;
        panel.add(kayitButton, gbc);

        kayitButton.addActionListener(e -> {
            String yeni_isim = isimgiris.getText();
            String yeni_sifre = new String(sifregiris.getPassword());

            if (yeni_isim.isEmpty() || yeni_sifre.isEmpty()) {
                JOptionPane.showMessageDialog(panel, "Lütfen tüm alanları doldurun!", "Hata", JOptionPane.ERROR_MESSAGE);
            } else if (baci.containsKey(yeni_isim)) {
                JOptionPane.showMessageDialog(panel, "Bacım sen zaten burdasın!", "Hata", JOptionPane.ERROR_MESSAGE);
            } else {baci.put(yeni_isim, yeni_sifre);
                KivirBacim.KullaniciKaydet.kayitEt(yeni_isim, yeni_sifre);
                cardLayout.show(mainPanel, "BaciGirisi");
            }
        });
        JButton geriButton = new JButton("Geri");
        geriButton.setPreferredSize(new Dimension(100, 40));
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 3;
        panel.add(geriButton, gbc);
        geriButton.addActionListener(e -> cardLayout.show(mainPanel, "BaciGirisi"));

        StyleUtil.applyMainTextStyle(isim);
        StyleUtil.applyMainTextStyle(sifre);
        StyleUtil.applyTextFieldStyle(isimgiris);
        StyleUtil.applyTextFieldStyle(sifregiris);
        StyleUtil.applyButtonStyle(kayitButton);
        StyleUtil.applyButtonStyle(geriButton);

        return panel;
    }
    //falcı ekranı
    public static JPanel FalciEkrani() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        StyleUtil.applyAppBackground(panel);

        JLabel baslikLabel = new JLabel("🔮 Hoşgeldin Falcı!");
        StyleUtil.applyMainTitleStyle(baslikLabel);
        panel.add(baslikLabel, BorderLayout.NORTH);

        JTextArea falSonuclari = new JTextArea();
        falSonuclari.setEditable(false);
        falSonuclari.setFont(new Font("Serif", Font.PLAIN, 16));
        falSonuclari.setForeground(Color.BLACK);
        falSonuclari.setBackground(Color.WHITE);
        falSonuclari.setLineWrap(true);
        falSonuclari.setWrapStyleWord(true);
        falSonuclari.setText("Henüz kimse fal yollamadı... Beklemedeyiz, kahve köpürsün.");

        JScrollPane scrollPane = new JScrollPane(falSonuclari);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel fotoLabel = new JLabel();
        fotoLabel.setHorizontalAlignment(SwingConstants.CENTER);
        fotoLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JPanel anaPanel = new JPanel(new BorderLayout());
        anaPanel.setBackground(Color.WHITE);
        anaPanel.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));
        anaPanel.add(scrollPane, BorderLayout.CENTER);
        anaPanel.add(fotoLabel, BorderLayout.SOUTH);
        panel.add(anaPanel, BorderLayout.CENTER);

        JPanel altPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 15));
        StyleUtil.applyAppBackground(altPanel);

        JButton geriButton = new JButton("🔙 Geri");
        StyleUtil.applyButtonStyle(geriButton);
        geriButton.addActionListener(e -> cardLayout.show(mainPanel, "FalciGirisi"));

        JButton falBakButton = new JButton("☕ Fala Bak!");
        StyleUtil.applyButtonStyle(falBakButton);

        falBakButton.addActionListener(e -> {
            if (!KahvePanelApp.yeniFallar.isEmpty()) {
                File yeniFal = KahvePanelApp.yeniFallar.remove(0);
                ImageIcon icon = new ImageIcon(yeniFal.getAbsolutePath());
                Image scaled = icon.getImage().getScaledInstance(300, 200, Image.SCALE_SMOOTH);
                fotoLabel.setIcon(new ImageIcon(scaled));

                falSonuclari.append("\nYeni bir bacımız geldi: " + yeniFal.getName());
                String yorum = yorumPenceresiGoster(panel);

                if (yorum != null && !yorum.isEmpty()) {
                    falSonuclari.append("\n🔮 Falcı der ki: " + yorum + "\n");
                    try {
                        String yorumMetni = "\n" +"["+java.time.LocalDateTime.now()+"] tarihli fal yorumu"+" -> " + yorum;
                        Path path = Paths.get("src/gelen_fal.txt");
                        if (!Files.exists(path)) {
                            Files.createFile(path);
                        }
                        Files.write(path, yorumMetni.getBytes(StandardCharsets.UTF_8),
                                StandardOpenOption.CREATE, StandardOpenOption.APPEND);
                        JOptionPane.showMessageDialog(panel, "Yorum başarıyla kaydedildi.");
                    } catch (IOException ex) {
                        ex.printStackTrace();
                        JOptionPane.showMessageDialog(panel, "Yorum kaydedilemedi.");
                    }
                }
                else {
                    falSonuclari.append("\n🔮 Falcı sustu bu kez...\n");
                }
            }
            else {
                JOptionPane.showMessageDialog(panel, "Yeni fal bulunamadı.");
            }
        });
        altPanel.add(falBakButton);
        altPanel.add(geriButton);
        panel.add(altPanel, BorderLayout.SOUTH);

        return panel;
    }
    // yorum penceresi
    private static String yorumPenceresiGoster(Component parent) {
        JDialog dialog = new JDialog((Frame) SwingUtilities.getWindowAncestor(parent), "🔮 Falcı Yorum Ekranı", true);
        dialog.setSize(500, 350);
        dialog.setLocationRelativeTo(parent);
        dialog.setLayout(new BorderLayout(10, 10));
        dialog.getContentPane().setBackground(new Color(0x3A0D1F));

        JTextArea yorumAlani = new JTextArea();
        yorumAlani.setLineWrap(true);
        yorumAlani.setWrapStyleWord(true);
        yorumAlani.setFont(new Font("Serif", Font.PLAIN, 16));
        yorumAlani.setBorder(BorderFactory.createTitledBorder("☕ Falcı, ne görüyorsun?"));
        yorumAlani.setForeground(Color.BLACK);
        yorumAlani.setCaretColor(Color.BLACK);

        JScrollPane scrollPane = new JScrollPane(yorumAlani);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JButton tamamButton = new JButton("✅ Yorumu Kaydet");
        tamamButton.setBackground(new Color(0x800040));
        tamamButton.setForeground(Color.WHITE);
        tamamButton.setFocusPainted(false);
        tamamButton.setPreferredSize(new Dimension(140, 40));

        JButton iptalButton = new JButton("❌ Vazgeç");
        iptalButton.setBackground(new Color(0x800040));
        iptalButton.setForeground(Color.WHITE);
        iptalButton.setFocusPainted(false);
        iptalButton.setPreferredSize(new Dimension(140, 40));

        JPanel butonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        butonPanel.setBackground(new Color(0x3A0D1F));
        butonPanel.add(iptalButton);
        butonPanel.add(tamamButton);

        dialog.add(scrollPane, BorderLayout.CENTER);
        dialog.add(butonPanel, BorderLayout.SOUTH);

        final String[] sonuc = {null};

        tamamButton.addActionListener(e -> {sonuc[0] = yorumAlani.getText().trim(); dialog.dispose();
        });
        iptalButton.addActionListener(e -> {dialog.dispose();
        });

        dialog.setVisible(true);
        return sonuc[0];
    }
    //kullanııcı ekranı
    public static JPanel BaciEkrani() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        StyleUtil.applyAppBackground(panel);
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(8, 10, 8, 10);

        JLabel baslik = new JLabel("Bugün ne bakalım?", JLabel.CENTER);
        StyleUtil.applyMainTitleStyle(baslik);
        baslik.setFont(new Font("Goudy Old Style", Font.BOLD, 50));
        gbc.gridy = 0;
        panel.add(baslik, gbc);

        JButton kahveButton = new JButton("☕ Kahve Falı");
        StyleUtil.applyButtonStyle(kahveButton);
        kahveButton.setFont(new Font("Segoe UI Emoji", Font.BOLD, 25));
        kahveButton.setPreferredSize(new Dimension(100, 50));
        gbc.gridy = 1;
        panel.add(kahveButton, gbc);
        kahveButton.addActionListener(e -> cardLayout.show(mainPanel, "Kahve Falı"));

        JButton tarotButton = new JButton("🃏 Tarot");
        StyleUtil.applyButtonStyle(tarotButton);
        tarotButton.setFont(new Font("Segoe UI Emoji", Font.BOLD, 25));
        tarotButton.setPreferredSize(new Dimension(100, 50));
        gbc.gridy = 2;
        panel.add(tarotButton, gbc);
        tarotButton.addActionListener(e -> cardLayout.show(mainPanel, "Tarot Falı"));

        JButton burcButton = new JButton("♈ Burç Yorumu");
        StyleUtil.applyButtonStyle(burcButton);
        burcButton.setFont(new Font("Segoe UI Emoji", Font.BOLD, 25));
        burcButton.setPreferredSize(new Dimension(100, 50));
        gbc.gridy = 3;
        panel.add(burcButton, gbc);
        burcButton.addActionListener(e -> cardLayout.show(mainPanel, "Burç Yorumu"));

        JButton ruyaButton = new JButton("💭 Rüya Yorumu");
        StyleUtil.applyButtonStyle(ruyaButton);
        ruyaButton.setFont(new Font("Segoe UI Emoji", Font.BOLD, 25));
        ruyaButton.setPreferredSize(new Dimension(100, 50));
        gbc.gridy = 4;
        panel.add(ruyaButton, gbc);
        ruyaButton.addActionListener(e -> cardLayout.show(mainPanel, "Rüya Yorumu"));

        JButton geriButton = new JButton("🔙 Geri");
        StyleUtil.applyButtonStyle(geriButton);
        geriButton.setFont(new Font("Segoe UI Emoji", Font.BOLD, 25));
        geriButton.setPreferredSize(new Dimension(100, 50));
        gbc.gridy = 5;
        panel.add(geriButton, gbc);
        geriButton.addActionListener(e -> cardLayout.show(mainPanel, "BaciGirisi"));

        return panel;
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new KivirBacim());
    }
}

