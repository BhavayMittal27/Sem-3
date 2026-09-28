import java.util.*;

// Question 3: Online Examination System
public class Question3_OnlineExaminationSystem {

    static abstract class Question {
        private String id;
        private String prompt;
        private int points;

        public Question(String id, String prompt, int points) {
            this.id = id;
            this.prompt = prompt;
            this.points = points;
        }

        public String getId() {
            return id;
        }

        public String getPrompt() {
            return prompt;
        }

        public int getPoints() {
            return points;
        }

        public abstract boolean evaluate(String answer);
    }

    static class MultipleChoiceQuestion extends Question {
        private String correctAnswer;

        public MultipleChoiceQuestion(String id, String prompt, int points, String correctAnswer) {
            super(id, prompt, points);
            this.correctAnswer = correctAnswer;
        }

        @Override
        public boolean evaluate(String answer) {
            return correctAnswer.equalsIgnoreCase(answer.trim());
        }
    }

    static class TrueFalseQuestion extends Question {
        private boolean correctAnswer;

        public TrueFalseQuestion(String id, String prompt, int points, boolean correctAnswer) {
            super(id, prompt, points);
            this.correctAnswer = correctAnswer;
        }

        @Override
        public boolean evaluate(String answer) {
            return Boolean.parseBoolean(answer.trim()) == correctAnswer;
        }
    }

    static class ShortAnswerQuestion extends Question {
        private String expectedKeywords;

        public ShortAnswerQuestion(String id, String prompt, int points, String expectedKeywords) {
            super(id, prompt, points);
            this.expectedKeywords = expectedKeywords;
        }

        @Override
        public boolean evaluate(String answer) {
            return answer != null && answer.toLowerCase().contains(expectedKeywords.toLowerCase());
        }
    }

    static class Examination {
        private String examId;
        private String title;
        private List<Question> questions = new ArrayList<>();

        public Examination(String examId, String title) {
            this.examId = examId;
            this.title = title;
        }

        public void addQuestion(Question question) {
            questions.add(question);
        }

        public String getExamId() {
            return examId;
        }

        public String getTitle() {
            return title;
        }

        public List<Question> getQuestions() {
            return questions;
        }
    }

    static class Student {
        private String id;
        private String name;

        public Student(String id, String name) {
            this.id = id;
            this.name = name;
        }

        public String getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        @Override
        public String toString() {
            return name;
        }
    }

    static class Attempt {
        private Student student;
        private Examination examination;
        private Map<String, String> recordedAnswers = new LinkedHashMap<>();
        private boolean submitted;

        public Attempt(Student student, Examination examination) {
            this.student = student;
            this.examination = examination;
            this.submitted = false;
            System.out.println(examination.getTitle() + " started by " + student.getName() + ".");
        }

        public void recordAnswer(int questionIndex, String answer) {
            if (submitted) {
                System.out.println("Cannot change answers for a submitted examination.");
                return;
            }
            if (questionIndex >= 1 && questionIndex <= examination.getQuestions().size()) {
                Question q = examination.getQuestions().get(questionIndex - 1);
                recordedAnswers.put(q.getId(), answer);
                System.out.println("Answer recorded for Question " + questionIndex + ".");
            }
        }

        public void submit() {
            if (submitted) {
                System.out.println("Exam already submitted.");
                return;
            }
            submitted = true;
            System.out.println(examination.getTitle() + " submitted by " + student.getName() + ".");

            int totalScore = 0;
            int maxScore = 0;
            List<String> results = new ArrayList<>();

            for (int i = 0; i < examination.getQuestions().size(); i++) {
                Question q = examination.getQuestions().get(i);
                maxScore += q.getPoints();
                String ans = recordedAnswers.get(q.getId());
                boolean correct = (ans != null) && q.evaluate(ans);
                if (correct) {
                    totalScore += q.getPoints();
                    results.add("Question " + (i + 1) + ": Correct (" + q.getPoints() + " points)");
                } else {
                    results.add("Question " + (i + 1) + ": Incorrect (0 points)");
                }
            }

            System.out.println("Result: " + String.join(", ", results) + ". Total score: " + totalScore + "/" + maxScore + ".");
        }
    }

    public static void main(String[] args) {
        Examination examA = new Examination("E101", "Exam A");
        examA.addQuestion(new MultipleChoiceQuestion("Q1", "Which of the following is correct?", 5, "C"));
        examA.addQuestion(new TrueFalseQuestion("Q2", "Java supports multiple inheritance for classes?", 5, false));

        Student student1 = new Student("S1", "Student 1");

        // Student 1 starts Exam A
        Attempt attempt = new Attempt(student1, examA);

        // Student 1 answers Question 1 (MCQ) with option C
        attempt.recordAnswer(1, "C");

        // Student 1 answers Question 2 (TF) with True
        attempt.recordAnswer(2, "True");

        // Student 1 submits Exam A
        attempt.submit();

        // Student 1 attempts to change answer for Question 1
        attempt.recordAnswer(1, "A");
    }
}
