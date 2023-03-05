//Auxiliar Functions
class Auxiliary {
    //Find index of a specific number
    int findIndex(int[] table, int number) {
        int res = -1;
        for(int i = 0; i < table.length; i++) {
            if(table[i] == number) {
                res = i;
                break;
            }
        }

        return res;
    }

    //Compare two arrays
    boolean compare(int[] list1, int[] list2) {
        boolean res = true;

        for(int i = 0; i < list1.length; i++) 
            if(list1[i] != list2[i]) 
                res = false;
            

        return res;
    }

    //Copy an Array
    int[] copyArray(int[] list) {
        int[] res = new int[list.length];
        for(int i = 0; i < list.length; i++) {
            res[i] = list[i];
        }

        return res;
    }
}