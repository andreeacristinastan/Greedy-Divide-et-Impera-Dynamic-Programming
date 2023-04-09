import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;


public class Crypto {
	static class Task {
		public static final String INPUT_FILE = "crypto.in";
		public static final String OUTPUT_FILE = "crypto.out";

		int N;
		int L;
		char[] K;
		char[] S;
		long modulo_constant = 1000000000 + 7;

		public void solve() {
			readInput();
			writeOutput(getResult(N, L, K, S));
		}

		private void readInput() {
			try {
				Scanner sc = new Scanner(new File(INPUT_FILE));
				N = sc.nextInt();
				L = sc.nextInt();
				sc.nextLine();
				String str = sc.nextLine();

				K = new char[N];
				K = str.toCharArray();

				str = sc.nextLine();

				S = new char[L];
				S = str.toCharArray();

				sc.close();
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}

		private void writeOutput(long result) {
			try {
				PrintWriter pw = new PrintWriter(new File(OUTPUT_FILE));
				pw.printf("%d\n", result);
				pw.close();
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}

		/**
		 * Functia initializeaza mai intai prima linie a matricii care
		 * ne va ajuta la construirea rezultatului cu 0 si calculeaza
		 * numarul de litere distincte din subsirul S.
		 * Apoi initializeaza prima coloana a acesteia diferit, in functie
		 * de ce caracter este la pozitia i - 1.
		 * Apoi se parcurge matricea si se aplica formulele in functie de
		 * caracterul de la pozitia i - 1 din vectorul K
		 * @param N - lungimea cheii K
		 * @param L - lungimea subsirului S
		 * @param K - cheia K
		 * @param S - subsirul S
		 * @return - de cate ori apare subsirul S in cheia K
		 */
		private long getResult(int N, int L, char[] K, char[] S) {
			long[][] matrix = new long[N + 1][L + 1];
			String helper = "";
			long distinct = 0;
			
			for (int i = 0; i < L; i++) {
				matrix[0][i] = 0;
				CharSequence seq = String.valueOf(S[i]);

				if (!helper.contains(seq)) {
					helper = helper.concat(String.valueOf(S[i]));
					distinct++;
				}

				if (i == L - 1) {
					matrix[0][i + 1] = 0;
				}
			}

			matrix[0][0] = 1;

			for (int i = 1; i < N; i++) {
				if (K[i - 1] == '?') {
					matrix[i][0] = ((distinct % modulo_constant)
									* (matrix[i - 1][0] % modulo_constant))
									% modulo_constant;
				} else {
					matrix[i][0] = matrix[i - 1][0];
				}
			}

			for (int i = 1; i <= N; i++) {
				for (int j = 1; j <= L; j++) {
					if (K[i - 1] == '?') {
						matrix[i][j] = ((matrix[i - 1][j - 1] % modulo_constant)
										+ (((distinct % modulo_constant)
										* (matrix[i - 1][j] % modulo_constant))
										% modulo_constant)) % modulo_constant;
					} else if (K[i - 1] == S[j - 1]) {
						matrix[i][j] = ((matrix[i - 1][j - 1] % modulo_constant)
										+ (matrix[i - 1][j] % modulo_constant))
										% modulo_constant;
					} else {
						matrix[i][j] = matrix[i - 1][j];
					}
				}
			}
			return matrix[N][L];
		}
	}

	public static void main(String[] args) {
		new Task().solve();
	}
}