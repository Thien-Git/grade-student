import javax.sound.midi.Soundbank;
import java.util.ArrayList;
import java.util.Scanner;

public class GradeStudent {
    public static void main(String[] args) {
        begin(); // hàm hiển thị thông điệp chào mừng
        System.out.println();

        System.out.println("MidTerm: ");
        int weightMidTerm1 = weightMidTerm(); //Hàm nhập và trả về trọng số điểm thi giữa kì
        double weightedMidTermScore = midTerm(weightMidTerm1); //Hàm tính toán và trả về điểm thi giữa kì đã tính dựa trên trọng số
        System.out.println();

        System.out.println("Final: ");
        int weightFinalTerm1 = weightFinalTerm(weightMidTerm1); //Hàm nhập và trả về trọng số điểm thi cuối kì
        double weightedFinalTermScore = finalTerm(weightFinalTerm1); //Hàm tính toán và trả về điểm thi cuối kì đã tính dựa trên trọng số
        System.out.println();

        System.out.println("HomeWork: ");
        double weightedHomeworkScore = homework(weightMidTerm1, weightFinalTerm1); //Hàm tính toán và trả về điểm của bài tập về nhà đã tính dựa trên trọng số
        System.out.println();

        report(weightedMidTermScore, weightedFinalTermScore, weightedHomeworkScore); // Hàm Hiển thị kết quả tổng cộng theo môn


    }


    // hàm hiển thị thông điệp chào mừng
    public static void begin() {
        System.out.println("This program reads exam/homework scores and reports your overall course grade.");
    }



    //Hàm nhập và trả về trọng số điểm thi giữa kì

    public static int weightMidTerm() {
        Scanner sc = new Scanner(System.in);
        int weightMidTerm;
        boolean test;

        do {
            System.out.print("Weight (0-100)? ");
            weightMidTerm = sc.nextInt();

            //Điều kiệm trọng số phải lớn hơn 0 và nhỏ hơn 100, nếu nhập sai thì nhập lại

            if (weightMidTerm > 0 && weightMidTerm < 100) {
                test = false;
            } else {
                System.out.println("Bạn nhập trọng số không đúng, mời bạn nhập lại");
                test = true;
            }
        } while (test == true);

        return weightMidTerm;
    }



    //Hàm tính toán và trả về điểm thi giữa kì đã tính dựa trên trọng số

    public static double midTerm(int weightMidTerm1) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Score earned? ");

        // Nhập điểm đạt được
        int scoreEarned = sc.nextInt();



        //nhập số điểm được tăng (nếu có)
        int scoresShifted;
        int sumShiftAmount = 0;
        boolean test;
        do {
            System.out.print("Were scores shifted (1 = yes, 2 = no)? ");
            scoresShifted = sc.nextInt();
            if (scoresShifted == 1) {
                System.out.print("Shift amount? ");
                int shiftAmount = sc.nextInt();
                sumShiftAmount += shiftAmount;
                test = false;
            } else if (scoresShifted == 2) {
                test = false;
            } else {
                System.out.println("Bạn nhập không đúng, mời bạn nhập lại!");
                test = true;
            }
        } while (test == true);


        int totalPoint = scoreEarned + sumShiftAmount;
        if (totalPoint > 100) {
            totalPoint = 100;
        }
        System.out.println("Total points = " + totalPoint + " / 100");

        //điểm số tính dựa trên trọng số
        double weightedMidTermScore = (Math.ceil((double) totalPoint / 100 * weightMidTerm1 * 10) / 10);
        System.out.println("Weighted score = " + weightedMidTermScore + " / " + weightMidTerm1);

