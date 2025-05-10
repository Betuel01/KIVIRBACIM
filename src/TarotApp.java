import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

//Ana maine bağlama:
public class TarotApp extends JPanel {

    private CardLayout cardLayout;
    private JPanel mainPanel;
    private JLabel gecmisLabel = new JLabel("", SwingConstants.CENTER);
    private JLabel simdiLabel = new JLabel("", SwingConstants.CENTER);
    private JLabel gelecekLabel = new JLabel("", SwingConstants.CENTER);
    private int secimSayaci = 0;

    private List<String> tarotKartlari;
    private GecmisAnlamlari gecmisAnlamlari = new GecmisAnlamlari();
    private SimdiAnlamlari simdiAnlamlari = new SimdiAnlamlari();
    private GelecekAnlamlari gelecekAnlamlari = new GelecekAnlamlari();

    //Kart seçim ekranı:
    public TarotApp(JPanel mainPanel,CardLayout cardLayout) {
        this.mainPanel = mainPanel;
        this.cardLayout = cardLayout;

        setLayout(new BorderLayout());
        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        tarotKartları();
        JPanel kartSecimEkrani = new JPanel(new BorderLayout());
        kartSecimEkrani.add(createCardScrollPane(), BorderLayout.CENTER);
        kartSecimEkrani.add(createAltPanel(), BorderLayout.SOUTH);

        mainPanel.add(kartSecimEkrani, "kartSecim");
        add(mainPanel, BorderLayout.CENTER);
        cardLayout.show(mainPanel, "kartsecim");

    }

    //Kart isimleri:
    private void tarotKartları() {
        tarotKartlari = Arrays.asList(
                "JOKER","BÜYÜCÜ","AZİZE","İMPARATORİÇE","İMPARATOR","AZİZ","AŞIKLAR","ARABA","GÜÇ","ERMİŞ",
                "KADER ÇARKI","ADALET","ASILAN ADAM","ÖLÜM","DENGE","ŞEYTAN","KULE","YILDIZ","AY","GÜNEŞ","MAHKEME","DÜNYA",
                "KILIÇ ASI","KILIÇ İKİLİSİ","KILIÇ ÜÇLÜSÜ","KILIÇ DÖRTLÜSÜ","KILIÇ BEŞLİSİ","KILIÇ ALTILISI","KILIÇ YEDİLİSİ",
                "KILIÇ SEKİZLİSİ","KILIÇ DOKUZLUSU","KILIÇ ONLUSU","KILIÇ KRALI","KILIÇ KRALİÇESİ","KILIÇ ŞÖVALYESİ","KILIÇ PRENSİ",
                "DEĞNEK ASI","DEĞNEK İKİLİSİ","DEĞNEK ÜÇLÜSÜ","DEĞNEK DÖRTLÜSÜ","DEĞNEK BEŞLİSİ","DEĞNEK ALTILISI","DEĞNEK YEDİLİSİ",
                "DEĞNEK SEKİZLİSİ","DEĞNEK DOKUZLUSU","DEĞNEK ONLUSU","DEĞNEK KRALI","DEĞNEK KRALİÇESİ","DEĞNEK ŞÖVALYESİ","DEĞNEK PRENSİ",
                "KUPA ASI","KUPA İKİLİSİ","KUPA ÜÇLÜSÜ","KUPA DÖRTLÜSÜ","KUPA BEŞLİSİ","KUPA ALTILISI","KUPA YEDİLİSİ","KUPA SEKİZLİSİ",
                "KUPA DOKUZLUSU","KUPA ONLUSU","KUPA KRALI","KUPA KRALİÇESİ","KUPA ŞÖVALYESİ","KUPA PRENSİ",
                "TILSIM ASI","TILSIM İKİLİSİ","TILSIM ÜÇLÜSÜ","TILSIM DÖRTLÜSÜ","TILSIM BEŞLİSİ","TILSIM ALTILISI","TILSIM YEDİLİSİ",
                "TILSIM SEKİZLİSİ","TILSIM DOKUZLUSU","TILSIM ONLUSU","TILSIM KRALI","TILSIM KRALİÇESİ","TILSIM ŞÖVALYESİ","TILSIM PRENSİ"
        );
    }

    //Kartların deseni:
    class TarotKartButonu extends JButton {
        private final String kartIsmi;

        public TarotKartButonu(String kartIsmi) {
            super("");
            this.kartIsmi = kartIsmi;
            setPreferredSize(new Dimension(200, 300));
            setContentAreaFilled(false);
            setFocusPainted(false);
            setBorderPainted(false);
        }

        public String getKartIsmi() {
            return kartIsmi;
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();

            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            int w = getWidth();
            int h = getHeight();

            GradientPaint gp = new GradientPaint(0, 0, new Color(45, 34, 90), 0, h, new Color(25, 20, 50));
            g2.setPaint(gp);
            g2.fillRoundRect(0, 0, w, h, 20, 20);

            g2.setColor(new Color(212, 175, 55));
            g2.setStroke(new BasicStroke(3));
            g2.drawRoundRect(0, 0, w - 1, h - 1, 20, 20);

            Random rand = new Random();
            for (int i = 0; i < 400; i++) {
                int x = rand.nextInt(w);
                int y = rand.nextInt(h);
                int alpha = rand.nextInt(40) + 10;
                g2.setColor(new Color(255, 255, 255, alpha));
                g2.fillRect(x, y, 1, 1);
            }

            g2.setFont(new Font("Serif", Font.BOLD, 28));
            FontMetrics fm = g2.getFontMetrics();
            int textWidth = fm.stringWidth("TAROT");
            g2.setColor(Color.WHITE);
            g2.drawString("TAROT", (w - textWidth) / 2, h / 2);

            g2.fillOval(30, 30, 8, 8);
            g2.fillOval(160, 30, 8, 8);
            g2.drawArc(40, 50, 20, 20, 45, 180);
            g2.drawArc(140, 50, 20, 20, 225, 180);

            g2.drawLine(w / 2, 240, w / 2, 260);
            for (int i = -30; i <= 30; i += 10) {
                g2.drawLine(w / 2, 250, w / 2 + i / 2, 260);
            }

            g2.dispose();
            super.paintComponent(g);
        }
    }
    //Tarot kartların hareketi:
    private JScrollPane createCardScrollPane() {
        JPanel cardPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        cardPanel.setBackground(new Color(76, 10, 20));

        List<String> karistirilmis = new ArrayList<>(tarotKartlari);
        Collections.shuffle(karistirilmis);

        for (String kart : karistirilmis) {
            TarotKartButonu kartButonu = new TarotKartButonu(kart);
            kartButonu.addActionListener(e -> handleCardSelection(kartButonu));
            cardPanel.add(kartButonu);
        }

        JScrollPane scrollPane = new JScrollPane(cardPanel);
        scrollPane.setPreferredSize(new Dimension(780, 300));
        return scrollPane;
    }

    // Kart seçim kutucuklarının görünüşü:
    private JPanel createAltPanel() {
        JPanel altPanel = new JPanel(new BorderLayout());
        JPanel boxPanel = new JPanel(new GridLayout(2, 3, 10, 5));
        boxPanel.setBackground(new Color(192, 192, 192));
        boxPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel[] etiketler = {gecmisLabel, simdiLabel, gelecekLabel};
        for (JLabel label : etiketler) {
            label.setFont(new Font("Serif", Font.BOLD | Font.ITALIC, 20));
            label.setOpaque(true);
            label.setBackground(new Color(193, 191, 191));
            label.setForeground(Color.BLACK);
            label.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 2));
            label.setPreferredSize(new Dimension(250, 50));
        }

        boxPanel.add(new JLabel("Geçmiş:", SwingConstants.CENTER));
        boxPanel.add(new JLabel("Şimdi:", SwingConstants.CENTER));
        boxPanel.add(new JLabel("Gelecek:", SwingConstants.CENTER));
        boxPanel.add(gecmisLabel);
        boxPanel.add(simdiLabel);
        boxPanel.add(gelecekLabel);

        JButton yorumlaButon = new JButton("Yorumla Bacım");
        yorumlaButon.setFont(new Font("Serif", Font.BOLD, 20));
        yorumlaButon.setBackground(new Color(207, 204, 205));
        yorumlaButon.addActionListener(this::yorumla);
        altPanel.add(boxPanel, BorderLayout.CENTER);
        altPanel.add(yorumlaButon, BorderLayout.SOUTH);
        yorumlaButon.setPreferredSize(new Dimension( 0,50));
        return altPanel;

    }

    // kart seçimi:
    private void handleCardSelection(TarotKartButonu buton) {
        if (secimSayaci >= 3) return;

        String kartIsmi = buton.getKartIsmi();
        if (gecmisLabel.getText().isEmpty()) {
            gecmisLabel.setText(kartIsmi);
        } else if (simdiLabel.getText().isEmpty()) {
            simdiLabel.setText(kartIsmi);
        } else {
            gelecekLabel.setText(kartIsmi);
        }

        buton.setEnabled(false);
        secimSayaci++;
    }

    //Yorum metni:
    private void yorumla(ActionEvent e) {
        String gecmis = gecmisLabel.getText();
        String simdi = simdiLabel.getText();
        String gelecek = gelecekLabel.getText();

        String yorum = "Geçmişinde bacım: " + gecmis + "\n  ➤ " + gecmisAnlamlari.getAnlam(gecmis) + "\n\n" +
                "Şu sıralar bacım: " + simdi + "\n  ➤ " + simdiAnlamlari.getAnlam(simdi) + "\n\n" +
                "Geleceğinde bacım: " + gelecek + "\n  ➤ " + gelecekAnlamlari.getAnlam(gelecek) + "\n\n\n" +
                "Günlük tarot hakkını doldurdun bacım. Yarın görüşmek üzereee\uD83D\uDC4B ";

        yorumPenceresiOlustur(yorum);
    }
    // yorumların görünüşü:
    private void yorumPenceresiOlustur(String yorumMetni) {
        JPanel yorumPaneli = new JPanel(new BorderLayout());
        yorumPaneli.setBackground(new Color(245, 234, 219));
        yorumPaneli.setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50));

        JTextArea textArea = new JTextArea(yorumMetni);
        textArea.setWrapStyleWord(true);
        textArea.setLineWrap(true);
        textArea.setFont(new Font("Serif", Font.PLAIN, 16));
        textArea.setEditable(false);
        textArea.setBackground(new Color(255, 251, 244));
        textArea.setBorder(BorderFactory.createLineBorder(new Color(139, 117, 98), 2));

        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setPreferredSize(new Dimension(460, 280));
        yorumPaneli.add(scrollPane, BorderLayout.CENTER);

        JButton geriDonButon = new JButton("↩️ Menüye Dön Bacım");
        geriDonButon.setFont(new Font("Serif", Font.BOLD, 16));
        geriDonButon.setBackground(new Color(200, 180, 150));
        geriDonButon.addActionListener(e -> cardLayout.show(mainPanel, "BaciEkrani"));
        add(geriDonButon);
        JPanel altPanel = new JPanel();
        altPanel.setBackground(new Color(245, 234, 219));
        altPanel.add(geriDonButon);

        yorumPaneli.add(altPanel, BorderLayout.SOUTH);
        mainPanel.add(yorumPaneli, "yorumEkrani");
        cardLayout.show(mainPanel, "yorumEkrani");
    }

}

