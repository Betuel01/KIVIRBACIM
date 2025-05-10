
import javax.swing.*;
import java.awt.*;
import java.util.Arrays;
import java.util.List;
import java.util.Random;


public class FalDeposu extends KahvePanelApp {
    private static final List<String> fallar = Arrays.asList("Kalbinde uzun zamandır taşıdığın bir yük var, ama bu yük artık hafiflemeye başlıyor. Yakın zamanda alacağın bir haber seni rahatlatacak. İçine doğan hisler seni yanıltmıyor; sezgilerine güvenmelisin. Özellikle önümüzdeki günlerde biriyle yapacağın samimi bir konuşma, bazı şeyleri netleştirecek.",

                "Zihnini kurcalayan soruların cevabı aslında çok yakında. Biraz sabır ve gözlemle, gerçeği kendi gözlerinle göreceksin. Ailenden biriyle olan bağların güçlenecek, bu sana huzur verecek. Kalbinin sesini dinlemekten çekinme, seni doğru yere götürüyor.",

                "Gelecekle ilgili planların var ama içindeki belirsizlik seni zaman zaman durduruyor. Fakat evren senin için bir sürpriz hazırlıyor; hiç beklemediğin bir anda güzel bir fırsat çıkacak karşına. Cesaret edip adım attığında, pişman olmayacaksın.",

                "Aşk hayatında yaşanan durağanlık yerini hareketli günlere bırakacak. Eski bir tanıdıkla yeniden yollar kesişebilir, bu defa her şey farklı olabilir. Karar verirken kalbinle birlikte aklını da devreye sok. Seni gerçekten düşünen bir kişi sessizce seni izliyor.",

                "Son günlerde iç huzurunu kaybetmiş gibisin, ama bu geçici bir dönem. Ruhsal olarak toparlanmaya başlıyorsun ve bu da çevrene yansıyacak. Yalnızca bir adım atman yeterli; ardından her şey kendiliğinden çözülecek. Özellikle bir kadın figür sana ilham verecek.",

                "Kariyerinde ya da eğitim hayatında bazı iniş çıkışlar olabilir ama bu seni yıldırmamalı. Her şeyin bir zamanı var ve senin yıldızın yavaş yavaş parlamaya başlıyor. Önümüzdeki haftalarda seni motive edecek bir gelişme yaşayacaksın. Cesaretle ilerlemeye devam et.",

                "Geçmişte yaşadığın bir hayal kırıklığı hala iz bırakmış olabilir. Fakat artık yenilenme vakti geldi. Sana iyi gelecek yeni insanlar hayatına girmeye hazırlanıyor. Yeter ki kalbini kapalı tutma.",

                "Maddi anlamda sıkışık günler yaşamış olabilirsin ama bu dönem kapanıyor. Yakın zamanda bir ödeme, fırsat ya da destek kapını çalacak. Bu gelişme seni hem rahatlatacak hem de umutlandıracak. Paranı iyi yönetmeyi unutma.",

                "Çevrende seni kıskanan ya da başarılarını görmezden gelen kişiler olabilir. Onlara kulak asmadan kendi yoluna odaklanmalısın. Senin içindeki ışık, zamanı geldiğinde her şeyi aydınlatacak. Özellikle bu ay içinde bir kararın büyük etkisi olacak.",

                "Aşkta şansın dönmek üzere. Kalbini korumakla meşguldün ama artık duvarları yıkma zamanı geldi. Biri seni uzaktan izliyor ve seninle iletişim kurmak istiyor. Sinyalleri fark etmen uzun sürmeyecek",

                "Gönlünde yer eden bir kişi var ve onunla ilgili kafan çok karışık. Yakında yaşanacak bir gelişme bu belirsizliği ortadan kaldıracak. Hislerin karşılıksız değil, ama zamanlama önemli. Sabırlı olursan duygular daha da netleşecek.",

                "Son dönemde kendini yalnız hissetmiş olabilirsin ama bu durum geçici. Yeni bir sosyal ortam ya da etkinlik sana yeni dostluklar getirecek. Özellikle biri, senin enerjinden çok etkilenecek. Onunla güzel bir başlangıcın sinyalleri şimdiden var.",

                "Aile içinde ufak bir anlaşmazlık olabilir, ama bu seni yıldırmasın. Konuya sakin yaklaşırsan herkes seni daha iyi anlayacak. Bu olay aslında bağları güçlendirmek için bir fırsat olacak. Gönülden gelen bir özür, tüm kırgınlıkları yok edecek.",

                "Bugünlerde içine doğan bazı hisler seni tedirgin ediyor olabilir. Ama sezgilerin seni doğru yöne yönlendiriyor. Özellikle sağlığınla ilgili ertelediğin bir konu varsa artık ilgilenmelisin. Küçük bir önlem büyük bir rahatlık sağlayacak.",

                "Geçmişte yaşadığın bir pişmanlık hâlâ kalbinde yer tutuyor. Ama artık kendini affetme zamanı geldi. Herkes hata yapar; önemli olan ders çıkarabilmek. Sen bu konuda içsel bir dönüşüm yaşayacaksın.",

                "Hayatında uzun zamandır istediğin bir değişiklik yakında gerçekleşecek. Bu bir taşınma, yeni bir başlangıç ya da bir iş fırsatı olabilir. Cesur olursan büyük bir adım atabilirsin. Değişimden korkma; bu senin büyüme sürecin.",

                "Kalbini kıran biriyle yolların yeniden kesişebilir. Bu kişi pişman ve seninle tekrar iletişime geçmek istiyor. Ama karar senin: geçmişi affetmek mi, yoksa yoluna devam etmek mi? İç sesin sana doğru yolu gösterecek.",

                "İçinde bastırdığın bir yetenek var ve artık kendini göstermek istiyor. Bu dönemde ilhamın artacak ve yaratıcı yönün ortaya çıkacak. Bu yeteneğini paylaşmaktan çekinme; insanlar senden etkilenecek. Belki de yeni bir yol seni bekliyordur.",

                "Yakın zamanda yaşadığın bir hayal kırıklığı seni daha güçlü biri yaptı. Şimdi bu gücü kullanarak yeniden ayağa kalkacaksın. Karşına çıkacak fırsatlar, seni daha önce hayal etmediğin yerlere taşıyabilir. Kendine güvenin tam olmalı.",

                "Kalbinin derinliklerinde özlediğin biri var. Yakın bir zamanda ondan haber alabilirsin. Bu haber seni duygulandıracak ama aynı zamanda bazı eski defterleri kapatmana da yardımcı olacak. Geçmişle barışıp geleceğe odaklanmalısın.",

                "Bir süredir beklediğin cevabın gecikmesi seni yorsa da, yakında netlik kazanacak. Sonunda içini ferahlatacak bir haber alacaksın. Bu gelişme hem motivasyonunu artıracak hem de sana yepyeni bir yön çizecek. Beklediğine değdiğini göreceksin.",

                "Hayatında yeni bir dönem başlıyor. Bu dönem seni biraz zorlayabilir ama ardından büyük bir huzur getirecek. Özellikle sabrını sınayan olaylar seni olgunlaştırıyor. Yalnız olmadığını unutma; evren seni destekliyor.",

                "Aşk konusunda çekingenliğin, güzel bir başlangıcın önüne geçebilir. Karşındaki kişi senin ilgini fark etmiş ama senden bir adım bekliyor. Bu cesareti gösterdiğinde, karşılığını fazlasıyla alacaksın. Kalbini açmak seni iyileştirecek.",

                "Sosyal çevrende seni yanlış anlayan biri olabilir. Ancak onunla yapacağın açık bir konuşma her şeyi düzeltecek. Bazen bir kelime bile büyük yanlış anlamaları çözebilir. İletişime açık ol, kazanan sen olacaksın.",

                "Maddi anlamda yeni bir kapı aralanmak üzere. Beklenmedik bir teklif ya da destek seni şaşırtabilir. Bu fırsatı değerlendirirken acele etmeden, akılcı kararlar almalısın. Uzun vadede seni çok rahatlatacak bir süreç başlayacak.",

                "Bu dönem ruhsal olarak inişli çıkışlı hissetsen de, içsel bir uyanış yaşıyorsun. Kendini daha iyi tanımaya başladıkça, ne istediğini daha net göreceksin. İç sesin seni doğru yola götürüyor. Sessizlikte yanıtları bulacaksın.",

                "Eski bir dostunla yeniden bir araya gelme ihtimalin yüksek. Bu karşılaşma seni geçmişe götürecek ama aynı zamanda bazı yaraları da iyileştirecek. Bu sefer her şey daha farklı olabilir. Şansını ikinci kez denemekte fayda var.",

                "İçinde biriktirdiğin duyguları artık dışa vurman gerekiyor. Paylaştıkça hafiflediğini göreceksin. Sana güven veren biriyle konuşmak, ruhunu rahatlatacak. Sessiz kalmak yerine ifade etmeyi dene.",

                "Kariyer ya da eğitim hayatında beklenmedik bir gelişme seni şaşırtabilir. Bu gelişme başlangıçta seni tedirgin etse de, ileride seni doğru yere yönlendirecek. Yeniliğe açık olmalısın. Değişim, sana güç katacak.",

                "Gönlünü kaptırdığın biriyle ilgili kafanda soru işaretleri var. Ama bu kişi düşündüğünden daha fazla seni önemsiyor. Yakında onunla geçireceğin bir an, birçok şeyi açıklığa kavuşturacak. Kalbinin sesine kulak ver, o doğruyu söylüyor.");

    public FalDeposu(JPanel mainPanel, CardLayout layout) {
        super(mainPanel, layout);
    }
    public static String rastgeleFalGetir() {
    Random random = new Random();
    return fallar.get(random.nextInt(fallar.size()));}

    public static List<String> getFallar() {
    return fallar;
    }
}


