import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class SentimentAnalysisApp {
    public static void main(String[] args) {
        // 1. Veri Toplama (örnek veriler)
        XAPIClient client = new XAPIClient();
        List<Tweet> tweets = client.fetchTweets("#ornekHashtag", 1500);

        // 2. Veri Ön İşleme (metin temizleme)
        tweets.forEach(tweet -> tweet.setText(TextCleaner.clean(tweet.getText())));

        // 3. Sentiment Analizi
        SentimentAnalyzer analyzer = new SimpleSentimentAnalyzer();
        List<SentimentResult> analysisResults = new ArrayList<>();
        tweets.forEach(tweet -> analysisResults.add(analyzer.analyze(tweet.getText())));

        // 4. Raporlama
        ReportGenerator reportGenerator = new ReportGenerator();
        reportGenerator.generateReport(analysisResults);
    }
}

// --- Tweet Sınıfı ---
class Tweet {
    private final String id;
    private String text;
    private final LocalDateTime timestamp;

    public Tweet(String id, String text, LocalDateTime timestamp) {
        this.id = id;
        this.text = text;
        this.timestamp = timestamp;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}

// --- XAPIClient Sınıfı (Örnek / Dummy Veri) ---
class XAPIClient {
    public List<Tweet> fetchTweets(String hashtag, int count) {
        List<Tweet> tweets = new ArrayList<>();
        for (int i = 1; i <= count; i++) {
            String content = "Bu tweet örnek bir içerik (" + hashtag + "). " + (i % 3 == 0 ? "berbat" : i % 2 == 0 ? "iyi" : "kötü") + " duygu içeriyor.";
            tweets.add(new Tweet("id" + i, content, LocalDateTime.now()));
        }
        return tweets;
    }
}

// --- TextCleaner Sınıfı ---
class TextCleaner {
    public static String clean(String text) {
        text = text.replaceAll("http\\S+", ""); // URL temizleme
        text = text.replaceAll("[^\\p{L}\\p{Nd}\\s]", ""); // Özel karakter temizleme
        return text.trim().toLowerCase();
    }
}

// --- SentimentAnalyzer Arayüzü ---
interface SentimentAnalyzer {
    SentimentResult analyze(String text);
}

// --- SimpleSentimentAnalyzer Sınıfı ---
class SimpleSentimentAnalyzer implements SentimentAnalyzer {
    @Override
    public SentimentResult analyze(String text) {
        int score = 0;
        if (text.contains("iyi") || text.contains("güzel")) score++;
        if (text.contains("kötü") || text.contains("berbat")) score--;

        SentimentCategory category = score > 0 ? SentimentCategory.POSITIVE : score < 0 ? SentimentCategory.NEGATIVE : SentimentCategory.NEUTRAL;
        return new SentimentResult(score, category);
    }
}

// --- SentimentResult Sınıfı ---
class SentimentResult {
    private final int score;
    private final SentimentCategory category;

    public SentimentResult(int score, SentimentCategory category) {
        this.score = score;
        this.category = category;
    }

    public SentimentCategory getCategory() {
        return category;
    }
}

// --- SentimentCategory Enum ---
enum SentimentCategory {
    POSITIVE, NEGATIVE, NEUTRAL
}

// --- ReportGenerator Sınıfı ---
class ReportGenerator {
    public void generateReport(List<SentimentResult> analysisResults) {
        long positive = analysisResults.stream().filter(r -> r.getCategory() == SentimentCategory.POSITIVE).count();
        long negative = analysisResults.stream().filter(r -> r.getCategory() == SentimentCategory.NEGATIVE).count();
        long neutral = analysisResults.stream().filter(r -> r.getCategory() == SentimentCategory.NEUTRAL).count();

        System.out.println("+----------------+------------+");
        System.out.println("| Duygu Durumu   | Tweet Sayısı |");
        System.out.println("+----------------+------------+");
        System.out.printf("| %-14s | %-10d |\n", "Pozitif", positive);
        System.out.printf("| %-14s | %-10d |\n", "Negatif", negative);
        System.out.printf("| %-14s | %-10d |\n", "Nötr", neutral);
        System.out.println("+----------------+------------+");
    }
}
