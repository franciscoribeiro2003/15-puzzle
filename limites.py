def limites(tabuleiro, direction):
    #encontrar o elemento 0
    pos=tabuleiro.index(0)
    if (direction=='u'): #up
        if (pos>=0 and pos<4):
            return False
        return True
    elif (direction=='d'): #down
        if (pos>=12 and pos<16):
            return False
        return True
    elif (direction=='l'): #left
        if (pos==0 or pos==4 or pos==8 or pos==12):
            return False
        return True
    elif (direction=='r'): #right
        if (pos==3 or pos==7 or pos==11 or pos==15):
            return False
        return True
