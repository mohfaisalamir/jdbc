import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Exception extends Throwable {
    // review materi Exception Handling (try, catch, finaly)
    public static void main(String[] args) throws FileNotFoundException {
        Scanner input = new Scanner(System.in);
        int[] array = {0,1,2,3,4,5,6};
        FileInputStream fis = null;
        boolean isTrue = true;

        while (isTrue){
            int index = input.nextInt();
            if(index >=8){
                System.out.println("KELUAR");
                isTrue = false;
            }
//            try {
//                System.out.printf("array index: %d adalah %d\n", index, array[index]);
//
//            }catch (ArrayIndexOutOfBoundsException e){
//                System.err.println(e.getMessage());
//            }
//
//            // runtime error jika tidak ada
//            try {
//                fis = new FileInputStream("input.txt");
//            }catch (IOException e){
//                System.err.println(e.getMessage()+ " Input file not found");
//            }
//            // mengabungkan dua Exception diatas
//            System.out.println("akhir program , JANCOK");

            try {
                System.out.printf("Array index: %d adalah %d\n", index, array[index]);
                //fis = new FileInputStream("input.txt");

            }catch (ArrayIndexOutOfBoundsException e){
                System.err.println("Bid'ah, index kelebihan");
            //}catch (FileNotFoundException e){
                System.err.println("File mu raenek su,");
            }finally {
                System.out.println("ini adalah finaly");;
            }
        }

    }
}
