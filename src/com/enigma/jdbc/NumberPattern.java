import java.util.*;
import java.io.*;

class Main {

    public static String StringChallenge(String sen) {
        // 1. Hapus tanda baca dan pisahkan kata-kata menjadi list
        String[] words = sen.toLowerCase().replaceAll("&!", "").split(" ");

        // 2. Temukan panjang kata terpanjang
        int maxLen = 0;
        for (String word : words) {
            maxLen = Math.max(maxLen, word.length());
        }

        // 3. Filter kata terpanjang yang sesuai panjangnya
        List<String> longestWords = new ArrayList<>();
        for (String word : words) {
            if (word.length() == maxLen) {
                longestWords.add(word);
            }
        }

        // 4. Ambil kata pertama jika ada beberapa kata terpanjang
        String longestWord = longestWords.get(0);

        // 5. ChallengeToken (ubah sesuai dengan soal Anda)
        String challengeToken = "hv9cn5j143a";

        // 6. Hapus karakter dari ChallengeToken (case-insensitive)
        StringBuilder filteredWord = new StringBuilder();
        for (char ch : longestWord.toCharArray()) {
            if (!challengeToken.toLowerCase().contains(String.valueOf(ch))) {
                filteredWord.append(ch);
            }
        }

        // 7. Periksa jika kata kosong setelah filter
        if (filteredWord.length() == 0) {
            return "EMPTY";
        }

        return filteredWord.toString();
    }

    public static void main (String[] args) {
        // keep this function call here
        Scanner s = new Scanner(System.in);
        System.out.print(StringChallenge(s.nextLine()));
    }
}
