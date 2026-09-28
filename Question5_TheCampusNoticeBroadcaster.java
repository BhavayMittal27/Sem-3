import java.util.*;

// Question 5: The Campus Notice Broadcaster
public class Question5_TheCampusNoticeBroadcaster {

    static class Notice {
        private String title;
        private List<String> targetDepartments;

        public Notice(String title, List<String> targetDepartments) {
            this.title = title;
            this.targetDepartments = targetDepartments;
        }

        public String getTitle() {
            return title;
        }

        public List<String> getTargetDepartments() {
            return targetDepartments;
        }
    }

    static interface NotificationChannel {
        void send(Student student, Notice notice);
    }

    static class EmailChannel implements NotificationChannel {
        @Override
        public void send(Student student, Notice notice) {
            System.out.println("[Email \u2192 " + student.getName() + "] " + notice.getTitle() + ".");
        }
    }

    static class SmsChannel implements NotificationChannel {
        @Override
        public void send(Student student, Notice notice) {
            System.out.println("[SMS \u2192 " + student.getName() + "] " + notice.getTitle() + ".");
        }
    }

    static class AppChannel implements NotificationChannel {
        @Override
        public void send(Student student, Notice notice) {
            System.out.println("[App \u2192 " + student.getName() + "] " + notice.getTitle() + ".");
        }
    }

    static class Student {
        private String name;
        private String department;
        private List<NotificationChannel> preferredChannels = new ArrayList<>();

        public Student(String name, String department) {
            this.name = name;
            this.department = department;
        }

        public String getName() {
            return name;
        }

        public String getDepartment() {
            return department;
        }

        public void addChannel(NotificationChannel channel) {
            preferredChannels.add(channel);
        }

        public List<NotificationChannel> getPreferredChannels() {
            return preferredChannels;
        }
    }

    static class NoticeBoard {
        private List<Student> students = new ArrayList<>();

        public void registerStudent(Student student) {
            students.add(student);
        }

        public boolean postNotice(Notice notice) {
            if (notice.getTargetDepartments() == null || notice.getTargetDepartments().isEmpty()) {
                System.out.println("Cannot post notice: At least one target department is required.");
                return false;
            }

            System.out.println("Notice '" + notice.getTitle() + "' posted to " + String.join(", ", notice.getTargetDepartments()) + ".");

            for (Student student : students) {
                if (notice.getTargetDepartments().contains(student.getDepartment())) {
                    for (NotificationChannel channel : student.getPreferredChannels()) {
                        channel.send(student, notice);
                    }
                }
            }
            return true;
        }
    }

    public static void main(String[] args) {
        NotificationChannel email = new EmailChannel();
        NotificationChannel sms = new SmsChannel();
        NotificationChannel app = new AppChannel();

        NoticeBoard noticeBoard = new NoticeBoard();

        // Asha (CSE) prefers Email and App
        Student asha = new Student("Asha", "CSE");
        asha.addChannel(email);
        asha.addChannel(app);
        noticeBoard.registerStudent(asha);

        // Ravi (ECE) prefers SMS
        Student ravi = new Student("Ravi", "ECE");
        ravi.addChannel(sms);
        noticeBoard.registerStudent(ravi);

        // Admin posts notice 'Lab Closed Tomorrow' for CSE
        Notice notice1 = new Notice("Lab Closed Tomorrow", Collections.singletonList("CSE"));
        noticeBoard.postNotice(notice1);

        // Admin posts notice 'Fee Deadline Extended' for CSE and ECE
        Notice notice2 = new Notice("Fee Deadline Extended", Arrays.asList("CSE", "ECE"));
        noticeBoard.postNotice(notice2);

        // Admin attempts to post notice 'Sports Day' with no target department
        Notice notice3 = new Notice("Sports Day", Collections.emptyList());
        noticeBoard.postNotice(notice3);
    }
}
