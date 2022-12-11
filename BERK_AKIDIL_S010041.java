import java.util.Scanner;

public class BERK_AKIDIL_S010041 {
    
    public static void main(String[] args) {
        
        int NumberOfOutlets, MaximumLamps = 0;
        int[] Outlets, Lamps;
        Scanner scanner = new Scanner(System.in);
        
        // Read the number of Outlets
        NumberOfOutlets = scanner.nextInt();
        
        // Read the Outlets
        Outlets = new int[NumberOfOutlets];
        for (int i = 1; i <= NumberOfOutlets; i++) {
            Outlets[i-1] = Integer.parseInt(scanner.next(), 16);
        }
        
        // Read the lamps
        Lamps = new int[NumberOfOutlets];
        for (int i = 1; i <= NumberOfOutlets; i++) {
            Lamps[i-1] = Integer.parseInt(scanner.next(), 16);
        }
        
        // Compute the maximum number of lamps that can be turned on
        int[][] DPTable = new int[NumberOfOutlets + 1][NumberOfOutlets + 1];
        for (int i = 0; i < NumberOfOutlets; i++) {
            for (int j = 0; j < NumberOfOutlets; j++) {
                if (Outlets[i] == Lamps[j]) {
                    DPTable[i+1][j+1] = DPTable[i][j] + 1;
                    if (MaximumLamps < DPTable[i+1][j+1]) {
                        MaximumLamps = DPTable[i+1][j+1];
                    }
                } else {
                    DPTable[i+1][j+1] = Math.max(DPTable[i][j+1], DPTable[i+1][j]);
                }
            }
        }
        
        // Print the maximum number of lamps that can be turned on
        System.out.println(MaximumLamps);
        
        // Print the codes of lamps that can be turned on
        int i = NumberOfOutlets;
        int j = NumberOfOutlets;
        StringBuilder SB = new StringBuilder();
        while (0 < i && 0 < j) {
            if (Outlets[i - 1] == Lamps[j - 1]) {
                SB.insert(0, Integer.toHexString(Lamps[j - 1]) + " ");
                i--;
                j--;
            } else if (DPTable[i - 1][j] > DPTable[i][j - 1]) {
                i--;
            } else {
                j--;
            }
        }
        System.out.println(SB.toString());
    }
}