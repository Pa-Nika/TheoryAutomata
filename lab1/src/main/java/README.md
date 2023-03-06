Грамматика имеет вид G = ( N , ∑ , P , S ), где
N - алфавит нетерминальных символов (может состоять из всего, кроме {',\",-,>,} )
∑ - алфавит терминальных символов (может состоять из всего, кроме N ⋃ {',\",-,>,} )
P - конечное множество правил (могут иметь вид a->b , где a ∈ (N ⋃ ∑)*N(N ⋃ ∑)*, ba ∈ (N ⋃ ∑)* )
S - начальный символ из N
e - зарезервированный символ для пустого слова (может появиться только в правилах)

Пример:
Enter Context Free Grammar:
N: S,A,B
∑: a,b
P: S->AB,A->aA,A->a,B->b
S: S

Enter a word
ab
YES