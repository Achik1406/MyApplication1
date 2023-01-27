package ftmk.bitp3453.helloclass;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;



    public class DbStudent extends SQLiteOpenHelper {

        public static final String dbName = "dbstudent";
        public static final String tblNameExpense = "students";
        public static final String colFullName = "students_name";
        public static final String colStudNum = "students_numbers";
        public static final String colEmail = "students_email";
        public static final String colBirth = "students_birthdate";
        public static final String colGender = "students_genders";
        public static final String colState = "students_state";

        public static final String strCrtTableExpenses = "CREATE TABLE " + tblNameExpense
                + " (" + colStudNum + " INTEGER PRIMARY KEY, "
                + colFullName + " TEXT, "
                + colEmail + " TEXT, "
                + colGender + " TEXT, "
                + colBirth + " DATE, "
                + colState + " TEXT" + ")";

        public static final String strDropTableExpenses = "DROP TABLE IF EXISTS " + tblNameExpense;

        public DbStudent(Context context) {
            super(context, dbName, null, 2);
        }


        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(strCrtTableExpenses);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL(strDropTableExpenses);
            onCreate(db);

        }


        public float fnInsertExpense(Student student) {
            float retResult = 0;
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put(colFullName, student.getStrFullName());
            values.put(colStudNum, student.getStrStudNo());
            values.put(colEmail, student.getStrEmail());
            values.put(colBirth, student.getStrBirthdate());
            values.put(colGender, student.getStrGender());
            values.put(colState, student.getStrState());

            retResult = db.insert(tblNameExpense, null, values);
            return retResult;
        }

        @SuppressLint("Range")
        public Student fnGetExpenses(int intExpId) {
            Student student = new Student();

            String strSelQuery = "Select * from " + tblNameExpense + "where " + colStudNum
                    + "= " + intExpId;
            Cursor cursor = this.getReadableDatabase().rawQuery(strSelQuery, null);

            if (cursor != null) {
                cursor.moveToFirst();
            }

            student.setStrFullName(cursor.getString(cursor.getColumnIndex(colFullName)));
            student.setStrStudNo(cursor.getString(cursor.getColumnIndex(colStudNum)));
            student.setStrEmail(cursor.getString(cursor.getColumnIndex(colEmail)));
            student.setStrBirthdate(cursor.getString(cursor.getColumnIndex(colBirth)));
            student.setStrState(cursor.getString(cursor.getColumnIndex(colState)));

            return student;
        }


        @SuppressLint("Range")
        public List<Student> fnGetAllExpenses() {

            List<Student> listExp = new ArrayList<>();

            String strSelAll = "Select * from " + tblNameExpense;

            Cursor cursor = this.getReadableDatabase().rawQuery(strSelAll, null);
            if (cursor.moveToFirst()) {
                do {
                    Student student = new Student();

                    student.setStrFullName(cursor.getString(cursor.getColumnIndex(colFullName)));
                    student.setStrStudNo(cursor.getString(cursor.getColumnIndex(colStudNum)));
                    student.setStrEmail(cursor.getString(cursor.getColumnIndex(colEmail)));
                    student.setStrBirthdate(cursor.getString(cursor.getColumnIndex(colBirth)));
                    student.setStrState(cursor.getString(cursor.getColumnIndex(colState)));

                    listExp.add(student);

                } while (cursor.moveToNext());
            }

            return listExp;
        }

        public int fnUpdateExpenses(Student student) {
            int retResult = 0;

            ContentValues values = new ContentValues();
            values.put(colFullName, student.getStrFullName());
            values.put(colStudNum, student.getStrStudNo());
            values.put(colEmail, student.getStrEmail());
            values.put(colBirth, student.getStrBirthdate());
            values.put(colGender, student.getStrGender());
            values.put(colState, student.getStrState());

            String[] argg = {String.valueOf(student.getStrStudNo())};

            this.getWritableDatabase().update(tblNameExpense, values, colStudNum + " = ?", argg);
            return retResult;
        }
    }

