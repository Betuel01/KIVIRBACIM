import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;

public class BurcHesaplama extends JPanel {
    private JTextField tarihField, saatField, ilField, ilceField;
    private JPanel mainPanel;
    private CardLayout cardLayout;

    public BurcHesaplama(JPanel mainPanel, CardLayout cardLayout) {
        this.mainPanel = mainPanel;
        this.cardLayout = cardLayout;

        setBackground(new Color(8, 73, 66));
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(8, 8, 8, 8);

        JLabel tarihLabel = new JLabel("Doğum Tarihi (GG-AA-YYYY):");
        tarihLabel.setForeground(new Color(255, 255, 255));
        tarihLabel.setFont(new Font("Serif", Font.BOLD, 20));
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(tarihLabel, gbc);

        tarihField = new JTextField();
        tarihField.setBackground(Color.LIGHT_GRAY);
        tarihField.setFont(new Font("Serif", Font.PLAIN, 20));
        tarihField.setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));
        tarihField.setPreferredSize(new Dimension(200, 50));
        tarihField.setBorder(new KivirBacim.StyleUtil.RoundedBorder(10));
        gbc.gridx = 1;
        gbc.gridy = 0;
        add(tarihField, gbc);

        // Saat
        JLabel saatLabel = new JLabel("Doğum Saati (HH:MM):");
        saatLabel.setForeground(new Color(255, 255, 255));
        saatLabel.setFont(new Font("Serif", Font.BOLD, 20));
        gbc.gridx = 0;
        gbc.gridy = 1;
        add(saatLabel, gbc);

        saatField = new JTextField();
        saatField.setBackground(Color.LIGHT_GRAY);
        saatField.setFont(new Font("Serif", Font.PLAIN, 20));
        saatField.setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));
        saatField.setPreferredSize(new Dimension(200, 50));
        saatField.setBorder(new KivirBacim.StyleUtil.RoundedBorder(10));
        gbc.gridx = 1;
        gbc.gridy = 1;
        add(saatField, gbc);

        // İl
        JLabel ilLabel = new JLabel("Doğduğu İl:");
        ilLabel.setForeground(new Color(255, 255, 255));
        ilLabel.setFont(new Font("Serif", Font.BOLD, 20));
        gbc.gridx = 0;
        gbc.gridy = 2;
        add(ilLabel, gbc);

        ilField = new JTextField();
        ilField.setBackground(Color.LIGHT_GRAY);
        ilField.setFont(new Font("Serif", Font.PLAIN, 20));
        ilField.setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));
        ilField.setPreferredSize(new Dimension(200, 50));
        ilField.setBorder(new KivirBacim.StyleUtil.RoundedBorder(10));
        gbc.gridx = 1;
        gbc.gridy = 2;
        add(ilField, gbc);

        // İlçe
        JLabel ilceLabel = new JLabel("Doğduğu İlçe:");
        ilceLabel.setForeground(new Color(255, 255, 255));
        ilceLabel.setFont(new Font("Serif", Font.BOLD, 20));
        gbc.gridx = 0;
        gbc.gridy = 3;
        add(ilceLabel, gbc);

        ilceField = new JTextField();
        ilceField.setBackground(Color.LIGHT_GRAY);
        ilceField.setFont(new Font("Serif", Font.PLAIN, 20));
        ilceField.setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));
        ilceField.setPreferredSize(new Dimension(200, 50));
        ilceField.setBorder(new KivirBacim.StyleUtil.RoundedBorder(10));
        gbc.gridx = 1;
        gbc.gridy = 3;
        add(ilceField, gbc);

        // Hesapla Butonu
        JButton hesaplaButton = new JButton("Hesapla Bacım");
        hesaplaButton.setFont(new Font("Serif", Font.BOLD, 20));
        hesaplaButton.setBorder(new KivirBacim.StyleUtil.RoundedBorder(10));
        hesaplaButton.setBackground(new Color(214, 219, 223));
        hesaplaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                hesaplaVeGoster();
            }
        });
        gbc.gridx = 0;
        gbc.gridy = 4;
        add(hesaplaButton, gbc);

        // Geri Butonu
        JButton geriButton = new JButton("Geri Bacım");
        geriButton.setFont(new Font("Serif", Font.BOLD, 20));
        geriButton.setBorder(new KivirBacim.StyleUtil.RoundedBorder(10));
        geriButton.addActionListener(e -> cardLayout.show(mainPanel, "BaciEkrani"));
        geriButton.setBackground(new Color(214, 219, 223));
        gbc.gridx = 1;
        gbc.gridy = 4;
        add(geriButton, gbc);
    }

    private void hesaplaVeGoster() {
        try {
            String tarihStr = tarihField.getText();
            String saatStr = saatField.getText();
            LocalDate date = LocalDate.parse(tarihStr, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
            LocalTime time = LocalTime.parse(saatStr, DateTimeFormatter.ofPattern("HH:mm"));

            String burc = burcBul(date);
            String yukselen = yukselenBurcBul(time);

            BurcSonucPanel sonucPanel = new BurcSonucPanel(mainPanel, cardLayout, burc, yukselen);
            mainPanel.add(sonucPanel, "BurcSonuc");
            cardLayout.show(mainPanel, "BurcSonuc");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Hatalı tarih veya saat formatı!", "Hata", JOptionPane.ERROR_MESSAGE);
        }
    }

    private String burcBul(LocalDate date) {
        int gun = date.getDayOfMonth();
        int ay = date.getMonthValue();

        if ((ay == 3 && gun >= 21) || (ay == 4 && gun<= 19)) return "Koç";
        if ((ay == 4 && gun >= 20) || (ay == 5 && gun<= 20)) return "Boğa";
        if ((ay == 5 && gun >= 21) || (ay == 6 && gun <= 20)) return "İkizler";
        if ((ay == 6 && gun >= 21) || (ay == 7 && gun <= 22)) return "Yengeç";
        if ((ay == 7 && gun >= 23) || (ay == 8 && gun <= 22)) return "Aslan";
        if ((ay == 8 && gun >= 23) || (ay == 9 && gun <= 22)) return "Başak";
        if ((ay == 9 && gun >= 23) || (ay == 10 && gun <= 22)) return "Terazi";
        if ((ay == 10 && gun >= 23) || (ay == 11 && gun <= 21)) return "Akrep";
        if ((ay == 11 && gun >= 22) || (ay == 12 && gun <= 21)) return "Yay";
        if ((ay == 12 && gun >= 22) || (ay == 1 && gun <= 19)) return "Oğlak";
        if ((ay == 1 && gun >= 20) || (ay == 2 && gun <= 18)) return "Kova";
        return "Balık";
    }

    public static String yukselenBurcBul(LocalTime time) {
        int hour = time.getHour();
        String[] burclar = {"Oğlak", "Kova", "Balık", "Koç", "Boğa", "İkizler", "Yengeç", "Aslan", "Başak", "Terazi", "Akrep", "Yay", };
        return burclar[(hour / 2) % 12];
    }
}
class BurcSonucPanel extends JPanel {
    private static final HashMap<String, String[][]> burcYorumlari = new HashMap<>();
    private final JPanel mainPanel;
    private final CardLayout cardLayout;

