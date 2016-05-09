package zheng.studybuddy;

/**
 * Created by zheng on 5/8/2016.
 */
public class DataProvider {

    private String className;
    private String schoolName;

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public DataProvider(String className, String schoolName){

        this.className = className;
        this.schoolName = schoolName;



    }
}
