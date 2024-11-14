public class Job {
    private int job_id;
    private String job_name;
    private float min_salary;
    private float max_salary;
    
    public Job(int jobId, String jobName, float minSalary, float maxSalary){
        this.job_id = jobId;
        this.job_name = jobName;
        this.min_salary = minSalary;
        this.max_salary = maxSalary;
    }

    public int getJobId(){
        return job_id;
    }

    public String getJobName(){
        return job_name;
    }

    public float getMinSalary(){
        return min_salary;
    }

    public float getMaxSalary(){
        return max_salary;
    }

    public void setJobId(int jobId){
        this.job_id = jobId;
    }

    public void setJobName(String jobName){
        this.job_name = jobName;
    }

    public void setMinSalary(float minSalary){
        this.min_salary = minSalary;
    }

    public void setMaxSalary(float maxSalary){
        this.max_salary = maxSalary;
    }
}