    static {
        burcYorumlari.put("Koç", new String[][]{
                {"Bugün enerjin yüksek olacak!", "Bugün yeni bir başlangıç yapabilirsin!", "Bugün sakin kalmaya çalış."},
                {"Bu hafta cesur adımlar atmalısın.", "Bu hafta finansal konulara dikkat etmelisin.", "Bu hafta sevdiklerinle vakit geçirmelisin."},
                {"Bu ay yeni fırsatlar seni bekliyor.", "Bu ay kariyerinde önemli gelişmeler olabilir.", "Bu ay ruhsal olarak güçleneceksin."}
        });
        burcYorumlari.put("Boğa", new String[][]{
                {"Sakin kal ve olayları akışına bırak.", "Bugün maddi konulara dikkat etmelisin.", "Bugün enerjin çok yüksek!"},
                {"Bu hafta sevdiklerinle vakit geçirmelisin.", "Bu hafta yeni insanlarla tanışabilirsin.", "Bu hafta sağlığına dikkat etmelisin."},
                {"Bu ay maddi konulara dikkat etmelisin.", "Bu ay önemli kararlar alabilirsin.", "Bu ay duygusal olarak yoğun geçebilir."}
        });
        burcYorumlari.put("İkizler", new String[][]{
                {"Zihnin çok aktif olacak, fikirlerini paylaş.", "Bugün yeni şeyler öğrenebilirsin.", "Bugün ani kararlar almaktan kaçın."},
                {"Bu hafta iletişim becerilerin ön planda olacak.", "Bu hafta yaratıcı fikirler ortaya çıkabilir.", "Bu hafta sosyal çevren genişleyebilir."},
                {"Bu ay seyahat planları yapabilirsin.", "Bu ay yeni projelere başlayabilirsin.", "Bu ay kariyerinde önemli gelişmeler olabilir."}
        });
        burcYorumlari.put("Yengeç", new String[][]{
                {"Bugün duygusal olabilirsin.", "Sevdiklerine vakit ayır.", "İç sesini dinlemelisin."},
                {"Bu hafta aile bağların güçleniyor.", "Bu hafta evinde değişiklikler yapabilirsin.", "Bu hafta iç huzurunu korumalısın."},
                {"Bu ay aşk hayatında sürprizler olabilir.", "Bu ay yeni dostluklar kurabilirsin.", "Bu ay ruhsal gelişimine odaklan."}
        });
        burcYorumlari.put("Aslan", new String[][]{
                {"Bugün liderlik özelliklerin öne çıkacak.", "Özgüvenin artıyor.", "Yeni projelere başlamak için uygun bir gün."},
                {"Bu hafta sahnede olacaksın.", "Bu hafta yeteneklerini sergile.", "Bu hafta dikkatler senin üzerinde."},
                {"Bu ay büyük başarılar elde edebilirsin.", "Bu ay özgüveninle fark yaratacaksın.", "Bu ay yaratıcı projelere odaklan."}
        });
        burcYorumlari.put("Başak", new String[][]{
                {"Bugün detaylara dikkat et.", "Planlı olmanın faydasını göreceksin.", "Yarım kalan işleri tamamlamalısın."},
                {"Bu hafta sağlığına özen göstermelisin.", "Bu hafta çalışma hayatında düzen kurmalısın.", "Bu hafta verimli geçecek."},
                {"Bu ay iş hayatında ilerleme kaydedebilirsin.", "Bu ay günlük rutinlerini gözden geçir.", "Bu ay organizasyon yeteneklerin gelişecek."}
        });
        burcYorumlari.put("Terazi", new String[][]{
                {"Bugün uyum ve denge arıyorsun.", "İlişkiler ön planda olacak.", "Sanatsal çalışmalar yapabilirsin."},
                {"Bu hafta sosyal hayatın hareketleniyor.", "Bu hafta yeni anlaşmalar yapabilirsin.", "Bu hafta dostluklarını güçlendirebilirsin."},
                {"Bu ay estetik konular ön planda.", "Bu ay ilişkilerde uyum sağlanacak.", "Bu ay ortak projeler gündemde."}
        });
        burcYorumlari.put("Akrep", new String[][]{
                {"Bugün sezgilerin güçlü.", "Gizli konulara ilgi duyabilirsin.", "Duygularını kontrol etmelisin."},
                {"Bu hafta derin düşünceler içindesin.", "Bu hafta gizemli konulara çekilebilirsin.", "Bu hafta hedeflerine odaklanmalısın."},
                {"Bu ay dönüşüm yaşayabilirsin.", "Bu ay tutkuların artacak.", "Bu ay içsel güç kazanıyorsun."}
        });
        burcYorumlari.put("Yay", new String[][]{
                {"Bugün macera arayışındasın.", "Yeni şeyler öğrenmeye açıksın.", "Özgürlük ihtiyacın artıyor."},
                {"Bu hafta seyahat planları yapabilirsin.", "Bu hafta eğitim konuları ön planda.", "Bu hafta hayat enerjin yüksek."},
                {"Bu ay yeni yerler keşfedebilirsin.", "Bu ay vizyonun genişliyor.", "Bu ay hedeflerini büyütebilirsin."}
        });
        burcYorumlari.put("Oğlak", new String[][]{
                {"Bugün sorumlulukların ön planda.", "Planlı hareket etmelisin.", "Hedeflerine odaklan."},
                {"Bu hafta kariyerin önem kazanıyor.", "Bu hafta disiplinli olmalısın.", "Bu hafta sabırlı olmalısın."},
                {"Bu ay iş hayatında başarı seni bekliyor.", "Bu ay hedeflerin netleşiyor.", "Bu ay uzun vadeli planlar yapabilirsin."}
        });
        burcYorumlari.put("Kova", new String[][]{
                {"Bugün özgün fikirler üretebilirsin.", "Arkadaşlık ilişkilerin ön planda.", "Toplumsal konulara ilgi duyabilirsin."},
                {"Bu hafta yeni projelere açık ol.", "Bu hafta teknolojik konular gündemde.", "Bu hafta sıra dışı etkinliklere katılabilirsin."},
                {"Bu ay özgürleşiyorsun.", "Bu ay yeni dostluklar kuruyorsun.", "Bu ay bireysel yeteneklerin öne çıkıyor."}
        });
        burcYorumlari.put("Balık", new String[][]{
                {"Bugün hayal gücün çok güçlü.", "Sanatsal faaliyetler için uygun bir gün.", "Sezgilerine güvenmelisin."},
                {"Bu hafta ruhsal çalışmalar yapabilirsin.", "Bu hafta duygusal yoğunluk yaşayabilirsin.", "Bu hafta yardım faaliyetlerine katılabilirsin."},
                {"Bu ay hayallerine ulaşabilirsin.", "Bu ay sezgilerin seni yönlendirecek.", "Bu ay yaratıcılığın artıyor."}
        });
    }