        return weightedMidTermScore;

    }


    //Hàm nhập và trả về trọng số điểm thi cuối kì

    public static int weightFinalTerm(int weightMidTerm1) {
        Scanner sc = new Scanner(System.in);
        int weightFinalTerm;
        boolean test;

        //Điều kiện trọng số phải lớn hơn 0 và nhỏ hơn 100 trừ trọng số giữa kì và bài tập về nhà, nếu nhập sai thì nhập lại

        do {
            System.out.print("Weight (0-100)? ");
            weightFinalTerm = sc.nextInt();

            if (weightFinalTerm > 0 && weightFinalTerm < (100 - weightMidTerm1)) {
                test = false;
            } else {
                System.out.println("Bạn nhập trọng số không đúng, mời bạn nhập lại");
                test = true;
            }
        } while (test == true);

        return weightFinalTerm;
    }


    //Hàm tính toán và trả về điểm thi cuối kì tính dựa trên trọng số

    public static double finalTerm(int weightFinalTerm) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Score earned? ");
        int scoreEarned = sc.nextInt();

        //nhập số điểm được tăng (nếu có)
        int scoresShifted;
        int sumShiftAmount = 0;
        boolean test;
        do {
            System.out.print("Were scores shifted (1 = yes, 2 = no)? ");
            scoresShifted = sc.nextInt();
            if (scoresShifted == 1) {
                System.out.print("Shift amount? ");
                int shiftAmount = sc.nextInt();
                sumShiftAmount += shiftAmount;
                test = false;
            } else if (scoresShifted == 2) {
                test = false;
            } else {
                System.out.println("Bạn nhập không đúng, mời bạn nhập lại!");
                test = true;
            }
        } while (test == true);


        //điểm tối đa của total point là 100
        int totalPoint = scoreEarned + sumShiftAmount;
        if (totalPoint > 100) {
            totalPoint = 100;
        }
        System.out.println("Total points = " + totalPoint + " / 100");

        //điểm số tính dựa trên trọng số
        double weightedFinalTermScore = (Math.floor((double) totalPoint / 100 * weightFinalTerm * 10) / 10);
        System.out.println("Weighted score = " + weightedFinalTermScore + " / " + weightFinalTerm);

        return weightedFinalTermScore;
    }



    //Hàm tính toán và trả về điểm của bài tập về nhà đã tính dựa trên trọng số
    public static double homework(int weightMidTerm, int weightFinalTerm) {
        Scanner sc = new Scanner(System.in);

        //Nhập  trọng số bài tập về nhà
        int weightHomework;
        boolean test;
        // Điều kiệm trọng số phải = 100 trừ tổng trọng số giữa kì và trong số cuối kì
        // Vì trong số giữa kì + cuối kì + bài tập về nhà  = 100
        do {
            System.out.print("Weight (0-100)? ");
            weightHomework = sc.nextInt();
            if (weightHomework == (100 - (weightMidTerm + weightFinalTerm))) {
                test = false;
            } else {
                System.out.println("Bạn nhập trọng số không đúng, mời bạn nhập lại");
                test = true;
            }

        } while (test == true);

        //Nhập số lượng bài tập về nhà cần làm
        System.out.print("Number of assignments? ");
        int numBerAssignment = sc.nextInt();

        // Tạo ArrayList để lưu điểm thực và điểm tối đa của các số bài tập về nhà cần làm
        ArrayList<Integer> arrScore = new ArrayList<Integer>();
        ArrayList<Integer> arrMax = new ArrayList<Integer>();

        //lặp qua số lượng bài tập về nhà cần làm và lưu và mảng
        for (int i = 1; i <= numBerAssignment; i++) {
            System.out.print("Assignment " + i + " score and max? ");
            int score = sc.nextInt();
            arrScore.add(score);
            int max = sc.nextInt();
            arrMax.add(max);
        }

        //Nhập số lượng buổi học sinh viên đã đi học và được điểm danh
        System.out.print("How many sections did you attend? ");
        int manySection = sc.nextInt();


        // Tính tổng số điểm chuyên cần của sinh viên, với mỗi buổi được điểm danh, thì sinh viên sẽ được tính 5 điểm
        int attend = manySection * 5;
        if (attend > 30) {
            attend = 30; // điểm chuyên cần tối đa là 30 điểm
        }
        System.out.println("Section points = " + attend + " / 30");

        int totalScore = 0; //tạo biến để lưu điểm thực bài về nhà
        for (int i = 0; i < arrScore.size(); i++) {
            totalScore += arrScore.get(i);
        }
        if (totalScore > 120) {
            totalScore = 150 - attend; //
        }

        int totalMax = 0; //tạo biến để lưu điểm tối đa bài về nhà
        for (int i = 0; i < arrMax.size(); i++) {
            totalMax += arrMax.get(i);
        }
        if (totalMax > 120) {
            totalMax = 120; // điểm tối đa bài về nhà là 120 vì điểm tối đa của Assignment là 150
        }

        System.out.println("Total points = " + (totalScore + attend) + " / " + (totalMax + 30));

        //điểm số bài homework tính dựa trên trọng số
        double weightedHomeworkScore = (Math.floor(((double) (totalScore + attend) * 45 / (totalMax + 30)) * 10) / 10);
        System.out.println("Weighted score = " + weightedHomeworkScore + " / 45");

        return weightedHomeworkScore;

    }


    // Hàm Hiển thị kết quả tổng cộng theo môn
    public static void report(double weightedMidTermScore, double weightedFinalTermScore, double weightedHomeworkScore) {
        double overallPercent = weightedMidTermScore + weightedFinalTermScore + weightedHomeworkScore;
        if (overallPercent >=100) {
            overallPercent = 100;
        }
        System.out.println("Overall percentage = " +overallPercent);


        //hiển thị thông báo (nhận xét) tuỳ ý dựa vào GPA của sinh viên.
        if(overallPercent == 100) {
            System.out.println("Your grade will be at least : 4.0");
            System.out.println("Xếp loại : Xuất sắc");
        } else if(overallPercent >= 85) {
            System.out.println("Your grade will be at least : 3.0");
            System.out.println("Xếp loại : Giỏi");
        } else if(overallPercent <= 84.99 && overallPercent >=75) {
            System.out.println("Your grade will be at least : 2.0");
            System.out.println("Xếp loại : Khá");
        } else if (overallPercent >= 60 && overallPercent <= 74.99){
            System.out.println("Your grade will be at least : 0.7");
            System.out.println("Xếp loại : Trung bình");
        } else {
            System.out.println("Your grade will be at least : 0.0");
            System.out.println("Xếp loại : Yếu");
        }
    }
}

