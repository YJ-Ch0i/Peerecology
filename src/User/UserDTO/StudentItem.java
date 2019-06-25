package User.UserDTO;

public class StudentItem {

   public String stu_name;
   public String stu_number;
   public String stu_gender;
   public String before_class;
   public String before_number;
   
   public String getStu_name() {
      return stu_name;
   }
   public void setStu_name(String stu_name) {
      this.stu_name = stu_name;
   }
   public String getStu_number() {
      return stu_number;
   }
   public void setStu_number(String stu_number) {
      this.stu_number = stu_number;
   }
   public String getStu_gender() {
      return stu_gender;
   }
   public void setStu_gender(String stu_gender) {
      this.stu_gender = stu_gender;
   }
   public String getBefore_class() {
      return before_class;
   }
   public void setBefore_class(String before_class) {
      this.before_class = before_class;
   }
   public String getBefore_number() {
      return before_number;
   }
   public void setBefore_number(String before_number) {
      this.before_number = before_number;
   }
   

   public StudentItem(String stu_name, String stu_number, String stu_gender, String before_class,
         String before_number) {
      super();
      this.stu_name = stu_name;
      this.stu_number = stu_number;
      this.stu_gender = stu_gender;
      this.before_class = before_class;
      this.before_number = before_number;
   }
   public StudentItem() {
      super();
   }
   
   

   
   
   
}