    public BurcSonucPanel(JPanel mainPanel, CardLayout cardLayout, String burc, String yukselen) {
        this.mainPanel = mainPanel;
        this.cardLayout = cardLayout;

        setLayout(new GridLayout(7, 1));
        setBackground(new Color(8, 73, 66));

        JLabel gunesBurcuLabel = new JLabel("Güneş Burcun: " + burc);
        JLabel yukselenBurcuLabel = new JLabel("Yükselen Burcun: " + yukselen);
        gunesBurcuLabel.setForeground(new Color(255, 255, 255));
        yukselenBurcuLabel.setForeground(new Color(255, 255, 255));
        gunesBurcuLabel.setFont(new Font("Serif", Font.BOLD, 30));
        yukselenBurcuLabel.setFont(new Font("Serif", Font.BOLD, 30));
        gunesBurcuLabel.setHorizontalAlignment(SwingConstants.CENTER);
        yukselenBurcuLabel.setHorizontalAlignment(SwingConstants.CENTER);

        String[][] yorumlar = burcYorumlari.getOrDefault(burc, new String[][]{
                {"Günlük yorum bulunamadı.", "Haftalık yorum bulunamadı.", "Aylık yorum bulunamadı."}
        });

        JLabel gunlukLabel = new JLabel("Günlük: " + yorumlar[0][0]);
        JLabel haftalikLabel = new JLabel("Haftalık: " + yorumlar[1][0]);
        JLabel aylikLabel = new JLabel("Aylık: " + yorumlar[2][0]);
        gunlukLabel.setForeground(Color.LIGHT_GRAY);
        haftalikLabel.setForeground(Color.LIGHT_GRAY);
        aylikLabel.setForeground(Color.LIGHT_GRAY);
        gunlukLabel.setFont(new Font("Serif", Font.BOLD, 20));
        haftalikLabel.setFont(new Font("Serif", Font.BOLD, 20));
        aylikLabel.setFont(new Font("Serif", Font.BOLD, 20));
        gunlukLabel.setHorizontalAlignment(SwingConstants.CENTER);
        haftalikLabel.setHorizontalAlignment(SwingConstants.CENTER);
        aylikLabel.setHorizontalAlignment(SwingConstants.CENTER);

        JButton menuyeDonButton = new JButton("Menüye Dön Bacım");
        menuyeDonButton.setFont(new Font("Serif", Font.BOLD, 20));
        menuyeDonButton.setBackground(new Color(214, 219, 223));
        menuyeDonButton.addActionListener(e -> cardLayout.show(mainPanel, "BaciEkrani"));

        add(gunesBurcuLabel);
        add(yukselenBurcuLabel);
        add(gunlukLabel);
        add(haftalikLabel);
        add(aylikLabel);
        add(menuyeDonButton);
    }
}