#Rules of possible operations
def movements(initial_table, i, mov):
    temp = initial_table[i]

    # u -> Up, d -> Down, l -> Left, r -> Right
    if mov == 'u':
        initial_table[i] = initial_table[i-4]
        initial_table[i-4] = temp
    elif mov == 'd':
        initial_table[i] = initial_table[i+4]
        initial_table[i+4] = temp
    elif mov == 'l':
        initial_table[i] = initial_table[i-1]
        initial_table[i-1] = temp
    elif mov == 'r':
        initial_table[i] = initial_table[i+1]
        initial_table[i+1] = temp

#Solvability of the puzzle -> NOTA: NÃO é o algoritmo optimizado
def solvability(initial_table, final_table):
    inv = 0 #Permutacoes
    blankRow = final_table.index(0) #espaco em branco
    #NOTA: Ver como funciona a regra do espaco em branco

    for i in range(16):
        if initial_table[i] != final_table[i]: #Se nao forem iguais, fazer permutacoes
            j = initial_table.index(final_table[i])
            for k in range(j, i, -1): #Troca todos os numeros no espaco entre i e j
                temp = initial_table[k-1]
                initial_table[k-1] = initial_table[k]
                initial_table[k] = temp
                inv += 1

    return (blankRow%2 == 0) == (inv%2 == 0)

#Input lines
initial_table = list(map(int, input().split()))
final_table = list(map(int, input().split()))

if solvability(initial_table, final_table): print("Ha solucao")
else: print("Nao ha solucao")
