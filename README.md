Sentiment Analysis App
Bu proje, X API kullanarak belirli bir hashtag altında paylaşılan tweet'leri toplar, metin temizleme işlemlerinden geçirir ve duygu analizini gerçekleştirir. Analiz sonuçları grafikler ve tablolarla raporlanır.

 Özellikler
Tweet Toplama: Belirtilen hashtag altında 1500'e kadar tweet toplanabilir.
Metin Temizleme: URL'ler, özel karakterler ve gereksiz boşluklar temizlenir.
Duygu Analizi: Tweet'ler pozitif, negatif veya nötr olarak sınıflandırılır.
Raporlama: Analiz sonuçları tablo halinde ekrana yazdırılır.

🛠️ Kurulum
Projeyi klonlayın:
bash
Kopyala
Düzenle
git clone https://github.com/KLU1230505070/vibecodingjava.git
Java'nın yüklü olduğundan emin olun (Java 11+ önerilir).
Projeyi derleyin ve çalıştırın:
bash
Kopyala
Düzenle
javac SentimentAnalysisApp.java
java SentimentAnalysisApp
Kullanım
XAPIClient sınıfı ile belirli bir hashtag altında tweet'leri çekin.
TextCleaner ile tweet metinlerini temizleyin.
SimpleSentimentAnalyzer kullanarak duygu analizini gerçekleştirin.
ReportGenerator ile sonuçları görüntüleyin.
 ÖRNEK ÇIKTI
 +----------------+------------+
| Duygu Durumu   | Tweet Sayısı |
+----------------+------------+
| Pozitif        | 523        |
| Negatif        | 432        |
| Nötr          | 545        |
+----------------+------------+
tablo şeklinde görüntülenmektedir.