// Kartların anlam sınıfları:
class GecmisAnlamlari {
    private final Map<String, String> anlamlar;

    public GecmisAnlamlari() {
        anlamlar = new HashMap<>();
        anlamlar.put("JOKER", "Geçmişte, cesurca atılmış bir adım ya da belki de dikkatsiz bir kararın sonucu olarak, bazı şeyler yolunda gitmedi. Ancak bu, seni korkutmamalı çünkü hatalar da hayatın bir parçasıdır. Geçmişteki bu hata, seni daha dikkatli ve olgun yaparak önemli bir ders aldırtmış olabilir.");
        anlamlar.put("BÜYÜCÜ", "Geçmişte yeteneklerin ve becerilerin büyük bir rol oynadı. Başarman gereken bir hedef vardı ve bunun için gereken tüm kaynakları bulmakta zorlanmadın. Yaratıcılığın ve zekânla, aslında neredeyse her şeyin mümkün olduğuna inandığın bir dönemi geride bıraktın.");
        anlamlar.put("AZİZE", "Geçmişinde gizli bilgiler ve sezgiler önemli bir yer tutuyor. Belki de içsel bir rehberlik seni yönlendirdi, ya da etrafındaki insanlar sana anlatmadıkları şeyleri gizli tutmaya çalıştı. Bir şekilde, bilinçaltında önemli bir bilgiyle donanmışsın.");
        anlamlar.put("İMPARATORİÇE", "Geçmişte, besleyici ve koruyucu bir figür, belki de bir anne ya da yakın bir kadın figürü, seni şekillendirdi. Bolluk ve yaratım zamanlarıydı. Geçmişinde duygusal anlamda sana huzur veren bir dönemi geride bırakmışsın; belki de o dönemde yeni bir başlangıç için uygun bir zemin hazırladın.");
        anlamlar.put("İMPARATOR", "Geçmişinde güçlü bir otorite figürü vardı; belki de bu kişi hayatında her şeyi düzenleyen, kontrol eden bir insandı. Otoriteyi kabul etmek zor olsa da, belki de bu düzen, seni bugünkü güçlü ve kararlı duruşuna getiren şeydi. Güç ve liderlik, geçmişte seni şekillendiren en önemli özelliklerdi.");
        anlamlar.put("AZİZ","Geçmişinde geleneklere, toplumsal normlara ya da inanç sistemlerine sıkı sıkıya bağlıydın. Belki de bir öğretmenden, bir mentor figüründen önemli dersler aldın. Bugün bu öğretiler, hala hayatını yönlendiriyor olabilir.");
        anlamlar.put("AŞIKLAR" , "Geçmişinde önemli bir ilişki ya da aşk bağlantısı vardı. Bu ilişki, seni bir seçim yapmak zorunda bırakmış olabilir. Seçimlerin, duygusal anlamda hayatını derinden etkilemiş ve şekillendirmiş. Aşk, seni bir yol ayrımına getiren önemli bir tema.");
        anlamlar.put("ARABA" , "Geçmişindeki zaferler ve mücadeleler seni şekillendirdi. Bir hedef uğruna yürüdüğün yolda zorluklar olsa da, kararlılıkla ilerledin ve büyük bir başarıya imza attın. Geçmişteki bu zafer, şu anki güçlü karakterinin temelini oluşturuyor.");
        anlamlar.put("GÜÇ" , "Geçmişinde büyük bir sabır ve içsel güç gösterdin. Zorluklarla karşılaştığında, dışarıdan görünenin aksine çok güçlü bir duruş sergileyebildin. Bu güç, senin ruhsal ve duygusal büyümene yardımcı oldu. Kendini her zaman yeniden keşfetmeye ve yeniden güçlü kılmaya devam ettin.");
        anlamlar.put("ERMİŞ" , "Geçmişinde yalnızlık ve içsel bir arayış vardı. Belki de dış dünyadan uzaklaşarak, yalnız kaldığın anlarda derin düşünceler içinde bulundun. Kendini keşfetme yolculuğunda, yalnızlık sana büyüme ve gerçek anlamda kim olduğunu anlama fırsatı sundu.");
        anlamlar.put("KADER ÇARKI" , "Geçmişindeki değişim ve talih dönüm noktaları seni yeniden şekillendirdi. Bir şeyin sona ermesi ya da yeni bir başlangıcın habercisi oldu. Hayatın çarkı her zaman dönüyor ve sen, bu döngülerin içindeydin. Şansının dönmesinin izlerini geçmişte bulabilirsin.");
        anlamlar.put("ADALET" , "Geçmişinde adaletin, doğru ve yanlışın sınırlarını zorladığın bir dönem oldu. Belki de hayatında önemli bir karar, adalet duygusuyla şekillendi. Bu, seni güçlü ve dürüst bir insan yaptı; adaletin peşinden gitmek seni bugünkü haline getirdi.");
        anlamlar.put("ASILAN ADAM" , "Geçmişinde bekleyiş ve teslimiyet vardı. Belki de bir duraklama ya da yavaşlama dönemi yaşadın. Ancak bu dönemde kazandığın perspektif, seni bugünkü haline getirdi. Bir şeylerin tersine gitmesine izin vermek, büyük bir içsel dönüşümü beraberinde getirdi.");
        anlamlar.put("ÖLÜM" , "Geçmişindeki büyük bir sonlanma, bir dönemin kapanışıydı. Ancak bu sonlanma, yeni bir başlangıcın habercisiydi. Hayatındaki bir şeyin sona ermesi, seni bugünkü güçlü ve özgür insan haline getirdi. Ölüm, sadece bir dönemin sonudur, yenisi için yer açmak gerekir.");
        anlamlar.put("DENGE" , "Geçmişinde denge ve sabır ön planda oldu. Zorluklarla karşılaştığında, ihtiyatlı ve ölçülü bir yaklaşım sergiledin. Bu içsel denge, seni her durumda huzurlu ve dengede tutmayı başardı. Geçmişinde her şeyin bir ölçüde olması gerektiğini öğrendin.");
        anlamlar.put("ŞEYTAN" , "Geçmişinde bağımlılıklar, korkular ve sınırlayıcı inançlar vardı. Belki de seni tutan ya da özgürlüğünü kısıtlayan bir durum vardı. Ancak bu kart, seni uyandırıyor; bu eski bağları kesme zamanın geldi. Geçmişteki bu zorlukları geride bırakma gücüne sahipsin.");
        anlamlar.put("KULE" , "Geçmişinde büyük bir yıkım, ani bir değişim ya da sarsıcı bir olay yaşanmış olabilir. Bu durum seni sarsmış olsa da, aslında seni yeniden inşa etmeye zorladı. Kule, temellerin yıkılmasının, yeniden sağlam temeller üzerinde inşa edilmesi gerektiğini simgeler.\n" +
                "\n");
        anlamlar.put("YILDIZ" , "Geçmişinde umut, iyileşme ve ruhsal arayış vardı. Belki de karanlık bir dönemin ardından bir ışık gördün. Bu dönemde umut, sana yeniden başlama gücü verdi. Geçmişindeki bu iyileşme süreci, seni bugünkü haline getirdi.");
        anlamlar.put("AY" , "Geçmişinde belirsizlik, yanıltıcı durumlar ya da gizli korkular vardı. Gerçekler bazen bulanık görünüyordu, ancak sen yine de yolunu buldun. Ay, seni geçmişteki gizli duygularınla tanıştırdı ve bu duygulara ne kadar hakim olduğunu gösterdi.");
        anlamlar.put("GÜNEŞ" , "Geçmişinde büyük bir mutluluk ve başarı dönemi vardı. Güneş, senin içindeki pozitif gücü ve başarıyı simgeliyor. Belki de geçmişindeki bu parlak dönem, seni her şeyin üstesinden gelebileceğine dair inançla donattı. Şimdi, ışığın ve neşenin kaynağısın.");
        anlamlar.put("MAHKEME" , "Geçmişinde büyük bir uyanış ve yeniden değerlendirme dönemi yaşandı. Bir dönem sona erdi ve yeni bir başlangıç için seni hazırladı. Yargı, geçmişteki hatalarını yüzleşerek kabullenmeyi ve bunlardan ders almayı simgeliyor. Bu, seni daha güçlü ve olgun bir insan haline getirdi.");
        anlamlar.put("DÜNYA" , "Geçmişindeki bir tamamlanma ve başarı dönemi vardı. Tüm yolculukların bir anlam kazandığı, hedeflerine ulaştığın bir zamandı. Dünya, sana tamamlanmışlık ve başarının huzurunu getirdi. Geçmişteki bu zafer, seni şu anki yerine taşıyan en önemli faktördü.\n" +
                "\n");

        anlamlar.put("KILIÇ ASI" , "Geçmişinde, zihinsel bir keskinlik ve doğruyu bulma arzusu vardı. Belki de bir dönüm noktasına gelmiş, hayatının gidişatını değiştirecek bir doğruyu keşfetmişsin. Geçmişte zihinsel bir yenilik ya da aydınlanma yaşadın. Bu, bir fikir veya gerçek, seni geçmişte derin bir şekilde etkilemiş olabilir.");
        anlamlar.put("KILIÇ İKİLİSİ" , "Geçmişinde bir karar verememek, belki de iki zıt seçenek arasında kalmak seni zorladı. Bu kart, içsel çatışmaların ve belirsizliklerin zamanında yer almıştı. Bir karara varmak zor olsa da, sonunda seçim yapmanın gücünü kazanmışsın. Belki de bu karar, seni ruhsal bir dengeye getiren bir adım oldu.");
        anlamlar.put("KILIÇ ÜÇLÜSÜ" , "Geçmişinde kalp kırıklığı, acı ya da kayıp vardı. Bu kart, duygusal bir yara veya ayrılık yaşadığını gösteriyor. Bir ilişkinin sonlanması ya da bir kayıp, seni derinden etkileyen bir döneme işaret ediyor. Ancak, bu acı seni güçlendiren bir deneyim haline geldi.");
        anlamlar.put("KILIÇ DÖRTLÜSÜ" , "Geçmişinde bir dinlenme dönemi, belki de ruhsal ya da fiziksel bir iyileşme süreci vardı. Hayatındaki bir dönem, bir tür geri çekilme, yalnızlık ve içe dönme zamanıydı. Bu dinlenme ve toparlanma süreci seni yeniden güçlü kılmak için gerekliydi.");
        anlamlar.put("KILIÇ BEŞLİSİ" , "Geçmişinde bir çatışma ya da kazanılan zaferin ardından bir yalnızlık duygusu vardı. Bu kart, belki de bir tartışma ya da haksızlık sonucu kazandığın bir zaferin seni yalnızlaştırdığını gösteriyor. Geçmişinde kazandığın her şeyin bedelini ödemek zorunda kaldın. Ancak, bu durum seni olgunlaştıran bir ders oldu.");
        anlamlar.put("KILIÇ ALTILISI" , "Geçmişinde zorlu bir yolculuk ya da geçiş dönemi vardı. Bir dönemin sonlanması ve yeni bir başlangıca doğru ilerlerken, bazı zorlukları geride bırakman gerekti. Bu kart, geçmişteki duygusal ya da zihinsel zorlukları geride bırakma sürecini simgeliyor. Bu geçiş seni daha huzurlu bir döneme taşıdı.");
        anlamlar.put("KILIÇ YEDİLİSİ" , "Geçmişinde gizli kalmış işler, belki de bir aldatmaca ya da gizli bir plan vardı. Bu kart, geçmişte bir şeyleri gizli tutma, saklama veya kaçma dürtüsünün baskın olduğunu gösteriyor. Ancak bu gizli durumlar, seni daha dikkatli ve akıllı hale getirmiş olabilir.");
        anlamlar.put("KILIÇ SEKİZLİSİ" , "Geçmişinde bir hapsolmuşluk, bir sınırlama hissi vardı. Belki de kendini bir çıkmazda, zihinsel bir tıkanıklık içinde buldun. Ancak bu kart, geçmişteki sınırlamaların ve korkuların seni bir yere kapatmış olsa da, aslında bu durumdan çıkmak için sadece içsel gücünü kullanman gerektiğini simgeliyor.");
        anlamlar.put("KILIÇ DOKUZLUSU" , "Geçmişinde kaygılar, korkular ve geceyi uyuyamadan geçirdiğin anlar vardı. Bu kart, geçmişte seni etkileyen bir stres veya endişenin varlığını gösteriyor. Zihnindeki karamsar düşünceler, sana zor zamanlar yaşattı. Ancak, bu süreç seni daha güçlü bir insan haline getirdi.");
        anlamlar.put("KILIÇ ONLUSU" , "Geçmişinde bir sonun, bir bitişin yaşandığı zorlu bir dönem vardı. Bu kart, belki de seni derinden etkileyen bir kayıp ya da yenilgiye işaret ediyor. Ancak unutma, her son yeni bir başlangıçtır. Kılıçların Onlusu, geçmişteki acılı bitişlerin ardından bir yenilenme sürecine girişin sinyalidir.\n" +
                "\n");
        anlamlar.put("KILIÇ KRALI" , "Geçmişte mantığınla hareket ettiğin, ciddi kararlar aldığın bir dönem vardı. Duygulardan çok akıl ön plandaydı. Bu kart, doğruyu savunan, adalet arayan bir figür olarak geçmişteki duruşunu gösteriyor. Net ve kararlı bir pozisyondaydın.");
        anlamlar.put("KILIÇ KRALİÇESİ" , "Geçmişte gerçeklerle yüzleşmiş, acı deneyimlerle olgunlaşmış olabilirsin. Bu kart, geçmişte yaşanan zorlukların seni içsel olarak güçlendirdiğini, mantığınla sezgiyi birleştirerek yol aldığını gösteriyor. Sessiz bir bilgelikle hareket ettin.");
        anlamlar.put("KILIÇ ŞÖVALYESİ" , "Geçmişte bir konuda hızla harekete geçtin. Kimi zaman fevri, kimi zaman cesur oldun ama niyetin netti. Bu kart, bir meseleyi çözmek ya da bir kararın peşinden gitmek adına hızla yol aldığın bir süreci simgeliyor.");
        anlamlar.put("KILIÇ PRENSİ" , "Geçmişte bir konuda hızla harekete geçtin. Kimi zaman fevri, kimi zaman cesur oldun ama niyetin netti. Bu kart, bir meseleyi çözmek ya da bir kararın peşinden gitmek adına hızla yol aldığın bir süreci simgeliyor.");

        anlamlar.put("DEĞNEK ASI" , "Geçmişinde, bir yaratıcı ilham ya da tutkulu bir başlangıç vardı. Bu kart, geçmişte seni harekete geçiren, yeni bir projeye ya da hedefe dair bir ateşin parlamış olduğunu gösteriyor. Enerji ve motivasyonun zirveye ulaşmış, yeni bir şeylere başlamak için cesaret bulmuştun.");
        anlamlar.put("DEĞNEK İKİLİSİ" , "Geçmişinde, dünyaya açılma ve yeni fırsatlar keşfetme kararı aldın. Bu kart, geçmişte bir hedef uğruna ilk adımları attığını, ancak henüz tam anlamıyla ilerlemeden önce bazı seçenekleri değerlendirdiğini simgeliyor. Geçmişte büyük bir keşif yapmak, yeni bir yolculuğa çıkma arzusuyla doluydun.");
        anlamlar.put("DEĞNEK ÜÇLÜSÜ" , "Geçmişinde, planlarının olgunlaşmaya başladığı, bir hedefe doğru ilerlerken başarıyı görmek için sabırla beklediğin bir dönem vardı. Bu kart, geçmişte başlattığın projelerin meyve vermeye başladığını ve dünyaya açılmanın, büyük adımlar atmanın zamanı geldiğini simgeliyor.");
        anlamlar.put("DEĞNEK DÖRTLÜSÜ" , "Geçmişinde, kutlamalar ve başarılar vardı. Bu kart, belki de önemli bir hedefi tamamladığın, bir başarıyı kutladığın ya da hayatındaki büyük bir dönüm noktasına ulaştığın bir zamanı simgeliyor. Geçmişte, içsel huzur ve dengeyi bulduğun bir dönemi geride bırakmışsın.");
        anlamlar.put("DEĞNEK BEŞLİSİ" , "Geçmişinde çatışmalar, rekabetler ya da zorlayıcı durumlar vardı. Bu kart, geçmişte seni zorlayan, bazen kafa karıştıran ama seni büyüten mücadelelerin olduğuna işaret ediyor. Her ne kadar zorlu olsa da, bu savaşlar seni güçlü ve dirençli kıldı.");
        anlamlar.put("DEĞNEK ALTILISI" , "Geçmişinde büyük bir zafer ve başarı vardı. Bu kart, senin pastandaki başarılı bir dönem, belki de uzun bir mücadelenin ardından elde ettiğin zaferi simgeliyor. İleriye doğru adım attıkça, çevrendeki insanlar başarılarını kutluyor ve seni takdir ediyordu.");
        anlamlar.put("DEĞNEK YEDİLİSİ" , "Geçmişinde, savunma ve direnç gösterme zamanı vardı. Bu kart, seni zorluklarla karşı karşıya bırakmış, ancak aynı zamanda seni güçlü ve cesur kılmış bir dönemi simgeliyor. Karşına çıkan engelleri aşmak için, zaman zaman yalnız başına savaştığını gösteriyor.");
        anlamlar.put("DEĞNEK SEKİZLİSİ" , "Geçmişinde hızlı bir değişim ve harekete geçme dönemi vardı. Bu kart, bir anda çok şeyin hızla değiştiğini ve hızlı bir şekilde yeni fırsatların önüne geldiğini simgeliyor. Geçmişteki hızlı ilerleme, seni bir sonuca yaklaştırmış ve ilerlemene katkıda bulunmuş.");
        anlamlar.put("DEĞNEK DOKUZLUSU" , "Geçmişinde uzun bir mücadele ve direncin sonucu olarak, belki de tükenmişlik ya da yorgunluk hissi yaşadın. Ancak bu kart, geçmişte seni her zorluğa karşı direnen ve savaşan biri olarak simgeliyor. Her ne kadar yorulmuş olsan da, son bir çaba ile zaferi elde etmeye çok yakındın.");
        anlamlar.put("DEĞNEK ONLUSU" , "Geçmişinde çok fazla sorumluluk ve yük vardı. Bu kart, seni zorlayan bir dönem, belki de fazlasıyla sorumluluk üstlenmen gerektiği bir zamanı simgeliyor. Yüklerinin ağırlaşması, seni biraz zorladı, ancak sonunda bu yüklerden kurtulman gerekebilir.");
        anlamlar.put("DEĞNEK KRALI" , "Geçmişte büyük bir liderlik gücün vardı. Kendi yolunu çizen, cesur ve tutkulu bir figür gibi davranmış olabilirsin. Hayatında hedeflerine ulaşmak için büyük bir irade göstermiş, başkalarına da ilham olmuşsun.");
        anlamlar.put("DEĞNEK KRALİÇESİ" , "Geçmişinde özgüven, çekicilik ve güçlü bir duruş vardı. Bu kart, içsel ışığını yansıttığın, insanları etkilediğin ve bir liderlik enerjisi taşıdığın bir dönemi gösteriyor. Hayat dolu bir kadın figür seni etkileyebilir ya da sen bu figür olabilirsin.");
        anlamlar.put("DEĞNEK ŞÖVALYESİ" , "Geçmişte ani kararlar, tutkuyla hareket ettiğin zamanlar oldu. Belki de bir maceraya atıldın, risk aldın ya da içindeki enerjiyi bir işe kanalize ettin. Hareketli ve cesaret dolu bir dönem seni şekillendirdi.");
        anlamlar.put("DEĞNEK PRENSİ" , "Geçmişte yeni fikirler, tutkular ve heyecan verici gelişmeler vardı. Bu kart, bir projeye başlamak ya da yeni bir yola çıkmak için içindeki çocuk ruhunu canlandırdığını gösteriyor. Denemekten korkmadığın o ilk adım seni bugün olduğun kişiye dönüştürdü.");

        anlamlar.put("KUPA ASI" , "Geçmişinde duygusal bir yenilik, kalbinde yeni bir başlangıç vardı. Bu kart, geçmişte sana derin bir sevgi, bağ kurma ya da duygusal bir ilham veren bir fırsatın ortaya çıktığını simgeliyor. Belki de bir ilişkinin başlangıcı ya da ruhsal bir aydınlanma, seni geçmişte büyülemişti.");
        anlamlar.put("KUPA İKİLİSİ" , "Geçmişinde önemli bir ilişki ya da duygusal bağ vardı. Bu kart, bir aşk ya da derin bir dostluk anlamına gelir. Geçmişindeki bu bağ, karşılıklı anlayış ve uyumla inşa edilmişti. Birlikte büyüdüğünüz, duygusal olarak birbirinizi desteklediğiniz bir döneme işaret ediyor.");
        anlamlar.put("KUPA ÜÇLÜSÜ" , "Geçmişinde kutlamalar, arkadaşlar ya da toplulukla birlikte geçirilen neşeli zamanlar vardı. Bu kart, geçmişte arkadaşlıklar, sosyal etkinlikler ve birlikte geçirilen keyifli anlar sayesinde morale kavuştuğunu gösteriyor. Bu dönemde topluluk desteğiyle duygusal iyileşmeler yaşadın.\n" +
                "\n");
        anlamlar.put("KUPA DÖRTLÜSÜ" , "Geçmişinde bir memnuniyetsizlik ya da duygusal bir doyumsuzluk dönemi vardı. Bu kart, belki de mevcut hayatından tatmin olmadığın, fırsatların gözünden kaçtığı bir zamanı simgeliyor. İçsel bir boşluk hissi seni sarmıştı, ancak bu dönemde bir şeyleri sorguladın ve gerçek mutluluğun peşine düştün.");
        anlamlar.put("KUPA BEŞLİSİ" , "Geçmişinde kayıplar, hüzünler ve duygusal zorluklar vardı. Bu kart, belki de geçmişte bir ilişkinin sonlanması ya da bir kayıp yaşadığını simgeliyor. Ancak unutma, bu kayıpların ardından hala derin bir umut ve iyileşme vardı. Geride kalanları görmek, seni güçlü ve olgun hale getirdi.");
        anlamlar.put("KUPA ALTILISI" , "Geçmişinde nostalji, eski anılar ve geçmişteki güzel zamanlar önemli bir yer tuttu. Bu kart, eski ilişkilerin, çocukluk anıların ya da geçmişte yaşadığın neşeli zamanların seni etkilemiş olabileceğini gösteriyor. Geçmişin hatıraları, bugün de sana huzur ve güven veriyor.\n" +
                "\n");
        anlamlar.put("KUPA YEDİLİSİ" , "Geçmişinde hayaller, arzular ve belirsizlikler vardı. Bu kart, geçmişte birçok olasılık ve seçim arasında kaybolduğunu, bazen karar vermekte zorlandığını gösteriyor. Ancak bu süreç seni, neyin gerçekten değerli olduğuna karar verme konusunda daha dikkatli hale getirdi.");
        anlamlar.put("KUPA SEKİZLİSİ" , "Geçmişinde duygusal olarak bir şeyleri geride bırakma, yeni bir yolculuğa çıkma kararı vardı. Bu kart, eski bir durumu, ilişkiyi ya da yaşam tarzını terk ettiğini ve daha anlamlı bir şey arayışına çıktığını simgeliyor. Bu dönemde cesaretle duygusal bağlarını kopardın, ama bu sana içsel bir huzur getirdi.");
        anlamlar.put("KUPA DOKUZLUSU" , "Geçmişinde duygusal tatmin, mutluluk ve huzur vardı. Bu kart, geçmişte başardığın şeylerin ardından gelen mutluluğu, rahatlığı ve tatmini simgeliyor. Belki de o dönemde hedeflerine ulaştın ve duygusal olarak kendini çok daha huzurlu ve dengede hissettin.");
        anlamlar.put("KUPA ONLUSU" , "Geçmişinde ailevi mutluluk, sevgi ve huzur dönemi vardı. Bu kart, geçmişinde ailenle, sevdiklerinle yaşadığın uyumlu ve mutlu zamanları gösteriyor. İçsel tatmin ve duygusal doyumun zirveye ulaştığı bir dönemi simgeliyor. Bu dönemde yaşamın duygusal anlamda dengede ve tam bir huzur içindeydi.");
        anlamlar.put("KUPA KRALI" , "Geçmişte duygularını kontrol etmeyi öğrenmiş biri oldun. Kalbin çok şey hissetti ama onları dengede tutmayı başardın. Başkalarına karşı anlayışlı, sevecen ve destekleyici bir figür gibi davrandın. Olgun bir sevgi ya da duygusal olarak güçlü bir erkek figür geçmişinde etkili olabilir.");
        anlamlar.put("KUPA KRALİÇESİ" , "Geçmişinde derin bir sezgi, empati ve içsel şefkat vardı. Belki de birine annelik ettin, duygusal destek verdin. Kalbinle hareket ettiğin, sezgilerine güvendiğin bir dönemi geride bıraktın. Sevgi dolu, hassas ama güçlü biri olarak çevrene ışık saçtın.");
        anlamlar.put("KUPA ŞÖVALYESİ" , "Geçmişte duygularının peşinden gitmeyi seçtin. Belki romantik bir teklif ya da duygusal bir yolculuk yaşadın. Bu kart, geçmişte yaşanan bir aşk hikayesini ya da kalbinin izinden giderek verdiğin önemli kararları simgeliyor.");
        anlamlar.put("KUPA PRENSİ" , "Geçmişinde duygusal bir uyanış, ilk aşk ya da duygusal bir haber vardı. Belki de kalbin ilk kez birine açıldı ya da yaratıcı bir ilham aldın. Bu dönem, duygularla tanıştığın ve saf bir kalp ile hayata baktığın özel bir zaman oldu.\n" +
                "\n");

        anlamlar.put("TILSIM ASI" , "Geçmişinde maddi bir fırsat, yeni bir iş ya da finansal bir başlangıç seni etkiledi. Bu kart, geçmişte karşına çıkan büyük bir fırsatın ya da güven arayışının önemli olduğunu gösteriyor. Başlangıçta basit görünen ama sana uzun vadeli bir güven sağlayan bir adım atılmış olabilir.");
        anlamlar.put("TILSIM İKİLİSİ" , "Geçmişinde denge kurmak için çaba harcadığın bir dönem oldu. İş ve özel hayatını bir arada yürütmeye çalıştığın, maddi ya da duygusal dengeyi sağlamaya uğraştığın bir zaman dilimi. Bu kart, geçmişteki esnekliğini ve dengede kalma yeteneğini simgeliyor.");
        anlamlar.put("TILSIM ÜÇLÜSÜ" , "Geçmişinde ortaklıklar, işbirlikleri ve grup çalışmaları vardı. Bu kart, senin ya da çevrendeki birinin başarısını yaratmak için işbirliği yapmayı simgeliyor. Bir takım çalışmasının, birlikte başarmanın önemini anladığın bir dönemi geride bırakmışsın.");
        anlamlar.put("TILSIM DÖRTLÜSÜ" , "Geçmişinde bir güven arayışı, belki de maddi anlamda birikim yapma çabası vardı. Bu kart, geçmişte tutuculuk, kontrollü ve sağlam bir finansal durum yaratma isteğini gösteriyor. Ancak bu aşırı tutuculuk, seni bazen kapalı ve kısıtlı hissettirmiş olabilir.");
        anlamlar.put("TILSIM BEŞLİSİ" , "Geçmişinde maddi ya da duygusal bir kayıp, zorluklar yaşadın. Belki de bir süre parasal sıkıntılarla ya da yalnızlıkla mücadele ettin. Bu kart, seni dışlanmış ya da yoksun hissettiren bir dönemi simgeliyor. Ancak, bu dönemde güçlü kalmayı başardın.");
        anlamlar.put("TILSIM ALTILISI" , "Geçmişinde yardımlaşma, denge ve adalet vardı. Belki de başkalarına yardım ettin veya yardım aldın. Bu kart, geçmişteki maddi ve manevi dengeyi bulma çabanı simgeliyor. Etrafındaki insanlara karşı cömert oldun, bu da seni daha mutlu ve huzurlu yaptı.");
        anlamlar.put("TILSIM YEDİLİSİ" , "Geçmişinde uzun vadeli bir plan ya da yatırımlar vardı. Sabırlı olman gerektiğini öğrendin. Bu kart, emeklerinin karşılığını beklerken, geçmişte çok çalıştığını ve sonunda hedeflerine ulaşmak için doğru zamanı beklediğini gösteriyor.");
        anlamlar.put("TILSIM SEKİZLİSİ" , "Geçmişinde azimle ve dikkatle çalıştın. Bu kart, bir işte uzmanlaşmak, bir beceri üzerinde çalışmak ve geliştirmek için gösterdiğin çabayı simgeliyor. Bu süreç seni güçlü ve yetenekli bir insan yaptı.");
        anlamlar.put("TILSIM DOKUZLUSU" , "Geçmişinde başarı ve bağımsızlık dönemi vardı. Belki de tek başına kazandığın bir başarı, maddi güvenlik ya da bir yaşam tarzını elde ettin. Bu kart, geçmişteki özverili çalışmalarının, bugün sana bağımsızlık ve rahatlık sağladığını gösteriyor.");
        anlamlar.put("TILSIM ONLUSU" , "Geçmişinde maddi başarı, ailevi güven ve kalıcı bir mutluluk vardı. Bu kart, geçmişte ailevi ilişkilerin ve mirasla ilgili bir durumun seni güvene kavuşturduğunu simgeliyor. Aile, kökler ve maddi güvenlik, geçmişinde önemli bir yer tuttu.");
        anlamlar.put("TILSIM KRALI" , "Geçmişte maddi güvenliği sağlamaya odaklandın. İşinde veya hayatında sorumluluk sahibi, planlı ve istikrarlı bir dönem yaşadın. Bu kart, geçmişte maddi güce ulaşmış ya da sana bu konuda destek olan bir erkek figürü de gösterebilir.");
        anlamlar.put("TILSIM KRALİÇESİ" , "Geçmişte ev, aile ve maddi konularda denge kuran bir yapın vardı. Belki bir eve sahip olmak, düzen kurmak ya da başkalarının maddi refahını sağlamak senin önceliğindi. Aynı zamanda üretkenlik ve doğayla iç içe olma hali geçmişte seni şifalandırdı.");
        anlamlar.put("TILSIM ŞÖVALYESİ" , "Geçmişte sabırlı, disiplinli ve kararlı adımlar attın. Belki ağır ilerledin ama sağlam temeller oluşturdun. Bu kart, geçmişte başlanan bir projenin ya da işin zamanla büyümesine sebep olan çabanı simgeliyor.");
        anlamlar.put("TILSIM PRENSİ" , "Geçmişinde yeni bir iş, eğitim ya da yatırım fırsatı vardı. Belki maddi bir haber ya da öğrenmeye açık bir dönem yaşadın. Bu kart, geçmişte sorumluluk almaya başladığın, gelişime açık bir döneme işaret ediyor.\n" +
                "\n");
    }
    public String getAnlam(String kart) {
        return anlamlar.getOrDefault(kart, "Geçmiş anlamı bulunamadı.");
    }

}

