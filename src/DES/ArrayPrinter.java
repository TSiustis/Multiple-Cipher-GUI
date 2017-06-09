package DES;

import GUI.App;

public class ArrayPrinter {
    
    public static void printarray(String[][] arr,String label) {
    	App.textArea_3.append("-- "+label+" -- "+'\n');
        
        for(int i=0;i<arr.length ;i++) {
        	App.textArea_3.append("| ");
            for(int j=0;j<arr[0].length;j++) {
                 
            	App.textArea_3.append(arr[i][j]+" ");
            }
            App.textArea_3.append("| "+'\n');
        }
    }
}