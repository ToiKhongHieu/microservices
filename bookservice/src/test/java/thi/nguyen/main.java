package thi.nguyen;

import java.util.Scanner;

public class main {
    public static void main(String[] args) {
        Boolean check = true;
        Scanner sc = new Scanner(System.in);
        do {
            try {
                System.out.println("Bạn muốn nhập bao nhiêu số nguyên?");
                int n = sc.nextInt();
                sc.nextLine();
                String[] arr = new String[n];
                for (int i = 0; i < n; i++) {
                    do{
                        System.out.println("Nhập chữ số thứ " + (i + 1));
                        arr[i] = sc.nextLine();
                    }while (!arr[i].matches("\\d+"));
                }
                check = false;
            } catch (Exception exception) {
                System.out.println("Vui lòng nhập số");
                System.out.println("-------------------------");
                sc.nextLine();
            }
            ;
        } while (check);
    }
}
