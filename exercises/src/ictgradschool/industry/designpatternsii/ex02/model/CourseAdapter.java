package ictgradschool.industry.designpatternsii.ex02.model;


import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;

public class CourseAdapter extends AbstractTableModel implements CourseListener {

    private static String[] columnNames = {"Student ID", "Surname" , "Forename", "Exam", "Test", "Assignment", "Overall"};


    private Course course;

    public CourseAdapter(Course course) {
        this.course = course;
//        course.addCourseListener(this);
    }


    @Override
    public int getRowCount() {
        return course.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override

    public Object getValueAt(int row, int column) {
//        for (int i = 0; i < course.size(); i++) {
//            String record = course.getResult(i).split.
//        }
        StudentResult result = course.getResultAt(row);
        Object value = null;
        switch (column) {
            case 0:
                value = result._studentID;
                break;
            case 1:
                value = result._studentSurname;
                break;
            case 2:
                value = result._studentForename;
                break;
            case 3:
                value = result.getAssessmentElement(StudentResult.AssessmentElement.Exam);
                break;
            case 4:
                value = result.getAssessmentElement(StudentResult.AssessmentElement.Test);
                break;
            case 5:
                value = result.getAssessmentElement(StudentResult.AssessmentElement.Assignment);
                break;
            case 6:
                value = result.getAssessmentElement(StudentResult.AssessmentElement.Overall);
                break;
        }
        return value;

    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }








    @Override
    public void courseHasChanged(Course course) {
        fireTableDataChanged();
    }


    public String getColumnName(int column) {
        return columnNames[column];
    }


}