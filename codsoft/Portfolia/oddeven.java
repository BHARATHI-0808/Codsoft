
import java.util.Scanner;

public class oddeven {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("enter your number");
        int a= scanner.nextInt();
        if (a%2==0) {
           System.out.println("even"); 
        }
        else if(a%2!=0){
            System.out.println("odd"); 
        
        }

    }
    
}
