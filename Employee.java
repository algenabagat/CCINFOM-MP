import java.util.Date;

public class Employee {
    private int employee_id;
    private String employee_name;
    private String email;
    private int contact_no;
    private Job job;
    private Hotel hotel;
    private float salary;
    private Date hire_date;
    private Date end_date;

    // Constructor
    public Employee(int employee_id, String employee_name, String email, int contact_no, Job job, Hotel hotel, float salary, Date hire_date, Date end_date) {
        this.employee_id = employee_id;
        this.employee_name = employee_name;
        this.email = email;
        this.contact_no = contact_no;
        this.job = job;
        this.hotel = hotel;
        this.salary = salary;
        this.hire_date = hire_date;
        this.end_date = end_date;
    }

    // Getters and Setters
    public int getemployee_id() {
        return employee_id;
    }

    public void setemployee_id(int employee_id) {
        this.employee_id = employee_id;
    }

    public String getemployee_name() {
        return employee_name;
    }

    public void setemployee_name(String employee_name) {
        this.employee_name = employee_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getcontact_no() {
        return contact_no;
    }

    public void setcontact_no(int contact_no) {
        this.contact_no = contact_no;
    }

    public Job getJob() {
        return job;
    }

    public void setJob(Job job) {
        this.job = job;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    public float getSalary() {
        return salary;
    }

    public void setSalary(float salary) {
        this.salary = salary;
    }

    public Date gethire_date() {
        return hire_date;
    }

    public void sethire_date(Date hire_date) {
        this.hire_date = hire_date;
    }

    public Date getend_date() {
        return end_date;
    }

    public void setend_date(Date end_date) {
        this.end_date = end_date;
    }
}