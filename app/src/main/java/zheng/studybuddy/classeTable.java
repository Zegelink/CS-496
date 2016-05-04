package zheng.studybuddy;

import android.provider.BaseColumns;

/**
 * Created by Zheng on 5/2/2016.
 */
public class classeTable {

    public classeTable(){



    }

    public static abstract class Table implements BaseColumns{

        public static final String classname = "classname";
        public static final String schoolname = "schoolname";
        public static final String database = "currentClassTable";
        public static final String table = "classinfo";
    }
}