class SimdiAnlamlari {
    private final Map<String, String> anlamlar;

    public SimdiAnlamlari() {
        anlamlar = new HashMap<>();
        anlamlar.put("JOKER", "Şu anda yeni başlangıçlar, macera ve özgürlük isteği içindesin. Kalbinin sesini dinliyor, bilinmeze doğru cesur bir adım atıyorsun.");
        anlamlar.put("BÜYÜCÜ", "Şu anda elinde gereken tüm kaynaklar var. İrade gücün yüksek ve hayallerini gerçekleştirme kapasitesine sahipsin.");
        anlamlar.put("AZİZE", "İç dünyana dönme zamanı. Şu anda sezgilerin çok güçlü, bilinçaltı sinyalleri dikkatle dinliyorsun. Saklı bilgilerle yüzleşiyorsun.");
        anlamlar.put("İMPARATORİÇE", "Şu an bereketli, üretken ve yaratıcı bir dönemdesin. Sevgi, doğurganlık ve doğa ile uyum içindesin.");
        anlamlar.put("İMPARATOR", "Disiplinli, düzenli ve güçlü bir yapıya sahipsin. Şu an hayatında kontrol sende. Liderlik rolü ön planda olabilir.");
        anlamlar.put("AZİZ","Geleneklere, öğretiye ya da bir rehbere yöneliyorsun. Şu an bilgi ve değerlerle hareket ettiğin, yapısal bir dönem yaşıyorsun.");
        anlamlar.put("AŞIKLAR" , "Şu anda kalbinle ilgili önemli bir karar aşamasındasın. Aşk, bağ kurma veya uyum arayışı gündeminde olabilir.");
        anlamlar.put("ARABA" , "Kontrol sende! Şu an kararlılık ve öz disiplinle zorlukları aşıyorsun. Hedeflerine doğru güçlü şekilde ilerliyorsun.");
        anlamlar.put("GÜÇ" , "İçsel gücünle, sabır ve şefkatle hareket ediyorsun. Şu anda zor bir durumun üstesinden yumuşak ama kararlı bir şekilde geliyorsun.");
        anlamlar.put("ERMİŞ" , "Şu anda içe dönme, yalnızlık ve içsel arayış zamanı. Kendi iç ışığını bulmaya çalışıyor, derin düşünceler içindesin.");
        anlamlar.put("KADER ÇARKI" , "Şu an hayatında büyük değişimler yaşıyorsun. Kaderin döngüsünde önemli bir dönemeçten geçiyor olabilirsin.");
        anlamlar.put("ADALET" , "Şu anda adalet, denge ve doğrulukla yüzleşiyorsun. Aldığın kararların sonuçlarını yaşıyor ya da haklılığını savunuyorsun.");
        anlamlar.put("ASILAN ADAM" , "Duraklama ve yeniden bakış zamanı. Şu an olaylara farklı bir açıdan bakmayı öğreniyor, teslimiyet içindesin.");
        anlamlar.put("ÖLÜM" , "Şu anda bir dönemi kapatıp yenisine yer açıyorsun. Bu bir bitiş gibi görünse de aslında dönüşüm sürecindesin.");
        anlamlar.put("DENGE" , " Şu anda denge, uyum ve sabırla ilerliyorsun. Hayatında bir şeyleri birleştiriyor, içsel barışı buluyorsun.");
        anlamlar.put("ŞEYTAN" , "Şu anda bağımlılıklar, tutku ya da kontrol altında olma hissi gündemde olabilir. Kendini sınırlayan bir durumla yüzleşiyorsun.");
        anlamlar.put("KULE" , "Beklenmedik gelişmeler ve ani sarsıntılar yaşıyor olabilirsin. Şu an seni sarsan ama özgürleştiren bir yıkım sürecindesin.");
        anlamlar.put("YILDIZ" , "Umut, iyileşme ve ilham zamanı. Şu anda evrenle uyum içinde, geleceğe umutla bakıyorsun.");
        anlamlar.put("AY" , "Şu anda belirsizlik ve karışıklık içinde olabilirsin. Sezgilerine güven, her şey göründüğü gibi olmayabilir.");
        anlamlar.put("GÜNEŞ" , "Mutluluk, başarı ve neşe içindesin. Şu an parlak bir dönem yaşıyorsun. Enerjin yüksek, hayat sana gülümsüyor.");
        anlamlar.put("MAHKEME" , "Şu an bir uyanış, farkındalık ya da geçmişle yüzleşme zamanındasın. Hayatının yönünü değerlendirme aşamasındasın.");
        anlamlar.put("DÜNYA" , "Tamamlama ve bütünlük hissi içindesin. Şu anda büyük bir döngüyü tamamladın. Olgunluk ve başarı enerjisi seni sarıyor.\n" +
                "\n");
        anlamlar.put("KILIÇ ASI" , "Zihninde netlik var. Yeni fikirler, kararlar veya iletişim alanında güçlü bir başlangıç yapıyorsun.");
        anlamlar.put("KILIÇ İKİLİSİ" , "Şu anda kararsızlık içindesin. İki seçenek arasında kaldın ama gözlerini açıp iç sesine güvenmen gerekiyor.");
        anlamlar.put("KILIÇ ÜÇLÜSÜ" , "Kalbinde bir kırıklık yaşıyorsun. Duygusal bir acı ya da hayal kırıklığı gündemde olabilir ama bu süreç seni güçlendiriyor.");
        anlamlar.put("KILIÇ DÖRTLÜSÜ" , "Kendine dinlenmek için zaman tanımalısın. Zihinsel yorgunluğun var, biraz içe çekilip toparlanıyorsun.");
        anlamlar.put("KILIÇ BEŞLİSİ" , "Bir çatışmanın ya da tartışmanın içindesin. Her şeyi kazanmak mı, yoksa huzuru korumak mı? Seçim senin.");
        anlamlar.put("KILIÇ ALTILISI" , "Zihinsel olarak zor bir dönemden geçip daha sakin sulara ilerliyorsun. Geçmişi arkanda bırakıyorsun.");
        anlamlar.put("KILIÇ YEDİLİSİ" , "Bir durum karşısında açık ve dürüst olunması gerekiyor. Gizli işler ya da kaçınmalar seni huzursuz edebilir.");
        anlamlar.put("KILIÇ SEKİZLİSİ" , "Kendini sıkışmış ve çaresiz hissediyorsun ama bu sınırlar aslında kendi zihninin yarattıkları.");
        anlamlar.put("KILIÇ DOKUZLUSU" , "Endişeler, uykusuzluk ya da suçluluk hissiyle boğuşuyorsun. Şu anda zihinsel baskılar seni yoruyor.");
        anlamlar.put("KILIÇ ONLUSU" , "Bir şey kesin olarak sona erdi. Evet, zor bir bitişti ama bu karanlık dönem artık sona eriyor. Yeniden doğma zamanı.");
        anlamlar.put("KILIÇ KRALI" , "Mantıklı ve analitik düşünüyorsunuz. Adalet ve doğruluk ön planda. Kararlarınızı objektif bir şekilde alıyorsunuz.");
        anlamlar.put("KILIÇ KRALİÇESİ" , "Zihinsel netlik ve sezgiyle hareket ediyorsunuz. Gerçekleri olduğu gibi görme yetisine sahipsiniz. Duygularınızı mantıkla dengeliyorsunuz.");
        anlamlar.put("KILIÇ ŞÖVALYESİ" , "Hızlı düşünme ve hareket etme dönemindesiniz. Kararlarınızı cesurca alıyorsunuz. Zorluklarla yüzleşmeye hazırsınız.");
        anlamlar.put("KILIÇ PRENSİ" , "Meraklı ve öğrenmeye açıksınız. Yeni bilgiler edinme ve iletişim kurma zamanı. Zihinsel olarak aktif bir dönemdesiniz.");

        anlamlar.put("DEĞNEK ASI" , "İçinde yeni bir tutku kıvılcımı var. Yeni bir proje, fikir ya da başlangıç için harika bir zaman!");
        anlamlar.put("DEĞNEK İKİLİSİ" , "Hedeflerini düşünüyorsun. Plan yapma ve geleceğe dair vizyon oluşturma aşamasındasın.");
        anlamlar.put("DEĞNEK ÜÇLÜSÜ" , "Ufukta fırsatlar var! Atılımlarının ilk meyvelerini görüyorsun. Yolculuk veya gelişim kapıda.");
        anlamlar.put("DEĞNEK DÖRTLÜSÜ" , "Kutlama ve birlik zamanı. Aile, dostlar ya da ilişkilerde mutluluk ve güvenlik enerjisi hâkim.");
        anlamlar.put("DEĞNEK BEŞLİSİ" , "Rekabet veya fikir ayrılıkları yaşanıyor olabilir. Şu anda enerjin yüksek ama yönlendirmeye ihtiyacın var.");
        anlamlar.put("DEĞNEK ALTILISI" , "Başarı seni buldu! Tanınma, takdir edilme ya da bir hedefe ulaşma söz konusu olabilir.");
        anlamlar.put("DEĞNEK YEDİLİSİ" , "Şu anda bir duruş sergiliyor, kendini ve fikirlerini savunuyorsun. Güçlü bir direnç içindesin.");
        anlamlar.put("DEĞNEK SEKİZLİSİ" , "Hızlı gelişmeler ve ani haberler kapıda. Enerjin yüksek ve olaylar hızla ilerliyor.");
        anlamlar.put("DEĞNEK DOKUZLUSU" , "Yorgun hissedebilirsin ama pes etmiyorsun. Şu anda dirençlisin, son bir gayretle ayakta duruyorsun.");
        anlamlar.put("DEĞNEK ONLUSU" , "Üzerinde fazla yük olabilir. Şu anda kendine fazla sorumluluk almış olabilirsin, hafiflemeyi düşünmelisin.\n" +
                "\n");
        anlamlar.put("DEĞNEK KRALI" , "Liderlik ve karizma ile çevrenizi etkiliyorsunuz. Hedeflerinize ulaşmak için cesur adımlar atıyorsunuz. Tutkularınızı gerçekleştirme zamanı.");
        anlamlar.put("DEĞNEK KRALİÇESİ" , "Liderlik ve karizma ile çevrenizi etkiliyorsunuz. Hedeflerinize ulaşmak için cesur adımlar atıyorsunuz. Tutkularınızı gerçekleştirme zamanı.");
        anlamlar.put("DEĞNEK ŞÖVALYESİ" , "Macera ve keşif arzusu içindesiniz. Hızlı ve cesur kararlar alıyorsunuz. Yeni projelere başlamak için enerjiniz yüksek.");
        anlamlar.put("DEĞNEK PRENSİ" , "Yaratıcı fikirler ve yeni başlangıçlar sizi cezbediyor. Tutkularınızı keşfetme ve ifade etme zamanı. Enerjinizi yeni projelere yönlendiriyorsunuz.");

        anlamlar.put("KUPA ASI" , "Yeni bir aşk, duygusal başlangıç ya da iç huzur hissi doğuyor. Kalbin açılıyor.");
        anlamlar.put("KUPA İKİLİSİ" , "İlişkilerde uyum, sevgi ve ruhsal bağlantı hissi içindesin. Romantik ya da duygusal bir birliktelik seni sarıyor.");
        anlamlar.put("KUPA ÜÇLÜSÜ" , "Kutlama, arkadaşlık ve sevinç dolu bir dönemdesin. Sosyal çevrende güzel gelişmeler var.");
        anlamlar.put("KUPA DÖRTLÜSÜ" , "Şu anda biraz durgun hissedebilirsin. Önüne gelen fırsatları fark etmiyor olabilirsin.");
        anlamlar.put("KUPA BEŞLİSİ" , "Geçmişin üzüntüsüyle oyalanıyor olabilirsin. Fakat arkanda hâlâ umut var. Şifaya odaklan.");
        anlamlar.put("KUPA ALTILISI" , "Anılar, nostalji veya geçmişle ilgili bir bağlantı gündeminde. Çocukluk ya da eski ilişkilerle ilgili duygular canlanıyor.");
        anlamlar.put("KUPA YEDİLİSİ" , "Birden fazla seçenek var ama hepsi seni gerçek anlamda tatmin etmeyebilir. Netleşmeye ihtiyacın var.");
        anlamlar.put("KUPA SEKİZLİSİ" , "Artık seni tatmin etmeyen bir durumu geride bırakıyorsun. Ruhsal arayış başlıyor.");
        anlamlar.put("KUPA DOKUZLUSU" , "Dileklerin gerçekleşiyor! Şu an tatmin, keyif ve huzur enerjisi içindesin.");
        anlamlar.put("KUPA ONLUSU" , "Ailevi mutluluk, sevgi ve duygusal doyum zamanı. Şu anda kalbin ve ruhun tam anlamıyla dolu.\n" +
                "\n");
        anlamlar.put("KUPA KRALI" , "Şu anda duygularınızı olgunlukla yönetiyorsunuz. Empati ve anlayışla çevrenizdekilere destek oluyorsunuz. Duygusal zekânız yüksek ve başkalarına rehberlik ediyorsunuz.");
        anlamlar.put("KUPA KRALİÇESİ" , "İçsel sezgileriniz ve duygusal derinliğinizle hareket ediyorsunuz. Şefkatli ve anlayışlı bir tavır sergiliyorsunuz. Kendinize ve başkalarına karşı nazik olma zamanı.");
        anlamlar.put("KUPA ŞÖVALYESİ" , "İçsel sezgileriniz ve duygusal derinliğinizle hareket ediyorsunuz. Şefkatli ve anlayışlı bir tavır sergiliyorsunuz. Kendinize ve başkalarına karşı nazik olma zamanı.");
        anlamlar.put("KUPA PRENSİ" , "Duygusal olarak yeni başlangıçlara açıksınız. Yaratıcı fikirler ve ilhamlar sizi etkiliyor. Sezgilerinize güvenerek yeni duygusal deneyimlere adım atıyorsunuz.");

        anlamlar.put("TILSIM ASI" , "Yeni bir iş, maddi fırsat ya da yatırım gündemde. Şu an bolluğa doğru açılan bir kapıdasın.");
        anlamlar.put("TILSIM İKİLİSİ" , "Birden fazla sorumluluğu dengelemeye çalışıyorsun. Esnekliğin önemli, kararlarını dengede kalarak almalısın.");
        anlamlar.put("TILSIM ÜÇLÜSÜ" , "İş birliği, ekip çalışması veya bir beceriyi geliştirme sürecindesin. Ortak emekle ilerliyorsun.");
        anlamlar.put("TILSIM DÖRTLÜSÜ" , "Güvende kalmak istiyorsun ama maddi konularda aşırı tutunmak seni kısıtlayabilir. Kontrol ihtiyacını sorgula.");
        anlamlar.put("TILSIM BEŞLİSİ" , "Şu anda maddi ya da manevi bir yoksunluk hissediyor olabilirsin. Ancak yardım yakınında, sadece fark etmen gerek.");
        anlamlar.put("TILSIM ALTILISI" , "Paylaşma, alma-verme dengesi içindesin. Maddi ya da duygusal anlamda destek veriyor ya da alıyorsun.");
        anlamlar.put("TILSIM YEDİLİSİ" , "Ektiğini biçme sürecindesin. Sabırla bekliyorsun. Emeklerinin karşılığı geliyor ama biraz daha zaman gerekebilir.");
        anlamlar.put("TILSIM SEKİZLİSİ" , "Kendini geliştirme, çalışkanlık ve beceri kazanma dönemindesin. Detaylara gösterdiğin özen dikkat çekiyor.");
        anlamlar.put("TILSIM DOKUZLUSU" , "Şu anda bolluk, huzur ve bağımsızlık içindesin. Emeklerinin karşılığını alıyorsun. Kendinle gurur duyabilirsin.");
        anlamlar.put("TILSIM ONLUSU" , "Aile, miras, kalıcılık ve maddi istikrar enerjisi içindesin. Hayatında sağlam temeller var.");
        anlamlar.put("TILSIM KRALI" , "Maddi konularda istikrar ve güvenlik sağlıyorsunuz. Pratik ve disiplinli yaklaşımlarınızla başarı elde ediyorsunuz. Finansal konularda liderlik yapma zamanı.");
        anlamlar.put("TILSIM KRALİÇESİ" , "Ev ve iş hayatınızda dengeyi sağlıyorsunuz. Üretken ve destekleyici bir rol üstleniyorsunuz. Maddi kaynaklarınızı verimli kullanıyorsunuz.");
        anlamlar.put("TILSIM ŞÖVALYESİ" , "Sabırlı ve kararlı adımlarla ilerliyorsunuz. Uzun vadeli hedefleriniz için çalışıyorsunuz. Disiplinli bir şekilde sorumluluklarınızı yerine getiriyorsunuz.");
        anlamlar.put("TILSIM PRENSİ" , "Yeni bir iş fırsatı ya da eğitim süreci içindesiniz. Öğrenmeye açık ve heveslisiniz. Maddi konularda yeni başlangıçlar yapma zamanı.");
    }
    public String getAnlam(String kart) {
        return anlamlar.getOrDefault(kart, "Şimdiki anlamı bulunamadı.");
    }

}

