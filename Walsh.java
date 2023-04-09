import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Walsh {

	static class Task {
		public static final String INPUT_FILE = "walsh.in";
		public static final String OUTPUT_FILE = "walsh.out";

		int N, K;
		int[][] pairs;
		int[] result;

		public void solve() {
			readInput();
			for (int i = 0 ; i < K; i++) {
				result[i] = getResult(N, pairs[i][0], pairs[i][1]);
			}
			writeOutput(result);
		}

		private void readInput() {
			try {
				Scanner sc = new Scanner(new File(INPUT_FILE));
				N = sc.nextInt();
				K = sc.nextInt();
				pairs = new int[K][2];
				result = new int[K];
				for (int i = 0; i < K; i++) {
					for (int j = 0; j < 2; j++) {
						pairs[i][j] = sc.nextInt();
					}
				}
				sc.close();
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}

		private void writeOutput(int[] result) {
			try {
				PrintWriter pw = new PrintWriter(new File(OUTPUT_FILE));
				for (int i = 0; i < K; i++) {
					pw.printf("%d\n", result[i]);
				}
				pw.close();
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}

		/**
		 * (x, y) in dreptunghiul [ (tlx, tly), (drx, dry) ]
		 * @param x - linia pe care se afla elementul cautat 
		 * @param y - coloana pe care se afla elementul cautat
		 * @param tlx - una din coordonatele verticale ale dreptunghiului [(tlx, tly), (drx, dry)]
		 * @param tly - una din coordonatele orizontale ale dreptunghiului [(tlx, tly), (drx, dry)]
		 * @param drx - una din coordonatele verticale ale dreptunghiului [(tlx, tly), (drx, dry)]
		 * @param dry - una din coordonatele orizontale ale dreptunghiului [(tlx, tly), (drx, dry)]
		 * @return - x intre cele 2 laturi verticale si y intre cele 2 laturi orizontale
		 */
		boolean in_rectangle(int x, int y, int tlx, int tly, int drx, int dry) {
			return (tlx <= x && x <= drx)
					&& (tly <= y && y <= dry);
		}

		/**
		 * Functia calculeaza recursiv valoarea de la linia x si coloana y dintr-o
		 * matrice imaginara pe care o impart in 4 cadrane.
		 * In functie de cadranul in care se afla x si y in raport cu dimensiunea matricei,
		 * reapelez functia cu noile dimensiuni potrivite avand grija ca in cazul in care
		 * ajung pe ramura in care x si y se afla in coltul din dreapta-jos sa modific number
		 * negandu-l (din 0 va fi 1 si invers).
		 * 
		 * @param x - linia pe care se afla elementul cautat
		 * @param y - coloana pe care se afla elementul cautat
		 * @param tlx - una din coordonatele verticale ale dreptunghiului [(tlx, tly), (drx, dry)]
		 * @param tly - una din coordonatele orizontale ale dreptunghiului [(tlx, tly), (drx, dry)]
		 * @param D - dimensiunea matriciei
		 * @param number - valoarea de la o anumita pozitie din matrice
		 * @return - functia returneaza 0 sau 1 in functie de ce se intoarce din recursivitate
		 */
		int helper(int x, int y, int tlx, int tly, int D, int number) {
			if (D == 1) {
				return number;
			}
			
			// down right
			int drx = tlx + D - 1;
			int dry = tly + D - 1;

			// middle cell
			int mx = (tlx + drx) >> 1;
			int my = (tly + dry) >> 1;

			// in care subdreptunghi se afla x, y?
			// are dimensiune de 2 ori mai mica => D / 2
			// fiecare submatrice are 1/4 din sirul de numere
			int new_D = (D >> 1); // D / 2

			// Perechea mea (x, y) se afla in coltul din stanga-sus
			if (in_rectangle(x, y, tlx, tly, mx, my)) {
				return helper(x, y, tlx, tly, new_D, number);
			}

			// Perechea mea (x, y) se afla in coltul din dreapta-sus
			if (in_rectangle(x, y, tlx, my + 1, mx, dry)) {
				return helper(x, y, tlx, my + 1, new_D, number);
			}

			// Perechea mea (x, y) se afla in coltul din stanga-jos
			if (in_rectangle(x, y, mx + 1, tly, drx, my)) {
				return helper(x, y, mx + 1, tly, new_D, number);
			}

			// Perechea mea (x, y) se afla in coltul din dreapta-jos
			if (in_rectangle(x, y, mx + 1, my + 1, drx, dry)) {
				return helper(x, y, mx + 1, my + 1, new_D, number ^ 1);
			}

			// avem mereu solutie, nu se va ajunge aici insa functia trebuie sa
			// aiba un return by default
			return -1;
		}

		private int getResult(int N, int x, int y) {
			return helper(x, y, 1, 1, N, 0);
		}
	}

	public static void main(String[] args) {
		new Task().solve();
	}
}
