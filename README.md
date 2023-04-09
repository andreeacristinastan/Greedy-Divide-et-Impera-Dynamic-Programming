# Greedy-Divide-et-Impera-Dynamic-Programming
#Copyright Stan Andreea-Cristina 323CA

1.Walsh
    Problema se reduce la una dintre problemele rezolvate
    la laborator si anume Zparcurgere.
    Astfel, avand la baza chiar ideea rezolvarii laboratorului postata
    pe Teams, am realizat un Divide et Impera pe o matrice careia nu ii
    stiam decat dimensiunea si pe care o imparteam recursiv in 4 la
    fiecare pas. 
    Astfel incep prin a calcula coltul din dreapta jos si mijlocul si apoi
    calculez in ce subdreptunghi al matricii mele imaginare se afla perechea
    data ca parametru (x, y) -> (exact ca in laborator).
    Impart matricea in 4 parti, iar fiecare parte reprezinta un cadran.
    Injumatatesc la fiecare pas dimensiunea de la pasul anterior a matricii.
    Dupa ce fac aceste calcule, iau toate cele 4 cazuri in care se poate incadra
    matricea mea, si anume coltul din stanga sus, stanga jos, dreapta sus sau dreapta
    jos, calculand cu ajutorul functiei in_rectangle, colturile fiecarui nou dreptunghi
    format.

    Inca de la inceput, imi iau o variabila number initial egala cu 0 in care memorez ce
    valoare se afla, in functie de cadran, pentru perechea (x, y). Astfel, atunci cand
    apelez recursiv, in cazul in care ma aflu in coltul din dreapta jos voi face un xor
    intre numar si 1 pentru ca aceasta va intoarce !number, fix cum am nevoie. 

    Rezultatul va fi chiar intoarcerea din apelul recursiv.

    Referinte : rezolvarea laboratorului 1 a problemei Zparcurgere

2.Statistics
    Problema incepe prin parcurgerea intregului alfabet.
    Apoi, pentru fiecare litera, parcurg vectorul in care am memorat propozitiile
    date initial de problema si memorez intr-un vector dominanta fiecarei litere in
    raport cu propozitia curenta (folosind formula prelucrata din cerinta ->
    2 * numarul de aparitii ale literei respective * dimensiunea propozitiei).
    Numarul de aparitii a literei in propozitia curenta l-am calculat dupa acest link,
    pentru ca am cautat un mod cat mai eficient pentru asta [1].

    Dupa ce am memorat in vector fiecare valoare pentru litera curenta, sortez descrescator
    vectorul folosindu-ma de functia predefinita din Java, apoi cu un for merg prin tot vectorul
    si adaug intr-o variabila valorile din acesta pana cand suma finala ajunge mai mica sau egala
    cu 0. In acest moment inseamna ca am facut concatenarile si pot iesi din bucla.
    In timp ce adun, memorez intr-un contor si cate concatenari fac, si la finalul loop-ului, verific
    daca numarul de concatenari pe care l-am facut este mai mare decat ultimul numar maxim de concatenari.

    Referinte : [1]:https://stackoverflow.com/questions/275944/how-do-i-count-the-number-of-occurrences-of-a-char-in-a-string

3.Prinel
    Problema este formata din doua parti : prima parte, cea in care calulez numarul de operatii pentru
    elementele din target si cea de-a doua care se reduce la problema rucsacului din laboratorul de
    programare dinamica. 

    Initial, asa cum am spus si mai sus, incep prin calculul numarului de operatii pentru fiecare numar.
    Initial, aceasta valoare va fi fix numarul in sine, apoi, cu ajutorul unui loop voi lua din nou
    toate numerele naturale de la 1 pana la 10^5, apoi voi calcula toti divizorii fiecarui numar.
    Pentru eficienta, cu for-ul de divizori voi merge pana la sqrt(i) si voi calcula cate 2 divizori
    odata(spre exemplu, daca lista de divizori este [1 2 3 6], in momentul in care calculez dp[2],
    voi calcula si dp[6]).
    Tot pentru eficienta, nu voi calcula pentru toate numerele de la 1 la 10^5, ci ma voi opri
    cand i ul ajunge la cea mai mare valoarea din target(vector pe care l-am sortat la inceputul
    functiei).
    Imi memorez intr-un vector separat doar numarul de operatii pentru elementele din target si
    calculez si suma tuturor rezultatelor obtinute.
    Pentru ca algoritmul rucsacului este destul de costisitor ca timp, daca suma calculata anterior
    este mai mica sau egala decat K(ce reprezinta de cate ori este folosita operatia), atunci returnez
    direct suma elementelor din vectorul de puncte dat initial, altfel, apelez functia rucsac si 
    realizez algoritmul explicat si in laboratorul de programare dinamica 1/2.

    Referinte : https://ocw.cs.pub.ro/courses/pa/laboratoare/laborator-03

4.Crypto
    Aici incep prin a declara o matrice de dimensiune [N + 1] * [L + 1] cu ajutorul careia voi ajunge
    la rezultatul final. 
    Incep prin a initializa prima linie din matrice cu 0, si tot aici sa calculez si numarul de litere
    distincte din subsirul meu dat ca parametru.
    Apoi, initializez prima coloana privind doua criterii : daca elementul de la pozitia i - 1 este '?'
    voi initializa cu elementul de la linia anterioara de pe prima coloana * numarul de litere distincte
    din subsirul dat ca parametru, si daca elementul este o litera, initializez doar cu elementul de la
    linia anterioara de pe prima coloana.
    Apoi, parcurg matricea si impart problema in 3 cazuri: cand ultimele caractere sunt la fel, cand
    difera si cand caracterul de la pozitia i - 1 este '?'.
    Pentru primele doua, regula este explicata si scrisa in cele doua referinte pe care le-am avut, iar
    ce-a dea treia am calculat-o si am ajuns la formula : 
    matrix[i - 1][j - 1] + numarul de litere distincte din subsirul dat ca parametru * matrix[i - 1][j]

    Pentru toate operatiile am efectuat modulo 10^9 + 7 cum scrie si in cerinta, respectand regula din
    laboratorul 1 de la problema exponentiere logaritmica unde este explicat cum se aplica corect operatia
    modulo pe un calcul.

    In final, returnez ultimul elemen din matrice care este si rezultatul meu.

    Referinte : https://ocw.cs.pub.ro/courses/pa/laboratoare/laborator-01
                https://www.geeksforgeeks.org/find-number-times-string-occurs-given-string/
                https://www.youtube.com/watch?v=YbanXpPqW1g
