package classes;

public class Job {
    private int jobId;
    private String jobName;
    private float minSalary;
    private float maxSalary;
    
    public Job(int jobId, String jobName, float minSalary, float maxSalary){
        this.jobId = jobId;
        this.jobName = jobName;
        this.minSalary = minSalary;
        this.maxSalary = maxSalary;
    }

    public int getJobId(){
        return jobId;
    }

    public String getJobName(){
        return jobName;
    }

    public float getMinSalary(){
        return minSalary;
    }

    public float getMaxSalary(){
        return maxSalary;
    }

    public void setJobId(int jobId){
        this.jobId = jobId;
    }

    public void setJobName(String jobName){
        this.jobName = jobName;
    }

    public void setMinSalary(float minSalary){
        this.minSalary = minSalary;
    }

    public void setMaxSalary(float maxSalary){
        this.maxSalary = maxSalary;
    }
}
