package hash_tables_exercises.Constructor_1;

public class HashTable {

    // CREATE CLASS VARIABLES, NODE CLASS, AND CONSTRUCTOR HERE//
    //                                                         //
    //                                                         //
    //                                                         //
    //                                                         //
    /////////////////////////////////////////////////////////////

    public Node[] getDataMap() {
        return dataMap;
    }

    public void printTable() {
        for(int i = 0; i < dataMap.length; i++) {
            System.out.println(i + ":");
            if(dataMap[i] != null) {
                Node temp = dataMap[i];
                while (temp != null) {
                    System.out.println("   {" + temp.key + ", " + temp.value + "}");
                    temp = temp.next;
                }
            }
        }
    }

}