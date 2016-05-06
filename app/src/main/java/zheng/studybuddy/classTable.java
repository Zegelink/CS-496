package zheng.studybuddy;

import android.provider.BaseColumns;

/**
 * Created by Zheng on 5/2/2016.
 */
public class classTable {

    public classTable(){



    }

    public static abstract class Table implements BaseColumns{

        public static final String className = "className";
        public static final String schoolName = "schoolName";
        public static final String database = "currentClassTable";
        public static final String table = "classInfo";
    }

}
