import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class Statistics {
	static class Task {
		public static final String INPUT_FILE = "statistics.in";
		public static final String OUTPUT_FILE = "statistics.out";
		int N;

		String[] propositions;
		int result = 0;

		public void solve() {
			readInput();
			result = getResult(N, propositions);
			writeOutput(result);
		}

		private void readInput() {
			try {
				Scanner sc = new Scanner(new File(INPUT_FILE));
				N = sc.nextInt();
				propositions = new String[N];
				sc.nextLine();
				for (int i = 0; i < N; i++) {
					propositions[i] = sc.nextLine();
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
		 * Functia parcurge tot alfabetul, apoi, pentru fiecare litera din
		 * alfabet memoreaza intr-un vector valoarea rezultata din formula
		 * 2 * (numarul de aparitii ale literei curente din alfabet in propozitia
		 * curenta -> de la pozitia i) - dimensiunea propozitiei curente.
		 * Sortez vectorul descrescator in functie de valorile rezultate si adun rand
		 * pe rand valorile din el la o suma pana cand ajunge sa fie 0 sau mai putin.
		 * Compar la fiecare pas numarul de concatenari si daca este mai mare decat cel
		 * anterior il inlocuiesc.
		 * @param N - numarul de propozitii
		 * @param propositions - vectorul de propozitii
		 * @return - numarul de concatenari realizate
		 */
		private int getResult(int N, String[] propositions) {
			int sol = -1;
			Integer[] newVector = new Integer[N];

			for (char letter = 'a'; letter <= 'z'; letter++) {
				int sum = 0;

				for (int i = 0; i < N; i++) {
					newVector[i] = 2 * (propositions[i].length()
									- propositions[i].replace(Character.toString(letter), "")
									.length()) - propositions[i].length();
				}

				Arrays.sort(newVector, Collections.reverseOrder());
				int total = 0;

				for (int i = 0; i < N; i++) {
					sum = sum + newVector[i];

					if (sum <= 0) {
						break;
					}
					total++;
				}
				if (sol < total) {
					sol = total;
				}
			}
			return sol;
		}
	}

	public static void main(String[] args) {
		new Task().solve();
	}

}