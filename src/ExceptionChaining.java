import java.util.Scanner;
import java.lang.Exception;

public class ExceptionChaining {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int[] array = {0,1,2,3,4,5,6};
        System.out.println("Index ke - : \n");
        boolean isTrue = true;

        while (isTrue) {
            int index = input.nextInt();
            if (index >= 9) {
                System.out.println("KELUAR");
                isTrue = false;
            }

            //Exception Reguler
            System.out.println("Exception Reguler");
            try {
                System.out.printf("Array index: %d adalah %d\n", index, array[index]);
            }catch (ArrayIndexOutOfBoundsException e){
                System.err.println("Array index out of bounds");
            }

            //Exception di dalam Method
            System.out.println("Exception di dalam Method");
            int data = exceptionMenthod(index,array);
            System.out.printf("Data dari index ke - %d adalah %d\n",index, data);

            //Exception Throw by Method
            System.out.println("Exception Throw by Method");

            int data2 = 0;
            try {
                data2 = exceptionMenthod2(index,array);
            }catch (Exception e){
                System.err.println("Exception Throw by Method version");
            }

            System.out.printf("Data dari index ke - %d adalah %d\n",index, data2);
            System.out.println("AKHIRI PROGRAM");
        }
    }
    private static int exceptionMenthod2(int i, int[] arr) throws Exception{
        int result = arr[i];
        return result;
    }
    private static int exceptionMenthod(int i, int[] arr){
        int result = 0;
        try {
            result = arr[i];
        }catch (Exception e){
            System.err.println("Array index out of bounds (Method Version)");
        }
        return result;
    }
}