class GelecekAnlamlari {
    private final Map<String, String> anlamlar;

    public GelecekAnlamlari() {
        anlamlar = new HashMap<>();
        anlamlar.put("JOKER", "Yakında hayatında yepyeni bir başlangıç yapacaksın. Cesaret edip bilinmeyene adım atarsan güzel sürprizler seni bekliyor.");
        anlamlar.put("BÜYÜCÜ", "Gelecekte kendi gücünü fark edeceğin bir dönem geliyor. Elindeki kaynaklarla mucizeler yaratabileceksin.");
        anlamlar.put("AZİZE", "Sırlar açığa çıkabilir. Yakında sezgilerin daha güçlü çalışacak, kalbinin sesini dinlemek sana doğru yolu gösterecek.");
        anlamlar.put("İMPARATORİÇE", "Bereketli bir dönem yaklaşıyor. Aşk, yaratıcılık ya da maddi konularda büyüme yaşayacaksın. Hamilelik ya da doğum gibi gelişmeler de olabilir.");
        anlamlar.put("İMPARATOR", "Yakında hayatına düzen ve yapı kazandıracaksın. Otorite sahibi biriyle çalışabilir ya da sen sorumluluk üstlenebilirsin.");
        anlamlar.put("AZİZ","Gelecekte geleneksel yollarla ilerlemek senin için en iyisi olabilir. Eğitim, resmi işler veya rehberlik gündeme gelebilir.");
        anlamlar.put("AŞIKLAR" , "Bir karar zamanı yaklaşıyor. Kalbinin yönünü dinleyeceğin önemli bir ilişki ya da seçimle karşılaşacaksın.");
        anlamlar.put("ARABA" , "Yakında bir engeli aşarak zafer kazanacaksın. Hedeflerine kararlılıkla ilerleyeceğin bir dönem geliyor.");
        anlamlar.put("GÜÇ" , "Gelecekte içsel gücünü kullanman gerekecek. Sabrın, şefkatin ve cesaretin seni zafere taşıyacak.");
        anlamlar.put("ERMİŞ" , "İçsel yolculuk ve yalnız kalma ihtiyacı ön plana çıkacak. Ruhsal olarak aydınlanacağın bir süreç başlayabilir.");
        anlamlar.put("KADER ÇARKI" , "Kader çarkı senin lehine dönüyor. Şans kapıda! Ani ve beklenmedik gelişmelerle yeni yollar açılabilir.");
        anlamlar.put("ADALET" , "Yakında adil bir sonuç alacaksın. Geçmişte yaptıklarınla yüzleşip hak ettiğini bulacaksın. Denge yeniden sağlanıyor.");
        anlamlar.put("ASILAN ADAM" , "Gelecekte bir duraksama olabilir ama bu bekleyiş sana büyük bir farkındalık kazandıracak. Bakış açını değiştireceksin.");
        anlamlar.put("ÖLÜM" , "Büyük bir dönüşüm geliyor. Eski bir dönem kapanacak ve sen yeniden doğacaksın. Bırakman gereken şeyler var.");
        anlamlar.put("DENGE" , "Gelecekte içsel ve dışsal dengeyi kurman önem kazanacak. Sabırlı olursan uyumlu gelişmeler yaşanacak.");
        anlamlar.put("ŞEYTAN" , "Bağımlı olduğun ya da seni tutan bir durumla yüzleşeceksin. Gelecekte özgürleşmek için güçlü bir farkındalık yaşayabilirsin.");
        anlamlar.put("KULE" , "Yakında ani bir değişim yaşanabilir. Yıkılan şeyler seni yeni bir düzene götürecek. Kriz fırsata dönüşecek.");
        anlamlar.put("YILDIZ" , "Gelecek sana umut, ilham ve şifa getiriyor. Yavaş ama sağlam adımlarla güzel günlere ilerliyorsun.");
        anlamlar.put("AY" , "Yakında belirsizlikler olabilir ama iç sesin sana yol gösterecek. Rüyalar ve sezgilerden mesaj alabilirsin.");
        anlamlar.put("GÜNEŞ" , "Bolluk, neşe ve başarı seni bekliyor. Hayat enerjin yükselecek ve içindeki çocuk yeniden canlanacak.");
        anlamlar.put("MAHKEME" , "Gelecekte geçmişi değerlendirecek ve yeni bir sayfa açacaksın. Büyük bir farkındalık ve ruhsal uyanış seni bekliyor.");
        anlamlar.put("DÜNYA" , "Büyük bir döngü başarıyla tamamlanıyor. Hedeflerin gerçekleşecek, bir hayalini tamamlama zamanı geliyor.");
        anlamlar.put("KILIÇ ASI" , "Yakında zihnin berraklaşacak. Yeni bir fikir ya da doğru bir karar seni bekliyor. Zihinsel başlangıçlar kapıda.");
        anlamlar.put("KILIÇ İKİLİSİ" , "İlerleyen günlerde seni kararsız bırakan bir durum netleşecek. Gözünü açıp iç sesini dinlersen çözüm kolaylaşacak.");
        anlamlar.put("KILIÇ ÜÇLÜSÜ" , "Yakında kalbini sızlatacak bir olay yaşanabilir. Ancak bu kırılma, seni daha güçlü biri haline getirecek.");
        anlamlar.put("KILIÇ DÖRTLÜSÜ" , "Bir süre sonra dinlenme ve yenilenme ihtiyacı ön plana çıkacak. Sessizliğe çekilmen gerekebilir.");
        anlamlar.put("KILIÇ BEŞLİSİ" , "Yakında bir tartışma ya da gerilim yaşanabilir. Fakat bu durumdan ders alarak çıkacaksın. Ne uğruna savaştığını unutma.");
        anlamlar.put("KILIÇ ALTILISI" , "Önünde daha huzurlu bir dönem var. Zihinsel yüklerden kurtulup sakinliğe doğru yol alacaksın.\n" +
                "\n");
        anlamlar.put("KILIÇ YEDİLİSİ" , "İleride bir durum karşısında dikkatli olmalısın. Gizli kalmış şeyler ortaya çıkabilir. Açık ve dürüst olmak faydalı olacak.");
        anlamlar.put("KILIÇ SEKİZLİSİ" , "Kendini çıkmazda hissedeceğin bir dönem olabilir ama bu geçici. Özgürlüğünü ancak zihnini serbest bırakarak bulacaksın.");
        anlamlar.put("KILIÇ DOKUZLUSU" , "Zihinsel kaygıların seni zorlayabilir ama karanlık gecenin ardından gün doğacak. Geçici korkulara teslim olma.");
        anlamlar.put("KILIÇ ONLUSU" , "Yakında bir döngü kapanacak. Bu son, seni yenilenmeye ve ruhsal uyanışa hazırlayacak.");
        anlamlar.put("KILIÇ KRALI" , "Yakında netleşmen gereken konularla karşılaşacaksın. Bir kadın figür ya da senin akılcı tarafın ön plana çıkacak.");
        anlamlar.put("KILIÇ KRALİÇESİ" , "Yakında netleşmen gereken konularla karşılaşacaksın. Bir kadın figür ya da senin akılcı tarafın ön plana çıkacak.");
        anlamlar.put("KILIÇ ŞÖVALYESİ" , "Hızlı gelişmeler kapıda! Kararlı ve net adımlar atacaksın. Ancak ani tepkilerden uzak durmak önemli olacak.");
        anlamlar.put("KILIÇ PRENSİ" , "Gelecekte bilgi toplama ya da haber alma dönemine gireceksin. Merakın artacak, bir şeyleri öğrenme süreci başlayacak.");

        anlamlar.put("DEĞNEK ASI" , "Yakında sana ilham verecek bir fikir ya da fırsat çıkacak. Tutkuyla dolu yeni bir başlangıç geliyor.");
        anlamlar.put("DEĞNEK İKİLİSİ" , "Gelecekte hedeflerin üzerine daha net düşüneceksin. Bir seçim yapacak ve yola çıkacaksın.");
        anlamlar.put("DEĞNEK ÜÇLÜSÜ" , "Ufukta beklediğin haber ya da gelişme var. Ektiğin tohumlar filizleniyor. Başarı kapıda.");
        anlamlar.put("DEĞNEK DÖRTLÜSÜ" , "Yakında kutlama yapabileceğin güzel bir olay yaşanabilir. Ailevi mutluluk, birlik ve uyum gündeme gelecek.");
        anlamlar.put("DEĞNEK BEŞLİSİ" , "Gelecekte küçük anlaşmazlıklar ya da rekabetler yaşanabilir. Bu süreç seni daha güçlü biri yapacak.");
        anlamlar.put("DEĞNEK ALTILISI" , "Başarılı bir dönem seni bekliyor. Tanınacak, övgü alacak ve çevrenden takdir göreceksin.");
        anlamlar.put("DEĞNEK YEDİLİSİ" , "İlerleyen zamanlarda kendini savunman ya da fikirlerini güçlü şekilde ifade etmen gerekebilir. Durduğun yeri koruyacaksın.");
        anlamlar.put("DEĞNEK SEKİZLİSİ" , "Çok yakında gelişmeler hızlanacak. Haberler, yolculuklar ya da ani kararlar gündemini sarabilir.");
        anlamlar.put("DEĞNEK DOKUZLUSU" , "Bir mücadele süreci seni yorabilir ama gelecekte dirençli ve dayanıklı bir duruş sergileyeceksin.");
        anlamlar.put("DEĞNEK ONLUSU" , "İleride üstlendiğin sorumlulukların fazlalığı seni zorlayabilir. Yükünü hafifletmeyi öğrenmen gerekebilir.");
        anlamlar.put("DEĞNEK KRALI" , "Yakında güçlü bir erkek figür ya da senin lider yönün ön plana çıkacak. Kararlarında cesaretle ilerleyeceksin.\n" +
                "\n");
        anlamlar.put("DEĞNEK KRALİÇESİ" , "Gelecekte özgüvenin artacak. Liderlik özelliklerinle dikkat çekeceksin. Etkileyici bir kadın hayatında etkili olabilir.");
        anlamlar.put("DEĞNEK ŞÖVALYESİ" , "Enerjik, cesur ve hızlı gelişmeler seni bekliyor. Aksiyon dolu bir sürece adım atacaksın. Harekete geçiyorsun!");
        anlamlar.put("DEĞNEK PRENSİ" , "Yakında seni heyecanlandıran bir haber, fikir ya da teklif gelecek. Yeni bir yolculuğa çıkma arzusu doğabilir.");

        anlamlar.put("KUPA ASI" , "Yakında kalbini açacak biriyle karşılaşabilirsin ya da ruhsal bir yenilenme yaşayacaksın. Duygular tazeleniyor.");
        anlamlar.put("KUPA İKİLİSİ" , "Gelecekte sevgi dolu bir bağ ya da yeni bir ilişki kapını çalabilir. Ruhsal uyum yakalayacaksın.");
        anlamlar.put("KUPA ÜÇLÜSÜ" , "Yakında seni mutlu edecek bir haber ya da sosyal bir kutlama var. Arkadaşlıklar güçleniyor.");
        anlamlar.put("KUPA DÖRTLÜSÜ" , "İleride ilgini çekmeyen şeylere odaklanabilirsin ama gözden kaçırdığın fırsatlar var. Farkındalığın artacak.");
        anlamlar.put("KUPA BEŞLİSİ" , "Geçici bir hayal kırıklığı ya da pişmanlık yaşayabilirsin. Ama ardından yeni bir umut seni bulacak.");
        anlamlar.put("KUPA ALTILISI" , "Geçmişten biriyle yollarınız kesişebilir. Eski duygular yeniden canlanabilir. Nostaljik bir dönem yaşayabilirsin.");
        anlamlar.put("KUPA YEDİLİSİ" , "Gelecekte bol seçenek önüne çıkacak ama hangisinin kalbine hitap ettiğini bulmak zaman alabilir. Netleşeceksin.");
        anlamlar.put("KUPA SEKİZLİSİ" , "Yakında seni tatmin etmeyen bir duygusal durumu geride bırakacak ve kendini bulmaya yöneleceksin.");
        anlamlar.put("KUPA DOKUZLUSU" , "Yakında dileklerin gerçekleşebilir. Mutluluk, tatmin ve huzur kapında. İç huzur artıyor.");
        anlamlar.put("KUPA ONLUSU" , "Gelecekte ruhsal bütünlük ve ailevi huzur seni bekliyor. Kalbin sevgiyle dolacak, huzur ön planda olacak.");
        anlamlar.put("KUPA KRALI" , "Yakında duygularını daha iyi yöneteceğin bir döneme giriyorsun. Sevgiyle yaklaşan, bilge bir figür sana rehber olabilir.\n" +
                "\n");
        anlamlar.put("KUPA KRALİÇESİ" , "Gelecekte sezgilerin güçlenecek. Duygusal olgunluk ve anlayış çevrene huzur verecek. Bir kadından içten bir destek gelebilir.");
        anlamlar.put("KUPA ŞÖVALYESİ" , "Romantik bir figür hayatına girebilir. İlerleyen günlerde seni duygusal anlamda etkileyen bir insanın yaklaşımı seni şaşırtabilir.");
        anlamlar.put("KUPA PRENSİ" , "Yakın gelecekte bir duygusal teklif alabilirsin. Yeni bir ilişki başlayabilir ya da seni heyecanlandıracak bir duygusal gelişme kapıda.");

        anlamlar.put("TILSIM ASI" , "Yakında yeni bir maddi fırsat ya da iş kapısı açılacak. Güvenli ve bereketli bir başlangıç yapacaksın.");
        anlamlar.put("TILSIM İKİLİSİ" , "Gelecekte birden fazla işle ya da sorumlulukla ilgileneceksin. Denge kurmak önemli olacak.");
        anlamlar.put("TILSIM ÜÇLÜSÜ" , "Yakın zamanda bir ekip çalışması ya da bir becerini geliştirme fırsatı seni bekliyor. Ortaklıklar değerli olacak.");
        anlamlar.put("TILSIM DÖRTLÜSÜ" , "Gelecekte güven ihtiyacın artacak. Maddi konularda daha fazla kontrol isteyebilirsin ama esnek olman gerekebilir.");
        anlamlar.put("TILSIM BEŞLİSİ" , "Kısa süre sonra geçici bir maddi ya da duygusal eksiklik hissedebilirsin ama yalnız değilsin. Destek bulacaksın.");
        anlamlar.put("TILSIM ALTILISI" , "Yakında biri sana yardım edebilir ya da sen başkasına destek olabilirsin. Alma-verme dengesi güçlenecek.");
        anlamlar.put("TILSIM YEDİLİSİ" , "Ektiğin emeklerin karşılığını alacaksın ama biraz daha sabırlı olman gerekecek. Yavaş ama sağlam adımlar geliyor.");
        anlamlar.put("TILSIM SEKİZLİSİ" , "Gelecekte bir konuda ustalaşmak için yoğun çalışma dönemine girebilirsin. Disiplinli bir çaba seni başarıya götürecek.");
        anlamlar.put("TILSIM DOKUZLUSU" , "Yakında finansal huzur ve kişisel tatmin seni bekliyor. Bağımsızlık ve konfor ön planda olacak.");
        anlamlar.put("TILSIM ONLUSU" , "İleride maddi güvence, ailevi mutluluk ve köklü başarılar yaşayacaksın. Kalıcılık ve bereket dolu bir dönem kapıda.");
        anlamlar.put("TILSIM KRALI" , "Maddi konularda güçlü bir pozisyona ulaşacaksın. Kararlı ve güvenilir bir erkek figür hayatına yön verebilir ya da sen bu role geçebilirsin.");
        anlamlar.put("TILSIM KRALİÇESİ" , "Yakında ev, aile veya para konularında bir kadının desteğiyle karşılaşabilirsin. Konforun ve düzenin önemi artacak.");
        anlamlar.put("TILSIM ŞÖVALYESİ" , "Gelecekte sabırlı ve istikrarlı ilerlemen gereken bir süreç var. Attığın adımlar sağlam zemine oturacak.");
        anlamlar.put("TILSIM PRENSİ" , "Yeni bir eğitim, iş ya da maddi fırsat seni bekliyor. Yakında somut bir teklif alabilir ya da finansal bir gelişme yaşayabilirsin.");
    }

    public String getAnlam(String kart) {
        return anlamlar.getOrDefault(kart, "Gelecek anlamı bulunamadı.");
    }

}
