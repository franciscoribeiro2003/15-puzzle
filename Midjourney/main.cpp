#include <iostream>
#include <string>
#include "Puzzle15.h"

using namespace std;

int main() {
    // read from scanf two lines, first line is the initial board and second line is the objective board, the numbers are separated by spaces
    string line;
    int beginTabuleiro[16];
    int objectiveTabuleiro[16];
    int i = 0;
    while (getline(cin, line)) {
        int j = 0;
        int pos = 0;
        while (pos < line.length()) {
            int pos2 = line.find(" ", pos);
            if (pos2 == -1) pos2 = line.length();
            string num = line.substr(pos, pos2-pos);
            if (i == 0) {
                beginTabuleiro[j] = stoi(num);
            } else {
                objectiveTabuleiro[j] = stoi(num);
            }
            j++;
            pos = pos2+1;
        }
        i++;
    }

    // print true or false according to the result of the search
    Tabuleiro begin(beginTabuleiro);
    Tabuleiro objective(objectiveTabuleiro);
    if (begin.hasSolution(objective)) {
        DFS dfs;
        if (dfs.search(begin, objective)) {
            cout << "true" << endl;
        } else {
            cout << "false" << endl;
        }
    } else {
        cout << "false" << endl;
    }

    return 0;
}
