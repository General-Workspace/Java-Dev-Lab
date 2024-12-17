package RecordProject;

public class Main {
    public static void main(String[] args) {
        // using pojo (plain old java object)
//        for (int i = 1; i <= 5; ++i) {
//            Student s = new Student("S92300%d".formatted(i), switch(i) {
//                case 1 -> "Mary";
//                case 2 -> "Bob";
//                case 3 -> "Aniebiet";
//                case 4 -> "Sandra";
//                case 5 -> "Jennifer";
//                default -> "Anonymous";
//            }, "01/11/1996", "Java Masterclass");
//
//            System.out.println(s);
//        }

        // using record
//        for (int i = 1; i <= 5; ++i) {
//            LPAStudent s = new LPAStudent("S92300%d".formatted(i), switch(i) {
//                case 1 -> "Mary";
//                case 2 -> "Bob";
//                case 3 -> "Aniebiet";
//                case 4 -> "Sandra";
//                case 5 -> "Jennifer";
//                default -> "Anonymous";
//            }, "01/11/1996", "Java Masterclass");
//
//            System.out.println(s);
//        }

        Student pojoStudent = new Student("S923006", "Aniebiet Afia", "01/11/1996", "Java Masterclass");
        LPAStudent lpaStudent = new LPAStudent("S923007", "Aniebiet Afia", "01/11/1996", "Java Masterclass");

        String pojoStudentOutput = "%s with student id %s is taking the course %s".formatted(pojoStudent.getName(), pojoStudent.getId(), pojoStudent.getClassList());
        System.out.println(pojoStudentOutput);

        String lpaStudentOutput = "%s with student id %s is taking the course %s".formatted(lpaStudent.name(),
                lpaStudent.id(), lpaStudent.classList());
        System.out.println(lpaStudentOutput);
    }
}
