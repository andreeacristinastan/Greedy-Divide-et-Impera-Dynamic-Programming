import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;

public class Prinel {
	static class Task {
		public static final String INPUT_FILE = "prinel.in";
		public static final String OUTPUT_FILE = "prinel.out";

		int N;
		int K;

		Integer[] target;
		Integer[] copy_target;
		int[] points;
		int[] dp;
		int[] copy_dp;
		int sum_dp = 0;
		int sum_points = 0;
		int max_dp = 200000;
		int max_loop = 100000;

		public void solve() {
			readInput();
			writeOutput(getResult(N, K, target, points));
		}

		private void readInput() {
			try {
				Scanner sc = new Scanner(new File(INPUT_FILE));
				N = sc.nextInt();
				K = sc.nextInt();
				target = new Integer[N];
				copy_target = new Integer[N];
				points = new int[N];

				for (int i = 0; i < N; i++) {
					target[i] = sc.nextInt();
					copy_target[i] = target[i];
				}

				for (int i = 0; i < N; i++) {
					points[i] = sc.nextInt();
					sum_points += points[i];
				}

				sc.close();
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}

		private void writeOutput(int result) {
			try {
				PrintWriter pw = new PrintWriter(new File(OUTPUT_FILE));
				pw.printf("%d\n", result);
				pw.close();
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}

		/**
		 * Functia implementeaza algoritmul problemei rucsacului.
		 * Se ia o matrice de dimensiune [N + 1]*[K + 1] pe care
		 * o vom folosi pe prima linie pentru multimea vida si pe
		 * prima coloana pentru situatia in care ghiozdanul are
		 * capacitate 0.
		 * Parcurg matricea si daca nu folosesc obiectul i inseamna
		 * ca solutia este cea de la pasul i - 1
		 * Daca folosesc obiectul, rezerv in rucsac copy_dp[i - 1] unități, iar
		 * inainte ocup maxim cap - copy_dp[i - 1] unități
		 * @param N - numarul de elemente ale vectorului target
		 * @param K - de cate ori este folosita operatia
		 * @param copy_dp - vectorul ce contine numarul maxim de operatii pentru fiecare
		  					element din target
		 * @param points - vectorul de puncte pentru fiecare element din target
		 * @return - numarul maxim de puncte ce pot fi obtinute
		 */
		private int rucsac(int N, int K, int[] copy_dp, int[] points) {
			int[][] matrix = new int[N + 1][K + 1];
		
			for (int i = 1; i <= N; ++i) {
				for (int cap = 0; cap <= K; ++cap) {
					matrix[i][cap] = matrix[i - 1][cap];
		
					if (cap - copy_dp[i - 1] >= 0) {
						int sol_aux = matrix[i - 1][cap - copy_dp[i - 1]] + points[i - 1];
		
						matrix[i][cap] = Math.max(matrix[i][cap], sol_aux);
					}
				}
			}
			return matrix[N][K];
		}

		/**
		 * Functia calculeaza numarul maxim de operatii pentru toate numerele
		 * cuprinse intre 1 si numarul maxim din vectorul target
		 * Memoreaza doar operatiile pentru elementele din vectorul target in
		 * copy_dp si apoi apeleaza functia rucsac pentru a determina
		 * rezultatul.
		 * @param N - numarul de elemente ale vectorului target
		 * @param K - de cate ori este folosita operatia
		 * @param target - vectorul cu elementele pentru care se va efectua
		 					logica programului
		 * @param points - vectorul de puncte pentru fiecare element din target
		 * @return - numarul maxim de puncte ce pot fi obtinute
		 */
		private int getResult(int N, int K, Integer[] target, int[] points) {
			Arrays.sort(target);

			dp = new int[max_dp];
			dp[1] = 0;

			for (int i = 2; i < max_loop; i++) {
				dp[i] = i;
			}

			for (int i = 1; i < max_loop; i++) {
				for (int j = 1; j <= Math.sqrt(i); j++) {
					if (i % j == 0) {
						dp[i + j] = Math.min(dp[i + j], dp[i] + 1);
						dp[i + i / j] = Math.min(dp[i + i / j], dp[i] + 1);
					}
				}

				if (i == target[target.length - 1]) {
					break;
				}
			}

			copy_dp = new int[target.length];

			for (int i = 0; i < N; i++) {
				copy_dp[i] = dp[copy_target[i]];
				sum_dp += copy_dp[i];
			}

			if (sum_dp <= K) {
				return sum_points; 
			}

			return rucsac(N, K, copy_dp, points);
		}
	}

	public static void main(String[] args) {
		new Task().solve();
	}
}